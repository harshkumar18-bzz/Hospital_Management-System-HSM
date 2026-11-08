package com.service;

import com.dao.AppointmentDao;
import com.entity.*;
import java.security.MessageDigest;
import java.sql.*;
import java.time.*;
import java.util.*;
import javax.sql.DataSource;

/** All scheduling mutations and their audit records share one database transaction. */
public final class AppointmentService {
  private final DataSource source;
  private final Clock clock;

  public AppointmentService(DataSource source, Clock clock) {
    this.source = source;
    this.clock = clock;
  }

  public record Booking(
      long slotId,
      String requestKey,
      String fullName,
      String gender,
      String age,
      String email,
      String phone,
      String reason,
      String address) {}

  @FunctionalInterface
  interface Work<T> {
    T run(Connection c) throws SQLException;
  }

  private <T> T transaction(Work<T> work) {
    try (Connection c = source.getConnection()) {
      c.setAutoCommit(false);
      try {
        T result = work.run(c);
        c.commit();
        return result;
      } catch (SQLException | RuntimeException e) {
        try {
          c.rollback();
        } catch (SQLException failure) {
          e.addSuppressed(failure);
        }
        throw e;
      }
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }

  private <T> T read(Work<T> work) {
    try (Connection c = source.getConnection()) {
      return work.run(c);
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }

  private LocalDateTime now() {
    return LocalDateTime.now(clock);
  }

  private static void require(boolean condition, int status, String message) {
    if (!condition) throw new ServiceException(status, message);
  }

  private void lockDoctor(Connection c, int id) throws SQLException {
    try (PreparedStatement ps =
        c.prepareStatement("SELECT active FROM doctor WHERE id=? FOR UPDATE")) {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        require(rs.next() && rs.getBoolean(1), 404, "Doctor is unavailable.");
      }
    }
  }

  private record LockedSlot(int doctorId, LocalDateTime startsAt, boolean enabled) {}

  private LockedSlot slot(Connection c, long id, boolean lock) throws SQLException {
    try (PreparedStatement ps =
        c.prepareStatement(
            "SELECT doctor_id,starts_at,enabled FROM doctor_slot WHERE id=?"
                + (lock ? " FOR UPDATE" : ""))) {
      ps.setLong(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        require(rs.next(), 404, "Slot not found.");
        return new LockedSlot(rs.getInt(1), rs.getTimestamp(2).toLocalDateTime(), rs.getBoolean(3));
      }
    }
  }

  private void event(Connection c, int id, Actor actor, String from, String to, String action)
      throws SQLException {
    try (PreparedStatement ps =
        c.prepareStatement(
            "INSERT INTO"
                + " appointment_event(appointment_id,actor_role,actor_id,from_status,to_status,action)"
                + " VALUES(?,?,?,?,?,?)")) {
      ps.setInt(1, id);
      ps.setString(2, actor.role());
      ps.setInt(3, actor.id());
      ps.setString(4, from);
      ps.setString(5, to);
      ps.setString(6, action);
      ps.executeUpdate();
    }
  }

  private String fingerprint(Booking b) {
    try {
      var bytes = new java.io.ByteArrayOutputStream();
      try (var out = new java.io.DataOutputStream(bytes)) {
        out.writeLong(b.slotId());
        for (String field :
            List.of(
                b.fullName(), b.gender(), b.age(), b.email(), b.phone(), b.reason(), b.address()))
          out.writeUTF(field);
      }
      return HexFormat.of()
          .formatHex(MessageDigest.getInstance("SHA-256").digest(bytes.toByteArray()));
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  private void validate(Booking b) {
    require(
        b.requestKey() != null && b.requestKey().matches("[A-Za-z0-9-]{16,64}"),
        400,
        "Reload the booking form before submitting.");
    String[] fields = {
      b.fullName(), b.gender(), b.age(), b.email(), b.phone(), b.reason(), b.address()
    };
    int[] limits = {120, 30, 3, 254, 20, 500, 500};
    for (int i = 0; i < fields.length; i++)
      require(
          fields[i] != null
              && !fields[i].isBlank()
              && fields[i].length() <= limits[i]
              && !fields[i].chars().anyMatch(x -> x == 0),
          400,
          "Complete all appointment fields within the allowed lengths.");
    require(
        Set.of("male", "female", "other", "prefer not to say").contains(b.gender()),
        400,
        "Choose a valid gender.");
    int age;
    try {
      age = Integer.parseInt(b.age());
    } catch (NumberFormatException e) {
      throw new ServiceException(400, "Enter a valid age.");
    }
    require(age >= 0 && age <= 120, 400, "Age must be between 0 and 120.");
    require(b.email().matches("[^\\s@]+@[^\\s@]+\\.[^\\s@]+"), 400, "Enter a valid email.");
    require(b.phone().matches("(?=.*[0-9])[+0-9 ()-]{7,20}"), 400, "Enter a valid phone number.");
  }

  public int book(int userId, Booking booking) {
    validate(booking);
    String digest = fingerprint(booking);
    try {
      return transaction(c -> {
        // A unique request key serializes duplicate submissions across application instances.
        try (PreparedStatement ps =
            c.prepareStatement(
                "INSERT INTO booking_request(user_id,request_key,payload_hash) VALUES(?,?,?)")) {
          ps.setInt(1, userId);
          ps.setString(2, booking.requestKey());
          ps.setString(3, digest);
          ps.executeUpdate();
        }
        LockedSlot initial = slot(c, booking.slotId(), false);
        // All mutations lock doctor -> slot -> appointment in the same order.
        lockDoctor(c, initial.doctorId());
        LockedSlot selected = slot(c, booking.slotId(), true);
        require(
            selected.enabled() && selected.startsAt().isAfter(now()),
            409,
            "This slot is no longer available. Choose another slot.");
        try (PreparedStatement ps =
            c.prepareStatement("SELECT appointment_id FROM slot_reservation WHERE slot_id=?")) {
          ps.setLong(1, booking.slotId());
          try (ResultSet rs = ps.executeQuery()) {
            require(!rs.next(), 409, "Another patient has just booked this slot. Choose another.");
          }
        }
        int id;
        try (PreparedStatement ps =
            c.prepareStatement(
                """
                INSERT INTO appointment(user_id,fullname,gender,age,appoint_date,email,phno,diseases,doctor_id,address,status,slot_id)
                VALUES(?,?,?,?,?,?,?,?,?,?,'PENDING',?)
                """,
                Statement.RETURN_GENERATED_KEYS)) {
          ps.setInt(1, userId);
          ps.setString(2, booking.fullName());
          ps.setString(3, booking.gender());
          ps.setString(4, booking.age());
          ps.setDate(5, java.sql.Date.valueOf(selected.startsAt().toLocalDate()));
          ps.setString(6, booking.email());
          ps.setString(7, booking.phone());
          ps.setString(8, booking.reason());
          ps.setInt(9, selected.doctorId());
          ps.setString(10, booking.address());
          ps.setLong(11, booking.slotId());
          ps.executeUpdate();
          try (ResultSet rs = ps.getGeneratedKeys()) {
            if (!rs.next()) throw new SQLException("Missing generated appointment ID");
            id = rs.getInt(1);
          }
        }
        try (PreparedStatement ps =
            c.prepareStatement(
                "INSERT INTO slot_reservation(slot_id,appointment_id) VALUES(?,?)")) {
          ps.setLong(1, booking.slotId());
          ps.setInt(2, id);
          ps.executeUpdate();
        }
        try (PreparedStatement ps =
            c.prepareStatement(
                "UPDATE booking_request SET appointment_id=? WHERE user_id=? AND request_key=?")) {
          ps.setInt(1, id);
          ps.setInt(2, userId);
          ps.setString(3, booking.requestKey());
          ps.executeUpdate();
        }
        event(c, id, Actor.patient(userId), null, "PENDING", "BOOKED");
        return id;
      });
    } catch (DataAccessException failure) {
      if (failure.getCause() instanceof SQLException sql && isDuplicate(sql)) {
        Integer previous =
            read(c -> {
              try (PreparedStatement ps =
                  c.prepareStatement(
                      "SELECT payload_hash,appointment_id FROM booking_request WHERE user_id=? AND"
                          + " request_key=?")) {
                ps.setInt(1, userId);
                ps.setString(2, booking.requestKey());
                try (ResultSet rs = ps.executeQuery()) {
                  if (!rs.next()) return null;
                  require(
                      digest.equals(rs.getString(1)),
                      409,
                      "This request key was already used for different booking details.");
                  return rs.getInt(2);
                }
              }
            });
        if (previous != null) return previous;
        throw new ServiceException(409, "This slot has already been reserved.");
      }
      throw failure;
    }
  }

  public static boolean isDuplicate(SQLException e) {
    return "23505".equals(e.getSQLState()) || e.getErrorCode() == 1062;
  }

  private void authorize(Actor actor, AppointmentRow row) {
    require(row != null, 404, "Appointment not found.");
    require(
        actor.role().equals("ADMIN")
            || actor.role().equals("PATIENT") && row.userId() == actor.id()
            || actor.role().equals("DOCTOR") && row.doctorId() == actor.id(),
        404,
        "Appointment not found.");
  }

  public AppointmentRow detail(Actor actor, int id) {
    return read(c -> {
      var row = new AppointmentDao(c).find(id);
      authorize(actor, row);
      return row;
    });
  }

  public List<AppointmentRow> list(Actor actor, int page) {
    return read(c -> new AppointmentDao(c).list(actor.role(), actor.id(), page));
  }

  public List<Note> notes(Actor actor, int id) {
    return read(c -> {
      var dao = new AppointmentDao(c);
      authorize(actor, dao.find(id));
      return dao.notes(id);
    });
  }

  public List<AuditEvent> events(Actor actor, int id) {
    return read(c -> {
      var dao = new AppointmentDao(c);
      authorize(actor, dao.find(id));
      return dao.events(id);
    });
  }

  public List<String> allowedActions(Actor actor, AppointmentRow row) {
    authorize(actor, row);
    List<String> actions = new ArrayList<>();
    LocalDateTime start =
        row.slotId() == null ? null : LocalDateTime.parse(row.slotTime().replace(' ', 'T'));
    if (row.isOpen() && (start == null || start.isAfter(now()))) actions.add("CANCELLED");
    if (!actor.role().equals("PATIENT")
        && row.status().equals("PENDING")
        && start != null
        && start.isAfter(now())) actions.add("CONFIRMED");
    if (!actor.role().equals("PATIENT")
        && row.status().equals("CONFIRMED")
        && start != null
        && !start.isAfter(now())) actions.add("COMPLETED");
    return actions;
  }

  public void transition(Actor actor, int id, String target) {
    require(
        Set.of("CONFIRMED", "COMPLETED", "CANCELLED").contains(target),
        400,
        "Choose a valid appointment action.");
    transaction(c -> {
      var dao = new AppointmentDao(c);
      var initial = dao.find(id);
      authorize(actor, initial);
      lockDoctor(c, initial.doctorId());
      LockedSlot selected = initial.slotId() == null ? null : slot(c, initial.slotId(), true);
      try (PreparedStatement ps =
          c.prepareStatement("SELECT status FROM appointment WHERE id=? FOR UPDATE")) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
          require(rs.next(), 404, "Appointment not found.");
        }
      }
      var current = dao.find(id);
      authorize(actor, current);
      if (current.status().equals(target)) return null; // Safe retry of the same state action.
      boolean patient = actor.role().equals("PATIENT");
      require(
          !patient || target.equals("CANCELLED"),
          403,
          "Patients can only cancel their own appointments.");
      if (target.equals("CONFIRMED"))
        require(
            current.status().equals("PENDING")
                && selected != null
                && selected.startsAt().isAfter(now()),
            409,
            "Only future pending appointments can be confirmed.");
      if (target.equals("COMPLETED"))
        require(
            !patient
                && current.status().equals("CONFIRMED")
                && selected != null
                && !selected.startsAt().isAfter(now()),
            409,
            "Complete a confirmed appointment after its start time.");
      if (target.equals("CANCELLED"))
        require(
            current.isOpen() && (selected == null || selected.startsAt().isAfter(now())),
            409,
            "Only upcoming pending or confirmed appointments can be cancelled.");
      try (PreparedStatement ps =
          c.prepareStatement("UPDATE appointment SET status=? WHERE id=?")) {
        ps.setString(1, target);
        ps.setInt(2, id);
        ps.executeUpdate();
      }
      if (target.equals("CANCELLED")) {
        try (PreparedStatement ps =
            c.prepareStatement("DELETE FROM slot_reservation WHERE appointment_id=?")) {
          ps.setInt(1, id);
          ps.executeUpdate();
        }
      }
      event(c, id, actor, current.status(), target, "STATUS_CHANGED");
      return null;
    });
  }

  public void addNote(Actor actor, int id, String body) {
    require(
        actor.role().equals("DOCTOR"),
        403,
        "Only the assigned doctor can write a consultation note.");
    require(
        body != null && !body.isBlank() && body.length() <= 1000,
        400,
        "Write a note of 1 to 1000 characters.");
    transaction(c -> {
      var dao = new AppointmentDao(c);
      var row = dao.find(id);
      authorize(actor, row);
      lockDoctor(c, row.doctorId());
      try (PreparedStatement ps =
          c.prepareStatement("SELECT status FROM appointment WHERE id=? FOR UPDATE")) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
          require(rs.next(), 404, "Appointment not found.");
          require(
              !rs.getString(1).equals("CANCELLED"),
              409,
              "Cancelled appointments cannot receive new notes.");
        }
      }
      try (PreparedStatement ps =
          c.prepareStatement(
              "INSERT INTO appointment_note(appointment_id,doctor_id,body) VALUES(?,?,?)")) {
        ps.setInt(1, id);
        ps.setInt(2, actor.id());
        ps.setString(3, body.trim());
        ps.executeUpdate();
      }
      var current = dao.find(id);
      event(c, id, actor, current.status(), current.status(), "NOTE_ADDED");
      return null;
    });
  }

  public List<Slot> available(Integer doctorId) {
    return read(c -> {
      String filter = doctorId == null ? "" : " AND s.doctor_id=?";
      try (PreparedStatement ps =
          c.prepareStatement(
              """
              SELECT s.id,s.doctor_id,d.full_name,d.specialist,s.starts_at FROM doctor_slot s
              JOIN doctor d ON d.id=s.doctor_id LEFT JOIN slot_reservation r ON r.slot_id=s.id
              WHERE s.enabled=TRUE AND d.active=TRUE AND r.slot_id IS NULL AND s.starts_at>?
              """
                  + filter
                  + " ORDER BY s.starts_at,s.doctor_id LIMIT 200")) {
        ps.setTimestamp(1, Timestamp.valueOf(now()));
        if (doctorId != null) ps.setInt(2, doctorId);
        try (ResultSet rs = ps.executeQuery()) {
          List<Slot> slots = new ArrayList<>();
          while (rs.next())
            slots.add(
                new Slot(
                    rs.getLong(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getTimestamp(5).toLocalDateTime()));
          return slots;
        }
      }
    });
  }

  public long publish(Actor actor, int doctorId, LocalDateTime start) {
    require(
        actor.role().equals("ADMIN") || actor.role().equals("DOCTOR") && actor.id() == doctorId,
        403,
        "You cannot publish slots for this doctor.");
    require(
        start != null
            && start.isAfter(now())
            && !start.isAfter(now().plusDays(90))
            && start.getMinute() % 30 == 0
            && start.getSecond() == 0
            && start.getNano() == 0,
        400,
        "Choose a future half-hour start within 90 days.");
    try {
      return transaction(c -> {
        lockDoctor(c, doctorId);
        try (PreparedStatement ps =
            c.prepareStatement(
                "INSERT INTO doctor_slot(doctor_id,starts_at) VALUES(?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
          ps.setInt(1, doctorId);
          ps.setTimestamp(2, Timestamp.valueOf(start));
          ps.executeUpdate();
          try (ResultSet rs = ps.getGeneratedKeys()) {
            rs.next();
            return rs.getLong(1);
          }
        }
      });
    } catch (DataAccessException e) {
      if (e.getCause() instanceof SQLException sql && isDuplicate(sql))
        throw new ServiceException(409, "This slot already exists.");
      throw e;
    }
  }

  public void disable(Actor actor, long slotId) {
    transaction(c -> {
      LockedSlot initial = slot(c, slotId, false);
      require(
          actor.role().equals("ADMIN")
              || actor.role().equals("DOCTOR") && actor.id() == initial.doctorId(),
          404,
          "Slot not found.");
      lockDoctor(c, initial.doctorId());
      slot(c, slotId, true);
      try (PreparedStatement ps =
          c.prepareStatement("SELECT appointment_id FROM slot_reservation WHERE slot_id=?")) {
        ps.setLong(1, slotId);
        try (ResultSet rs = ps.executeQuery()) {
          require(!rs.next(), 409, "Cancel the appointment before withdrawing its slot.");
        }
      }
      try (PreparedStatement ps =
          c.prepareStatement("UPDATE doctor_slot SET enabled=FALSE WHERE id=?")) {
        ps.setLong(1, slotId);
        ps.executeUpdate();
      }
      return null;
    });
  }
}
