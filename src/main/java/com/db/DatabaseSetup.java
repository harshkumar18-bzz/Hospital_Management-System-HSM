package com.db;

import com.security.Passwords;
import com.service.HospitalService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import java.sql.*;
import java.time.*;
import org.flywaydb.core.Flyway;

/** Versioned, checksum-validated migrations; fictional seed data is explicitly opt-in. */
@WebListener
public final class DatabaseSetup implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent event) {
    try {
      event.getServletContext().getSessionCookieConfig().setAttribute("SameSite", "Lax");
      event
          .getServletContext()
          .getSessionCookieConfig()
          .setSecure(Boolean.parseBoolean(DBConnect.config("SECURE_COOKIES", "false")));
      if (Boolean.parseBoolean(
          DBConnect.config("DB_MIGRATE", DBConnect.config("DB_INIT", "true")))) {
        Flyway.configure()
            .dataSource(DBConnect.dataSource())
            .baselineOnMigrate(
                Boolean.parseBoolean(
                    DBConnect.config("DB_BASELINE_LEGACY", DBConnect.config("DEMO_MODE", "false"))))
            .baselineVersion("0")
            .locations("classpath:db/migration")
            .cleanDisabled(true)
            .load()
            .migrate();
      }
      if (Boolean.parseBoolean(DBConnect.config("DEMO_MODE", "false"))) seedDemo();
    } catch (Exception e) {
      DBConnect.shutdown();
      throw new IllegalStateException(
          "Hospital database startup failed; inspect migration configuration.", e);
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    DBConnect.shutdown();
  }

  private void seedDemo() throws SQLException {
    try (Connection c = DBConnect.open()) {
      c.setAutoCommit(false);
      try {
        for (String specialty :
            new String[] {"General Medicine", "Cardiology", "Pediatrics", "Dermatology"}) {
          try (PreparedStatement ps =
              c.prepareStatement(
                  "INSERT INTO specialist(spec_name) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM"
                      + " specialist WHERE spec_name=?)")) {
            ps.setString(1, specialty);
            ps.setString(2, specialty);
            ps.executeUpdate();
          }
        }
        String[][] doctors = {
          {"Dr. Aditi Shah", "General Medicine", "aditi@hospital.test"},
          {"Dr. Arjun Mehta", "Cardiology", "arjun@hospital.test"},
          {"Dr. Neha Rao", "Pediatrics", "neha@hospital.test"},
          {"Dr. Rohan Patel", "Dermatology", "rohan@hospital.test"}
        };
        for (String[] d : doctors) {
          boolean exists;
          try (PreparedStatement ps = c.prepareStatement("SELECT id FROM doctor WHERE email=?")) {
            ps.setString(1, d[2]);
            try (ResultSet rs = ps.executeQuery()) {
              exists = rs.next();
            }
          }
          if (!exists)
            try (PreparedStatement ps =
                c.prepareStatement(
                    "INSERT INTO"
                        + " doctor(full_name,dob,qualification,specialist,email,mobno,password)"
                        + " VALUES(?,?,?,?,?,?,?)")) {
              ps.setString(1, d[0]);
              ps.setString(2, "1985-06-15");
              ps.setString(3, "MBBS, MD");
              ps.setString(4, d[1]);
              ps.setString(5, d[2]);
              ps.setString(6, "9000000000");
              ps.setString(7, Passwords.hash("Doctor@123"));
              ps.executeUpdate();
            }
        }
        try (PreparedStatement ps = c.prepareStatement("SELECT id FROM doctor WHERE active=TRUE");
            ResultSet rs = ps.executeQuery()) {
          while (rs.next())
            for (int day = 1; day <= 14; day++)
              for (int hour : new int[] {9, 10, 11, 14, 15}) {
                LocalDateTime start =
                    LocalDate.now(HospitalService.zone()).plusDays(day).atTime(hour, 0);
                try (PreparedStatement insert =
                    c.prepareStatement(
                        "INSERT INTO doctor_slot(doctor_id,starts_at) SELECT ?,? WHERE NOT EXISTS"
                            + " (SELECT 1 FROM doctor_slot WHERE doctor_id=? AND starts_at=?)")) {
                  insert.setInt(1, rs.getInt(1));
                  insert.setTimestamp(2, Timestamp.valueOf(start));
                  insert.setInt(3, rs.getInt(1));
                  insert.setTimestamp(4, Timestamp.valueOf(start));
                  insert.executeUpdate();
                }
              }
        }
        c.commit();
      } catch (SQLException | RuntimeException e) {
        c.rollback();
        throw e;
      }
    }
  }
}
