# Measured performance report

Recorded HTTP run: 2026-11-07T08:02:27.242063+00:00. These results describe the local test environment and workloads below, not production capacity.

## External WAR deployment

- Tomcat 10.1.61, normal packaged WAR; no embedded local profile/H2 runtime.
- Runtime: openjdk version "17.0.21" 2026-10-20.
- MySQL 8.4.11 on the same machine, dedicated application database identity.
- Eight authenticated Python clients, 15-second closed-loop read workload, 37,192 measured requests.
- Alternates available slots for one doctor and one page of the signed-in patient's appointment history.
- Small demo dataset: four active demo doctors and 14 days of published availability; benchmark accounts have empty histories.
- Account creation, password verification and per-route warmup are excluded.
- Python urllib clients open requests without a persistent-connection benchmark harness; client/runtime/loopback costs are part of the result.

| Metric | Measured value |
| --- | --- |
| Requests | 37,192 |
| Elapsed | 15.002 seconds |
| Throughput | 2479.2 requests/second |
| Mean | 3.22 ms |
| p50 | 3.08 ms |
| p95 | 5.31 ms |
| p99 | 6.47 ms |
| Errors | 0 |

This is an authenticated **read** result. It does not establish booking/write throughput, patient-data-scale behavior, internet latency, high availability, cloud capacity, or a sustainable production limit. A closed-loop load generator reduces offered load as the server slows; it is not an open-loop arrival test. No uncontrolled background integration script ran during this recorded sustained workload.

## Controlled query optimization

The old dashboard pattern transferred row IDs and incremented a Java counter. The replacement requests a COUNT aggregate in SQL.

- 10,000 synthetic appointments for one synthetic doctor in isolated hms_test.
- Same indexed doctor filter and JDBC connection for both query forms.
- Five warmups and 20 samples per form; warm-cache localhost execution.
- Client runtime: Java 25.0.4.1; server: MySQL 8.4.11-0ubuntu0.26.04.1.

| Metric | Fetch rows and count in Java | SELECT COUNT(*) |
| --- | --- | --- |
| Mean | 3.9291 ms | 1.8465 ms |
| p95 | 4.5317 ms | 2.5292 ms |
| Rows transferred to client | 10,000 | 1 |

Observed mean speedup in this controlled comparison: **2.13x**. This is a query microbenchmark, not an application-wide speedup. The database still has to evaluate the filtered count; the benefit is reduced result transfer and client-side row processing.

## Correctness evidence collected alongside measurement

- 13 Java tests pass on H2 and again on MySQL, including legacy migration.
- 85 HTTP assertions pass against the external Java 17/Tomcat/MySQL deployment.
- Simultaneous booking and duplicate-key tests run on real database transactions.
- Fault injection after early writes verifies complete rollback.
- Performance measurements do not replace correctness tests or visual/accessibility review.

## Reproduce

```bash
python3 scripts/benchmark.py --base http://localhost:8082 --users 8 --duration 15
```

The synthetic-account setup expects an explicitly local demo. Repeating the benchmark creates additional synthetic accounts.

For the JDBC comparison, follow README's CountBenchmark command and point it only at the isolated hms_test database. It inserts synthetic benchmark fixtures that remain available for inspection.

Raw measurements: [http.json](http.json), [sql-count.json](sql-count.json). Both scripts record actual measurements; no results are hard-coded into the benchmark implementations.
