package com.perf;

import java.nio.file.*;
import java.sql.*;
import java.util.*;

/** Controlled comparison of the old row-count loop with the replacement aggregate. */
public final class CountBenchmark {
  public static void main(String[] args) throws Exception {
    String url = System.getProperty("bench.db.url", "");
    if (!url.contains("/hms_test"))
      throw new IllegalArgumentException("Use the isolated MySQL hms_test database only.");
    int rows = 10000, samples = 20;
    String key = UUID.randomUUID().toString();
    try (Connection c =
        DriverManager.getConnection(
            url,
            System.getProperty("bench.db.user", "root"),
            System.getProperty("bench.db.password", ""))) {
      int patient =
          insert(
              c,
              "INSERT INTO user_dtls(full_name,email,password) VALUES(?,?,?)",
              "Synthetic benchmark",
              key + "@benchmark.test",
              "not-an-authentication-fixture");
      int doctor =
          insert(
              c,
              "INSERT INTO doctor(full_name,dob,qualification,specialist,email,mobno,password)"
                  + " VALUES(?,?,?,?,?,?,?)",
              "Synthetic benchmark",
              "1980-01-01",
              "MD",
              "General",
              key + "@doctor-benchmark.test",
              "9000000000",
              "not-an-authentication-fixture");
      c.setAutoCommit(false);
      try (PreparedStatement ps =
          c.prepareStatement(
              "INSERT INTO"
                  + " appointment(user_id,fullname,gender,age,appoint_date,email,phno,diseases,doctor_id,address,status)"
                  + " VALUES(?,'Synthetic','other','20','2020-01-01','synthetic@test.test','9000000000','Benchmark"
                  + " fixture',?,'Synthetic','COMPLETED')")) {
        for (int i = 0; i < rows; i++) {
          ps.setInt(1, patient);
          ps.setInt(2, doctor);
          ps.addBatch();
          if (i % 500 == 499) ps.executeBatch();
        }
        c.commit();
      } catch (Exception e) {
        c.rollback();
        throw e;
      } finally {
        c.setAutoCommit(true);
      }
      for (int i = 0; i < 5; i++) {
        loop(c, doctor, rows);
        aggregate(c, doctor, rows);
      }
      double[] old = new double[samples], optimized = new double[samples];
      for (int i = 0; i < samples; i++) {
        long start = System.nanoTime();
        loop(c, doctor, rows);
        old[i] = (System.nanoTime() - start) / 1_000_000.0;
        start = System.nanoTime();
        aggregate(c, doctor, rows);
        optimized[i] = (System.nanoTime() - start) / 1_000_000.0;
      }
      double before = Arrays.stream(old).average().orElseThrow(),
          after = Arrays.stream(optimized).average().orElseThrow();
      Arrays.sort(old);
      Arrays.sort(optimized);
      String json =
          String.format(
              Locale.ROOT,
              """
              {
                "recorded_at_utc": "%s",
                "database": "MySQL %s",
                "java": "%s",
                "fixture_rows": %d,
                "warmup_iterations": 5,
                "samples_per_query": %d,
                "legacy_select_ids_and_count_mean_ms": %.4f,
                "aggregate_count_mean_ms": %.4f,
                "legacy_p95_ms": %.4f,
                "aggregate_p95_ms": %.4f,
                "observed_mean_speedup": %.2f,
                "client_rows_transferred_before": %d,
                "client_rows_transferred_after": 1,
                "limits": "Single JDBC connection, localhost, warm cache, synthetic records, indexed doctor filter. Microbenchmark only; not an end-to-end application speedup. Fixtures remain in hms_test."
              }
              """,
              java.time.Instant.now(),
              c.getMetaData().getDatabaseProductVersion(),
              System.getProperty("java.version"),
              rows,
              samples,
              before,
              after,
              old[18],
              optimized[18],
              before / after,
              rows);
      Path output = Path.of("docs/performance/sql-count.json");
      Files.createDirectories(output.getParent());
      Files.writeString(output, json);
      System.out.println(json);
    }
  }

  private static int insert(Connection c, String sql, String... values) throws SQLException {
    try (PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      for (int i = 0; i < values.length; i++) ps.setString(i + 1, values[i]);
      ps.executeUpdate();
      try (ResultSet rs = ps.getGeneratedKeys()) {
        rs.next();
        return rs.getInt(1);
      }
    }
  }

  private static void loop(Connection c, int doctor, int expected) throws SQLException {
    try (PreparedStatement ps =
        c.prepareStatement("SELECT id FROM appointment WHERE doctor_id=?")) {
      ps.setInt(1, doctor);
      try (ResultSet rs = ps.executeQuery()) {
        int count = 0;
        while (rs.next()) {
          rs.getInt(1);
          count++;
        }
        if (count != expected) throw new AssertionError(count);
      }
    }
  }

  private static void aggregate(Connection c, int doctor, int expected) throws SQLException {
    try (PreparedStatement ps =
        c.prepareStatement("SELECT COUNT(*) FROM appointment WHERE doctor_id=?")) {
      ps.setInt(1, doctor);
      try (ResultSet rs = ps.executeQuery()) {
        rs.next();
        if (rs.getInt(1) != expected) throw new AssertionError();
      }
    }
  }
}
