package com.service;

import static org.junit.Assert.*;

import com.zaxxer.hikari.*;
import java.lang.reflect.*;
import java.sql.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.junit.*;

/** Same contract runs against H2 by default and real MySQL in CI/local integration mode. */
public class AppointmentServiceTest {
  private static HikariDataSource source;
  private static final Clock CLOCK =
      Clock.fixed(Instant.parse("2030-01-01T04:00:00Z"), ZoneId.of("Asia/Kolkata"));
  private AppointmentService service;
  private int patient, otherPatient, doctor, otherDoctor;
  private long slot;
  private final LocalDateTime start = LocalDateTime.of(2030, 1, 2, 10, 0);

  @BeforeClass
  public static void database() {
    String url =
        System.getProperty(
            "test.db.url",
            "jdbc:h2:mem:hms_test;MODE=MySQL;DATABASE_TO_LOWER=TRUE;DB_CLOSE_DELAY=-1;LOCK_TIMEOUT=5000");
    if (!url.contains("hms_test"))
      throw new IllegalArgumentException("Use an isolated database named hms_test.");
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(url);
    config.setUsername(System.getProperty("test.db.user", "sa"));
    config.setPassword(System.getProperty("test.db.password", ""));
    config.setMaximumPoolSize(6);
    source = new HikariDataSource(config);
    Flyway flyway =
        Flyway.configure().dataSource(source).baselineOnMigrate(true).baselineVersion("0").load();
    flyway.migrate();
    assertEquals(0, flyway.migrate().migrationsExecuted);
  }

  @AfterClass
  public static void shutdown() {
    source.close();
  }

  private int insert(String sql, String... values) throws SQLException {
    try (Connection c = source.getConnection();
        PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      for (int i = 0; i < values.length; i++) ps.setString(i + 1, values[i]);
      ps.executeUpdate();
      try (ResultSet rs = ps.getGeneratedKeys()) {
        rs.next();
        return rs.getInt(1);
      }
    }
  }

  @Before
  public void fixtures() throws SQLException {
    String key = UUID.randomUUID().toString();
    patient =
        insert(
            "INSERT INTO user_dtls(full_name,email,password) VALUES(?,?,?)",
            "Synthetic patient",
            key + "@patient.test",
            "not-used-by-authentication");
    otherPatient =
        insert(
            "INSERT INTO user_dtls(full_name,email,password) VALUES(?,?,?)",
            "Other patient",
            key + "@other.test",
            "not-used-by-authentication");
    doctor =
        insert(
            "INSERT INTO doctor(full_name,dob,qualification,specialist,email,mobno,password)"
                + " VALUES(?,?,?,?,?,?,?)",
            "Synthetic doctor",
            "1980-01-01",
            "MD",
            "General",
            key + "@doctor.test",
            "9000000000",
            "unused");
    otherDoctor =
        insert(
            "INSERT INTO doctor(full_name,dob,qualification,specialist,email,mobno,password)"
                + " VALUES(?,?,?,?,?,?,?)",
            "Other doctor",
            "1980-01-01",
            "MD",
            "General",
            key + "@other-doctor.test",
            "9000000000",
            "unused");
    service = new AppointmentService(source, CLOCK);
    slot = service.publish(Actor.doctor(doctor), doctor, start);
  }

  private AppointmentService.Booking booking(String key) {
    return new AppointmentService.Booking(
        slot,
        key,
        "Synthetic patient",
        "other",
        "24",
        "test@hospital.test",
        "9000000000",
        "Synthetic reason",
        "Test address");
  }

  private long count(String sql, long id) throws SQLException {
    try (Connection c = source.getConnection();
        PreparedStatement ps = c.prepareStatement(sql)) {
      ps.setLong(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        rs.next();
        return rs.getLong(1);
      }
    }
  }

  private void status(int expected, Runnable action) {
    ServiceException e = assertThrows(ServiceException.class, action::run);
    assertEquals(expected, e.getStatus());
  }

  @Test
  public void concurrentPatientsCannotClaimSameSlot() throws Exception {
    ExecutorService threads = Executors.newFixedThreadPool(2);
    CountDownLatch ready = new CountDownLatch(2), go = new CountDownLatch(1);
    List<Future<Integer>> futures = new ArrayList<>();
    try {
      for (int user : new int[] {patient, otherPatient})
        futures.add(
            threads.submit(() -> {
              ready.countDown();
              go.await();
              try {
                return new AppointmentService(source, CLOCK)
                    .book(user, booking(UUID.randomUUID().toString()));
              } catch (ServiceException e) {
                assertEquals(409, e.getStatus());
                return -1;
              }
            }));
      assertTrue(ready.await(5, TimeUnit.SECONDS));
      go.countDown();
      int successes = 0;
      for (Future<Integer> future : futures) if (future.get(15, TimeUnit.SECONDS) > 0) successes++;
      assertEquals(1, successes);
      assertEquals(1, count("SELECT COUNT(*) FROM slot_reservation WHERE slot_id=?", slot));
    } finally {
      threads.shutdownNow();
    }
  }

  @Test
  public void concurrentRetriesAreIdempotentAndPayloadReuseIsRejected() throws Exception {
    var booking = booking(UUID.randomUUID().toString());
    ExecutorService threads = Executors.newFixedThreadPool(2);
    CountDownLatch go = new CountDownLatch(1);
    try {
      Future<Integer> a =
          threads.submit(() -> {
            go.await();
            return service.book(patient, booking);
          });
      Future<Integer> b =
          threads.submit(() -> {
            go.await();
            return new AppointmentService(source, CLOCK).book(patient, booking);
          });
      go.countDown();
      assertEquals(a.get(15, TimeUnit.SECONDS), b.get(15, TimeUnit.SECONDS));
      assertEquals(1, count("SELECT COUNT(*) FROM appointment WHERE slot_id=?", slot));
      var changed =
          new AppointmentService.Booking(
              slot,
              booking.requestKey(),
              "Different name",
              "other",
              "24",
              "test@hospital.test",
              "9000000000",
              "Synthetic reason",
              "Test address");
      status(409, () -> service.book(patient, changed));
    } finally {
      threads.shutdownNow();
    }
  }

  @Test
  public void cancellationReleasesSlotAndRetainsHistory() throws Exception {
    int first = service.book(patient, booking(UUID.randomUUID().toString()));
    service.transition(Actor.patient(patient), first, "CANCELLED");
    service.transition(Actor.patient(patient), first, "CANCELLED");
    assertEquals("CANCELLED", service.detail(Actor.patient(patient), first).status());
    int next = service.book(otherPatient, booking(UUID.randomUUID().toString()));
    assertNotEquals(first, next);
    assertEquals(2, count("SELECT COUNT(*) FROM appointment WHERE slot_id=?", slot));
    assertEquals(2, service.events(Actor.patient(patient), first).size());
  }

  @Test
  public void ownershipAndLifecycleAreEnforced() {
    int id = service.book(patient, booking(UUID.randomUUID().toString()));
    status(404, () -> service.detail(Actor.patient(otherPatient), id));
    status(404, () -> service.addNote(Actor.doctor(otherDoctor), id, "forged doctor"));
    status(403, () -> service.transition(Actor.patient(patient), id, "CONFIRMED"));
    status(409, () -> service.transition(Actor.doctor(doctor), id, "COMPLETED"));
    service.transition(Actor.doctor(doctor), id, "CONFIRMED");
    status(409, () -> service.transition(Actor.doctor(doctor), id, "COMPLETED"));
    service.addNote(Actor.doctor(doctor), id, "First note");
    service.addNote(Actor.doctor(doctor), id, "Follow-up note");
    assertEquals("CONFIRMED", service.detail(Actor.patient(patient), id).status());
    assertEquals(2, service.notes(Actor.patient(patient), id).size());
    AppointmentService afterVisit =
        new AppointmentService(
            source, Clock.fixed(Instant.parse("2030-01-02T06:00:00Z"), CLOCK.getZone()));
    afterVisit.transition(Actor.doctor(doctor), id, "COMPLETED");
    status(409, () -> afterVisit.transition(Actor.patient(patient), id, "CANCELLED"));
    assertEquals("COMPLETED", afterVisit.detail(Actor.patient(patient), id).status());
  }

  @Test
  public void failedAuditWriteRollsBackBookingReservationAndRequestKey() throws Exception {
    DataSource failing =
        (DataSource)
            Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[] {DataSource.class},
                (proxy, method, args) -> {
                  try {
                    Object result = method.invoke(source, args);
                    if (method.getName().equals("getConnection"))
                      return Proxy.newProxyInstance(
                          getClass().getClassLoader(),
                          new Class[] {Connection.class},
                          (p, m, a) -> {
                            if (m.getName().equals("prepareStatement")
                                && a[0].toString().contains("INSERT INTO appointment_event"))
                              throw new SQLException("Injected test audit failure");
                            try {
                              return m.invoke(result, a);
                            } catch (InvocationTargetException e) {
                              throw e.getCause();
                            }
                          });
                    return result;
                  } catch (InvocationTargetException e) {
                    throw e.getCause();
                  }
                });
    assertThrows(
        DataAccessException.class,
        () ->
            new AppointmentService(failing, CLOCK)
                .book(patient, booking(UUID.randomUUID().toString())));
    assertEquals(0, count("SELECT COUNT(*) FROM appointment WHERE slot_id=?", slot));
    assertEquals(0, count("SELECT COUNT(*) FROM slot_reservation WHERE slot_id=?", slot));
    assertEquals(0, count("SELECT COUNT(*) FROM booking_request WHERE user_id=?", patient));
    assertTrue(service.book(patient, booking(UUID.randomUUID().toString())) > 0);
  }

  @Test
  public void publishedSlotsAreBoundedAndWithdrawalIsSafe() {
    status(400, () -> service.publish(Actor.doctor(doctor), doctor, start.plusMinutes(15)));
    status(403, () -> service.publish(Actor.doctor(otherDoctor), doctor, start.plusHours(1)));
    status(409, () -> service.publish(Actor.doctor(doctor), doctor, start));
    int id = service.book(patient, booking(UUID.randomUUID().toString()));
    status(409, () -> service.disable(Actor.doctor(doctor), slot));
    service.transition(Actor.patient(patient), id, "CANCELLED");
    service.disable(Actor.doctor(doctor), slot);
    status(409, () -> service.book(patient, booking(UUID.randomUUID().toString())));
  }

  @Test
  public void everyOperationReturnsItsPooledConnection() {
    service.available(doctor);
    service.list(Actor.patient(patient), 1);
    assertEquals(0, source.getHikariPoolMXBean().getActiveConnections());
  }

  @Test
  public void successfulLoginUpgradesAnOlderHashWithoutRevokingItsIdentity() throws Exception {
    String password = "LegacyPassword@123";
    byte[] salt = new byte[16];
    new java.security.SecureRandom().nextBytes(salt);
    var spec = new javax.crypto.spec.PBEKeySpec(password.toCharArray(), salt, 210000, 256);
    String previous =
        "pbkdf2$210000$"
            + Base64.getEncoder().encodeToString(salt)
            + "$"
            + Base64.getEncoder()
                .encodeToString(
                    javax.crypto.SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                        .generateSecret(spec)
                        .getEncoded());
    spec.clearPassword();
    try (Connection c = source.getConnection()) {
      String email;
      try (PreparedStatement ps =
          c.prepareStatement("UPDATE user_dtls SET password=? WHERE id=?")) {
        ps.setString(1, previous);
        ps.setInt(2, patient);
        ps.executeUpdate();
      }
      try (PreparedStatement ps = c.prepareStatement("SELECT email FROM user_dtls WHERE id=?")) {
        ps.setInt(1, patient);
        try (ResultSet rs = ps.executeQuery()) {
          rs.next();
          email = rs.getString(1);
        }
      }
      var user = new com.dao.UserDao(c).login(email, password);
      assertNotNull(user);
      assertEquals(patient, user.getId());
      assertEquals(0, user.getAuthVersion());
      assertNull(user.getPassword());
      try (PreparedStatement ps = c.prepareStatement("SELECT password FROM user_dtls WHERE id=?")) {
        ps.setInt(1, patient);
        try (ResultSet rs = ps.executeQuery()) {
          rs.next();
          assertTrue(rs.getString(1).startsWith("pbkdf2$600000$"));
        }
      }
    }
  }
}
