package com.dao;

import com.entity.User;
import com.security.Passwords;
import java.sql.*;

public final class UserDao {
  private final Connection c;

  public UserDao(Connection c) {
    this.c = c;
  }

  public int register(User user) throws SQLException {
    try (PreparedStatement ps =
        c.prepareStatement(
            "INSERT INTO user_dtls(full_name,email,password) VALUES(?,?,?)",
            Statement.RETURN_GENERATED_KEYS)) {
      ps.setString(1, user.getFullName());
      ps.setString(2, user.getEmail());
      ps.setString(3, Passwords.hash(user.getPassword()));
      ps.executeUpdate();
      try (ResultSet rs = ps.getGeneratedKeys()) {
        rs.next();
        return rs.getInt(1);
      }
    }
  }

  public User login(String email, String password) throws SQLException {
    try (PreparedStatement ps =
        c.prepareStatement(
            "SELECT id,full_name,email,password,auth_version FROM user_dtls WHERE email=?")) {
      ps.setString(1, email);
      try (ResultSet rs = ps.executeQuery()) {
        if (!rs.next() || !Passwords.verify(password, rs.getString("password"))) return null;
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFullName(rs.getString("full_name"));
        user.setEmail(rs.getString("email"));
        user.setAuthVersion(rs.getInt("auth_version"));
        String previous = rs.getString("password");
        if (Passwords.needsRehash(previous))
          try (PreparedStatement update =
              c.prepareStatement("UPDATE user_dtls SET password=? WHERE id=? AND password=?")) {
            update.setString(1, Passwords.hash(password));
            update.setInt(2, user.getId());
            update.setString(3, previous);
            update.executeUpdate();
          }
        return user;
      }
    }
  }

  public boolean changePassword(int id, String oldPassword, String replacement)
      throws SQLException {
    String previous;
    try (PreparedStatement ps = c.prepareStatement("SELECT password FROM user_dtls WHERE id=?")) {
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (!rs.next() || !Passwords.verify(oldPassword, rs.getString(1))) return false;
        previous = rs.getString(1);
      }
    }
    try (PreparedStatement ps =
        c.prepareStatement(
            "UPDATE user_dtls SET password=?,auth_version=auth_version+1 WHERE id=? AND"
                + " password=?")) {
      ps.setString(1, Passwords.hash(replacement));
      ps.setInt(2, id);
      ps.setString(3, previous);
      return ps.executeUpdate() == 1;
    }
  }
}
