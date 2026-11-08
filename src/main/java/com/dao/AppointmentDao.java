package com.dao;

import com.entity.*;
import java.sql.*;
import java.util.*;

/** SQL and row mapping only; AppointmentService owns transactions and business rules. */
public final class AppointmentDao {
  private final Connection connection;

  public AppointmentDao(Connection connection) {
    this.connection = connection;
  }

  private static final String SELECT =
      """
      SELECT a.*, d.full_name AS doctor_name, s.starts_at,
      (SELECT n.body FROM appointment_note n WHERE n.appointment_id=a.id ORDER BY n.id DESC LIMIT 1) AS latest_note
      FROM appointment a JOIN doctor d ON d.id=a.doctor_id LEFT JOIN doctor_slot s ON s.id=a.slot_id
      """;

  public List<AppointmentRow> list(String role, int owner, int page) throws SQLException {
    String where =
        role.equals("PATIENT")
            ? " WHERE a.user_id=?"
            : role.equals("DOCTOR") ? " WHERE a.doctor_id=?" : "";
    try (PreparedStatement ps =
        connection.prepareStatement(SELECT + where + " ORDER BY a.id DESC LIMIT 20 OFFSET ?")) {
      int i = 1;
      if (!role.equals("ADMIN")) ps.setInt(i++, owner);
      ps.setInt(i, Math.max(0, page - 1) * 20);
      try (ResultSet rs = ps.executeQuery()) {
        List<AppointmentRow> rows = new ArrayList<>();
        while (rs.next()) rows.add(map(rs));
        return rows;
      }
    }
  }

  public AppointmentRow find(int id) throws SQLException {
    try (PreparedStatement ps = connection.prepareStatement(SELECT + " WHERE a.id=?")) {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        return rs.next() ? map(rs) : null;
      }
    }
  }

  public List<Note> notes(int id) throws SQLException {
    try (PreparedStatement ps =
        connection.prepareStatement(
            "SELECT d.full_name,n.body,n.created_at FROM appointment_note n JOIN doctor d ON"
                + " d.id=n.doctor_id WHERE n.appointment_id=? ORDER BY n.id DESC")) {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        List<Note> rows = new ArrayList<>();
        while (rs.next()) rows.add(new Note(rs.getString(1), rs.getString(2), rs.getString(3)));
        return rows;
      }
    }
  }

  public List<AuditEvent> events(int id) throws SQLException {
    try (PreparedStatement ps =
        connection.prepareStatement(
            "SELECT actor_role,action,from_status,to_status,created_at FROM appointment_event WHERE"
                + " appointment_id=? ORDER BY id DESC")) {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        List<AuditEvent> rows = new ArrayList<>();
        while (rs.next())
          rows.add(
              new AuditEvent(
                  rs.getString(1),
                  rs.getString(2),
                  rs.getString(3),
                  rs.getString(4),
                  rs.getString(5)));
        return rows;
      }
    }
  }

  private AppointmentRow map(ResultSet rs) throws SQLException {
    Long slot = (Long) rs.getObject("slot_id", Long.class);
    Timestamp starts = rs.getTimestamp("starts_at");
    return new AppointmentRow(
        rs.getInt("id"),
        rs.getInt("user_id"),
        rs.getInt("doctor_id"),
        slot,
        rs.getString("fullname"),
        rs.getString("gender"),
        rs.getString("age"),
        rs.getString("appoint_date"),
        rs.getString("email"),
        rs.getString("phno"),
        rs.getString("diseases"),
        rs.getString("address"),
        rs.getString("status"),
        rs.getString("doctor_name"),
        starts == null
            ? "Unscheduled legacy request"
            : starts.toLocalDateTime().toString().replace('T', ' '),
        rs.getString("latest_note"));
  }
}
