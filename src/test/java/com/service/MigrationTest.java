package com.service;

import static org.junit.Assert.*;

import com.zaxxer.hikari.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import org.flywaydb.core.Flyway;
import org.junit.Test;

public class MigrationTest {
  @Test
  public void preservesLegacyNotesAndAppointmentsWithoutInventingSlots() throws Exception {
    String primary = System.getProperty("test.db.url", "");
    String legacy = "jdbc:h2:mem:legacy;MODE=MySQL;DATABASE_TO_LOWER=TRUE";
    if (primary.startsWith("jdbc:mysql:")) {
      if (!primary.contains("/hms_test"))
        throw new IllegalArgumentException("Use an isolated test database.");
      String database =
          "hms_test_legacy_" + java.util.UUID.randomUUID().toString().replace("-", "");
      try (Connection connection =
              DriverManager.getConnection(
                  primary,
                  System.getProperty("test.db.user", "root"),
                  System.getProperty("test.db.password", ""));
          Statement create = connection.createStatement()) {
        create.execute("CREATE DATABASE " + database);
      }
      int query = primary.indexOf('?');
      String suffix = query < 0 ? "" : primary.substring(query);
      legacy = primary.substring(0, primary.lastIndexOf('/') + 1) + database + suffix;
    }
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(legacy);
    config.setUsername(System.getProperty("test.db.user", "sa"));
    config.setPassword(System.getProperty("test.db.password", ""));
    try (HikariDataSource source = new HikariDataSource(config);
        Connection c = source.getConnection();
        Statement s = c.createStatement()) {
      String schema =
          new String(
              getClass().getResourceAsStream("/db/migration/V1__initial_schema.sql").readAllBytes(),
              StandardCharsets.UTF_8);
      for (String statement : schema.split(";")) if (!statement.isBlank()) s.execute(statement);
      s.execute("INSERT INTO user_dtls VALUES(1,'Legacy patient','legacy@test.test','legacy')");
      s.execute(
          "INSERT INTO doctor VALUES(1,'Legacy"
              + " doctor','1980-01-01','MD','General','legacy-doctor@test.test','9000000000','legacy')");
      s.execute(
          "INSERT INTO appointment VALUES(1,1,'Legacy"
              + " patient','other','20','2025-01-01','legacy@test.test','9000000000','Reason',1,'Address','Original"
              + " clinical note')");
      Flyway.configure()
          .dataSource(source)
          .baselineOnMigrate(true)
          .baselineVersion("0")
          .load()
          .migrate();
      try (ResultSet rs = s.executeQuery("SELECT status,slot_id FROM appointment WHERE id=1")) {
        rs.next();
        assertEquals("COMPLETED", rs.getString(1));
        assertNull(rs.getObject(2));
      }
      try (ResultSet rs =
          s.executeQuery("SELECT body FROM appointment_note WHERE appointment_id=1")) {
        rs.next();
        assertEquals("Original clinical note", rs.getString(1));
      }
    }
  }
}
