# Hospital Management System: interview questions and design decisions

Prepared for SDE internship project discussions, including Visa interviews. These are practice questions based on this repository, not a claimed Visa question bank.

Implementation checked on **7 November 2026**. Read this with [the architecture guide](INTERVIEW_GUIDE.md), [source walkthrough](SOURCE_WALKTHROUGH.md), and [performance evidence](performance/REPORT.md).

## How to use this guide

Start with the introduction, then practise explaining booking correctness without looking at the code. For each answer, understand **the problem, the decision, the reason, the tradeoff, and the evidence**.

The repository adapts [lokeshmori/Hospital_Management-System-HSM](https://github.com/lokeshmori/Hospital_Management-System-HSM). The first-person scripts below are templates: describe an improvement as your own only if you implemented it or can accurately explain your contribution. Credit the starting project and any assistance you used. Knowing how a feature works and having personally built it are different claims.

## A short introduction to say to an interviewer

### About 30 seconds

> My project is a Java-based hospital appointment scheduling application adapted from an existing hospital management project. Patients book published slots, doctors manage visits, and administrators manage doctors and availability. My focus was on making scheduling reliable: preventing double booking, handling repeated requests safely, enforcing access rules, and preserving appointment history. The application runs with Tomcat and MySQL in Docker, with automated tests for its main correctness rules.

### About 60–90 seconds

> I worked on improving an existing Java Servlet/JSP hospital management application into a transactional appointment scheduling system. It supports patient booking, doctor confirmation and consultation notes, and administrator management of doctors and availability.
>
> The main engineering problem was concurrent booking. Two patients can see the same slot as available, so checking availability in Java is insufficient. The implementation uses database transactions, row locks and a unique active reservation per slot. It also stores account-scoped request keys, so a retry returns the original appointment instead of creating another one. A payload fingerprint rejects reuse of the same key for different details.
>
> Other improvements include separating appointment states from notes, moving database access out of JSPs, connection pooling, versioned migrations and security checks. The Docker build passed 13 Java tests, including concurrent booking and idempotent retry tests. My main learning was how to turn a business rule into a database invariant and then verify it under concurrent requests.

Personalize the exact contribution list before using this version. The 13-test result was observed during the Docker rebuild in this session; historical HTTP/MySQL benchmark results are separately recorded in the repository.

## The project in one picture

```text
Browser: HTML forms + session cookie + CSRF token
                         |
                   RequestFilter
             route/session/CSRF checks
                         |
             Page / Action / API controller
                         |
              Services: rules + transactions
                         |
              JDBC SQL / reusable DAO reads
                         |
                 HikariCP connection pool
                         |
           MySQL: locks + constraints + records

Page responses: controller -> protected JSP/JSTL -> HTML
API responses: controller -> authorized service data -> JSON
```

This is one application with internal layers. It is not a microservice system. The normal deployable artifact is a WAR running in Tomcat; H2 and embedded Tomcat support local development/testing.

## The decisions at a glance

| Decision | Why it fits this project | Cost or boundary |
| --- | --- | --- |
| Keep Java Servlets/JSP | Improve the inherited application's correctness while keeping HTTP/JDBC behavior visible | More manual routing, validation and security wiring |
| Keep one deployable application | Scheduling, accounts and history are closely related and share transactions | Deployment units cannot scale independently |
| Use a relational database | Ownership, relationships, uniqueness and atomic updates are central | Schema changes and lock contention need care |
| Separate services from views/controllers | Business rules and transactions have a clear owner | Some transaction-specific SQL remains in services |
| Use database row locks | Coordinate competing writes at the shared database | Writes for the same doctor can wait on each other |
| Also use unique/composite constraints | Protect invariants even if application checks miss a case | Constraint failures must become useful errors |
| Store request keys and payload fingerprints | Resolve retries and detect conflicting reuse | Extra writes, storage and retention decisions |
| Separate reservations from appointments | Release occupancy while retaining cancelled history | More tables and transactional coordination |
| Separate notes, states and events | Each represents a different kind of information | More joins and lifecycle rules |
| Use HikariCP | Reuse a bounded set of connections | Pool size must match measured capacity |
| Use Flyway | Apply repeatable, ordered, checksum-validated schema changes | Migration recovery needs planning; MySQL DDL is not ordinary transactional DML |
| Use server sessions | Fit the server-rendered browser application | Multiple replicas need a session strategy |
| Use slow salted password hashing | Increase the cost of offline password guessing | Login deliberately consumes computation |
| Use Docker Compose | Reproduce the app/database environment locally | Containers alone do not provide production operations |

## A. Scope, architecture and ownership

### 1. What problem does the application solve?

It manages a patient's appointment from reservation through confirmation, completion or cancellation. Doctors publish availability and add consultation notes; administrators manage doctors and specialties. The deeper engineering problem is keeping scheduling correct when requests overlap, fail or are retried.

**Why this scope:** it creates meaningful ownership, transaction and concurrency problems within a manageable application. It does not attempt billing, insurance or a complete electronic medical record system.

### 2. What did you build yourself, and what came from the original project?

Say that the project starts from an existing Java/JSP hospital application. Then identify your actual contributions, using commits, code and demonstrations. The current implementation contains transactional scheduling, idempotency, state/history separation, security changes, migrations, tests and Docker deployment; do not automatically claim personal authorship of all of them.

**Why this matters:** explaining a precise improvement and its test is more credible than claiming the entire inherited application was written from scratch.

### 3. Why did you keep Servlets and JSP instead of rewriting in Spring Boot?

The starting application already used this stack. Keeping it let the work focus on scheduling correctness and separation of responsibilities. Servlets expose request handling and JSP/JSTL supports the server-rendered interface.

**Tradeoff:** the application manually wires concerns that a framework could simplify. Spring Boot could be a later maintenance improvement, but changing frameworks would not automatically solve concurrency or authorization.

### 4. Why a monolith instead of microservices?

One deployment is sufficient for the current scope, and related scheduling writes can share one database transaction. Splitting it would introduce network failures, service authentication, deployment coordination and cross-service consistency before there is evidence those costs are justified.

**When to reconsider:** independently owned domains or measured scaling/deployment needs. A monolith can still have clear internal modules.

### 5. Why MySQL rather than a document database?

Appointments reference patients, doctors and slots. The design needs foreign keys, unique occupancy and multi-table atomic updates. A relational model expresses those requirements directly.

**Tradeoff:** relationships and schema evolution require care. This is a fit-for-requirements decision, not a claim that document databases cannot support transactions.

### 6. Why have controllers, services and DAOs?

Controllers translate HTTP inputs and responses. Services enforce use cases, authorization and transaction boundaries. DAOs provide reusable reads and row mapping. JSPs render controller-supplied data.

**Why:** the same scheduling rule can serve both HTML and JSON reads without copying SQL into views. Some write SQL remains inside service transactions; the repository does not implement a completely abstract persistence layer.

### 7. Why put JSPs under WEB-INF?

The servlet container prevents direct public access to these view files. Controllers load the required data and forward to a view after the request policy has run.

**Boundary:** protected view placement does not replace authentication or object-level authorization.

### 8. Explain one complete booking request.

1. A signed-in patient loads the booking page with a CSRF token and request key.
2. The browser submits the selected slot and patient details.
3. `RequestFilter` checks route policy, session validity and CSRF.
4. `ActionController` parses the input and gets the acting patient ID from the session.
5. `AppointmentService.book` validates and performs the transaction.
6. On success the controller sends a **303 redirect** to the appointment page.
7. The GET request loads an authorized appointment model and renders a JSP.

**Why 303:** refreshing the result page repeats a GET rather than the original form submission. Database idempotency still handles network retries and duplicate submissions.

**Code for this section:** [ActionController](../src/main/java/com/web/ActionController.java), [PageController](../src/main/java/com/web/PageController.java), [RequestFilter](../src/main/java/com/security/RequestFilter.java).

## B. Database design and lifecycle

### 9. What are the important tables?

| Table | Responsibility |
| --- | --- |
| `user_dtls`, `doctor`, `specialist` | Accounts and doctor/specialty information |
| `doctor_slot` | Published doctor/start-time availability |
| `appointment` | A patient's historical booking and lifecycle state |
| `slot_reservation` | Current occupancy of a slot |
| `booking_request` | Account/request key, payload fingerprint and appointment result |
| `appointment_note` | Separate consultation text and author |
| `appointment_event` | Actor, action, state change and timestamp |

**Why split them:** availability, occupancy, a historical booking, a note and a retry record have different lifetimes and rules.

### 10. Why not make appointment.slot_id unique?

The same slot can have an old cancelled appointment and a later active appointment. A permanent unique constraint on `appointment.slot_id` would prevent that history-preserving rebooking.

Instead, `slot_reservation.slot_id` is the primary key: one active reservation per slot. Cancellation removes the reservation but keeps the appointment.

### 11. Why have foreign keys and composite foreign keys?

Ordinary foreign keys ensure referenced records exist. Composite relationships also ensure that the appointment's doctor matches its slot's doctor, and that a reservation's appointment belongs to that same slot.

**Why:** individually valid IDs can still form an invalid combination. Application checks improve errors; database constraints protect stored relationships.

### 12. Why store patient details on the appointment?

They act as a snapshot of the details submitted for that visit. A later account-profile change should not silently rewrite what was recorded for an earlier appointment.

**Tradeoff:** snapshots duplicate data and introduce retention/privacy considerations. The schema is pragmatic rather than perfectly normalized; doctor specialty is also still represented as a text label.

### 13. Why separate appointment status from consultation notes?

A status answers “where is this appointment in its lifecycle?” A note records consultation text. Putting arbitrary text into status breaks reliable filtering and overwrites clinical context.

The implementation uses constrained status values and separate append-only note rows. Append-only here means application behavior; a database administrator can still change the database. It is not a tamper-proof audit system.

### 14. What transitions are allowed, and why?

```text
PENDING -> CONFIRMED -> COMPLETED
   |           |
   +-----------+-----> CANCELLED
```

Confirmation requires a future pending appointment. Completion requires confirmation and a start time that has arrived. Cancellation applies to an upcoming open appointment; legacy unscheduled open records can also be cancelled. Patients can cancel their own appointments; assigned doctors and administrators have staff actions.

**Why:** explicit transitions prevent arbitrary status edits. `COMPLETED` and `CANCELLED` are terminal in this model. Retrying the already-applied target does not add a duplicate transition event.

### 15. Why archive doctors instead of deleting them?

Deleting would break the meaning of historical appointments and notes. Archiving marks the doctor inactive, increments the authentication version and disables availability. Open pending/confirmed appointments must first be resolved.

**Why:** remove future access/availability while keeping historical references intact. Archiving and booking coordinate through the doctor-row lock.

### 16. How are existing records migrated without inventing history?

Flyway V2 copies old free-text status content into notes and normalizes lifecycle values. Old appointments without a real slot keep a nullable `slot_id`; the migration does not invent their times.

**Tradeoff:** the application must handle unscheduled legacy records explicitly. Migration tests check preservation rather than merely checking that SQL executes.

**Code for this section:** [V2 scheduling schema](../src/main/resources/db/migration/V2__scheduled_appointments.sql), [V3 relationship constraints](../src/main/resources/db/migration/V3__integrity_and_credential_storage.sql), [AppointmentService](../src/main/java/com/service/AppointmentService.java), [HospitalService](../src/main/java/com/service/HospitalService.java).

## C. Transactions, concurrency and retries

### 17. What is a race condition in this application?

Two requests can both read “no reservation” before either writes. If each then inserts a booking without coordination, one slot can be allocated twice.

**Why a normal if-check fails:** the read and write are separate operations, and other requests can execute between them. The availability shown in the browser is only a snapshot.

### 18. How does the application prevent double booking?

Booking runs in a transaction. It locks the doctor and selected slot, rechecks availability, creates the appointment and inserts a reservation whose slot ID is unique. It commits the appointment, reservation, request result and event together.

**Two layers:** locks coordinate business decisions; constraints reject invalid persisted occupancy. Do not describe the lock alone as the entire guarantee.

### 19. Walk through two patients booking the same slot.

Assume different request keys and no other slot mutation:

| Step | Patient A | Patient B |
| --- | --- | --- |
| 1 | Creates its uncommitted request record | Creates its own uncommitted request record |
| 2 | Acquires the doctor lock, then slot lock | Waits for the same doctor lock |
| 3 | Finds no reservation; writes appointment/reservation/event | Still waiting |
| 4 | Commits and releases locks | Acquires locks |
| 5 | Receives the appointment result | Sees occupancy and gets a conflict; transaction rolls back |

**Result:** one active reservation. The losing request should refresh availability. The concurrency test checks that exactly one contender succeeds.

### 20. Why not use Java synchronized?

It coordinates threads sharing the relevant monitor in one JVM. A second application instance has separate memory and could bypass it.

**Why database coordination:** all instances that use the same transactional database share its locks and constraints. This does not by itself make sessions, rate limits or the rest of the application replica-ready.

### 21. Why lock the doctor before the slot?

Booking must coordinate with doctor archiving and slot/lifecycle changes. A doctor-level lock gives these operations a common coordination point. The scheduling lock order is **doctor -> slot -> appointment**, where those records are involved.

**Tradeoff:** bookings for different slots belonging to the same doctor can serialize. This simplifies correctness but can limit write parallelism. Some operations skip a lock they do not need; booking first inserts its idempotency record before acquiring these scheduling locks.

### 22. Why use both locks and unique constraints?

Locks make multi-step decisions safe while related operations run. Constraints remain a database backstop against inconsistent writes or future code paths that forget an application check.

**Tradeoff:** more coordination and error handling. A unique constraint does not enforce every business rule, such as doctor activity or “start time must be in the future.”

### 23. What is idempotency here?

For booking, submitting the same account/request key with the same payload returns the original appointment ID instead of creating a second appointment.

**Why:** the client might retry after a timeout even though the first request committed. Disabling the submit button and using redirects are useful UX measures, but neither solves an uncertain network outcome.

### 24. Why scope the request key to the patient?

`booking_request` has primary key `(user_id, request_key)`. A request key is therefore associated with the authenticated account's operation rather than treated as a globally shared identity.

**Why:** another patient's use of the same text key cannot resolve to the first patient's appointment. The acting user ID comes from trusted session state, not a submitted `userid` field.

### 25. Why store a payload fingerprint as well as a key?

A key alone cannot distinguish a retry from reuse for a different booking. The service hashes the slot and submitted booking fields using SHA-256 over an unambiguous encoded representation.

Same key/same fingerprint returns the original appointment. Same key/different fingerprint returns **409 Conflict**. This fingerprint is not a password hash, a digital signature, or encryption of patient information.

### 26. What happens when the same key arrives concurrently?

The unique request record is inserted inside the booking transaction. A competing insert waits for resolution at that database uniqueness boundary. If the first commits, the second gets a duplicate-key failure, rolls back and reads the committed original result.

**Why:** resolving the request result at the shared database works across application instances. If the first transaction rolls back, a waiting contender may proceed rather than finding a committed duplicate.

### 27. What is the booking transaction boundary?

The group includes the request record, appointment, active reservation, completed request-to-appointment link and booking event. The service borrows one connection, disables auto-commit, executes the work, then commits. SQL and runtime failures trigger rollback.

**Why:** an appointment without occupancy, an occupied slot without a valid appointment, or a committed appointment without its required event would be inconsistent outcomes.

### 28. What happens if the audit/event insert fails?

Booking fails and the entire transaction rolls back, including earlier appointment, reservation and request-key writes. The test suite injects a late failure and verifies those writes do not remain.

**Tradeoff:** recording the event is part of successful scheduling, so an event-table failure blocks booking. That is an explicit consistency decision.

### 29. What if the server commits but the response never reaches the browser?

The browser cannot know whether booking succeeded merely from the timeout. Retrying with the **same key and same payload** allows the service to retrieve the committed result.

**Boundary:** this provides an idempotent booking effect within the transaction model, not a universal “exactly once delivery” guarantee. Future email/payment side effects would need their own durable coordination.

### 30. What if the appointment is cancelled and the original request is retried?

The original request key still identifies the original appointment, including its cancelled state. It must not silently reserve the slot again. A deliberate new booking needs a new request key.

**Why:** a retry should recover the original operation's result, not reinterpret the user's earlier intention as a new booking.

### 31. How do transactions and ACID apply here?

- **Atomicity:** related booking writes commit or roll back together.
- **Consistency:** constraints and domain rules preserve valid relationships and occupancy.
- **Isolation:** locks coordinate concurrent changes to shared scheduling records.
- **Durability:** committed data persistence depends on the database's durability configuration.

The code does not explicitly set transaction isolation; it uses the datasource/database configuration. Do not claim the application explicitly selects `SERIALIZABLE`. A transaction alone is not proof that every race has been handled.

### 32. Can deadlocks or database failures still happen?

Yes. A consistent lock order reduces avoidable deadlocks but does not prove they are impossible in every database interaction. Transactions can also fail because of timeouts or connectivity loss.

**Current boundary:** there is no general automatic deadlock retry loop in `AppointmentService`. A future bounded retry would need to classify transient errors, retry the whole transaction and preserve the request key. Never blindly retry every SQL exception.

**Code for this section:** [AppointmentService.book, transaction, fingerprint and transition](../src/main/java/com/service/AppointmentService.java), [concurrency/rollback contract tests](../src/test/java/com/service/AppointmentServiceTest.java).

## D. Security and trusted identity

### 33. What is the difference between authentication and authorization?

Authentication establishes who signed in. Authorization decides whether that identity may perform a particular action on a particular record.

**Example:** being a signed-in doctor does not permit reading another doctor's patient's appointment. Route-role checks and record-ownership checks address different layers.

### 34. Why not trust hidden user or doctor IDs from the form?

A user can edit a request independently of the page. The controller creates an `Actor` from server-held session identity and uses that identity for the operation.

**Why:** hidden fields are input, not proof of authority. Resource IDs still need validation and authorization even when the actor is trusted.

### 35. Why does an inaccessible appointment return 404?

The service returns the same “not found” result when the appointment is missing or does not belong to the actor, except for permitted administrator access.

**Why:** avoid revealing another account's record existence through that endpoint. This is deliberate resource-level behavior; it does not mean every unauthorized operation everywhere returns 404.

### 36. How are passwords stored, and why this method?

The implementation uses PBKDF2-HMAC-SHA256 with a random 16-byte salt, 600,000 iterations and a 256-bit derived key. The stored representation contains the algorithm, work factor, salt and derived key.

**Why:** passwords need a deliberately expensive one-way verification function. Unique salts prevent identical passwords producing identical stored hashes. PBKDF2 is available through the JDK; the exact work factor is a current code setting, not a permanent universal recommendation.

### 37. Why not plain SHA-256, encryption or Base64 for passwords?

Fast hashes make offline guessing cheaper. Reversible encryption introduces a decryption key whose compromise exposes passwords. Base64 merely encodes bytes.

**Important distinction:** SHA-256 is used for booking-payload fingerprints because those need equality detection, whereas password verification deliberately needs a costly derivation function. The two jobs have different requirements.

### 38. Why upgrade password hashes on successful login?

A successful login provides the original password, allowing the application to derive a stronger hash for supported older work factors. DAO login code conditionally stores the replacement.

**Why:** improve existing account hashes without requiring everyone to reset at once. The current verifier does not accept the original project's plaintext passwords. Rehashing and an actual password change have different session-revocation semantics.

### 39. Why sessions instead of JWTs?

The browser application already uses server-rendered pages and session cookies. Server-held identity is straightforward for this architecture and does not require introducing a token service.

**Tradeoff:** local sessions complicate multiple replicas and restarts. JWTs would bring expiry, key management and revocation decisions; they are not automatically better or more secure.

### 40. Why rotate session IDs and check auth_version?

Login rotates the session ID, clears other role markers and refreshes the CSRF token. Patient/doctor sessions carry an authentication version that the filter compares with the database.

**Why:** rotation reduces session-fixation risk. Incrementing the account version on password change or doctor archival makes older sessions fail on their next check. Administrator sessions use a stamp derived from configured credentials rather than the patient/doctor version column.

**Tradeoff:** checking account validity adds database work. It is request-time invalidation, not a claim that every already-running request is instantly cancelled.

### 41. Why do cookie-based POST actions need CSRF tokens?

A browser can attach its session cookie to a request even when the signed-in user did not intentionally submit the application's form. A session-bound token gives the server an additional check on the POST.

**Implementation:** `RequestFilter` compares the form token to the session token; login refreshes it. Logout is a POST action. SameSite cookies help but are not treated as a replacement for token checks.

### 42. How are SQL injection and XSS addressed differently?

Prepared statements bind values separately from SQL syntax. Output escaping prevents user input from becoming executable browser markup in the contexts where it is rendered.

**Why both:** parameterized SQL does not protect HTML, and HTML escaping does not protect a database query. JSP Expression Language does not automatically escape every output; templates use JSTL `c:out` where needed. Other contexts, such as JavaScript or URLs, need appropriate encoding/validation.

### 43. What protections exist beyond login?

The application includes route restrictions, ownership checks, input bounds, login throttling, generic error messages, request IDs, no-store dynamic responses and security headers. Cookies are configured with HttpOnly/SameSite; secure cookies require the HTTPS deployment setting.

**Boundary:** the content policy still allows selected CDNs and inline styles. Login throttling is process-local, and its check/update path should not be described as a perfectly exact distributed rate limiter. HTTPS, MFA and verified email delivery are not provided simply by running this demo.

**Code for this section:** [RequestFilter](../src/main/java/com/security/RequestFilter.java), [Passwords](../src/main/java/com/security/Passwords.java), [LoginLimiter](../src/main/java/com/security/LoginLimiter.java), [HospitalService](../src/main/java/com/service/HospitalService.java), [ActionController](../src/main/java/com/web/ActionController.java).

## E. Java, JDBC and testability

### 44. Why HikariCP instead of opening a physical connection for every request?

A pool reuses a bounded number of database connections. `DBConnect.open()` borrows a connection; closing that JDBC wrapper returns it to the pool.

**Why:** avoid repeated connection setup and cap concurrent database usage. The current default maximum is 10 with a five-second acquisition timeout. A larger pool can increase database contention rather than improve throughput.

### 45. Why try-with-resources, and who owns a transaction?

Try-with-resources closes JDBC results, statements and borrowed connections even when an exception occurs. The service owns the connection and the transaction for the whole business operation.

**Why:** opening unrelated connections for individual writes would prevent those writes from sharing one atomic transaction. Connection closure is necessary, but explicit rollback is still part of transaction failure handling.

### 46. Are servlets and shared services automatically thread-safe?

No. Servlet instances can handle overlapping requests. Mutable per-request information must not live in shared instance fields.

The controllers use local variables for request-specific data. `AppointmentService` holds a datasource and clock rather than a shared current booking or connection. Database correctness still requires database coordination; avoiding Java data races alone is insufficient.

### 47. Why inject DataSource and Clock into AppointmentService?

`DataSource` lets the service borrow real connections from the configured test/application pool. `Clock` makes current-time business rules controllable in tests.

**Why:** confirmation and completion tests should not depend on the machine clock or sleeping until a real appointment time. Tests use a fixed clock. This is dependency injection through constructors without requiring a DI framework.

### 48. Why use records and different exception types?

The booking record groups the submitted booking values into a fixed data carrier instead of passing a long list of unrelated parameters. A record's component references are final; that does not make arbitrary referenced objects deeply immutable.

`ServiceException` carries expected domain failures and an HTTP status. `DataAccessException` identifies persistence failures. **Why:** “slot conflict,” “bad input” and “database unavailable” need different handling. Do not return an empty list to disguise a failed query.

### 49. Why JDBC rather than Hibernate/JPA?

Manual JDBC keeps the SQL, lock acquisition and transaction boundaries explicit and fits the inherited stack. Prepared statements and explicit row mapping are easy to inspect during this project discussion.

**Tradeoff:** more repetitive mapping and SQL, with less abstraction. An ORM can reduce boilerplate but would still need deliberate fetch plans, transaction boundaries and concurrency behavior.

**Code for this section:** [DBConnect](../src/main/java/com/db/DBConnect.java), [AppointmentService](../src/main/java/com/service/AppointmentService.java), [AppointmentServiceTest](../src/test/java/com/service/AppointmentServiceTest.java).

## F. Testing, migrations and evidence

### 50. What are the most valuable tests?

The tests that exercise the central failure modes: simultaneous patients competing for one slot, simultaneous duplicate-key submissions, payload mismatch, cancellation/rebooking, illegal ownership/transitions and rollback after an injected late audit failure.

**Why:** a happy-path insert test cannot establish these guarantees. The Docker rebuild in this session passed 13 Java tests. Prior H2/MySQL and 85-assertion HTTP results are documented in [the verification record](DOCKER_VERIFICATION.md); those full historical runs were not repeated just to remove the frontend notice.

### 51. How do you test concurrency without relying on Thread.sleep?

The tests use multiple executor tasks, separate service/connection activity and latches to coordinate contenders. For the same-slot test, a readiness latch and a start latch bring both tasks to the contested operation. Futures have bounded waits and the database result is checked.

**Boundary:** coordinated tests catch specific interleavings; they are not a mathematical proof of every possible schedule. Use production-database tests as well as the design invariants.

### 52. Why test H2 and MySQL?

H2 provides convenient local tests. MySQL is the deployment database and can differ in SQL syntax, locking, constraints and migration behavior.

**Why:** H2 MySQL compatibility mode does not make H2 identical to MySQL. The same scheduling contracts support an isolated MySQL `hms_test` database. Do not run fixtures or benchmarks against the ordinary application database.

### 53. Why Flyway, and what if a migration fails?

Flyway applies ordered versioned migrations and validates recorded checksums. It makes schema state reproducible rather than dependent on undocumented manual edits.

**Failure handling:** inspect the actual database and migration history before repair. MySQL DDL can implicitly commit, so a business-transaction rollback is not a general migration rollback strategy. Back up before adopting a legacy schema; baselining requires an explicit setting outside demo mode. Do not edit an already-applied migration just to silence a checksum error.

### 54. Does an HTTP smoke test replace browser testing or prove CI ran?

No. HTTP checks validate response and workflow behavior, including sessions and authorization. They do not establish visual layout, accessibility or keyboard usability.

Likewise, a configured GitHub Actions file describes an automated pipeline; it does not prove a hosted run completed. Use an actual run record before claiming successful hosted CI. Local Docker build results are separate evidence.

**Code for this section:** [Java scheduling contracts](../src/test/java/com/service/AppointmentServiceTest.java), [legacy migration test](../src/test/java/com/service/MigrationTest.java), [HTTP checks](../scripts/smoke_test.py), [CI workflow](../.github/workflows/ci.yml).

## G. Performance, deployment and operations

### 55. Why paginate and use COUNT(*) rather than count fetched rows?

Pagination bounds response size and per-request work; appointment lists use 20 rows per page and available slots are capped at 200. A SQL aggregate returns the requested count rather than transferring every matching row for Java to count.

**Tradeoff:** the database still evaluates the count, and offset pagination can become costly at large offsets. Indexing and keyset pagination should be considered based on query plans and measured workloads.

### 56. What performance improvements can you honestly claim?

The saved query comparison used 10,000 synthetic appointments, the same indexed filter/connection, five warmups and 20 samples. Fetch-and-count averaged 3.9291 ms; `COUNT(*)` averaged 1.8465 ms, about **2.13x for that query comparison**.

The recorded local read workload used eight authenticated clients for 15 seconds: 37,192 requests, about 2,479 requests/second, p95 5.31 ms and zero observed errors. It used a small dataset and empty benchmark-account histories, excluding login/setup/warmup.

**Do not say:** “the whole app is 2.13x faster,” “booking handles 2,479 writes/second,” or “this is production capacity.” These are saved measurements from specific environments, not a fresh benchmark performed while writing this file. See [the report and raw results](performance/REPORT.md).

### 57. Why Docker, a multi-stage image and a non-root runtime?

Docker packages the runtime environment. The build stage runs Maven verification and creates the WAR; the final stage runs it in Tomcat without requiring Maven as the build toolchain. The application runs as UID/GID 10001.

**Why:** reproducible startup and a smaller separation between build tooling and runtime responsibilities. Non-root execution limits privileges; it is not a complete security boundary. Compose supplies MySQL, readiness dependency and a persistent database volume.

### 58. What is the difference between liveness and readiness?

`/health/live` indicates that the web application can respond. `/health/ready` borrows a database connection and executes `SELECT 1`; it returns 503 on failure.

**Why separate them:** a responding web process may still be unable to serve database-backed features. Readiness is a limited check, not proof that every operation will succeed. The earlier stopped-database incident demonstrated this distinction: both services had to be healthy before the app was usable.

### 59. How would you diagnose slow or failing requests?

Use the response's request ID to find route/status/duration logs. Check application/database readiness, pool saturation, lock waits, query plans and recent changes. Admin-only metrics provide process-local request/error/duration counters.

**Why:** separate application, connection, database and contention problems before optimizing. Logs avoid passwords and patient form/note content. A future production setup needs centralized monitoring, useful latency distributions and alerting; current counters are not distributed tracing.

### 60. What prevents simply adding more application replicas?

Database-backed booking coordination can span instances, but server sessions, login throttling and operational counters are currently process-local. More replicas also create more potential database connections.

**Next decisions:** shared sessions or another deliberate authentication strategy, shared throttling, aggregated metrics, pool budgeting and load tests. Sticky sessions alone do not provide durable session failover. Read replicas also need care because stale availability reads cannot authorize a booking on their own.

**Code/evidence for this section:** [Dockerfile](../Dockerfile), [Compose](../compose.yaml), [OperationsController](../src/main/java/com/web/OperationsController.java), [RequestFilter](../src/main/java/com/security/RequestFilter.java), [performance report](performance/REPORT.md).

## H. Deeper follow-ups and project judgement

### 61. Why not cache slot availability immediately?

A cache can improve some read workloads but introduces invalidation and staleness. A patient may see a cached available slot that was just reserved.

**Decision:** keep correctness at the transactional database and add caching only after measurement justifies it. Even with caching, booking must revalidate availability under the write-coordination mechanism. Caching is a future option, not an implemented feature here.

### 62. How would you add email notifications reliably?

This is future work. Sending email inside the database transaction holds locks during an external call and still cannot atomically commit an email provider's state with MySQL. Sending after commit without durable bookkeeping can lose the notification if the process crashes.

**Possible design:** insert an outbox event in the booking transaction, then deliver it asynchronously with retries and duplicate handling. Explain this as a proposal; there is no implemented message broker or outbox in the project.

### 63. How are time and scheduling simplified?

The current model publishes aligned 30-minute slots and uses a configured hospital zone, defaulting to Asia/Kolkata. Injected clocks make time-sensitive behavior testable.

**Boundary:** JDBC timestamp conversion and local date-time handling must be reviewed before multi-zone scheduling. Variable durations, overlapping intervals, daylight-saving transitions and holidays need explicit modelling/tests; the current fixed-slot model does not solve all of those cases.

### 64. Is this ready to handle real patient information in production?

It is a functional engineering prototype with security measures, not demonstrated production healthcare readiness. Public hosting/TLS, verified identities, backups/restores, access reviews, retention policies, incident handling and stronger audit controls require further work.

**Why this answer matters:** data integrity tests and password hashing do not establish operational or regulatory readiness. Do not claim compliance certification or real hospital adoption without evidence.

### 65. What would you improve next?

First strengthen evidence around the existing design: run hosted CI, add sustained MySQL write-contention tests, exercise archive/cancel/book races and document backup/restore behavior. Then prioritize deployment/monitoring, email ownership verification and session/rate-limit strategy as requirements justify them.

**Why this order:** verify the core guarantees and operational behavior before adding architectural complexity. A framework migration, cache or broker should solve an identified problem rather than serve as a résumé keyword.

### 66. Why is this relevant to a payments-oriented engineering discussion?

The transferable ideas are transaction boundaries, concurrent resource allocation, retry handling, identity/authorization and audit history. They let you discuss how software maintains invariants despite overlapping requests and uncertain responses.

**Boundary:** an appointment reservation is not a payment ledger. This application does not implement money movement, settlement, reconciliation or distributed financial transactions. Visa-specific team requirements depend on the actual opening; these are project discussion themes, not claimed interview questions.

### 67. What was the hardest technical problem?

Use this answer only if it reflects your work:

> The hardest problem was coordinating booking, retries and cancellation. A simple availability check allowed a read/write race. The solution combines a transactional active-reservation table, a consistent scheduling lock order and database request-key uniqueness. I checked the behavior with concurrent tests, then tested cancellation and retry semantics so fixing double booking did not erase history or create a second operation.

Be prepared to open `book()` and explain each write and its failure behavior. If a different part was your actual contribution, describe that instead.

### 68. What would you say if asked something the project does not prove?

State what exists, what the evidence shows and what remains untested. For example:

> I have verified the transaction contracts and a limited local read benchmark. I have not demonstrated production write capacity or multi-region behavior. To answer that, I would design a realistic contention workload, collect latency and database metrics, and test failure recovery before making a capacity claim.

**Why:** engineering judgement includes identifying the boundary of your evidence.

## A five-minute demonstration plan

1. **Scope and ownership:** give the short introduction and identify your actual contributions.
2. **Business flow:** show patient booking, doctor confirmation and separate consultation history using fictional data.
3. **Correctness:** open `AppointmentService.book`; point to the request insert, doctor/slot locks, reservation and event before commit.
4. **Evidence:** show the simultaneous-booking and duplicate-key tests and a genuine build/test result.
5. **Judgement:** explain doctor-level serialization, local-only deployment and the limits of the recorded benchmark.

If a required demo action depends on a future slot, prepare fictional availability first. Avoid depending on an expired sample slot during the interview.

## Claims to avoid and better wording

| Avoid | Say instead |
| --- | --- |
| “I built the entire project from scratch.” | “I adapted an existing project; these are my specific changes.” |
| “The app is fully secure.” | “These controls are implemented; these deployment and security gaps remain.” |
| “It guarantees exactly-once execution.” | “Repeated booking requests with the same account/key/payload resolve to one committed booking result.” |
| “The app supports thousands of bookings per second.” | “The saved result is a limited local authenticated read workload.” |
| “There can never be a deadlock.” | “A consistent lock order reduces risk; transient failures still need handling.” |
| “The audit log is immutable.” | “Notes/events are append-oriented through the application; they are not tamper-proof storage.” |
| “Docker makes it production-ready.” | “Docker makes the tested local app/database deployment reproducible.” |
| “This uses Spring Boot, JWT, Redis or Kafka.” | Name only implemented technologies; label proposed additions as future work. |
| “CI passed because a workflow exists.” | Distinguish local results from an actual successful hosted run. |

## Quick revision sheet

| Term | Meaning in this project |
| --- | --- |
| Invariant | A rule that must remain true, such as one active reservation per slot |
| Transaction | A group of writes committed or rolled back together |
| Race condition | Outcome depends on overlapping operations without sufficient coordination |
| Row lock | Database coordination that makes conflicting transactions wait |
| Idempotency | Same booking operation can be retried without a second booking effect |
| Constraint | Database-enforced validity rule, such as a primary or foreign key |
| Authentication | Establish the signed-in identity |
| Authorization | Decide which records/actions that identity may access |
| Connection pool | Bounded reusable database connections |
| Migration | Versioned schema/data evolution step |
| 303 | Redirect to a GET after successful form processing |
| 409 | Request conflicts with current state or prior key usage |
| p95 | Approximately 95% of measured request latencies were at or below this value |

Before an interview, be able to explain **the booking race, idempotent retry, cancellation/history separation, ownership checks and one meaningful test** in your own words. Those explanations are stronger than memorizing every answer.
