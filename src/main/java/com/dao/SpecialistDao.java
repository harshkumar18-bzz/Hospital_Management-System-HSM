package com.dao;

import com.entity.Specalist;
import java.sql.*;
import java.util.*;

public final class SpecialistDao {
  private final Connection c;

  public SpecialistDao(Connection c) {
    this.c = c;
  }

  public List<Specalist> list() throws SQLException {
    try (PreparedStatement ps =
            c.prepareStatement("SELECT id,spec_name FROM specialist ORDER BY spec_name");
        ResultSet rs = ps.executeQuery()) {
      List<Specalist> result = new ArrayList<>();
      while (rs.next()) result.add(new Specalist(rs.getInt(1), rs.getString(2)));
      return result;
    }
  }

  public void add(String name) throws SQLException {
    try (PreparedStatement ps = c.prepareStatement("INSERT INTO specialist(spec_name) VALUES(?)")) {
      ps.setString(1, name);
      ps.executeUpdate();
    }
  }
}
