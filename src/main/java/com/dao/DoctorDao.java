package com.dao;

import com.entity.Doctor;
import com.security.Passwords;
import java.sql.*;
import java.util.*;

public final class DoctorDao {
  private final Connection c;
  private static final String COLUMNS =
      "id,full_name,dob,qualification,specialist,email,mobno,active,auth_version";

  public DoctorDao(Connection c) {
    this.c = c;
  }

  private Doctor map(ResultSet rs) throws SQLException {
    Doctor d = new Doctor();
    d.setId(rs.getInt("id"));
    d.setFullName(rs.getString("full_name"));
    d.setDob(rs.getString("dob"));
    d.setQualification(rs.getString("qualification"));
    d.setSpecialist(rs.getString("specialist"));
    d.setEmail(rs.getString("email"));
    d.setMobNo(rs.getString("mobno"));
    d.setActive(rs.getBoolean("active"));
    d.setAuthVersion(rs.getInt("auth_version"));
    return d;
  }

  public List<Doctor> list(boolean archived) throws SQLException {
    try (PreparedStatement ps =
            c.prepareStatement(
                "SELECT "
                    + COLUMNS
                    + " FROM doctor"
                    + (archived ? "" : " WHERE active=TRUE")
                    + " ORDER BY full_name");
        ResultSet rs = ps.executeQuery()) {
      List<Doctor> result = new ArrayList<>();
      while (rs.next()) result.add(map(rs));
      return result;
    }
  }

  public Doctor find(int id) throws SQLException {
    try (PreparedStatement ps =
        c.prepareStatement("SELECT " + COLUMNS + " FROM doctor WHERE id=?")) {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        return rs.next() ? map(rs) : null;
      }
    }
  }

  public Doctor login(String email, String password) throws SQLException {
    try (PreparedStatement ps =
        c.prepareStatement("SELECT id,password FROM doctor WHERE email=? AND active=TRUE")) {
      ps.setString(1, email);
      try (ResultSet rs = ps.executeQuery()) {
        if (!rs.next() || !Passwords.verify(password, rs.getString("password"))) return null;
        int id = rs.getInt("id");
        String previous = rs.getString("password");
        if (Passwords.needsRehash(previous))
          try (PreparedStatement update =
              c.prepareStatement("UPDATE doctor SET password=? WHERE id=? AND password=?")) {
            update.setString(1, Passwords.hash(password));
            update.setInt(2, id);
            update.setString(3, previous);
            update.executeUpdate();
          }
        return find(id);
      }
    }
  }

  public boolean save(Doctor d, boolean create, boolean profileOnly) throws SQLException {
    String sql =
        create
            ? "INSERT INTO doctor(full_name,dob,qualification,specialist,email,mobno,password)"
                  + " VALUES(?,?,?,?,?,?,?)"
            : "UPDATE doctor SET"
                  + " full_name=?,dob=?,qualification=?,specialist=?,email=?,mobno=?,password=COALESCE(?,password),auth_version=auth_version+?"
                  + " WHERE id=? AND active=TRUE";
    try (PreparedStatement ps = c.prepareStatement(sql)) {
      ps.setString(1, d.getFullName());
      ps.setString(2, d.getDob());
      ps.setString(3, d.getQualification());
      ps.setString(4, d.getSpecialist());
      ps.setString(5, d.getEmail());
      ps.setString(6, d.getMobNo());
      String replacement =
          profileOnly || d.getPassword() == null || d.getPassword().isBlank()
              ? null
              : Passwords.hash(d.getPassword());
      ps.setString(7, replacement);
      if (!create) {
        ps.setInt(8, replacement == null ? 0 : 1);
        ps.setInt(9, d.getId());
      }
      return ps.executeUpdate() == 1;
    }
  }

  public boolean changePassword(int id, String oldPassword, String replacement)
      throws SQLException {
    String previous;
    try (PreparedStatement ps =
        c.prepareStatement("SELECT password FROM doctor WHERE id=? AND active=TRUE")) {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (!rs.next() || !Passwords.verify(oldPassword, rs.getString(1))) return false;
        previous = rs.getString(1);
      }
    }
    try (PreparedStatement ps =
        c.prepareStatement(
            "UPDATE doctor SET password=?,auth_version=auth_version+1 WHERE id=? AND password=? AND"
                + " active=TRUE")) {
      ps.setString(1, Passwords.hash(replacement));
      ps.setInt(2, id);
      ps.setString(3, previous);
      return ps.executeUpdate() == 1;
    }
  }

  public Map<String, Long> counts(Integer doctorId) throws SQLException {
    Map<String, Long> values = new HashMap<>();
    if (doctorId == null) {
      for (String table : List.of("doctor", "user_dtls", "appointment", "specialist")) {
        String sql =
            "SELECT COUNT(*) FROM " + table + (table.equals("doctor") ? " WHERE active=TRUE" : "");
        try (PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
          rs.next();
          values.put(table, rs.getLong(1));
        }
      }
    } else {
      try (PreparedStatement ps =
          c.prepareStatement(
              "SELECT status,COUNT(*) FROM appointment WHERE doctor_id=? GROUP BY status")) {
        ps.setInt(1, doctorId);
        try (ResultSet rs = ps.executeQuery()) {
          long total = 0;
          while (rs.next()) {
            values.put(rs.getString(1), rs.getLong(2));
            total += rs.getLong(2);
          }
          values.put("total", total);
        }
      }
    }
    return values;
  }
}
