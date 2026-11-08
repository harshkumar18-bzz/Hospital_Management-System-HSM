package com.service;

import com.dao.*;
import com.db.DBConnect;
import com.entity.*;
import com.security.Passwords;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;
import java.time.*;
import java.util.*;

/** Application facade: controllers never open a connection or execute SQL. */
public final class HospitalService {
  public static ZoneId zone() {
    return ZoneId.of(DBConnect.config("HOSPITAL_ZONE", "Asia/Kolkata"));
  }

  public static AppointmentService appointments() {
    return new AppointmentService(DBConnect.dataSource(), Clock.system(zone()));
  }

  @FunctionalInterface
  private interface Work<T> {
    T run(Connection c) throws SQLException;
  }

  private static <T> T read(Work<T> work) {
    try (Connection c = DBConnect.open()) {
      return work.run(c);
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }

  public static User loginPatient(String email, String password) {
    return read(c -> new UserDao(c).login(email, password));
  }

  public static Doctor loginDoctor(String email, String password) {
    return read(c -> new DoctorDao(c).login(email, password));
  }

  public static void register(User user) {
    try {
      read(c -> new UserDao(c).register(user));
    } catch (DataAccessException e) {
      if (e.getCause() instanceof SQLException sql && AppointmentService.isDuplicate(sql))
        throw new ServiceException(
            409, "An account already uses this email. Sign in or use another email.");
      throw e;
    }
  }

  public static List<Doctor> doctors(boolean archived) {
    return read(c -> new DoctorDao(c).list(archived));
  }

  public static Doctor doctor(int id) {
    return read(c -> {
      Doctor doctor = new DoctorDao(c).find(id);
      if (doctor == null) throw new ServiceException(404, "Doctor not found.");
      return doctor;
    });
  }

  public static List<Specalist> specialties() {
    return read(c -> new SpecialistDao(c).list());
  }

  public static Map<String, Long> counts(Integer doctor) {
    return read(c -> new DoctorDao(c).counts(doctor));
  }

  public static void specialty(String name) {
    try {
      read(c -> {
        new SpecialistDao(c).add(name);
        return null;
      });
    } catch (DataAccessException e) {
      if (e.getCause() instanceof SQLException sql && AppointmentService.isDuplicate(sql))
        throw new ServiceException(409, "This specialty already exists.");
      throw e;
    }
  }

  public static void saveDoctor(Doctor d, boolean create, boolean profileOnly) {
    try {
      read(c -> {
        try (PreparedStatement ps =
            c.prepareStatement("SELECT id FROM specialist WHERE spec_name=?")) {
          ps.setString(1, d.getSpecialist());
          try (ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) throw new ServiceException(400, "Choose an existing specialty.");
          }
        }
        if (!new DoctorDao(c).save(d, create, profileOnly))
          throw new ServiceException(404, "Doctor not found or archived.");
        return null;
      });
    } catch (DataAccessException e) {
      if (e.getCause() instanceof SQLException sql && AppointmentService.isDuplicate(sql))
        throw new ServiceException(409, "A doctor already uses this email.");
      throw e;
    }
  }

  public static void archiveDoctor(int id) {
    try (Connection c = DBConnect.open()) {
      c.setAutoCommit(false);
      try {
        try (PreparedStatement ps =
            c.prepareStatement("SELECT id FROM doctor WHERE id=? FOR UPDATE")) {
          ps.setInt(1, id);
          try (ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) throw new ServiceException(404, "Doctor not found.");
          }
        }
        try (PreparedStatement ps =
            c.prepareStatement(
                "SELECT COUNT(*) FROM appointment WHERE doctor_id=? AND status IN"
                    + " ('PENDING','CONFIRMED')")) {
          ps.setInt(1, id);
          try (ResultSet rs = ps.executeQuery()) {
            rs.next();
            if (rs.getInt(1) > 0)
              throw new ServiceException(
                  409, "Cancel or finish this doctor's open appointments before archiving.");
          }
        }
        try (PreparedStatement ps =
            c.prepareStatement(
                "UPDATE doctor SET active=FALSE,auth_version=auth_version+1 WHERE id=?")) {
          ps.setInt(1, id);
          ps.executeUpdate();
        }
        try (PreparedStatement ps =
            c.prepareStatement("UPDATE doctor_slot SET enabled=FALSE WHERE doctor_id=?")) {
          ps.setInt(1, id);
          ps.executeUpdate();
        }
        c.commit();
      } catch (SQLException | RuntimeException e) {
        c.rollback();
        throw e;
      }
    } catch (SQLException e) {
      throw new DataAccessException(e);
    }
  }

  public static boolean changePassword(Actor actor, String old, String replacement) {
    return read(c ->
        actor.role().equals("PATIENT")
            ? new UserDao(c).changePassword(actor.id(), old, replacement)
            : new DoctorDao(c).changePassword(actor.id(), old, replacement));
  }

  public static boolean validSession(Actor actor, int version) {
    return read(c -> {
      String table = actor.role().equals("PATIENT") ? "user_dtls" : "doctor";
      try (PreparedStatement ps =
          c.prepareStatement(
              "SELECT auth_version FROM "
                  + table
                  + " WHERE id=?"
                  + (table.equals("doctor") ? " AND active=TRUE" : ""))) {
        ps.setInt(1, actor.id());
        try (ResultSet rs = ps.executeQuery()) {
          return rs.next() && rs.getInt(1) == version;
        }
      }
    });
  }

  public static boolean adminLogin(String email, String password) {
    boolean demo = Boolean.parseBoolean(DBConnect.config("DEMO_MODE", "false"));
    String expected = DBConnect.config("ADMIN_EMAIL", demo ? "admin@hospital.test" : "");
    String hash = DBConnect.config("ADMIN_PASSWORD_HASH", "");
    if (expected.isBlank() || !expected.equals(email)) return false;
    if (!hash.isBlank()) return Passwords.verify(password, hash);
    return demo
        && password != null
        && MessageDigest.isEqual(
            DBConnect.config("ADMIN_PASSWORD", "Admin@123").getBytes(StandardCharsets.UTF_8),
            password.getBytes(StandardCharsets.UTF_8));
  }

  public static String adminStamp() {
    try {
      return HexFormat.of()
          .formatHex(
              MessageDigest.getInstance("SHA-256")
                  .digest(
                      (DBConnect.config("ADMIN_EMAIL", "admin@hospital.test")
                              + "|"
                              + DBConnect.config("ADMIN_PASSWORD_HASH", "")
                              + "|"
                              + DBConnect.config("ADMIN_PASSWORD", ""))
                          .getBytes(StandardCharsets.UTF_8)));
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }
}
