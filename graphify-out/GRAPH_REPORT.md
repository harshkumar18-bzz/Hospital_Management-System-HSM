# Graph Report - Hospital_Management-System-HSM  (2026-11-08)

## Corpus Check
- 71 files · ~263,026 words
- Verdict: corpus is large enough that graph structure adds value.
- Unclassified: 40 file(s) not represented in the graph (top: .jsp 30, (none) 5, .xml 2)

## Summary
- 881 nodes · 1352 edges · 35 communities (22 shown, 13 thin omitted)
- Extraction: 94% EXTRACTED · 6% INFERRED · 0% AMBIGUOUS · INFERRED: 84 edges (avg confidence: 0.86)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `7ec79ddf`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- jakarta.servlet.annotation.WebServlet
- jakarta.servlet.http.HttpServletRequest
- User
- Doctor
- Specalist
- Appointments
- Numbered source walkthrough
- HospitalService.java
- smoke_test.py
- LocalServer.java
- .doGet
- com.hospital:Hospital_Management_System
- HospitalService
- Numbered source walkthrough
- AppointmentService
- AppointmentRow
- environment
- Hospital Management System: current architecture and interview guide
- steps
- Slot
- Hospital Management System: current architecture and interview guide
- DataAccessException
- D. Security and trusted identity
- Hospital Management System: current architecture and interview guide
- LoginLimiter.java
- C. Transactions, concurrency and retries
- CountBenchmark
- Hospital Management System: interview questions and design decisions
- B. Database design and lifecycle
- H. Deeper follow-ups and project judgement
- E. Java, JDBC and testability
- G. Performance, deployment and operations
- DoctorDao
- F. Testing, migrations and evidence

## God Nodes (most connected - your core abstractions)
1. `Numbered source walkthrough` - 100 edges
2. `Numbered source walkthrough` - 100 edges
3. `Doctor` - 34 edges
4. `AppointmentService` - 30 edges
5. `Appointments` - 26 edges
6. `AppointmentRow` - 25 edges
7. `ActionController` - 24 edges
8. `HospitalService` - 20 edges
9. `Actor` - 19 edges
10. `AppointmentServiceTest` - 18 edges

## Surprising Connections (you probably didn't know these)
- `34. Why not trust hidden user or doctor IDs from the form?` --references--> `Actor`  [INFERRED]
  docs/INTERVIEW_QUESTIONS_AND_DECISIONS.md → src/main/java/com/service/Actor.java
- `41. Why do cookie-based POST actions need CSRF tokens?` --references--> `RequestFilter`  [INFERRED]
  docs/INTERVIEW_QUESTIONS_AND_DECISIONS.md → src/main/java/com/security/RequestFilter.java
- `8. Explain one complete booking request.` --references--> `ActionController`  [INFERRED]
  docs/INTERVIEW_QUESTIONS_AND_DECISIONS.md → src/main/java/com/web/ActionController.java
- `8. Explain one complete booking request.` --references--> `RequestFilter`  [INFERRED]
  docs/INTERVIEW_QUESTIONS_AND_DECISIONS.md → src/main/java/com/security/RequestFilter.java
- `main()` --calls--> `login()`  [EXTRACTED]
  scripts/benchmark.py → scripts/smoke_test.py

## Import Cycles
- None detected.

## Communities (35 total, 13 thin omitted)

### Community 0 - "jakarta.servlet.annotation.WebServlet"
Cohesion: 0.07
Nodes (21): addDoctor, addSpecialist, AdminLogin, adminLogout, DeleteDoctor, UpdateDoctor, DoctorLogin, DoctorLogout (+13 more)

### Community 1 - "jakarta.servlet.http.HttpServletRequest"
Cohesion: 0.13
Nodes (5): DoctorInputs, Html, Inputs, InputsTest, MigrationTest

### Community 2 - "User"
Cohesion: 0.10
Nodes (3): UserDao, User, PasswordsTest

### Community 6 - "Numbered source walkthrough"
Cohesion: 0.02
Nodes (100): 10. src/main/java/com/dao/AppointmentDao.java, 11. src/main/java/com/dao/DoctorDao.java, 12. src/main/java/com/dao/SpecialistDao.java, 13. src/main/java/com/dao/UserDao.java, 14. src/main/java/com/db/DBConnect.java, 15. src/main/java/com/db/DatabaseSetup.java, 16. src/main/java/com/doctor/servlet/DoctorLogin.java, 17. src/main/java/com/doctor/servlet/DoctorLogout.java (+92 more)

### Community 7 - "HospitalService.java"
Cohesion: 0.05
Nodes (4): DatabaseSetup, DBConnect, Passwords, ApiController

### Community 8 - "smoke_test.py"
Cohesion: 0.12
Nodes (6): main(), check(), login(), main(), register(), Session

### Community 13 - "Numbered source walkthrough"
Cohesion: 0.02
Nodes (100): 10. src/main/java/com/dao/AppointmentDao.java, 11. src/main/java/com/dao/DoctorDao.java, 12. src/main/java/com/dao/SpecialistDao.java, 13. src/main/java/com/dao/UserDao.java, 14. src/main/java/com/db/DBConnect.java, 15. src/main/java/com/db/DatabaseSetup.java, 16. src/main/java/com/doctor/servlet/DoctorLogin.java, 17. src/main/java/com/doctor/servlet/DoctorLogout.java (+92 more)

### Community 14 - "AppointmentService"
Cohesion: 0.11
Nodes (6): Actor, AppointmentService, Booking, LockedSlot, Work, AppointmentServiceTest

### Community 15 - "AppointmentRow"
Cohesion: 0.05
Nodes (4): AppointmentDao, AppointmentRow, AuditEvent, Note

### Community 16 - "environment"
Cohesion: 0.06
Nodes (35): build, depends_on, environment, ports, restart, condition, environment, healthcheck (+27 more)

### Community 17 - "Hospital Management System: current architecture and interview guide"
Cohesion: 0.08
Nodes (21): Docker verification, Issue and fix, Start and stop this demo, Verified deployment, Controlled query optimization, Correctness evidence collected alongside measurement, External WAR deployment, Measured performance report (+13 more)

### Community 18 - "steps"
Cohesion: 0.07
Nodes (29): HMS_TEST_ADMIN_PASSWORD, MYSQL_DATABASE, MYSQL_ROOT_PASSWORD, jobs, verify, env, image, options (+21 more)

### Community 20 - "Hospital Management System: current architecture and interview guide"
Cohesion: 0.09
Nodes (22): 10. Migrations and existing data, 11. APIs, health and observability, 12. Testing evidence, 13. Performance evidence and correct interpretation, 14. Deployment and what remains, 15. Interview questions to practise, 1. Why this approach, 2. Source structure (+14 more)

### Community 22 - "D. Security and trusted identity"
Cohesion: 0.07
Nodes (23): 1. What problem does the application solve?, 2. What did you build yourself, and what came from the original project?, 33. What is the difference between authentication and authorization?, 34. Why not trust hidden user or doctor IDs from the form?, 35. Why does an inaccessible appointment return 404?, 36. How are passwords stored, and why this method?, 37. Why not plain SHA-256, encryption or Base64 for passwords?, 38. Why upgrade password hashes on successful login? (+15 more)

### Community 24 - "Hospital Management System: current architecture and interview guide"
Cohesion: 0.09
Nodes (22): 10. Migrations and existing data, 11. APIs, health and observability, 12. Testing evidence, 13. Performance evidence and correct interpretation, 14. Deployment and what remains, 15. Interview questions to practise, 1. Why this approach, 2. Source structure (+14 more)

### Community 26 - "C. Transactions, concurrency and retries"
Cohesion: 0.12
Nodes (17): 17. What is a race condition in this application?, 18. How does the application prevent double booking?, 19. Walk through two patients booking the same slot., 20. Why not use Java synchronized?, 21. Why lock the doctor before the slot?, 22. Why use both locks and unique constraints?, 23. What is idempotency here?, 24. Why scope the request key to the patient? (+9 more)

### Community 28 - "Hospital Management System: interview questions and design decisions"
Cohesion: 0.20
Nodes (10): A five-minute demonstration plan, A short introduction to say to an interviewer, About 30 seconds, About 60–90 seconds, Claims to avoid and better wording, Hospital Management System: interview questions and design decisions, How to use this guide, Quick revision sheet (+2 more)

### Community 29 - "B. Database design and lifecycle"
Cohesion: 0.22
Nodes (9): 10. Why not make appointment.slot_id unique?, 11. Why have foreign keys and composite foreign keys?, 12. Why store patient details on the appointment?, 13. Why separate appointment status from consultation notes?, 14. What transitions are allowed, and why?, 15. Why archive doctors instead of deleting them?, 16. How are existing records migrated without inventing history?, 9. What are the important tables? (+1 more)

### Community 30 - "H. Deeper follow-ups and project judgement"
Cohesion: 0.22
Nodes (9): 61. Why not cache slot availability immediately?, 62. How would you add email notifications reliably?, 63. How are time and scheduling simplified?, 64. Is this ready to handle real patient information in production?, 65. What would you improve next?, 66. Why is this relevant to a payments-oriented engineering discussion?, 67. What was the hardest technical problem?, 68. What would you say if asked something the project does not prove? (+1 more)

### Community 31 - "E. Java, JDBC and testability"
Cohesion: 0.29
Nodes (7): 44. Why HikariCP instead of opening a physical connection for every request?, 45. Why try-with-resources, and who owns a transaction?, 46. Are servlets and shared services automatically thread-safe?, 47. Why inject DataSource and Clock into AppointmentService?, 48. Why use records and different exception types?, 49. Why JDBC rather than Hibernate/JPA?, E. Java, JDBC and testability

### Community 32 - "G. Performance, deployment and operations"
Cohesion: 0.29
Nodes (7): 55. Why paginate and use COUNT(*) rather than count fetched rows?, 56. What performance improvements can you honestly claim?, 57. Why Docker, a multi-stage image and a non-root runtime?, 58. What is the difference between liveness and readiness?, 59. How would you diagnose slow or failing requests?, 60. What prevents simply adding more application replicas?, G. Performance, deployment and operations

### Community 34 - "F. Testing, migrations and evidence"
Cohesion: 0.33
Nodes (6): 50. What are the most valuable tests?, 51. How do you test concurrency without relying on Thread.sleep?, 52. Why test H2 and MySQL?, 53. Why Flyway, and what if a migration fails?, 54. Does an HTTP smoke test replace browser testing or prove CI ran?, F. Testing, migrations and evidence

## Knowledge Gaps
- **1 isolated node(s):** `com.hospital:Hospital_Management_System`
  These have ≤1 connection - possible missing edges or undocumented components. (Counts symbols only; 519 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **13 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `Hospital Management System: interview questions and design decisions` connect `Hospital Management System: interview questions and design decisions` to `G. Performance, deployment and operations`, `F. Testing, migrations and evidence`, `Hospital Management System: current architecture and interview guide`, `D. Security and trusted identity`, `C. Transactions, concurrency and retries`, `B. Database design and lifecycle`, `H. Deeper follow-ups and project judgement`, `E. Java, JDBC and testability`?**
  _High betweenness centrality (0.421) - this node is a cross-community bridge._
- **What connects `com.hospital:Hospital_Management_System` to the rest of the system?**
  _1 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `jakarta.servlet.annotation.WebServlet` be split into smaller, more focused modules?**
  _Cohesion score 0.07390648567119155 - nodes in this community are weakly interconnected._
- **Why does `A five-minute demonstration plan` connect `Hospital Management System: interview questions and design decisions` to `AppointmentService`?**
  _High betweenness centrality (0.252) - this node is a cross-community bridge._
- **Should `jakarta.servlet.http.HttpServletRequest` be split into smaller, more focused modules?**
  _Cohesion score 0.12962962962962962 - nodes in this community are weakly interconnected._
- **Why does `Numbered source walkthrough` connect `Numbered source walkthrough` to `Hospital Management System: current architecture and interview guide`?**
  _High betweenness centrality (0.185) - this node is a cross-community bridge._
- **Should `User` be split into smaller, more focused modules?**
  _Cohesion score 0.10483870967741936 - nodes in this community are weakly interconnected._