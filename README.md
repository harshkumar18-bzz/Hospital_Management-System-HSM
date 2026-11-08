# Hospital Management System

## Implemented behavior

- Patients reserve **published 30-minute slots** and see status, notes, and activity history.
- A slot has at most one active reservation, enforced with database locks and constraints.
- Repeating a booking with the same account/request key and payload returns the original appointment.
- Reusing the key for different details returns HTTP 409.
- Appointment states are PENDING, CONFIRMED, COMPLETED, and CANCELLED.
- Doctors confirm future requests and complete confirmed visits after their start time.
- Patients cancel their own upcoming visits; cancellation releases the slot and retains the record.
- Doctor notes are append-only and separate from appointment status.
- Doctors and administrators publish/withdraw availability; patients cannot.
- Administrators manage specialties and doctor profiles. Archiving a doctor retains history and revokes doctor sessions; open appointments must first be resolved.
- JSPs render controller-supplied data and contain no Java scriptlets/SQL.
- HikariCP pools connections; services close every borrowed connection.
- Errors distinguish validation, authentication, authorization, conflict and database failure.
- Password changes revoke existing account sessions through a database authentication version.
- POST forms have CSRF protection; logout is POST-only. Login failures are throttled.
- Protected read APIs, health checks, request IDs/timing logs and admin metrics are included.

## Run locally

Requires JDK 17+ and Maven 3.9+:

```bash
mvn -Plocal compile exec:java
```

Open **http://localhost:8080**. Stop with Ctrl+C. If an IDE already uses that port:

```bash
mvn -Plocal -Dport=8081 compile exec:java
```

The local profile uses a persistent H2 database at `.local/hospital.mv.db`, embedded Tomcat, and sample doctors/slots. It binds to 127.0.0.1. It migrates an existing demo database without deleting its original appointment records. All displayed scheduling times use `HOSPITAL_ZONE`, default `Asia/Kolkata`.

| Role | Demo email | Password |
| --- | --- | --- |
| Administrator | admin@hospital.test | Admin@123 |
| General medicine doctor | aditi@hospital.test | Doctor@123 |
| Cardiology doctor | arjun@hospital.test | Doctor@123 |
| Pediatrics doctor | neha@hospital.test | Doctor@123 |
| Dermatology doctor | rohan@hospital.test | Doctor@123 |
| Patient | Register a new account | Your chosen password |

Sample accounts and availability are seeded only in explicit demo mode. Restarting demo mode publishes missing sample availability for the next 14 days. Withdrawn existing slots are not reopened. Each doctor's slot starts at :00 or :30 and lasts 30 minutes; staff can publish up to 90 days ahead.

The local runner respects explicit DB_URL/DB_USER/DB_PASSWORD overrides, allowing a real MySQL demo:

```bash
export DB_URL='jdbc:mysql://127.0.0.1:3306/hospital_1'
export DB_USER='hospital'
export DB_PASSWORD='your-password'
mvn -Plocal compile exec:java
```

## Docker deployment

The multi-stage image builds/tests the normal WAR and runs it in Tomcat as a non-root user. Compose starts MySQL 8.4, waits for readiness, and exposes the app on localhost.

```bash
mvn clean verify
java -cp target/classes com.security.Passwords
cp .env.example .env
# Fill .env with separate database passwords, admin email, and the generated admin hash.
docker compose up --build --wait
```

The password helper reads the password interactively and prints only its encoded hash. Use at least 12 characters for this admin password. Keep the hash single-quoted in .env so dollar signs remain literal.

Set `DEMO_MODE=true` only for fictional sample data. Outside demo mode, admin authentication requires `ADMIN_EMAIL` and `ADMIN_PASSWORD_HASH`; plaintext admin-password fallback is disabled. The app does not itself load .env; Compose does.

The app listens at http://localhost:8080 by default. `APP_PORT` changes the host port. MySQL is reachable only on the Compose network and persists in the named volume. Restarting an existing MySQL volume does not change previously initialized database passwords.

On this Linux workstation, Docker uses the `desktop-linux` context. If Docker reports a missing `~/.docker/desktop/docker.sock`, start Docker Desktop with `systemctl --user start docker-desktop` and check `docker info`. Enable startup at graphical login with `systemctl --user enable docker-desktop`. A native Docker Engine installation uses a different service and socket; switching contexts alone cannot start a stopped engine.

The Docker build and deployment were verified locally on November 7, 2026: 13 Java tests and 85 HTTP integration checks passed. See [Docker verification](docs/DOCKER_VERIFICATION.md) for the running demo and restart commands.

For a real HTTPS deployment, configure your trusted TLS endpoint and set `SECURE_COOKIES=true`. This repository does not provision a public host or TLS certificate. Its default Compose network uses unencrypted internal MySQL traffic on the local container network; configure database TLS for a remote database.

## External Tomcat deployment

```bash
mvn clean verify
```

Deploy `target/Hospital_Management_System.war` to Tomcat 10.1 on JDK 17+. The normal WAR excludes embedded Tomcat and H2. Set configuration in the Tomcat process:

| Variable | Purpose / default |
| --- | --- |
| DB_URL | JDBC URL; defaults to local MySQL hospital_1 |
| DB_USER / DB_PASSWORD | Dedicated application database credentials |
| DB_POOL_SIZE | Maximum pooled connections; default 10 |
| DB_MIGRATE | Run Flyway on startup; default true |
| DB_BASELINE_LEGACY | Explicitly adopt a pre-Flyway non-empty schema; default false outside demo |
| DEMO_MODE | Seed fictional data; false outside local runner |
| ADMIN_EMAIL | Configured administrator identity |
| ADMIN_PASSWORD_HASH | PBKDF2 representation generated by the password helper |
| HOSPITAL_ZONE | Hospital scheduling zone; default Asia/Kolkata |
| SECURE_COOKIES | Require HTTPS for session cookies; default false for localhost |

Use `deploy/server.xml` as the reproducible container-server configuration. It caps forms/parameter counts and suppresses Tomcat's detailed error report. For deployment under a non-root context, application links/redirects include the context path.

### Existing databases

Back up an existing database before migration. Set `DB_BASELINE_LEGACY=true` deliberately for an original schema without Flyway history. Migrations:

1. V1 creates the original base tables if absent.
2. V2 adds slots/reservations, idempotency records, separate notes, audit events, account versions and indexes. Legacy free-text status content becomes a consultation note; its appointment becomes COMPLETED. Original pending records remain PENDING with no invented slot.
3. V3 widens credential columns and enforces matching slot/doctor and reservation/appointment relationships with composite foreign keys.

Legacy appointments without a slot are labelled as unscheduled. They can be cancelled but cannot be confirmed as if a slot existed. No records or clinical notes are discarded.

Old PBKDF2 hashes upgrade after successful login. Original plaintext credentials are deliberately unsupported and need an explicit password reset/migration; the application never silently accepts plaintext as a stored password.

Migration clean operations are disabled. MySQL DDL is not transactional in the same way as normal business writes; fix a failed migration deliberately rather than deleting history or blindly rerunning destructive commands.

## Tests

```bash
mvn clean verify
```

Run the same scheduling contract against a **separate MySQL database named hms_test**, never your normal application database:

```bash
mvn test \
  '-Dtest.db.url=jdbc:mysql://127.0.0.1:3306/hms_test?allowPublicKeyRetrieval=true&useSSL=false' \
  -Dtest.db.user=root \
  -Dtest.db.password='your-test-only-password'
```

The legacy migration test creates a uniquely named hms_test_legacy_* database, so this test identity needs CREATE DATABASE privileges. These fixtures are isolated test data, not application-user privileges. Test fixtures remain for inspection.

Start a local demo, then:

```bash
python3 scripts/smoke_test.py http://localhost:8080
```

Java tests cover simultaneous booking, idempotent retries, cancellation/rebooking, lifecycle/ownership, rollback after an injected late audit failure, pool release, rehash-on-login, legacy migration, validation and escaping. HTTP checks cover complete browser-form workflows, read APIs, forged identities, notes, slot management, archiving, session revocation, CSRF and login throttling.

The smoke script restricts its destination to localhost, requires the demo-mode HTML marker, and leaves synthetic records. H2 compatibility mode is convenient, but real MySQL contract/deployment tests are also provided and were run locally.

## CI

`.github/workflows/ci.yml` runs:

1. Java 17 clean build and H2 tests.
2. The same contracts and legacy migration against MySQL 8.4.
3. An actual container build and Tomcat/MySQL startup.
4. Full HTTP integration and race checks against that deployed stack.
5. Disposable CI container/data cleanup.

The workflow is configured; a hosted GitHub Actions run occurs after pushing the branch. No remote repository was pushed or modified by the local implementation.

## Read APIs and operations

These APIs use the same server session and object authorization as the rendered pages:

- `GET /api/slots?doctorId=1`: available slots, time zone, and 30-minute duration.
- `GET /api/appointments?page=1`: the signed-in role's records, 20 per page.
- `GET /api/appointments/{id}`: one authorized record; other patients/doctors receive 404.

Read APIs return JSON. Mutations currently use CSRF-protected HTML form endpoints, with 303 redirects on success and meaningful 400/401/403/404/409/429/503 failures.

- `GET /health/live`: process/webapp liveness.
- `GET /health/ready`: database readiness.
- `GET /admin/metrics`: authenticated administrator-only Prometheus-format request/error/duration counters.

Responses include X-Request-ID. Request logs record correlation ID, route, method, status and elapsed time, excluding form data, passwords, notes, emails and addresses.

## Performance evidence

See [the measured report](docs/performance/REPORT.md) and its raw JSON.

```bash
python3 scripts/benchmark.py --base http://localhost:8080 --users 8 --duration 15
```

This uses synthetic accounts and authenticated localhost read traffic, with setup/warmup excluded. It is not a production capacity claim.

The JDBC comparison reproduces the old count-in-Java pattern versus SELECT COUNT(*) using 10,000 synthetic rows in hms_test:

```bash
mvn test-compile
mvn org.codehaus.mojo:exec-maven-plugin:3.5.0:java \
  -Dexec.mainClass=com.perf.CountBenchmark -Dexec.classpathScope=test \
  '-Dbench.db.url=jdbc:mysql://127.0.0.1:3306/hms_test?allowPublicKeyRetrieval=true&useSSL=false' \
  -Dbench.db.user=root -Dbench.db.password='your-test-only-password'
```

## Architecture and interview notes

[INTERVIEW_QUESTIONS_AND_DECISIONS.md](docs/INTERVIEW_QUESTIONS_AND_DECISIONS.md) provides short introduction scripts, 68 practice questions with answers, design tradeoffs, code references and a five-minute demonstration plan.

[HOSPITAL_PROJECT_EXPLAINED.md](docs/HOSPITAL_PROJECT_EXPLAINED.md) explains the current design, guarantees, tradeoffs and source walkthrough.

The key transaction lock order is doctor -> slot -> appointment. Booking first creates a unique account/request-key record; duplicate submissions serialize on that key. Booking then locks the doctor and slot, verifies availability, inserts an appointment, creates its unique slot reservation, completes the idempotency record and appends an event before commit. Any failure rolls the group back.

This intentionally serializes short scheduling writes for a given doctor. It is simple and correct for this scale; finer-grained locks would require coordinated archive/availability rules and new concurrency tests.

## Remaining scope limits

Login throttling and operational counters are process-local; multiple replicas need a shared rate limiter/session strategy and aggregated metrics. Cookie/session security depends on correct HTTPS deployment. The project has no MFA, email ownership verification, email delivery, billing, holiday scheduling or externally hosted deployment. Bootstrap/Font Awesome currently load from CDNs. Automated HTTP checks are not visual-browser/accessibility testing.
