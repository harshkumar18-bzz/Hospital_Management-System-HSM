package com.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/** Application-wide connection pool; callers own and close each borrowed connection. */
public final class DBConnect {
  private static volatile HikariDataSource pool;

  private DBConnect() {}

  public static String config(String name, String fallback) {
    String value = System.getProperty(name);
    if (value == null) value = System.getenv(name);
    return value == null ? fallback : value;
  }

  public static synchronized HikariDataSource dataSource() {
    if (pool == null || pool.isClosed()) {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(config("DB_URL", "jdbc:mysql://localhost:3306/hospital_1"));
      config.setUsername(config("DB_USER", "hospital"));
      config.setPassword(config("DB_PASSWORD", ""));
      config.setMaximumPoolSize(Integer.parseInt(config("DB_POOL_SIZE", "10")));
      config.setMinimumIdle(1);
      config.setConnectionTimeout(5000);
      config.setValidationTimeout(2000);
      config.setPoolName("hospital");
      config.setAutoCommit(true);
      pool = new HikariDataSource(config);
    }
    return pool;
  }

  public static Connection open() throws SQLException {
    return dataSource().getConnection();
  }

  public static synchronized void shutdown() {
    if (pool != null) {
      pool.close();
      pool = null;
    }
  }
}
