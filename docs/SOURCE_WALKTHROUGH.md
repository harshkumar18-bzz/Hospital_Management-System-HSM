# Numbered source walkthrough

Read [INTERVIEW_GUIDE.md](INTERVIEW_GUIDE.md) first for architecture, decisions, flows, limitations and interview answers.

This companion includes all 98 source/configuration files. It preserves every physical source line in numbered snapshots, with annotations for nonblank substantive lines. Blank lines provide spacing. Braces and closing-only delimiters close the surrounding structure and add no independent business rule, so they are visible in snapshots but are not repeated as separate notes. Several compact source lines contain multiple statements/tags; read their combined note.

Binary images, IDE metadata, Git internals, generated target output and graph outputs are excluded. This is a study snapshot; refresh it when source changes. SHA-256 hashes identify each snapshot. Application source was not changed to produce these notes.

## File index

- [pom.xml](#file-1)
- [src/main/resources/db/schema.sql](#file-2)
- [src/main/webapp/WEB-INF/web.xml](#file-3)
- [src/main/java/com/admin/servlet/AdminLogin.java](#file-4)
- [src/main/java/com/admin/servlet/DeleteDoctor.java](#file-5)
- [src/main/java/com/admin/servlet/UpdateDoctor.java](#file-6)
- [src/main/java/com/admin/servlet/addDoctor.java](#file-7)
- [src/main/java/com/admin/servlet/addSpecialist.java](#file-8)
- [src/main/java/com/admin/servlet/adminLogout.java](#file-9)
- [src/main/java/com/dao/AppointmentDao.java](#file-10)
- [src/main/java/com/dao/DoctorDao.java](#file-11)
- [src/main/java/com/dao/SpecialistDao.java](#file-12)
- [src/main/java/com/dao/UserDao.java](#file-13)
- [src/main/java/com/db/DBConnect.java](#file-14)
- [src/main/java/com/db/DatabaseSetup.java](#file-15)
- [src/main/java/com/doctor/servlet/DoctorLogin.java](#file-16)
- [src/main/java/com/doctor/servlet/DoctorLogout.java](#file-17)
- [src/main/java/com/doctor/servlet/EditProfileDoctor.java](#file-18)
- [src/main/java/com/doctor/servlet/PosswordChange.java](#file-19)
- [src/main/java/com/doctor/servlet/UpdateStatus.java](#file-20)
- [src/main/java/com/entity/AppointmentRow.java](#file-21)
- [src/main/java/com/entity/Appointments.java](#file-22)
- [src/main/java/com/entity/AuditEvent.java](#file-23)
- [src/main/java/com/entity/Doctor.java](#file-24)
- [src/main/java/com/entity/Note.java](#file-25)
- [src/main/java/com/entity/Slot.java](#file-26)
- [src/main/java/com/entity/Specalist.java](#file-27)
- [src/main/java/com/entity/User.java](#file-28)
- [src/main/java/com/security/DoctorInputs.java](#file-29)
- [src/main/java/com/security/Html.java](#file-30)
- [src/main/java/com/security/Inputs.java](#file-31)
- [src/main/java/com/security/LoginLimiter.java](#file-32)
- [src/main/java/com/security/Passwords.java](#file-33)
- [src/main/java/com/security/RequestFilter.java](#file-34)
- [src/main/java/com/service/Actor.java](#file-35)
- [src/main/java/com/service/AppointmentService.java](#file-36)
- [src/main/java/com/service/DataAccessException.java](#file-37)
- [src/main/java/com/service/HospitalService.java](#file-38)
- [src/main/java/com/service/ServiceException.java](#file-39)
- [src/main/java/com/user/servlet/Appointment.java](#file-40)
- [src/main/java/com/user/servlet/ChangePassword.java](#file-41)
- [src/main/java/com/user/servlet/UserLogin.java](#file-42)
- [src/main/java/com/user/servlet/UserLogout.java](#file-43)
- [src/main/java/com/user/servlet/UserRegister.java](#file-44)
- [src/main/java/com/web/ActionController.java](#file-45)
- [src/main/java/com/web/ApiController.java](#file-46)
- [src/main/java/com/web/Metrics.java](#file-47)
- [src/main/java/com/web/OperationsController.java](#file-48)
- [src/main/java/com/web/PageController.java](#file-49)
- [src/main/java/com/web/SchedulingActions.java](#file-50)
- [src/dev/java/com/hospital/dev/LocalServer.java](#file-51)
- [src/test/java/com/perf/CountBenchmark.java](#file-52)
- [src/test/java/com/security/InputsTest.java](#file-53)
- [src/test/java/com/security/PasswordsTest.java](#file-54)
- [src/test/java/com/service/AppointmentServiceTest.java](#file-55)
- [src/test/java/com/service/MigrationTest.java](#file-56)
- [src/main/resources/db/migration/V1__initial_schema.sql](#file-57)
- [src/main/resources/db/migration/V2__scheduled_appointments.sql](#file-58)
- [src/main/resources/db/migration/V3__integrity_and_credential_storage.sql](#file-59)
- [Dockerfile](#file-60)
- [compose.yaml](#file-61)
- [deploy/server.xml](#file-62)
- [.github/workflows/ci.yml](#file-63)
- [scripts/benchmark.py](#file-64)
- [src/main/webapp/WEB-INF/views/admin/appointment.jsp](#file-65)
- [src/main/webapp/WEB-INF/views/admin/doctor.jsp](#file-66)
- [src/main/webapp/WEB-INF/views/admin/edit_doctor.jsp](#file-67)
- [src/main/webapp/WEB-INF/views/admin/index.jsp](#file-68)
- [src/main/webapp/WEB-INF/views/admin/navbar.jsp](#file-69)
- [src/main/webapp/WEB-INF/views/admin/patient.jsp](#file-70)
- [src/main/webapp/WEB-INF/views/admin/slots.jsp](#file-71)
- [src/main/webapp/WEB-INF/views/admin/view_doctor.jsp](#file-72)
- [src/main/webapp/WEB-INF/views/admin.jsp](#file-73)
- [src/main/webapp/WEB-INF/views/appointment.jsp](#file-74)
- [src/main/webapp/WEB-INF/views/change_password.jsp](#file-75)
- [src/main/webapp/WEB-INF/views/component/allcss.jsp](#file-76)
- [src/main/webapp/WEB-INF/views/component/appointment_detail.jsp](#file-77)
- [src/main/webapp/WEB-INF/views/component/appointment_table.jsp](#file-78)
- [src/main/webapp/WEB-INF/views/component/doctor_form.jsp](#file-79)
- [src/main/webapp/WEB-INF/views/component/footer.jsp](#file-80)
- [src/main/webapp/WEB-INF/views/component/messages.jsp](#file-81)
- [src/main/webapp/WEB-INF/views/component/navbar.jsp](#file-82)
- [src/main/webapp/WEB-INF/views/doctor/comment.jsp](#file-83)
- [src/main/webapp/WEB-INF/views/doctor/edit_profile.jsp](#file-84)
- [src/main/webapp/WEB-INF/views/doctor/index.jsp](#file-85)
- [src/main/webapp/WEB-INF/views/doctor/navbar.jsp](#file-86)
- [src/main/webapp/WEB-INF/views/doctor/patient.jsp](#file-87)
- [src/main/webapp/WEB-INF/views/doctor/slots.jsp](#file-88)
- [src/main/webapp/WEB-INF/views/doctor.jsp](#file-89)
- [src/main/webapp/WEB-INF/views/error.jsp](#file-90)
- [src/main/webapp/WEB-INF/views/index.jsp](#file-91)
- [src/main/webapp/WEB-INF/views/signup.jsp](#file-92)
- [src/main/webapp/WEB-INF/views/user_Login.jsp](#file-93)
- [src/main/webapp/WEB-INF/views/user_appointment.jsp](#file-94)
- [src/main/webapp/WEB-INF/views/view_appointment.jsp](#file-95)
- [src/main/webapp/component/app.css](#file-96)
- [src/main/webapp/component/style.css](#file-97)
- [scripts/smoke_test.py](#file-98)

<a id="file-1"></a>

## 1. pom.xml

Maven build, dependency scopes, WAR packaging and opt-in local runtime profile.

Source SHA-256: `905e21924d3d01ab9f26adba740d9f9d108b0aaef96eba49456075077a7d7b4c`

<details>
<summary>Numbered source (34 physical lines)</summary>

~~~text
   1 | <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
   2 |   <modelVersion>4.0.0</modelVersion>
   3 |   <groupId>com.hospital</groupId><artifactId>Hospital_Management_System</artifactId><version>1.0.0-SNAPSHOT</version>
   4 |   <packaging>war</packaging><name>Hospital Management System</name>
   5 |   <properties><maven.compiler.release>17</maven.compiler.release><project.build.sourceEncoding>UTF-8</project.build.sourceEncoding><tomcat.version>10.1.61</tomcat.version></properties>
   6 |   <dependencies>
   7 |     <dependency><groupId>jakarta.servlet</groupId><artifactId>jakarta.servlet-api</artifactId><version>6.0.0</version><scope>provided</scope></dependency>
   8 |     <dependency><groupId>jakarta.servlet.jsp.jstl</groupId><artifactId>jakarta.servlet.jsp.jstl-api</artifactId><version>3.0.0</version></dependency>
   9 |     <dependency><groupId>org.glassfish.web</groupId><artifactId>jakarta.servlet.jsp.jstl</artifactId><version>3.0.1</version></dependency>
  10 |     <dependency><groupId>com.mysql</groupId><artifactId>mysql-connector-j</artifactId><version>8.4.0</version></dependency>
  11 |     <dependency><groupId>junit</groupId><artifactId>junit</artifactId><version>4.13.2</version><scope>test</scope></dependency>
  12 |     <dependency><groupId>com.fasterxml.jackson.core</groupId><artifactId>jackson-databind</artifactId><version>2.19.0</version></dependency>
  13 |     <dependency><groupId>com.zaxxer</groupId><artifactId>HikariCP</artifactId><version>6.3.0</version></dependency>
  14 |     <dependency><groupId>org.flywaydb</groupId><artifactId>flyway-core</artifactId><version>13.12.1</version></dependency>
  15 |     <dependency><groupId>org.flywaydb</groupId><artifactId>flyway-mysql</artifactId><version>13.12.1</version></dependency>
  16 |     <dependency><groupId>org.slf4j</groupId><artifactId>slf4j-api</artifactId><version>2.0.17</version></dependency>
  17 |     <dependency><groupId>org.slf4j</groupId><artifactId>slf4j-jdk14</artifactId><version>2.0.17</version></dependency>
  18 |     <dependency><groupId>com.h2database</groupId><artifactId>h2</artifactId><version>2.3.232</version><scope>test</scope></dependency>
  19 |   </dependencies>
  20 |
  21 |   <build><finalName>Hospital_Management_System</finalName><plugins>
  22 |     <plugin><groupId>org.apache.maven.plugins</groupId><artifactId>maven-compiler-plugin</artifactId><version>3.14.0</version></plugin>
  23 |     <plugin><groupId>org.apache.maven.plugins</groupId><artifactId>maven-war-plugin</artifactId><version>3.4.0</version></plugin>
  24 |     <plugin><groupId>org.apache.maven.plugins</groupId><artifactId>maven-surefire-plugin</artifactId><version>3.5.3</version></plugin>
  25 |   </plugins></build>
  26 |   <profiles><profile><id>local</id><dependencies>
  27 |     <dependency><groupId>com.h2database</groupId><artifactId>h2</artifactId><version>2.3.232</version></dependency>
  28 |     <dependency><groupId>org.apache.tomcat.embed</groupId><artifactId>tomcat-embed-core</artifactId><version>${tomcat.version}</version></dependency>
  29 |     <dependency><groupId>org.apache.tomcat.embed</groupId><artifactId>tomcat-embed-jasper</artifactId><version>${tomcat.version}</version></dependency>
  30 |   </dependencies><build><plugins>
  31 |     <plugin><groupId>org.codehaus.mojo</groupId><artifactId>build-helper-maven-plugin</artifactId><version>3.6.0</version><executions><execution><id>local-runner</id><phase>generate-sources</phase><goals><goal>add-source</goal></goals><configuration><sources><source>src/dev/java</source></sources></configuration></execution></executions></plugin>
  32 |     <plugin><groupId>org.codehaus.mojo</groupId><artifactId>exec-maven-plugin</artifactId><version>3.5.0</version><configuration><mainClass>com.hospital.dev.LocalServer</mainClass><classpathScope>compile</classpathScope></configuration></plugin>
  33 |   </plugins></build></profile></profiles>
  34 | </project>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 2 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 3 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 4 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 5 | Centralizes Java 17 release targeting, UTF-8 source encoding and the embedded Tomcat version. |
| 6 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 7 | Declares dependency jakarta.servlet-api at the shown version with provided scope. Provided dependencies come from the container; test dependencies are excluded from the runtime WAR. |
| 8 | Declares dependency jakarta.servlet.jsp.jstl-api at the shown version. Provided dependencies come from the container; test dependencies are excluded from the runtime WAR. |
| 9 | Declares dependency jakarta.servlet.jsp.jstl at the shown version. Provided dependencies come from the container; test dependencies are excluded from the runtime WAR. |
| 10 | Declares dependency mysql-connector-j at the shown version. Provided dependencies come from the container; test dependencies are excluded from the runtime WAR. |
| 11 | Declares dependency junit at the shown version with test scope. Provided dependencies come from the container; test dependencies are excluded from the runtime WAR. |
| 12 | Declares dependency jackson-databind at the shown version. Provided dependencies come from the container; test dependencies are excluded from the runtime WAR. |
| 13 | Declares dependency HikariCP at the shown version. Provided dependencies come from the container; test dependencies are excluded from the runtime WAR. |
| 14 | Declares dependency flyway-core at the shown version. Provided dependencies come from the container; test dependencies are excluded from the runtime WAR. |
| 15 | Declares dependency flyway-mysql at the shown version. Provided dependencies come from the container; test dependencies are excluded from the runtime WAR. |
| 16 | Declares dependency slf4j-api at the shown version. Provided dependencies come from the container; test dependencies are excluded from the runtime WAR. |
| 17 | Declares dependency slf4j-jdk14 at the shown version. Provided dependencies come from the container; test dependencies are excluded from the runtime WAR. |
| 18 | Declares dependency h2 at the shown version with test scope. Provided dependencies come from the container; test dependencies are excluded from the runtime WAR. |
| 19 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 21 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 22 | Configures the named Maven plugin and lifecycle/entry point. The local profile adds only the development runner and embedded dependencies. |
| 23 | Configures the named Maven plugin and lifecycle/entry point. The local profile adds only the development runner and embedded dependencies. |
| 24 | Configures the named Maven plugin and lifecycle/entry point. The local profile adds only the development runner and embedded dependencies. |
| 25 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 26 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 27 | Declares dependency h2 at the shown version. Provided dependencies come from the container; test dependencies are excluded from the runtime WAR. |
| 28 | Declares dependency tomcat-embed-core at the shown version. Provided dependencies come from the container; test dependencies are excluded from the runtime WAR. |
| 29 | Declares dependency tomcat-embed-jasper at the shown version. Provided dependencies come from the container; test dependencies are excluded from the runtime WAR. |
| 30 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 31 | Configures the named Maven plugin and lifecycle/entry point. The local profile adds only the development runner and embedded dependencies. |
| 32 | Configures the named Maven plugin and lifecycle/entry point. The local profile adds only the development runner and embedded dependencies. |
| 33 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 34 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |

<a id="file-2"></a>

## 2. src/main/resources/db/schema.sql

Initial relational structure, uniqueness and foreign-key rules.

Source SHA-256: `c0a0b29aca0b05cd64d6a3d25d5f42771532cd210e2ecd80ca2a2f2ade5257f2`

<details>
<summary>Numbered source (36 physical lines)</summary>

~~~text
   1 | CREATE TABLE IF NOT EXISTS user_dtls (
   2 |   id INT AUTO_INCREMENT PRIMARY KEY,
   3 |   full_name VARCHAR(120) NOT NULL,
   4 |   email VARCHAR(254) NOT NULL UNIQUE,
   5 |   password VARCHAR(255) NOT NULL
   6 | );
   7 | CREATE TABLE IF NOT EXISTS specialist (
   8 |   id INT AUTO_INCREMENT PRIMARY KEY,
   9 |   spec_name VARCHAR(120) NOT NULL UNIQUE
  10 | );
  11 | CREATE TABLE IF NOT EXISTS doctor (
  12 |   id INT AUTO_INCREMENT PRIMARY KEY,
  13 |   full_name VARCHAR(120) NOT NULL,
  14 |   dob VARCHAR(10) NOT NULL,
  15 |   qualification VARCHAR(160) NOT NULL,
  16 |   specialist VARCHAR(120) NOT NULL,
  17 |   email VARCHAR(254) NOT NULL UNIQUE,
  18 |   mobno VARCHAR(20) NOT NULL,
  19 |   password VARCHAR(255) NOT NULL
  20 | );
  21 | CREATE TABLE IF NOT EXISTS appointment (
  22 |   id INT AUTO_INCREMENT PRIMARY KEY,
  23 |   user_id INT NOT NULL,
  24 |   fullname VARCHAR(120) NOT NULL,
  25 |   gender VARCHAR(30) NOT NULL,
  26 |   age VARCHAR(3) NOT NULL,
  27 |   appoint_date DATE NOT NULL,
  28 |   email VARCHAR(254) NOT NULL,
  29 |   phno VARCHAR(20) NOT NULL,
  30 |   diseases VARCHAR(500) NOT NULL,
  31 |   doctor_id INT NOT NULL,
  32 |   address VARCHAR(500) NOT NULL,
  33 |   status VARCHAR(1000) NOT NULL,
  34 |   FOREIGN KEY (user_id) REFERENCES user_dtls(id),
  35 |   FOREIGN KEY (doctor_id) REFERENCES doctor(id)
  36 | );
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Creates the named table only if absent. This does not migrate an existing incompatible schema. |
| 2 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 3 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 4 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 5 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 7 | Creates the named table only if absent. This does not migrate an existing incompatible schema. |
| 8 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 9 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 11 | Creates the named table only if absent. This does not migrate an existing incompatible schema. |
| 12 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 13 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 14 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 15 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 16 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 17 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 18 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 19 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 21 | Creates the named table only if absent. This does not migrate an existing incompatible schema. |
| 22 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 23 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 24 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 25 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 26 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 27 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 28 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 29 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 30 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 31 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 32 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 33 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 34 | Enforces the shown account/doctor relationship and rejects orphan appointments; referenced parent deletion is restricted by this schema. |
| 35 | Enforces the shown account/doctor relationship and rejects orphan appointments; referenced parent deletion is restricted by this schema. |

<a id="file-3"></a>

## 3. src/main/webapp/WEB-INF/web.xml

Servlet descriptor with session settings and welcome page.

Source SHA-256: `1b4f6d5749649513c2dd63648f461a8240dcf96d0a03e309dc5f1aee1ed3a83b`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | <?xml version="1.0" encoding="UTF-8"?>
   2 | <web-app xmlns="https://jakarta.ee/xml/ns/jakartaee" version="6.0">
   3 |   <display-name>Hospital Management System</display-name>
   4 |   <session-config><session-timeout>30</session-timeout><cookie-config><http-only>true</http-only></cookie-config><tracking-mode>COOKIE</tracking-mode></session-config>
   5 |   <welcome-file-list><welcome-file>index.jsp</welcome-file></welcome-file-list>
   6 | </web-app>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Declares XML encoding/version. |
| 2 | Declares a Jakarta Servlet 6 web application. |
| 3 | Sets the servlet-container display name. |
| 4 | Configures 30-minute inactive sessions, HttpOnly and cookie-only tracking. DatabaseSetup sets SameSite=Lax and the configurable Secure flag. |
| 5 | Uses index.jsp as the application welcome page. |
| 6 | Closes the descriptor. |

<a id="file-4"></a>

## 4. src/main/java/com/admin/servlet/AdminLogin.java

Thin URL adapter delegating to ActionController, which calls the shared authentication service.

Source SHA-256: `5a9e0f6760c7522e4f7dab493cb91bc2af1c309c44e5a36b83df551a7cfca603`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | package com.admin.servlet;
   2 |
   3 | import jakarta.servlet.annotation.WebServlet;
   4 |
   5 | @WebServlet("/adminLogin")
   6 | public final class AdminLogin extends com.web.ActionController {}
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Registers this HTTP servlet at the quoted application-relative URL. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |

<a id="file-5"></a>

## 5. src/main/java/com/admin/servlet/DeleteDoctor.java

Thin admin POST adapter; the service archives doctors while retaining history.

Source SHA-256: `80cff11dc180d198c804a53439e3db2e3046b87301e641c2637c4b3cf297fb57`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | package com.admin.servlet;
   2 |
   3 | import jakarta.servlet.annotation.WebServlet;
   4 |
   5 | @WebServlet("/delete_doctor")
   6 | public final class DeleteDoctor extends com.web.ActionController {}
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Registers this HTTP servlet at the quoted application-relative URL. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |

<a id="file-6"></a>

## 6. src/main/java/com/admin/servlet/UpdateDoctor.java

Admin profile editing with optional replacement password.

Source SHA-256: `bd0a1a66e228484b13617041da8102cf9559f05944bc9a7cabf2b9e645fd7dea`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | package com.admin.servlet;
   2 |
   3 | import jakarta.servlet.annotation.WebServlet;
   4 |
   5 | @WebServlet("/updateDoctor")
   6 | public final class UpdateDoctor extends com.web.ActionController {}
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Registers this HTTP servlet at the quoted application-relative URL. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |

<a id="file-7"></a>

## 7. src/main/java/com/admin/servlet/addDoctor.java

Admin doctor registration with required password validation.

Source SHA-256: `28fb108d3653a2b0f056ccbe6c081044d7fc3f8fc7681a93bea5443438b94291`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | package com.admin.servlet;
   2 |
   3 | import jakarta.servlet.annotation.WebServlet;
   4 |
   5 | @WebServlet("/addDoctor")
   6 | public final class addDoctor extends com.web.ActionController {}
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Registers this HTTP servlet at the quoted application-relative URL. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |

<a id="file-8"></a>

## 8. src/main/java/com/admin/servlet/addSpecialist.java

Admin specialty creation with shared required-field validation.

Source SHA-256: `e79d650e7aab0ddf38cdb5cfa1fed576af37f2488e05344dd6ee5435d6fa3825`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | package com.admin.servlet;
   2 |
   3 | import jakarta.servlet.annotation.WebServlet;
   4 |
   5 | @WebServlet("/addSpecialist")
   6 | public final class addSpecialist extends com.web.ActionController {}
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Registers this HTTP servlet at the quoted application-relative URL. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |

<a id="file-9"></a>

## 9. src/main/java/com/admin/servlet/adminLogout.java

Removes the relevant role marker through inherited GET logout; does not invalidate the entire session.

Source SHA-256: `9e60407d3be1dac4cbb75ef3883034bd10ebf78c98100935eed3e8bbc1718273`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | package com.admin.servlet;
   2 |
   3 | import jakarta.servlet.annotation.WebServlet;
   4 |
   5 | @WebServlet("/adminLogout")
   6 | public final class adminLogout extends com.web.ActionController {}
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Registers this HTTP servlet at the quoted application-relative URL. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |

<a id="file-10"></a>

## 10. src/main/java/com/dao/AppointmentDao.java

Paginated, role-filtered appointment reads and named-column mapping; the service owns mutations and transaction boundaries.

Source SHA-256: `7fd8516359967714389a8e629a953f92618205a4a5f8aa6813d96f18752d965e`

<details>
<summary>Numbered source (107 physical lines)</summary>

~~~text
   1 | package com.dao;
   2 |
   3 | import com.entity.*;
   4 | import java.sql.*;
   5 | import java.util.*;
   6 |
   7 | /** SQL and row mapping only; AppointmentService owns transactions and business rules. */
   8 | public final class AppointmentDao {
   9 |   private final Connection connection;
  10 |
  11 |   public AppointmentDao(Connection connection) {
  12 |     this.connection = connection;
  13 |   }
  14 |
  15 |   private static final String SELECT =
  16 |       """
  17 |       SELECT a.*, d.full_name AS doctor_name, s.starts_at,
  18 |       (SELECT n.body FROM appointment_note n WHERE n.appointment_id=a.id ORDER BY n.id DESC LIMIT 1) AS latest_note
  19 |       FROM appointment a JOIN doctor d ON d.id=a.doctor_id LEFT JOIN doctor_slot s ON s.id=a.slot_id
  20 |       """;
  21 |
  22 |   public List<AppointmentRow> list(String role, int owner, int page) throws SQLException {
  23 |     String where =
  24 |         role.equals("PATIENT")
  25 |             ? " WHERE a.user_id=?"
  26 |             : role.equals("DOCTOR") ? " WHERE a.doctor_id=?" : "";
  27 |     try (PreparedStatement ps =
  28 |         connection.prepareStatement(SELECT + where + " ORDER BY a.id DESC LIMIT 20 OFFSET ?")) {
  29 |       int i = 1;
  30 |       if (!role.equals("ADMIN")) ps.setInt(i++, owner);
  31 |       ps.setInt(i, Math.max(0, page - 1) * 20);
  32 |       try (ResultSet rs = ps.executeQuery()) {
  33 |         List<AppointmentRow> rows = new ArrayList<>();
  34 |         while (rs.next()) rows.add(map(rs));
  35 |         return rows;
  36 |       }
  37 |     }
  38 |   }
  39 |
  40 |   public AppointmentRow find(int id) throws SQLException {
  41 |     try (PreparedStatement ps = connection.prepareStatement(SELECT + " WHERE a.id=?")) {
  42 |       ps.setInt(1, id);
  43 |       try (ResultSet rs = ps.executeQuery()) {
  44 |         return rs.next() ? map(rs) : null;
  45 |       }
  46 |     }
  47 |   }
  48 |
  49 |   public List<Note> notes(int id) throws SQLException {
  50 |     try (PreparedStatement ps =
  51 |         connection.prepareStatement(
  52 |             "SELECT d.full_name,n.body,n.created_at FROM appointment_note n JOIN doctor d ON"
  53 |                 + " d.id=n.doctor_id WHERE n.appointment_id=? ORDER BY n.id DESC")) {
  54 |       ps.setInt(1, id);
  55 |       try (ResultSet rs = ps.executeQuery()) {
  56 |         List<Note> rows = new ArrayList<>();
  57 |         while (rs.next()) rows.add(new Note(rs.getString(1), rs.getString(2), rs.getString(3)));
  58 |         return rows;
  59 |       }
  60 |     }
  61 |   }
  62 |
  63 |   public List<AuditEvent> events(int id) throws SQLException {
  64 |     try (PreparedStatement ps =
  65 |         connection.prepareStatement(
  66 |             "SELECT actor_role,action,from_status,to_status,created_at FROM appointment_event WHERE"
  67 |                 + " appointment_id=? ORDER BY id DESC")) {
  68 |       ps.setInt(1, id);
  69 |       try (ResultSet rs = ps.executeQuery()) {
  70 |         List<AuditEvent> rows = new ArrayList<>();
  71 |         while (rs.next())
  72 |           rows.add(
  73 |               new AuditEvent(
  74 |                   rs.getString(1),
  75 |                   rs.getString(2),
  76 |                   rs.getString(3),
  77 |                   rs.getString(4),
  78 |                   rs.getString(5)));
  79 |         return rows;
  80 |       }
  81 |     }
  82 |   }
  83 |
  84 |   private AppointmentRow map(ResultSet rs) throws SQLException {
  85 |     Long slot = (Long) rs.getObject("slot_id", Long.class);
  86 |     Timestamp starts = rs.getTimestamp("starts_at");
  87 |     return new AppointmentRow(
  88 |         rs.getInt("id"),
  89 |         rs.getInt("user_id"),
  90 |         rs.getInt("doctor_id"),
  91 |         slot,
  92 |         rs.getString("fullname"),
  93 |         rs.getString("gender"),
  94 |         rs.getString("age"),
  95 |         rs.getString("appoint_date"),
  96 |         rs.getString("email"),
  97 |         rs.getString("phno"),
  98 |         rs.getString("diseases"),
  99 |         rs.getString("address"),
 100 |         rs.getString("status"),
 101 |         rs.getString("doctor_name"),
 102 |         starts == null
 103 |             ? "Unscheduled legacy request"
 104 |             : starts.toLocalDateTime().toString().replace('T', ' '),
 105 |         rs.getString("latest_note"));
 106 |   }
 107 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Source comment/documentation; TODO statements describe unfinished work. |
| 8 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 9 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 11 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 12 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 15 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 16 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 17 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 18 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 19 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 20 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 22 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 23 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 24 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 25 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 26 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 27 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 28 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 29 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 30 | Counts one row in Java. A COUNT(*) query would avoid transferring every row. Branches on the shown condition; be able to explain both the success and rejection path. |
| 31 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 32 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 33 | Allocates the initially empty collection of mapped objects. |
| 34 | Advances the cursor and checks whether a row exists. |
| 35 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 40 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 41 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 42 | Binds parameter 1 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 43 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 44 | Advances the cursor and checks whether a row exists. Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 49 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 50 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 51 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 52 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 53 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 54 | Binds parameter 1 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 55 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 56 | Allocates the initially empty collection of mapped objects. |
| 57 | Advances the cursor and checks whether a row exists. Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 58 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 63 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 64 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 65 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 66 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 67 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 68 | Binds parameter 1 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 69 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 70 | Allocates the initially empty collection of mapped objects. |
| 71 | Advances the cursor and checks whether a row exists. |
| 72 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 73 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 74 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 75 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 76 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 77 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 78 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 79 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 84 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 85 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 86 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 87 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 88 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 89 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 90 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 91 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 92 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 93 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 94 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 95 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 96 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 97 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 98 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 99 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 100 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 101 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 102 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 103 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 104 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 105 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |

<a id="file-11"></a>

## 11. src/main/java/com/dao/DoctorDao.java

Doctor SQL, credentials, profiles, CRUD and inherited dashboard counting.

Source SHA-256: `b9eef759bd7c207037fd659e06292266386bde1b1a750aa9d710297ab185c62d`

<details>
<summary>Numbered source (156 physical lines)</summary>

~~~text
   1 | package com.dao;
   2 |
   3 | import com.entity.Doctor;
   4 | import com.security.Passwords;
   5 | import java.sql.*;
   6 | import java.util.*;
   7 |
   8 | public final class DoctorDao {
   9 |   private final Connection c;
  10 |   private static final String COLUMNS =
  11 |       "id,full_name,dob,qualification,specialist,email,mobno,active,auth_version";
  12 |
  13 |   public DoctorDao(Connection c) {
  14 |     this.c = c;
  15 |   }
  16 |
  17 |   private Doctor map(ResultSet rs) throws SQLException {
  18 |     Doctor d = new Doctor();
  19 |     d.setId(rs.getInt("id"));
  20 |     d.setFullName(rs.getString("full_name"));
  21 |     d.setDob(rs.getString("dob"));
  22 |     d.setQualification(rs.getString("qualification"));
  23 |     d.setSpecialist(rs.getString("specialist"));
  24 |     d.setEmail(rs.getString("email"));
  25 |     d.setMobNo(rs.getString("mobno"));
  26 |     d.setActive(rs.getBoolean("active"));
  27 |     d.setAuthVersion(rs.getInt("auth_version"));
  28 |     return d;
  29 |   }
  30 |
  31 |   public List<Doctor> list(boolean archived) throws SQLException {
  32 |     try (PreparedStatement ps =
  33 |             c.prepareStatement(
  34 |                 "SELECT "
  35 |                     + COLUMNS
  36 |                     + " FROM doctor"
  37 |                     + (archived ? "" : " WHERE active=TRUE")
  38 |                     + " ORDER BY full_name");
  39 |         ResultSet rs = ps.executeQuery()) {
  40 |       List<Doctor> result = new ArrayList<>();
  41 |       while (rs.next()) result.add(map(rs));
  42 |       return result;
  43 |     }
  44 |   }
  45 |
  46 |   public Doctor find(int id) throws SQLException {
  47 |     try (PreparedStatement ps =
  48 |         c.prepareStatement("SELECT " + COLUMNS + " FROM doctor WHERE id=?")) {
  49 |       ps.setInt(1, id);
  50 |       try (ResultSet rs = ps.executeQuery()) {
  51 |         return rs.next() ? map(rs) : null;
  52 |       }
  53 |     }
  54 |   }
  55 |
  56 |   public Doctor login(String email, String password) throws SQLException {
  57 |     try (PreparedStatement ps =
  58 |         c.prepareStatement("SELECT id,password FROM doctor WHERE email=? AND active=TRUE")) {
  59 |       ps.setString(1, email);
  60 |       try (ResultSet rs = ps.executeQuery()) {
  61 |         if (!rs.next() || !Passwords.verify(password, rs.getString("password"))) return null;
  62 |         int id = rs.getInt("id");
  63 |         String previous = rs.getString("password");
  64 |         if (Passwords.needsRehash(previous))
  65 |           try (PreparedStatement update =
  66 |               c.prepareStatement("UPDATE doctor SET password=? WHERE id=? AND password=?")) {
  67 |             update.setString(1, Passwords.hash(password));
  68 |             update.setInt(2, id);
  69 |             update.setString(3, previous);
  70 |             update.executeUpdate();
  71 |           }
  72 |         return find(id);
  73 |       }
  74 |     }
  75 |   }
  76 |
  77 |   public boolean save(Doctor d, boolean create, boolean profileOnly) throws SQLException {
  78 |     String sql =
  79 |         create
  80 |             ? "INSERT INTO doctor(full_name,dob,qualification,specialist,email,mobno,password)"
  81 |                   + " VALUES(?,?,?,?,?,?,?)"
  82 |             : "UPDATE doctor SET"
  83 |                   + " full_name=?,dob=?,qualification=?,specialist=?,email=?,mobno=?,password=COALESCE(?,password),auth_version=auth_version+?"
  84 |                   + " WHERE id=? AND active=TRUE";
  85 |     try (PreparedStatement ps = c.prepareStatement(sql)) {
  86 |       ps.setString(1, d.getFullName());
  87 |       ps.setString(2, d.getDob());
  88 |       ps.setString(3, d.getQualification());
  89 |       ps.setString(4, d.getSpecialist());
  90 |       ps.setString(5, d.getEmail());
  91 |       ps.setString(6, d.getMobNo());
  92 |       String replacement =
  93 |           profileOnly || d.getPassword() == null || d.getPassword().isBlank()
  94 |               ? null
  95 |               : Passwords.hash(d.getPassword());
  96 |       ps.setString(7, replacement);
  97 |       if (!create) {
  98 |         ps.setInt(8, replacement == null ? 0 : 1);
  99 |         ps.setInt(9, d.getId());
 100 |       }
 101 |       return ps.executeUpdate() == 1;
 102 |     }
 103 |   }
 104 |
 105 |   public boolean changePassword(int id, String oldPassword, String replacement)
 106 |       throws SQLException {
 107 |     String previous;
 108 |     try (PreparedStatement ps =
 109 |         c.prepareStatement("SELECT password FROM doctor WHERE id=? AND active=TRUE")) {
 110 |       ps.setInt(1, id);
 111 |       try (ResultSet rs = ps.executeQuery()) {
 112 |         if (!rs.next() || !Passwords.verify(oldPassword, rs.getString(1))) return false;
 113 |         previous = rs.getString(1);
 114 |       }
 115 |     }
 116 |     try (PreparedStatement ps =
 117 |         c.prepareStatement(
 118 |             "UPDATE doctor SET password=?,auth_version=auth_version+1 WHERE id=? AND password=? AND"
 119 |                 + " active=TRUE")) {
 120 |       ps.setString(1, Passwords.hash(replacement));
 121 |       ps.setInt(2, id);
 122 |       ps.setString(3, previous);
 123 |       return ps.executeUpdate() == 1;
 124 |     }
 125 |   }
 126 |
 127 |   public Map<String, Long> counts(Integer doctorId) throws SQLException {
 128 |     Map<String, Long> values = new HashMap<>();
 129 |     if (doctorId == null) {
 130 |       for (String table : List.of("doctor", "user_dtls", "appointment", "specialist")) {
 131 |         String sql =
 132 |             "SELECT COUNT(*) FROM " + table + (table.equals("doctor") ? " WHERE active=TRUE" : "");
 133 |         try (PreparedStatement ps = c.prepareStatement(sql);
 134 |             ResultSet rs = ps.executeQuery()) {
 135 |           rs.next();
 136 |           values.put(table, rs.getLong(1));
 137 |         }
 138 |       }
 139 |     } else {
 140 |       try (PreparedStatement ps =
 141 |           c.prepareStatement(
 142 |               "SELECT status,COUNT(*) FROM appointment WHERE doctor_id=? GROUP BY status")) {
 143 |         ps.setInt(1, doctorId);
 144 |         try (ResultSet rs = ps.executeQuery()) {
 145 |           long total = 0;
 146 |           while (rs.next()) {
 147 |             values.put(rs.getString(1), rs.getLong(2));
 148 |             total += rs.getLong(2);
 149 |           }
 150 |           values.put("total", total);
 151 |         }
 152 |       }
 153 |     }
 154 |     return values;
 155 |   }
 156 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 8 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 9 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 10 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 11 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 13 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 14 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 17 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 18 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 19 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Maps data into a plain Java object; the setter itself does not write SQL. |
| 20 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Maps data into a plain Java object; the setter itself does not write SQL. |
| 21 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Maps data into a plain Java object; the setter itself does not write SQL. |
| 22 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Maps data into a plain Java object; the setter itself does not write SQL. |
| 23 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Maps data into a plain Java object; the setter itself does not write SQL. |
| 24 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Maps data into a plain Java object; the setter itself does not write SQL. |
| 25 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Maps data into a plain Java object; the setter itself does not write SQL. |
| 26 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 27 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 28 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 31 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 32 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 33 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 34 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 35 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 36 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 37 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 38 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 39 | Executes SELECT and obtains a ResultSet cursor. |
| 40 | Allocates the initially empty collection of mapped objects. |
| 41 | Advances the cursor and checks whether a row exists. |
| 42 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 46 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 47 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 48 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 49 | Binds parameter 1 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 50 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 51 | Advances the cursor and checks whether a row exists. Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 56 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 57 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 58 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 59 | Binds parameter 1 (JDBC indexes start at 1) to email. Match the SQL placeholder order. |
| 60 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 61 | Advances the cursor and checks whether a row exists. Reads the current database row. Numeric column indexes depend on SELECT/schema order. Verifies using the salt and work factor embedded in the stored representation. Branches on the shown condition; be able to explain both the success and rejection path. |
| 62 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 63 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 64 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 65 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 66 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 67 | Derives a freshly salted password representation before storage. |
| 68 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 69 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 70 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 72 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 77 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 78 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 79 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 80 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 81 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 82 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 83 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 84 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 85 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Creates a prepared SQL statement without executing it yet. |
| 86 | Binds parameter 1 (JDBC indexes start at 1) to d.getFullName(). Match the SQL placeholder order. |
| 87 | Binds parameter 2 (JDBC indexes start at 1) to d.getDob(). Match the SQL placeholder order. |
| 88 | Binds parameter 3 (JDBC indexes start at 1) to d.getQualification(). Match the SQL placeholder order. |
| 89 | Binds parameter 4 (JDBC indexes start at 1) to d.getSpecialist(). Match the SQL placeholder order. |
| 90 | Binds parameter 5 (JDBC indexes start at 1) to d.getEmail(). Match the SQL placeholder order. |
| 91 | Binds parameter 6 (JDBC indexes start at 1) to d.getMobNo(). Match the SQL placeholder order. |
| 92 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 93 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 94 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 95 | Derives a freshly salted password representation before storage. |
| 96 | Binds parameter 7 (JDBC indexes start at 1) to replacement. Match the SQL placeholder order. |
| 97 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 98 | Binds parameter 8 (JDBC indexes start at 1) to replacement == null ? 0 : 1. Match the SQL placeholder order. |
| 99 | Binds parameter 9 (JDBC indexes start at 1) to d.getId(). Match the SQL placeholder order. |
| 101 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 105 | Declares changePassword: Store a newly salted password hash for the account ID supplied by the trusted servlet. |
| 106 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 107 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 108 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 109 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 110 | Binds parameter 1 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 111 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 112 | Advances the cursor and checks whether a row exists. Reads the current database row. Numeric column indexes depend on SELECT/schema order. Verifies using the salt and work factor embedded in the stored representation. Branches on the shown condition; be able to explain both the success and rejection path. |
| 113 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 116 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 117 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 118 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 119 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 120 | Binds parameter 1 (JDBC indexes start at 1) to Passwords.hash(replacement). Match the SQL placeholder order. Derives a freshly salted password representation before storage. |
| 121 | Binds parameter 2 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 122 | Binds parameter 3 (JDBC indexes start at 1) to previous. Match the SQL placeholder order. |
| 123 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 127 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 128 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 129 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 130 | Iterates the listed values/collection and executes the enclosed operations for each item. |
| 131 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 132 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 133 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Creates a prepared SQL statement without executing it yet. |
| 134 | Executes SELECT and obtains a ResultSet cursor. |
| 135 | Advances the cursor and checks whether a row exists. |
| 136 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 139 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 140 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 141 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 142 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 143 | Binds parameter 1 (JDBC indexes start at 1) to doctorId. Match the SQL placeholder order. |
| 144 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 145 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 146 | Advances the cursor and checks whether a row exists. |
| 147 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 148 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 150 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 154 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |

<a id="file-12"></a>

## 12. src/main/java/com/dao/SpecialistDao.java

Specialty insertion and listing.

Source SHA-256: `faabd41079ec1a4e178e10f0c083f3814d0dc114f4fdd72627c0b04c2e6e3de6`

<details>
<summary>Numbered source (30 physical lines)</summary>

~~~text
   1 | package com.dao;
   2 |
   3 | import com.entity.Specalist;
   4 | import java.sql.*;
   5 | import java.util.*;
   6 |
   7 | public final class SpecialistDao {
   8 |   private final Connection c;
   9 |
  10 |   public SpecialistDao(Connection c) {
  11 |     this.c = c;
  12 |   }
  13 |
  14 |   public List<Specalist> list() throws SQLException {
  15 |     try (PreparedStatement ps =
  16 |             c.prepareStatement("SELECT id,spec_name FROM specialist ORDER BY spec_name");
  17 |         ResultSet rs = ps.executeQuery()) {
  18 |       List<Specalist> result = new ArrayList<>();
  19 |       while (rs.next()) result.add(new Specalist(rs.getInt(1), rs.getString(2)));
  20 |       return result;
  21 |     }
  22 |   }
  23 |
  24 |   public void add(String name) throws SQLException {
  25 |     try (PreparedStatement ps = c.prepareStatement("INSERT INTO specialist(spec_name) VALUES(?)")) {
  26 |       ps.setString(1, name);
  27 |       ps.executeUpdate();
  28 |     }
  29 |   }
  30 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 8 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 10 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 11 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 14 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 15 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 16 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 17 | Executes SELECT and obtains a ResultSet cursor. |
| 18 | Allocates the initially empty collection of mapped objects. |
| 19 | Advances the cursor and checks whether a row exists. Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 20 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 24 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 25 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 26 | Binds parameter 1 (JDBC indexes start at 1) to name. Match the SQL placeholder order. |
| 27 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |

<a id="file-13"></a>

## 13. src/main/java/com/dao/UserDao.java

Patient account SQL, conditional rehash-on-login and versioned password changes with automatic resource cleanup.

Source SHA-256: `c363c341b44b3c37f3db7634dfddc2811f22e8a3fd1f186eefea964381e1c404`

<details>
<summary>Numbered source (76 physical lines)</summary>

~~~text
   1 | package com.dao;
   2 |
   3 | import com.entity.User;
   4 | import com.security.Passwords;
   5 | import java.sql.*;
   6 |
   7 | public final class UserDao {
   8 |   private final Connection c;
   9 |
  10 |   public UserDao(Connection c) {
  11 |     this.c = c;
  12 |   }
  13 |
  14 |   public int register(User user) throws SQLException {
  15 |     try (PreparedStatement ps =
  16 |         c.prepareStatement(
  17 |             "INSERT INTO user_dtls(full_name,email,password) VALUES(?,?,?)",
  18 |             Statement.RETURN_GENERATED_KEYS)) {
  19 |       ps.setString(1, user.getFullName());
  20 |       ps.setString(2, user.getEmail());
  21 |       ps.setString(3, Passwords.hash(user.getPassword()));
  22 |       ps.executeUpdate();
  23 |       try (ResultSet rs = ps.getGeneratedKeys()) {
  24 |         rs.next();
  25 |         return rs.getInt(1);
  26 |       }
  27 |     }
  28 |   }
  29 |
  30 |   public User login(String email, String password) throws SQLException {
  31 |     try (PreparedStatement ps =
  32 |         c.prepareStatement(
  33 |             "SELECT id,full_name,email,password,auth_version FROM user_dtls WHERE email=?")) {
  34 |       ps.setString(1, email);
  35 |       try (ResultSet rs = ps.executeQuery()) {
  36 |         if (!rs.next() || !Passwords.verify(password, rs.getString("password"))) return null;
  37 |         User user = new User();
  38 |         user.setId(rs.getInt("id"));
  39 |         user.setFullName(rs.getString("full_name"));
  40 |         user.setEmail(rs.getString("email"));
  41 |         user.setAuthVersion(rs.getInt("auth_version"));
  42 |         String previous = rs.getString("password");
  43 |         if (Passwords.needsRehash(previous))
  44 |           try (PreparedStatement update =
  45 |               c.prepareStatement("UPDATE user_dtls SET password=? WHERE id=? AND password=?")) {
  46 |             update.setString(1, Passwords.hash(password));
  47 |             update.setInt(2, user.getId());
  48 |             update.setString(3, previous);
  49 |             update.executeUpdate();
  50 |           }
  51 |         return user;
  52 |       }
  53 |     }
  54 |   }
  55 |
  56 |   public boolean changePassword(int id, String oldPassword, String replacement)
  57 |       throws SQLException {
  58 |     String previous;
  59 |     try (PreparedStatement ps = c.prepareStatement("SELECT password FROM user_dtls WHERE id=?")) {
  60 |       ps.setInt(1, id);
  61 |       try (ResultSet rs = ps.executeQuery()) {
  62 |         if (!rs.next() || !Passwords.verify(oldPassword, rs.getString(1))) return false;
  63 |         previous = rs.getString(1);
  64 |       }
  65 |     }
  66 |     try (PreparedStatement ps =
  67 |         c.prepareStatement(
  68 |             "UPDATE user_dtls SET password=?,auth_version=auth_version+1 WHERE id=? AND"
  69 |                 + " password=?")) {
  70 |       ps.setString(1, Passwords.hash(replacement));
  71 |       ps.setInt(2, id);
  72 |       ps.setString(3, previous);
  73 |       return ps.executeUpdate() == 1;
  74 |     }
  75 |   }
  76 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 8 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 10 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 11 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 14 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 15 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 16 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 17 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 18 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 19 | Binds parameter 1 (JDBC indexes start at 1) to user.getFullName(). Match the SQL placeholder order. |
| 20 | Binds parameter 2 (JDBC indexes start at 1) to user.getEmail(). Match the SQL placeholder order. |
| 21 | Binds parameter 3 (JDBC indexes start at 1) to Passwords.hash(user.getPassword()). Match the SQL placeholder order. Derives a freshly salted password representation before storage. |
| 22 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 23 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 24 | Advances the cursor and checks whether a row exists. |
| 25 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 30 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 31 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 32 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 33 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 34 | Binds parameter 1 (JDBC indexes start at 1) to email. Match the SQL placeholder order. |
| 35 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 36 | Advances the cursor and checks whether a row exists. Reads the current database row. Numeric column indexes depend on SELECT/schema order. Verifies using the salt and work factor embedded in the stored representation. Branches on the shown condition; be able to explain both the success and rejection path. |
| 37 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 38 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Maps data into a plain Java object; the setter itself does not write SQL. |
| 39 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Maps data into a plain Java object; the setter itself does not write SQL. |
| 40 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Maps data into a plain Java object; the setter itself does not write SQL. |
| 41 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 42 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 43 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 44 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 45 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 46 | Derives a freshly salted password representation before storage. |
| 47 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 48 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 49 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 51 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 56 | Declares changePassword: Store a newly salted password hash for the account ID supplied by the trusted servlet. |
| 57 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 58 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 59 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 60 | Binds parameter 1 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 61 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 62 | Advances the cursor and checks whether a row exists. Reads the current database row. Numeric column indexes depend on SELECT/schema order. Verifies using the salt and work factor embedded in the stored representation. Branches on the shown condition; be able to explain both the success and rejection path. |
| 63 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 66 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 67 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 68 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 69 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 70 | Binds parameter 1 (JDBC indexes start at 1) to Passwords.hash(replacement). Match the SQL placeholder order. Derives a freshly salted password representation before storage. |
| 71 | Binds parameter 2 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 72 | Binds parameter 3 (JDBC indexes start at 1) to previous. Match the SQL placeholder order. |
| 73 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |

<a id="file-14"></a>

## 14. src/main/java/com/db/DBConnect.java

JVM/environment configuration and an application-wide HikariCP connection pool; services close each borrowed connection.

Source SHA-256: `2fca1c55852a320b064e2d7be44a92d59fd5a2abe686a4b48b7a17e13cc5e6ec`

<details>
<summary>Numbered source (47 physical lines)</summary>

~~~text
   1 | package com.db;
   2 |
   3 | import com.zaxxer.hikari.HikariConfig;
   4 | import com.zaxxer.hikari.HikariDataSource;
   5 | import java.sql.Connection;
   6 | import java.sql.SQLException;
   7 |
   8 | /** Application-wide connection pool; callers own and close each borrowed connection. */
   9 | public final class DBConnect {
  10 |   private static volatile HikariDataSource pool;
  11 |
  12 |   private DBConnect() {}
  13 |
  14 |   public static String config(String name, String fallback) {
  15 |     String value = System.getProperty(name);
  16 |     if (value == null) value = System.getenv(name);
  17 |     return value == null ? fallback : value;
  18 |   }
  19 |
  20 |   public static synchronized HikariDataSource dataSource() {
  21 |     if (pool == null || pool.isClosed()) {
  22 |       HikariConfig config = new HikariConfig();
  23 |       config.setJdbcUrl(config("DB_URL", "jdbc:mysql://localhost:3306/hospital_1"));
  24 |       config.setUsername(config("DB_USER", "hospital"));
  25 |       config.setPassword(config("DB_PASSWORD", ""));
  26 |       config.setMaximumPoolSize(Integer.parseInt(config("DB_POOL_SIZE", "10")));
  27 |       config.setMinimumIdle(1);
  28 |       config.setConnectionTimeout(5000);
  29 |       config.setValidationTimeout(2000);
  30 |       config.setPoolName("hospital");
  31 |       config.setAutoCommit(true);
  32 |       pool = new HikariDataSource(config);
  33 |     }
  34 |     return pool;
  35 |   }
  36 |
  37 |   public static Connection open() throws SQLException {
  38 |     return dataSource().getConnection();
  39 |   }
  40 |
  41 |   public static synchronized void shutdown() {
  42 |     if (pool != null) {
  43 |       pool.close();
  44 |       pool = null;
  45 |     }
  46 |   }
  47 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 8 | Source comment/documentation; TODO statements describe unfinished work. |
| 9 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 10 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 12 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 14 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 15 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 16 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 17 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 20 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 21 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 22 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 23 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 24 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 25 | Maps data into a plain Java object; the setter itself does not write SQL. |
| 26 | Parses an integer and may raise NumberFormatException, a subtype of IllegalArgumentException. |
| 27 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 28 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 29 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 30 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 31 | Restores auto-commit after the grouped transaction. |
| 32 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 34 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 37 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 38 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 41 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 42 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 43 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 44 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |

<a id="file-15"></a>

## 15. src/main/java/com/db/DatabaseSetup.java

Flyway versioned migrations, session cookie configuration, explicit demo seeding and pool shutdown.

Source SHA-256: `fb3764f3be02aef264f86c96a75adab1bcb9f57aafdb91847ca5f0f45b1deb56`

<details>
<summary>Numbered source (119 physical lines)</summary>

~~~text
   1 | package com.db;
   2 |
   3 | import com.security.Passwords;
   4 | import com.service.HospitalService;
   5 | import jakarta.servlet.*;
   6 | import jakarta.servlet.annotation.WebListener;
   7 | import java.sql.*;
   8 | import java.time.*;
   9 | import org.flywaydb.core.Flyway;
  10 |
  11 | /** Versioned, checksum-validated migrations; fictional seed data is explicitly opt-in. */
  12 | @WebListener
  13 | public final class DatabaseSetup implements ServletContextListener {
  14 |   @Override
  15 |   public void contextInitialized(ServletContextEvent event) {
  16 |     try {
  17 |       event.getServletContext().getSessionCookieConfig().setAttribute("SameSite", "Lax");
  18 |       event
  19 |           .getServletContext()
  20 |           .getSessionCookieConfig()
  21 |           .setSecure(Boolean.parseBoolean(DBConnect.config("SECURE_COOKIES", "false")));
  22 |       if (Boolean.parseBoolean(
  23 |           DBConnect.config("DB_MIGRATE", DBConnect.config("DB_INIT", "true")))) {
  24 |         Flyway.configure()
  25 |             .dataSource(DBConnect.dataSource())
  26 |             .baselineOnMigrate(
  27 |                 Boolean.parseBoolean(
  28 |                     DBConnect.config("DB_BASELINE_LEGACY", DBConnect.config("DEMO_MODE", "false"))))
  29 |             .baselineVersion("0")
  30 |             .locations("classpath:db/migration")
  31 |             .cleanDisabled(true)
  32 |             .load()
  33 |             .migrate();
  34 |       }
  35 |       if (Boolean.parseBoolean(DBConnect.config("DEMO_MODE", "false"))) seedDemo();
  36 |     } catch (Exception e) {
  37 |       DBConnect.shutdown();
  38 |       throw new IllegalStateException(
  39 |           "Hospital database startup failed; inspect migration configuration.", e);
  40 |     }
  41 |   }
  42 |
  43 |   @Override
  44 |   public void contextDestroyed(ServletContextEvent event) {
  45 |     DBConnect.shutdown();
  46 |   }
  47 |
  48 |   private void seedDemo() throws SQLException {
  49 |     try (Connection c = DBConnect.open()) {
  50 |       c.setAutoCommit(false);
  51 |       try {
  52 |         for (String specialty :
  53 |             new String[] {"General Medicine", "Cardiology", "Pediatrics", "Dermatology"}) {
  54 |           try (PreparedStatement ps =
  55 |               c.prepareStatement(
  56 |                   "INSERT INTO specialist(spec_name) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM"
  57 |                       + " specialist WHERE spec_name=?)")) {
  58 |             ps.setString(1, specialty);
  59 |             ps.setString(2, specialty);
  60 |             ps.executeUpdate();
  61 |           }
  62 |         }
  63 |         String[][] doctors = {
  64 |           {"Dr. Aditi Shah", "General Medicine", "aditi@hospital.test"},
  65 |           {"Dr. Arjun Mehta", "Cardiology", "arjun@hospital.test"},
  66 |           {"Dr. Neha Rao", "Pediatrics", "neha@hospital.test"},
  67 |           {"Dr. Rohan Patel", "Dermatology", "rohan@hospital.test"}
  68 |         };
  69 |         for (String[] d : doctors) {
  70 |           boolean exists;
  71 |           try (PreparedStatement ps = c.prepareStatement("SELECT id FROM doctor WHERE email=?")) {
  72 |             ps.setString(1, d[2]);
  73 |             try (ResultSet rs = ps.executeQuery()) {
  74 |               exists = rs.next();
  75 |             }
  76 |           }
  77 |           if (!exists)
  78 |             try (PreparedStatement ps =
  79 |                 c.prepareStatement(
  80 |                     "INSERT INTO"
  81 |                         + " doctor(full_name,dob,qualification,specialist,email,mobno,password)"
  82 |                         + " VALUES(?,?,?,?,?,?,?)")) {
  83 |               ps.setString(1, d[0]);
  84 |               ps.setString(2, "1985-06-15");
  85 |               ps.setString(3, "MBBS, MD");
  86 |               ps.setString(4, d[1]);
  87 |               ps.setString(5, d[2]);
  88 |               ps.setString(6, "9000000000");
  89 |               ps.setString(7, Passwords.hash("Doctor@123"));
  90 |               ps.executeUpdate();
  91 |             }
  92 |         }
  93 |         try (PreparedStatement ps = c.prepareStatement("SELECT id FROM doctor WHERE active=TRUE");
  94 |             ResultSet rs = ps.executeQuery()) {
  95 |           while (rs.next())
  96 |             for (int day = 1; day <= 14; day++)
  97 |               for (int hour : new int[] {9, 10, 11, 14, 15}) {
  98 |                 LocalDateTime start =
  99 |                     LocalDate.now(HospitalService.zone()).plusDays(day).atTime(hour, 0);
 100 |                 try (PreparedStatement insert =
 101 |                     c.prepareStatement(
 102 |                         "INSERT INTO doctor_slot(doctor_id,starts_at) SELECT ?,? WHERE NOT EXISTS"
 103 |                             + " (SELECT 1 FROM doctor_slot WHERE doctor_id=? AND starts_at=?)")) {
 104 |                   insert.setInt(1, rs.getInt(1));
 105 |                   insert.setTimestamp(2, Timestamp.valueOf(start));
 106 |                   insert.setInt(3, rs.getInt(1));
 107 |                   insert.setTimestamp(4, Timestamp.valueOf(start));
 108 |                   insert.executeUpdate();
 109 |                 }
 110 |               }
 111 |         }
 112 |         c.commit();
 113 |       } catch (SQLException | RuntimeException e) {
 114 |         c.rollback();
 115 |         throw e;
 116 |       }
 117 |     }
 118 |   }
 119 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 8 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 9 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 11 | Source comment/documentation; TODO statements describe unfinished work. |
| 12 | Registers a web-application startup listener. |
| 13 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 14 | Checks at compile time that the following method overrides a parent/interface contract. |
| 15 | Declares contextInitialized: Run optional schema/demo setup when the web application starts, not on each browser request. |
| 16 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 17 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 18 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 19 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 20 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 21 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 22 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 23 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 24 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 25 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 26 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 27 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 28 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 29 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 30 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 31 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 32 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 33 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 35 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 36 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 37 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 38 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 39 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 43 | Checks at compile time that the following method overrides a parent/interface contract. |
| 44 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 45 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 48 | Declares seedDemo: Insert a fictional dataset in a JDBC transaction only when no doctor rows exist. |
| 49 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 50 | Begins manual control of a transaction containing multiple writes. |
| 51 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 52 | Iterates the listed values/collection and executes the enclosed operations for each item. |
| 53 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 54 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 55 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 56 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 57 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 58 | Binds parameter 1 (JDBC indexes start at 1) to specialty. Match the SQL placeholder order. |
| 59 | Binds parameter 2 (JDBC indexes start at 1) to specialty. Match the SQL placeholder order. |
| 60 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 63 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 64 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 65 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 66 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 67 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 69 | Iterates the listed values/collection and executes the enclosed operations for each item. |
| 70 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 71 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 72 | Binds parameter 1 (JDBC indexes start at 1) to d[2]. Match the SQL placeholder order. |
| 73 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 74 | Advances the cursor and checks whether a row exists. |
| 77 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 78 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 79 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 80 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 81 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 82 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 83 | Binds parameter 1 (JDBC indexes start at 1) to d[0]. Match the SQL placeholder order. |
| 84 | Binds parameter 2 (JDBC indexes start at 1) to "1985-06-15". Match the SQL placeholder order. |
| 85 | Binds parameter 3 (JDBC indexes start at 1) to "MBBS, MD". Match the SQL placeholder order. |
| 86 | Binds parameter 4 (JDBC indexes start at 1) to d[1]. Match the SQL placeholder order. |
| 87 | Binds parameter 5 (JDBC indexes start at 1) to d[2]. Match the SQL placeholder order. |
| 88 | Binds parameter 6 (JDBC indexes start at 1) to "9000000000". Match the SQL placeholder order. |
| 89 | Binds parameter 7 (JDBC indexes start at 1) to Passwords.hash("Doctor@123"). Match the SQL placeholder order. Derives a freshly salted password representation before storage. |
| 90 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 93 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 94 | Executes SELECT and obtains a ResultSet cursor. |
| 95 | Advances the cursor and checks whether a row exists. |
| 96 | Iterates the listed values/collection and executes the enclosed operations for each item. |
| 97 | Iterates the listed values/collection and executes the enclosed operations for each item. |
| 98 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 99 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 100 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 101 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 102 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 103 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 104 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 105 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 106 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 107 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 108 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 112 | Commits the grouped writes. |
| 113 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 114 | Rolls back the grouped writes on failure. |
| 115 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |

<a id="file-16"></a>

## 16. src/main/java/com/doctor/servlet/DoctorLogin.java

Doctor authentication, session rotation and establishment of docObj.

Source SHA-256: `5aad33285f8136fe178b861075af1b0c2718fdf293971afa006a693e45e6e852`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | package com.doctor.servlet;
   2 |
   3 | import jakarta.servlet.annotation.WebServlet;
   4 |
   5 | @WebServlet("/loginDoctor")
   6 | public final class DoctorLogin extends com.web.ActionController {}
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Registers this HTTP servlet at the quoted application-relative URL. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |

<a id="file-17"></a>

## 17. src/main/java/com/doctor/servlet/DoctorLogout.java

Removes the relevant role marker through inherited GET logout; does not invalidate the entire session.

Source SHA-256: `34ec86c71c3a2b7f78be83f61b5dedf5338fee07033fd615ef27506d569ccd90`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | package com.doctor.servlet;
   2 |
   3 | import jakarta.servlet.annotation.WebServlet;
   4 |
   5 | @WebServlet("/docLogout")
   6 | public final class DoctorLogout extends com.web.ActionController {}
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Registers this HTTP servlet at the quoted application-relative URL. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |

<a id="file-18"></a>

## 18. src/main/java/com/doctor/servlet/EditProfileDoctor.java

Updates the signed-in doctor, ignoring a forged submitted identity.

Source SHA-256: `01373d83643dde36817f9b38fdc0262e6c85ab2989c6843ab0af9de08650c1d9`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | package com.doctor.servlet;
   2 |
   3 | import jakarta.servlet.annotation.WebServlet;
   4 |
   5 | @WebServlet("/doctorUpdateProfile")
   6 | public final class EditProfileDoctor extends com.web.ActionController {}
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Registers this HTTP servlet at the quoted application-relative URL. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |

<a id="file-19"></a>

## 19. src/main/java/com/doctor/servlet/PosswordChange.java

Doctor password change with session identity and current-password verification.

Source SHA-256: `7584f99164e9bf65b8ddf3ec9d23132634d7b9bf6f611120fa0482e3e918a071`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | package com.doctor.servlet;
   2 |
   3 | import jakarta.servlet.annotation.WebServlet;
   4 |
   5 | @WebServlet("/docChangePassword")
   6 | public final class PosswordChange extends com.web.ActionController {}
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Registers this HTTP servlet at the quoted application-relative URL. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |

<a id="file-20"></a>

## 20. src/main/java/com/doctor/servlet/UpdateStatus.java

Thin URL adapter; ActionController delegates note appending to the transactional service.

Source SHA-256: `e6747fbc0d0202bd569134d4d6628816841866f7b46afca6784dce732c9a54eb`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | package com.doctor.servlet;
   2 |
   3 | import jakarta.servlet.annotation.WebServlet;
   4 |
   5 | @WebServlet("/updateStatus")
   6 | public final class UpdateStatus extends com.web.ActionController {}
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Registers this HTTP servlet at the quoted application-relative URL. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |

<a id="file-21"></a>

## 21. src/main/java/com/entity/AppointmentRow.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `d2e1ea79fb5d24f6ba439ae1d723d8a5ffa5265d37cfb258b476dc446b31298e`

<details>
<summary>Numbered source (87 physical lines)</summary>

~~~text
   1 | package com.entity;
   2 |
   3 | public record AppointmentRow(
   4 |     int id,
   5 |     int userId,
   6 |     int doctorId,
   7 |     Long slotId,
   8 |     String fullName,
   9 |     String gender,
  10 |     String age,
  11 |     String date,
  12 |     String email,
  13 |     String phone,
  14 |     String reason,
  15 |     String address,
  16 |     String status,
  17 |     String doctorName,
  18 |     String slotTime,
  19 |     String latestNote) {
  20 |   public int getId() {
  21 |     return id;
  22 |   }
  23 |
  24 |   public int getUserId() {
  25 |     return userId;
  26 |   }
  27 |
  28 |   public int getDoctorId() {
  29 |     return doctorId;
  30 |   }
  31 |
  32 |   public Long getSlotId() {
  33 |     return slotId;
  34 |   }
  35 |
  36 |   public String getFullName() {
  37 |     return fullName;
  38 |   }
  39 |
  40 |   public String getGender() {
  41 |     return gender;
  42 |   }
  43 |
  44 |   public String getAge() {
  45 |     return age;
  46 |   }
  47 |
  48 |   public String getDate() {
  49 |     return date;
  50 |   }
  51 |
  52 |   public String getEmail() {
  53 |     return email;
  54 |   }
  55 |
  56 |   public String getPhone() {
  57 |     return phone;
  58 |   }
  59 |
  60 |   public String getReason() {
  61 |     return reason;
  62 |   }
  63 |
  64 |   public String getAddress() {
  65 |     return address;
  66 |   }
  67 |
  68 |   public String getStatus() {
  69 |     return status;
  70 |   }
  71 |
  72 |   public String getDoctorName() {
  73 |     return doctorName;
  74 |   }
  75 |
  76 |   public String getSlotTime() {
  77 |     return slotTime;
  78 |   }
  79 |
  80 |   public String getLatestNote() {
  81 |     return latestNote;
  82 |   }
  83 |
  84 |   public boolean isOpen() {
  85 |     return status.equals("PENDING") || status.equals("CONFIRMED");
  86 |   }
  87 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 4 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 5 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 6 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 7 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 8 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 9 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 10 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 11 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 12 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 13 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 14 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 15 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 16 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 17 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 18 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 19 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 20 | JavaBean getter for Id. JSP property syntax can resolve the corresponding getter. |
| 21 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 24 | JavaBean getter for UserId. JSP property syntax can resolve the corresponding getter. |
| 25 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 28 | JavaBean getter for DoctorId. JSP property syntax can resolve the corresponding getter. |
| 29 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 32 | JavaBean getter for SlotId. JSP property syntax can resolve the corresponding getter. |
| 33 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 36 | JavaBean getter for FullName. JSP property syntax can resolve the corresponding getter. |
| 37 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 40 | JavaBean getter for Gender. JSP property syntax can resolve the corresponding getter. |
| 41 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 44 | JavaBean getter for Age. JSP property syntax can resolve the corresponding getter. |
| 45 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 48 | JavaBean getter for Date. JSP property syntax can resolve the corresponding getter. |
| 49 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 52 | JavaBean getter for Email. JSP property syntax can resolve the corresponding getter. |
| 53 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 56 | JavaBean getter for Phone. JSP property syntax can resolve the corresponding getter. |
| 57 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 60 | JavaBean getter for Reason. JSP property syntax can resolve the corresponding getter. |
| 61 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 64 | JavaBean getter for Address. JSP property syntax can resolve the corresponding getter. |
| 65 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 68 | JavaBean getter for Status. JSP property syntax can resolve the corresponding getter. |
| 69 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 72 | JavaBean getter for DoctorName. JSP property syntax can resolve the corresponding getter. |
| 73 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 76 | JavaBean getter for SlotTime. JSP property syntax can resolve the corresponding getter. |
| 77 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 80 | JavaBean getter for LatestNote. JSP property syntax can resolve the corresponding getter. |
| 81 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 84 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 85 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |

<a id="file-22"></a>

## 22. src/main/java/com/entity/Appointments.java

Retained legacy data object; current service/controller reads use AppointmentRow instead.

Source SHA-256: `841914208788d257148cb859eee57be7445f2c6aab1e882647eb6879861b4a84`

<details>
<summary>Numbered source (144 physical lines)</summary>

~~~text
   1 | package com.entity;
   2 |
   3 | public class Appointments {
   4 |
   5 |   private int id;
   6 |   private int userId;
   7 |   private String fullName;
   8 |   private String gender;
   9 |   private String age;
  10 |   private String appoinDate;
  11 |   private String email;
  12 |   private String phNo;
  13 |   private String diseases;
  14 |   private int doctorId;
  15 |   private String address;
  16 |   private String status;
  17 |
  18 |   public Appointments() {
  19 |     super();
  20 |     // TODO Auto-generated constructor stub
  21 |   }
  22 |
  23 |   public Appointments(
  24 |       int userId,
  25 |       String fullName,
  26 |       String gender,
  27 |       String age,
  28 |       String appoinDate,
  29 |       String email,
  30 |       String phNo,
  31 |       String diseases,
  32 |       int doctorId,
  33 |       String address,
  34 |       String status) {
  35 |     super();
  36 |     this.userId = userId;
  37 |     this.fullName = fullName;
  38 |     this.gender = gender;
  39 |     this.age = age;
  40 |     this.appoinDate = appoinDate;
  41 |     this.email = email;
  42 |     this.phNo = phNo;
  43 |     this.diseases = diseases;
  44 |     this.doctorId = doctorId;
  45 |     this.address = address;
  46 |     this.status = status;
  47 |   }
  48 |
  49 |   public int getId() {
  50 |     return id;
  51 |   }
  52 |
  53 |   public void setId(int id) {
  54 |     this.id = id;
  55 |   }
  56 |
  57 |   public int getUserId() {
  58 |     return userId;
  59 |   }
  60 |
  61 |   public void setUserId(int userId) {
  62 |     this.userId = userId;
  63 |   }
  64 |
  65 |   public String getFullName() {
  66 |     return fullName;
  67 |   }
  68 |
  69 |   public void setFullName(String fullName) {
  70 |     this.fullName = fullName;
  71 |   }
  72 |
  73 |   public String getGender() {
  74 |     return gender;
  75 |   }
  76 |
  77 |   public void setGender(String gender) {
  78 |     this.gender = gender;
  79 |   }
  80 |
  81 |   public String getAge() {
  82 |     return age;
  83 |   }
  84 |
  85 |   public void setAge(String age) {
  86 |     this.age = age;
  87 |   }
  88 |
  89 |   public String getAppoinDate() {
  90 |     return appoinDate;
  91 |   }
  92 |
  93 |   public void setAppoinDate(String appoinDate) {
  94 |     this.appoinDate = appoinDate;
  95 |   }
  96 |
  97 |   public String getEmail() {
  98 |     return email;
  99 |   }
 100 |
 101 |   public void setEmail(String email) {
 102 |     this.email = email;
 103 |   }
 104 |
 105 |   public String getPhNo() {
 106 |     return phNo;
 107 |   }
 108 |
 109 |   public void setPhNo(String phNo) {
 110 |     this.phNo = phNo;
 111 |   }
 112 |
 113 |   public String getDiseases() {
 114 |     return diseases;
 115 |   }
 116 |
 117 |   public void setDiseases(String diseases) {
 118 |     this.diseases = diseases;
 119 |   }
 120 |
 121 |   public int getDoctorId() {
 122 |     return doctorId;
 123 |   }
 124 |
 125 |   public void setDoctorId(int doctorId) {
 126 |     this.doctorId = doctorId;
 127 |   }
 128 |
 129 |   public String getAddress() {
 130 |     return address;
 131 |   }
 132 |
 133 |   public void setAddress(String address) {
 134 |     this.address = address;
 135 |   }
 136 |
 137 |   public String getStatus() {
 138 |     return status;
 139 |   }
 140 |
 141 |   public void setStatus(String status) {
 142 |     this.status = status;
 143 |   }
 144 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 5 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 6 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 7 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 8 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 9 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 10 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 11 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 12 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 13 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 14 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 15 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 16 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 18 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 19 | Calls the superclass constructor; this inherited Object-constructor boilerplate is usually implicit. |
| 20 | Source comment/documentation; TODO statements describe unfinished work. |
| 23 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 24 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 25 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 26 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 27 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 28 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 29 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 30 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 31 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 32 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 33 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 34 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 35 | Calls the superclass constructor; this inherited Object-constructor boilerplate is usually implicit. |
| 36 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 37 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 38 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 39 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 40 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 41 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 42 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 43 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 44 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 45 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 46 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 49 | JavaBean getter for Id. JSP property syntax can resolve the corresponding getter. |
| 50 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 53 | JavaBean setter for Id. A setter changes the object, not the database. |
| 54 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 57 | JavaBean getter for UserId. JSP property syntax can resolve the corresponding getter. |
| 58 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 61 | JavaBean setter for UserId. A setter changes the object, not the database. |
| 62 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 65 | JavaBean getter for FullName. JSP property syntax can resolve the corresponding getter. |
| 66 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 69 | JavaBean setter for FullName. A setter changes the object, not the database. |
| 70 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 73 | JavaBean getter for Gender. JSP property syntax can resolve the corresponding getter. |
| 74 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 77 | JavaBean setter for Gender. A setter changes the object, not the database. |
| 78 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 81 | JavaBean getter for Age. JSP property syntax can resolve the corresponding getter. |
| 82 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 85 | JavaBean setter for Age. A setter changes the object, not the database. |
| 86 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 89 | JavaBean getter for AppoinDate. JSP property syntax can resolve the corresponding getter. |
| 90 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 93 | JavaBean setter for AppoinDate. A setter changes the object, not the database. |
| 94 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 97 | JavaBean getter for Email. JSP property syntax can resolve the corresponding getter. |
| 98 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 101 | JavaBean setter for Email. A setter changes the object, not the database. |
| 102 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 105 | JavaBean getter for PhNo. JSP property syntax can resolve the corresponding getter. |
| 106 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 109 | JavaBean setter for PhNo. A setter changes the object, not the database. |
| 110 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 113 | JavaBean getter for Diseases. JSP property syntax can resolve the corresponding getter. |
| 114 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 117 | JavaBean setter for Diseases. A setter changes the object, not the database. |
| 118 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 121 | JavaBean getter for DoctorId. JSP property syntax can resolve the corresponding getter. |
| 122 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 125 | JavaBean setter for DoctorId. A setter changes the object, not the database. |
| 126 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 129 | JavaBean getter for Address. JSP property syntax can resolve the corresponding getter. |
| 130 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 133 | JavaBean setter for Address. A setter changes the object, not the database. |
| 134 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 137 | JavaBean getter for Status. JSP property syntax can resolve the corresponding getter. |
| 138 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 141 | JavaBean setter for Status. A setter changes the object, not the database. |
| 142 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |

<a id="file-23"></a>

## 23. src/main/java/com/entity/AuditEvent.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `1bc8b1f374f09e3fcc92c2f042e962a65030fd933eacae711882c358f68b9347`

<details>
<summary>Numbered source (24 physical lines)</summary>

~~~text
   1 | package com.entity;
   2 |
   3 | public record AuditEvent(
   4 |     String actorRole, String action, String fromStatus, String toStatus, String createdAt) {
   5 |   public String getActorRole() {
   6 |     return actorRole;
   7 |   }
   8 |
   9 |   public String getAction() {
  10 |     return action;
  11 |   }
  12 |
  13 |   public String getFromStatus() {
  14 |     return fromStatus;
  15 |   }
  16 |
  17 |   public String getToStatus() {
  18 |     return toStatus;
  19 |   }
  20 |
  21 |   public String getCreatedAt() {
  22 |     return createdAt;
  23 |   }
  24 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 4 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 5 | JavaBean getter for ActorRole. JSP property syntax can resolve the corresponding getter. |
| 6 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 9 | JavaBean getter for Action. JSP property syntax can resolve the corresponding getter. |
| 10 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 13 | JavaBean getter for FromStatus. JSP property syntax can resolve the corresponding getter. |
| 14 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 17 | JavaBean getter for ToStatus. JSP property syntax can resolve the corresponding getter. |
| 18 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 21 | JavaBean getter for CreatedAt. JSP property syntax can resolve the corresponding getter. |
| 22 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |

<a id="file-24"></a>

## 24. src/main/java/com/entity/Doctor.java

Plain Java doctor profile with constructors and JavaBean accessors.

Source SHA-256: `f7378df1794d158e7b11db0bccc5cbcb33dd5d312c67dabdd89c04afe13a412b`

<details>
<summary>Numbered source (139 physical lines)</summary>

~~~text
   1 | package com.entity;
   2 |
   3 | public class Doctor {
   4 |   private boolean active = true;
   5 |
   6 |   public boolean isActive() {
   7 |     return active;
   8 |   }
   9 |
  10 |   public void setActive(boolean value) {
  11 |     active = value;
  12 |   }
  13 |
  14 |   private int authVersion;
  15 |
  16 |   public int getAuthVersion() {
  17 |     return authVersion;
  18 |   }
  19 |
  20 |   public void setAuthVersion(int version) {
  21 |     authVersion = version;
  22 |   }
  23 |
  24 |   private int id;
  25 |   private String fullName;
  26 |   private String dob;
  27 |   private String qualification;
  28 |   private String specialist;
  29 |   private String email;
  30 |   private String mobNo;
  31 |   private String password;
  32 |
  33 |   public Doctor() {
  34 |     super();
  35 |     // TODO Auto-generated constructor stub
  36 |   }
  37 |
  38 |   public Doctor(
  39 |       String fullName,
  40 |       String dob,
  41 |       String qualification,
  42 |       String specialist,
  43 |       String email,
  44 |       String mobNo,
  45 |       String password) {
  46 |     super();
  47 |     this.fullName = fullName;
  48 |     this.dob = dob;
  49 |     this.qualification = qualification;
  50 |     this.specialist = specialist;
  51 |     this.email = email;
  52 |     this.mobNo = mobNo;
  53 |     this.password = password;
  54 |   }
  55 |
  56 |   public Doctor(
  57 |       int id,
  58 |       String fullName,
  59 |       String dob,
  60 |       String qualification,
  61 |       String specialist,
  62 |       String email,
  63 |       String mobNo,
  64 |       String password) {
  65 |     super();
  66 |     this.id = id;
  67 |     this.fullName = fullName;
  68 |     this.dob = dob;
  69 |     this.qualification = qualification;
  70 |     this.specialist = specialist;
  71 |     this.email = email;
  72 |     this.mobNo = mobNo;
  73 |     this.password = password;
  74 |   }
  75 |
  76 |   public int getId() {
  77 |     return id;
  78 |   }
  79 |
  80 |   public void setId(int id) {
  81 |     this.id = id;
  82 |   }
  83 |
  84 |   public String getFullName() {
  85 |     return fullName;
  86 |   }
  87 |
  88 |   public void setFullName(String fullName) {
  89 |     this.fullName = fullName;
  90 |   }
  91 |
  92 |   public String getDob() {
  93 |     return dob;
  94 |   }
  95 |
  96 |   public void setDob(String dob) {
  97 |     this.dob = dob;
  98 |   }
  99 |
 100 |   public String getQualification() {
 101 |     return qualification;
 102 |   }
 103 |
 104 |   public void setQualification(String qualification) {
 105 |     this.qualification = qualification;
 106 |   }
 107 |
 108 |   public String getSpecialist() {
 109 |     return specialist;
 110 |   }
 111 |
 112 |   public void setSpecialist(String specialist) {
 113 |     this.specialist = specialist;
 114 |   }
 115 |
 116 |   public String getEmail() {
 117 |     return email;
 118 |   }
 119 |
 120 |   public void setEmail(String email) {
 121 |     this.email = email;
 122 |   }
 123 |
 124 |   public String getMobNo() {
 125 |     return mobNo;
 126 |   }
 127 |
 128 |   public void setMobNo(String mobNo) {
 129 |     this.mobNo = mobNo;
 130 |   }
 131 |
 132 |   public String getPassword() {
 133 |     return password;
 134 |   }
 135 |
 136 |   public void setPassword(String password) {
 137 |     this.password = password;
 138 |   }
 139 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 4 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 6 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 7 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 10 | JavaBean setter for Active. A setter changes the object, not the database. |
| 11 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 14 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 16 | JavaBean getter for AuthVersion. JSP property syntax can resolve the corresponding getter. |
| 17 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 20 | JavaBean setter for AuthVersion. A setter changes the object, not the database. |
| 21 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 24 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 25 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 26 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 27 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 28 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 29 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 30 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 31 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 33 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 34 | Calls the superclass constructor; this inherited Object-constructor boilerplate is usually implicit. |
| 35 | Source comment/documentation; TODO statements describe unfinished work. |
| 38 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 39 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 40 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 41 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 42 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 43 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 44 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 45 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 46 | Calls the superclass constructor; this inherited Object-constructor boilerplate is usually implicit. |
| 47 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 48 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 49 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 50 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 51 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 52 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 53 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 56 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 57 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 58 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 59 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 60 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 61 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 62 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 63 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 64 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 65 | Calls the superclass constructor; this inherited Object-constructor boilerplate is usually implicit. |
| 66 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 67 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 68 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 69 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 70 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 71 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 72 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 73 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 76 | JavaBean getter for Id. JSP property syntax can resolve the corresponding getter. |
| 77 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 80 | JavaBean setter for Id. A setter changes the object, not the database. |
| 81 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 84 | JavaBean getter for FullName. JSP property syntax can resolve the corresponding getter. |
| 85 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 88 | JavaBean setter for FullName. A setter changes the object, not the database. |
| 89 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 92 | JavaBean getter for Dob. JSP property syntax can resolve the corresponding getter. |
| 93 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 96 | JavaBean setter for Dob. A setter changes the object, not the database. |
| 97 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 100 | JavaBean getter for Qualification. JSP property syntax can resolve the corresponding getter. |
| 101 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 104 | JavaBean setter for Qualification. A setter changes the object, not the database. |
| 105 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 108 | JavaBean getter for Specialist. JSP property syntax can resolve the corresponding getter. |
| 109 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 112 | JavaBean setter for Specialist. A setter changes the object, not the database. |
| 113 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 116 | JavaBean getter for Email. JSP property syntax can resolve the corresponding getter. |
| 117 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 120 | JavaBean setter for Email. A setter changes the object, not the database. |
| 121 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 124 | JavaBean getter for MobNo. JSP property syntax can resolve the corresponding getter. |
| 125 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 128 | JavaBean setter for MobNo. A setter changes the object, not the database. |
| 129 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 132 | JavaBean getter for Password. JSP property syntax can resolve the corresponding getter. |
| 133 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 136 | JavaBean setter for Password. A setter changes the object, not the database. |
| 137 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |

<a id="file-25"></a>

## 25. src/main/java/com/entity/Note.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `acbeea5fc8adeba68df9e9b0f5b3e1bf9647db0a4f4e0e0a8e2226a09502612a`

<details>
<summary>Numbered source (15 physical lines)</summary>

~~~text
   1 | package com.entity;
   2 |
   3 | public record Note(String author, String body, String createdAt) {
   4 |   public String getAuthor() {
   5 |     return author;
   6 |   }
   7 |
   8 |   public String getBody() {
   9 |     return body;
  10 |   }
  11 |
  12 |   public String getCreatedAt() {
  13 |     return createdAt;
  14 |   }
  15 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 4 | JavaBean getter for Author. JSP property syntax can resolve the corresponding getter. |
| 5 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 8 | JavaBean getter for Body. JSP property syntax can resolve the corresponding getter. |
| 9 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 12 | JavaBean getter for CreatedAt. JSP property syntax can resolve the corresponding getter. |
| 13 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |

<a id="file-26"></a>

## 26. src/main/java/com/entity/Slot.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `1c1d6e5b35890673449eba567f44b6fcdd072bcbe88c6ecf8c838b0e2016a3be`

<details>
<summary>Numbered source (27 physical lines)</summary>

~~~text
   1 | package com.entity;
   2 |
   3 | import java.time.LocalDateTime;
   4 | import java.time.format.DateTimeFormatter;
   5 |
   6 | public record Slot(
   7 |     long id, int doctorId, String doctorName, String specialty, LocalDateTime startsAt) {
   8 |   public long getId() {
   9 |     return id;
  10 |   }
  11 |
  12 |   public int getDoctorId() {
  13 |     return doctorId;
  14 |   }
  15 |
  16 |   public String getLabel() {
  17 |     return startsAt.format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy · HH:mm"))
  18 |         + " · "
  19 |         + doctorName
  20 |         + " · "
  21 |         + specialty;
  22 |   }
  23 |
  24 |   public String getStartsAt() {
  25 |     return startsAt.toString();
  26 |   }
  27 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 7 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 8 | JavaBean getter for Id. JSP property syntax can resolve the corresponding getter. |
| 9 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 12 | JavaBean getter for DoctorId. JSP property syntax can resolve the corresponding getter. |
| 13 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 16 | JavaBean getter for Label. JSP property syntax can resolve the corresponding getter. |
| 17 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 18 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 19 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 20 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 21 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 24 | JavaBean getter for StartsAt. JSP property syntax can resolve the corresponding getter. |
| 25 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |

<a id="file-27"></a>

## 27. src/main/java/com/entity/Specalist.java

Plain Java specialty object; its name preserves an inherited spelling mistake.

Source SHA-256: `04ac17b9cbcf3008de4a5712341db8b1192dfbf6ef93d2d2daedf77ebc423e92`

<details>
<summary>Numbered source (34 physical lines)</summary>

~~~text
   1 | package com.entity;
   2 |
   3 | public class Specalist {
   4 |
   5 |   private int id;
   6 |   private String specialistName;
   7 |
   8 |   public Specalist() {
   9 |     super();
  10 |     // TODO Auto-generated constructor stub
  11 |   }
  12 |
  13 |   public Specalist(int id, String specialistName) {
  14 |     super();
  15 |     this.id = id;
  16 |     this.specialistName = specialistName;
  17 |   }
  18 |
  19 |   public int getId() {
  20 |     return id;
  21 |   }
  22 |
  23 |   public void setId(int id) {
  24 |     this.id = id;
  25 |   }
  26 |
  27 |   public String getSpecialistName() {
  28 |     return specialistName;
  29 |   }
  30 |
  31 |   public void setSpecialistName(String specialistName) {
  32 |     this.specialistName = specialistName;
  33 |   }
  34 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 5 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 6 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 8 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 9 | Calls the superclass constructor; this inherited Object-constructor boilerplate is usually implicit. |
| 10 | Source comment/documentation; TODO statements describe unfinished work. |
| 13 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 14 | Calls the superclass constructor; this inherited Object-constructor boilerplate is usually implicit. |
| 15 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 16 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 19 | JavaBean getter for Id. JSP property syntax can resolve the corresponding getter. |
| 20 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 23 | JavaBean setter for Id. A setter changes the object, not the database. |
| 24 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 27 | JavaBean getter for SpecialistName. JSP property syntax can resolve the corresponding getter. |
| 28 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 31 | JavaBean setter for SpecialistName. A setter changes the object, not the database. |
| 32 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |

<a id="file-28"></a>

## 28. src/main/java/com/entity/User.java

Plain Java account object; not an ORM-managed entity.

Source SHA-256: `afdd93fac1274e80e0fe7486ae240cfc2adb6e84c10d770df19707859bbdce88`

<details>
<summary>Numbered source (62 physical lines)</summary>

~~~text
   1 | package com.entity;
   2 |
   3 | public class User {
   4 |   private int authVersion;
   5 |
   6 |   public int getAuthVersion() {
   7 |     return authVersion;
   8 |   }
   9 |
  10 |   public void setAuthVersion(int version) {
  11 |     authVersion = version;
  12 |   }
  13 |
  14 |   private int id;
  15 |   private String FullName;
  16 |   private String email;
  17 |   private String password;
  18 |
  19 |   public User() {
  20 |     super();
  21 |     // TODO Auto-generated constructor stub
  22 |   }
  23 |
  24 |   public User(String fullName, String email, String password) {
  25 |     super();
  26 |     FullName = fullName;
  27 |     this.email = email;
  28 |     this.password = password;
  29 |   }
  30 |
  31 |   public int getId() {
  32 |     return id;
  33 |   }
  34 |
  35 |   public void setId(int id) {
  36 |     this.id = id;
  37 |   }
  38 |
  39 |   public String getFullName() {
  40 |     return FullName;
  41 |   }
  42 |
  43 |   public void setFullName(String fullName) {
  44 |     FullName = fullName;
  45 |   }
  46 |
  47 |   public String getEmail() {
  48 |     return email;
  49 |   }
  50 |
  51 |   public void setEmail(String email) {
  52 |     this.email = email;
  53 |   }
  54 |
  55 |   public String getPassword() {
  56 |     return password;
  57 |   }
  58 |
  59 |   public void setPassword(String password) {
  60 |     this.password = password;
  61 |   }
  62 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 4 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 6 | JavaBean getter for AuthVersion. JSP property syntax can resolve the corresponding getter. |
| 7 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 10 | JavaBean setter for AuthVersion. A setter changes the object, not the database. |
| 11 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 14 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 15 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 16 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 17 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 19 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 20 | Calls the superclass constructor; this inherited Object-constructor boilerplate is usually implicit. |
| 21 | Source comment/documentation; TODO statements describe unfinished work. |
| 24 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 25 | Calls the superclass constructor; this inherited Object-constructor boilerplate is usually implicit. |
| 26 | Assigns the incoming constructor/setter value to this data object field. |
| 27 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 28 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 31 | JavaBean getter for Id. JSP property syntax can resolve the corresponding getter. |
| 32 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 35 | JavaBean setter for Id. A setter changes the object, not the database. |
| 36 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 39 | JavaBean getter for FullName. JSP property syntax can resolve the corresponding getter. |
| 40 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 43 | JavaBean setter for FullName. A setter changes the object, not the database. |
| 44 | Assigns the incoming constructor/setter value to this data object field. |
| 47 | JavaBean getter for Email. JSP property syntax can resolve the corresponding getter. |
| 48 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 51 | JavaBean setter for Email. A setter changes the object, not the database. |
| 52 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 55 | JavaBean getter for Password. JSP property syntax can resolve the corresponding getter. |
| 56 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 59 | JavaBean setter for Password. A setter changes the object, not the database. |
| 60 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |

<a id="file-29"></a>

## 29. src/main/java/com/security/DoctorInputs.java

Shared profile validation and optional-password rules.

Source SHA-256: `f332062c2b271c096569727d6dadc1f3c7761e4b5e855a7c54fe3c4b2f972a44`

<details>
<summary>Numbered source (30 physical lines)</summary>

~~~text
   1 | package com.security;
   2 |
   3 | import com.entity.Doctor;
   4 | import jakarta.servlet.http.HttpServletRequest;
   5 | import java.time.LocalDate;
   6 |
   7 | public final class DoctorInputs {
   8 |   private DoctorInputs() {}
   9 |
  10 |   public static Doctor read(
  11 |       HttpServletRequest req, int id, String specialtyField, boolean requirePassword) {
  12 |     String name = Inputs.required(req, "fullname", 120);
  13 |     String dob = Inputs.required(req, "dob", 10);
  14 |     try {
  15 |       if (LocalDate.parse(dob).isAfter(LocalDate.now())) throw new IllegalArgumentException();
  16 |     } catch (RuntimeException e) {
  17 |       throw new IllegalArgumentException("Enter a valid date of birth.");
  18 |     }
  19 |     String qualification = Inputs.required(req, "qualification", 160);
  20 |     String specialty = Inputs.required(req, specialtyField, 120);
  21 |     String email = Inputs.email(req);
  22 |     String phone = Inputs.required(req, "mobno", 20);
  23 |     if (!phone.matches("(?=.*[0-9])[+0-9 ()-]{7,20}"))
  24 |       throw new IllegalArgumentException("Enter a valid phone number.");
  25 |     String password = req.getParameter("password");
  26 |     if (requirePassword || (password != null && !password.isBlank()))
  27 |       password = Inputs.password(req, "password");
  28 |     return new Doctor(id, name, dob, qualification, specialty, email, phone, password);
  29 |   }
  30 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 8 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 10 | Declares read: Share doctor-profile validation between administrator and doctor actions. |
| 11 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 12 | Runs shared server-side validation before persistence. |
| 13 | Runs shared server-side validation before persistence. |
| 14 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 15 | Branches on the shown condition; be able to explain both the success and rejection path. Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 16 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 17 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 19 | Runs shared server-side validation before persistence. |
| 20 | Runs shared server-side validation before persistence. |
| 21 | Runs shared server-side validation before persistence. |
| 22 | Runs shared server-side validation before persistence. |
| 23 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 24 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 25 | Reads untrusted submitted String data, which can be absent/null. |
| 26 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 27 | Runs shared server-side validation before persistence. |
| 28 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |

<a id="file-30"></a>

## 30. src/main/java/com/security/Html.java

Escaping helper for legacy server-generated HTML.

Source SHA-256: `4f21de754ae26f9f65f8bee0cb83588eee7fcb8cde6392cb5fa2ee872a3c0759`

<details>
<summary>Numbered source (17 physical lines)</summary>

~~~text
   1 | package com.security;
   2 |
   3 | /** Escapes legacy JSP expressions in both HTML text and quoted attributes. */
   4 | public final class Html {
   5 |   private Html() {}
   6 |
   7 |   public static String escape(Object value) {
   8 |     if (value == null) return "";
   9 |     return value
  10 |         .toString()
  11 |         .replace("&", "&amp;")
  12 |         .replace("<", "&lt;")
  13 |         .replace(">", "&gt;")
  14 |         .replace("\"", "&quot;")
  15 |         .replace("'", "&#39;");
  16 |   }
  17 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Source comment/documentation; TODO statements describe unfinished work. |
| 4 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 5 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 7 | Declares escape: Encode untrusted values for HTML text/quoted attributes, not arbitrary JavaScript/CSS/URL contexts. |
| 8 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 9 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 10 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 11 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 12 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 13 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 14 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 15 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |

<a id="file-31"></a>

## 31. src/main/java/com/security/Inputs.java

Reusable server validation; browser form attributes are supplementary feedback.

Source SHA-256: `b81fb507ba92e3b1894b4ba6100673e9379baaa90a1d984f7cfc157a8b3b6392`

<details>
<summary>Numbered source (39 physical lines)</summary>

~~~text
   1 | package com.security;
   2 |
   3 | import jakarta.servlet.http.HttpServletRequest;
   4 | import java.time.LocalDate;
   5 |
   6 | public final class Inputs {
   7 |   private Inputs() {}
   8 |
   9 |   public static String required(HttpServletRequest req, String name, int max) {
  10 |     String value = req.getParameter(name);
  11 |     if (value == null || value.trim().isEmpty() || value.trim().length() > max)
  12 |       throw new IllegalArgumentException("Please complete all fields within the allowed lengths.");
  13 |     return value.trim();
  14 |   }
  15 |
  16 |   public static String email(HttpServletRequest req) {
  17 |     String value = required(req, "email", 254).toLowerCase(java.util.Locale.ROOT);
  18 |     if (!value.matches("[^\\s@]+@[^\\s@]+\\.[^\\s@]+"))
  19 |       throw new IllegalArgumentException("Enter a valid email address.");
  20 |     return value;
  21 |   }
  22 |
  23 |   public static String password(HttpServletRequest req, String field) {
  24 |     String value = req.getParameter(field);
  25 |     if (value == null || value.length() < 8 || value.length() > 128)
  26 |       throw new IllegalArgumentException("Use a password between 8 and 128 characters.");
  27 |     return value;
  28 |   }
  29 |
  30 |   public static String appointmentDate(HttpServletRequest req) {
  31 |     try {
  32 |       LocalDate date = LocalDate.parse(required(req, "appoint_date", 10));
  33 |       if (date.isBefore(LocalDate.now())) throw new IllegalArgumentException();
  34 |       return date.toString();
  35 |     } catch (RuntimeException e) {
  36 |       throw new IllegalArgumentException("Choose today or a future appointment date.");
  37 |     }
  38 |   }
  39 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 7 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 9 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 10 | Reads untrusted submitted String data, which can be absent/null. |
| 11 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 12 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 13 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 16 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 17 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 18 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 19 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 20 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 23 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 24 | Reads untrusted submitted String data, which can be absent/null. |
| 25 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 26 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 27 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 30 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 31 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 32 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 33 | Branches on the shown condition; be able to explain both the success and rejection path. Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 34 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 35 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 36 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |

<a id="file-32"></a>

## 32. src/main/java/com/security/LoginLimiter.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `bc0c916c37f9f18837a5952776703efb58fe07f1a2670caba09c447e5bf8b6df`

<details>
<summary>Numbered source (44 physical lines)</summary>

~~~text
   1 | package com.security;
   2 |
   3 | import com.service.ServiceException;
   4 | import java.util.concurrent.ConcurrentHashMap;
   5 |
   6 | /** Bounded, process-local failed-login throttling. Use a shared store for multiple replicas. */
   7 | public final class LoginLimiter {
   8 |   private static final ConcurrentHashMap<String, Attempt> attempts = new ConcurrentHashMap<>();
   9 |   private static final long WINDOW = 300_000;
  10 |
  11 |   private record Attempt(long since, int count) {}
  12 |
  13 |   private LoginLimiter() {}
  14 |
  15 |   private static String key(String ip, String email) {
  16 |     return ip + "|" + email;
  17 |   }
  18 |
  19 |   public static void check(String ip, String email) {
  20 |     long now = System.currentTimeMillis();
  21 |     attempts.entrySet().removeIf(e -> now - e.getValue().since() > WINDOW);
  22 |     Attempt account = attempts.get(key(ip, email)), address = attempts.get(ip);
  23 |     if (account != null && account.count() >= 5
  24 |         || address != null && address.count() >= 25
  25 |         || attempts.size() >= 10000)
  26 |       throw new ServiceException(
  27 |           429, "Too many failed sign-in attempts. Wait five minutes and try again.");
  28 |   }
  29 |
  30 |   public static void failed(String ip, String email) {
  31 |     long now = System.currentTimeMillis();
  32 |     for (String k : new String[] {key(ip, email), ip})
  33 |       attempts.compute(
  34 |           k,
  35 |           (ignored, a) ->
  36 |               a == null || now - a.since() > WINDOW
  37 |                   ? new Attempt(now, 1)
  38 |                   : new Attempt(a.since(), a.count() + 1));
  39 |   }
  40 |
  41 |   public static void succeeded(String ip, String email) {
  42 |     attempts.remove(key(ip, email));
  43 |   }
  44 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Source comment/documentation; TODO statements describe unfinished work. |
| 7 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 8 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 9 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 11 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 13 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 15 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 16 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 19 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 20 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 21 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 22 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 23 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 24 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 25 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 26 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 27 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 30 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 31 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 32 | Iterates the listed values/collection and executes the enclosed operations for each item. |
| 33 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 34 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 35 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 36 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 37 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 38 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 41 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 42 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |

<a id="file-33"></a>

## 33. src/main/java/com/security/Passwords.java

JDK PBKDF2 derivation/verification; the current work factor needs strengthening before deployment.

Source SHA-256: `d0419c43f3d6d52be3a481087b05d5e05abf7a801d8fbdcaaca35af417b1ce69`

<details>
<summary>Numbered source (71 physical lines)</summary>

~~~text
   1 | package com.security;
   2 |
   3 | import java.security.MessageDigest;
   4 | import java.security.SecureRandom;
   5 | import java.util.Base64;
   6 | import javax.crypto.SecretKeyFactory;
   7 | import javax.crypto.spec.PBEKeySpec;
   8 |
   9 | public final class Passwords {
  10 |   private static final int ITERATIONS = 600_000;
  11 |   private static final SecureRandom RANDOM = new SecureRandom();
  12 |
  13 |   private Passwords() {}
  14 |
  15 |   public static String hash(String password) {
  16 |     byte[] salt = new byte[16];
  17 |     RANDOM.nextBytes(salt);
  18 |     return "pbkdf2$"
  19 |         + ITERATIONS
  20 |         + "$"
  21 |         + Base64.getEncoder().encodeToString(salt)
  22 |         + "$"
  23 |         + Base64.getEncoder().encodeToString(derive(password, salt, ITERATIONS));
  24 |   }
  25 |
  26 |   public static boolean verify(String password, String stored) {
  27 |     if (password == null || stored == null || !stored.startsWith("pbkdf2$")) return false;
  28 |     try {
  29 |       String[] parts = stored.split("\\$");
  30 |       if (parts.length != 4) return false;
  31 |       int iterations = Integer.parseInt(parts[1]);
  32 |       if (iterations < 1 || iterations > 1_000_000) return false;
  33 |       return MessageDigest.isEqual(
  34 |           Base64.getDecoder().decode(parts[3]),
  35 |           derive(password, Base64.getDecoder().decode(parts[2]), iterations));
  36 |     } catch (IllegalArgumentException e) {
  37 |       return false;
  38 |     }
  39 |   }
  40 |
  41 |   public static boolean needsRehash(String stored) {
  42 |     try {
  43 |       return Integer.parseInt(stored.split("\\$")[1]) < ITERATIONS;
  44 |     } catch (RuntimeException e) {
  45 |       return true;
  46 |     }
  47 |   }
  48 |
  49 |   public static void main(String[] args) throws java.io.IOException {
  50 |     java.io.Console console = System.console();
  51 |     String password =
  52 |         console == null
  53 |             ? new java.io.BufferedReader(new java.io.InputStreamReader(System.in)).readLine()
  54 |             : new String(console.readPassword("Administrator password: "));
  55 |     if (password == null || password.length() < 12)
  56 |       throw new IllegalArgumentException(
  57 |           "Use at least 12 characters for an administrator password.");
  58 |     System.out.println(hash(password));
  59 |   }
  60 |
  61 |   private static byte[] derive(String password, byte[] salt, int iterations) {
  62 |     PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, 256);
  63 |     try {
  64 |       return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
  65 |     } catch (Exception e) {
  66 |       throw new IllegalStateException("Password hashing unavailable", e);
  67 |     } finally {
  68 |       spec.clearPassword();
  69 |     }
  70 |   }
  71 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 9 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 10 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 11 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 13 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 15 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 16 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 17 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 18 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 19 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 20 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 21 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 22 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 23 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 26 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 27 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 28 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 29 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 30 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 31 | Parses an integer and may raise NumberFormatException, a subtype of IllegalArgumentException. |
| 32 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 33 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 34 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 35 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 36 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 37 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 41 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 42 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 43 | Parses an integer and may raise NumberFormatException, a subtype of IllegalArgumentException. Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 44 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 45 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 49 | Declares main: Start the local-only embedded server, register shutdown cleanup, and wait for requests. |
| 50 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 51 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 52 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 53 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 54 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 55 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 56 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 57 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 58 | Prints process diagnostics, not HTTP response content. |
| 61 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 62 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 63 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 64 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 65 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 66 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 67 | Runs cleanup even on return/exception; connection or transaction state must not leak. |
| 68 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |

<a id="file-34"></a>

## 34. src/main/java/com/security/RequestFilter.java

Shared role, CSRF, response-header and connection-cleanup boundary.

Source SHA-256: `2f2331e0636e79d780d000c79eed2629341aa7ee4ace0e907056ba48bf029610`

<details>
<summary>Numbered source (176 physical lines)</summary>

~~~text
   1 | package com.security;
   2 |
   3 | import com.entity.*;
   4 | import com.service.*;
   5 | import com.web.Metrics;
   6 | import jakarta.servlet.*;
   7 | import jakarta.servlet.annotation.WebFilter;
   8 | import jakarta.servlet.http.*;
   9 | import java.io.IOException;
  10 | import java.util.*;
  11 | import java.util.logging.Logger;
  12 |
  13 | @WebFilter("/*")
  14 | public final class RequestFilter implements Filter {
  15 |   private static final Logger LOG = Logger.getLogger(RequestFilter.class.getName());
  16 |   private static final Set<String> PUBLIC =
  17 |       Set.of(
  18 |           "/",
  19 |           "/index.jsp",
  20 |           "/signup.jsp",
  21 |           "/user_Login.jsp",
  22 |           "/doctor.jsp",
  23 |           "/admin.jsp",
  24 |           "/register",
  25 |           "/userLogin",
  26 |           "/loginDoctor",
  27 |           "/adminLogin",
  28 |           "/health/live",
  29 |           "/health/ready");
  30 |   private static final Set<String> ADMIN =
  31 |       Set.of("/addDoctor", "/updateDoctor", "/delete_doctor", "/addSpecialist", "/adminLogout");
  32 |   private static final Set<String> DOCTOR =
  33 |       Set.of("/doctorUpdateProfile", "/docChangePassword", "/updateStatus", "/docLogout");
  34 |   private static final Set<String> PATIENT =
  35 |       Set.of(
  36 |           "/user_appointment.jsp",
  37 |           "/view_appointment.jsp",
  38 |           "/appointment.jsp",
  39 |           "/change_password.jsp",
  40 |           "/appAppointment",
  41 |           "/userChangePassword",
  42 |           "/userLogout");
  43 |   private static final Set<String> SHARED =
  44 |       Set.of(
  45 |           "/appointmentState", "/publishSlot", "/withdrawSlot", "/api/slots", "/api/appointments");
  46 |
  47 |   @Override
  48 |   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
  49 |       throws IOException, ServletException {
  50 |     HttpServletRequest req = (HttpServletRequest) request;
  51 |     HttpServletResponse resp = (HttpServletResponse) response;
  52 |     long start = System.nanoTime();
  53 |     String requestId = UUID.randomUUID().toString();
  54 |     req.setAttribute("requestId", requestId);
  55 |     resp.setHeader("X-Request-ID", requestId);
  56 |     resp.setHeader("X-Content-Type-Options", "nosniff");
  57 |     resp.setHeader("X-Frame-Options", "DENY");
  58 |     resp.setHeader("Referrer-Policy", "same-origin");
  59 |     resp.setHeader(
  60 |         "Content-Security-Policy",
  61 |         "default-src 'self'; script-src 'self' https://cdn.jsdelivr.net; style-src 'self'"
  62 |             + " 'unsafe-inline' https://cdn.jsdelivr.net https://cdnjs.cloudflare.com; font-src"
  63 |             + " 'self' https://cdnjs.cloudflare.com data:; img-src 'self' data:; form-action"
  64 |             + " 'self'; frame-ancestors 'none'; base-uri 'self'");
  65 |     if (req.isSecure()) resp.setHeader("Strict-Transport-Security", "max-age=31536000");
  66 |     req.setCharacterEncoding("UTF-8");
  67 |     String path = req.getServletPath();
  68 |     try {
  69 |       if (path.startsWith("/img/") || path.startsWith("/component/") && !path.endsWith(".jsp")) {
  70 |         chain.doFilter(req, resp);
  71 |         return;
  72 |       }
  73 |       resp.setHeader("Cache-Control", "no-store");
  74 |       if (path.startsWith("/health/")) {
  75 |         chain.doFilter(req, resp);
  76 |         return;
  77 |       }
  78 |       if (req.getContentLengthLong() > 16384)
  79 |         throw new ServiceException(413, "This form is too large.");
  80 |       req.setAttribute(
  81 |           "demoMode", Boolean.parseBoolean(com.db.DBConnect.config("DEMO_MODE", "false")));
  82 |       HttpSession session = req.getSession();
  83 |       if (session.getAttribute("csrfToken") == null)
  84 |         session.setAttribute("csrfToken", UUID.randomUUID().toString());
  85 |       String role =
  86 |           path.startsWith("/admin/") || ADMIN.contains(path)
  87 |               ? "adminObj"
  88 |               : path.startsWith("/doctor/") || DOCTOR.contains(path)
  89 |                   ? "docObj"
  90 |                   : PATIENT.contains(path) ? "userObj" : null;
  91 |       boolean shared = SHARED.contains(path);
  92 |       if (!PUBLIC.contains(path) && role == null && !shared)
  93 |         throw new ServiceException(404, "Page not found.");
  94 |       Object principal = role == null ? null : session.getAttribute(role);
  95 |       if (role != null && principal == null) {
  96 |         if (req.getMethod().equals("GET")) {
  97 |           resp.sendRedirect(
  98 |               req.getContextPath()
  99 |                   + (role.equals("adminObj")
 100 |                       ? "/admin.jsp"
 101 |                       : role.equals("docObj") ? "/doctor.jsp" : "/user_Login.jsp"));
 102 |           return;
 103 |         }
 104 |         throw new ServiceException(401, "Sign in before submitting this form.");
 105 |       }
 106 |       if (session.getAttribute("userObj") instanceof User u
 107 |           && !HospitalService.validSession(Actor.patient(u.getId()), u.getAuthVersion())) {
 108 |         session.invalidate();
 109 |         throw new ServiceException(401, "Your session expired. Sign in again.");
 110 |       }
 111 |       if (session.getAttribute("docObj") instanceof Doctor d
 112 |           && !HospitalService.validSession(Actor.doctor(d.getId()), d.getAuthVersion())) {
 113 |         session.invalidate();
 114 |         throw new ServiceException(401, "Your doctor session expired. Sign in again.");
 115 |       }
 116 |       if (session.getAttribute("adminObj") != null
 117 |           && !HospitalService.adminStamp().equals(session.getAttribute("adminStamp"))) {
 118 |         session.invalidate();
 119 |         throw new ServiceException(401, "Your administrator session expired.");
 120 |       }
 121 |       if (shared
 122 |           && session.getAttribute("userObj") == null
 123 |           && session.getAttribute("docObj") == null
 124 |           && session.getAttribute("adminObj") == null)
 125 |         throw new ServiceException(401, "Please sign in.");
 126 |       if ((path.equals("/publishSlot") || path.equals("/withdrawSlot"))
 127 |           && session.getAttribute("userObj") != null)
 128 |         throw new ServiceException(403, "Only hospital staff can manage slots.");
 129 |       if (req.getMethod().equals("POST")
 130 |           && !Objects.equals(session.getAttribute("csrfToken"), req.getParameter("csrfToken")))
 131 |         throw new ServiceException(403, "Your form expired. Reload the form and try again.");
 132 |       chain.doFilter(req, resp);
 133 |     } catch (ServiceException e) {
 134 |       error(req, resp, e.getStatus(), e.getMessage());
 135 |     } catch (DataAccessException e) {
 136 |       LOG.severe("requestId=" + requestId + " database_failure");
 137 |       error(req, resp, 503, "The database is temporarily unavailable. Please try again.");
 138 |     } catch (Exception e) {
 139 |       LOG.severe("requestId=" + requestId + " failure_type=" + e.getClass().getSimpleName());
 140 |       error(req, resp, 500, "We could not complete this request. Please try again.");
 141 |     } finally {
 142 |       long elapsed = System.nanoTime() - start;
 143 |       Metrics.requests.increment();
 144 |       Metrics.nanos.add(elapsed);
 145 |       if (resp.getStatus() >= 500) Metrics.errors.increment();
 146 |       LOG.info(
 147 |           "requestId="
 148 |               + requestId
 149 |               + " method="
 150 |               + req.getMethod()
 151 |               + " path="
 152 |               + path
 153 |               + " status="
 154 |               + resp.getStatus()
 155 |               + " duration_ms="
 156 |               + (elapsed / 1_000_000));
 157 |     }
 158 |   }
 159 |
 160 |   private void error(HttpServletRequest req, HttpServletResponse resp, int status, String message)
 161 |       throws ServletException, IOException {
 162 |     if (resp.isCommitted()) return;
 163 |     resp.setStatus(status);
 164 |     req.setAttribute("errorMessage", message);
 165 |     if (status == 429) resp.setHeader("Retry-After", "300");
 166 |     if (req.getServletPath().startsWith("/api/")) {
 167 |       resp.setContentType("application/json;charset=UTF-8");
 168 |       new com.fasterxml.jackson.databind.ObjectMapper()
 169 |           .writeValue(
 170 |               resp.getWriter(),
 171 |               Map.of("error", message, "requestId", req.getAttribute("requestId")));
 172 |       return;
 173 |     }
 174 |     req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
 175 |   }
 176 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 8 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 9 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 10 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 11 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 13 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 14 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 15 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 16 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 17 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 18 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 19 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 20 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 21 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 22 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 23 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 24 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 25 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 26 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 27 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 28 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 29 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 30 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 31 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 32 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 33 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 34 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 35 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 36 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 37 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 38 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 39 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 40 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 41 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 42 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 43 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 44 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 45 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 47 | Checks at compile time that the following method overrides a parent/interface contract. |
| 48 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 49 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 50 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 51 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 52 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 53 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 54 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 55 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 56 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 57 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 58 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 59 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 60 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 61 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 62 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 63 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 64 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 65 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 66 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 67 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 68 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 69 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 70 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 71 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 73 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 74 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 75 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 76 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 78 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 79 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 80 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 81 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 82 | Uses the server session identified by the browser cookie; attributes survive redirects. |
| 83 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 84 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 85 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 86 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 87 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 88 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 89 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 90 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 91 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 92 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 93 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 94 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 95 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 96 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 97 | Sends a redirect; the browser issues a new request and request attributes do not carry across. |
| 98 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 99 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 100 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 101 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 102 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 104 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 106 | Reads authenticated server identity rather than trusting a submitted account ID. Branches on the shown condition; be able to explain both the success and rejection path. |
| 107 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 108 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 109 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 111 | Reads authenticated server identity rather than trusting a submitted account ID. Branches on the shown condition; be able to explain both the success and rejection path. |
| 112 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 113 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 114 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 116 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 117 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 118 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 119 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 121 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 122 | Reads authenticated server identity rather than trusting a submitted account ID. |
| 123 | Reads authenticated server identity rather than trusting a submitted account ID. |
| 124 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 125 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 126 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 127 | Reads authenticated server identity rather than trusting a submitted account ID. |
| 128 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 129 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 130 | Reads untrusted submitted String data, which can be absent/null. |
| 131 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 132 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 133 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 134 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 135 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 136 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 137 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 138 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 139 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 140 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 141 | Runs cleanup even on return/exception; connection or transaction state must not leak. |
| 142 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 143 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 144 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 145 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 146 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 147 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 148 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 149 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 150 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 151 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 152 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 153 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 154 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 155 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 156 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 160 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 161 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 162 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 163 | Maps data into a plain Java object; the setter itself does not write SQL. |
| 164 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 165 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 166 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 167 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 168 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 169 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 170 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 171 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 172 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 174 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |

<a id="file-35"></a>

## 35. src/main/java/com/service/Actor.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `975f475152958492d98a9db17b931f1a0cb5de4ee4a649e33a3fcd2fdb122846`

<details>
<summary>Numbered source (20 physical lines)</summary>

~~~text
   1 | package com.service;
   2 |
   3 | public record Actor(String role, int id) {
   4 |   public Actor {
   5 |     if (!java.util.Set.of("PATIENT", "DOCTOR", "ADMIN").contains(role)
   6 |         || (!role.equals("ADMIN") && id < 1)) throw new IllegalArgumentException("Invalid actor");
   7 |   }
   8 |
   9 |   public static Actor patient(int id) {
  10 |     return new Actor("PATIENT", id);
  11 |   }
  12 |
  13 |   public static Actor doctor(int id) {
  14 |     return new Actor("DOCTOR", id);
  15 |   }
  16 |
  17 |   public static Actor admin() {
  18 |     return new Actor("ADMIN", 0);
  19 |   }
  20 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 4 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 5 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 6 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 9 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 10 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 13 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 14 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 17 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 18 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |

<a id="file-36"></a>

## 36. src/main/java/com/service/AppointmentService.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `1f9b1edaae5b072a8d73537f968de625362fe23c4dc7fe1c273f079568593d43`

<details>
<summary>Numbered source (517 physical lines)</summary>

~~~text
   1 | package com.service;
   2 |
   3 | import com.dao.AppointmentDao;
   4 | import com.entity.*;
   5 | import java.security.MessageDigest;
   6 | import java.sql.*;
   7 | import java.time.*;
   8 | import java.util.*;
   9 | import javax.sql.DataSource;
  10 |
  11 | /** All scheduling mutations and their audit records share one database transaction. */
  12 | public final class AppointmentService {
  13 |   private final DataSource source;
  14 |   private final Clock clock;
  15 |
  16 |   public AppointmentService(DataSource source, Clock clock) {
  17 |     this.source = source;
  18 |     this.clock = clock;
  19 |   }
  20 |
  21 |   public record Booking(
  22 |       long slotId,
  23 |       String requestKey,
  24 |       String fullName,
  25 |       String gender,
  26 |       String age,
  27 |       String email,
  28 |       String phone,
  29 |       String reason,
  30 |       String address) {}
  31 |
  32 |   @FunctionalInterface
  33 |   interface Work<T> {
  34 |     T run(Connection c) throws SQLException;
  35 |   }
  36 |
  37 |   private <T> T transaction(Work<T> work) {
  38 |     try (Connection c = source.getConnection()) {
  39 |       c.setAutoCommit(false);
  40 |       try {
  41 |         T result = work.run(c);
  42 |         c.commit();
  43 |         return result;
  44 |       } catch (SQLException | RuntimeException e) {
  45 |         try {
  46 |           c.rollback();
  47 |         } catch (SQLException failure) {
  48 |           e.addSuppressed(failure);
  49 |         }
  50 |         throw e;
  51 |       }
  52 |     } catch (SQLException e) {
  53 |       throw new DataAccessException(e);
  54 |     }
  55 |   }
  56 |
  57 |   private <T> T read(Work<T> work) {
  58 |     try (Connection c = source.getConnection()) {
  59 |       return work.run(c);
  60 |     } catch (SQLException e) {
  61 |       throw new DataAccessException(e);
  62 |     }
  63 |   }
  64 |
  65 |   private LocalDateTime now() {
  66 |     return LocalDateTime.now(clock);
  67 |   }
  68 |
  69 |   private static void require(boolean condition, int status, String message) {
  70 |     if (!condition) throw new ServiceException(status, message);
  71 |   }
  72 |
  73 |   private void lockDoctor(Connection c, int id) throws SQLException {
  74 |     try (PreparedStatement ps =
  75 |         c.prepareStatement("SELECT active FROM doctor WHERE id=? FOR UPDATE")) {
  76 |       ps.setInt(1, id);
  77 |       try (ResultSet rs = ps.executeQuery()) {
  78 |         require(rs.next() && rs.getBoolean(1), 404, "Doctor is unavailable.");
  79 |       }
  80 |     }
  81 |   }
  82 |
  83 |   private record LockedSlot(int doctorId, LocalDateTime startsAt, boolean enabled) {}
  84 |
  85 |   private LockedSlot slot(Connection c, long id, boolean lock) throws SQLException {
  86 |     try (PreparedStatement ps =
  87 |         c.prepareStatement(
  88 |             "SELECT doctor_id,starts_at,enabled FROM doctor_slot WHERE id=?"
  89 |                 + (lock ? " FOR UPDATE" : ""))) {
  90 |       ps.setLong(1, id);
  91 |       try (ResultSet rs = ps.executeQuery()) {
  92 |         require(rs.next(), 404, "Slot not found.");
  93 |         return new LockedSlot(rs.getInt(1), rs.getTimestamp(2).toLocalDateTime(), rs.getBoolean(3));
  94 |       }
  95 |     }
  96 |   }
  97 |
  98 |   private void event(Connection c, int id, Actor actor, String from, String to, String action)
  99 |       throws SQLException {
 100 |     try (PreparedStatement ps =
 101 |         c.prepareStatement(
 102 |             "INSERT INTO"
 103 |                 + " appointment_event(appointment_id,actor_role,actor_id,from_status,to_status,action)"
 104 |                 + " VALUES(?,?,?,?,?,?)")) {
 105 |       ps.setInt(1, id);
 106 |       ps.setString(2, actor.role());
 107 |       ps.setInt(3, actor.id());
 108 |       ps.setString(4, from);
 109 |       ps.setString(5, to);
 110 |       ps.setString(6, action);
 111 |       ps.executeUpdate();
 112 |     }
 113 |   }
 114 |
 115 |   private String fingerprint(Booking b) {
 116 |     try {
 117 |       var bytes = new java.io.ByteArrayOutputStream();
 118 |       try (var out = new java.io.DataOutputStream(bytes)) {
 119 |         out.writeLong(b.slotId());
 120 |         for (String field :
 121 |             List.of(
 122 |                 b.fullName(), b.gender(), b.age(), b.email(), b.phone(), b.reason(), b.address()))
 123 |           out.writeUTF(field);
 124 |       }
 125 |       return HexFormat.of()
 126 |           .formatHex(MessageDigest.getInstance("SHA-256").digest(bytes.toByteArray()));
 127 |     } catch (Exception e) {
 128 |       throw new IllegalStateException(e);
 129 |     }
 130 |   }
 131 |
 132 |   private void validate(Booking b) {
 133 |     require(
 134 |         b.requestKey() != null && b.requestKey().matches("[A-Za-z0-9-]{16,64}"),
 135 |         400,
 136 |         "Reload the booking form before submitting.");
 137 |     String[] fields = {
 138 |       b.fullName(), b.gender(), b.age(), b.email(), b.phone(), b.reason(), b.address()
 139 |     };
 140 |     int[] limits = {120, 30, 3, 254, 20, 500, 500};
 141 |     for (int i = 0; i < fields.length; i++)
 142 |       require(
 143 |           fields[i] != null
 144 |               && !fields[i].isBlank()
 145 |               && fields[i].length() <= limits[i]
 146 |               && !fields[i].chars().anyMatch(x -> x == 0),
 147 |           400,
 148 |           "Complete all appointment fields within the allowed lengths.");
 149 |     require(
 150 |         Set.of("male", "female", "other", "prefer not to say").contains(b.gender()),
 151 |         400,
 152 |         "Choose a valid gender.");
 153 |     int age;
 154 |     try {
 155 |       age = Integer.parseInt(b.age());
 156 |     } catch (NumberFormatException e) {
 157 |       throw new ServiceException(400, "Enter a valid age.");
 158 |     }
 159 |     require(age >= 0 && age <= 120, 400, "Age must be between 0 and 120.");
 160 |     require(b.email().matches("[^\\s@]+@[^\\s@]+\\.[^\\s@]+"), 400, "Enter a valid email.");
 161 |     require(b.phone().matches("(?=.*[0-9])[+0-9 ()-]{7,20}"), 400, "Enter a valid phone number.");
 162 |   }
 163 |
 164 |   public int book(int userId, Booking booking) {
 165 |     validate(booking);
 166 |     String digest = fingerprint(booking);
 167 |     try {
 168 |       return transaction(c -> {
 169 |         // A unique request key serializes duplicate submissions across application instances.
 170 |         try (PreparedStatement ps =
 171 |             c.prepareStatement(
 172 |                 "INSERT INTO booking_request(user_id,request_key,payload_hash) VALUES(?,?,?)")) {
 173 |           ps.setInt(1, userId);
 174 |           ps.setString(2, booking.requestKey());
 175 |           ps.setString(3, digest);
 176 |           ps.executeUpdate();
 177 |         }
 178 |         LockedSlot initial = slot(c, booking.slotId(), false);
 179 |         // All mutations lock doctor -> slot -> appointment in the same order.
 180 |         lockDoctor(c, initial.doctorId());
 181 |         LockedSlot selected = slot(c, booking.slotId(), true);
 182 |         require(
 183 |             selected.enabled() && selected.startsAt().isAfter(now()),
 184 |             409,
 185 |             "This slot is no longer available. Choose another slot.");
 186 |         try (PreparedStatement ps =
 187 |             c.prepareStatement("SELECT appointment_id FROM slot_reservation WHERE slot_id=?")) {
 188 |           ps.setLong(1, booking.slotId());
 189 |           try (ResultSet rs = ps.executeQuery()) {
 190 |             require(!rs.next(), 409, "Another patient has just booked this slot. Choose another.");
 191 |           }
 192 |         }
 193 |         int id;
 194 |         try (PreparedStatement ps =
 195 |             c.prepareStatement(
 196 |                 """
 197 |                 INSERT INTO appointment(user_id,fullname,gender,age,appoint_date,email,phno,diseases,doctor_id,address,status,slot_id)
 198 |                 VALUES(?,?,?,?,?,?,?,?,?,?,'PENDING',?)
 199 |                 """,
 200 |                 Statement.RETURN_GENERATED_KEYS)) {
 201 |           ps.setInt(1, userId);
 202 |           ps.setString(2, booking.fullName());
 203 |           ps.setString(3, booking.gender());
 204 |           ps.setString(4, booking.age());
 205 |           ps.setDate(5, java.sql.Date.valueOf(selected.startsAt().toLocalDate()));
 206 |           ps.setString(6, booking.email());
 207 |           ps.setString(7, booking.phone());
 208 |           ps.setString(8, booking.reason());
 209 |           ps.setInt(9, selected.doctorId());
 210 |           ps.setString(10, booking.address());
 211 |           ps.setLong(11, booking.slotId());
 212 |           ps.executeUpdate();
 213 |           try (ResultSet rs = ps.getGeneratedKeys()) {
 214 |             if (!rs.next()) throw new SQLException("Missing generated appointment ID");
 215 |             id = rs.getInt(1);
 216 |           }
 217 |         }
 218 |         try (PreparedStatement ps =
 219 |             c.prepareStatement(
 220 |                 "INSERT INTO slot_reservation(slot_id,appointment_id) VALUES(?,?)")) {
 221 |           ps.setLong(1, booking.slotId());
 222 |           ps.setInt(2, id);
 223 |           ps.executeUpdate();
 224 |         }
 225 |         try (PreparedStatement ps =
 226 |             c.prepareStatement(
 227 |                 "UPDATE booking_request SET appointment_id=? WHERE user_id=? AND request_key=?")) {
 228 |           ps.setInt(1, id);
 229 |           ps.setInt(2, userId);
 230 |           ps.setString(3, booking.requestKey());
 231 |           ps.executeUpdate();
 232 |         }
 233 |         event(c, id, Actor.patient(userId), null, "PENDING", "BOOKED");
 234 |         return id;
 235 |       });
 236 |     } catch (DataAccessException failure) {
 237 |       if (failure.getCause() instanceof SQLException sql && isDuplicate(sql)) {
 238 |         Integer previous =
 239 |             read(c -> {
 240 |               try (PreparedStatement ps =
 241 |                   c.prepareStatement(
 242 |                       "SELECT payload_hash,appointment_id FROM booking_request WHERE user_id=? AND"
 243 |                           + " request_key=?")) {
 244 |                 ps.setInt(1, userId);
 245 |                 ps.setString(2, booking.requestKey());
 246 |                 try (ResultSet rs = ps.executeQuery()) {
 247 |                   if (!rs.next()) return null;
 248 |                   require(
 249 |                       digest.equals(rs.getString(1)),
 250 |                       409,
 251 |                       "This request key was already used for different booking details.");
 252 |                   return rs.getInt(2);
 253 |                 }
 254 |               }
 255 |             });
 256 |         if (previous != null) return previous;
 257 |         throw new ServiceException(409, "This slot has already been reserved.");
 258 |       }
 259 |       throw failure;
 260 |     }
 261 |   }
 262 |
 263 |   public static boolean isDuplicate(SQLException e) {
 264 |     return "23505".equals(e.getSQLState()) || e.getErrorCode() == 1062;
 265 |   }
 266 |
 267 |   private void authorize(Actor actor, AppointmentRow row) {
 268 |     require(row != null, 404, "Appointment not found.");
 269 |     require(
 270 |         actor.role().equals("ADMIN")
 271 |             || actor.role().equals("PATIENT") && row.userId() == actor.id()
 272 |             || actor.role().equals("DOCTOR") && row.doctorId() == actor.id(),
 273 |         404,
 274 |         "Appointment not found.");
 275 |   }
 276 |
 277 |   public AppointmentRow detail(Actor actor, int id) {
 278 |     return read(c -> {
 279 |       var row = new AppointmentDao(c).find(id);
 280 |       authorize(actor, row);
 281 |       return row;
 282 |     });
 283 |   }
 284 |
 285 |   public List<AppointmentRow> list(Actor actor, int page) {
 286 |     return read(c -> new AppointmentDao(c).list(actor.role(), actor.id(), page));
 287 |   }
 288 |
 289 |   public List<Note> notes(Actor actor, int id) {
 290 |     return read(c -> {
 291 |       var dao = new AppointmentDao(c);
 292 |       authorize(actor, dao.find(id));
 293 |       return dao.notes(id);
 294 |     });
 295 |   }
 296 |
 297 |   public List<AuditEvent> events(Actor actor, int id) {
 298 |     return read(c -> {
 299 |       var dao = new AppointmentDao(c);
 300 |       authorize(actor, dao.find(id));
 301 |       return dao.events(id);
 302 |     });
 303 |   }
 304 |
 305 |   public List<String> allowedActions(Actor actor, AppointmentRow row) {
 306 |     authorize(actor, row);
 307 |     List<String> actions = new ArrayList<>();
 308 |     LocalDateTime start =
 309 |         row.slotId() == null ? null : LocalDateTime.parse(row.slotTime().replace(' ', 'T'));
 310 |     if (row.isOpen() && (start == null || start.isAfter(now()))) actions.add("CANCELLED");
 311 |     if (!actor.role().equals("PATIENT")
 312 |         && row.status().equals("PENDING")
 313 |         && start != null
 314 |         && start.isAfter(now())) actions.add("CONFIRMED");
 315 |     if (!actor.role().equals("PATIENT")
 316 |         && row.status().equals("CONFIRMED")
 317 |         && start != null
 318 |         && !start.isAfter(now())) actions.add("COMPLETED");
 319 |     return actions;
 320 |   }
 321 |
 322 |   public void transition(Actor actor, int id, String target) {
 323 |     require(
 324 |         Set.of("CONFIRMED", "COMPLETED", "CANCELLED").contains(target),
 325 |         400,
 326 |         "Choose a valid appointment action.");
 327 |     transaction(c -> {
 328 |       var dao = new AppointmentDao(c);
 329 |       var initial = dao.find(id);
 330 |       authorize(actor, initial);
 331 |       lockDoctor(c, initial.doctorId());
 332 |       LockedSlot selected = initial.slotId() == null ? null : slot(c, initial.slotId(), true);
 333 |       try (PreparedStatement ps =
 334 |           c.prepareStatement("SELECT status FROM appointment WHERE id=? FOR UPDATE")) {
 335 |         ps.setInt(1, id);
 336 |         try (ResultSet rs = ps.executeQuery()) {
 337 |           require(rs.next(), 404, "Appointment not found.");
 338 |         }
 339 |       }
 340 |       var current = dao.find(id);
 341 |       authorize(actor, current);
 342 |       if (current.status().equals(target)) return null; // Safe retry of the same state action.
 343 |       boolean patient = actor.role().equals("PATIENT");
 344 |       require(
 345 |           !patient || target.equals("CANCELLED"),
 346 |           403,
 347 |           "Patients can only cancel their own appointments.");
 348 |       if (target.equals("CONFIRMED"))
 349 |         require(
 350 |             current.status().equals("PENDING")
 351 |                 && selected != null
 352 |                 && selected.startsAt().isAfter(now()),
 353 |             409,
 354 |             "Only future pending appointments can be confirmed.");
 355 |       if (target.equals("COMPLETED"))
 356 |         require(
 357 |             !patient
 358 |                 && current.status().equals("CONFIRMED")
 359 |                 && selected != null
 360 |                 && !selected.startsAt().isAfter(now()),
 361 |             409,
 362 |             "Complete a confirmed appointment after its start time.");
 363 |       if (target.equals("CANCELLED"))
 364 |         require(
 365 |             current.isOpen() && (selected == null || selected.startsAt().isAfter(now())),
 366 |             409,
 367 |             "Only upcoming pending or confirmed appointments can be cancelled.");
 368 |       try (PreparedStatement ps =
 369 |           c.prepareStatement("UPDATE appointment SET status=? WHERE id=?")) {
 370 |         ps.setString(1, target);
 371 |         ps.setInt(2, id);
 372 |         ps.executeUpdate();
 373 |       }
 374 |       if (target.equals("CANCELLED")) {
 375 |         try (PreparedStatement ps =
 376 |             c.prepareStatement("DELETE FROM slot_reservation WHERE appointment_id=?")) {
 377 |           ps.setInt(1, id);
 378 |           ps.executeUpdate();
 379 |         }
 380 |       }
 381 |       event(c, id, actor, current.status(), target, "STATUS_CHANGED");
 382 |       return null;
 383 |     });
 384 |   }
 385 |
 386 |   public void addNote(Actor actor, int id, String body) {
 387 |     require(
 388 |         actor.role().equals("DOCTOR"),
 389 |         403,
 390 |         "Only the assigned doctor can write a consultation note.");
 391 |     require(
 392 |         body != null && !body.isBlank() && body.length() <= 1000,
 393 |         400,
 394 |         "Write a note of 1 to 1000 characters.");
 395 |     transaction(c -> {
 396 |       var dao = new AppointmentDao(c);
 397 |       var row = dao.find(id);
 398 |       authorize(actor, row);
 399 |       lockDoctor(c, row.doctorId());
 400 |       try (PreparedStatement ps =
 401 |           c.prepareStatement("SELECT status FROM appointment WHERE id=? FOR UPDATE")) {
 402 |         ps.setInt(1, id);
 403 |         try (ResultSet rs = ps.executeQuery()) {
 404 |           require(rs.next(), 404, "Appointment not found.");
 405 |           require(
 406 |               !rs.getString(1).equals("CANCELLED"),
 407 |               409,
 408 |               "Cancelled appointments cannot receive new notes.");
 409 |         }
 410 |       }
 411 |       try (PreparedStatement ps =
 412 |           c.prepareStatement(
 413 |               "INSERT INTO appointment_note(appointment_id,doctor_id,body) VALUES(?,?,?)")) {
 414 |         ps.setInt(1, id);
 415 |         ps.setInt(2, actor.id());
 416 |         ps.setString(3, body.trim());
 417 |         ps.executeUpdate();
 418 |       }
 419 |       var current = dao.find(id);
 420 |       event(c, id, actor, current.status(), current.status(), "NOTE_ADDED");
 421 |       return null;
 422 |     });
 423 |   }
 424 |
 425 |   public List<Slot> available(Integer doctorId) {
 426 |     return read(c -> {
 427 |       String filter = doctorId == null ? "" : " AND s.doctor_id=?";
 428 |       try (PreparedStatement ps =
 429 |           c.prepareStatement(
 430 |               """
 431 |               SELECT s.id,s.doctor_id,d.full_name,d.specialist,s.starts_at FROM doctor_slot s
 432 |               JOIN doctor d ON d.id=s.doctor_id LEFT JOIN slot_reservation r ON r.slot_id=s.id
 433 |               WHERE s.enabled=TRUE AND d.active=TRUE AND r.slot_id IS NULL AND s.starts_at>?
 434 |               """
 435 |                   + filter
 436 |                   + " ORDER BY s.starts_at,s.doctor_id LIMIT 200")) {
 437 |         ps.setTimestamp(1, Timestamp.valueOf(now()));
 438 |         if (doctorId != null) ps.setInt(2, doctorId);
 439 |         try (ResultSet rs = ps.executeQuery()) {
 440 |           List<Slot> slots = new ArrayList<>();
 441 |           while (rs.next())
 442 |             slots.add(
 443 |                 new Slot(
 444 |                     rs.getLong(1),
 445 |                     rs.getInt(2),
 446 |                     rs.getString(3),
 447 |                     rs.getString(4),
 448 |                     rs.getTimestamp(5).toLocalDateTime()));
 449 |           return slots;
 450 |         }
 451 |       }
 452 |     });
 453 |   }
 454 |
 455 |   public long publish(Actor actor, int doctorId, LocalDateTime start) {
 456 |     require(
 457 |         actor.role().equals("ADMIN") || actor.role().equals("DOCTOR") && actor.id() == doctorId,
 458 |         403,
 459 |         "You cannot publish slots for this doctor.");
 460 |     require(
 461 |         start != null
 462 |             && start.isAfter(now())
 463 |             && !start.isAfter(now().plusDays(90))
 464 |             && start.getMinute() % 30 == 0
 465 |             && start.getSecond() == 0
 466 |             && start.getNano() == 0,
 467 |         400,
 468 |         "Choose a future half-hour start within 90 days.");
 469 |     try {
 470 |       return transaction(c -> {
 471 |         lockDoctor(c, doctorId);
 472 |         try (PreparedStatement ps =
 473 |             c.prepareStatement(
 474 |                 "INSERT INTO doctor_slot(doctor_id,starts_at) VALUES(?,?)",
 475 |                 Statement.RETURN_GENERATED_KEYS)) {
 476 |           ps.setInt(1, doctorId);
 477 |           ps.setTimestamp(2, Timestamp.valueOf(start));
 478 |           ps.executeUpdate();
 479 |           try (ResultSet rs = ps.getGeneratedKeys()) {
 480 |             rs.next();
 481 |             return rs.getLong(1);
 482 |           }
 483 |         }
 484 |       });
 485 |     } catch (DataAccessException e) {
 486 |       if (e.getCause() instanceof SQLException sql && isDuplicate(sql))
 487 |         throw new ServiceException(409, "This slot already exists.");
 488 |       throw e;
 489 |     }
 490 |   }
 491 |
 492 |   public void disable(Actor actor, long slotId) {
 493 |     transaction(c -> {
 494 |       LockedSlot initial = slot(c, slotId, false);
 495 |       require(
 496 |           actor.role().equals("ADMIN")
 497 |               || actor.role().equals("DOCTOR") && actor.id() == initial.doctorId(),
 498 |           404,
 499 |           "Slot not found.");
 500 |       lockDoctor(c, initial.doctorId());
 501 |       slot(c, slotId, true);
 502 |       try (PreparedStatement ps =
 503 |           c.prepareStatement("SELECT appointment_id FROM slot_reservation WHERE slot_id=?")) {
 504 |         ps.setLong(1, slotId);
 505 |         try (ResultSet rs = ps.executeQuery()) {
 506 |           require(!rs.next(), 409, "Cancel the appointment before withdrawing its slot.");
 507 |         }
 508 |       }
 509 |       try (PreparedStatement ps =
 510 |           c.prepareStatement("UPDATE doctor_slot SET enabled=FALSE WHERE id=?")) {
 511 |         ps.setLong(1, slotId);
 512 |         ps.executeUpdate();
 513 |       }
 514 |       return null;
 515 |     });
 516 |   }
 517 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 8 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 9 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 11 | Source comment/documentation; TODO statements describe unfinished work. |
| 12 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 13 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 14 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 16 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 17 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 18 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 21 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 22 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 23 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 24 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 25 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 26 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 27 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 28 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 29 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 30 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 32 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 33 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 34 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 37 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 38 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 39 | Begins manual control of a transaction containing multiple writes. |
| 40 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 41 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 42 | Commits the grouped writes. |
| 43 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 44 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 45 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 46 | Rolls back the grouped writes on failure. |
| 47 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 48 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 50 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 52 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 53 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 57 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 58 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 59 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 60 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 61 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 65 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 66 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 69 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 70 | Branches on the shown condition; be able to explain both the success and rejection path. Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 73 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 74 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 75 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 76 | Binds parameter 1 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 77 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 78 | Advances the cursor and checks whether a row exists. Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 83 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 85 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 86 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 87 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 88 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 89 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 90 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 91 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 92 | Advances the cursor and checks whether a row exists. |
| 93 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 98 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 99 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 100 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 101 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 102 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 103 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 104 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 105 | Binds parameter 1 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 106 | Binds parameter 2 (JDBC indexes start at 1) to actor.role(). Match the SQL placeholder order. |
| 107 | Binds parameter 3 (JDBC indexes start at 1) to actor.id(). Match the SQL placeholder order. |
| 108 | Binds parameter 4 (JDBC indexes start at 1) to from. Match the SQL placeholder order. |
| 109 | Binds parameter 5 (JDBC indexes start at 1) to to. Match the SQL placeholder order. |
| 110 | Binds parameter 6 (JDBC indexes start at 1) to action. Match the SQL placeholder order. |
| 111 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 115 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 116 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 117 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 118 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 119 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 120 | Iterates the listed values/collection and executes the enclosed operations for each item. |
| 121 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 122 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 123 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 125 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 126 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 127 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 128 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 132 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 133 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 134 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 135 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 136 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 137 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 138 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 140 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 141 | Counts one row in Java. A COUNT(*) query would avoid transferring every row. |
| 142 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 143 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 144 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 145 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 146 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 147 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 148 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 149 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 150 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 151 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 152 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 153 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 154 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 155 | Parses an integer and may raise NumberFormatException, a subtype of IllegalArgumentException. |
| 156 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 157 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 159 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 160 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 161 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 164 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 165 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 166 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 167 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 168 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 169 | Source comment/documentation; TODO statements describe unfinished work. |
| 170 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 171 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 172 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 173 | Binds parameter 1 (JDBC indexes start at 1) to userId. Match the SQL placeholder order. |
| 174 | Binds parameter 2 (JDBC indexes start at 1) to booking.requestKey(). Match the SQL placeholder order. |
| 175 | Binds parameter 3 (JDBC indexes start at 1) to digest. Match the SQL placeholder order. |
| 176 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 178 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 179 | Source comment/documentation; TODO statements describe unfinished work. |
| 180 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 181 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 182 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 183 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 184 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 185 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 186 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 187 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 188 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 189 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 190 | Advances the cursor and checks whether a row exists. |
| 193 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 194 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 195 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 196 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 197 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 198 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 199 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 200 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 201 | Binds parameter 1 (JDBC indexes start at 1) to userId. Match the SQL placeholder order. |
| 202 | Binds parameter 2 (JDBC indexes start at 1) to booking.fullName(). Match the SQL placeholder order. |
| 203 | Binds parameter 3 (JDBC indexes start at 1) to booking.gender(). Match the SQL placeholder order. |
| 204 | Binds parameter 4 (JDBC indexes start at 1) to booking.age(). Match the SQL placeholder order. |
| 205 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 206 | Binds parameter 6 (JDBC indexes start at 1) to booking.email(). Match the SQL placeholder order. |
| 207 | Binds parameter 7 (JDBC indexes start at 1) to booking.phone(). Match the SQL placeholder order. |
| 208 | Binds parameter 8 (JDBC indexes start at 1) to booking.reason(). Match the SQL placeholder order. |
| 209 | Binds parameter 9 (JDBC indexes start at 1) to selected.doctorId(). Match the SQL placeholder order. |
| 210 | Binds parameter 10 (JDBC indexes start at 1) to booking.address(). Match the SQL placeholder order. |
| 211 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 212 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 213 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 214 | Advances the cursor and checks whether a row exists. Branches on the shown condition; be able to explain both the success and rejection path. Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 215 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 218 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 219 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 220 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 221 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 222 | Binds parameter 2 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 223 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 225 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 226 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 227 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 228 | Binds parameter 1 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 229 | Binds parameter 2 (JDBC indexes start at 1) to userId. Match the SQL placeholder order. |
| 230 | Binds parameter 3 (JDBC indexes start at 1) to booking.requestKey(). Match the SQL placeholder order. |
| 231 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 233 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 234 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 236 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 237 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 238 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 239 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 240 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 241 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 242 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 243 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 244 | Binds parameter 1 (JDBC indexes start at 1) to userId. Match the SQL placeholder order. |
| 245 | Binds parameter 2 (JDBC indexes start at 1) to booking.requestKey(). Match the SQL placeholder order. |
| 246 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 247 | Advances the cursor and checks whether a row exists. Branches on the shown condition; be able to explain both the success and rejection path. |
| 248 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 249 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 250 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 251 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 252 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 256 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 257 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 259 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 263 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 264 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 267 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 268 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 269 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 270 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 271 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 272 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 273 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 274 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 277 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 278 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 279 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 280 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 281 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 285 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 286 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 289 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 290 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 291 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 292 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 293 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 297 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 298 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 299 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 300 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 301 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 305 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 306 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 307 | Allocates the initially empty collection of mapped objects. |
| 308 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 309 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 310 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 311 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 312 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 313 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 314 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 315 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 316 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 317 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 318 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 319 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 322 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 323 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 324 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 325 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 326 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 327 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 328 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 329 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 330 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 331 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 332 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 333 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 334 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 335 | Binds parameter 1 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 336 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 337 | Advances the cursor and checks whether a row exists. |
| 340 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 341 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 342 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 343 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 344 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 345 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 346 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 347 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 348 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 349 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 350 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 351 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 352 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 353 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 354 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 355 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 356 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 357 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 358 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 359 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 360 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 361 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 362 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 363 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 364 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 365 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 366 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 367 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 368 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 369 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 370 | Binds parameter 1 (JDBC indexes start at 1) to target. Match the SQL placeholder order. |
| 371 | Binds parameter 2 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 372 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 374 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 375 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 376 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 377 | Binds parameter 1 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 378 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 381 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 382 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 386 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 387 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 388 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 389 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 390 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 391 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 392 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 393 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 394 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 395 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 396 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 397 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 398 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 399 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 400 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 401 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 402 | Binds parameter 1 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 403 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 404 | Advances the cursor and checks whether a row exists. |
| 405 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 406 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 407 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 408 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 411 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 412 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 413 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 414 | Binds parameter 1 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 415 | Binds parameter 2 (JDBC indexes start at 1) to actor.id(). Match the SQL placeholder order. |
| 416 | Binds parameter 3 (JDBC indexes start at 1) to body.trim(). Match the SQL placeholder order. |
| 417 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 419 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 420 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 421 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 425 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 426 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 427 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 428 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 429 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 430 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 431 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 432 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 433 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 434 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 435 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 436 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 437 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 438 | Binds parameter 2 (JDBC indexes start at 1) to doctorId. Match the SQL placeholder order. Branches on the shown condition; be able to explain both the success and rejection path. |
| 439 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 440 | Allocates the initially empty collection of mapped objects. |
| 441 | Advances the cursor and checks whether a row exists. |
| 442 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 443 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 444 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 445 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 446 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 447 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 448 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 449 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 455 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 456 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 457 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 458 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 459 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 460 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 461 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 462 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 463 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 464 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 465 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 466 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 467 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 468 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 469 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 470 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 471 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 472 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 473 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 474 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 475 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 476 | Binds parameter 1 (JDBC indexes start at 1) to doctorId. Match the SQL placeholder order. |
| 477 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 478 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 479 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 480 | Advances the cursor and checks whether a row exists. |
| 481 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 485 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 486 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 487 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 488 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 492 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 493 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 494 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 495 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 496 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 497 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 498 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 499 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 500 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 501 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 502 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 503 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 504 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 505 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 506 | Advances the cursor and checks whether a row exists. |
| 509 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 510 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 511 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 512 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 514 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |

<a id="file-37"></a>

## 37. src/main/java/com/service/DataAccessException.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `3415dc9ee282b0f4e4e414b3b966229e010c90aaef8202a1b0bf955520963119`

<details>
<summary>Numbered source (7 physical lines)</summary>

~~~text
   1 | package com.service;
   2 |
   3 | public final class DataAccessException extends RuntimeException {
   4 |   public DataAccessException(Throwable cause) {
   5 |     super("The database operation could not be completed.", cause);
   6 |   }
   7 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 4 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 5 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |

<a id="file-38"></a>

## 38. src/main/java/com/service/HospitalService.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `b12a3cce61fddaee78981734464d1122c1af177cbea7398bb455bff204c7825f`

<details>
<summary>Numbered source (206 physical lines)</summary>

~~~text
   1 | package com.service;
   2 |
   3 | import com.dao.*;
   4 | import com.db.DBConnect;
   5 | import com.entity.*;
   6 | import com.security.Passwords;
   7 | import java.nio.charset.StandardCharsets;
   8 | import java.security.MessageDigest;
   9 | import java.sql.*;
  10 | import java.time.*;
  11 | import java.util.*;
  12 |
  13 | /** Application facade: controllers never open a connection or execute SQL. */
  14 | public final class HospitalService {
  15 |   public static ZoneId zone() {
  16 |     return ZoneId.of(DBConnect.config("HOSPITAL_ZONE", "Asia/Kolkata"));
  17 |   }
  18 |
  19 |   public static AppointmentService appointments() {
  20 |     return new AppointmentService(DBConnect.dataSource(), Clock.system(zone()));
  21 |   }
  22 |
  23 |   @FunctionalInterface
  24 |   private interface Work<T> {
  25 |     T run(Connection c) throws SQLException;
  26 |   }
  27 |
  28 |   private static <T> T read(Work<T> work) {
  29 |     try (Connection c = DBConnect.open()) {
  30 |       return work.run(c);
  31 |     } catch (SQLException e) {
  32 |       throw new DataAccessException(e);
  33 |     }
  34 |   }
  35 |
  36 |   public static User loginPatient(String email, String password) {
  37 |     return read(c -> new UserDao(c).login(email, password));
  38 |   }
  39 |
  40 |   public static Doctor loginDoctor(String email, String password) {
  41 |     return read(c -> new DoctorDao(c).login(email, password));
  42 |   }
  43 |
  44 |   public static void register(User user) {
  45 |     try {
  46 |       read(c -> new UserDao(c).register(user));
  47 |     } catch (DataAccessException e) {
  48 |       if (e.getCause() instanceof SQLException sql && AppointmentService.isDuplicate(sql))
  49 |         throw new ServiceException(
  50 |             409, "An account already uses this email. Sign in or use another email.");
  51 |       throw e;
  52 |     }
  53 |   }
  54 |
  55 |   public static List<Doctor> doctors(boolean archived) {
  56 |     return read(c -> new DoctorDao(c).list(archived));
  57 |   }
  58 |
  59 |   public static Doctor doctor(int id) {
  60 |     return read(c -> {
  61 |       Doctor doctor = new DoctorDao(c).find(id);
  62 |       if (doctor == null) throw new ServiceException(404, "Doctor not found.");
  63 |       return doctor;
  64 |     });
  65 |   }
  66 |
  67 |   public static List<Specalist> specialties() {
  68 |     return read(c -> new SpecialistDao(c).list());
  69 |   }
  70 |
  71 |   public static Map<String, Long> counts(Integer doctor) {
  72 |     return read(c -> new DoctorDao(c).counts(doctor));
  73 |   }
  74 |
  75 |   public static void specialty(String name) {
  76 |     try {
  77 |       read(c -> {
  78 |         new SpecialistDao(c).add(name);
  79 |         return null;
  80 |       });
  81 |     } catch (DataAccessException e) {
  82 |       if (e.getCause() instanceof SQLException sql && AppointmentService.isDuplicate(sql))
  83 |         throw new ServiceException(409, "This specialty already exists.");
  84 |       throw e;
  85 |     }
  86 |   }
  87 |
  88 |   public static void saveDoctor(Doctor d, boolean create, boolean profileOnly) {
  89 |     try {
  90 |       read(c -> {
  91 |         try (PreparedStatement ps =
  92 |             c.prepareStatement("SELECT id FROM specialist WHERE spec_name=?")) {
  93 |           ps.setString(1, d.getSpecialist());
  94 |           try (ResultSet rs = ps.executeQuery()) {
  95 |             if (!rs.next()) throw new ServiceException(400, "Choose an existing specialty.");
  96 |           }
  97 |         }
  98 |         if (!new DoctorDao(c).save(d, create, profileOnly))
  99 |           throw new ServiceException(404, "Doctor not found or archived.");
 100 |         return null;
 101 |       });
 102 |     } catch (DataAccessException e) {
 103 |       if (e.getCause() instanceof SQLException sql && AppointmentService.isDuplicate(sql))
 104 |         throw new ServiceException(409, "A doctor already uses this email.");
 105 |       throw e;
 106 |     }
 107 |   }
 108 |
 109 |   public static void archiveDoctor(int id) {
 110 |     try (Connection c = DBConnect.open()) {
 111 |       c.setAutoCommit(false);
 112 |       try {
 113 |         try (PreparedStatement ps =
 114 |             c.prepareStatement("SELECT id FROM doctor WHERE id=? FOR UPDATE")) {
 115 |           ps.setInt(1, id);
 116 |           try (ResultSet rs = ps.executeQuery()) {
 117 |             if (!rs.next()) throw new ServiceException(404, "Doctor not found.");
 118 |           }
 119 |         }
 120 |         try (PreparedStatement ps =
 121 |             c.prepareStatement(
 122 |                 "SELECT COUNT(*) FROM appointment WHERE doctor_id=? AND status IN"
 123 |                     + " ('PENDING','CONFIRMED')")) {
 124 |           ps.setInt(1, id);
 125 |           try (ResultSet rs = ps.executeQuery()) {
 126 |             rs.next();
 127 |             if (rs.getInt(1) > 0)
 128 |               throw new ServiceException(
 129 |                   409, "Cancel or finish this doctor's open appointments before archiving.");
 130 |           }
 131 |         }
 132 |         try (PreparedStatement ps =
 133 |             c.prepareStatement(
 134 |                 "UPDATE doctor SET active=FALSE,auth_version=auth_version+1 WHERE id=?")) {
 135 |           ps.setInt(1, id);
 136 |           ps.executeUpdate();
 137 |         }
 138 |         try (PreparedStatement ps =
 139 |             c.prepareStatement("UPDATE doctor_slot SET enabled=FALSE WHERE doctor_id=?")) {
 140 |           ps.setInt(1, id);
 141 |           ps.executeUpdate();
 142 |         }
 143 |         c.commit();
 144 |       } catch (SQLException | RuntimeException e) {
 145 |         c.rollback();
 146 |         throw e;
 147 |       }
 148 |     } catch (SQLException e) {
 149 |       throw new DataAccessException(e);
 150 |     }
 151 |   }
 152 |
 153 |   public static boolean changePassword(Actor actor, String old, String replacement) {
 154 |     return read(c ->
 155 |         actor.role().equals("PATIENT")
 156 |             ? new UserDao(c).changePassword(actor.id(), old, replacement)
 157 |             : new DoctorDao(c).changePassword(actor.id(), old, replacement));
 158 |   }
 159 |
 160 |   public static boolean validSession(Actor actor, int version) {
 161 |     return read(c -> {
 162 |       String table = actor.role().equals("PATIENT") ? "user_dtls" : "doctor";
 163 |       try (PreparedStatement ps =
 164 |           c.prepareStatement(
 165 |               "SELECT auth_version FROM "
 166 |                   + table
 167 |                   + " WHERE id=?"
 168 |                   + (table.equals("doctor") ? " AND active=TRUE" : ""))) {
 169 |         ps.setInt(1, actor.id());
 170 |         try (ResultSet rs = ps.executeQuery()) {
 171 |           return rs.next() && rs.getInt(1) == version;
 172 |         }
 173 |       }
 174 |     });
 175 |   }
 176 |
 177 |   public static boolean adminLogin(String email, String password) {
 178 |     boolean demo = Boolean.parseBoolean(DBConnect.config("DEMO_MODE", "false"));
 179 |     String expected = DBConnect.config("ADMIN_EMAIL", demo ? "admin@hospital.test" : "");
 180 |     String hash = DBConnect.config("ADMIN_PASSWORD_HASH", "");
 181 |     if (expected.isBlank() || !expected.equals(email)) return false;
 182 |     if (!hash.isBlank()) return Passwords.verify(password, hash);
 183 |     return demo
 184 |         && password != null
 185 |         && MessageDigest.isEqual(
 186 |             DBConnect.config("ADMIN_PASSWORD", "Admin@123").getBytes(StandardCharsets.UTF_8),
 187 |             password.getBytes(StandardCharsets.UTF_8));
 188 |   }
 189 |
 190 |   public static String adminStamp() {
 191 |     try {
 192 |       return HexFormat.of()
 193 |           .formatHex(
 194 |               MessageDigest.getInstance("SHA-256")
 195 |                   .digest(
 196 |                       (DBConnect.config("ADMIN_EMAIL", "admin@hospital.test")
 197 |                               + "|"
 198 |                               + DBConnect.config("ADMIN_PASSWORD_HASH", "")
 199 |                               + "|"
 200 |                               + DBConnect.config("ADMIN_PASSWORD", ""))
 201 |                           .getBytes(StandardCharsets.UTF_8)));
 202 |     } catch (Exception e) {
 203 |       throw new IllegalStateException(e);
 204 |     }
 205 |   }
 206 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 8 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 9 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 10 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 11 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 13 | Source comment/documentation; TODO statements describe unfinished work. |
| 14 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 15 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 16 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 19 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 20 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 23 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 24 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 25 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 28 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 29 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 30 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 31 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 32 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 36 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 37 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 40 | Declares loginDoctor: Read a doctor by email, verify its password representation, then return its non-secret profile. |
| 41 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 44 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 45 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 46 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 47 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 48 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 49 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 50 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 51 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 55 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 56 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 59 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 60 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 61 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 62 | Branches on the shown condition; be able to explain both the success and rejection path. Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 63 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 67 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 68 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 71 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 72 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 75 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 76 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 77 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 78 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 79 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 81 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 82 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 83 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 84 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 88 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 89 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 90 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 91 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 92 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 93 | Binds parameter 1 (JDBC indexes start at 1) to d.getSpecialist(). Match the SQL placeholder order. |
| 94 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 95 | Advances the cursor and checks whether a row exists. Branches on the shown condition; be able to explain both the success and rejection path. Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 98 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 99 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 100 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 102 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 103 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 104 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 105 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 109 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 110 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 111 | Begins manual control of a transaction containing multiple writes. |
| 112 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 113 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 114 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 115 | Binds parameter 1 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 116 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 117 | Advances the cursor and checks whether a row exists. Branches on the shown condition; be able to explain both the success and rejection path. Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 120 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 121 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 122 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 123 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 124 | Binds parameter 1 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 125 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 126 | Advances the cursor and checks whether a row exists. |
| 127 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Branches on the shown condition; be able to explain both the success and rejection path. |
| 128 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 129 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 132 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 133 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 134 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 135 | Binds parameter 1 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 136 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 138 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 139 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. Check whether doctor_id is an ownership predicate, as in the consultation UPDATE. |
| 140 | Binds parameter 1 (JDBC indexes start at 1) to id. Match the SQL placeholder order. |
| 141 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 143 | Commits the grouped writes. |
| 144 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 145 | Rolls back the grouped writes on failure. |
| 146 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 148 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 149 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 153 | Declares changePassword: Store a newly salted password hash for the account ID supplied by the trusted servlet. |
| 154 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 155 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 156 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 157 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 160 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 161 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 162 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 163 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 164 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 165 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 166 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 167 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 168 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 169 | Binds parameter 1 (JDBC indexes start at 1) to actor.id(). Match the SQL placeholder order. |
| 170 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 171 | Advances the cursor and checks whether a row exists. Reads the current database row. Numeric column indexes depend on SELECT/schema order. Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 177 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 178 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 179 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 180 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 181 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 182 | Verifies using the salt and work factor embedded in the stored representation. Branches on the shown condition; be able to explain both the success and rejection path. |
| 183 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 184 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 185 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 186 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 187 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 190 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 191 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 192 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 193 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 194 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 195 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 196 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 197 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 198 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 199 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 200 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 201 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 202 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 203 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |

<a id="file-39"></a>

## 39. src/main/java/com/service/ServiceException.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `9c42099e2e2421ba0ae22e1b7c23b0052d9042b867986f4c15fc2701fec448fc`

<details>
<summary>Numbered source (15 physical lines)</summary>

~~~text
   1 | package com.service;
   2 |
   3 | /** Expected domain/validation failure, distinct from a database outage. */
   4 | public final class ServiceException extends RuntimeException {
   5 |   private final int status;
   6 |
   7 |   public ServiceException(int status, String message) {
   8 |     super(message);
   9 |     this.status = status;
  10 |   }
  11 |
  12 |   public int getStatus() {
  13 |     return status;
  14 |   }
  15 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Source comment/documentation; TODO statements describe unfinished work. |
| 4 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 5 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 7 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 8 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 9 | Assigns the incoming parameter to the current object field; this distinguishes the field from the same-named parameter. |
| 12 | JavaBean getter for Status. JSP property syntax can resolve the corresponding getter. |
| 13 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |

<a id="file-40"></a>

## 40. src/main/java/com/user/servlet/Appointment.java

Validates booking fields and gets the account owner from the authenticated session.

Source SHA-256: `a1f1ea61e1a2d417eb2869b3f9d8de55a5efbbd64a4b1a02ad06a17bed49e415`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | package com.user.servlet;
   2 |
   3 | import jakarta.servlet.annotation.WebServlet;
   4 |
   5 | @WebServlet("/appAppointment")
   6 | public final class Appointment extends com.web.ActionController {}
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Registers this HTTP servlet at the quoted application-relative URL. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |

<a id="file-41"></a>

## 41. src/main/java/com/user/servlet/ChangePassword.java

Patient password change with session identity and current-password verification.

Source SHA-256: `5d0d540579792c0c557a6bb250d7494b3a5424ff96c1854eab2919a32cbd9d86`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | package com.user.servlet;
   2 |
   3 | import jakarta.servlet.annotation.WebServlet;
   4 |
   5 | @WebServlet("/userChangePassword")
   6 | public final class ChangePassword extends com.web.ActionController {}
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Registers this HTTP servlet at the quoted application-relative URL. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |

<a id="file-42"></a>

## 42. src/main/java/com/user/servlet/UserLogin.java

Patient authentication, session rotation and establishment of userObj.

Source SHA-256: `f78f60835ec69b5aaf0cd9f76a0d2a1cffae04572affbd1bbf6c3e7c4d39cd7a`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | package com.user.servlet;
   2 |
   3 | import jakarta.servlet.annotation.WebServlet;
   4 |
   5 | @WebServlet("/userLogin")
   6 | public final class UserLogin extends com.web.ActionController {}
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Registers this HTTP servlet at the quoted application-relative URL. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |

<a id="file-43"></a>

## 43. src/main/java/com/user/servlet/UserLogout.java

Removes the relevant role marker through inherited GET logout; does not invalidate the entire session.

Source SHA-256: `665e0d64f7998177c556ef8ffe0f626dc3cd95ba30cce5fbdfe96bdbbd17abc1`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | package com.user.servlet;
   2 |
   3 | import jakarta.servlet.annotation.WebServlet;
   4 |
   5 | @WebServlet("/userLogout")
   6 | public final class UserLogout extends com.web.ActionController {}
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Registers this HTTP servlet at the quoted application-relative URL. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |

<a id="file-44"></a>

## 44. src/main/java/com/user/servlet/UserRegister.java

Patient validation, account creation and Post/Redirect/Get feedback.

Source SHA-256: `958be0574077db496e79aaa95bd18ece00bf6fd1470dac54be7805d9686e47bd`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | package com.user.servlet;
   2 |
   3 | import jakarta.servlet.annotation.WebServlet;
   4 |
   5 | @WebServlet("/register")
   6 | public final class UserRegister extends com.web.ActionController {}
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Registers this HTTP servlet at the quoted application-relative URL. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |

<a id="file-45"></a>

## 45. src/main/java/com/web/ActionController.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `9c9bff9bc2f297320ab401a4431f3ae1b2bafc507d8dfb8555728f832967b307`

<details>
<summary>Numbered source (207 physical lines)</summary>

~~~text
   1 | package com.web;
   2 |
   3 | import com.entity.*;
   4 | import com.security.*;
   5 | import com.service.*;
   6 | import jakarta.servlet.ServletException;
   7 | import jakarta.servlet.http.*;
   8 | import java.io.IOException;
   9 | import java.time.*;
  10 | import java.util.*;
  11 |
  12 | public abstract class ActionController extends HttpServlet {
  13 |   private void redirect(HttpServletRequest req, HttpServletResponse resp, String path) {
  14 |     resp.setStatus(303);
  15 |     resp.setHeader("Location", req.getContextPath() + "/" + path);
  16 |   }
  17 |
  18 |   private void establish(HttpServletRequest req, String marker, Object principal) {
  19 |     req.changeSessionId();
  20 |     for (String key : List.of("adminObj", "userObj", "docObj"))
  21 |       req.getSession().removeAttribute(key);
  22 |     req.getSession().setAttribute(marker, principal);
  23 |     req.getSession().setAttribute("csrfToken", UUID.randomUUID().toString());
  24 |   }
  25 |
  26 |   @Override
  27 |   protected final void doPost(HttpServletRequest req, HttpServletResponse resp)
  28 |       throws IOException, ServletException {
  29 |     String path = req.getServletPath();
  30 |     String destination;
  31 |     try {
  32 |       switch (path) {
  33 |         case "/register" -> {
  34 |           HospitalService.register(
  35 |               new User(
  36 |                   Inputs.required(req, "Fullname", 120),
  37 |                   Inputs.email(req),
  38 |                   Inputs.password(req, "password")));
  39 |           req.getSession().setAttribute("sucMsg", "Your account is ready. Sign in to book.");
  40 |           destination = "user_Login.jsp";
  41 |         }
  42 |         case "/userLogin", "/loginDoctor", "/adminLogin" -> {
  43 |           String email = Inputs.email(req);
  44 |           String password = req.getParameter("password");
  45 |           if (password == null || password.length() > 128)
  46 |             throw new ServiceException(401, "Email or password is incorrect.");
  47 |           LoginLimiter.check(req.getRemoteAddr(), email);
  48 |           Object principal =
  49 |               path.equals("/userLogin")
  50 |                   ? HospitalService.loginPatient(email, password)
  51 |                   : path.equals("/loginDoctor")
  52 |                       ? HospitalService.loginDoctor(email, password)
  53 |                       : HospitalService.adminLogin(email, password) ? new User() : null;
  54 |           if (principal == null) {
  55 |             LoginLimiter.failed(req.getRemoteAddr(), email);
  56 |             throw new ServiceException(401, "Email or password is incorrect.");
  57 |           }
  58 |           LoginLimiter.succeeded(req.getRemoteAddr(), email);
  59 |           String marker =
  60 |               path.equals("/userLogin")
  61 |                   ? "userObj"
  62 |                   : path.equals("/loginDoctor") ? "docObj" : "adminObj";
  63 |           establish(req, marker, principal);
  64 |           if (marker.equals("adminObj"))
  65 |             req.getSession().setAttribute("adminStamp", HospitalService.adminStamp());
  66 |           destination =
  67 |               marker.equals("userObj")
  68 |                   ? "view_appointment.jsp"
  69 |                   : marker.equals("docObj") ? "doctor/index.jsp" : "admin/index.jsp";
  70 |         }
  71 |         case "/appAppointment" -> {
  72 |           Actor actor = PageController.actor(req);
  73 |           long slot;
  74 |           try {
  75 |             slot = Long.parseLong(Inputs.required(req, "slotId", 20));
  76 |           } catch (NumberFormatException e) {
  77 |             throw new ServiceException(400, "Choose a published slot.");
  78 |           }
  79 |           var booking =
  80 |               new AppointmentService.Booking(
  81 |                   slot,
  82 |                   Inputs.required(req, "requestKey", 64),
  83 |                   Inputs.required(req, "fullname", 120),
  84 |                   Inputs.required(req, "gender", 30),
  85 |                   Inputs.required(req, "age", 3),
  86 |                   Inputs.email(req),
  87 |                   Inputs.required(req, "phno", 20),
  88 |                   Inputs.required(req, "diseases", 500),
  89 |                   Inputs.required(req, "address", 500));
  90 |           int id = HospitalService.appointments().book(actor.id(), booking);
  91 |           req.getSession().removeAttribute("appointmentForm");
  92 |           req.getSession()
  93 |               .setAttribute(
  94 |                   "succMsg", "Slot reserved. Your appointment is pending doctor confirmation.");
  95 |           destination = "appointment.jsp?id=" + id;
  96 |         }
  97 |         case "/appointmentState" -> {
  98 |           Actor actor = PageController.actor(req);
  99 |           int id = PageController.number(req, "id", 0);
 100 |           HospitalService.appointments().transition(actor, id, Inputs.required(req, "status", 16));
 101 |           req.getSession().setAttribute("succMsg", "Appointment status updated.");
 102 |           destination =
 103 |               actor.role().equals("PATIENT")
 104 |                   ? "appointment.jsp?id=" + id
 105 |                   : actor.role().equals("DOCTOR")
 106 |                       ? "doctor/comment.jsp?id=" + id
 107 |                       : "admin/appointment.jsp?id=" + id;
 108 |         }
 109 |         case "/updateStatus" -> {
 110 |           int id = PageController.number(req, "id", 0);
 111 |           HospitalService.appointments()
 112 |               .addNote(PageController.actor(req), id, Inputs.required(req, "comm", 1000));
 113 |           req.getSession().setAttribute("succMsg", "Consultation note added to the history.");
 114 |           destination = "doctor/comment.jsp?id=" + id;
 115 |         }
 116 |         case "/publishSlot" -> {
 117 |           Actor actor = PageController.actor(req);
 118 |           int doctor =
 119 |               actor.role().equals("DOCTOR")
 120 |                   ? actor.id()
 121 |                   : PageController.number(req, "doctorId", 0);
 122 |           LocalDateTime start;
 123 |           try {
 124 |             start = LocalDateTime.parse(Inputs.required(req, "startsAt", 25));
 125 |           } catch (java.time.format.DateTimeParseException e) {
 126 |             throw new ServiceException(400, "Choose a valid date and half-hour start.");
 127 |           }
 128 |           HospitalService.appointments().publish(actor, doctor, start);
 129 |           req.getSession().setAttribute("succMsg", "Half-hour slot published.");
 130 |           destination = actor.role().equals("DOCTOR") ? "doctor/slots.jsp" : "admin/slots.jsp";
 131 |         }
 132 |         case "/withdrawSlot" -> {
 133 |           Actor actor = PageController.actor(req);
 134 |           HospitalService.appointments()
 135 |               .disable(actor, Long.parseLong(Inputs.required(req, "slotId", 20)));
 136 |           req.getSession().setAttribute("succMsg", "Slot withdrawn.");
 137 |           destination = actor.role().equals("DOCTOR") ? "doctor/slots.jsp" : "admin/slots.jsp";
 138 |         }
 139 |         case "/userChangePassword", "/docChangePassword" -> {
 140 |           if (!HospitalService.changePassword(
 141 |               PageController.actor(req),
 142 |               req.getParameter("oldPassword"),
 143 |               Inputs.password(req, "newPassword")))
 144 |             throw new ServiceException(
 145 |                 400, "The current password is incorrect or has changed. Try again.");
 146 |           boolean patient = path.equals("/userChangePassword");
 147 |           req.getSession().invalidate();
 148 |           req.getSession(true)
 149 |               .setAttribute(
 150 |                   "sucMsg",
 151 |                   "Password changed. Sign in again; previous sessions have been revoked.");
 152 |           destination = patient ? "user_Login.jsp" : "doctor.jsp";
 153 |         }
 154 |         case "/addDoctor", "/updateDoctor", "/doctorUpdateProfile" -> {
 155 |           boolean create = path.equals("/addDoctor"), profile = path.equals("/doctorUpdateProfile");
 156 |           int id =
 157 |               create
 158 |                   ? 0
 159 |                   : profile ? PageController.actor(req).id() : PageController.number(req, "id", 0);
 160 |           HospitalService.saveDoctor(
 161 |               DoctorInputs.read(req, id, profile ? "spec" : "specialist", create), create, profile);
 162 |           if (profile) req.getSession().setAttribute("docObj", HospitalService.doctor(id));
 163 |           req.getSession().setAttribute("sucMsg", "Doctor profile saved.");
 164 |           destination = profile ? "doctor/edit_profile.jsp" : "admin/view_doctor.jsp";
 165 |         }
 166 |         case "/delete_doctor" -> {
 167 |           HospitalService.archiveDoctor(PageController.number(req, "id", 0));
 168 |           req.getSession()
 169 |               .setAttribute("sucMsg", "Doctor archived. Historical records remain available.");
 170 |           destination = "admin/view_doctor.jsp";
 171 |         }
 172 |         case "/addSpecialist" -> {
 173 |           HospitalService.specialty(Inputs.required(req, "specName", 120));
 174 |           req.getSession().setAttribute("sucMsg", "Specialty added.");
 175 |           destination = "admin/index.jsp";
 176 |         }
 177 |         case "/userLogout", "/docLogout", "/adminLogout" -> {
 178 |           req.getSession().invalidate();
 179 |           destination =
 180 |               path.equals("/userLogout")
 181 |                   ? "user_Login.jsp"
 182 |                   : path.equals("/docLogout") ? "doctor.jsp" : "admin.jsp";
 183 |         }
 184 |         default -> throw new ServiceException(404, "Action not found.");
 185 |       }
 186 |       redirect(req, resp, destination);
 187 |     } catch (IllegalArgumentException e) {
 188 |       remember(req, path);
 189 |       throw new ServiceException(
 190 |           400, e.getMessage() == null ? "Invalid form input." : e.getMessage());
 191 |     } catch (ServiceException e) {
 192 |       remember(req, path);
 193 |       throw e;
 194 |     }
 195 |   }
 196 |
 197 |   private void remember(HttpServletRequest req, String path) {
 198 |     if (!path.equals("/appAppointment")) return;
 199 |     Map<String, String> form = new HashMap<>();
 200 |     for (String field :
 201 |         List.of("fullname", "gender", "age", "email", "phno", "diseases", "address")) {
 202 |       String value = req.getParameter(field);
 203 |       if (value != null) form.put(field, value.substring(0, Math.min(500, value.length())));
 204 |     }
 205 |     req.getSession().setAttribute("appointmentForm", form);
 206 |   }
 207 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 8 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 9 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 10 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 12 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 13 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 14 | Maps data into a plain Java object; the setter itself does not write SQL. |
| 15 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 18 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 19 | Rotates the session identifier after authentication while preserving attributes. |
| 20 | Iterates the listed values/collection and executes the enclosed operations for each item. |
| 21 | Uses the server session identified by the browser cookie; attributes survive redirects. Removes the named session marker/value. It does not invalidate the entire session. |
| 22 | Uses the server session identified by the browser cookie; attributes survive redirects. Exposes named request/session state; flash values use session scope to survive the redirect. |
| 23 | Uses the server session identified by the browser cookie; attributes survive redirects. Exposes named request/session state; flash values use session scope to survive the redirect. |
| 26 | Checks at compile time that the following method overrides a parent/interface contract. |
| 27 | Declares doPost: Handle the form POST after the request filter has run. Request-specific data stays in local variables. |
| 28 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 29 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 30 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 31 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 32 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 33 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 34 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 35 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 36 | Runs shared server-side validation before persistence. |
| 37 | Runs shared server-side validation before persistence. |
| 38 | Runs shared server-side validation before persistence. |
| 39 | Uses the server session identified by the browser cookie; attributes survive redirects. Exposes named request/session state; flash values use session scope to survive the redirect. |
| 40 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 42 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 43 | Runs shared server-side validation before persistence. |
| 44 | Reads untrusted submitted String data, which can be absent/null. |
| 45 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 46 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 47 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 48 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 49 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 50 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 51 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 52 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 53 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 54 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 55 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 56 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 58 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 59 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 60 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 61 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 62 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 63 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 64 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 65 | Uses the server session identified by the browser cookie; attributes survive redirects. Exposes named request/session state; flash values use session scope to survive the redirect. |
| 66 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 67 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 68 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 69 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 71 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 72 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 73 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 74 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 75 | Runs shared server-side validation before persistence. |
| 76 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 77 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 79 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 80 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 81 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 82 | Runs shared server-side validation before persistence. |
| 83 | Runs shared server-side validation before persistence. |
| 84 | Runs shared server-side validation before persistence. |
| 85 | Runs shared server-side validation before persistence. |
| 86 | Runs shared server-side validation before persistence. |
| 87 | Runs shared server-side validation before persistence. |
| 88 | Runs shared server-side validation before persistence. |
| 89 | Runs shared server-side validation before persistence. |
| 90 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 91 | Uses the server session identified by the browser cookie; attributes survive redirects. Removes the named session marker/value. It does not invalidate the entire session. |
| 92 | Uses the server session identified by the browser cookie; attributes survive redirects. |
| 93 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 94 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 95 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 97 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 98 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 99 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 100 | Runs shared server-side validation before persistence. |
| 101 | Uses the server session identified by the browser cookie; attributes survive redirects. Exposes named request/session state; flash values use session scope to survive the redirect. |
| 102 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 103 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 104 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 105 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 106 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 107 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 109 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 110 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 111 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 112 | Runs shared server-side validation before persistence. |
| 113 | Uses the server session identified by the browser cookie; attributes survive redirects. Exposes named request/session state; flash values use session scope to survive the redirect. |
| 114 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 116 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 117 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 118 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 119 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 120 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 121 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 122 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 123 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 124 | Runs shared server-side validation before persistence. |
| 125 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 126 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 128 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 129 | Uses the server session identified by the browser cookie; attributes survive redirects. Exposes named request/session state; flash values use session scope to survive the redirect. |
| 130 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 132 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 133 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 134 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 135 | Runs shared server-side validation before persistence. |
| 136 | Uses the server session identified by the browser cookie; attributes survive redirects. Exposes named request/session state; flash values use session scope to survive the redirect. |
| 137 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 139 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 140 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 141 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 142 | Reads untrusted submitted String data, which can be absent/null. |
| 143 | Runs shared server-side validation before persistence. |
| 144 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 145 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 146 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 147 | Uses the server session identified by the browser cookie; attributes survive redirects. |
| 148 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 149 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 150 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 151 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 152 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 154 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 155 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 156 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 157 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 158 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 159 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 160 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 161 | Runs shared server-side validation before persistence. |
| 162 | Uses the server session identified by the browser cookie; attributes survive redirects. Exposes named request/session state; flash values use session scope to survive the redirect. Branches on the shown condition; be able to explain both the success and rejection path. |
| 163 | Uses the server session identified by the browser cookie; attributes survive redirects. Exposes named request/session state; flash values use session scope to survive the redirect. |
| 164 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 166 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 167 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 168 | Uses the server session identified by the browser cookie; attributes survive redirects. |
| 169 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 170 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 172 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 173 | Runs shared server-side validation before persistence. |
| 174 | Uses the server session identified by the browser cookie; attributes survive redirects. Exposes named request/session state; flash values use session scope to survive the redirect. |
| 175 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 177 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 178 | Uses the server session identified by the browser cookie; attributes survive redirects. |
| 179 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 180 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 181 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 182 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 184 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 186 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 187 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 188 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 189 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 190 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 191 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 192 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 193 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 197 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 198 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 199 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 200 | Iterates the listed values/collection and executes the enclosed operations for each item. |
| 201 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 202 | Reads untrusted submitted String data, which can be absent/null. |
| 203 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 205 | Uses the server session identified by the browser cookie; attributes survive redirects. Exposes named request/session state; flash values use session scope to survive the redirect. |

<a id="file-46"></a>

## 46. src/main/java/com/web/ApiController.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `15625ee5bc677365154c186f63d316fb58162b4643658c5e3c0d70b8e0462b14`

<details>
<summary>Numbered source (66 physical lines)</summary>

~~~text
   1 | package com.web;
   2 |
   3 | import com.fasterxml.jackson.databind.ObjectMapper;
   4 | import com.service.*;
   5 | import jakarta.servlet.annotation.WebServlet;
   6 | import jakarta.servlet.http.*;
   7 | import java.io.IOException;
   8 | import java.util.*;
   9 |
  10 | /** Authenticated read APIs share the same service authorization as rendered pages. */
  11 | @WebServlet(urlPatterns = {"/api/slots", "/api/appointments", "/api/appointments/*"})
  12 | public final class ApiController extends HttpServlet {
  13 |   private static final ObjectMapper JSON = new ObjectMapper();
  14 |
  15 |   @Override
  16 |   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
  17 |     resp.setContentType("application/json;charset=UTF-8");
  18 |     if (req.getServletPath().equals("/api/slots")) {
  19 |       Integer doctor =
  20 |           req.getParameter("doctorId") == null ? null : PageController.number(req, "doctorId", 0);
  21 |       JSON.writeValue(
  22 |           resp.getWriter(),
  23 |           Map.of(
  24 |               "timeZone",
  25 |               HospitalService.zone().getId(),
  26 |               "slotDurationMinutes",
  27 |               30,
  28 |               "slots",
  29 |               HospitalService.appointments().available(doctor).stream()
  30 |                   .map(s ->
  31 |                       Map.of(
  32 |                           "id",
  33 |                           s.id(),
  34 |                           "doctorId",
  35 |                           s.doctorId(),
  36 |                           "label",
  37 |                           s.getLabel(),
  38 |                           "startsAt",
  39 |                           s.getStartsAt()))
  40 |                   .toList()));
  41 |       return;
  42 |     }
  43 |     Actor actor = PageController.actor(req);
  44 |     String info = req.getPathInfo();
  45 |     if (info == null || info.equals("/")) {
  46 |       int page = PageController.number(req, "page", 1);
  47 |       JSON.writeValue(
  48 |           resp.getWriter(),
  49 |           Map.of(
  50 |               "page",
  51 |               page,
  52 |               "pageSize",
  53 |               20,
  54 |               "appointments",
  55 |               HospitalService.appointments().list(actor, page)));
  56 |       return;
  57 |     }
  58 |     int id;
  59 |     try {
  60 |       id = Integer.parseInt(info.substring(1));
  61 |     } catch (NumberFormatException e) {
  62 |       throw new ServiceException(400, "Invalid appointment reference.");
  63 |     }
  64 |     JSON.writeValue(resp.getWriter(), HospitalService.appointments().detail(actor, id));
  65 |   }
  66 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 8 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 10 | Source comment/documentation; TODO statements describe unfinished work. |
| 11 | Registers this HTTP servlet at the quoted application-relative URL. |
| 12 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 13 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 15 | Checks at compile time that the following method overrides a parent/interface contract. |
| 16 | Declares doGet: Handle a GET request. The inherited logout handlers change session state through GET; protected POST logout is a future improvement. |
| 17 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 18 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 19 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 20 | Reads untrusted submitted String data, which can be absent/null. |
| 21 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 22 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 23 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 24 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 25 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 26 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 27 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 28 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 29 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 30 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 31 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 32 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 33 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 34 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 35 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 36 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 37 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 38 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 39 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 40 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 41 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 43 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 44 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 45 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 46 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 47 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 48 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 49 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 50 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 51 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 52 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 53 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 54 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 55 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 56 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 58 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 59 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 60 | Parses an integer and may raise NumberFormatException, a subtype of IllegalArgumentException. |
| 61 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 62 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 64 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |

<a id="file-47"></a>

## 47. src/main/java/com/web/Metrics.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `d498a507e60bfd89cc846dbe3f25d5e317f494459b06d20623518959e7ec034b`

<details>
<summary>Numbered source (21 physical lines)</summary>

~~~text
   1 | package com.web;
   2 |
   3 | import java.util.concurrent.atomic.LongAdder;
   4 |
   5 | public final class Metrics {
   6 |   public static final LongAdder requests = new LongAdder(),
   7 |       errors = new LongAdder(),
   8 |       nanos = new LongAdder();
   9 |
  10 |   private Metrics() {}
  11 |
  12 |   public static String prometheus() {
  13 |     return "hms_http_requests_total "
  14 |         + requests.sum()
  15 |         + "\nhms_http_errors_total "
  16 |         + errors.sum()
  17 |         + "\nhms_http_duration_seconds_sum "
  18 |         + (nanos.sum() / 1_000_000_000.0)
  19 |         + "\n";
  20 |   }
  21 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 6 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 7 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 8 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 10 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 12 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 13 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 14 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 15 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 16 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 17 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 18 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 19 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |

<a id="file-48"></a>

## 48. src/main/java/com/web/OperationsController.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `114517604a2a44ee186b06db03837806609d9d096c0ff06d55a9d0e3acaa7df5`

<details>
<summary>Numbered source (29 physical lines)</summary>

~~~text
   1 | package com.web;
   2 |
   3 | import com.db.DBConnect;
   4 | import jakarta.servlet.annotation.WebServlet;
   5 | import jakarta.servlet.http.*;
   6 | import java.io.IOException;
   7 | import java.sql.*;
   8 |
   9 | @WebServlet(urlPatterns = {"/health/live", "/health/ready", "/admin/metrics"})
  10 | public final class OperationsController extends HttpServlet {
  11 |   @Override
  12 |   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
  13 |     resp.setContentType("text/plain;charset=UTF-8");
  14 |     if (req.getServletPath().equals("/admin/metrics")) {
  15 |       resp.getWriter().print(Metrics.prometheus());
  16 |       return;
  17 |     }
  18 |     if (req.getServletPath().endsWith("/ready"))
  19 |       try (Connection c = DBConnect.open();
  20 |           PreparedStatement ps = c.prepareStatement("SELECT 1")) {
  21 |         ps.executeQuery().close();
  22 |       } catch (SQLException | RuntimeException e) {
  23 |         resp.setStatus(503);
  24 |         resp.getWriter().println("not ready");
  25 |         return;
  26 |       }
  27 |     resp.getWriter().println("ok");
  28 |   }
  29 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 9 | Registers this HTTP servlet at the quoted application-relative URL. |
| 10 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 11 | Checks at compile time that the following method overrides a parent/interface contract. |
| 12 | Declares doGet: Handle a GET request. The inherited logout handlers change session state through GET; protected POST logout is a future improvement. |
| 13 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 14 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 15 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 16 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 18 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 19 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 20 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 21 | Executes SELECT and obtains a ResultSet cursor. |
| 22 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 23 | Maps data into a plain Java object; the setter itself does not write SQL. |
| 24 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 25 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 27 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |

<a id="file-49"></a>

## 49. src/main/java/com/web/PageController.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `354f679782540a523f8c03cd28f8688e2cd4ab2e2f93b758f7e77f1828058690`

<details>
<summary>Numbered source (118 physical lines)</summary>

~~~text
   1 | package com.web;
   2 |
   3 | import com.entity.*;
   4 | import com.service.*;
   5 | import jakarta.servlet.*;
   6 | import jakarta.servlet.annotation.WebServlet;
   7 | import jakarta.servlet.http.*;
   8 | import java.io.IOException;
   9 | import java.time.*;
  10 | import java.util.*;
  11 |
  12 | @WebServlet(
  13 |     urlPatterns = {
  14 |       "/index.jsp",
  15 |       "/signup.jsp",
  16 |       "/user_Login.jsp",
  17 |       "/doctor.jsp",
  18 |       "/admin.jsp",
  19 |       "/user_appointment.jsp",
  20 |       "/view_appointment.jsp",
  21 |       "/appointment.jsp",
  22 |       "/change_password.jsp",
  23 |       "/admin/index.jsp",
  24 |       "/admin/doctor.jsp",
  25 |       "/admin/view_doctor.jsp",
  26 |       "/admin/edit_doctor.jsp",
  27 |       "/admin/patient.jsp",
  28 |       "/admin/slots.jsp",
  29 |       "/admin/appointment.jsp",
  30 |       "/doctor/index.jsp",
  31 |       "/doctor/patient.jsp",
  32 |       "/doctor/comment.jsp",
  33 |       "/doctor/edit_profile.jsp",
  34 |       "/doctor/slots.jsp"
  35 |     })
  36 | public final class PageController extends HttpServlet {
  37 |   public static Actor actor(HttpServletRequest req) {
  38 |     HttpSession session = req.getSession();
  39 |     if (session.getAttribute("adminObj") != null) return Actor.admin();
  40 |     if (session.getAttribute("docObj") instanceof Doctor d) return Actor.doctor(d.getId());
  41 |     if (session.getAttribute("userObj") instanceof User u) return Actor.patient(u.getId());
  42 |     throw new ServiceException(401, "Please sign in.");
  43 |   }
  44 |
  45 |   public static int number(HttpServletRequest req, String key, int fallback) {
  46 |     String value = req.getParameter(key);
  47 |     if (value == null || value.isBlank()) return fallback;
  48 |     try {
  49 |       int n = Integer.parseInt(value.trim());
  50 |       if (n < 1 || n > 1000000) throw new NumberFormatException();
  51 |       return n;
  52 |     } catch (NumberFormatException e) {
  53 |       throw new ServiceException(400, "Invalid " + key + ".");
  54 |     }
  55 |   }
  56 |
  57 |   @Override
  58 |   protected void doGet(HttpServletRequest req, HttpServletResponse resp)
  59 |       throws ServletException, IOException {
  60 |     String path = req.getServletPath();
  61 |     int page = number(req, "page", 1);
  62 |     req.setAttribute("page", page);
  63 |     switch (path) {
  64 |       case "/user_appointment.jsp" -> {
  65 |         Integer doctor = req.getParameter("doctorId") == null ? null : number(req, "doctorId", 0);
  66 |         req.setAttribute("doctors", HospitalService.doctors(false));
  67 |         req.setAttribute("slots", HospitalService.appointments().available(doctor));
  68 |         req.setAttribute("bookingKey", UUID.randomUUID().toString());
  69 |         req.setAttribute("hospitalZone", HospitalService.zone().getId());
  70 |       }
  71 |       case "/view_appointment.jsp", "/doctor/patient.jsp", "/admin/patient.jsp" -> {
  72 |         var rows = HospitalService.appointments().list(actor(req), page);
  73 |         req.setAttribute("appointments", rows);
  74 |         req.setAttribute("hasNext", rows.size() == 20);
  75 |         req.setAttribute(
  76 |             "detailsPath",
  77 |             path.startsWith("/admin/")
  78 |                 ? "appointment.jsp"
  79 |                 : path.startsWith("/doctor/") ? "comment.jsp" : "appointment.jsp");
  80 |       }
  81 |       case "/appointment.jsp", "/doctor/comment.jsp", "/admin/appointment.jsp" -> {
  82 |         int id = number(req, "id", 0);
  83 |         Actor actor = actor(req);
  84 |         var service = HospitalService.appointments();
  85 |         req.setAttribute("appointment", service.detail(actor, id));
  86 |         req.setAttribute("notes", service.notes(actor, id));
  87 |         req.setAttribute("events", service.events(actor, id));
  88 |         req.setAttribute(
  89 |             "allowedActions", service.allowedActions(actor, service.detail(actor, id)));
  90 |       }
  91 |       case "/admin/index.jsp" -> {
  92 |         req.setAttribute("stats", HospitalService.counts(null));
  93 |         req.setAttribute("specialties", HospitalService.specialties());
  94 |       }
  95 |       case "/doctor/index.jsp" -> {
  96 |         req.setAttribute("stats", HospitalService.counts(actor(req).id()));
  97 |       }
  98 |       case "/admin/view_doctor.jsp" -> req.setAttribute("doctors", HospitalService.doctors(true));
  99 |       case "/admin/doctor.jsp", "/admin/edit_doctor.jsp" -> {
 100 |         req.setAttribute("specialties", HospitalService.specialties());
 101 |         if (path.contains("edit_"))
 102 |           req.setAttribute("doctor", HospitalService.doctor(number(req, "id", 0)));
 103 |       }
 104 |       case "/doctor/edit_profile.jsp" -> {
 105 |         req.setAttribute("specialties", HospitalService.specialties());
 106 |         req.setAttribute("doctor", HospitalService.doctor(actor(req).id()));
 107 |         req.setAttribute("profileOnly", true);
 108 |       }
 109 |       case "/doctor/slots.jsp", "/admin/slots.jsp" -> {
 110 |         Integer id = path.startsWith("/doctor") ? actor(req).id() : null;
 111 |         req.setAttribute("slots", HospitalService.appointments().available(id));
 112 |         req.setAttribute("doctors", HospitalService.doctors(false));
 113 |         req.setAttribute("hospitalZone", HospitalService.zone().getId());
 114 |       }
 115 |     }
 116 |     req.getRequestDispatcher("/WEB-INF/views" + path).forward(req, resp);
 117 |   }
 118 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 8 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 9 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 10 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 12 | Registers this HTTP servlet at the quoted application-relative URL. |
| 13 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 14 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 15 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 16 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 17 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 18 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 19 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 20 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 21 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 22 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 23 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 24 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 25 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 26 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 27 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 28 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 29 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 30 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 31 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 32 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 33 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 34 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 36 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 37 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 38 | Uses the server session identified by the browser cookie; attributes survive redirects. |
| 39 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 40 | Reads authenticated server identity rather than trusting a submitted account ID. Branches on the shown condition; be able to explain both the success and rejection path. |
| 41 | Reads authenticated server identity rather than trusting a submitted account ID. Branches on the shown condition; be able to explain both the success and rejection path. |
| 42 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 45 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 46 | Reads untrusted submitted String data, which can be absent/null. |
| 47 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 48 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 49 | Parses an integer and may raise NumberFormatException, a subtype of IllegalArgumentException. |
| 50 | Branches on the shown condition; be able to explain both the success and rejection path. Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 51 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 52 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 53 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 57 | Checks at compile time that the following method overrides a parent/interface contract. |
| 58 | Declares doGet: Handle a GET request. The inherited logout handlers change session state through GET; protected POST logout is a future improvement. |
| 59 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 60 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 61 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 62 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 63 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 64 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 65 | Reads untrusted submitted String data, which can be absent/null. |
| 66 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 67 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 68 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 69 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 71 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 72 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 73 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 74 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 75 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 76 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 77 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 78 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 79 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 81 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 82 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 83 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 84 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 85 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 86 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 87 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 88 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 89 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 91 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 92 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 93 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 95 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 96 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 98 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 99 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 100 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 101 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 102 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 104 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 105 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 106 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 107 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 109 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 110 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 111 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 112 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 113 | Exposes named request/session state; flash values use session scope to survive the redirect. |
| 116 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |

<a id="file-50"></a>

## 50. src/main/java/com/web/SchedulingActions.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `cd281bd321883de2be2dde91c47a7e27b2c38ed45e12fa97248d26ddbd3cf55e`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | package com.web;
   2 |
   3 | import jakarta.servlet.annotation.WebServlet;
   4 |
   5 | @WebServlet(urlPatterns = {"/appointmentState", "/publishSlot", "/withdrawSlot"})
   6 | public final class SchedulingActions extends ActionController {}
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Registers this HTTP servlet at the quoted application-relative URL. |
| 6 | Declares the class and its inheritance/interface contract. See the file purpose above. |

<a id="file-51"></a>

## 51. src/dev/java/com/hospital/dev/LocalServer.java

Local-profile-only embedded Tomcat/H2 startup, excluded from the normal clean WAR.

Source SHA-256: `916ef682ba8320bb9a7c710653e4018ec749b605d4d765d6c9a44c3800e08b59`

<details>
<summary>Numbered source (61 physical lines)</summary>

~~~text
   1 | package com.hospital.dev;
   2 |
   3 | import java.nio.file.Path;
   4 | import org.apache.catalina.Context;
   5 | import org.apache.catalina.startup.Tomcat;
   6 | import org.apache.catalina.webresources.DirResourceSet;
   7 | import org.apache.catalina.webresources.StandardRoot;
   8 | import org.apache.tomcat.util.scan.StandardJarScanner;
   9 |
  10 | /** Local-only runner, excluded from the normal WAR build. */
  11 | public final class LocalServer {
  12 |   public static void main(String[] args) throws Exception {
  13 |     if (com.db.DBConnect.config("DB_URL", "").isBlank())
  14 |       System.setProperty(
  15 |           "DB_URL", "jdbc:h2:file:./.local/hospital;MODE=MySQL;DATABASE_TO_LOWER=TRUE");
  16 |     if (System.getenv("DB_USER") == null && System.getProperty("DB_USER") == null)
  17 |       System.setProperty("DB_USER", "sa");
  18 |     if (System.getenv("DB_PASSWORD") == null && System.getProperty("DB_PASSWORD") == null)
  19 |       System.setProperty("DB_PASSWORD", "");
  20 |     System.setProperty("DB_MIGRATE", "true");
  21 |     if (System.getenv("DEMO_MODE") == null && System.getProperty("DEMO_MODE") == null)
  22 |       System.setProperty("DEMO_MODE", "true");
  23 |     Tomcat tomcat = new Tomcat();
  24 |     tomcat.setBaseDir(
  25 |         Path.of(".local/tomcat-" + System.getProperty("port", "8080")).toAbsolutePath().toString());
  26 |     int port = Integer.parseInt(System.getProperty("port", "8080"));
  27 |     tomcat.setPort(port);
  28 |     tomcat.getConnector().setMaxPostSize(16384);
  29 |     tomcat.getConnector().setMaxParameterCount(50);
  30 |     tomcat.getConnector().setProperty("address", "127.0.0.1");
  31 |     Context context = tomcat.addWebapp("", Path.of("src/main/webapp").toAbsolutePath().toString());
  32 |     context.setParentClassLoader(LocalServer.class.getClassLoader());
  33 |     StandardRoot resources = new StandardRoot(context);
  34 |     resources.addPreResources(
  35 |         new DirResourceSet(
  36 |             resources,
  37 |             "/WEB-INF/classes",
  38 |             Path.of("target/classes").toAbsolutePath().toString(),
  39 |             "/"));
  40 |     context.setResources(resources);
  41 |     ((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
  42 |     Runtime.getRuntime()
  43 |         .addShutdownHook(
  44 |             new Thread(() -> {
  45 |               try {
  46 |                 tomcat.stop();
  47 |                 tomcat.destroy();
  48 |               } catch (Exception e) {
  49 |                 e.printStackTrace();
  50 |               }
  51 |             }));
  52 |     tomcat.start();
  53 |     if (!context.getState().isAvailable()) {
  54 |       tomcat.stop();
  55 |       tomcat.destroy();
  56 |       throw new IllegalStateException("Web application failed to start");
  57 |     }
  58 |     System.out.println("Hospital Management System: http://localhost:" + port);
  59 |     tomcat.getServer().await();
  60 |   }
  61 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 8 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 10 | Source comment/documentation; TODO statements describe unfinished work. |
| 11 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 12 | Declares main: Start the local-only embedded server, register shutdown cleanup, and wait for requests. |
| 13 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 14 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 15 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 16 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 17 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 18 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 19 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 20 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 21 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 22 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 23 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 24 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 25 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 26 | Parses an integer and may raise NumberFormatException, a subtype of IllegalArgumentException. |
| 27 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 28 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 29 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 30 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 31 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 32 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 33 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 34 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 35 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 36 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 37 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 38 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 39 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 40 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 41 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 42 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 43 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 44 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 45 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 46 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 47 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 48 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 49 | Logs a stack trace to server output; replace with structured logging/error translation later. |
| 52 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 53 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 54 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 55 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 56 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 58 | Prints process diagnostics, not HTTP response content. |
| 59 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |

<a id="file-52"></a>

## 52. src/test/java/com/perf/CountBenchmark.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `121f5b44198753bad32590af529645b97e37d7f5a660c430c885146338d7b4df`

<details>
<summary>Numbered source (151 physical lines)</summary>

~~~text
   1 | package com.perf;
   2 |
   3 | import java.nio.file.*;
   4 | import java.sql.*;
   5 | import java.util.*;
   6 |
   7 | /** Controlled comparison of the old row-count loop with the replacement aggregate. */
   8 | public final class CountBenchmark {
   9 |   public static void main(String[] args) throws Exception {
  10 |     String url = System.getProperty("bench.db.url", "");
  11 |     if (!url.contains("/hms_test"))
  12 |       throw new IllegalArgumentException("Use the isolated MySQL hms_test database only.");
  13 |     int rows = 10000, samples = 20;
  14 |     String key = UUID.randomUUID().toString();
  15 |     try (Connection c =
  16 |         DriverManager.getConnection(
  17 |             url,
  18 |             System.getProperty("bench.db.user", "root"),
  19 |             System.getProperty("bench.db.password", ""))) {
  20 |       int patient =
  21 |           insert(
  22 |               c,
  23 |               "INSERT INTO user_dtls(full_name,email,password) VALUES(?,?,?)",
  24 |               "Synthetic benchmark",
  25 |               key + "@benchmark.test",
  26 |               "not-an-authentication-fixture");
  27 |       int doctor =
  28 |           insert(
  29 |               c,
  30 |               "INSERT INTO doctor(full_name,dob,qualification,specialist,email,mobno,password)"
  31 |                   + " VALUES(?,?,?,?,?,?,?)",
  32 |               "Synthetic benchmark",
  33 |               "1980-01-01",
  34 |               "MD",
  35 |               "General",
  36 |               key + "@doctor-benchmark.test",
  37 |               "9000000000",
  38 |               "not-an-authentication-fixture");
  39 |       c.setAutoCommit(false);
  40 |       try (PreparedStatement ps =
  41 |           c.prepareStatement(
  42 |               "INSERT INTO"
  43 |                   + " appointment(user_id,fullname,gender,age,appoint_date,email,phno,diseases,doctor_id,address,status)"
  44 |                   + " VALUES(?,'Synthetic','other','20','2020-01-01','synthetic@test.test','9000000000','Benchmark"
  45 |                   + " fixture',?,'Synthetic','COMPLETED')")) {
  46 |         for (int i = 0; i < rows; i++) {
  47 |           ps.setInt(1, patient);
  48 |           ps.setInt(2, doctor);
  49 |           ps.addBatch();
  50 |           if (i % 500 == 499) ps.executeBatch();
  51 |         }
  52 |         c.commit();
  53 |       } catch (Exception e) {
  54 |         c.rollback();
  55 |         throw e;
  56 |       } finally {
  57 |         c.setAutoCommit(true);
  58 |       }
  59 |       for (int i = 0; i < 5; i++) {
  60 |         loop(c, doctor, rows);
  61 |         aggregate(c, doctor, rows);
  62 |       }
  63 |       double[] old = new double[samples], optimized = new double[samples];
  64 |       for (int i = 0; i < samples; i++) {
  65 |         long start = System.nanoTime();
  66 |         loop(c, doctor, rows);
  67 |         old[i] = (System.nanoTime() - start) / 1_000_000.0;
  68 |         start = System.nanoTime();
  69 |         aggregate(c, doctor, rows);
  70 |         optimized[i] = (System.nanoTime() - start) / 1_000_000.0;
  71 |       }
  72 |       double before = Arrays.stream(old).average().orElseThrow(),
  73 |           after = Arrays.stream(optimized).average().orElseThrow();
  74 |       Arrays.sort(old);
  75 |       Arrays.sort(optimized);
  76 |       String json =
  77 |           String.format(
  78 |               Locale.ROOT,
  79 |               """
  80 |               {
  81 |                 "recorded_at_utc": "%s",
  82 |                 "database": "MySQL %s",
  83 |                 "java": "%s",
  84 |                 "fixture_rows": %d,
  85 |                 "warmup_iterations": 5,
  86 |                 "samples_per_query": %d,
  87 |                 "legacy_select_ids_and_count_mean_ms": %.4f,
  88 |                 "aggregate_count_mean_ms": %.4f,
  89 |                 "legacy_p95_ms": %.4f,
  90 |                 "aggregate_p95_ms": %.4f,
  91 |                 "observed_mean_speedup": %.2f,
  92 |                 "client_rows_transferred_before": %d,
  93 |                 "client_rows_transferred_after": 1,
  94 |                 "limits": "Single JDBC connection, localhost, warm cache, synthetic records, indexed doctor filter. Microbenchmark only; not an end-to-end application speedup. Fixtures remain in hms_test."
  95 |               }
  96 |               """,
  97 |               java.time.Instant.now(),
  98 |               c.getMetaData().getDatabaseProductVersion(),
  99 |               System.getProperty("java.version"),
 100 |               rows,
 101 |               samples,
 102 |               before,
 103 |               after,
 104 |               old[18],
 105 |               optimized[18],
 106 |               before / after,
 107 |               rows);
 108 |       Path output = Path.of("docs/performance/sql-count.json");
 109 |       Files.createDirectories(output.getParent());
 110 |       Files.writeString(output, json);
 111 |       System.out.println(json);
 112 |     }
 113 |   }
 114 |
 115 |   private static int insert(Connection c, String sql, String... values) throws SQLException {
 116 |     try (PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
 117 |       for (int i = 0; i < values.length; i++) ps.setString(i + 1, values[i]);
 118 |       ps.executeUpdate();
 119 |       try (ResultSet rs = ps.getGeneratedKeys()) {
 120 |         rs.next();
 121 |         return rs.getInt(1);
 122 |       }
 123 |     }
 124 |   }
 125 |
 126 |   private static void loop(Connection c, int doctor, int expected) throws SQLException {
 127 |     try (PreparedStatement ps =
 128 |         c.prepareStatement("SELECT id FROM appointment WHERE doctor_id=?")) {
 129 |       ps.setInt(1, doctor);
 130 |       try (ResultSet rs = ps.executeQuery()) {
 131 |         int count = 0;
 132 |         while (rs.next()) {
 133 |           rs.getInt(1);
 134 |           count++;
 135 |         }
 136 |         if (count != expected) throw new AssertionError(count);
 137 |       }
 138 |     }
 139 |   }
 140 |
 141 |   private static void aggregate(Connection c, int doctor, int expected) throws SQLException {
 142 |     try (PreparedStatement ps =
 143 |         c.prepareStatement("SELECT COUNT(*) FROM appointment WHERE doctor_id=?")) {
 144 |       ps.setInt(1, doctor);
 145 |       try (ResultSet rs = ps.executeQuery()) {
 146 |         rs.next();
 147 |         if (rs.getInt(1) != expected) throw new AssertionError();
 148 |       }
 149 |     }
 150 |   }
 151 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 4 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Source comment/documentation; TODO statements describe unfinished work. |
| 8 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 9 | Declares main: Start the local-only embedded server, register shutdown cleanup, and wait for requests. |
| 10 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 11 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 12 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 13 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 14 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 15 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 16 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 17 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 18 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 19 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 20 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 21 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 22 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 23 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 24 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 25 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 26 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 27 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 28 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 29 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 30 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 31 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 32 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 33 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 34 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 35 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 36 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 37 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 38 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 39 | Begins manual control of a transaction containing multiple writes. |
| 40 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 41 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 42 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 43 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 44 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 45 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 46 | Counts one row in Java. A COUNT(*) query would avoid transferring every row. |
| 47 | Binds parameter 1 (JDBC indexes start at 1) to patient. Match the SQL placeholder order. |
| 48 | Binds parameter 2 (JDBC indexes start at 1) to doctor. Match the SQL placeholder order. |
| 49 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 50 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 52 | Commits the grouped writes. |
| 53 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 54 | Rolls back the grouped writes on failure. |
| 55 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 56 | Runs cleanup even on return/exception; connection or transaction state must not leak. |
| 57 | Restores auto-commit after the grouped transaction. |
| 59 | Counts one row in Java. A COUNT(*) query would avoid transferring every row. |
| 60 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 61 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 63 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 64 | Counts one row in Java. A COUNT(*) query would avoid transferring every row. |
| 65 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 66 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 67 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 68 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 69 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 70 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 72 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 73 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 74 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 75 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 76 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 77 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 78 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 79 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 81 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 82 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 83 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 84 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 85 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 86 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 87 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 88 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 89 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 90 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 91 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 92 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 93 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 94 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 96 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 97 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 98 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 99 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 100 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 101 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 102 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 103 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 104 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 105 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 106 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 107 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 108 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 109 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 110 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 111 | Prints process diagnostics, not HTTP response content. |
| 115 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 116 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 117 | Counts one row in Java. A COUNT(*) query would avoid transferring every row. |
| 118 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 119 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 120 | Advances the cursor and checks whether a row exists. |
| 121 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 126 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 127 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 128 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. Check whether doctor_id is an ownership predicate, as in the consultation UPDATE. |
| 129 | Binds parameter 1 (JDBC indexes start at 1) to doctor. Match the SQL placeholder order. |
| 130 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 131 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 132 | Advances the cursor and checks whether a row exists. |
| 133 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 134 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 136 | Branches on the shown condition; be able to explain both the success and rejection path. Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 141 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 142 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 143 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. Check whether doctor_id is an ownership predicate, as in the consultation UPDATE. |
| 144 | Binds parameter 1 (JDBC indexes start at 1) to doctor. Match the SQL placeholder order. |
| 145 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 146 | Advances the cursor and checks whether a row exists. |
| 147 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Branches on the shown condition; be able to explain both the success and rejection path. Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |

<a id="file-53"></a>

## 53. src/test/java/com/security/InputsTest.java

Tests validation using a minimal dynamic-proxy request double.

Source SHA-256: `b530ebd82039a4bf39b5b885e7d7c3b6aff4f66af5128e62c3555af3cda58488`

<details>
<summary>Numbered source (44 physical lines)</summary>

~~~text
   1 | package com.security;
   2 |
   3 | import static org.junit.Assert.*;
   4 |
   5 | import jakarta.servlet.http.HttpServletRequest;
   6 | import java.lang.reflect.Proxy;
   7 | import java.time.LocalDate;
   8 | import java.util.Map;
   9 | import org.junit.Test;
  10 |
  11 | public class InputsTest {
  12 |   private HttpServletRequest request(Map<String, String> values) {
  13 |     return (HttpServletRequest)
  14 |         Proxy.newProxyInstance(
  15 |             getClass().getClassLoader(),
  16 |             new Class<?>[] {HttpServletRequest.class},
  17 |             (proxy, method, args) ->
  18 |                 method.getName().equals("getParameter") ? values.get(args[0]) : null);
  19 |   }
  20 |
  21 |   @Test
  22 |   public void normalizesEmailAndAcceptsFutureDate() {
  23 |     assertEquals(
  24 |         "patient@example.test", Inputs.email(request(Map.of("email", " PATIENT@example.test "))));
  25 |     String date = LocalDate.now().plusDays(2).toString();
  26 |     assertEquals(date, Inputs.appointmentDate(request(Map.of("appoint_date", date))));
  27 |   }
  28 |
  29 |   @Test
  30 |   public void rejectsPastDatesMalformedDatesAndMissingFields() {
  31 |     for (Map<String, String> fields :
  32 |         java.util.List.of(
  33 |             Map.of("appoint_date", "2000-01-01"),
  34 |             Map.of("appoint_date", "invalid"),
  35 |             Map.<String, String>of())) {
  36 |       assertThrows(IllegalArgumentException.class, () -> Inputs.appointmentDate(request(fields)));
  37 |     }
  38 |     assertThrows(
  39 |         IllegalArgumentException.class, () -> Inputs.email(request(Map.of("email", "invalid"))));
  40 |     assertThrows(
  41 |         IllegalArgumentException.class,
  42 |         () -> Inputs.password(request(Map.of("password", "short")), "password"));
  43 |   }
  44 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 8 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 9 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 11 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 12 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 13 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 14 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 15 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 16 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 17 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 18 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 21 | Declares a JUnit test method with assertions below. |
| 22 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 23 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 24 | Runs shared server-side validation before persistence. |
| 25 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 26 | Runs shared server-side validation before persistence. Asserts the required test outcome; a failed assertion fails this unit test. |
| 29 | Declares a JUnit test method with assertions below. |
| 30 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 31 | Iterates the listed values/collection and executes the enclosed operations for each item. |
| 32 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 33 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 34 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 35 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 36 | Runs shared server-side validation before persistence. Asserts the required test outcome; a failed assertion fails this unit test. |
| 38 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 39 | Runs shared server-side validation before persistence. |
| 40 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 41 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 42 | Runs shared server-side validation before persistence. |

<a id="file-54"></a>

## 54. src/test/java/com/security/PasswordsTest.java

Tests salt variability, credential rejection, malformed hashes and escaping.

Source SHA-256: `30fbd3f257be982ab747e77e7e84a08c55e9e989cd23dd1c3063e1d5d9c5dbb5`

<details>
<summary>Numbered source (29 physical lines)</summary>

~~~text
   1 | package com.security;
   2 |
   3 | import static org.junit.Assert.*;
   4 |
   5 | import org.junit.Test;
   6 |
   7 | public class PasswordsTest {
   8 |   @Test
   9 |   public void saltsPasswordsAndRejectsIncorrectCredentials() {
  10 |     String first = Passwords.hash("CorrectPassword123");
  11 |     String second = Passwords.hash("CorrectPassword123");
  12 |     assertNotEquals(first, second);
  13 |     assertTrue(first.startsWith("pbkdf2$600000$"));
  14 |     assertFalse(Passwords.needsRehash(first));
  15 |     assertTrue(Passwords.needsRehash("pbkdf2$210000$old$representation"));
  16 |     assertTrue(Passwords.verify("CorrectPassword123", first));
  17 |     assertFalse(Passwords.verify("wrong", first));
  18 |     assertFalse(Passwords.verify(null, first));
  19 |     assertFalse(Passwords.verify("plain", "plain"));
  20 |     assertFalse(Passwords.verify("anything", "pbkdf2$-1$invalid$invalid"));
  21 |     assertFalse(Passwords.verify("anything", "pbkdf2$210000$invalid$invalid"));
  22 |   }
  23 |
  24 |   @Test
  25 |   public void escapesHtmlAndAttributeBreakouts() {
  26 |     assertEquals("&lt;script&gt;&quot;&#39;&amp;", Html.escape("<script>\"'&"));
  27 |     assertEquals("", Html.escape(null));
  28 |   }
  29 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 8 | Declares a JUnit test method with assertions below. |
| 9 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 10 | Derives a freshly salted password representation before storage. |
| 11 | Derives a freshly salted password representation before storage. |
| 12 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 13 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 14 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 15 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 16 | Verifies using the salt and work factor embedded in the stored representation. Asserts the required test outcome; a failed assertion fails this unit test. |
| 17 | Verifies using the salt and work factor embedded in the stored representation. Asserts the required test outcome; a failed assertion fails this unit test. |
| 18 | Verifies using the salt and work factor embedded in the stored representation. Asserts the required test outcome; a failed assertion fails this unit test. |
| 19 | Verifies using the salt and work factor embedded in the stored representation. Asserts the required test outcome; a failed assertion fails this unit test. |
| 20 | Verifies using the salt and work factor embedded in the stored representation. Asserts the required test outcome; a failed assertion fails this unit test. |
| 21 | Verifies using the salt and work factor embedded in the stored representation. Asserts the required test outcome; a failed assertion fails this unit test. |
| 24 | Declares a JUnit test method with assertions below. |
| 25 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 26 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 27 | Asserts the required test outcome; a failed assertion fails this unit test. |

<a id="file-55"></a>

## 55. src/test/java/com/service/AppointmentServiceTest.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `be2f6f9cda77f72cb122f5ad55945a3ee1f827fdf0c291bbbf7747fbce580980`

<details>
<summary>Numbered source (335 physical lines)</summary>

~~~text
   1 | package com.service;
   2 |
   3 | import static org.junit.Assert.*;
   4 |
   5 | import com.zaxxer.hikari.*;
   6 | import java.lang.reflect.*;
   7 | import java.sql.*;
   8 | import java.time.*;
   9 | import java.util.*;
  10 | import java.util.concurrent.*;
  11 | import javax.sql.DataSource;
  12 | import org.flywaydb.core.Flyway;
  13 | import org.junit.*;
  14 |
  15 | /** Same contract runs against H2 by default and real MySQL in CI/local integration mode. */
  16 | public class AppointmentServiceTest {
  17 |   private static HikariDataSource source;
  18 |   private static final Clock CLOCK =
  19 |       Clock.fixed(Instant.parse("2030-01-01T04:00:00Z"), ZoneId.of("Asia/Kolkata"));
  20 |   private AppointmentService service;
  21 |   private int patient, otherPatient, doctor, otherDoctor;
  22 |   private long slot;
  23 |   private final LocalDateTime start = LocalDateTime.of(2030, 1, 2, 10, 0);
  24 |
  25 |   @BeforeClass
  26 |   public static void database() {
  27 |     String url =
  28 |         System.getProperty(
  29 |             "test.db.url",
  30 |             "jdbc:h2:mem:hms_test;MODE=MySQL;DATABASE_TO_LOWER=TRUE;DB_CLOSE_DELAY=-1;LOCK_TIMEOUT=5000");
  31 |     if (!url.contains("hms_test"))
  32 |       throw new IllegalArgumentException("Use an isolated database named hms_test.");
  33 |     HikariConfig config = new HikariConfig();
  34 |     config.setJdbcUrl(url);
  35 |     config.setUsername(System.getProperty("test.db.user", "sa"));
  36 |     config.setPassword(System.getProperty("test.db.password", ""));
  37 |     config.setMaximumPoolSize(6);
  38 |     source = new HikariDataSource(config);
  39 |     Flyway flyway =
  40 |         Flyway.configure().dataSource(source).baselineOnMigrate(true).baselineVersion("0").load();
  41 |     flyway.migrate();
  42 |     assertEquals(0, flyway.migrate().migrationsExecuted);
  43 |   }
  44 |
  45 |   @AfterClass
  46 |   public static void shutdown() {
  47 |     source.close();
  48 |   }
  49 |
  50 |   private int insert(String sql, String... values) throws SQLException {
  51 |     try (Connection c = source.getConnection();
  52 |         PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
  53 |       for (int i = 0; i < values.length; i++) ps.setString(i + 1, values[i]);
  54 |       ps.executeUpdate();
  55 |       try (ResultSet rs = ps.getGeneratedKeys()) {
  56 |         rs.next();
  57 |         return rs.getInt(1);
  58 |       }
  59 |     }
  60 |   }
  61 |
  62 |   @Before
  63 |   public void fixtures() throws SQLException {
  64 |     String key = UUID.randomUUID().toString();
  65 |     patient =
  66 |         insert(
  67 |             "INSERT INTO user_dtls(full_name,email,password) VALUES(?,?,?)",
  68 |             "Synthetic patient",
  69 |             key + "@patient.test",
  70 |             "not-used-by-authentication");
  71 |     otherPatient =
  72 |         insert(
  73 |             "INSERT INTO user_dtls(full_name,email,password) VALUES(?,?,?)",
  74 |             "Other patient",
  75 |             key + "@other.test",
  76 |             "not-used-by-authentication");
  77 |     doctor =
  78 |         insert(
  79 |             "INSERT INTO doctor(full_name,dob,qualification,specialist,email,mobno,password)"
  80 |                 + " VALUES(?,?,?,?,?,?,?)",
  81 |             "Synthetic doctor",
  82 |             "1980-01-01",
  83 |             "MD",
  84 |             "General",
  85 |             key + "@doctor.test",
  86 |             "9000000000",
  87 |             "unused");
  88 |     otherDoctor =
  89 |         insert(
  90 |             "INSERT INTO doctor(full_name,dob,qualification,specialist,email,mobno,password)"
  91 |                 + " VALUES(?,?,?,?,?,?,?)",
  92 |             "Other doctor",
  93 |             "1980-01-01",
  94 |             "MD",
  95 |             "General",
  96 |             key + "@other-doctor.test",
  97 |             "9000000000",
  98 |             "unused");
  99 |     service = new AppointmentService(source, CLOCK);
 100 |     slot = service.publish(Actor.doctor(doctor), doctor, start);
 101 |   }
 102 |
 103 |   private AppointmentService.Booking booking(String key) {
 104 |     return new AppointmentService.Booking(
 105 |         slot,
 106 |         key,
 107 |         "Synthetic patient",
 108 |         "other",
 109 |         "24",
 110 |         "test@hospital.test",
 111 |         "9000000000",
 112 |         "Synthetic reason",
 113 |         "Test address");
 114 |   }
 115 |
 116 |   private long count(String sql, long id) throws SQLException {
 117 |     try (Connection c = source.getConnection();
 118 |         PreparedStatement ps = c.prepareStatement(sql)) {
 119 |       ps.setLong(1, id);
 120 |       try (ResultSet rs = ps.executeQuery()) {
 121 |         rs.next();
 122 |         return rs.getLong(1);
 123 |       }
 124 |     }
 125 |   }
 126 |
 127 |   private void status(int expected, Runnable action) {
 128 |     ServiceException e = assertThrows(ServiceException.class, action::run);
 129 |     assertEquals(expected, e.getStatus());
 130 |   }
 131 |
 132 |   @Test
 133 |   public void concurrentPatientsCannotClaimSameSlot() throws Exception {
 134 |     ExecutorService threads = Executors.newFixedThreadPool(2);
 135 |     CountDownLatch ready = new CountDownLatch(2), go = new CountDownLatch(1);
 136 |     List<Future<Integer>> futures = new ArrayList<>();
 137 |     try {
 138 |       for (int user : new int[] {patient, otherPatient})
 139 |         futures.add(
 140 |             threads.submit(() -> {
 141 |               ready.countDown();
 142 |               go.await();
 143 |               try {
 144 |                 return new AppointmentService(source, CLOCK)
 145 |                     .book(user, booking(UUID.randomUUID().toString()));
 146 |               } catch (ServiceException e) {
 147 |                 assertEquals(409, e.getStatus());
 148 |                 return -1;
 149 |               }
 150 |             }));
 151 |       assertTrue(ready.await(5, TimeUnit.SECONDS));
 152 |       go.countDown();
 153 |       int successes = 0;
 154 |       for (Future<Integer> future : futures) if (future.get(15, TimeUnit.SECONDS) > 0) successes++;
 155 |       assertEquals(1, successes);
 156 |       assertEquals(1, count("SELECT COUNT(*) FROM slot_reservation WHERE slot_id=?", slot));
 157 |     } finally {
 158 |       threads.shutdownNow();
 159 |     }
 160 |   }
 161 |
 162 |   @Test
 163 |   public void concurrentRetriesAreIdempotentAndPayloadReuseIsRejected() throws Exception {
 164 |     var booking = booking(UUID.randomUUID().toString());
 165 |     ExecutorService threads = Executors.newFixedThreadPool(2);
 166 |     CountDownLatch go = new CountDownLatch(1);
 167 |     try {
 168 |       Future<Integer> a =
 169 |           threads.submit(() -> {
 170 |             go.await();
 171 |             return service.book(patient, booking);
 172 |           });
 173 |       Future<Integer> b =
 174 |           threads.submit(() -> {
 175 |             go.await();
 176 |             return new AppointmentService(source, CLOCK).book(patient, booking);
 177 |           });
 178 |       go.countDown();
 179 |       assertEquals(a.get(15, TimeUnit.SECONDS), b.get(15, TimeUnit.SECONDS));
 180 |       assertEquals(1, count("SELECT COUNT(*) FROM appointment WHERE slot_id=?", slot));
 181 |       var changed =
 182 |           new AppointmentService.Booking(
 183 |               slot,
 184 |               booking.requestKey(),
 185 |               "Different name",
 186 |               "other",
 187 |               "24",
 188 |               "test@hospital.test",
 189 |               "9000000000",
 190 |               "Synthetic reason",
 191 |               "Test address");
 192 |       status(409, () -> service.book(patient, changed));
 193 |     } finally {
 194 |       threads.shutdownNow();
 195 |     }
 196 |   }
 197 |
 198 |   @Test
 199 |   public void cancellationReleasesSlotAndRetainsHistory() throws Exception {
 200 |     int first = service.book(patient, booking(UUID.randomUUID().toString()));
 201 |     service.transition(Actor.patient(patient), first, "CANCELLED");
 202 |     service.transition(Actor.patient(patient), first, "CANCELLED");
 203 |     assertEquals("CANCELLED", service.detail(Actor.patient(patient), first).status());
 204 |     int next = service.book(otherPatient, booking(UUID.randomUUID().toString()));
 205 |     assertNotEquals(first, next);
 206 |     assertEquals(2, count("SELECT COUNT(*) FROM appointment WHERE slot_id=?", slot));
 207 |     assertEquals(2, service.events(Actor.patient(patient), first).size());
 208 |   }
 209 |
 210 |   @Test
 211 |   public void ownershipAndLifecycleAreEnforced() {
 212 |     int id = service.book(patient, booking(UUID.randomUUID().toString()));
 213 |     status(404, () -> service.detail(Actor.patient(otherPatient), id));
 214 |     status(404, () -> service.addNote(Actor.doctor(otherDoctor), id, "forged doctor"));
 215 |     status(403, () -> service.transition(Actor.patient(patient), id, "CONFIRMED"));
 216 |     status(409, () -> service.transition(Actor.doctor(doctor), id, "COMPLETED"));
 217 |     service.transition(Actor.doctor(doctor), id, "CONFIRMED");
 218 |     status(409, () -> service.transition(Actor.doctor(doctor), id, "COMPLETED"));
 219 |     service.addNote(Actor.doctor(doctor), id, "First note");
 220 |     service.addNote(Actor.doctor(doctor), id, "Follow-up note");
 221 |     assertEquals("CONFIRMED", service.detail(Actor.patient(patient), id).status());
 222 |     assertEquals(2, service.notes(Actor.patient(patient), id).size());
 223 |     AppointmentService afterVisit =
 224 |         new AppointmentService(
 225 |             source, Clock.fixed(Instant.parse("2030-01-02T06:00:00Z"), CLOCK.getZone()));
 226 |     afterVisit.transition(Actor.doctor(doctor), id, "COMPLETED");
 227 |     status(409, () -> afterVisit.transition(Actor.patient(patient), id, "CANCELLED"));
 228 |     assertEquals("COMPLETED", afterVisit.detail(Actor.patient(patient), id).status());
 229 |   }
 230 |
 231 |   @Test
 232 |   public void failedAuditWriteRollsBackBookingReservationAndRequestKey() throws Exception {
 233 |     DataSource failing =
 234 |         (DataSource)
 235 |             Proxy.newProxyInstance(
 236 |                 getClass().getClassLoader(),
 237 |                 new Class[] {DataSource.class},
 238 |                 (proxy, method, args) -> {
 239 |                   try {
 240 |                     Object result = method.invoke(source, args);
 241 |                     if (method.getName().equals("getConnection"))
 242 |                       return Proxy.newProxyInstance(
 243 |                           getClass().getClassLoader(),
 244 |                           new Class[] {Connection.class},
 245 |                           (p, m, a) -> {
 246 |                             if (m.getName().equals("prepareStatement")
 247 |                                 && a[0].toString().contains("INSERT INTO appointment_event"))
 248 |                               throw new SQLException("Injected test audit failure");
 249 |                             try {
 250 |                               return m.invoke(result, a);
 251 |                             } catch (InvocationTargetException e) {
 252 |                               throw e.getCause();
 253 |                             }
 254 |                           });
 255 |                     return result;
 256 |                   } catch (InvocationTargetException e) {
 257 |                     throw e.getCause();
 258 |                   }
 259 |                 });
 260 |     assertThrows(
 261 |         DataAccessException.class,
 262 |         () ->
 263 |             new AppointmentService(failing, CLOCK)
 264 |                 .book(patient, booking(UUID.randomUUID().toString())));
 265 |     assertEquals(0, count("SELECT COUNT(*) FROM appointment WHERE slot_id=?", slot));
 266 |     assertEquals(0, count("SELECT COUNT(*) FROM slot_reservation WHERE slot_id=?", slot));
 267 |     assertEquals(0, count("SELECT COUNT(*) FROM booking_request WHERE user_id=?", patient));
 268 |     assertTrue(service.book(patient, booking(UUID.randomUUID().toString())) > 0);
 269 |   }
 270 |
 271 |   @Test
 272 |   public void publishedSlotsAreBoundedAndWithdrawalIsSafe() {
 273 |     status(400, () -> service.publish(Actor.doctor(doctor), doctor, start.plusMinutes(15)));
 274 |     status(403, () -> service.publish(Actor.doctor(otherDoctor), doctor, start.plusHours(1)));
 275 |     status(409, () -> service.publish(Actor.doctor(doctor), doctor, start));
 276 |     int id = service.book(patient, booking(UUID.randomUUID().toString()));
 277 |     status(409, () -> service.disable(Actor.doctor(doctor), slot));
 278 |     service.transition(Actor.patient(patient), id, "CANCELLED");
 279 |     service.disable(Actor.doctor(doctor), slot);
 280 |     status(409, () -> service.book(patient, booking(UUID.randomUUID().toString())));
 281 |   }
 282 |
 283 |   @Test
 284 |   public void everyOperationReturnsItsPooledConnection() {
 285 |     service.available(doctor);
 286 |     service.list(Actor.patient(patient), 1);
 287 |     assertEquals(0, source.getHikariPoolMXBean().getActiveConnections());
 288 |   }
 289 |
 290 |   @Test
 291 |   public void successfulLoginUpgradesAnOlderHashWithoutRevokingItsIdentity() throws Exception {
 292 |     String password = "LegacyPassword@123";
 293 |     byte[] salt = new byte[16];
 294 |     new java.security.SecureRandom().nextBytes(salt);
 295 |     var spec = new javax.crypto.spec.PBEKeySpec(password.toCharArray(), salt, 210000, 256);
 296 |     String previous =
 297 |         "pbkdf2$210000$"
 298 |             + Base64.getEncoder().encodeToString(salt)
 299 |             + "$"
 300 |             + Base64.getEncoder()
 301 |                 .encodeToString(
 302 |                     javax.crypto.SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
 303 |                         .generateSecret(spec)
 304 |                         .getEncoded());
 305 |     spec.clearPassword();
 306 |     try (Connection c = source.getConnection()) {
 307 |       String email;
 308 |       try (PreparedStatement ps =
 309 |           c.prepareStatement("UPDATE user_dtls SET password=? WHERE id=?")) {
 310 |         ps.setString(1, previous);
 311 |         ps.setInt(2, patient);
 312 |         ps.executeUpdate();
 313 |       }
 314 |       try (PreparedStatement ps = c.prepareStatement("SELECT email FROM user_dtls WHERE id=?")) {
 315 |         ps.setInt(1, patient);
 316 |         try (ResultSet rs = ps.executeQuery()) {
 317 |           rs.next();
 318 |           email = rs.getString(1);
 319 |         }
 320 |       }
 321 |       var user = new com.dao.UserDao(c).login(email, password);
 322 |       assertNotNull(user);
 323 |       assertEquals(patient, user.getId());
 324 |       assertEquals(0, user.getAuthVersion());
 325 |       assertNull(user.getPassword());
 326 |       try (PreparedStatement ps = c.prepareStatement("SELECT password FROM user_dtls WHERE id=?")) {
 327 |         ps.setInt(1, patient);
 328 |         try (ResultSet rs = ps.executeQuery()) {
 329 |           rs.next();
 330 |           assertTrue(rs.getString(1).startsWith("pbkdf2$600000$"));
 331 |         }
 332 |       }
 333 |     }
 334 |   }
 335 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 8 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 9 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 10 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 11 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 12 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 13 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 15 | Source comment/documentation; TODO statements describe unfinished work. |
| 16 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 17 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 18 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 19 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 20 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 21 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 22 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 23 | Declares a field and its type/visibility/modifiers. Avoid storing mutable per-request state on a shared servlet instance. |
| 25 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 26 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 27 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 28 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 29 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 30 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 31 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 32 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 33 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 34 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 35 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 36 | Maps data into a plain Java object; the setter itself does not write SQL. |
| 37 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 38 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 39 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 40 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 41 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 42 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 45 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 46 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 47 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 50 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 51 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 52 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 53 | Counts one row in Java. A COUNT(*) query would avoid transferring every row. |
| 54 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 55 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 56 | Advances the cursor and checks whether a row exists. |
| 57 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 62 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 63 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 64 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 65 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 66 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 67 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 68 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 69 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 70 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 71 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 72 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 73 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 74 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 75 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 76 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 77 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 78 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 79 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 80 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 81 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 82 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 83 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 84 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 85 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 86 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 87 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 88 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 89 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 90 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 91 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 92 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 93 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 94 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 95 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 96 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 97 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 98 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 99 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 100 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 103 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 104 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 105 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 106 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 107 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 108 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 109 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 110 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 111 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 112 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 113 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 116 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 117 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 118 | Creates a prepared SQL statement without executing it yet. |
| 119 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 120 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 121 | Advances the cursor and checks whether a row exists. |
| 122 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 127 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 128 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 129 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 132 | Declares a JUnit test method with assertions below. |
| 133 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 134 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 135 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 136 | Allocates the initially empty collection of mapped objects. |
| 137 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 138 | Iterates the listed values/collection and executes the enclosed operations for each item. |
| 139 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 140 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 141 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 142 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 143 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 144 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 145 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 146 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 147 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 148 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 151 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 152 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 153 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 154 | Iterates the listed values/collection and executes the enclosed operations for each item. |
| 155 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 156 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 157 | Runs cleanup even on return/exception; connection or transaction state must not leak. |
| 158 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 162 | Declares a JUnit test method with assertions below. |
| 163 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 164 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 165 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 166 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 167 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 168 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 169 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 170 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 171 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 173 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 174 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 175 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 176 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 178 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 179 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 180 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 181 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 182 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 183 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 184 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 185 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 186 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 187 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 188 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 189 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 190 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 191 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 192 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 193 | Runs cleanup even on return/exception; connection or transaction state must not leak. |
| 194 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 198 | Declares a JUnit test method with assertions below. |
| 199 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 200 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 201 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 202 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 203 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 204 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 205 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 206 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 207 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 210 | Declares a JUnit test method with assertions below. |
| 211 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 212 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 213 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 214 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 215 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 216 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 217 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 218 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 219 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 220 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 221 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 222 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 223 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 224 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 225 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 226 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 227 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 228 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 231 | Declares a JUnit test method with assertions below. |
| 232 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 233 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 234 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 235 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 236 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 237 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 238 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 239 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 240 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 241 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 242 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 243 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 244 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 245 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 246 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 247 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 248 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 249 | Begins operations whose failures are handled by the following catch/finally clauses. |
| 250 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 251 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 252 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 255 | Returns this result to the caller. Inherited DAO failure contracts may use false/null/empty data. |
| 256 | Handles the indicated exception. Legacy false/null/empty-list outcomes can hide the difference between no data and query failure. |
| 257 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 260 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 261 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 262 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 263 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 264 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 265 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 266 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 267 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 268 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 271 | Declares a JUnit test method with assertions below. |
| 272 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 273 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 274 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 275 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 276 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 277 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 278 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 279 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 280 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 283 | Declares a JUnit test method with assertions below. |
| 284 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 285 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 286 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 287 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 290 | Declares a JUnit test method with assertions below. |
| 291 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 292 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 293 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 294 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 295 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 296 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 297 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 298 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 299 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 300 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 301 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 302 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 303 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 304 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 305 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 306 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 307 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 308 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 309 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. |
| 310 | Binds parameter 1 (JDBC indexes start at 1) to previous. Match the SQL placeholder order. |
| 311 | Binds parameter 2 (JDBC indexes start at 1) to patient. Match the SQL placeholder order. |
| 312 | Executes a write and obtains the affected-row count; exactly one affected row is normally success here. |
| 314 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 315 | Binds parameter 1 (JDBC indexes start at 1) to patient. Match the SQL placeholder order. |
| 316 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 317 | Advances the cursor and checks whether a row exists. |
| 318 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. |
| 321 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 322 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 323 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 324 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 325 | Asserts the required test outcome; a failed assertion fails this unit test. |
| 326 | Defines fixed SQL with value placeholders where shown; untrusted input is bound separately. Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 327 | Binds parameter 1 (JDBC indexes start at 1) to patient. Match the SQL placeholder order. |
| 328 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. Executes SELECT and obtains a ResultSet cursor. |
| 329 | Advances the cursor and checks whether a row exists. |
| 330 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Asserts the required test outcome; a failed assertion fails this unit test. |

<a id="file-56"></a>

## 56. src/test/java/com/service/MigrationTest.java

Application source; its annotations below explain the visible statements.

Source SHA-256: `032a16f9b4572853fa39879e435625fe161d0879f643061aac3f918b62174701`

<details>
<summary>Numbered source (71 physical lines)</summary>

~~~text
   1 | package com.service;
   2 |
   3 | import static org.junit.Assert.*;
   4 |
   5 | import com.zaxxer.hikari.*;
   6 | import java.nio.charset.StandardCharsets;
   7 | import java.sql.*;
   8 | import org.flywaydb.core.Flyway;
   9 | import org.junit.Test;
  10 |
  11 | public class MigrationTest {
  12 |   @Test
  13 |   public void preservesLegacyNotesAndAppointmentsWithoutInventingSlots() throws Exception {
  14 |     String primary = System.getProperty("test.db.url", "");
  15 |     String legacy = "jdbc:h2:mem:legacy;MODE=MySQL;DATABASE_TO_LOWER=TRUE";
  16 |     if (primary.startsWith("jdbc:mysql:")) {
  17 |       if (!primary.contains("/hms_test"))
  18 |         throw new IllegalArgumentException("Use an isolated test database.");
  19 |       String database =
  20 |           "hms_test_legacy_" + java.util.UUID.randomUUID().toString().replace("-", "");
  21 |       try (Connection connection =
  22 |               DriverManager.getConnection(
  23 |                   primary,
  24 |                   System.getProperty("test.db.user", "root"),
  25 |                   System.getProperty("test.db.password", ""));
  26 |           Statement create = connection.createStatement()) {
  27 |         create.execute("CREATE DATABASE " + database);
  28 |       }
  29 |       int query = primary.indexOf('?');
  30 |       String suffix = query < 0 ? "" : primary.substring(query);
  31 |       legacy = primary.substring(0, primary.lastIndexOf('/') + 1) + database + suffix;
  32 |     }
  33 |     HikariConfig config = new HikariConfig();
  34 |     config.setJdbcUrl(legacy);
  35 |     config.setUsername(System.getProperty("test.db.user", "sa"));
  36 |     config.setPassword(System.getProperty("test.db.password", ""));
  37 |     try (HikariDataSource source = new HikariDataSource(config);
  38 |         Connection c = source.getConnection();
  39 |         Statement s = c.createStatement()) {
  40 |       String schema =
  41 |           new String(
  42 |               getClass().getResourceAsStream("/db/migration/V1__initial_schema.sql").readAllBytes(),
  43 |               StandardCharsets.UTF_8);
  44 |       for (String statement : schema.split(";")) if (!statement.isBlank()) s.execute(statement);
  45 |       s.execute("INSERT INTO user_dtls VALUES(1,'Legacy patient','legacy@test.test','legacy')");
  46 |       s.execute(
  47 |           "INSERT INTO doctor VALUES(1,'Legacy"
  48 |               + " doctor','1980-01-01','MD','General','legacy-doctor@test.test','9000000000','legacy')");
  49 |       s.execute(
  50 |           "INSERT INTO appointment VALUES(1,1,'Legacy"
  51 |               + " patient','other','20','2025-01-01','legacy@test.test','9000000000','Reason',1,'Address','Original"
  52 |               + " clinical note')");
  53 |       Flyway.configure()
  54 |           .dataSource(source)
  55 |           .baselineOnMigrate(true)
  56 |           .baselineVersion("0")
  57 |           .load()
  58 |           .migrate();
  59 |       try (ResultSet rs = s.executeQuery("SELECT status,slot_id FROM appointment WHERE id=1")) {
  60 |         rs.next();
  61 |         assertEquals("COMPLETED", rs.getString(1));
  62 |         assertNull(rs.getObject(2));
  63 |       }
  64 |       try (ResultSet rs =
  65 |           s.executeQuery("SELECT body FROM appointment_note WHERE appointment_id=1")) {
  66 |         rs.next();
  67 |         assertEquals("Original clinical note", rs.getString(1));
  68 |       }
  69 |     }
  70 |   }
  71 | }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Places the class in this Java namespace. Packages organize code; URL annotations define routes. |
| 3 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 5 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 6 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 7 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 8 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 9 | Imports the named type(s) or static helpers for compilation; this does not instantiate anything. |
| 11 | Declares the class and its inheritance/interface contract. See the file purpose above. |
| 12 | Declares a JUnit test method with assertions below. |
| 13 | Declares a constructor/helper. Arguments supply its input; its body defines the operation. |
| 14 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 15 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 16 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 17 | Branches on the shown condition; be able to explain both the success and rejection path. |
| 18 | Raises the indicated failure and stops the normal path; its enclosing catch controls feedback. |
| 19 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 20 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 21 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 22 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 23 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 24 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 25 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 26 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 27 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 29 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 30 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 31 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 33 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 34 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 35 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 36 | Maps data into a plain Java object; the setter itself does not write SQL. |
| 37 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 38 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 39 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 40 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 41 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 42 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 43 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 44 | Iterates the listed values/collection and executes the enclosed operations for each item. |
| 45 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 46 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 47 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 48 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 49 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 50 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 51 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 52 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 53 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 54 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 55 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 56 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 57 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 58 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 59 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 60 | Advances the cursor and checks whether a row exists. |
| 61 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Asserts the required test outcome; a failed assertion fails this unit test. |
| 62 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Asserts the required test outcome; a failed assertion fails this unit test. |
| 64 | Uses try-with-resources to close the declared JDBC/resource objects on normal or exceptional exit. |
| 65 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 66 | Advances the cursor and checks whether a row exists. |
| 67 | Reads the current database row. Numeric column indexes depend on SELECT/schema order. Asserts the required test outcome; a failed assertion fails this unit test. |

<a id="file-57"></a>

## 57. src/main/resources/db/migration/V1__initial_schema.sql

Application source; its annotations below explain the visible statements.

Source SHA-256: `c0a0b29aca0b05cd64d6a3d25d5f42771532cd210e2ecd80ca2a2f2ade5257f2`

<details>
<summary>Numbered source (36 physical lines)</summary>

~~~text
   1 | CREATE TABLE IF NOT EXISTS user_dtls (
   2 |   id INT AUTO_INCREMENT PRIMARY KEY,
   3 |   full_name VARCHAR(120) NOT NULL,
   4 |   email VARCHAR(254) NOT NULL UNIQUE,
   5 |   password VARCHAR(255) NOT NULL
   6 | );
   7 | CREATE TABLE IF NOT EXISTS specialist (
   8 |   id INT AUTO_INCREMENT PRIMARY KEY,
   9 |   spec_name VARCHAR(120) NOT NULL UNIQUE
  10 | );
  11 | CREATE TABLE IF NOT EXISTS doctor (
  12 |   id INT AUTO_INCREMENT PRIMARY KEY,
  13 |   full_name VARCHAR(120) NOT NULL,
  14 |   dob VARCHAR(10) NOT NULL,
  15 |   qualification VARCHAR(160) NOT NULL,
  16 |   specialist VARCHAR(120) NOT NULL,
  17 |   email VARCHAR(254) NOT NULL UNIQUE,
  18 |   mobno VARCHAR(20) NOT NULL,
  19 |   password VARCHAR(255) NOT NULL
  20 | );
  21 | CREATE TABLE IF NOT EXISTS appointment (
  22 |   id INT AUTO_INCREMENT PRIMARY KEY,
  23 |   user_id INT NOT NULL,
  24 |   fullname VARCHAR(120) NOT NULL,
  25 |   gender VARCHAR(30) NOT NULL,
  26 |   age VARCHAR(3) NOT NULL,
  27 |   appoint_date DATE NOT NULL,
  28 |   email VARCHAR(254) NOT NULL,
  29 |   phno VARCHAR(20) NOT NULL,
  30 |   diseases VARCHAR(500) NOT NULL,
  31 |   doctor_id INT NOT NULL,
  32 |   address VARCHAR(500) NOT NULL,
  33 |   status VARCHAR(1000) NOT NULL,
  34 |   FOREIGN KEY (user_id) REFERENCES user_dtls(id),
  35 |   FOREIGN KEY (doctor_id) REFERENCES doctor(id)
  36 | );
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Creates the named table only if absent. This does not migrate an existing incompatible schema. |
| 2 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 3 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 4 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 5 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 7 | Creates the named table only if absent. This does not migrate an existing incompatible schema. |
| 8 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 9 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 11 | Creates the named table only if absent. This does not migrate an existing incompatible schema. |
| 12 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 13 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 14 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 15 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 16 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 17 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 18 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 19 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 21 | Creates the named table only if absent. This does not migrate an existing incompatible schema. |
| 22 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 23 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 24 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 25 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 26 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 27 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 28 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 29 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 30 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 31 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 32 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 33 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 34 | Enforces the shown account/doctor relationship and rejects orphan appointments; referenced parent deletion is restricted by this schema. |
| 35 | Enforces the shown account/doctor relationship and rejects orphan appointments; referenced parent deletion is restricted by this schema. |

<a id="file-58"></a>

## 58. src/main/resources/db/migration/V2__scheduled_appointments.sql

Application source; its annotations below explain the visible statements.

Source SHA-256: `38fa14fdcc60000de066ce10e0b6f96a1f3e9545e61f3a22141de4166ae68b52`

<details>
<summary>Numbered source (62 physical lines)</summary>

~~~text
   1 | ALTER TABLE doctor ADD COLUMN active BOOLEAN NOT NULL DEFAULT TRUE;
   2 | ALTER TABLE doctor ADD COLUMN auth_version INT NOT NULL DEFAULT 0;
   3 | ALTER TABLE user_dtls ADD COLUMN auth_version INT NOT NULL DEFAULT 0;
   4 | CREATE TABLE doctor_slot (
   5 |  id BIGINT AUTO_INCREMENT PRIMARY KEY,
   6 |  doctor_id INT NOT NULL,
   7 |  starts_at TIMESTAMP NOT NULL,
   8 |  enabled BOOLEAN NOT NULL DEFAULT TRUE,
   9 |  CONSTRAINT uq_doctor_start UNIQUE (doctor_id, starts_at),
  10 |  FOREIGN KEY (doctor_id) REFERENCES doctor(id)
  11 | );
  12 | ALTER TABLE appointment ADD COLUMN slot_id BIGINT NULL;
  13 | ALTER TABLE appointment ADD COLUMN created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
  14 | ALTER TABLE appointment ADD CONSTRAINT fk_appointment_slot FOREIGN KEY (slot_id) REFERENCES doctor_slot(id);
  15 | CREATE TABLE appointment_note (
  16 |  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  17 |  appointment_id INT NOT NULL,
  18 |  doctor_id INT NOT NULL,
  19 |  body VARCHAR(1000) NOT NULL,
  20 |  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  21 |  FOREIGN KEY (appointment_id) REFERENCES appointment(id),
  22 |  FOREIGN KEY (doctor_id) REFERENCES doctor(id)
  23 | );
  24 | INSERT INTO appointment_note(appointment_id,doctor_id,body)
  25 |  SELECT id,doctor_id,status FROM appointment
  26 |  WHERE UPPER(status) NOT IN ('PENDING','CONFIRMED','COMPLETED','CANCELLED');
  27 | UPDATE appointment SET status = CASE
  28 |  WHEN UPPER(status) IN ('PENDING','CONFIRMED','COMPLETED','CANCELLED') THEN UPPER(status)
  29 |  ELSE 'COMPLETED' END;
  30 | ALTER TABLE appointment MODIFY COLUMN status VARCHAR(16) NOT NULL;
  31 | ALTER TABLE appointment ADD CONSTRAINT ck_appointment_status CHECK (status IN ('PENDING','CONFIRMED','COMPLETED','CANCELLED'));
  32 | CREATE TABLE slot_reservation (
  33 |  slot_id BIGINT PRIMARY KEY,
  34 |  appointment_id INT NOT NULL UNIQUE,
  35 |  FOREIGN KEY (slot_id) REFERENCES doctor_slot(id),
  36 |  FOREIGN KEY (appointment_id) REFERENCES appointment(id)
  37 | );
  38 | CREATE TABLE booking_request (
  39 |  user_id INT NOT NULL,
  40 |  request_key VARCHAR(64) NOT NULL,
  41 |  payload_hash VARCHAR(64) NOT NULL,
  42 |  appointment_id INT NULL,
  43 |  PRIMARY KEY (user_id,request_key),
  44 |  FOREIGN KEY (user_id) REFERENCES user_dtls(id),
  45 |  FOREIGN KEY (appointment_id) REFERENCES appointment(id)
  46 | );
  47 | CREATE TABLE appointment_event (
  48 |  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  49 |  appointment_id INT NOT NULL,
  50 |  actor_role VARCHAR(16) NOT NULL,
  51 |  actor_id INT NOT NULL,
  52 |  from_status VARCHAR(16) NULL,
  53 |  to_status VARCHAR(16) NOT NULL,
  54 |  action VARCHAR(40) NOT NULL,
  55 |  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  56 |  FOREIGN KEY (appointment_id) REFERENCES appointment(id)
  57 | );
  58 | CREATE INDEX ix_appointment_user ON appointment(user_id,id);
  59 | CREATE INDEX ix_appointment_doctor ON appointment(doctor_id,id);
  60 | CREATE INDEX ix_note_appointment ON appointment_note(appointment_id,id);
  61 | CREATE INDEX ix_event_appointment ON appointment_event(appointment_id,id);
  62 | CREATE INDEX ix_slot_available ON doctor_slot(starts_at,doctor_id);
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 2 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 3 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 4 | Creates the named table only if absent. This does not migrate an existing incompatible schema. |
| 5 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 6 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 7 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 8 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 9 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 10 | Enforces the shown account/doctor relationship and rejects orphan appointments; referenced parent deletion is restricted by this schema. |
| 12 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 13 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 14 | Enforces the shown account/doctor relationship and rejects orphan appointments; referenced parent deletion is restricted by this schema. |
| 15 | Creates the named table only if absent. This does not migrate an existing incompatible schema. |
| 16 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 17 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 18 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 19 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 20 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 21 | Enforces the shown account/doctor relationship and rejects orphan appointments; referenced parent deletion is restricted by this schema. |
| 22 | Enforces the shown account/doctor relationship and rejects orphan appointments; referenced parent deletion is restricted by this schema. |
| 24 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 25 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 26 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 27 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 28 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 29 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 30 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 31 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 32 | Creates the named table only if absent. This does not migrate an existing incompatible schema. |
| 33 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 34 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 35 | Enforces the shown account/doctor relationship and rejects orphan appointments; referenced parent deletion is restricted by this schema. |
| 36 | Enforces the shown account/doctor relationship and rejects orphan appointments; referenced parent deletion is restricted by this schema. |
| 38 | Creates the named table only if absent. This does not migrate an existing incompatible schema. |
| 39 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 40 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 41 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 42 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 43 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 44 | Enforces the shown account/doctor relationship and rejects orphan appointments; referenced parent deletion is restricted by this schema. |
| 45 | Enforces the shown account/doctor relationship and rejects orphan appointments; referenced parent deletion is restricted by this schema. |
| 47 | Creates the named table only if absent. This does not migrate an existing incompatible schema. |
| 48 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 49 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 50 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 51 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 52 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 53 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 54 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 55 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 56 | Enforces the shown account/doctor relationship and rejects orphan appointments; referenced parent deletion is restricted by this schema. |
| 58 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 59 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 60 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 61 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 62 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |

<a id="file-59"></a>

## 59. src/main/resources/db/migration/V3__integrity_and_credential_storage.sql

Application source; its annotations below explain the visible statements.

Source SHA-256: `0235922d1c5c83afc0b26ffaef24efee5722c34be2c1674c2845913bc55485a6`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | ALTER TABLE user_dtls MODIFY COLUMN password VARCHAR(255) NOT NULL;
   2 | ALTER TABLE doctor MODIFY COLUMN password VARCHAR(255) NOT NULL;
   3 | ALTER TABLE doctor_slot ADD CONSTRAINT uq_slot_doctor UNIQUE(id,doctor_id);
   4 | ALTER TABLE appointment ADD CONSTRAINT fk_slot_doctor FOREIGN KEY(slot_id,doctor_id) REFERENCES doctor_slot(id,doctor_id);
   5 | ALTER TABLE appointment ADD CONSTRAINT uq_appointment_slot UNIQUE(id,slot_id);
   6 | ALTER TABLE slot_reservation ADD CONSTRAINT fk_reserved_appointment_slot FOREIGN KEY(appointment_id,slot_id) REFERENCES appointment(id,slot_id);
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 2 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 3 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 4 | Enforces the shown account/doctor relationship and rejects orphan appointments; referenced parent deletion is restricted by this schema. |
| 5 | Defines the named column with its SQL type, length, requiredness, uniqueness or generated primary-key rule as shown. See the main guide for each column meaning. |
| 6 | Enforces the shown account/doctor relationship and rejects orphan appointments; referenced parent deletion is restricted by this schema. |

<a id="file-60"></a>

## 60. Dockerfile

Application source; its annotations below explain the visible statements.

Source SHA-256: `e7c2f26a2276d4d00daee0ada191a4eac7e68c50dd080538e80109dc5ec8e67f`

<details>
<summary>Numbered source (18 physical lines)</summary>

~~~text
   1 | FROM maven:3.9.9-eclipse-temurin-17 AS build
   2 | WORKDIR /app
   3 | COPY pom.xml .
   4 | RUN mvn -B -q dependency:go-offline
   5 | COPY src ./src
   6 | RUN mvn -B clean verify
   7 |
   8 | FROM tomcat:10.1.61-jdk17-temurin
   9 | RUN apt-get update && apt-get install -y --no-install-recommends curl \
  10 |     && rm -rf /var/lib/apt/lists/* /usr/local/tomcat/webapps/* \
  11 |     && groupadd --gid 10001 hospital && useradd --uid 10001 --gid hospital --no-create-home hospital
  12 | COPY deploy/server.xml /usr/local/tomcat/conf/server.xml
  13 | COPY --from=build /app/target/Hospital_Management_System.war /usr/local/tomcat/webapps/ROOT.war
  14 | RUN chown -R hospital:hospital /usr/local/tomcat
  15 | USER 10001:10001
  16 | EXPOSE 8080
  17 | HEALTHCHECK --interval=10s --timeout=3s --start-period=40s --retries=12 CMD curl --fail --silent http://127.0.0.1:8080/health/ready || exit 1
  18 | CMD ["catalina.sh","run"]
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Defines the container build/runtime instruction shown. Build stages package/test the WAR; the runtime is non-root and uses a readiness probe. |
| 2 | Defines the container build/runtime instruction shown. Build stages package/test the WAR; the runtime is non-root and uses a readiness probe. |
| 3 | Defines the container build/runtime instruction shown. Build stages package/test the WAR; the runtime is non-root and uses a readiness probe. |
| 4 | Defines the container build/runtime instruction shown. Build stages package/test the WAR; the runtime is non-root and uses a readiness probe. |
| 5 | Defines the container build/runtime instruction shown. Build stages package/test the WAR; the runtime is non-root and uses a readiness probe. |
| 6 | Defines the container build/runtime instruction shown. Build stages package/test the WAR; the runtime is non-root and uses a readiness probe. |
| 8 | Defines the container build/runtime instruction shown. Build stages package/test the WAR; the runtime is non-root and uses a readiness probe. |
| 9 | Defines the container build/runtime instruction shown. Build stages package/test the WAR; the runtime is non-root and uses a readiness probe. |
| 10 | Defines the container build/runtime instruction shown. Build stages package/test the WAR; the runtime is non-root and uses a readiness probe. |
| 11 | Defines the container build/runtime instruction shown. Build stages package/test the WAR; the runtime is non-root and uses a readiness probe. |
| 12 | Defines the container build/runtime instruction shown. Build stages package/test the WAR; the runtime is non-root and uses a readiness probe. |
| 13 | Defines the container build/runtime instruction shown. Build stages package/test the WAR; the runtime is non-root and uses a readiness probe. |
| 14 | Defines the container build/runtime instruction shown. Build stages package/test the WAR; the runtime is non-root and uses a readiness probe. |
| 15 | Defines the container build/runtime instruction shown. Build stages package/test the WAR; the runtime is non-root and uses a readiness probe. |
| 16 | Defines the container build/runtime instruction shown. Build stages package/test the WAR; the runtime is non-root and uses a readiness probe. |
| 17 | Defines the container build/runtime instruction shown. Build stages package/test the WAR; the runtime is non-root and uses a readiness probe. |
| 18 | Defines the container build/runtime instruction shown. Build stages package/test the WAR; the runtime is non-root and uses a readiness probe. |

<a id="file-61"></a>

## 61. compose.yaml

Application source; its annotations below explain the visible statements.

Source SHA-256: `336ecc613f2a6256049deab21b44e26ac1e0e8ecdfb6995f105a7f43436dd8a6`

<details>
<summary>Numbered source (37 physical lines)</summary>

~~~text
   1 | services:
   2 |   db:
   3 |     image: mysql:8.4
   4 |     environment:
   5 |       MYSQL_DATABASE: hospital_1
   6 |       MYSQL_USER: hospital
   7 |       MYSQL_PASSWORD: ${DB_PASSWORD:?Set DB_PASSWORD in .env}
   8 |       MYSQL_ROOT_PASSWORD: ${MYSQL_ROOT_PASSWORD:?Set MYSQL_ROOT_PASSWORD in .env}
   9 |     volumes:
  10 |       - hospital-db:/var/lib/mysql
  11 |     healthcheck:
  12 |       test: ["CMD-SHELL", "MYSQL_PWD=$$MYSQL_ROOT_PASSWORD mysql -h 127.0.0.1 -u root -e 'SELECT 1' >/dev/null 2>&1"]
  13 |       interval: 5s
  14 |       timeout: 3s
  15 |       retries: 30
  16 |   app:
  17 |     build: .
  18 |     ports:
  19 |       - "127.0.0.1:${APP_PORT:-8080}:8080"
  20 |     environment:
  21 |       DB_URL: jdbc:mysql://db:3306/hospital_1?allowPublicKeyRetrieval=true&useSSL=false
  22 |       DB_USER: hospital
  23 |       DB_PASSWORD: ${DB_PASSWORD:?Set DB_PASSWORD in .env}
  24 |       DB_POOL_SIZE: ${DB_POOL_SIZE:-10}
  25 |       DB_MIGRATE: "true"
  26 |       DEMO_MODE: ${DEMO_MODE:-false}
  27 |       ADMIN_EMAIL: ${ADMIN_EMAIL:?Set ADMIN_EMAIL in .env}
  28 |       ADMIN_PASSWORD_HASH: ${ADMIN_PASSWORD_HASH:?Set ADMIN_PASSWORD_HASH in .env}
  29 |       HOSPITAL_ZONE: ${HOSPITAL_ZONE:-Asia/Kolkata}
  30 |       SECURE_COOKIES: ${SECURE_COOKIES:-false}
  31 |       TZ: UTC
  32 |     depends_on:
  33 |       db:
  34 |         condition: service_healthy
  35 |     restart: on-failure:3
  36 | volumes:
  37 |   hospital-db:
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 2 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 3 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 4 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 5 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 6 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 7 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 8 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 9 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 10 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 11 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 12 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 13 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 14 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 15 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 16 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 17 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 18 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 19 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 20 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 21 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 22 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 23 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 24 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 25 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 26 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 27 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 28 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 29 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 30 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 31 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 32 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 33 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 34 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 35 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 36 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 37 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |

<a id="file-62"></a>

## 62. deploy/server.xml

Application source; its annotations below explain the visible statements.

Source SHA-256: `5329d3512a217a798ae814cb76eadf7c137045b218f928bbd9d865859ed6994f`

<details>
<summary>Numbered source (11 physical lines)</summary>

~~~text
   1 | <?xml version="1.0" encoding="UTF-8"?>
   2 | <Server port="-1">
   3 |  <Service name="Catalina">
   4 |   <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" maxPostSize="16384" maxParameterCount="50" server="Hospital"/>
   5 |   <Engine name="Catalina" defaultHost="localhost">
   6 |    <Host name="localhost" appBase="webapps" unpackWARs="true" autoDeploy="false">
   7 |     <Valve className="org.apache.catalina.valves.ErrorReportValve" showReport="false" showServerInfo="false"/>
   8 |    </Host>
   9 |   </Engine>
  10 |  </Service>
  11 | </Server>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 2 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 3 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 4 | Configures the HTTP connector, request size/count limits and supplied deployment settings. |
| 5 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 6 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 7 | Suppresses detailed Tomcat error reports and server information on container error pages. |
| 8 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 9 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 10 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |
| 11 | Defines the XML build/container/deployment element shown; the enclosing project, profile or server block supplies its context. |

<a id="file-63"></a>

## 63. .github/workflows/ci.yml

Application source; its annotations below explain the visible statements.

Source SHA-256: `10ea81bcb48de123dc1180f1e92c12f3c6b51fb3b704d81fa37bf6f5dd8e8d44`

<details>
<summary>Numbered source (58 physical lines)</summary>

~~~text
   1 | name: Build, MySQL contracts, and deployed smoke tests
   2 | on:
   3 |   push:
   4 |   pull_request:
   5 | permissions:
   6 |   contents: read
   7 | jobs:
   8 |   verify:
   9 |     runs-on: ubuntu-24.04
  10 |     services:
  11 |       mysql:
  12 |         image: mysql:8.4
  13 |         env:
  14 |           MYSQL_ROOT_PASSWORD: ci-test-password
  15 |           MYSQL_DATABASE: hms_test
  16 |         ports: ["3306:3306"]
  17 |         options: >-
  18 |           --health-cmd="mysqladmin ping -h localhost --silent"
  19 |           --health-interval=5s --health-timeout=3s --health-retries=30
  20 |     steps:
  21 |       - uses: actions/checkout@v7
  22 |         with:
  23 |           persist-credentials: false
  24 |       - uses: actions/setup-java@v6
  25 |         with:
  26 |           distribution: temurin
  27 |           java-version: "17"
  28 |           cache: maven
  29 |       - name: H2 contracts and legacy migration
  30 |         run: mvn -B clean verify
  31 |       - name: Same contract against MySQL 8.4
  32 |         run: mvn -B test "-Dtest.db.url=jdbc:mysql://127.0.0.1:3306/hms_test?allowPublicKeyRetrieval=true&useSSL=false" -Dtest.db.user=root -Dtest.db.password=ci-test-password
  33 |       - name: Configure disposable container demo
  34 |         run: |
  35 |           mkdir -p .local
  36 |           HASH=$(printf '%s\n' 'AdminDemo@123' | java -cp target/classes com.security.Passwords)
  37 |           python3 - "$HASH" <<'PY'
  38 |           import sys
  39 |           from pathlib import Path
  40 |           Path(".local/ci.env").write_text(
  41 |               "DB_PASSWORD=ci-application-password\nMYSQL_ROOT_PASSWORD=ci-root-password\n"
  42 |               "ADMIN_EMAIL=admin@hospital.test\nADMIN_PASSWORD_HASH='"+sys.argv[1]+"'\n"
  43 |               "DEMO_MODE=true\nAPP_PORT=8082\n")
  44 |           PY
  45 |       - name: Build and run the actual WAR in Tomcat and MySQL
  46 |         run: docker compose --env-file .local/ci.env up --build --wait --wait-timeout 240
  47 |       - name: Full HTTP workflow and race checks
  48 |         env:
  49 |           HMS_TEST_ADMIN_PASSWORD: AdminDemo@123
  50 |         run: python3 scripts/smoke_test.py http://localhost:8082
  51 |       - name: Container diagnostics
  52 |         if: failure()
  53 |         run: |
  54 |           if [ -f .local/ci.env ]; then docker compose --env-file .local/ci.env logs --tail=150; fi
  55 |       - name: Remove disposable CI containers and data
  56 |         if: always()
  57 |         run: |
  58 |           if [ -f .local/ci.env ]; then docker compose --env-file .local/ci.env down --volumes; fi
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 2 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 3 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 4 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 5 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 6 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 7 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 8 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 9 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 10 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 11 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 12 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 13 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 14 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 15 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 16 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 17 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 18 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 19 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 20 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 21 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 22 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 23 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 24 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 25 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 26 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 27 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 28 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 29 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 30 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 31 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 32 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 33 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 34 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 35 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 36 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 37 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 38 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 39 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 40 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 41 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 42 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 43 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 44 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 45 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 46 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 47 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 48 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 49 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 50 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 51 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 52 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 53 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 54 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 55 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 56 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 57 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |
| 58 | Configures the deployment/CI key or command shown. Variables are supplied by the environment; the surrounding service/job determines execution order. |

<a id="file-64"></a>

## 64. scripts/benchmark.py

Application source; its annotations below explain the visible statements.

Source SHA-256: `ee34ac80ee22a755d6d69d2ac025488bdf081a8a7ed5d58f5d1428752a04767c`

<details>
<summary>Numbered source (69 physical lines)</summary>

~~~text
   1 | #!/usr/bin/env python3
   2 | """Reproducible authenticated read workload against the local demo; no third-party dependencies."""
   3 | import argparse
   4 | import concurrent.futures
   5 | import datetime
   6 | import json
   7 | import platform
   8 | import statistics
   9 | import sys
  10 | import time
  11 | import uuid
  12 | from pathlib import Path
  13 | import smoke_test
  14 |
  15 | def main():
  16 |     parser=argparse.ArgumentParser()
  17 |     parser.add_argument("--base",default="http://localhost:8081")
  18 |     parser.add_argument("--users",type=int,default=8)
  19 |     parser.add_argument("--requests",type=int,default=50,help="Requests per user")
  20 |     parser.add_argument("--duration",type=int,default=0,help="Sustained seconds instead of a fixed request count; max 60")
  21 |     parser.add_argument("--output",default="docs/performance/http.json")
  22 |     args=parser.parse_args()
  23 |     if not 0<=args.duration<=60:parser.error("Use duration 0-60 seconds")
  24 |     if not 1<=args.users<=30 or not 1<=args.requests<=1000:parser.error("Use 1-30 users and 1-1000 requests per user")
  25 |     smoke_test.BASE=args.base
  26 |     assert __import__("urllib.parse",fromlist=["urlparse"]).urlparse(args.base).hostname in ("localhost","127.0.0.1")
  27 |     assert "Local project demo" in smoke_test.Session().request("/")[2]
  28 |     sessions=[]
  29 |     for _ in range(args.users):
  30 |         session=smoke_test.Session();session.request("/signup.jsp")
  31 |         email="benchmark-"+uuid.uuid4().hex+"@hospital.test"
  32 |         assert session.request("/register",{"Fullname":"Synthetic benchmark user","email":email,"password":"Benchmark@123"})[0]==200
  33 |         assert smoke_test.login(session,"patient",email,"Benchmark@123")[1].endswith("view_appointment.jsp")
  34 |         for path in ("/api/slots?doctorId=1","/api/appointments?page=1"):
  35 |             assert session.request(path)[0]==200
  36 |         sessions.append(session)
  37 |     def worker(session):
  38 |         samples=[];errors=0
  39 |         i=0
  40 |         while (time.perf_counter()<deadline if args.duration else i<args.requests):
  41 |             path="/api/slots?doctorId=1" if i%2==0 else "/api/appointments?page=1"
  42 |             start=time.perf_counter()
  43 |             try:
  44 |                 status=session.request(path)[0]
  45 |                 if status!=200:errors+=1
  46 |             except Exception:errors+=1
  47 |             samples.append((time.perf_counter()-start)*1000)
  48 |             i+=1
  49 |         return samples,errors
  50 |     started=time.perf_counter()
  51 |     deadline=started+args.duration
  52 |     with concurrent.futures.ThreadPoolExecutor(max_workers=args.users) as pool:
  53 |         results=list(pool.map(worker,sessions))
  54 |     elapsed=time.perf_counter()-started
  55 |     samples=sorted(x for batch,_ in results for x in batch)
  56 |     errors=sum(n for _,n in results)
  57 |     def percentile(p):return samples[min(len(samples)-1,int((len(samples)-1)*p))]
  58 |     report={"recorded_at_utc":datetime.datetime.now(datetime.timezone.utc).isoformat(),"base":args.base,
  59 |         "python":platform.python_version(),"machine":platform.machine(),"users":args.users,"requests":len(samples),
  60 |         "endpoints":["/api/slots?doctorId=1","/api/appointments?page=1"],
  61 |         "setup_and_warmup_excluded":True,"dataset":"Four demo doctors, 14 days of slots; new users have empty appointment histories.",
  62 |         "requested_duration_seconds":args.duration,"elapsed_seconds":round(elapsed,3),"throughput_requests_per_second":round(len(samples)/elapsed,2),
  63 |         "latency_ms":{"mean":round(statistics.mean(samples),2),"p50":round(percentile(.50),2),"p95":round(percentile(.95),2),"p99":round(percentile(.99),2)},
  64 |         "errors":errors,"error_rate":errors/len(samples),
  65 |         "limits":"Closed-loop localhost read workload with Python threads and one server. Not a production capacity, write-throughput, or internet-latency claim."}
  66 |     target=Path(args.output);target.parent.mkdir(parents=True,exist_ok=True);target.write_text(json.dumps(report,indent=2)+"\n")
  67 |     print(json.dumps(report,indent=2))
  68 |     return 1 if errors else 0
  69 | if __name__=="__main__":sys.exit(main())
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Documents the local integration test and its operational scope. |
| 2 | Documents the local integration test and its operational scope. |
| 3 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 4 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 5 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 6 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 7 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 8 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 9 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 10 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 11 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 12 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 13 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 15 | Defines the named HTTP/test helper; its body executes when called. |
| 16 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 17 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 18 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 19 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 20 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 21 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 22 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 23 | Controls the explicit success/error branch shown. |
| 24 | Controls the explicit success/error branch shown. |
| 25 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 26 | Rejects a failed test/safety precondition; synthetic workflows are intended only for the local demo. |
| 27 | Rejects a failed test/safety precondition; synthetic workflows are intended only for the local demo. |
| 28 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 29 | Repeats the listed route/workflow checks. |
| 30 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 31 | Creates a unique synthetic test value so reruns do not collide with old accounts or requests. |
| 32 | Rejects a failed test/safety precondition; synthetic workflows are intended only for the local demo. |
| 33 | Rejects a failed test/safety precondition; synthetic workflows are intended only for the local demo. |
| 34 | Repeats the listed route/workflow checks. |
| 35 | Rejects a failed test/safety precondition; synthetic workflows are intended only for the local demo. |
| 36 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 37 | Defines the named HTTP/test helper; its body executes when called. |
| 38 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 39 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 40 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 41 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 42 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 43 | Controls the explicit success/error branch shown. |
| 44 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 45 | Controls the explicit success/error branch shown. |
| 46 | Controls the explicit success/error branch shown. |
| 47 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 48 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 49 | Returns this helper result to its caller. |
| 50 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 51 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 52 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 53 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 54 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 55 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 56 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 57 | Defines the named HTTP/test helper; its body executes when called. |
| 58 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 59 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 60 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 61 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 62 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 63 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 64 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 65 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 66 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 67 | Prints progress or the final count to the test terminal. |
| 68 | Returns this helper result to its caller. |
| 69 | Controls the explicit success/error branch shown. |

<a id="file-65"></a>

## 65. src/main/webapp/WEB-INF/views/admin/appointment.jsp

Server-rendered admin view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `84071e5c740975808cc44d90f6b64ece0919230987b8dc89771afe87b893a3b3`

<details>
<summary>Numbered source (3 physical lines)</summary>

~~~text
   1 | <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Appointment details | HMS</title><%@include file="../component/allcss.jsp"%></head><body><%@include file="navbar.jsp"%><main id="main-content" class="page-main"><div class="container"><%@include file="../component/appointment_detail.jsp"%></div></main></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Includes shared ../component/allcss.jsp markup at JSP translation time. Includes shared navbar.jsp markup at JSP translation time. Includes shared ../component/appointment_detail.jsp markup at JSP translation time. Defines document encoding, viewport or description metadata. Sets the browser tab title. |

<a id="file-66"></a>

## 66. src/main/webapp/WEB-INF/views/admin/doctor.jsp

Server-rendered admin view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `4afcaad640c93c98cbd4b1f418fa89dea39a4810cb31e9904569db08509cf5cb`

<details>
<summary>Numbered source (3 physical lines)</summary>

~~~text
   1 | <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Add doctor | HMS</title><%@include file="../component/allcss.jsp"%></head><body><%@include file="navbar.jsp"%><main id="main-content" class="page-main"><div class="container"><h1 class="page-title">Add a doctor</h1><section class="form-panel"><form action="../addDoctor" method="post"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><input type="hidden" name="id" value="${doctor.id}"><%@include file="../component/doctor_form.jsp"%><button class="btn btn-primary mt-4">Save doctor</button></form></section></div></main></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Includes shared ../component/allcss.jsp markup at JSP translation time. Includes shared navbar.jsp markup at JSP translation time. Includes shared ../component/doctor_form.jsp markup at JSP translation time. Creates a POST form targeting ../addDoctor; named controls become servlet parameters. Echoes the session CSRF token in a hidden control for the filter to check on POST. Defines submitted field(s) id; their names must match the handler keys. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Defines document encoding, viewport or description metadata. Sets the browser tab title. |

<a id="file-67"></a>

## 67. src/main/webapp/WEB-INF/views/admin/edit_doctor.jsp

Server-rendered admin view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `5d18e660541c8ebdbf8fcbc77179b61aa5cc4780515f8ff61e5812179cf33d84`

<details>
<summary>Numbered source (3 physical lines)</summary>

~~~text
   1 | <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Edit doctor | HMS</title><%@include file="../component/allcss.jsp"%></head><body><%@include file="navbar.jsp"%><main id="main-content" class="page-main"><div class="container"><h1 class="page-title">Edit doctor</h1><section class="form-panel"><form action="../updateDoctor" method="post"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><input type="hidden" name="id" value="${doctor.id}"><%@include file="../component/doctor_form.jsp"%><button class="btn btn-primary mt-4">Save doctor</button></form></section></div></main></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Includes shared ../component/allcss.jsp markup at JSP translation time. Includes shared navbar.jsp markup at JSP translation time. Includes shared ../component/doctor_form.jsp markup at JSP translation time. Creates a POST form targeting ../updateDoctor; named controls become servlet parameters. Echoes the session CSRF token in a hidden control for the filter to check on POST. Defines submitted field(s) id; their names must match the handler keys. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Defines document encoding, viewport or description metadata. Sets the browser tab title. |

<a id="file-68"></a>

## 68. src/main/webapp/WEB-INF/views/admin/index.jsp

Server-rendered admin view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `472192eddb63f998bed12b463ed32b7e909759026056d754012a2bb948726b3a`

<details>
<summary>Numbered source (5 physical lines)</summary>

~~~text
   1 | <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Administration | HMS</title><%@include file="../component/allcss.jsp"%></head><body><%@include file="navbar.jsp"%><main id="main-content" class="page-main"><div class="container"><h1 class="page-title">Hospital overview</h1><p>Live database aggregates, with historical appointments retained.</p><%@include file="../component/messages.jsp"%><div class="dashboard-grid">
   4 | <a href="view_doctor.jsp"><span>Active doctors</span><strong>${stats.doctor}</strong><span>Manage doctors</span></a><div><span>Patient accounts</span><strong>${stats.user_dtls}</strong></div><a href="patient.jsp"><span>Appointments</span><strong>${stats.appointment}</strong><span>View records</span></a><a href="slots.jsp"><span>Specialties</span><strong>${stats.specialist}</strong><span>Manage availability</span></a></div>
   5 | <section class="form-panel mt-4"><h2>Specialties</h2><div class="specialty-tags"><c:forEach var="s" items="${specialties}"><span><c:out value="${s.specialistName}"/></span></c:forEach></div><form action="../addSpecialist" method="post" class="specialty-form"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><div><label for="spec" class="form-label">New specialty</label><input id="spec" name="specName" class="form-control" maxlength="120" required></div><button class="btn btn-primary">Add specialty</button></form></section></div></main></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Includes shared ../component/allcss.jsp markup at JSP translation time. Includes shared navbar.jsp markup at JSP translation time. Includes shared ../component/messages.jsp markup at JSP translation time. Defines document encoding, viewport or description metadata. Sets the browser tab title. |
| 4 | Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Provides navigation/resource links to view_doctor.jsp, patient.jsp, slots.jsp. |
| 5 | Creates a POST form targeting ../addSpecialist; named controls become servlet parameters. Echoes the session CSRF token in a hidden control for the filter to check on POST. Defines submitted field(s) specName; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Renders repeated options/rows from the supplied collection. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |

<a id="file-69"></a>

## 69. src/main/webapp/WEB-INF/views/admin/navbar.jsp

Server-rendered admin view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `5f6a75c192871f94ee542829e29e806d872474f54337ce9e9194853bf54a2604`

<details>
<summary>Numbered source (10 physical lines)</summary>

~~~text
   1 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   2 | <nav class="navbar navbar-expand-lg hms-nav" aria-label="Administration navigation"><div class="container">
   3 | <a class="navbar-brand" href="index.jsp"><span class="brand-mark" aria-hidden="true">+</span><span>HMS<span class="brand-sub">Administration</span></span></a>
   4 | <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#portal-nav" aria-controls="portal-nav" aria-expanded="false" aria-label="Toggle navigation"><i class="fa-solid fa-bars" aria-hidden="true"></i></button>
   5 | <div class="collapse navbar-collapse" id="portal-nav"><ul class="navbar-nav ms-auto align-items-lg-center gap-lg-3">
   6 | <li class="nav-item"><a class="nav-link" href="index.jsp">Overview</a></li>
   7 | <li class="nav-item"><a class="nav-link" href="view_doctor.jsp">Doctors</a></li><li class="nav-item"><a class="nav-link" href="doctor.jsp">Add doctor</a></li>
   8 | <li class="nav-item"><a class="nav-link" href="patient.jsp">Appointments</a></li><li class="nav-item"><a class="nav-link" href="slots.jsp">Slots</a></li>
   9 | <li class="nav-item"><form action="../adminLogout" method="post"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><button class="nav-link account-toggle">Sign out</button></form></li>
  10 | </ul></div></div></nav>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Enables the c-prefixed JSTL core library. |
| 2 | Adds assistive-technology/navigation/feedback semantics where shown. |
| 3 | Provides navigation/resource links to index.jsp. Adds assistive-technology/navigation/feedback semantics where shown. |
| 4 | Adds assistive-technology/navigation/feedback semantics where shown. Connects markup to Bootstrap collapse/dropdown/modal behavior. |
| 5 | Outputs div, ul HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 6 | Provides navigation/resource links to index.jsp. |
| 7 | Provides navigation/resource links to view_doctor.jsp, doctor.jsp. |
| 8 | Provides navigation/resource links to patient.jsp, slots.jsp. |
| 9 | Creates a POST form targeting ../adminLogout; named controls become servlet parameters. Echoes the session CSRF token in a hidden control for the filter to check on POST. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 10 | Closes the matching HTML/JSTL element or conditional/loop structure. |

<a id="file-70"></a>

## 70. src/main/webapp/WEB-INF/views/admin/patient.jsp

Server-rendered admin view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `86af8954f6bd1dd343b3f315e42c24137e69dda6f03946354ba035d2836ed560`

<details>
<summary>Numbered source (3 physical lines)</summary>

~~~text
   1 | <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Hospital appointments | HMS</title><%@include file="../component/allcss.jsp"%></head><body><%@include file="navbar.jsp"%><main id="main-content" class="page-main"><div class="container"><h1 class="page-title">Hospital appointments</h1><p class="page-intro">Track reserved slots, appointment status, and consultation notes. Times use the hospital time zone.</p><%@include file="../component/messages.jsp"%><section class="appointments-panel"><%@include file="../component/appointment_table.jsp"%></section></div></main></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Includes shared ../component/allcss.jsp markup at JSP translation time. Includes shared navbar.jsp markup at JSP translation time. Includes shared ../component/messages.jsp markup at JSP translation time. Includes shared ../component/appointment_table.jsp markup at JSP translation time. Defines document encoding, viewport or description metadata. Sets the browser tab title. |

<a id="file-71"></a>

## 71. src/main/webapp/WEB-INF/views/admin/slots.jsp

Server-rendered admin view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `9de934659999fa128c7c5fb3b2f5f7684909e027939b0236ad08e766821273ce`

<details>
<summary>Numbered source (5 physical lines)</summary>

~~~text
   1 | <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Manage slots | HMS</title><%@include file="../component/allcss.jsp"%></head><body><%@include file="navbar.jsp"%><main id="main-content" class="page-main"><div class="container"><h1 class="page-title">Manage appointment slots</h1><p>Slots last 30 minutes. Choose a future :00 or :30 start within 90 days. Time zone: <c:out value="${hospitalZone}"/>.</p><%@include file="../component/messages.jsp"%>
   4 | <section class="form-panel"><h2>Publish a slot</h2><form action="../publishSlot" method="post" class="row g-3"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><div class="col-md-5"><label class="form-label" for="doctor">Doctor</label><select name="doctorId" id="doctor" class="form-select" required><c:forEach var="d" items="${doctors}"><option value="${d.id}"><c:out value="${d.fullName}"/></option></c:forEach></select></div><div class="col-md-5"><label class="form-label" for="start">Start date and time</label><input id="start" name="startsAt" type="datetime-local" step="1800" class="form-control" required></div><div class="col-md-2 d-flex align-items-end"><button class="btn btn-primary">Publish</button></div></form></section>
   5 | <section class="appointments-panel mt-4"><h2>Available published slots</h2><p>Reserved and withdrawn slots are excluded. Cancel an appointment from its details page to release its slot.</p><c:forEach var="slot" items="${slots}"><div class="d-flex justify-content-between align-items-center gap-3 mb-3"><span><c:out value="${slot.label}"/></span><form action="../withdrawSlot" method="post"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><input type="hidden" name="slotId" value="${slot.id}"><button class="btn btn-sm btn-outline-primary">Withdraw</button></form></div></c:forEach></section></div></main></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Includes shared ../component/allcss.jsp markup at JSP translation time. Includes shared navbar.jsp markup at JSP translation time. Includes shared ../component/messages.jsp markup at JSP translation time. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Defines document encoding, viewport or description metadata. Sets the browser tab title. |
| 4 | Creates a POST form targeting ../publishSlot; named controls become servlet parameters. Echoes the session CSRF token in a hidden control for the filter to check on POST. Defines submitted field(s) doctorId, startsAt; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Renders repeated options/rows from the supplied collection. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 5 | Creates a POST form targeting ../withdrawSlot; named controls become servlet parameters. Echoes the session CSRF token in a hidden control for the filter to check on POST. Defines submitted field(s) slotId; their names must match the handler keys. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Renders repeated options/rows from the supplied collection. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |

<a id="file-72"></a>

## 72. src/main/webapp/WEB-INF/views/admin/view_doctor.jsp

Server-rendered admin view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `14ef03eecd28b90b3976ddd8a0a3e4198aa17017c425e893e73cef5dcb2c6c49`

<details>
<summary>Numbered source (3 physical lines)</summary>

~~~text
   1 | <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Manage doctors | HMS</title><%@include file="../component/allcss.jsp"%></head><body><%@include file="navbar.jsp"%><main id="main-content" class="page-main"><div class="container"><div class="appointments-heading"><h1 class="page-title">Doctors</h1><a class="btn btn-primary" href="doctor.jsp">Add doctor</a></div><%@include file="../component/messages.jsp"%><section class="appointments-panel"><div class="table-responsive"><table class="table"><thead><tr><th>Name</th><th>Specialty</th><th>Email</th><th>State</th><th>Actions</th></tr></thead><tbody><c:forEach var="d" items="${doctors}"><tr><td><c:out value="${d.fullName}"/></td><td><c:out value="${d.specialist}"/></td><td><c:out value="${d.email}"/></td><td>${d.active ? 'Active' : 'Archived'}</td><td><c:if test="${d.active}"><a href="edit_doctor.jsp?id=${d.id}" class="btn btn-sm btn-outline-primary">Edit</a><form action="../delete_doctor" method="post" class="d-inline"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><input type="hidden" name="id" value="${d.id}"><button class="btn btn-sm btn-outline-primary">Archive</button></form></c:if></td></tr></c:forEach></tbody></table></div></section></div></main></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Includes shared ../component/allcss.jsp markup at JSP translation time. Includes shared navbar.jsp markup at JSP translation time. Includes shared ../component/messages.jsp markup at JSP translation time. Creates a POST form targeting ../delete_doctor; named controls become servlet parameters. Echoes the session CSRF token in a hidden control for the filter to check on POST. Defines submitted field(s) id; their names must match the handler keys. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Renders repeated options/rows from the supplied collection. Chooses visible markup from an EL condition; this presentation branch does not replace authorization. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Provides navigation/resource links to doctor.jsp, edit_doctor.jsp?id=${d.id}. Defines document encoding, viewport or description metadata. Sets the browser tab title. Labels table data columns/content for accessible interpretation. |

<a id="file-73"></a>

## 73. src/main/webapp/WEB-INF/views/admin.jsp

Server-rendered public/patient view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `1aaab9fb747386c2dc8c37a924f0aa83da297379b4f378c40264fb8cb56815ab`

<details>
<summary>Numbered source (19 physical lines)</summary>

~~~text
   1 | <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@ taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <%@page isELIgnored="false"%>
   4 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Administration | HMS</title><%@include file="component/allcss.jsp"%></head>
   5 | <body><%@include file="component/navbar.jsp"%>
   6 | <main id="main-content" class="auth-main"><div class="auth-layout">
   7 |  <div class="auth-copy"><h1>Keep your hospital<br>moving forward.</h1><p>Manage your doctors, specialties, and appointment records.</p></div>
   8 |  <section class="auth-panel"><h2>Administrator sign in</h2><p class="subtext">Use your administrator credentials.</p>
   9 |  <%@include file="component/messages.jsp"%>
  10 |  <form action="adminLogin" method="post">
  11 | <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
  12 |
  13 |  <div class="mb-3"><label class="form-label" for="email">Email address</label><input class="form-control" id="email" name="email" type="email" autocomplete="username" required maxlength="254"></div>
  14 |  <div class="mb-4"><label class="form-label" for="password">Password</label><input class="form-control" id="password" name="password" type="password" autocomplete="current-password"  maxlength="128" required></div>
  15 |  <button class="btn btn-primary w-100" type="submit">Sign in</button>
  16 |  </form>
  17 |
  18 |  </section>
  19 | </div></main><%@include file="component/footer.jsp"%></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 4 | Includes shared component/allcss.jsp markup at JSP translation time. Defines document encoding, viewport or description metadata. Sets the browser tab title. |
| 5 | Includes shared component/navbar.jsp markup at JSP translation time. |
| 6 | Outputs main, div HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 7 | Outputs div, h1, br, p HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 8 | Outputs section, h2, p HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 9 | Includes shared component/messages.jsp markup at JSP translation time. |
| 10 | Creates a POST form targeting adminLogin; named controls become servlet parameters. |
| 11 | Echoes the session CSRF token in a hidden control for the filter to check on POST. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 13 | Defines submitted field(s) email; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. |
| 14 | Defines submitted field(s) password; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Masks browser display; HTTPS is still needed to encrypt transport. |
| 15 | Outputs button HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 16 | Closes the matching HTML/JSTL element or conditional/loop structure. |
| 19 | Includes shared component/footer.jsp markup at JSP translation time. |

<a id="file-74"></a>

## 74. src/main/webapp/WEB-INF/views/appointment.jsp

Server-rendered public/patient view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `4bf07f7b140134b64c9fb9414617dbacd459d2a73b580294d845b762725dad72`

<details>
<summary>Numbered source (3 physical lines)</summary>

~~~text
   1 | <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Appointment details | HMS</title><%@include file="component/allcss.jsp"%></head><body><%@include file="component/navbar.jsp"%><main id="main-content" class="page-main"><div class="container"><%@include file="component/appointment_detail.jsp"%></div></main></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Includes shared component/allcss.jsp markup at JSP translation time. Includes shared component/navbar.jsp markup at JSP translation time. Includes shared component/appointment_detail.jsp markup at JSP translation time. Defines document encoding, viewport or description metadata. Sets the browser tab title. |

<a id="file-75"></a>

## 75. src/main/webapp/WEB-INF/views/change_password.jsp

Server-rendered public/patient view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `c5ca1168b98e7b45e35c75e26ee1b4b32179f6d1a4f5c309c7009258579efc0f`

<details>
<summary>Numbered source (7 physical lines)</summary>

~~~text
   1 | <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@ taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <%@page isELIgnored="false"%>
   4 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Change password | HMS</title><%@include file="component/allcss.jsp"%></head>
   5 | <body><%@include file="component/navbar.jsp"%><main id="main-content" class="auth-main"><section class="auth-panel mx-auto" style="max-width:440px"><h1 class="h3">Change your password</h1><p class="subtext">Choose a password you haven't used before.</p><%@include file="component/messages.jsp"%>
   6 | <form action="userChangePassword" method="post">
   7 | <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><div class="mb-3"><label class="form-label" for="old">Current password</label><input class="form-control" id="old" name="oldPassword" type="password" autocomplete="current-password" required maxlength="128"></div><div class="mb-4"><label class="form-label" for="new">New password</label><input class="form-control" id="new" name="newPassword" type="password" autocomplete="new-password" required minlength="8" maxlength="128"><p class="form-note">Use at least 8 characters.</p></div><button class="btn btn-primary w-100">Update password</button></form></section></main><%@include file="component/footer.jsp"%></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 4 | Includes shared component/allcss.jsp markup at JSP translation time. Defines document encoding, viewport or description metadata. Sets the browser tab title. |
| 5 | Includes shared component/navbar.jsp markup at JSP translation time. Includes shared component/messages.jsp markup at JSP translation time. |
| 6 | Creates a POST form targeting userChangePassword; named controls become servlet parameters. |
| 7 | Includes shared component/footer.jsp markup at JSP translation time. Echoes the session CSRF token in a hidden control for the filter to check on POST. Defines submitted field(s) oldPassword, newPassword; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Masks browser display; HTTPS is still needed to encrypt transport. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |

<a id="file-76"></a>

## 76. src/main/webapp/WEB-INF/views/component/allcss.jsp

Server-rendered public/patient view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `eeca8b04f1478c3eb9b26fab97f5d885ceece75c88cf756f1b1fc81e6a53bee9`

<details>
<summary>Numbered source (18 physical lines)</summary>

~~~text
   1 |
   2 | <link
   3 | 	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
   4 | 	rel="stylesheet"
   5 | 	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
   6 | 	crossorigin="anonymous">
   7 |
   8 |
   9 | <link rel="stylesheet"
  10 | 	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
  11 | 	integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
  12 | 	crossorigin="anonymous" referrerpolicy="no-referrer" />
  13 |
  14 | <script
  15 | 	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
  16 | 	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
  17 | 	crossorigin="anonymous"></script>
  18 | <link rel="stylesheet" href="${pageContext.request.contextPath}/component/app.css">
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 2 | Loads CSS or browser JavaScript; current CDN dependencies require network access. |
| 3 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 4 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 5 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 6 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 9 | Loads CSS or browser JavaScript; current CDN dependencies require network access. |
| 10 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 11 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 12 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 14 | Loads CSS or browser JavaScript; current CDN dependencies require network access. |
| 15 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 16 | Declares/initializes the typed local data shown. Its value is used in the enclosing method, not shared as servlet instance state. |
| 17 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 18 | Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Provides navigation/resource links to ${pageContext.request.contextPath}/component/app.css. Loads CSS or browser JavaScript; current CDN dependencies require network access. |

<a id="file-77"></a>

## 77. src/main/webapp/WEB-INF/views/component/appointment_detail.jsp

Server-rendered public/patient view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `198ae383068cc6656c32b39bd1875b181a26ea214ed6e33f192b663fd61bb9df`

<details>
<summary>Numbered source (9 physical lines)</summary>

~~~text
   1 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   2 | <h1 class="page-title">Appointment #${appointment.id}</h1>
   3 | <%@include file="messages.jsp"%>
   4 | <section class="form-panel"><h2><c:out value="${appointment.fullName}"/></h2><p><strong><c:out value="${appointment.status}"/></strong> · <c:out value="${appointment.slotTime}"/> · <c:out value="${appointment.doctorName}"/></p>
   5 | <p><c:out value="${appointment.age}"/> years · <c:out value="${appointment.gender}"/></p><p><strong>Contact</strong><br><c:out value="${appointment.email}"/><br><c:out value="${appointment.phone}"/><br><c:out value="${appointment.address}"/></p><p><strong>Reason</strong><br><c:out value="${appointment.reason}"/></p>
   6 | <div class="d-flex flex-wrap gap-3"><c:forEach var="action" items="${allowedActions}"><form action="${pageContext.request.contextPath}/appointmentState" method="post"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><input type="hidden" name="id" value="${appointment.id}"><input type="hidden" name="status" value="${action}"><button class="btn btn-outline-primary" type="submit"><c:out value="${action}"/></button></form></c:forEach></div></section>
   7 | <c:if test="${not empty docObj and appointment.status ne 'CANCELLED'}"><section class="form-panel mt-4"><h2>Add a consultation note</h2><p>Notes are appended to the history and are visible to this patient.</p><form action="${pageContext.request.contextPath}/updateStatus" method="post"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><input type="hidden" name="id" value="${appointment.id}"><label for="note" class="form-label">Note for the patient</label><textarea id="note" name="comm" class="form-control" rows="4" required maxlength="1000"></textarea><button class="btn btn-primary mt-3">Add note</button></form></section></c:if>
   8 | <section class="form-panel mt-4"><h2>Consultation history</h2><c:if test="${empty notes}"><p>No consultation notes yet.</p></c:if><c:forEach var="note" items="${notes}"><article class="mb-4"><strong><c:out value="${note.author}"/></strong> · <c:out value="${note.createdAt}"/><p class="preserve-lines"><c:out value="${note.body}"/></p></article></c:forEach></section>
   9 | <section class="form-panel mt-4"><h2>Activity history</h2><c:forEach var="event" items="${events}"><p><c:out value="${event.createdAt}"/> · <c:out value="${event.actorRole}"/> · <c:out value="${event.action}"/> · <c:out value="${event.toStatus}"/></p></c:forEach></section>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Enables the c-prefixed JSTL core library. |
| 2 | Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 3 | Includes shared messages.jsp markup at JSP translation time. |
| 4 | Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 5 | Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 6 | Creates a POST form targeting ${pageContext.request.contextPath}/appointmentState; named controls become servlet parameters. Echoes the session CSRF token in a hidden control for the filter to check on POST. Defines submitted field(s) id, status; their names must match the handler keys. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Renders repeated options/rows from the supplied collection. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 7 | Creates a POST form targeting ${pageContext.request.contextPath}/updateStatus; named controls become servlet parameters. Echoes the session CSRF token in a hidden control for the filter to check on POST. Defines submitted field(s) id, comm; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Chooses visible markup from an EL condition; this presentation branch does not replace authorization. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 8 | Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Renders repeated options/rows from the supplied collection. Chooses visible markup from an EL condition; this presentation branch does not replace authorization. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 9 | Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Renders repeated options/rows from the supplied collection. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |

<a id="file-78"></a>

## 78. src/main/webapp/WEB-INF/views/component/appointment_table.jsp

Server-rendered public/patient view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `7e68e23f889070c08a70a90d1465014e06a8b970709dce5b41fba75ff400715d`

<details>
<summary>Numbered source (5 physical lines)</summary>

~~~text
   1 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   2 | <c:choose><c:when test="${empty appointments}"><div class="empty-state"><h2>No appointments on this page.</h2><p>New appointment records will appear here.</p></div></c:when><c:otherwise><div class="table-responsive"><table class="table"><caption class="visually-hidden">Appointments, 20 per page</caption><thead><tr><th>Reference / patient</th><th>Doctor / time</th><th>Reason</th><th>Status</th><th>Latest note</th><th>Details</th></tr></thead><tbody>
   3 | <c:forEach var="a" items="${appointments}"><tr><td>#${a.id}<br><c:out value="${a.fullName}"/></td><td><c:out value="${a.doctorName}"/><br><c:out value="${a.slotTime}"/></td><td><c:out value="${a.reason}"/></td><td><span class="status-pending"><c:out value="${a.status}"/></span></td><td><c:out value="${a.latestNote}" default="No consultation notes yet."/></td><td><a href="${detailsPath}?id=${a.id}" class="btn btn-sm btn-outline-primary">Open</a></td></tr></c:forEach>
   4 | </tbody></table></div></c:otherwise></c:choose>
   5 | <nav aria-label="Appointment pages" class="mt-4 d-flex gap-3"><c:if test="${page gt 1}"><a href="?page=${page-1}">Previous</a></c:if><span>Page ${page}</span><c:if test="${hasNext}"><a href="?page=${page+1}">Next</a></c:if></nav>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Enables the c-prefixed JSTL core library. |
| 2 | Chooses visible markup from an EL condition; this presentation branch does not replace authorization. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Adds assistive-technology/navigation/feedback semantics where shown. Labels table data columns/content for accessible interpretation. |
| 3 | Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Renders repeated options/rows from the supplied collection. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Provides navigation/resource links to ${detailsPath}?id=${a.id}. |
| 4 | Closes the matching HTML/JSTL element or conditional/loop structure. |
| 5 | Chooses visible markup from an EL condition; this presentation branch does not replace authorization. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Provides navigation/resource links to ?page=${page-1}, ?page=${page+1}. Adds assistive-technology/navigation/feedback semantics where shown. |

<a id="file-79"></a>

## 79. src/main/webapp/WEB-INF/views/component/doctor_form.jsp

Server-rendered public/patient view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `70af2e17dbb3488da2d4cc0f470eb6d8d0281aab800846342f4aef8a57e97180`

<details>
<summary>Numbered source (9 physical lines)</summary>

~~~text
   1 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   2 | <div class="row g-3">
   3 | <div class="col-md-6"><label for="name" class="form-label">Full name</label><input id="name" name="fullname" class="form-control" required maxlength="120" value="<c:out value='${doctor.fullName}'/>"></div>
   4 | <div class="col-md-6"><label for="dob" class="form-label">Date of birth</label><input id="dob" name="dob" type="date" class="form-control" required value="<c:out value='${doctor.dob}'/>"></div>
   5 | <div class="col-md-6"><label for="qualification" class="form-label">Qualification</label><input id="qualification" name="qualification" class="form-control" required maxlength="160" value="<c:out value='${doctor.qualification}'/>"></div>
   6 | <div class="col-md-6"><label for="specialty" class="form-label">Specialty</label><select id="specialty" name="${profileOnly ? 'spec' : 'specialist'}" class="form-select" required><c:forEach var="s" items="${specialties}"><option value="<c:out value='${s.specialistName}'/>" ${doctor.specialist eq s.specialistName ? 'selected' : ''}><c:out value="${s.specialistName}"/></option></c:forEach></select></div>
   7 | <div class="col-md-6"><label for="email" class="form-label">Email</label><input id="email" name="email" type="email" class="form-control" required maxlength="254" value="<c:out value='${doctor.email}'/>"></div>
   8 | <div class="col-md-6"><label for="phone" class="form-label">Phone</label><input id="phone" name="mobno" type="tel" class="form-control" required maxlength="20" value="<c:out value='${doctor.mobNo}'/>"></div>
   9 | <c:if test="${not profileOnly}"><div class="col-12"><label for="password" class="form-label">Password ${empty doctor ? '(required)' : '(leave blank to keep current)'}</label><input id="password" name="password" type="password" class="form-control" minlength="8" maxlength="128" autocomplete="new-password" ${empty doctor ? 'required' : ''}></div></c:if></div>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Enables the c-prefixed JSTL core library. |
| 2 | Outputs div HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 3 | Defines submitted field(s) fullname; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 4 | Defines submitted field(s) dob; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 5 | Defines submitted field(s) qualification; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 6 | Defines submitted field(s) ${profileOnly ? 'spec' : 'specialist'}; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Renders repeated options/rows from the supplied collection. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 7 | Defines submitted field(s) email; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 8 | Defines submitted field(s) mobno; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 9 | Defines submitted field(s) password; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Masks browser display; HTTPS is still needed to encrypt transport. Chooses visible markup from an EL condition; this presentation branch does not replace authorization. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |

<a id="file-80"></a>

## 80. src/main/webapp/WEB-INF/views/component/footer.jsp

Server-rendered public/patient view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `2d88659241219f5e9acfcad3d63054dee1e4a3d7d047f9154169209549898a89`

<details>
<summary>Numbered source (6 physical lines)</summary>

~~~text
   1 | <footer class="hms-footer">
   2 |  <div class="container footer-layout">
   3 |   <div><a class="footer-brand" href="${pageContext.request.contextPath}/index.jsp">HMS <span>Hospital Management System</span></a><p>A simpler way to manage your care.</p></div>
   4 |   <nav aria-label="Footer navigation"><a href="${pageContext.request.contextPath}/user_appointment.jsp">Book an appointment</a><a href="${pageContext.request.contextPath}/doctor.jsp">Doctor portal</a><a href="${pageContext.request.contextPath}/admin.jsp">Administration</a></nav>
   5 |  </div>
   6 | </footer>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Outputs footer HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 2 | Outputs div HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 3 | Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Provides navigation/resource links to ${pageContext.request.contextPath}/index.jsp. |
| 4 | Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Provides navigation/resource links to ${pageContext.request.contextPath}/user_appointment.jsp, ${pageContext.request.contextPath}/doctor.jsp, ${pageContext.request.contextPath}/admin.jsp. Adds assistive-technology/navigation/feedback semantics where shown. |
| 6 | Closes the matching HTML/JSTL element or conditional/loop structure. |

<a id="file-81"></a>

## 81. src/main/webapp/WEB-INF/views/component/messages.jsp

Server-rendered public/patient view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `df5fdf3297794fd20a88cfe90f0bdf3b3b1a97554926ac22d2119d6a5c08702e`

<details>
<summary>Numbered source (3 physical lines)</summary>

~~~text
   1 | <c:if test="${not empty errorMsg}"><div class="alert alert-danger" role="alert"><c:out value="${errorMsg}"/></div><c:remove var="errorMsg" scope="session"/></c:if>
   2 | <c:if test="${not empty sucMsg}"><div class="alert alert-success" role="status"><c:out value="${sucMsg}"/></div><c:remove var="sucMsg" scope="session"/></c:if>
   3 | <c:if test="${not empty succMsg}"><div class="alert alert-success" role="status"><c:out value="${succMsg}"/></div><c:remove var="succMsg" scope="session"/></c:if>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Chooses visible markup from an EL condition; this presentation branch does not replace authorization. Consumes temporary/flash state after rendering so it is not displayed repeatedly. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Adds assistive-technology/navigation/feedback semantics where shown. |
| 2 | Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Chooses visible markup from an EL condition; this presentation branch does not replace authorization. Consumes temporary/flash state after rendering so it is not displayed repeatedly. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Adds assistive-technology/navigation/feedback semantics where shown. |
| 3 | Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Chooses visible markup from an EL condition; this presentation branch does not replace authorization. Consumes temporary/flash state after rendering so it is not displayed repeatedly. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Adds assistive-technology/navigation/feedback semantics where shown. |

<a id="file-82"></a>

## 82. src/main/webapp/WEB-INF/views/component/navbar.jsp

Server-rendered public/patient view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `1676442fdd491c829e8dc8ebbe3f267387393bbae8c1738a8ffb397f7add1520`

<details>
<summary>Numbered source (24 physical lines)</summary>

~~~text
   1 | <%@ taglib prefix="c" uri="jakarta.tags.core" %>
   2 | <%@page isELIgnored="false" %>
   3 | <a class="skip-link" href="#main-content">Skip to content</a>
   4 | <nav class="navbar navbar-expand-lg hms-nav" aria-label="Main navigation">
   5 |  <div class="container">
   6 |   <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp"><span class="brand-mark" aria-hidden="true">+</span><span>HMS<span class="brand-sub">Hospital Management System</span></span></a>
   7 |   <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#main-nav" aria-controls="main-nav" aria-expanded="false" aria-label="Toggle navigation"><i class="fa-solid fa-bars" aria-hidden="true"></i></button>
   8 |   <div class="collapse navbar-collapse" id="main-nav">
   9 |    <ul class="navbar-nav ms-auto align-items-lg-center gap-lg-3">
  10 |     <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/index.jsp#care">Our care</a></li>
  11 |     <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/doctor.jsp">Doctor portal</a></li>
  12 |     <c:choose><c:when test="${not empty userObj}">
  13 |      <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/view_appointment.jsp">My appointments</a></li>
  14 |      <li class="nav-item dropdown"><button class="nav-link dropdown-toggle account-toggle" data-bs-toggle="dropdown" aria-expanded="false"><c:out value="${userObj.fullName}"/></button>
  15 |       <ul class="dropdown-menu"><li><a class="dropdown-item" href="${pageContext.request.contextPath}/change_password.jsp">Change password</a></li><li><form action="${pageContext.request.contextPath}/userLogout" method="post"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><button class="dropdown-item">Sign out</button></form></li></ul>
  16 |      </li>
  17 |     </c:when><c:otherwise><li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/user_Login.jsp">Patient sign in</a></li></c:otherwise></c:choose>
  18 |     <li class="nav-item"><a class="btn btn-primary" href="${pageContext.request.contextPath}/user_appointment.jsp">Book an appointment <i class="fa-solid fa-arrow-right ms-2" aria-hidden="true"></i></a></li>
  19 |    </ul>
  20 |   </div>
  21 |  </div>
  22 | </nav>
  23 |
  24 | <c:if test="${demoMode}"><div class="demo-notice">Local project demo. Doctor profiles are sample data.</div></c:if>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Enables the c-prefixed JSTL core library. |
| 2 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 3 | Provides navigation/resource links to #main-content. |
| 4 | Adds assistive-technology/navigation/feedback semantics where shown. |
| 5 | Outputs div HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 6 | Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Provides navigation/resource links to ${pageContext.request.contextPath}/index.jsp. Adds assistive-technology/navigation/feedback semantics where shown. |
| 7 | Adds assistive-technology/navigation/feedback semantics where shown. Connects markup to Bootstrap collapse/dropdown/modal behavior. |
| 8 | Outputs div HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 9 | Outputs ul HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 10 | Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Provides navigation/resource links to ${pageContext.request.contextPath}/index.jsp#care. |
| 11 | Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Provides navigation/resource links to ${pageContext.request.contextPath}/doctor.jsp. |
| 12 | Chooses visible markup from an EL condition; this presentation branch does not replace authorization. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 13 | Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Provides navigation/resource links to ${pageContext.request.contextPath}/view_appointment.jsp. |
| 14 | Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Adds assistive-technology/navigation/feedback semantics where shown. Connects markup to Bootstrap collapse/dropdown/modal behavior. |
| 15 | Creates a POST form targeting ${pageContext.request.contextPath}/userLogout; named controls become servlet parameters. Echoes the session CSRF token in a hidden control for the filter to check on POST. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Provides navigation/resource links to ${pageContext.request.contextPath}/change_password.jsp. |
| 16 | Closes the matching HTML/JSTL element or conditional/loop structure. |
| 17 | Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Provides navigation/resource links to ${pageContext.request.contextPath}/user_Login.jsp. |
| 18 | Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Provides navigation/resource links to ${pageContext.request.contextPath}/user_appointment.jsp. Adds assistive-technology/navigation/feedback semantics where shown. |
| 19 | Closes the matching HTML/JSTL element or conditional/loop structure. |
| 22 | Closes the matching HTML/JSTL element or conditional/loop structure. |
| 24 | Chooses visible markup from an EL condition; this presentation branch does not replace authorization. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |

<a id="file-83"></a>

## 83. src/main/webapp/WEB-INF/views/doctor/comment.jsp

Server-rendered doctor view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `84071e5c740975808cc44d90f6b64ece0919230987b8dc89771afe87b893a3b3`

<details>
<summary>Numbered source (3 physical lines)</summary>

~~~text
   1 | <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Appointment details | HMS</title><%@include file="../component/allcss.jsp"%></head><body><%@include file="navbar.jsp"%><main id="main-content" class="page-main"><div class="container"><%@include file="../component/appointment_detail.jsp"%></div></main></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Includes shared ../component/allcss.jsp markup at JSP translation time. Includes shared navbar.jsp markup at JSP translation time. Includes shared ../component/appointment_detail.jsp markup at JSP translation time. Defines document encoding, viewport or description metadata. Sets the browser tab title. |

<a id="file-84"></a>

## 84. src/main/webapp/WEB-INF/views/doctor/edit_profile.jsp

Server-rendered doctor view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `2afbe8a3ed800317169a79c0e5c2c90d13b32b72d04e7fc60be26c122b235642`

<details>
<summary>Numbered source (3 physical lines)</summary>

~~~text
   1 | <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>My profile | HMS</title><%@include file="../component/allcss.jsp"%></head><body><%@include file="navbar.jsp"%><main id="main-content" class="page-main"><div class="container"><h1 class="page-title">My profile</h1><%@include file="../component/messages.jsp"%><section class="form-panel"><form action="../doctorUpdateProfile" method="post"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><%@include file="../component/doctor_form.jsp"%><button class="btn btn-primary mt-4">Save profile</button></form></section><section class="form-panel mt-4"><h2>Change password</h2><p>Changing your password signs out all existing doctor sessions.</p><form action="../docChangePassword" method="post"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><label class="form-label" for="old">Current password</label><input id="old" name="oldPassword" type="password" class="form-control mb-3" required maxlength="128" autocomplete="current-password"><label class="form-label" for="new">New password</label><input id="new" name="newPassword" type="password" class="form-control mb-3" required minlength="8" maxlength="128" autocomplete="new-password"><button class="btn btn-primary">Change password</button></form></section></div></main></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Includes shared ../component/allcss.jsp markup at JSP translation time. Includes shared navbar.jsp markup at JSP translation time. Includes shared ../component/messages.jsp markup at JSP translation time. Includes shared ../component/doctor_form.jsp markup at JSP translation time. Creates a POST form targeting ../doctorUpdateProfile; named controls become servlet parameters. Creates a POST form targeting ../docChangePassword; named controls become servlet parameters. Echoes the session CSRF token in a hidden control for the filter to check on POST. Defines submitted field(s) oldPassword, newPassword; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Masks browser display; HTTPS is still needed to encrypt transport. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Defines document encoding, viewport or description metadata. Sets the browser tab title. |

<a id="file-85"></a>

## 85. src/main/webapp/WEB-INF/views/doctor/index.jsp

Server-rendered doctor view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `64aac6a2d9a994c73b0ee1a70a08904abdae65e218319888eb5c8209b3a015fb`

<details>
<summary>Numbered source (3 physical lines)</summary>

~~~text
   1 | <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Doctor overview | HMS</title><%@include file="../component/allcss.jsp"%></head><body><%@include file="navbar.jsp"%><main id="main-content" class="page-main"><div class="container"><h1 class="page-title">Welcome, <c:out value="${docObj.fullName}"/>.</h1><p>Review requests, manage published slots, and keep your patients informed.</p><div class="dashboard-grid"><a href="patient.jsp"><span>All appointments</span><strong><c:out value="${stats.total}" default="0"/></strong></a><div><span>Pending</span><strong><c:out value="${stats.PENDING}" default="0"/></strong></div><div><span>Confirmed</span><strong><c:out value="${stats.CONFIRMED}" default="0"/></strong></div><a href="slots.jsp"><span>Availability</span><strong>30 min</strong><span>Publish slots</span></a></div></div></main></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Includes shared ../component/allcss.jsp markup at JSP translation time. Includes shared navbar.jsp markup at JSP translation time. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Provides navigation/resource links to patient.jsp, slots.jsp. Defines document encoding, viewport or description metadata. Sets the browser tab title. |

<a id="file-86"></a>

## 86. src/main/webapp/WEB-INF/views/doctor/navbar.jsp

Server-rendered doctor view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `3cca191607beaa03bb5a8697464e0caf538a786ef23348a1513779a619f3f14f`

<details>
<summary>Numbered source (10 physical lines)</summary>

~~~text
   1 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   2 | <nav class="navbar navbar-expand-lg hms-nav" aria-label="Doctor navigation"><div class="container">
   3 | <a class="navbar-brand" href="index.jsp"><span class="brand-mark" aria-hidden="true">+</span><span>HMS<span class="brand-sub">Doctor portal</span></span></a>
   4 | <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#portal-nav" aria-controls="portal-nav" aria-expanded="false" aria-label="Toggle navigation"><i class="fa-solid fa-bars" aria-hidden="true"></i></button>
   5 | <div class="collapse navbar-collapse" id="portal-nav"><ul class="navbar-nav ms-auto align-items-lg-center gap-lg-3">
   6 | <li class="nav-item"><a class="nav-link" href="index.jsp">Overview</a></li>
   7 | <li class="nav-item"><a class="nav-link" href="edit_profile.jsp">My profile</a></li>
   8 | <li class="nav-item"><a class="nav-link" href="patient.jsp">Appointments</a></li><li class="nav-item"><a class="nav-link" href="slots.jsp">Slots</a></li>
   9 | <li class="nav-item"><form action="../docLogout" method="post"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><button class="nav-link account-toggle">Sign out</button></form></li>
  10 | </ul></div></div></nav>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Enables the c-prefixed JSTL core library. |
| 2 | Adds assistive-technology/navigation/feedback semantics where shown. |
| 3 | Provides navigation/resource links to index.jsp. Adds assistive-technology/navigation/feedback semantics where shown. |
| 4 | Adds assistive-technology/navigation/feedback semantics where shown. Connects markup to Bootstrap collapse/dropdown/modal behavior. |
| 5 | Outputs div, ul HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 6 | Provides navigation/resource links to index.jsp. |
| 7 | Provides navigation/resource links to edit_profile.jsp. |
| 8 | Provides navigation/resource links to patient.jsp, slots.jsp. |
| 9 | Creates a POST form targeting ../docLogout; named controls become servlet parameters. Echoes the session CSRF token in a hidden control for the filter to check on POST. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 10 | Closes the matching HTML/JSTL element or conditional/loop structure. |

<a id="file-87"></a>

## 87. src/main/webapp/WEB-INF/views/doctor/patient.jsp

Server-rendered doctor view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `be24064801d7f86a556616525f28363735740755f074f3a8cf8fb40f9870a7dd`

<details>
<summary>Numbered source (3 physical lines)</summary>

~~~text
   1 | <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Your appointments | HMS</title><%@include file="../component/allcss.jsp"%></head><body><%@include file="navbar.jsp"%><main id="main-content" class="page-main"><div class="container"><h1 class="page-title">Your appointments</h1><p class="page-intro">Track reserved slots, appointment status, and consultation notes. Times use the hospital time zone.</p><%@include file="../component/messages.jsp"%><section class="appointments-panel"><%@include file="../component/appointment_table.jsp"%></section></div></main></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Includes shared ../component/allcss.jsp markup at JSP translation time. Includes shared navbar.jsp markup at JSP translation time. Includes shared ../component/messages.jsp markup at JSP translation time. Includes shared ../component/appointment_table.jsp markup at JSP translation time. Defines document encoding, viewport or description metadata. Sets the browser tab title. |

<a id="file-88"></a>

## 88. src/main/webapp/WEB-INF/views/doctor/slots.jsp

Server-rendered doctor view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `da6403907e3313212d1197307036b833933dc61f079cb0afec6fa0a334911d64`

<details>
<summary>Numbered source (5 physical lines)</summary>

~~~text
   1 | <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Manage slots | HMS</title><%@include file="../component/allcss.jsp"%></head><body><%@include file="navbar.jsp"%><main id="main-content" class="page-main"><div class="container"><h1 class="page-title">Manage appointment slots</h1><p>Slots last 30 minutes. Choose a future :00 or :30 start within 90 days. Time zone: <c:out value="${hospitalZone}"/>.</p><%@include file="../component/messages.jsp"%>
   4 | <section class="form-panel"><h2>Publish a slot</h2><form action="../publishSlot" method="post" class="row g-3"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><div class="col-md-5"><label class="form-label" for="start">Start date and time</label><input id="start" name="startsAt" type="datetime-local" step="1800" class="form-control" required></div><div class="col-md-2 d-flex align-items-end"><button class="btn btn-primary">Publish</button></div></form></section>
   5 | <section class="appointments-panel mt-4"><h2>Available published slots</h2><p>Reserved and withdrawn slots are excluded. Cancel an appointment from its details page to release its slot.</p><c:forEach var="slot" items="${slots}"><div class="d-flex justify-content-between align-items-center gap-3 mb-3"><span><c:out value="${slot.label}"/></span><form action="../withdrawSlot" method="post"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><input type="hidden" name="slotId" value="${slot.id}"><button class="btn btn-sm btn-outline-primary">Withdraw</button></form></div></c:forEach></section></div></main></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Includes shared ../component/allcss.jsp markup at JSP translation time. Includes shared navbar.jsp markup at JSP translation time. Includes shared ../component/messages.jsp markup at JSP translation time. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Defines document encoding, viewport or description metadata. Sets the browser tab title. |
| 4 | Creates a POST form targeting ../publishSlot; named controls become servlet parameters. Echoes the session CSRF token in a hidden control for the filter to check on POST. Defines submitted field(s) startsAt; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 5 | Creates a POST form targeting ../withdrawSlot; named controls become servlet parameters. Echoes the session CSRF token in a hidden control for the filter to check on POST. Defines submitted field(s) slotId; their names must match the handler keys. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Renders repeated options/rows from the supplied collection. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |

<a id="file-89"></a>

## 89. src/main/webapp/WEB-INF/views/doctor.jsp

Server-rendered public/patient view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `f2a1dd9f9257f646d9b5d84c646373643efcde8b7ddcca2319da31597fc9ede1`

<details>
<summary>Numbered source (19 physical lines)</summary>

~~~text
   1 | <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@ taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <%@page isELIgnored="false"%>
   4 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Doctor portal | HMS</title><%@include file="component/allcss.jsp"%></head>
   5 | <body><%@include file="component/navbar.jsp"%>
   6 | <main id="main-content" class="auth-main"><div class="auth-layout">
   7 |  <div class="auth-copy"><h1>More time<br>for your patients.</h1><p>Review appointment requests, add consultation notes, and manage your profile.</p></div>
   8 |  <section class="auth-panel"><h2>Doctor sign in</h2><p class="subtext">Sign in with your hospital account.</p>
   9 |  <%@include file="component/messages.jsp"%>
  10 |  <form action="loginDoctor" method="post">
  11 | <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
  12 |
  13 |  <div class="mb-3"><label class="form-label" for="email">Email address</label><input class="form-control" id="email" name="email" type="email" autocomplete="username" required maxlength="254"></div>
  14 |  <div class="mb-4"><label class="form-label" for="password">Password</label><input class="form-control" id="password" name="password" type="password" autocomplete="current-password"  maxlength="128" required></div>
  15 |  <button class="btn btn-primary w-100" type="submit">Sign in</button>
  16 |  </form>
  17 |
  18 |  </section>
  19 | </div></main><%@include file="component/footer.jsp"%></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 4 | Includes shared component/allcss.jsp markup at JSP translation time. Defines document encoding, viewport or description metadata. Sets the browser tab title. |
| 5 | Includes shared component/navbar.jsp markup at JSP translation time. |
| 6 | Outputs main, div HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 7 | Outputs div, h1, br, p HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 8 | Outputs section, h2, p HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 9 | Includes shared component/messages.jsp markup at JSP translation time. |
| 10 | Creates a POST form targeting loginDoctor; named controls become servlet parameters. |
| 11 | Echoes the session CSRF token in a hidden control for the filter to check on POST. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 13 | Defines submitted field(s) email; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. |
| 14 | Defines submitted field(s) password; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Masks browser display; HTTPS is still needed to encrypt transport. |
| 15 | Outputs button HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 16 | Closes the matching HTML/JSTL element or conditional/loop structure. |
| 19 | Includes shared component/footer.jsp markup at JSP translation time. |

<a id="file-90"></a>

## 90. src/main/webapp/WEB-INF/views/error.jsp

Server-rendered public/patient view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `d8019a99558b04881e92cee6d2263d6f79b7aa4a3186cd408ecc68c8a26861bc`

<details>
<summary>Numbered source (3 physical lines)</summary>

~~~text
   1 | <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Request could not be completed | HMS</title><link rel="stylesheet" href="${pageContext.request.contextPath}/component/app.css"></head><body><main class="container page-main"><section class="form-panel"><h1>We couldn't complete this request.</h1><p role="alert"><c:out value="${errorMessage}"/></p><p>Please return to the relevant form and try again.</p><p><a href="${pageContext.request.contextPath}/user_appointment.jsp">Choose an appointment slot</a> · <a href="${pageContext.request.contextPath}/user_Login.jsp">Patient sign in</a> · <a href="${pageContext.request.contextPath}/doctor.jsp">Doctor sign in</a> · <a href="${pageContext.request.contextPath}/admin.jsp">Admin sign in</a></p><small>Request reference: <c:out value="${requestId}"/></small></section></main></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Provides navigation/resource links to ${pageContext.request.contextPath}/component/app.css, ${pageContext.request.contextPath}/user_appointment.jsp, ${pageContext.request.contextPath}/user_Login.jsp, ${pageContext.request.contextPath}/doctor.jsp, ${pageContext.request.contextPath}/admin.jsp. Adds assistive-technology/navigation/feedback semantics where shown. Defines document encoding, viewport or description metadata. Sets the browser tab title. Loads CSS or browser JavaScript; current CDN dependencies require network access. |

<a id="file-91"></a>

## 91. src/main/webapp/WEB-INF/views/index.jsp

Server-rendered public/patient view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `6e68366bbafa16cd74729069c83388d42ae0c3bd29d3d1aab09289e90b977944`

<details>
<summary>Numbered source (29 physical lines)</summary>

~~~text
   1 | <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <!DOCTYPE html>
   3 | <html lang="en">
   4 | <head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><meta name="description" content="Book hospital appointments, choose your doctor, and follow your care in one place."><title>HMS | Care starts here</title><%@include file="component/allcss.jsp"%></head>
   5 | <body>
   6 | <%@include file="component/navbar.jsp"%>
   7 | <main id="main-content">
   8 |  <section class="container home-hero">
   9 |   <div class="hero-copy"><p class="eyebrow">YOUR HEALTH, MADE SIMPLER</p><h1>Good care.<br>Less waiting.</h1><p class="hero-description">Find your doctor, request an appointment, and keep your care in one place.</p><a class="btn btn-primary btn-lg" href="user_appointment.jsp">Book an appointment <i class="fa-solid fa-arrow-right ms-3" aria-hidden="true"></i></a></div>
  10 |   <div class="hero-photo"><img src="img/hosp2.jpg" width="1200" height="800" alt="Hospital building and surrounding grounds"></div>
  11 |  </section>
  12 |  <section class="container journey" aria-label="How appointments work">
  13 |   <div><span class="step-number">01</span><div><h2>Create your account</h2><p>Your details, ready for your next visit.</p></div></div>
  14 |   <div><span class="step-number">02</span><div><h2>Choose your doctor</h2><p>Find the specialty that suits your needs.</p></div></div>
  15 |   <div><span class="step-number">03</span><div><h2>Request your visit</h2><p>Track your request and your doctor's notes.</p></div></div>
  16 |  </section>
  17 |  <section id="care" class="container care-section">
  18 |   <div class="section-heading"><h2>A place to start feeling better.</h2><p>Choose a specialty when you book. Your doctor can guide the next steps.</p></div>
  19 |   <div class="specialty-grid">
  20 |    <a href="user_appointment.jsp"><i class="fa-solid fa-stethoscope" aria-hidden="true"></i><h3>General medicine</h3><p>Everyday concerns and routine consultations.</p><span>Request a visit <i class="fa-solid fa-arrow-right" aria-hidden="true"></i></span></a>
  21 |    <a href="user_appointment.jsp"><i class="fa-solid fa-heart-pulse" aria-hidden="true"></i><h3>Cardiology</h3><p>Consultations focused on heart health.</p><span>Request a visit <i class="fa-solid fa-arrow-right" aria-hidden="true"></i></span></a>
  22 |    <a href="user_appointment.jsp"><i class="fa-solid fa-child" aria-hidden="true"></i><h3>Pediatrics</h3><p>Care for children at every stage of growth.</p><span>Request a visit <i class="fa-solid fa-arrow-right" aria-hidden="true"></i></span></a>
  23 |    <a href="user_appointment.jsp"><i class="fa-solid fa-hand-holding-medical" aria-hidden="true"></i><h3>Dermatology</h3><p>Support for skin and related concerns.</p><span>Request a visit <i class="fa-solid fa-arrow-right" aria-hidden="true"></i></span></a>
  24 |   </div>
  25 |  </section>
  26 |  <section class="container portal-section"><div><h2>Your next visit, without the back-and-forth.</h2><p>Already have an account? Check your appointment requests and read updates from your doctor.</p></div><a class="btn btn-outline-primary" href="view_appointment.jsp">View my appointments <i class="fa-solid fa-arrow-right ms-2" aria-hidden="true"></i></a></section>
  27 | </main>
  28 | <%@include file="component/footer.jsp"%>
  29 | </body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Continues the surrounding expression/declaration. Read with the enclosing method and its adjacent annotated lines; delimiters do not add a separate business rule. |
| 3 | Outputs html HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 4 | Includes shared component/allcss.jsp markup at JSP translation time. Defines document encoding, viewport or description metadata. Sets the browser tab title. |
| 5 | Outputs body HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 6 | Includes shared component/navbar.jsp markup at JSP translation time. |
| 7 | Outputs main HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 8 | Outputs section HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 9 | Provides navigation/resource links to user_appointment.jsp. Adds assistive-technology/navigation/feedback semantics where shown. |
| 10 | Loads the named static image; alt text and declared dimensions help accessibility/layout. |
| 12 | Adds assistive-technology/navigation/feedback semantics where shown. |
| 13 | Outputs div, span, h2, p HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 14 | Outputs div, span, h2, p HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 15 | Outputs div, span, h2, p HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 17 | Outputs section HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 18 | Outputs div, h2, p HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 19 | Outputs div HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 20 | Provides navigation/resource links to user_appointment.jsp. Adds assistive-technology/navigation/feedback semantics where shown. |
| 21 | Provides navigation/resource links to user_appointment.jsp. Adds assistive-technology/navigation/feedback semantics where shown. |
| 22 | Provides navigation/resource links to user_appointment.jsp. Adds assistive-technology/navigation/feedback semantics where shown. |
| 23 | Provides navigation/resource links to user_appointment.jsp. Adds assistive-technology/navigation/feedback semantics where shown. |
| 26 | Provides navigation/resource links to view_appointment.jsp. Adds assistive-technology/navigation/feedback semantics where shown. |
| 27 | Closes the matching HTML/JSTL element or conditional/loop structure. |
| 28 | Includes shared component/footer.jsp markup at JSP translation time. |
| 29 | Closes the matching HTML/JSTL element or conditional/loop structure. |

<a id="file-92"></a>

## 92. src/main/webapp/WEB-INF/views/signup.jsp

Server-rendered public/patient view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `c6233ff88c2428854555d11859961e295bc58a6206ab43515d3cdca67c52b431`

<details>
<summary>Numbered source (19 physical lines)</summary>

~~~text
   1 | <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@ taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <%@page isELIgnored="false"%>
   4 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Create an account | HMS</title><%@include file="component/allcss.jsp"%></head>
   5 | <body><%@include file="component/navbar.jsp"%>
   6 | <main id="main-content" class="auth-main"><div class="auth-layout">
   7 |  <div class="auth-copy"><h1>A healthier start<br>begins here.</h1><p>Create your patient account to request an appointment.</p></div>
   8 |  <section class="auth-panel"><h2>Create your account</h2><p class="subtext">A few details and you are ready to book.</p>
   9 |  <%@include file="component/messages.jsp"%>
  10 |  <form action="register" method="post">
  11 | <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
  12 |  <div class="mb-3"><label class="form-label" for="fullname">Full name</label><input class="form-control" id="fullname" name="Fullname" autocomplete="name" required maxlength="120"></div>
  13 |  <div class="mb-3"><label class="form-label" for="email">Email address</label><input class="form-control" id="email" name="email" type="email" autocomplete="username" required maxlength="254"></div>
  14 |  <div class="mb-4"><label class="form-label" for="password">Password</label><input class="form-control" id="password" name="password" type="password" autocomplete="new-password" minlength="8" maxlength="128" required><p class="form-note">Use at least 8 characters.</p></div>
  15 |  <button class="btn btn-primary w-100" type="submit">Create account</button>
  16 |  </form>
  17 |  <p class="auth-switch">Already registered? <a href="user_Login.jsp">Sign in</a></p>
  18 |  </section>
  19 | </div></main><%@include file="component/footer.jsp"%></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 4 | Includes shared component/allcss.jsp markup at JSP translation time. Defines document encoding, viewport or description metadata. Sets the browser tab title. |
| 5 | Includes shared component/navbar.jsp markup at JSP translation time. |
| 6 | Outputs main, div HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 7 | Outputs div, h1, br, p HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 8 | Outputs section, h2, p HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 9 | Includes shared component/messages.jsp markup at JSP translation time. |
| 10 | Creates a POST form targeting register; named controls become servlet parameters. |
| 11 | Echoes the session CSRF token in a hidden control for the filter to check on POST. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 12 | Defines submitted field(s) Fullname; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. |
| 13 | Defines submitted field(s) email; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. |
| 14 | Defines submitted field(s) password; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Masks browser display; HTTPS is still needed to encrypt transport. |
| 15 | Outputs button HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 16 | Closes the matching HTML/JSTL element or conditional/loop structure. |
| 17 | Provides navigation/resource links to user_Login.jsp. |
| 19 | Includes shared component/footer.jsp markup at JSP translation time. |

<a id="file-93"></a>

## 93. src/main/webapp/WEB-INF/views/user_Login.jsp

Server-rendered public/patient view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `7b8dd091edf33d6b140fe97129cd2866a57fdac90fbf8f00f6c2104075a5fbe0`

<details>
<summary>Numbered source (19 physical lines)</summary>

~~~text
   1 | <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@ taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <%@page isELIgnored="false"%>
   4 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Patient sign in | HMS</title><%@include file="component/allcss.jsp"%></head>
   5 | <body><%@include file="component/navbar.jsp"%>
   6 | <main id="main-content" class="auth-main"><div class="auth-layout">
   7 |  <div class="auth-copy"><h1>Your care,<br>in one place.</h1><p>Book your next visit and follow updates from your doctor.</p></div>
   8 |  <section class="auth-panel"><h2>Sign in to your account</h2><p class="subtext">Use the email you registered with.</p>
   9 |  <%@include file="component/messages.jsp"%>
  10 |  <form action="userLogin" method="post">
  11 | <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
  12 |
  13 |  <div class="mb-3"><label class="form-label" for="email">Email address</label><input class="form-control" id="email" name="email" type="email" autocomplete="username" required maxlength="254"></div>
  14 |  <div class="mb-4"><label class="form-label" for="password">Password</label><input class="form-control" id="password" name="password" type="password" autocomplete="current-password"  maxlength="128" required></div>
  15 |  <button class="btn btn-primary w-100" type="submit">Sign in</button>
  16 |  </form>
  17 |  <p class="auth-switch">New here? <a href="signup.jsp">Create an account</a></p>
  18 |  </section>
  19 | </div></main><%@include file="component/footer.jsp"%></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 4 | Includes shared component/allcss.jsp markup at JSP translation time. Defines document encoding, viewport or description metadata. Sets the browser tab title. |
| 5 | Includes shared component/navbar.jsp markup at JSP translation time. |
| 6 | Outputs main, div HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 7 | Outputs div, h1, br, p HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 8 | Outputs section, h2, p HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 9 | Includes shared component/messages.jsp markup at JSP translation time. |
| 10 | Creates a POST form targeting userLogin; named controls become servlet parameters. |
| 11 | Echoes the session CSRF token in a hidden control for the filter to check on POST. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 13 | Defines submitted field(s) email; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. |
| 14 | Defines submitted field(s) password; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Masks browser display; HTTPS is still needed to encrypt transport. |
| 15 | Outputs button HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 16 | Closes the matching HTML/JSTL element or conditional/loop structure. |
| 17 | Provides navigation/resource links to signup.jsp. |
| 19 | Includes shared component/footer.jsp markup at JSP translation time. |

<a id="file-94"></a>

## 94. src/main/webapp/WEB-INF/views/user_appointment.jsp

Server-rendered public/patient view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `bc21ef4ccec3378b59a210fbf8242a3d67ef611c05cac23d9d876b083e3cd17a`

<details>
<summary>Numbered source (18 physical lines)</summary>

~~~text
   1 | <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Book a slot | HMS</title><%@include file="component/allcss.jsp"%></head><body><%@include file="component/navbar.jsp"%><main id="main-content" class="page-main"><div class="container">
   4 | <h1 class="page-title">Choose your appointment slot.</h1><p class="page-intro">Published slots last 30 minutes. All times use <c:out value="${hospitalZone}"/>. Your selected slot is held while the doctor reviews your request.</p>
   5 | <%@include file="component/messages.jsp"%>
   6 | <form action="user_appointment.jsp" method="get" class="specialty-form mb-4"><div><label class="form-label" for="filter">Filter doctors</label><select id="filter" name="doctorId" class="form-select"><c:forEach var="d" items="${doctors}"><option value="${d.id}"><c:out value="${d.fullName}"/> · <c:out value="${d.specialist}"/></option></c:forEach></select></div><button class="btn btn-outline-primary">Find slots</button><a href="user_appointment.jsp">All doctors</a></form>
   7 | <section class="form-panel"><c:choose><c:when test="${empty slots}"><h2>No published slots are available.</h2><p>Choose another doctor or check again later. Hospital staff publish availability from their slot management page.</p></c:when><c:otherwise>
   8 | <form action="appAppointment" method="post"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><input type="hidden" name="requestKey" value="${bookingKey}">
   9 | <div class="row g-3">
  10 | <div class="col-12"><label for="slot" class="form-label">Available doctor and time</label><select id="slot" class="form-select" name="slotId" required><option value="">Choose a published half-hour slot</option><c:forEach var="slot" items="${slots}"><option value="${slot.id}"><c:out value="${slot.label}"/></option></c:forEach></select></div>
  11 | <div class="col-md-6"><label for="fullname" class="form-label">Patient full name</label><input id="fullname" class="form-control" name="fullname" required maxlength="120" value="<c:out value='${not empty appointmentForm ? appointmentForm.fullname : userObj.fullName}'/>"></div>
  12 | <div class="col-md-6"><label for="gender" class="form-label">Gender</label><select id="gender" name="gender" class="form-select" required><c:forEach var="g" items="${['male','female','other','prefer not to say']}"><option value="${g}" ${appointmentForm.gender eq g ? 'selected' : ''}><c:out value="${g}"/></option></c:forEach></select></div>
  13 | <div class="col-md-6"><label for="age" class="form-label">Age</label><input id="age" name="age" type="number" class="form-control" required min="0" max="120" value="<c:out value='${appointmentForm.age}'/>"></div>
  14 | <div class="col-md-6"><label for="email" class="form-label">Email</label><input id="email" name="email" type="email" class="form-control" required maxlength="254" value="<c:out value='${not empty appointmentForm ? appointmentForm.email : userObj.email}'/>"></div>
  15 | <div class="col-12"><label for="phone" class="form-label">Phone</label><input id="phone" name="phno" type="tel" class="form-control" required maxlength="20" value="<c:out value='${appointmentForm.phno}'/>"></div>
  16 | <div class="col-12"><label for="reason" class="form-label">Reason for visit</label><textarea id="reason" name="diseases" class="form-control" required rows="3" maxlength="500"><c:out value="${appointmentForm.diseases}"/></textarea></div>
  17 | <div class="col-12"><label for="address" class="form-label">Address</label><textarea id="address" name="address" class="form-control" required rows="2" maxlength="500"><c:out value="${appointmentForm.address}"/></textarea></div>
  18 | </div><button class="btn btn-primary mt-4">Reserve this slot</button></form></c:otherwise></c:choose><c:remove var="appointmentForm" scope="session"/></section></div></main></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Includes shared component/allcss.jsp markup at JSP translation time. Includes shared component/navbar.jsp markup at JSP translation time. Defines document encoding, viewport or description metadata. Sets the browser tab title. |
| 4 | Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 5 | Includes shared component/messages.jsp markup at JSP translation time. |
| 6 | Creates a GET form targeting user_appointment.jsp; named controls become servlet parameters. Defines submitted field(s) doctorId; their names must match the handler keys. Associates a visible label with its input ID. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Renders repeated options/rows from the supplied collection. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. Provides navigation/resource links to user_appointment.jsp. |
| 7 | Chooses visible markup from an EL condition; this presentation branch does not replace authorization. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 8 | Creates a POST form targeting appAppointment; named controls become servlet parameters. Echoes the session CSRF token in a hidden control for the filter to check on POST. Defines submitted field(s) requestKey; their names must match the handler keys. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 9 | Outputs div HTML content/layout. CSS classes control appearance, not backend identity or persistence. |
| 10 | Defines submitted field(s) slotId; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Renders repeated options/rows from the supplied collection. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 11 | Defines submitted field(s) fullname; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 12 | Defines submitted field(s) gender; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Renders repeated options/rows from the supplied collection. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 13 | Defines submitted field(s) age; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 14 | Defines submitted field(s) email; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 15 | Defines submitted field(s) phno; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 16 | Defines submitted field(s) diseases; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 17 | Defines submitted field(s) address; their names must match the handler keys. Adds browser-side presence/length hints; server validation remains authoritative. Associates a visible label with its input ID. Escapes dynamic output for the HTML text/attribute context instead of rendering user input as active markup. Evaluates server-side attributes/JavaBean properties. Plain EL interpolation alone is not automatic output escaping. |
| 18 | Consumes temporary/flash state after rendering so it is not displayed repeatedly. |

<a id="file-95"></a>

## 95. src/main/webapp/WEB-INF/views/view_appointment.jsp

Server-rendered public/patient view/fragment. Form targets invoke servlets; JSTL/EL display server data. Navigation visibility is not the authorization boundary.

Source SHA-256: `5aa83d3697952db23b19f7f21b8b07767c3ab939952e3aabda4a30945543b4b6`

<details>
<summary>Numbered source (3 physical lines)</summary>

~~~text
   1 | <%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   2 | <%@taglib prefix="c" uri="jakarta.tags.core"%>
   3 | <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>My appointments | HMS</title><%@include file="component/allcss.jsp"%></head><body><%@include file="component/navbar.jsp"%><main id="main-content" class="page-main"><div class="container"><h1 class="page-title">My appointments</h1><p class="page-intro">Track reserved slots, appointment status, and consultation notes. Times use the hospital time zone.</p><%@include file="component/messages.jsp"%><section class="appointments-panel"><%@include file="component/appointment_table.jsp"%></section></div></main></body></html>
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Configures JSP translation/imports, response/source encoding or Expression Language behavior. |
| 2 | Enables the c-prefixed JSTL core library. |
| 3 | Includes shared component/allcss.jsp markup at JSP translation time. Includes shared component/navbar.jsp markup at JSP translation time. Includes shared component/messages.jsp markup at JSP translation time. Includes shared component/appointment_table.jsp markup at JSP translation time. Defines document encoding, viewport or description metadata. Sets the browser tab title. |

<a id="file-96"></a>

## 96. src/main/webapp/component/app.css

Presentation stylesheet. app.css is linked by the shared current asset include; older style.css rules matter only on pages that link it.

Source SHA-256: `766dc0e93643460e4a1ede663a3cc6ceac0704f90b76ff463d95757a192ac5bc`

<details>
<summary>Numbered source (706 physical lines)</summary>

~~~text
   1 | :root {
   2 |   --hms-accent:#09675f;
   3 |   --hms-accent-dark:#064e48;
   4 |   --hms-ink:#173431;
   5 |   --hms-muted:#536c68;
   6 |   --hms-line:#dce7e4;
   7 |   --hms-surface:#f2f7f5;
   8 | }
   9 | html {
  10 |   scroll-behavior:smooth;
  11 |   scroll-padding-top:24px;
  12 | }
  13 | body {
  14 |   font-family:"Segoe UI",Arial,sans-serif;
  15 |   color:var(--hms-ink);
  16 |   background:#fff;
  17 |   line-height:1.6;
  18 |   margin:0;
  19 | }
  20 | a {
  21 |   color:var(--hms-accent);
  22 | }
  23 | a:hover {
  24 |   color:var(--hms-accent-dark);
  25 | }
  26 | h1,h2,h3 {
  27 |   letter-spacing:-.04em;
  28 |   line-height:1.12;
  29 | }
  30 | a,button,input,select,textarea {
  31 |   touch-action:manipulation;
  32 | }
  33 | :focus-visible {
  34 |   outline:3px solid var(--hms-accent);
  35 |   outline-offset:4px;
  36 | }
  37 | .btn {
  38 |   border-radius:8px;
  39 |   padding:11px 19px;
  40 |   font-weight:600;
  41 |   white-space:nowrap;
  42 | }
  43 | .btn-primary,.btn-success {
  44 |   background:var(--hms-accent);
  45 |   border-color:var(--hms-accent);
  46 | }
  47 | .btn-primary:hover,.btn-success:hover,.btn-primary:focus,.btn-success:focus {
  48 |   background:var(--hms-accent-dark);
  49 |   border-color:var(--hms-accent-dark);
  50 | }
  51 | .btn-outline-primary {
  52 |   color:var(--hms-accent);
  53 |   border-color:var(--hms-accent);
  54 | }
  55 | .btn-outline-primary:hover {
  56 |   color:#fff;
  57 |   background:var(--hms-accent);
  58 |   border-color:var(--hms-accent);
  59 | }
  60 | .bg-success {
  61 |   background-color:var(--hms-accent)!important;
  62 | }
  63 | .text-success {
  64 |   color:var(--hms-accent)!important;
  65 | }
  66 | .container {
  67 |   max-width:1200px;
  68 | }
  69 | .hms-nav {
  70 |   padding:15px 0;
  71 |   border-bottom:1px solid var(--hms-line);
  72 |   background:#fff;
  73 | }
  74 | .navbar-brand {
  75 |   color:var(--hms-ink);
  76 |   display:flex;
  77 |   align-items:center;
  78 |   gap:10px;
  79 |   font-size:25px;
  80 |   font-weight:750;
  81 |   line-height:1.1;
  82 | }
  83 | .navbar-brand:hover {
  84 |   color:var(--hms-accent);
  85 | }
  86 | .brand-mark {
  87 |   display:grid;
  88 |   place-items:center;
  89 |   background:var(--hms-accent);
  90 |   color:white;
  91 |   width:40px;
  92 |   height:40px;
  93 |   border-radius:8px;
  94 |   font-size:34px;
  95 |   font-weight:400;
  96 | }
  97 | .brand-sub {
  98 |   display:block;
  99 |   font-size:10px;
 100 |   letter-spacing:.01em;
 101 |   font-weight:500;
 102 |   margin-top:4px;
 103 | }
 104 | .hms-nav .nav-link {
 105 |   color:var(--hms-muted);
 106 |   font-size:14px;
 107 |   font-weight:600;
 108 | }
 109 | .hms-nav .nav-link:hover {
 110 |   color:var(--hms-accent);
 111 | }
 112 | .account-toggle {
 113 |   border:0;
 114 |   background:transparent;
 115 | }
 116 | .skip-link {
 117 |   position:fixed;
 118 |   left:15px;
 119 |   top:-80px;
 120 |   background:#fff;
 121 |   padding:12px;
 122 |   z-index:100;
 123 | }
 124 | .skip-link:focus {
 125 |   top:5px;
 126 | }
 127 | .home-hero {
 128 |   display:grid;
 129 |   grid-template-columns:1fr 1fr;
 130 |   align-items:center;
 131 |   gap:70px;
 132 |   padding-top:58px;
 133 |   padding-bottom:58px;
 134 | }
 135 | .eyebrow {
 136 |   font-size:11px;
 137 |   font-weight:750;
 138 |   letter-spacing:.13em;
 139 |   color:var(--hms-accent);
 140 |   margin-bottom:22px;
 141 | }
 142 | .hero-copy h1 {
 143 |   font-size:clamp(48px,5.7vw,76px);
 144 |   font-weight:700;
 145 |   line-height:1.04;
 146 |   margin:0 0 24px;
 147 |   letter-spacing:-.055em;
 148 | }
 149 | .hero-description {
 150 |   font-size:18px;
 151 |   color:var(--hms-muted);
 152 |   max-width:360px;
 153 |   line-height:1.6;
 154 |   margin-bottom:30px;
 155 | }
 156 | .hero-photo {
 157 |   height:425px;
 158 |   border-radius:16px;
 159 |   overflow:hidden;
 160 |   background:var(--hms-surface);
 161 | }
 162 | .hero-photo img {
 163 |   width:100%;
 164 |   height:100%;
 165 |   object-fit:cover;
 166 | }
 167 | .journey {
 168 |   display:grid;
 169 |   grid-template-columns:repeat(3,1fr);
 170 |   gap:25px;
 171 |   padding-top:28px;
 172 |   padding-bottom:28px;
 173 |   border-top:1px solid var(--hms-line);
 174 |   border-bottom:1px solid var(--hms-line);
 175 | }
 176 | .journey>div {
 177 |   display:flex;
 178 |   gap:15px;
 179 | }
 180 | .step-number {
 181 |   color:var(--hms-accent);
 182 |   font-size:14px;
 183 |   font-weight:700;
 184 |   padding-top:2px;
 185 | }
 186 | .journey h2 {
 187 |   font-size:17px;
 188 |   letter-spacing:-.02em;
 189 |   font-weight:650;
 190 |   margin:0 0 7px;
 191 | }
 192 | .journey p {
 193 |   font-size:13px;
 194 |   color:var(--hms-muted);
 195 |   margin:0;
 196 | }
 197 | .care-section {
 198 |   padding-top:72px;
 199 |   padding-bottom:65px;
 200 | }
 201 | .section-heading {
 202 |   max-width:600px;
 203 |   margin-bottom:30px;
 204 | }
 205 | .section-heading h2 {
 206 |   font-size:36px;
 207 |   font-weight:650;
 208 |   margin-bottom:14px;
 209 | }
 210 | .section-heading p,.portal-section p {
 211 |   color:var(--hms-muted);
 212 | }
 213 | .specialty-grid {
 214 |   display:grid;
 215 |   grid-template-columns:repeat(4,1fr);
 216 |   gap:18px;
 217 | }
 218 | .specialty-grid>a {
 219 |   text-decoration:none;
 220 |   background:var(--hms-surface);
 221 |   border-radius:12px;
 222 |   padding:28px 24px;
 223 |   color:var(--hms-ink);
 224 |   transition:background .15s;
 225 | }
 226 | .specialty-grid>a:hover {
 227 |   background:#e5f0ec;
 228 | }
 229 | .specialty-grid>a>i {
 230 |   font-size:24px;
 231 |   color:var(--hms-accent);
 232 |   margin-bottom:30px;
 233 | }
 234 | .specialty-grid h3 {
 235 |   font-size:20px;
 236 |   font-weight:650;
 237 |   letter-spacing:-.02em;
 238 | }
 239 | .specialty-grid p {
 240 |   font-size:14px;
 241 |   color:var(--hms-muted);
 242 |   margin:12px 0 24px;
 243 | }
 244 | .specialty-grid span {
 245 |   font-size:13px;
 246 |   font-weight:650;
 247 |   color:var(--hms-accent);
 248 | }
 249 | .specialty-grid span i {
 250 |   margin-left:8px;
 251 | }
 252 | .portal-section {
 253 |   border-top:1px solid var(--hms-line);
 254 |   display:flex;
 255 |   align-items:center;
 256 |   justify-content:space-between;
 257 |   gap:30px;
 258 |   padding-top:38px;
 259 |   padding-bottom:48px;
 260 | }
 261 | .portal-section h2 {
 262 |   font-size:29px;
 263 |   max-width:520px;
 264 | }
 265 | .portal-section p {
 266 |   max-width:570px;
 267 |   margin-bottom:0;
 268 |   font-size:14px;
 269 | }
 270 | .hms-footer {
 271 |   border-top:1px solid var(--hms-line);
 272 |   padding:32px 0;
 273 |   color:var(--hms-muted);
 274 |   background:#fff;
 275 | }
 276 | .footer-layout {
 277 |   display:flex;
 278 |   justify-content:space-between;
 279 |   gap:25px;
 280 | }
 281 | .footer-brand {
 282 |   font-size:20px;
 283 |   font-weight:700;
 284 |   text-decoration:none;
 285 |   color:var(--hms-ink);
 286 | }
 287 | .footer-brand span {
 288 |   font-size:12px;
 289 |   font-weight:500;
 290 |   margin-left:10px;
 291 | }
 292 | .hms-footer p {
 293 |   font-size:12px;
 294 |   margin:8px 0 0;
 295 | }
 296 | .hms-footer nav {
 297 |   display:flex;
 298 |   gap:22px;
 299 |   align-items:center;
 300 | }
 301 | .hms-footer nav a {
 302 |   font-size:12px;
 303 |   color:var(--hms-muted);
 304 | }
 305 | .auth-main {
 306 |   min-height:70vh;
 307 |   background:var(--hms-surface);
 308 |   padding:55px 20px 70px;
 309 | }
 310 | .auth-layout {
 311 |   display:grid;
 312 |   grid-template-columns:1fr 1fr;
 313 |   align-items:center;
 314 |   gap:90px;
 315 |   max-width:950px;
 316 |   margin:auto;
 317 | }
 318 | .auth-copy h1 {
 319 |   font-size:48px;
 320 |   max-width:360px;
 321 |   font-weight:650;
 322 | }
 323 | .auth-copy p {
 324 |   color:var(--hms-muted);
 325 |   max-width:340px;
 326 |   margin-top:22px;
 327 | }
 328 | .auth-panel {
 329 |   background:#fff;
 330 |   border:1px solid var(--hms-line);
 331 |   border-radius:12px;
 332 |   padding:32px;
 333 | }
 334 | .auth-panel h2 {
 335 |   font-size:26px;
 336 |   font-weight:650;
 337 |   margin-bottom:8px;
 338 | }
 339 | .auth-panel .subtext {
 340 |   font-size:14px;
 341 |   color:var(--hms-muted);
 342 |   margin-bottom:26px;
 343 | }
 344 | .form-label {
 345 |   font-size:13px;
 346 |   font-weight:650;
 347 |   margin-bottom:6px;
 348 | }
 349 | .form-control,.form-select {
 350 |   border-radius:8px;
 351 |   border-color:#b5c9c3;
 352 |   min-height:45px;
 353 |   font-size:15px;
 354 |   color:var(--hms-ink);
 355 | }
 356 | .form-control:focus,.form-select:focus {
 357 |   border-color:var(--hms-accent);
 358 |   box-shadow:0 0 0 3px #09675f22;
 359 | }
 360 | .form-control::placeholder {
 361 |   color:#647b76;
 362 | }
 363 | .form-note {
 364 |   font-size:12px;
 365 |   color:var(--hms-muted);
 366 |   margin-top:7px;
 367 | }
 368 | .auth-switch {
 369 |   margin:22px 0 0;
 370 |   font-size:14px;
 371 | }
 372 | .page-main {
 373 |   background:var(--hms-surface);
 374 |   min-height:72vh;
 375 |   padding:40px 0 65px;
 376 | }
 377 | .page-title {
 378 |   font-size:38px;
 379 |   font-weight:650;
 380 |   margin-bottom:10px;
 381 | }
 382 | .page-intro {
 383 |   color:var(--hms-muted);
 384 |   max-width:650px;
 385 | }
 386 | .booking-layout {
 387 |   display:grid;
 388 |   grid-template-columns:1fr 300px;
 389 |   gap:28px;
 390 |   align-items:start;
 391 |   margin-top:30px;
 392 | }
 393 | .form-panel,.info-panel,.appointments-panel {
 394 |   background:#fff;
 395 |   border:1px solid var(--hms-line);
 396 |   border-radius:12px;
 397 |   padding:28px;
 398 | }
 399 | .form-panel h2,.info-panel h2 {
 400 |   font-size:21px;
 401 |   font-weight:650;
 402 |   margin:0 0 23px;
 403 | }
 404 | .info-panel {
 405 |   background:#e6f0ec;
 406 | }
 407 | .info-panel p,.info-panel li {
 408 |   font-size:14px;
 409 |   color:var(--hms-muted);
 410 | }
 411 | .info-panel ol {
 412 |   padding-left:20px;
 413 |   margin-bottom:20px;
 414 | }
 415 | .info-panel li {
 416 |   margin-bottom:12px;
 417 | }
 418 | .booking-actions {
 419 |   display:flex;
 420 |   align-items:center;
 421 |   gap:20px;
 422 |   margin-top:25px;
 423 | }
 424 | .booking-actions p {
 425 |   font-size:12px;
 426 |   color:var(--hms-muted);
 427 |   margin:0;
 428 | }
 429 | .appointments-heading {
 430 |   display:flex;
 431 |   justify-content:space-between;
 432 |   align-items:center;
 433 |   gap:20px;
 434 |   margin-bottom:28px;
 435 | }
 436 | .appointments-panel .table {
 437 |   margin-bottom:0;
 438 |   vertical-align:middle;
 439 |   font-size:14px;
 440 | }
 441 | .appointments-panel th {
 442 |   font-size:12px;
 443 |   color:var(--hms-muted);
 444 |   font-weight:600;
 445 |   white-space:nowrap;
 446 |   padding:15px 12px;
 447 | }
 448 | .appointments-panel td {
 449 |   padding:18px 12px;
 450 |   max-width:280px;
 451 |   overflow-wrap:anywhere;
 452 | }
 453 | .status-pending {
 454 |   background:#f4ecce;
 455 |   color:#685315;
 456 |   display:inline-block;
 457 |   padding:4px 10px;
 458 |   border-radius:6px;
 459 |   font-size:12px;
 460 |   font-weight:600;
 461 | }
 462 | .empty-state {
 463 |   text-align:center;
 464 |   padding:55px 15px;
 465 | }
 466 | .empty-state>i {
 467 |   font-size:35px;
 468 |   color:var(--hms-accent);
 469 |   margin-bottom:22px;
 470 | }
 471 | .empty-state h2 {
 472 |   font-size:26px;
 473 | }
 474 | .empty-state p {
 475 |   color:var(--hms-muted);
 476 |   margin:16px auto 25px;
 477 |   max-width:390px;
 478 | }
 479 | .paint-card {
 480 |   box-shadow:none!important;
 481 |   border:1px solid var(--hms-line);
 482 |   border-radius:12px;
 483 | }
 484 | .alert {
 485 |   font-size:14px;
 486 |   border-radius:8px;
 487 | }
 488 | .table-responsive {
 489 |   border-radius:8px;
 490 | }
 491 | @media(max-width:991px) {
 492 |   .hms-nav .navbar-collapse {
 493 |     padding-top:20px;
 494 |   }
 495 |   .hms-nav .nav-item {
 496 |     margin-bottom:8px;
 497 |   }
 498 |   .home-hero {
 499 |     gap:30px;
 500 |     padding-top:38px;
 501 |   }
 502 |   .hero-photo {
 503 |     height:360px;
 504 |   }
 505 |   .specialty-grid {
 506 |     grid-template-columns:repeat(2,1fr);
 507 |   }
 508 |   .auth-layout {
 509 |     gap:35px;
 510 |   }
 511 |   .booking-layout {
 512 |     grid-template-columns:1fr 240px;
 513 |   }
 514 |   .footer-layout {
 515 |     flex-direction:column;
 516 |   }
 517 | }
 518 | @media(max-width:767px) {
 519 |   .container {
 520 |     padding-left:20px;
 521 |     padding-right:20px;
 522 |   }
 523 |   .home-hero {
 524 |     grid-template-columns:1fr;
 525 |     padding-bottom:35px;
 526 |     gap:30px;
 527 |   }
 528 |   .hero-copy h1 {
 529 |     font-size:53px;
 530 |   }
 531 |   .hero-photo {
 532 |     height:270px;
 533 |   }
 534 |   .hero-description {
 535 |     font-size:17px;
 536 |   }
 537 |   .journey {
 538 |     grid-template-columns:1fr;
 539 |     gap:23px;
 540 |     padding-top:24px;
 541 |     padding-bottom:24px;
 542 |   }
 543 |   .care-section {
 544 |     padding-top:45px;
 545 |     padding-bottom:35px;
 546 |   }
 547 |   .section-heading h2 {
 548 |     font-size:30px;
 549 |   }
 550 |   .specialty-grid {
 551 |     gap:12px;
 552 |   }
 553 |   .specialty-grid>a {
 554 |     padding:22px 17px;
 555 |   }
 556 |   .specialty-grid h3 {
 557 |     font-size:18px;
 558 |   }
 559 |   .specialty-grid p {
 560 |     font-size:13px;
 561 |   }
 562 |   .portal-section {
 563 |     flex-direction:column;
 564 |     align-items:start;
 565 |     padding-top:30px;
 566 |   }
 567 |   .portal-section h2 {
 568 |     font-size:26px;
 569 |   }
 570 |   .hms-footer nav {
 571 |     flex-wrap:wrap;
 572 |     gap:18px;
 573 |   }
 574 |   .auth-main {
 575 |     padding:32px 20px 45px;
 576 |   }
 577 |   .auth-layout {
 578 |     grid-template-columns:1fr;
 579 |     gap:20px;
 580 |   }
 581 |   .auth-copy h1 {
 582 |     font-size:34px;
 583 |     max-width:none;
 584 |   }
 585 |   .auth-copy p {
 586 |     margin-top:10px;
 587 |     max-width:none;
 588 |   }
 589 |   .auth-panel {
 590 |     padding:24px;
 591 |   }
 592 |   .booking-layout {
 593 |     grid-template-columns:1fr;
 594 |   }
 595 |   .booking-actions {
 596 |     flex-direction:column;
 597 |     align-items:start;
 598 |   }
 599 |   .page-title {
 600 |     font-size:32px;
 601 |   }
 602 |   .appointments-heading {
 603 |     flex-direction:column;
 604 |     align-items:start;
 605 |   }
 606 |   .form-panel,.appointments-panel {
 607 |     padding:20px;
 608 |   }
 609 |   .page-main {
 610 |     padding-top:30px;
 611 |   }
 612 |   .info-panel {
 613 |     order:-1;
 614 |   }
 615 |   .info-panel ol {
 616 |     display:none;
 617 |   }
 618 | }
 619 | @media(prefers-reduced-motion:reduce) {
 620 |   html {
 621 |     scroll-behavior:auto;
 622 |   }
 623 |   * {
 624 |     transition:none!important;
 625 |   }
 626 | }
 627 | .demo-notice {
 628 |   text-align:center;
 629 |   background:var(--hms-surface);
 630 |   color:var(--hms-muted);
 631 |   font-size:12px;
 632 |   padding:6px 20px;
 633 |   border-bottom:1px solid var(--hms-line);
 634 | }
 635 | .dashboard-grid {
 636 |   display:grid;
 637 |   grid-template-columns:repeat(4,1fr);
 638 |   gap:20px;
 639 |   margin-top:30px;
 640 | }
 641 | .dashboard-grid>a,.dashboard-grid>div {
 642 |   display:flex;
 643 |   flex-direction:column;
 644 |   padding:26px;
 645 |   background:#fff;
 646 |   border:1px solid var(--hms-line);
 647 |   border-radius:12px;
 648 |   color:var(--hms-ink);
 649 |   text-decoration:none;
 650 | }
 651 | .dashboard-grid>a:hover {
 652 |   border-color:var(--hms-accent);
 653 | }
 654 | .dashboard-grid span {
 655 |   font-size:13px;
 656 |   color:var(--hms-muted);
 657 | }
 658 | .dashboard-grid strong {
 659 |   font-size:43px;
 660 |   font-weight:650;
 661 |   line-height:1.5;
 662 |   letter-spacing:-.04em;
 663 | }
 664 | .doctor-stats {
 665 |   grid-template-columns:repeat(2,1fr);
 666 |   max-width:760px;
 667 | }
 668 | .specialty-tags {
 669 |   display:flex;
 670 |   flex-wrap:wrap;
 671 |   gap:10px;
 672 |   margin-bottom:25px;
 673 | }
 674 | .specialty-tags span {
 675 |   background:var(--hms-surface);
 676 |   padding:6px 12px;
 677 |   border-radius:6px;
 678 |   font-size:13px;
 679 | }
 680 | .specialty-form {
 681 |   display:flex;
 682 |   gap:15px;
 683 |   align-items:end;
 684 | }
 685 | .specialty-form>div {
 686 |   width:320px;
 687 |   max-width:100%;
 688 | }
 689 | @media(max-width:767px) {
 690 |   .dashboard-grid {
 691 |     grid-template-columns:repeat(2,1fr);
 692 |     gap:12px;
 693 |   }
 694 |   .dashboard-grid>a,.dashboard-grid>div {
 695 |     padding:18px;
 696 |   }
 697 |   .specialty-form {
 698 |     flex-direction:column;
 699 |     align-items:start;
 700 |   }
 701 |   .specialty-form>div {
 702 |     width:100%;
 703 |   }
 704 | }
 705 |
 706 | .preserve-lines { white-space: pre-wrap; overflow-wrap: anywhere; }
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Starts presentation rules for selector :root. These selectors do not define Java classes or routes. |
| 2 | Defines reusable CSS token --hms-accent as #09675f for use through var(...). |
| 3 | Defines reusable CSS token --hms-accent-dark as #064e48 for use through var(...). |
| 4 | Defines reusable CSS token --hms-ink as #173431 for use through var(...). |
| 5 | Defines reusable CSS token --hms-muted as #536c68 for use through var(...). |
| 6 | Defines reusable CSS token --hms-line as #dce7e4 for use through var(...). |
| 7 | Defines reusable CSS token --hms-surface as #f2f7f5 for use through var(...). |
| 9 | Starts presentation rules for selector html. These selectors do not define Java classes or routes. |
| 10 | Sets scroll motion to smooth. It changes visual rendering, not database data or access control. |
| 11 | Sets scroll-padding-top presentation property to 24px. It changes visual rendering, not database data or access control. |
| 13 | Starts presentation rules for selector body. These selectors do not define Java classes or routes. |
| 14 | Sets typeface fallback list to "Segoe UI",Arial,sans-serif. It changes visual rendering, not database data or access control. |
| 15 | Sets foreground/text color to var(--hms-ink). It changes visual rendering, not database data or access control. |
| 16 | Sets background appearance to #fff. It changes visual rendering, not database data or access control. |
| 17 | Sets line spacing to 1.6. It changes visual rendering, not database data or access control. |
| 18 | Sets outside spacing to 0. It changes visual rendering, not database data or access control. |
| 20 | Starts presentation rules for selector a. These selectors do not define Java classes or routes. |
| 21 | Sets foreground/text color to var(--hms-accent). It changes visual rendering, not database data or access control. |
| 23 | Starts presentation rules for selector a:hover. These selectors do not define Java classes or routes. |
| 24 | Sets foreground/text color to var(--hms-accent-dark). It changes visual rendering, not database data or access control. |
| 26 | Starts presentation rules for selector h1,h2,h3. These selectors do not define Java classes or routes. |
| 27 | Sets letter-spacing presentation property to -.04em. It changes visual rendering, not database data or access control. |
| 28 | Sets line spacing to 1.12. It changes visual rendering, not database data or access control. |
| 30 | Starts presentation rules for selector a,button,input,select,textarea. These selectors do not define Java classes or routes. |
| 31 | Sets touch-action presentation property to manipulation. It changes visual rendering, not database data or access control. |
| 33 | Starts presentation rules for selector :focus-visible. These selectors do not define Java classes or routes. |
| 34 | Sets focus outline to 3px solid var(--hms-accent). It changes visual rendering, not database data or access control. |
| 35 | Sets outline-offset presentation property to 4px. It changes visual rendering, not database data or access control. |
| 37 | Starts presentation rules for selector .btn. These selectors do not define Java classes or routes. |
| 38 | Sets corner rounding to 8px. It changes visual rendering, not database data or access control. |
| 39 | Sets inside spacing to 11px 19px. It changes visual rendering, not database data or access control. |
| 40 | Sets text weight to 600. It changes visual rendering, not database data or access control. |
| 41 | Sets text wrapping to nowrap. It changes visual rendering, not database data or access control. |
| 43 | Starts presentation rules for selector .btn-primary,.btn-success. These selectors do not define Java classes or routes. |
| 44 | Sets background appearance to var(--hms-accent). It changes visual rendering, not database data or access control. |
| 45 | Sets border-color presentation property to var(--hms-accent). It changes visual rendering, not database data or access control. |
| 47 | Starts presentation rules for selector .btn-primary:hover,.btn-success:hover,.btn-primary:focus,.btn-success:focus. These selectors do not define Java classes or routes. |
| 48 | Sets background appearance to var(--hms-accent-dark). It changes visual rendering, not database data or access control. |
| 49 | Sets border-color presentation property to var(--hms-accent-dark). It changes visual rendering, not database data or access control. |
| 51 | Starts presentation rules for selector .btn-outline-primary. These selectors do not define Java classes or routes. |
| 52 | Sets foreground/text color to var(--hms-accent). It changes visual rendering, not database data or access control. |
| 53 | Sets border-color presentation property to var(--hms-accent). It changes visual rendering, not database data or access control. |
| 55 | Starts presentation rules for selector .btn-outline-primary:hover. These selectors do not define Java classes or routes. |
| 56 | Sets foreground/text color to #fff. It changes visual rendering, not database data or access control. |
| 57 | Sets background appearance to var(--hms-accent). It changes visual rendering, not database data or access control. |
| 58 | Sets border-color presentation property to var(--hms-accent). It changes visual rendering, not database data or access control. |
| 60 | Starts presentation rules for selector .bg-success. These selectors do not define Java classes or routes. |
| 61 | Sets background-color presentation property to var(--hms-accent)!important. It changes visual rendering, not database data or access control. |
| 63 | Starts presentation rules for selector .text-success. These selectors do not define Java classes or routes. |
| 64 | Sets foreground/text color to var(--hms-accent)!important. It changes visual rendering, not database data or access control. |
| 66 | Starts presentation rules for selector .container. These selectors do not define Java classes or routes. |
| 67 | Sets upper width limit to 1200px. It changes visual rendering, not database data or access control. |
| 69 | Starts presentation rules for selector .hms-nav. These selectors do not define Java classes or routes. |
| 70 | Sets inside spacing to 15px 0. It changes visual rendering, not database data or access control. |
| 71 | Sets border-bottom presentation property to 1px solid var(--hms-line). It changes visual rendering, not database data or access control. |
| 72 | Sets background appearance to #fff. It changes visual rendering, not database data or access control. |
| 74 | Starts presentation rules for selector .navbar-brand. These selectors do not define Java classes or routes. |
| 75 | Sets foreground/text color to var(--hms-ink). It changes visual rendering, not database data or access control. |
| 76 | Sets layout model to flex. It changes visual rendering, not database data or access control. |
| 77 | Sets item alignment to center. It changes visual rendering, not database data or access control. |
| 78 | Sets space between layout items to 10px. It changes visual rendering, not database data or access control. |
| 79 | Sets text size to 25px. It changes visual rendering, not database data or access control. |
| 80 | Sets text weight to 750. It changes visual rendering, not database data or access control. |
| 81 | Sets line spacing to 1.1. It changes visual rendering, not database data or access control. |
| 83 | Starts presentation rules for selector .navbar-brand:hover. These selectors do not define Java classes or routes. |
| 84 | Sets foreground/text color to var(--hms-accent). It changes visual rendering, not database data or access control. |
| 86 | Starts presentation rules for selector .brand-mark. These selectors do not define Java classes or routes. |
| 87 | Sets layout model to grid. It changes visual rendering, not database data or access control. |
| 88 | Sets place-items presentation property to center. It changes visual rendering, not database data or access control. |
| 89 | Sets background appearance to var(--hms-accent). It changes visual rendering, not database data or access control. |
| 90 | Sets foreground/text color to white. It changes visual rendering, not database data or access control. |
| 91 | Sets width presentation property to 40px. It changes visual rendering, not database data or access control. |
| 92 | Sets height presentation property to 40px. It changes visual rendering, not database data or access control. |
| 93 | Sets corner rounding to 8px. It changes visual rendering, not database data or access control. |
| 94 | Sets text size to 34px. It changes visual rendering, not database data or access control. |
| 95 | Sets text weight to 400. It changes visual rendering, not database data or access control. |
| 97 | Starts presentation rules for selector .brand-sub. These selectors do not define Java classes or routes. |
| 98 | Sets layout model to block. It changes visual rendering, not database data or access control. |
| 99 | Sets text size to 10px. It changes visual rendering, not database data or access control. |
| 100 | Sets letter-spacing presentation property to .01em. It changes visual rendering, not database data or access control. |
| 101 | Sets text weight to 500. It changes visual rendering, not database data or access control. |
| 102 | Sets margin-top presentation property to 4px. It changes visual rendering, not database data or access control. |
| 104 | Starts presentation rules for selector .hms-nav .nav-link. These selectors do not define Java classes or routes. |
| 105 | Sets foreground/text color to var(--hms-muted). It changes visual rendering, not database data or access control. |
| 106 | Sets text size to 14px. It changes visual rendering, not database data or access control. |
| 107 | Sets text weight to 600. It changes visual rendering, not database data or access control. |
| 109 | Starts presentation rules for selector .hms-nav .nav-link:hover. These selectors do not define Java classes or routes. |
| 110 | Sets foreground/text color to var(--hms-accent). It changes visual rendering, not database data or access control. |
| 112 | Starts presentation rules for selector .account-toggle. These selectors do not define Java classes or routes. |
| 113 | Sets border presentation property to 0. It changes visual rendering, not database data or access control. |
| 114 | Sets background appearance to transparent. It changes visual rendering, not database data or access control. |
| 116 | Starts presentation rules for selector .skip-link. These selectors do not define Java classes or routes. |
| 117 | Sets positioning mode to fixed. It changes visual rendering, not database data or access control. |
| 118 | Sets left presentation property to 15px. It changes visual rendering, not database data or access control. |
| 119 | Sets top presentation property to -80px. It changes visual rendering, not database data or access control. |
| 120 | Sets background appearance to #fff. It changes visual rendering, not database data or access control. |
| 121 | Sets inside spacing to 12px. It changes visual rendering, not database data or access control. |
| 122 | Sets stack order to 100. It changes visual rendering, not database data or access control. |
| 124 | Starts presentation rules for selector .skip-link:focus. These selectors do not define Java classes or routes. |
| 125 | Sets top presentation property to 5px. It changes visual rendering, not database data or access control. |
| 127 | Starts presentation rules for selector .home-hero. These selectors do not define Java classes or routes. |
| 128 | Sets layout model to grid. It changes visual rendering, not database data or access control. |
| 129 | Sets grid column structure to 1fr 1fr. It changes visual rendering, not database data or access control. |
| 130 | Sets item alignment to center. It changes visual rendering, not database data or access control. |
| 131 | Sets space between layout items to 70px. It changes visual rendering, not database data or access control. |
| 132 | Sets padding-top presentation property to 58px. It changes visual rendering, not database data or access control. |
| 133 | Sets padding-bottom presentation property to 58px. It changes visual rendering, not database data or access control. |
| 135 | Starts presentation rules for selector .eyebrow. These selectors do not define Java classes or routes. |
| 136 | Sets text size to 11px. It changes visual rendering, not database data or access control. |
| 137 | Sets text weight to 750. It changes visual rendering, not database data or access control. |
| 138 | Sets letter-spacing presentation property to .13em. It changes visual rendering, not database data or access control. |
| 139 | Sets foreground/text color to var(--hms-accent). It changes visual rendering, not database data or access control. |
| 140 | Sets margin-bottom presentation property to 22px. It changes visual rendering, not database data or access control. |
| 142 | Starts presentation rules for selector .hero-copy h1. These selectors do not define Java classes or routes. |
| 143 | Sets text size to clamp(48px,5.7vw,76px). It changes visual rendering, not database data or access control. |
| 144 | Sets text weight to 700. It changes visual rendering, not database data or access control. |
| 145 | Sets line spacing to 1.04. It changes visual rendering, not database data or access control. |
| 146 | Sets outside spacing to 0 0 24px. It changes visual rendering, not database data or access control. |
| 147 | Sets letter-spacing presentation property to -.055em. It changes visual rendering, not database data or access control. |
| 149 | Starts presentation rules for selector .hero-description. These selectors do not define Java classes or routes. |
| 150 | Sets text size to 18px. It changes visual rendering, not database data or access control. |
| 151 | Sets foreground/text color to var(--hms-muted). It changes visual rendering, not database data or access control. |
| 152 | Sets upper width limit to 360px. It changes visual rendering, not database data or access control. |
| 153 | Sets line spacing to 1.6. It changes visual rendering, not database data or access control. |
| 154 | Sets margin-bottom presentation property to 30px. It changes visual rendering, not database data or access control. |
| 156 | Starts presentation rules for selector .hero-photo. These selectors do not define Java classes or routes. |
| 157 | Sets height presentation property to 425px. It changes visual rendering, not database data or access control. |
| 158 | Sets corner rounding to 16px. It changes visual rendering, not database data or access control. |
| 159 | Sets overflow handling to hidden. It changes visual rendering, not database data or access control. |
| 160 | Sets background appearance to var(--hms-surface). It changes visual rendering, not database data or access control. |
| 162 | Starts presentation rules for selector .hero-photo img. These selectors do not define Java classes or routes. |
| 163 | Sets width presentation property to 100%. It changes visual rendering, not database data or access control. |
| 164 | Sets height presentation property to 100%. It changes visual rendering, not database data or access control. |
| 165 | Sets image fit/cropping to cover. It changes visual rendering, not database data or access control. |
| 167 | Starts presentation rules for selector .journey. These selectors do not define Java classes or routes. |
| 168 | Sets layout model to grid. It changes visual rendering, not database data or access control. |
| 169 | Sets grid column structure to repeat(3,1fr). It changes visual rendering, not database data or access control. |
| 170 | Sets space between layout items to 25px. It changes visual rendering, not database data or access control. |
| 171 | Sets padding-top presentation property to 28px. It changes visual rendering, not database data or access control. |
| 172 | Sets padding-bottom presentation property to 28px. It changes visual rendering, not database data or access control. |
| 173 | Sets border-top presentation property to 1px solid var(--hms-line). It changes visual rendering, not database data or access control. |
| 174 | Sets border-bottom presentation property to 1px solid var(--hms-line). It changes visual rendering, not database data or access control. |
| 176 | Starts presentation rules for selector .journey>div. These selectors do not define Java classes or routes. |
| 177 | Sets layout model to flex. It changes visual rendering, not database data or access control. |
| 178 | Sets space between layout items to 15px. It changes visual rendering, not database data or access control. |
| 180 | Starts presentation rules for selector .step-number. These selectors do not define Java classes or routes. |
| 181 | Sets foreground/text color to var(--hms-accent). It changes visual rendering, not database data or access control. |
| 182 | Sets text size to 14px. It changes visual rendering, not database data or access control. |
| 183 | Sets text weight to 700. It changes visual rendering, not database data or access control. |
| 184 | Sets padding-top presentation property to 2px. It changes visual rendering, not database data or access control. |
| 186 | Starts presentation rules for selector .journey h2. These selectors do not define Java classes or routes. |
| 187 | Sets text size to 17px. It changes visual rendering, not database data or access control. |
| 188 | Sets letter-spacing presentation property to -.02em. It changes visual rendering, not database data or access control. |
| 189 | Sets text weight to 650. It changes visual rendering, not database data or access control. |
| 190 | Sets outside spacing to 0 0 7px. It changes visual rendering, not database data or access control. |
| 192 | Starts presentation rules for selector .journey p. These selectors do not define Java classes or routes. |
| 193 | Sets text size to 13px. It changes visual rendering, not database data or access control. |
| 194 | Sets foreground/text color to var(--hms-muted). It changes visual rendering, not database data or access control. |
| 195 | Sets outside spacing to 0. It changes visual rendering, not database data or access control. |
| 197 | Starts presentation rules for selector .care-section. These selectors do not define Java classes or routes. |
| 198 | Sets padding-top presentation property to 72px. It changes visual rendering, not database data or access control. |
| 199 | Sets padding-bottom presentation property to 65px. It changes visual rendering, not database data or access control. |
| 201 | Starts presentation rules for selector .section-heading. These selectors do not define Java classes or routes. |
| 202 | Sets upper width limit to 600px. It changes visual rendering, not database data or access control. |
| 203 | Sets margin-bottom presentation property to 30px. It changes visual rendering, not database data or access control. |
| 205 | Starts presentation rules for selector .section-heading h2. These selectors do not define Java classes or routes. |
| 206 | Sets text size to 36px. It changes visual rendering, not database data or access control. |
| 207 | Sets text weight to 650. It changes visual rendering, not database data or access control. |
| 208 | Sets margin-bottom presentation property to 14px. It changes visual rendering, not database data or access control. |
| 210 | Starts presentation rules for selector .section-heading p,.portal-section p. These selectors do not define Java classes or routes. |
| 211 | Sets foreground/text color to var(--hms-muted). It changes visual rendering, not database data or access control. |
| 213 | Starts presentation rules for selector .specialty-grid. These selectors do not define Java classes or routes. |
| 214 | Sets layout model to grid. It changes visual rendering, not database data or access control. |
| 215 | Sets grid column structure to repeat(4,1fr). It changes visual rendering, not database data or access control. |
| 216 | Sets space between layout items to 18px. It changes visual rendering, not database data or access control. |
| 218 | Starts presentation rules for selector .specialty-grid>a. These selectors do not define Java classes or routes. |
| 219 | Sets text-decoration presentation property to none. It changes visual rendering, not database data or access control. |
| 220 | Sets background appearance to var(--hms-surface). It changes visual rendering, not database data or access control. |
| 221 | Sets corner rounding to 12px. It changes visual rendering, not database data or access control. |
| 222 | Sets inside spacing to 28px 24px. It changes visual rendering, not database data or access control. |
| 223 | Sets foreground/text color to var(--hms-ink). It changes visual rendering, not database data or access control. |
| 224 | Sets state-change animation to background .15s. It changes visual rendering, not database data or access control. |
| 226 | Starts presentation rules for selector .specialty-grid>a:hover. These selectors do not define Java classes or routes. |
| 227 | Sets background appearance to #e5f0ec. It changes visual rendering, not database data or access control. |
| 229 | Starts presentation rules for selector .specialty-grid>a>i. These selectors do not define Java classes or routes. |
| 230 | Sets text size to 24px. It changes visual rendering, not database data or access control. |
| 231 | Sets foreground/text color to var(--hms-accent). It changes visual rendering, not database data or access control. |
| 232 | Sets margin-bottom presentation property to 30px. It changes visual rendering, not database data or access control. |
| 234 | Starts presentation rules for selector .specialty-grid h3. These selectors do not define Java classes or routes. |
| 235 | Sets text size to 20px. It changes visual rendering, not database data or access control. |
| 236 | Sets text weight to 650. It changes visual rendering, not database data or access control. |
| 237 | Sets letter-spacing presentation property to -.02em. It changes visual rendering, not database data or access control. |
| 239 | Starts presentation rules for selector .specialty-grid p. These selectors do not define Java classes or routes. |
| 240 | Sets text size to 14px. It changes visual rendering, not database data or access control. |
| 241 | Sets foreground/text color to var(--hms-muted). It changes visual rendering, not database data or access control. |
| 242 | Sets outside spacing to 12px 0 24px. It changes visual rendering, not database data or access control. |
| 244 | Starts presentation rules for selector .specialty-grid span. These selectors do not define Java classes or routes. |
| 245 | Sets text size to 13px. It changes visual rendering, not database data or access control. |
| 246 | Sets text weight to 650. It changes visual rendering, not database data or access control. |
| 247 | Sets foreground/text color to var(--hms-accent). It changes visual rendering, not database data or access control. |
| 249 | Starts presentation rules for selector .specialty-grid span i. These selectors do not define Java classes or routes. |
| 250 | Sets margin-left presentation property to 8px. It changes visual rendering, not database data or access control. |
| 252 | Starts presentation rules for selector .portal-section. These selectors do not define Java classes or routes. |
| 253 | Sets border-top presentation property to 1px solid var(--hms-line). It changes visual rendering, not database data or access control. |
| 254 | Sets layout model to flex. It changes visual rendering, not database data or access control. |
| 255 | Sets item alignment to center. It changes visual rendering, not database data or access control. |
| 256 | Sets item distribution to space-between. It changes visual rendering, not database data or access control. |
| 257 | Sets space between layout items to 30px. It changes visual rendering, not database data or access control. |
| 258 | Sets padding-top presentation property to 38px. It changes visual rendering, not database data or access control. |
| 259 | Sets padding-bottom presentation property to 48px. It changes visual rendering, not database data or access control. |
| 261 | Starts presentation rules for selector .portal-section h2. These selectors do not define Java classes or routes. |
| 262 | Sets text size to 29px. It changes visual rendering, not database data or access control. |
| 263 | Sets upper width limit to 520px. It changes visual rendering, not database data or access control. |
| 265 | Starts presentation rules for selector .portal-section p. These selectors do not define Java classes or routes. |
| 266 | Sets upper width limit to 570px. It changes visual rendering, not database data or access control. |
| 267 | Sets margin-bottom presentation property to 0. It changes visual rendering, not database data or access control. |
| 268 | Sets text size to 14px. It changes visual rendering, not database data or access control. |
| 270 | Starts presentation rules for selector .hms-footer. These selectors do not define Java classes or routes. |
| 271 | Sets border-top presentation property to 1px solid var(--hms-line). It changes visual rendering, not database data or access control. |
| 272 | Sets inside spacing to 32px 0. It changes visual rendering, not database data or access control. |
| 273 | Sets foreground/text color to var(--hms-muted). It changes visual rendering, not database data or access control. |
| 274 | Sets background appearance to #fff. It changes visual rendering, not database data or access control. |
| 276 | Starts presentation rules for selector .footer-layout. These selectors do not define Java classes or routes. |
| 277 | Sets layout model to flex. It changes visual rendering, not database data or access control. |
| 278 | Sets item distribution to space-between. It changes visual rendering, not database data or access control. |
| 279 | Sets space between layout items to 25px. It changes visual rendering, not database data or access control. |
| 281 | Starts presentation rules for selector .footer-brand. These selectors do not define Java classes or routes. |
| 282 | Sets text size to 20px. It changes visual rendering, not database data or access control. |
| 283 | Sets text weight to 700. It changes visual rendering, not database data or access control. |
| 284 | Sets text-decoration presentation property to none. It changes visual rendering, not database data or access control. |
| 285 | Sets foreground/text color to var(--hms-ink). It changes visual rendering, not database data or access control. |
| 287 | Starts presentation rules for selector .footer-brand span. These selectors do not define Java classes or routes. |
| 288 | Sets text size to 12px. It changes visual rendering, not database data or access control. |
| 289 | Sets text weight to 500. It changes visual rendering, not database data or access control. |
| 290 | Sets margin-left presentation property to 10px. It changes visual rendering, not database data or access control. |
| 292 | Starts presentation rules for selector .hms-footer p. These selectors do not define Java classes or routes. |
| 293 | Sets text size to 12px. It changes visual rendering, not database data or access control. |
| 294 | Sets outside spacing to 8px 0 0. It changes visual rendering, not database data or access control. |
| 296 | Starts presentation rules for selector .hms-footer nav. These selectors do not define Java classes or routes. |
| 297 | Sets layout model to flex. It changes visual rendering, not database data or access control. |
| 298 | Sets space between layout items to 22px. It changes visual rendering, not database data or access control. |
| 299 | Sets item alignment to center. It changes visual rendering, not database data or access control. |
| 301 | Starts presentation rules for selector .hms-footer nav a. These selectors do not define Java classes or routes. |
| 302 | Sets text size to 12px. It changes visual rendering, not database data or access control. |
| 303 | Sets foreground/text color to var(--hms-muted). It changes visual rendering, not database data or access control. |
| 305 | Starts presentation rules for selector .auth-main. These selectors do not define Java classes or routes. |
| 306 | Sets min-height presentation property to 70vh. It changes visual rendering, not database data or access control. |
| 307 | Sets background appearance to var(--hms-surface). It changes visual rendering, not database data or access control. |
| 308 | Sets inside spacing to 55px 20px 70px. It changes visual rendering, not database data or access control. |
| 310 | Starts presentation rules for selector .auth-layout. These selectors do not define Java classes or routes. |
| 311 | Sets layout model to grid. It changes visual rendering, not database data or access control. |
| 312 | Sets grid column structure to 1fr 1fr. It changes visual rendering, not database data or access control. |
| 313 | Sets item alignment to center. It changes visual rendering, not database data or access control. |
| 314 | Sets space between layout items to 90px. It changes visual rendering, not database data or access control. |
| 315 | Sets upper width limit to 950px. It changes visual rendering, not database data or access control. |
| 316 | Sets outside spacing to auto. It changes visual rendering, not database data or access control. |
| 318 | Starts presentation rules for selector .auth-copy h1. These selectors do not define Java classes or routes. |
| 319 | Sets text size to 48px. It changes visual rendering, not database data or access control. |
| 320 | Sets upper width limit to 360px. It changes visual rendering, not database data or access control. |
| 321 | Sets text weight to 650. It changes visual rendering, not database data or access control. |
| 323 | Starts presentation rules for selector .auth-copy p. These selectors do not define Java classes or routes. |
| 324 | Sets foreground/text color to var(--hms-muted). It changes visual rendering, not database data or access control. |
| 325 | Sets upper width limit to 340px. It changes visual rendering, not database data or access control. |
| 326 | Sets margin-top presentation property to 22px. It changes visual rendering, not database data or access control. |
| 328 | Starts presentation rules for selector .auth-panel. These selectors do not define Java classes or routes. |
| 329 | Sets background appearance to #fff. It changes visual rendering, not database data or access control. |
| 330 | Sets border presentation property to 1px solid var(--hms-line). It changes visual rendering, not database data or access control. |
| 331 | Sets corner rounding to 12px. It changes visual rendering, not database data or access control. |
| 332 | Sets inside spacing to 32px. It changes visual rendering, not database data or access control. |
| 334 | Starts presentation rules for selector .auth-panel h2. These selectors do not define Java classes or routes. |
| 335 | Sets text size to 26px. It changes visual rendering, not database data or access control. |
| 336 | Sets text weight to 650. It changes visual rendering, not database data or access control. |
| 337 | Sets margin-bottom presentation property to 8px. It changes visual rendering, not database data or access control. |
| 339 | Starts presentation rules for selector .auth-panel .subtext. These selectors do not define Java classes or routes. |
| 340 | Sets text size to 14px. It changes visual rendering, not database data or access control. |
| 341 | Sets foreground/text color to var(--hms-muted). It changes visual rendering, not database data or access control. |
| 342 | Sets margin-bottom presentation property to 26px. It changes visual rendering, not database data or access control. |
| 344 | Starts presentation rules for selector .form-label. These selectors do not define Java classes or routes. |
| 345 | Sets text size to 13px. It changes visual rendering, not database data or access control. |
| 346 | Sets text weight to 650. It changes visual rendering, not database data or access control. |
| 347 | Sets margin-bottom presentation property to 6px. It changes visual rendering, not database data or access control. |
| 349 | Starts presentation rules for selector .form-control,.form-select. These selectors do not define Java classes or routes. |
| 350 | Sets corner rounding to 8px. It changes visual rendering, not database data or access control. |
| 351 | Sets border-color presentation property to #b5c9c3. It changes visual rendering, not database data or access control. |
| 352 | Sets min-height presentation property to 45px. It changes visual rendering, not database data or access control. |
| 353 | Sets text size to 15px. It changes visual rendering, not database data or access control. |
| 354 | Sets foreground/text color to var(--hms-ink). It changes visual rendering, not database data or access control. |
| 356 | Starts presentation rules for selector .form-control:focus,.form-select:focus. These selectors do not define Java classes or routes. |
| 357 | Sets border-color presentation property to var(--hms-accent). It changes visual rendering, not database data or access control. |
| 358 | Sets box-shadow presentation property to 0 0 0 3px #09675f22. It changes visual rendering, not database data or access control. |
| 360 | Starts presentation rules for selector .form-control::placeholder. These selectors do not define Java classes or routes. |
| 361 | Sets foreground/text color to #647b76. It changes visual rendering, not database data or access control. |
| 363 | Starts presentation rules for selector .form-note. These selectors do not define Java classes or routes. |
| 364 | Sets text size to 12px. It changes visual rendering, not database data or access control. |
| 365 | Sets foreground/text color to var(--hms-muted). It changes visual rendering, not database data or access control. |
| 366 | Sets margin-top presentation property to 7px. It changes visual rendering, not database data or access control. |
| 368 | Starts presentation rules for selector .auth-switch. These selectors do not define Java classes or routes. |
| 369 | Sets outside spacing to 22px 0 0. It changes visual rendering, not database data or access control. |
| 370 | Sets text size to 14px. It changes visual rendering, not database data or access control. |
| 372 | Starts presentation rules for selector .page-main. These selectors do not define Java classes or routes. |
| 373 | Sets background appearance to var(--hms-surface). It changes visual rendering, not database data or access control. |
| 374 | Sets min-height presentation property to 72vh. It changes visual rendering, not database data or access control. |
| 375 | Sets inside spacing to 40px 0 65px. It changes visual rendering, not database data or access control. |
| 377 | Starts presentation rules for selector .page-title. These selectors do not define Java classes or routes. |
| 378 | Sets text size to 38px. It changes visual rendering, not database data or access control. |
| 379 | Sets text weight to 650. It changes visual rendering, not database data or access control. |
| 380 | Sets margin-bottom presentation property to 10px. It changes visual rendering, not database data or access control. |
| 382 | Starts presentation rules for selector .page-intro. These selectors do not define Java classes or routes. |
| 383 | Sets foreground/text color to var(--hms-muted). It changes visual rendering, not database data or access control. |
| 384 | Sets upper width limit to 650px. It changes visual rendering, not database data or access control. |
| 386 | Starts presentation rules for selector .booking-layout. These selectors do not define Java classes or routes. |
| 387 | Sets layout model to grid. It changes visual rendering, not database data or access control. |
| 388 | Sets grid column structure to 1fr 300px. It changes visual rendering, not database data or access control. |
| 389 | Sets space between layout items to 28px. It changes visual rendering, not database data or access control. |
| 390 | Sets item alignment to start. It changes visual rendering, not database data or access control. |
| 391 | Sets margin-top presentation property to 30px. It changes visual rendering, not database data or access control. |
| 393 | Starts presentation rules for selector .form-panel,.info-panel,.appointments-panel. These selectors do not define Java classes or routes. |
| 394 | Sets background appearance to #fff. It changes visual rendering, not database data or access control. |
| 395 | Sets border presentation property to 1px solid var(--hms-line). It changes visual rendering, not database data or access control. |
| 396 | Sets corner rounding to 12px. It changes visual rendering, not database data or access control. |
| 397 | Sets inside spacing to 28px. It changes visual rendering, not database data or access control. |
| 399 | Starts presentation rules for selector .form-panel h2,.info-panel h2. These selectors do not define Java classes or routes. |
| 400 | Sets text size to 21px. It changes visual rendering, not database data or access control. |
| 401 | Sets text weight to 650. It changes visual rendering, not database data or access control. |
| 402 | Sets outside spacing to 0 0 23px. It changes visual rendering, not database data or access control. |
| 404 | Starts presentation rules for selector .info-panel. These selectors do not define Java classes or routes. |
| 405 | Sets background appearance to #e6f0ec. It changes visual rendering, not database data or access control. |
| 407 | Starts presentation rules for selector .info-panel p,.info-panel li. These selectors do not define Java classes or routes. |
| 408 | Sets text size to 14px. It changes visual rendering, not database data or access control. |
| 409 | Sets foreground/text color to var(--hms-muted). It changes visual rendering, not database data or access control. |
| 411 | Starts presentation rules for selector .info-panel ol. These selectors do not define Java classes or routes. |
| 412 | Sets padding-left presentation property to 20px. It changes visual rendering, not database data or access control. |
| 413 | Sets margin-bottom presentation property to 20px. It changes visual rendering, not database data or access control. |
| 415 | Starts presentation rules for selector .info-panel li. These selectors do not define Java classes or routes. |
| 416 | Sets margin-bottom presentation property to 12px. It changes visual rendering, not database data or access control. |
| 418 | Starts presentation rules for selector .booking-actions. These selectors do not define Java classes or routes. |
| 419 | Sets layout model to flex. It changes visual rendering, not database data or access control. |
| 420 | Sets item alignment to center. It changes visual rendering, not database data or access control. |
| 421 | Sets space between layout items to 20px. It changes visual rendering, not database data or access control. |
| 422 | Sets margin-top presentation property to 25px. It changes visual rendering, not database data or access control. |
| 424 | Starts presentation rules for selector .booking-actions p. These selectors do not define Java classes or routes. |
| 425 | Sets text size to 12px. It changes visual rendering, not database data or access control. |
| 426 | Sets foreground/text color to var(--hms-muted). It changes visual rendering, not database data or access control. |
| 427 | Sets outside spacing to 0. It changes visual rendering, not database data or access control. |
| 429 | Starts presentation rules for selector .appointments-heading. These selectors do not define Java classes or routes. |
| 430 | Sets layout model to flex. It changes visual rendering, not database data or access control. |
| 431 | Sets item distribution to space-between. It changes visual rendering, not database data or access control. |
| 432 | Sets item alignment to center. It changes visual rendering, not database data or access control. |
| 433 | Sets space between layout items to 20px. It changes visual rendering, not database data or access control. |
| 434 | Sets margin-bottom presentation property to 28px. It changes visual rendering, not database data or access control. |
| 436 | Starts presentation rules for selector .appointments-panel .table. These selectors do not define Java classes or routes. |
| 437 | Sets margin-bottom presentation property to 0. It changes visual rendering, not database data or access control. |
| 438 | Sets vertical-align presentation property to middle. It changes visual rendering, not database data or access control. |
| 439 | Sets text size to 14px. It changes visual rendering, not database data or access control. |
| 441 | Starts presentation rules for selector .appointments-panel th. These selectors do not define Java classes or routes. |
| 442 | Sets text size to 12px. It changes visual rendering, not database data or access control. |
| 443 | Sets foreground/text color to var(--hms-muted). It changes visual rendering, not database data or access control. |
| 444 | Sets text weight to 600. It changes visual rendering, not database data or access control. |
| 445 | Sets text wrapping to nowrap. It changes visual rendering, not database data or access control. |
| 446 | Sets inside spacing to 15px 12px. It changes visual rendering, not database data or access control. |
| 448 | Starts presentation rules for selector .appointments-panel td. These selectors do not define Java classes or routes. |
| 449 | Sets inside spacing to 18px 12px. It changes visual rendering, not database data or access control. |
| 450 | Sets upper width limit to 280px. It changes visual rendering, not database data or access control. |
| 451 | Sets overflow-wrap presentation property to anywhere. It changes visual rendering, not database data or access control. |
| 453 | Starts presentation rules for selector .status-pending. These selectors do not define Java classes or routes. |
| 454 | Sets background appearance to #f4ecce. It changes visual rendering, not database data or access control. |
| 455 | Sets foreground/text color to #685315. It changes visual rendering, not database data or access control. |
| 456 | Sets layout model to inline-block. It changes visual rendering, not database data or access control. |
| 457 | Sets inside spacing to 4px 10px. It changes visual rendering, not database data or access control. |
| 458 | Sets corner rounding to 6px. It changes visual rendering, not database data or access control. |
| 459 | Sets text size to 12px. It changes visual rendering, not database data or access control. |
| 460 | Sets text weight to 600. It changes visual rendering, not database data or access control. |
| 462 | Starts presentation rules for selector .empty-state. These selectors do not define Java classes or routes. |
| 463 | Sets text-align presentation property to center. It changes visual rendering, not database data or access control. |
| 464 | Sets inside spacing to 55px 15px. It changes visual rendering, not database data or access control. |
| 466 | Starts presentation rules for selector .empty-state>i. These selectors do not define Java classes or routes. |
| 467 | Sets text size to 35px. It changes visual rendering, not database data or access control. |
| 468 | Sets foreground/text color to var(--hms-accent). It changes visual rendering, not database data or access control. |
| 469 | Sets margin-bottom presentation property to 22px. It changes visual rendering, not database data or access control. |
| 471 | Starts presentation rules for selector .empty-state h2. These selectors do not define Java classes or routes. |
| 472 | Sets text size to 26px. It changes visual rendering, not database data or access control. |
| 474 | Starts presentation rules for selector .empty-state p. These selectors do not define Java classes or routes. |
| 475 | Sets foreground/text color to var(--hms-muted). It changes visual rendering, not database data or access control. |
| 476 | Sets outside spacing to 16px auto 25px. It changes visual rendering, not database data or access control. |
| 477 | Sets upper width limit to 390px. It changes visual rendering, not database data or access control. |
| 479 | Starts presentation rules for selector .paint-card. These selectors do not define Java classes or routes. |
| 480 | Sets box-shadow presentation property to none!important. It changes visual rendering, not database data or access control. |
| 481 | Sets border presentation property to 1px solid var(--hms-line). It changes visual rendering, not database data or access control. |
| 482 | Sets corner rounding to 12px. It changes visual rendering, not database data or access control. |
| 484 | Starts presentation rules for selector .alert. These selectors do not define Java classes or routes. |
| 485 | Sets text size to 14px. It changes visual rendering, not database data or access control. |
| 486 | Sets corner rounding to 8px. It changes visual rendering, not database data or access control. |
| 488 | Starts presentation rules for selector .table-responsive. These selectors do not define Java classes or routes. |
| 489 | Sets corner rounding to 8px. It changes visual rendering, not database data or access control. |
| 491 | Scopes the following styles to a responsive width or user preference. Reduced-motion rules avoid unnecessary animation. |
| 492 | Starts presentation rules for selector .hms-nav .navbar-collapse. These selectors do not define Java classes or routes. |
| 493 | Sets padding-top presentation property to 20px. It changes visual rendering, not database data or access control. |
| 495 | Starts presentation rules for selector .hms-nav .nav-item. These selectors do not define Java classes or routes. |
| 496 | Sets margin-bottom presentation property to 8px. It changes visual rendering, not database data or access control. |
| 498 | Starts presentation rules for selector .home-hero. These selectors do not define Java classes or routes. |
| 499 | Sets space between layout items to 30px. It changes visual rendering, not database data or access control. |
| 500 | Sets padding-top presentation property to 38px. It changes visual rendering, not database data or access control. |
| 502 | Starts presentation rules for selector .hero-photo. These selectors do not define Java classes or routes. |
| 503 | Sets height presentation property to 360px. It changes visual rendering, not database data or access control. |
| 505 | Starts presentation rules for selector .specialty-grid. These selectors do not define Java classes or routes. |
| 506 | Sets grid column structure to repeat(2,1fr). It changes visual rendering, not database data or access control. |
| 508 | Starts presentation rules for selector .auth-layout. These selectors do not define Java classes or routes. |
| 509 | Sets space between layout items to 35px. It changes visual rendering, not database data or access control. |
| 511 | Starts presentation rules for selector .booking-layout. These selectors do not define Java classes or routes. |
| 512 | Sets grid column structure to 1fr 240px. It changes visual rendering, not database data or access control. |
| 514 | Starts presentation rules for selector .footer-layout. These selectors do not define Java classes or routes. |
| 515 | Sets flex-direction presentation property to column. It changes visual rendering, not database data or access control. |
| 518 | Scopes the following styles to a responsive width or user preference. Reduced-motion rules avoid unnecessary animation. |
| 519 | Starts presentation rules for selector .container. These selectors do not define Java classes or routes. |
| 520 | Sets padding-left presentation property to 20px. It changes visual rendering, not database data or access control. |
| 521 | Sets padding-right presentation property to 20px. It changes visual rendering, not database data or access control. |
| 523 | Starts presentation rules for selector .home-hero. These selectors do not define Java classes or routes. |
| 524 | Sets grid column structure to 1fr. It changes visual rendering, not database data or access control. |
| 525 | Sets padding-bottom presentation property to 35px. It changes visual rendering, not database data or access control. |
| 526 | Sets space between layout items to 30px. It changes visual rendering, not database data or access control. |
| 528 | Starts presentation rules for selector .hero-copy h1. These selectors do not define Java classes or routes. |
| 529 | Sets text size to 53px. It changes visual rendering, not database data or access control. |
| 531 | Starts presentation rules for selector .hero-photo. These selectors do not define Java classes or routes. |
| 532 | Sets height presentation property to 270px. It changes visual rendering, not database data or access control. |
| 534 | Starts presentation rules for selector .hero-description. These selectors do not define Java classes or routes. |
| 535 | Sets text size to 17px. It changes visual rendering, not database data or access control. |
| 537 | Starts presentation rules for selector .journey. These selectors do not define Java classes or routes. |
| 538 | Sets grid column structure to 1fr. It changes visual rendering, not database data or access control. |
| 539 | Sets space between layout items to 23px. It changes visual rendering, not database data or access control. |
| 540 | Sets padding-top presentation property to 24px. It changes visual rendering, not database data or access control. |
| 541 | Sets padding-bottom presentation property to 24px. It changes visual rendering, not database data or access control. |
| 543 | Starts presentation rules for selector .care-section. These selectors do not define Java classes or routes. |
| 544 | Sets padding-top presentation property to 45px. It changes visual rendering, not database data or access control. |
| 545 | Sets padding-bottom presentation property to 35px. It changes visual rendering, not database data or access control. |
| 547 | Starts presentation rules for selector .section-heading h2. These selectors do not define Java classes or routes. |
| 548 | Sets text size to 30px. It changes visual rendering, not database data or access control. |
| 550 | Starts presentation rules for selector .specialty-grid. These selectors do not define Java classes or routes. |
| 551 | Sets space between layout items to 12px. It changes visual rendering, not database data or access control. |
| 553 | Starts presentation rules for selector .specialty-grid>a. These selectors do not define Java classes or routes. |
| 554 | Sets inside spacing to 22px 17px. It changes visual rendering, not database data or access control. |
| 556 | Starts presentation rules for selector .specialty-grid h3. These selectors do not define Java classes or routes. |
| 557 | Sets text size to 18px. It changes visual rendering, not database data or access control. |
| 559 | Starts presentation rules for selector .specialty-grid p. These selectors do not define Java classes or routes. |
| 560 | Sets text size to 13px. It changes visual rendering, not database data or access control. |
| 562 | Starts presentation rules for selector .portal-section. These selectors do not define Java classes or routes. |
| 563 | Sets flex-direction presentation property to column. It changes visual rendering, not database data or access control. |
| 564 | Sets item alignment to start. It changes visual rendering, not database data or access control. |
| 565 | Sets padding-top presentation property to 30px. It changes visual rendering, not database data or access control. |
| 567 | Starts presentation rules for selector .portal-section h2. These selectors do not define Java classes or routes. |
| 568 | Sets text size to 26px. It changes visual rendering, not database data or access control. |
| 570 | Starts presentation rules for selector .hms-footer nav. These selectors do not define Java classes or routes. |
| 571 | Sets flex-wrap presentation property to wrap. It changes visual rendering, not database data or access control. |
| 572 | Sets space between layout items to 18px. It changes visual rendering, not database data or access control. |
| 574 | Starts presentation rules for selector .auth-main. These selectors do not define Java classes or routes. |
| 575 | Sets inside spacing to 32px 20px 45px. It changes visual rendering, not database data or access control. |
| 577 | Starts presentation rules for selector .auth-layout. These selectors do not define Java classes or routes. |
| 578 | Sets grid column structure to 1fr. It changes visual rendering, not database data or access control. |
| 579 | Sets space between layout items to 20px. It changes visual rendering, not database data or access control. |
| 581 | Starts presentation rules for selector .auth-copy h1. These selectors do not define Java classes or routes. |
| 582 | Sets text size to 34px. It changes visual rendering, not database data or access control. |
| 583 | Sets upper width limit to none. It changes visual rendering, not database data or access control. |
| 585 | Starts presentation rules for selector .auth-copy p. These selectors do not define Java classes or routes. |
| 586 | Sets margin-top presentation property to 10px. It changes visual rendering, not database data or access control. |
| 587 | Sets upper width limit to none. It changes visual rendering, not database data or access control. |
| 589 | Starts presentation rules for selector .auth-panel. These selectors do not define Java classes or routes. |
| 590 | Sets inside spacing to 24px. It changes visual rendering, not database data or access control. |
| 592 | Starts presentation rules for selector .booking-layout. These selectors do not define Java classes or routes. |
| 593 | Sets grid column structure to 1fr. It changes visual rendering, not database data or access control. |
| 595 | Starts presentation rules for selector .booking-actions. These selectors do not define Java classes or routes. |
| 596 | Sets flex-direction presentation property to column. It changes visual rendering, not database data or access control. |
| 597 | Sets item alignment to start. It changes visual rendering, not database data or access control. |
| 599 | Starts presentation rules for selector .page-title. These selectors do not define Java classes or routes. |
| 600 | Sets text size to 32px. It changes visual rendering, not database data or access control. |
| 602 | Starts presentation rules for selector .appointments-heading. These selectors do not define Java classes or routes. |
| 603 | Sets flex-direction presentation property to column. It changes visual rendering, not database data or access control. |
| 604 | Sets item alignment to start. It changes visual rendering, not database data or access control. |
| 606 | Starts presentation rules for selector .form-panel,.appointments-panel. These selectors do not define Java classes or routes. |
| 607 | Sets inside spacing to 20px. It changes visual rendering, not database data or access control. |
| 609 | Starts presentation rules for selector .page-main. These selectors do not define Java classes or routes. |
| 610 | Sets padding-top presentation property to 30px. It changes visual rendering, not database data or access control. |
| 612 | Starts presentation rules for selector .info-panel. These selectors do not define Java classes or routes. |
| 613 | Sets order presentation property to -1. It changes visual rendering, not database data or access control. |
| 615 | Starts presentation rules for selector .info-panel ol. These selectors do not define Java classes or routes. |
| 616 | Sets layout model to none. It changes visual rendering, not database data or access control. |
| 619 | Scopes the following styles to a responsive width or user preference. Reduced-motion rules avoid unnecessary animation. |
| 620 | Starts presentation rules for selector html. These selectors do not define Java classes or routes. |
| 621 | Sets scroll motion to auto. It changes visual rendering, not database data or access control. |
| 623 | Starts presentation rules for selector *. These selectors do not define Java classes or routes. |
| 624 | Sets state-change animation to none!important. It changes visual rendering, not database data or access control. |
| 627 | Starts presentation rules for selector .demo-notice. These selectors do not define Java classes or routes. |
| 628 | Sets text-align presentation property to center. It changes visual rendering, not database data or access control. |
| 629 | Sets background appearance to var(--hms-surface). It changes visual rendering, not database data or access control. |
| 630 | Sets foreground/text color to var(--hms-muted). It changes visual rendering, not database data or access control. |
| 631 | Sets text size to 12px. It changes visual rendering, not database data or access control. |
| 632 | Sets inside spacing to 6px 20px. It changes visual rendering, not database data or access control. |
| 633 | Sets border-bottom presentation property to 1px solid var(--hms-line). It changes visual rendering, not database data or access control. |
| 635 | Starts presentation rules for selector .dashboard-grid. These selectors do not define Java classes or routes. |
| 636 | Sets layout model to grid. It changes visual rendering, not database data or access control. |
| 637 | Sets grid column structure to repeat(4,1fr). It changes visual rendering, not database data or access control. |
| 638 | Sets space between layout items to 20px. It changes visual rendering, not database data or access control. |
| 639 | Sets margin-top presentation property to 30px. It changes visual rendering, not database data or access control. |
| 641 | Starts presentation rules for selector .dashboard-grid>a,.dashboard-grid>div. These selectors do not define Java classes or routes. |
| 642 | Sets layout model to flex. It changes visual rendering, not database data or access control. |
| 643 | Sets flex-direction presentation property to column. It changes visual rendering, not database data or access control. |
| 644 | Sets inside spacing to 26px. It changes visual rendering, not database data or access control. |
| 645 | Sets background appearance to #fff. It changes visual rendering, not database data or access control. |
| 646 | Sets border presentation property to 1px solid var(--hms-line). It changes visual rendering, not database data or access control. |
| 647 | Sets corner rounding to 12px. It changes visual rendering, not database data or access control. |
| 648 | Sets foreground/text color to var(--hms-ink). It changes visual rendering, not database data or access control. |
| 649 | Sets text-decoration presentation property to none. It changes visual rendering, not database data or access control. |
| 651 | Starts presentation rules for selector .dashboard-grid>a:hover. These selectors do not define Java classes or routes. |
| 652 | Sets border-color presentation property to var(--hms-accent). It changes visual rendering, not database data or access control. |
| 654 | Starts presentation rules for selector .dashboard-grid span. These selectors do not define Java classes or routes. |
| 655 | Sets text size to 13px. It changes visual rendering, not database data or access control. |
| 656 | Sets foreground/text color to var(--hms-muted). It changes visual rendering, not database data or access control. |
| 658 | Starts presentation rules for selector .dashboard-grid strong. These selectors do not define Java classes or routes. |
| 659 | Sets text size to 43px. It changes visual rendering, not database data or access control. |
| 660 | Sets text weight to 650. It changes visual rendering, not database data or access control. |
| 661 | Sets line spacing to 1.5. It changes visual rendering, not database data or access control. |
| 662 | Sets letter-spacing presentation property to -.04em. It changes visual rendering, not database data or access control. |
| 664 | Starts presentation rules for selector .doctor-stats. These selectors do not define Java classes or routes. |
| 665 | Sets grid column structure to repeat(2,1fr). It changes visual rendering, not database data or access control. |
| 666 | Sets upper width limit to 760px. It changes visual rendering, not database data or access control. |
| 668 | Starts presentation rules for selector .specialty-tags. These selectors do not define Java classes or routes. |
| 669 | Sets layout model to flex. It changes visual rendering, not database data or access control. |
| 670 | Sets flex-wrap presentation property to wrap. It changes visual rendering, not database data or access control. |
| 671 | Sets space between layout items to 10px. It changes visual rendering, not database data or access control. |
| 672 | Sets margin-bottom presentation property to 25px. It changes visual rendering, not database data or access control. |
| 674 | Starts presentation rules for selector .specialty-tags span. These selectors do not define Java classes or routes. |
| 675 | Sets background appearance to var(--hms-surface). It changes visual rendering, not database data or access control. |
| 676 | Sets inside spacing to 6px 12px. It changes visual rendering, not database data or access control. |
| 677 | Sets corner rounding to 6px. It changes visual rendering, not database data or access control. |
| 678 | Sets text size to 13px. It changes visual rendering, not database data or access control. |
| 680 | Starts presentation rules for selector .specialty-form. These selectors do not define Java classes or routes. |
| 681 | Sets layout model to flex. It changes visual rendering, not database data or access control. |
| 682 | Sets space between layout items to 15px. It changes visual rendering, not database data or access control. |
| 683 | Sets item alignment to end. It changes visual rendering, not database data or access control. |
| 685 | Starts presentation rules for selector .specialty-form>div. These selectors do not define Java classes or routes. |
| 686 | Sets width presentation property to 320px. It changes visual rendering, not database data or access control. |
| 687 | Sets upper width limit to 100%. It changes visual rendering, not database data or access control. |
| 689 | Scopes the following styles to a responsive width or user preference. Reduced-motion rules avoid unnecessary animation. |
| 690 | Starts presentation rules for selector .dashboard-grid. These selectors do not define Java classes or routes. |
| 691 | Sets grid column structure to repeat(2,1fr). It changes visual rendering, not database data or access control. |
| 692 | Sets space between layout items to 12px. It changes visual rendering, not database data or access control. |
| 694 | Starts presentation rules for selector .dashboard-grid>a,.dashboard-grid>div. These selectors do not define Java classes or routes. |
| 695 | Sets inside spacing to 18px. It changes visual rendering, not database data or access control. |
| 697 | Starts presentation rules for selector .specialty-form. These selectors do not define Java classes or routes. |
| 698 | Sets flex-direction presentation property to column. It changes visual rendering, not database data or access control. |
| 699 | Sets item alignment to start. It changes visual rendering, not database data or access control. |
| 701 | Starts presentation rules for selector .specialty-form>div. These selectors do not define Java classes or routes. |
| 702 | Sets width presentation property to 100%. It changes visual rendering, not database data or access control. |
| 706 | Sets .preserve-lines { white-space presentation property to pre-wrap; overflow-wrap: anywhere; }. It changes visual rendering, not database data or access control. |

<a id="file-97"></a>

## 97. src/main/webapp/component/style.css

Presentation stylesheet. app.css is linked by the shared current asset include; older style.css rules matter only on pages that link it.

Source SHA-256: `db554eb0ede0b3d917f692a381281afea3e0bba5d1ad63e030412300e9ec9c1b`

<details>
<summary>Numbered source (206 physical lines)</summary>

~~~text
   1 |
   2 |
   3 | body {
   4 |     font-family: Arial, sans-serif;
   5 |     margin: 0;
   6 |     padding: 0;
   7 |     box-sizing: border-box;
   8 |     color: #333;
   9 |
  10 |
  11 |
  12 | }
  13 |
  14 |
  15 | /* Hero Section */
  16 | .hero {
  17 |
  18 |    background-color: #efeee9;
  19 |     background-size: cover;
  20 |     background-position: center;
  21 |     color: rgba(0,0,0,0.8);
  22 |     text-align: center;
  23 |     padding: 110px 20px;
  24 | }
  25 |
  26 | .hero-content h1 {
  27 |     font-size: 48px;
  28 |     margin-bottom: 20px;
  29 | }
  30 |
  31 | .hero-content p {
  32 |     font-size: 24px;
  33 |     margin-bottom: 40px;
  34 | }
  35 |
  36 | .btn-primary {
  37 |     background-color: #3a8cef;
  38 |     color: white;
  39 |     padding: 15px 30px;
  40 |     font-size: 18px;
  41 |     border: none;
  42 |     border-radius: 5px;
  43 |     cursor: pointer;
  44 |
  45 | }
  46 |
  47 | .btn-primary:hover {
  48 |     background-color: #0056b3;
  49 | }
  50 |
  51 | /* Services Section */
  52 | .services {
  53 |     padding: 60px 20px;
  54 |     text-align: center;
  55 |
  56 |      background-color: #eaeaea ;
  57 | }
  58 |
  59 | .services h2 {
  60 |     font-size: 36px;
  61 |     margin-bottom: 40px;
  62 | }
  63 |
  64 | .service-cards {
  65 |     display: flex;
  66 |     justify-content: space-around;
  67 |     flex-wrap: wrap;
  68 | }
  69 |
  70 | .card {
  71 |      background-color: #f8f9fa;
  72 |     border: 1px solid #ddd;
  73 |     padding: 20px;
  74 |     width: 300px;
  75 |     margin: 20px;
  76 |     box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  77 |     border-radius: 5px;
  78 |     transition: transform 0.2s;
  79 | }
  80 |
  81 | .card:hover {
  82 |     transform: translateY(-10px);
  83 | }
  84 |
  85 |
  86 |
  87 | /* Footer Styles */
  88 | footer {
  89 |     background-color: #333;
  90 |     color: white;
  91 |     padding: 40px 20px;
  92 |     text-align: left;
  93 | }
  94 |
  95 | .footer-container {
  96 |     display: flex;
  97 |     justify-content: space-between;
  98 |     flex-wrap: wrap;
  99 | }
 100 |
 101 | .footer-section {
 102 |     flex: 1;
 103 |     margin: 10px;
 104 |     min-width: 250px;
 105 | }
 106 |
 107 | .footer-section h3 {
 108 |     font-size: 18px;
 109 |     margin-bottom: 20px;
 110 | }
 111 |
 112 | .footer-section p, .footer-section ul, .footer-section a {
 113 |     font-size: 14px;
 114 |     line-height: 1.8;
 115 |     color: #bbb;
 116 | }
 117 |
 118 | .footer-section ul {
 119 |     list-style-type: none;
 120 |     padding: 0;
 121 | }
 122 |
 123 | .footer-section ul li {
 124 |     margin-bottom: 10px;
 125 | }
 126 |
 127 | .footer-section ul li a {
 128 |     color: #bbb;
 129 |     text-decoration: none;
 130 | }
 131 |
 132 | .footer-section ul li a:hover {
 133 |     color: #fff;
 134 | }
 135 |
 136 | .social-icons a {
 137 |     margin-right: 15px;
 138 | }
 139 |
 140 | .social-icons img {
 141 |     width: 24px;
 142 |     height: 24px;
 143 | }
 144 |
 145 | .footer-bottom {
 146 |     text-align: center;
 147 |     margin-top: 30px;
 148 |     font-size: 14px;
 149 |     border-top: 1px solid #444;
 150 |     padding-top: 20px;
 151 |     color: #bbb;
 152 | }
 153 |
 154 | .serv{
 155 |
 156 | 	   background-color:#efeee9 ;
 157 |     background-size: cover;
 158 |     background-position: center;
 159 |     color: rgba(0,0,0,0.8);
 160 |     text-align: center;
 161 |     padding: 25px 20px;
 162 |
 163 |     display: flex;
 164 |     justify-content: space-between;
 165 |     flex-direction: column;
 166 | }
 167 | .serv-cards{
 168 |
 169 | 	 display: flex;
 170 |     justify-content: space-around;
 171 |     flex-direction: row;
 172 |     text-align: center;
 173 |     padding: 25px 20px;
 174 |
 175 |     flex-wrap: wrap;
 176 |
 177 | }
 178 | .cards{
 179 |
 180 | 	 background-color: white;
 181 |     border: 1px solid #ddd;
 182 |     padding: 10px;
 183 |     width: 450px;
 184 |     margin: 10px;
 185 |     box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
 186 |     border-radius: 5px;
 187 |     transition: transform 0.2s;
 188 |
 189 | }
 190 |
 191 | .cards:hover {
 192 |     transform: translateY(-10px);
 193 | }
 194 |
 195 | .d-img{
 196 |
 197 | 	 padding: 15px 5px;
 198 | 	 background-color: white;
 199 |
 200 |
 201 |
 202 | }
 203 |
 204 |
 205 |
 206 |
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 3 | Starts presentation rules for selector body. These selectors do not define Java classes or routes. |
| 4 | Sets typeface fallback list to Arial, sans-serif. It changes visual rendering, not database data or access control. |
| 5 | Sets outside spacing to 0. It changes visual rendering, not database data or access control. |
| 6 | Sets inside spacing to 0. It changes visual rendering, not database data or access control. |
| 7 | Sets box-sizing presentation property to border-box. It changes visual rendering, not database data or access control. |
| 8 | Sets foreground/text color to #333. It changes visual rendering, not database data or access control. |
| 15 | Stylesheet comment documenting the visual design. |
| 16 | Starts presentation rules for selector .hero. These selectors do not define Java classes or routes. |
| 18 | Sets background-color presentation property to #efeee9. It changes visual rendering, not database data or access control. |
| 19 | Sets background-size presentation property to cover. It changes visual rendering, not database data or access control. |
| 20 | Sets background-position presentation property to center. It changes visual rendering, not database data or access control. |
| 21 | Sets foreground/text color to rgba(0,0,0,0.8). It changes visual rendering, not database data or access control. |
| 22 | Sets text-align presentation property to center. It changes visual rendering, not database data or access control. |
| 23 | Sets inside spacing to 110px 20px. It changes visual rendering, not database data or access control. |
| 26 | Starts presentation rules for selector .hero-content h1. These selectors do not define Java classes or routes. |
| 27 | Sets text size to 48px. It changes visual rendering, not database data or access control. |
| 28 | Sets margin-bottom presentation property to 20px. It changes visual rendering, not database data or access control. |
| 31 | Starts presentation rules for selector .hero-content p. These selectors do not define Java classes or routes. |
| 32 | Sets text size to 24px. It changes visual rendering, not database data or access control. |
| 33 | Sets margin-bottom presentation property to 40px. It changes visual rendering, not database data or access control. |
| 36 | Starts presentation rules for selector .btn-primary. These selectors do not define Java classes or routes. |
| 37 | Sets background-color presentation property to #3a8cef. It changes visual rendering, not database data or access control. |
| 38 | Sets foreground/text color to white. It changes visual rendering, not database data or access control. |
| 39 | Sets inside spacing to 15px 30px. It changes visual rendering, not database data or access control. |
| 40 | Sets text size to 18px. It changes visual rendering, not database data or access control. |
| 41 | Sets border presentation property to none. It changes visual rendering, not database data or access control. |
| 42 | Sets corner rounding to 5px. It changes visual rendering, not database data or access control. |
| 43 | Sets cursor presentation property to pointer. It changes visual rendering, not database data or access control. |
| 47 | Starts presentation rules for selector .btn-primary:hover. These selectors do not define Java classes or routes. |
| 48 | Sets background-color presentation property to #0056b3. It changes visual rendering, not database data or access control. |
| 51 | Stylesheet comment documenting the visual design. |
| 52 | Starts presentation rules for selector .services. These selectors do not define Java classes or routes. |
| 53 | Sets inside spacing to 60px 20px. It changes visual rendering, not database data or access control. |
| 54 | Sets text-align presentation property to center. It changes visual rendering, not database data or access control. |
| 56 | Sets background-color presentation property to #eaeaea . It changes visual rendering, not database data or access control. |
| 59 | Starts presentation rules for selector .services h2. These selectors do not define Java classes or routes. |
| 60 | Sets text size to 36px. It changes visual rendering, not database data or access control. |
| 61 | Sets margin-bottom presentation property to 40px. It changes visual rendering, not database data or access control. |
| 64 | Starts presentation rules for selector .service-cards. These selectors do not define Java classes or routes. |
| 65 | Sets layout model to flex. It changes visual rendering, not database data or access control. |
| 66 | Sets item distribution to space-around. It changes visual rendering, not database data or access control. |
| 67 | Sets flex-wrap presentation property to wrap. It changes visual rendering, not database data or access control. |
| 70 | Starts presentation rules for selector .card. These selectors do not define Java classes or routes. |
| 71 | Sets background-color presentation property to #f8f9fa. It changes visual rendering, not database data or access control. |
| 72 | Sets border presentation property to 1px solid #ddd. It changes visual rendering, not database data or access control. |
| 73 | Sets inside spacing to 20px. It changes visual rendering, not database data or access control. |
| 74 | Sets width presentation property to 300px. It changes visual rendering, not database data or access control. |
| 75 | Sets outside spacing to 20px. It changes visual rendering, not database data or access control. |
| 76 | Sets box-shadow presentation property to 0 4px 8px rgba(0, 0, 0, 0.1). It changes visual rendering, not database data or access control. |
| 77 | Sets corner rounding to 5px. It changes visual rendering, not database data or access control. |
| 78 | Sets state-change animation to transform 0.2s. It changes visual rendering, not database data or access control. |
| 81 | Starts presentation rules for selector .card:hover. These selectors do not define Java classes or routes. |
| 82 | Sets transform presentation property to translateY(-10px). It changes visual rendering, not database data or access control. |
| 87 | Stylesheet comment documenting the visual design. |
| 88 | Starts presentation rules for selector footer. These selectors do not define Java classes or routes. |
| 89 | Sets background-color presentation property to #333. It changes visual rendering, not database data or access control. |
| 90 | Sets foreground/text color to white. It changes visual rendering, not database data or access control. |
| 91 | Sets inside spacing to 40px 20px. It changes visual rendering, not database data or access control. |
| 92 | Sets text-align presentation property to left. It changes visual rendering, not database data or access control. |
| 95 | Starts presentation rules for selector .footer-container. These selectors do not define Java classes or routes. |
| 96 | Sets layout model to flex. It changes visual rendering, not database data or access control. |
| 97 | Sets item distribution to space-between. It changes visual rendering, not database data or access control. |
| 98 | Sets flex-wrap presentation property to wrap. It changes visual rendering, not database data or access control. |
| 101 | Starts presentation rules for selector .footer-section. These selectors do not define Java classes or routes. |
| 102 | Sets flex presentation property to 1. It changes visual rendering, not database data or access control. |
| 103 | Sets outside spacing to 10px. It changes visual rendering, not database data or access control. |
| 104 | Sets min-width presentation property to 250px. It changes visual rendering, not database data or access control. |
| 107 | Starts presentation rules for selector .footer-section h3. These selectors do not define Java classes or routes. |
| 108 | Sets text size to 18px. It changes visual rendering, not database data or access control. |
| 109 | Sets margin-bottom presentation property to 20px. It changes visual rendering, not database data or access control. |
| 112 | Starts presentation rules for selector .footer-section p, .footer-section ul, .footer-section a. These selectors do not define Java classes or routes. |
| 113 | Sets text size to 14px. It changes visual rendering, not database data or access control. |
| 114 | Sets line spacing to 1.8. It changes visual rendering, not database data or access control. |
| 115 | Sets foreground/text color to #bbb. It changes visual rendering, not database data or access control. |
| 118 | Starts presentation rules for selector .footer-section ul. These selectors do not define Java classes or routes. |
| 119 | Sets list-style-type presentation property to none. It changes visual rendering, not database data or access control. |
| 120 | Sets inside spacing to 0. It changes visual rendering, not database data or access control. |
| 123 | Starts presentation rules for selector .footer-section ul li. These selectors do not define Java classes or routes. |
| 124 | Sets margin-bottom presentation property to 10px. It changes visual rendering, not database data or access control. |
| 127 | Starts presentation rules for selector .footer-section ul li a. These selectors do not define Java classes or routes. |
| 128 | Sets foreground/text color to #bbb. It changes visual rendering, not database data or access control. |
| 129 | Sets text-decoration presentation property to none. It changes visual rendering, not database data or access control. |
| 132 | Starts presentation rules for selector .footer-section ul li a:hover. These selectors do not define Java classes or routes. |
| 133 | Sets foreground/text color to #fff. It changes visual rendering, not database data or access control. |
| 136 | Starts presentation rules for selector .social-icons a. These selectors do not define Java classes or routes. |
| 137 | Sets margin-right presentation property to 15px. It changes visual rendering, not database data or access control. |
| 140 | Starts presentation rules for selector .social-icons img. These selectors do not define Java classes or routes. |
| 141 | Sets width presentation property to 24px. It changes visual rendering, not database data or access control. |
| 142 | Sets height presentation property to 24px. It changes visual rendering, not database data or access control. |
| 145 | Starts presentation rules for selector .footer-bottom. These selectors do not define Java classes or routes. |
| 146 | Sets text-align presentation property to center. It changes visual rendering, not database data or access control. |
| 147 | Sets margin-top presentation property to 30px. It changes visual rendering, not database data or access control. |
| 148 | Sets text size to 14px. It changes visual rendering, not database data or access control. |
| 149 | Sets border-top presentation property to 1px solid #444. It changes visual rendering, not database data or access control. |
| 150 | Sets padding-top presentation property to 20px. It changes visual rendering, not database data or access control. |
| 151 | Sets foreground/text color to #bbb. It changes visual rendering, not database data or access control. |
| 154 | Starts presentation rules for selector .serv. These selectors do not define Java classes or routes. |
| 156 | Sets background-color presentation property to #efeee9 . It changes visual rendering, not database data or access control. |
| 157 | Sets background-size presentation property to cover. It changes visual rendering, not database data or access control. |
| 158 | Sets background-position presentation property to center. It changes visual rendering, not database data or access control. |
| 159 | Sets foreground/text color to rgba(0,0,0,0.8). It changes visual rendering, not database data or access control. |
| 160 | Sets text-align presentation property to center. It changes visual rendering, not database data or access control. |
| 161 | Sets inside spacing to 25px 20px. It changes visual rendering, not database data or access control. |
| 163 | Sets layout model to flex. It changes visual rendering, not database data or access control. |
| 164 | Sets item distribution to space-between. It changes visual rendering, not database data or access control. |
| 165 | Sets flex-direction presentation property to column. It changes visual rendering, not database data or access control. |
| 167 | Starts presentation rules for selector .serv-cards. These selectors do not define Java classes or routes. |
| 169 | Sets layout model to flex. It changes visual rendering, not database data or access control. |
| 170 | Sets item distribution to space-around. It changes visual rendering, not database data or access control. |
| 171 | Sets flex-direction presentation property to row. It changes visual rendering, not database data or access control. |
| 172 | Sets text-align presentation property to center. It changes visual rendering, not database data or access control. |
| 173 | Sets inside spacing to 25px 20px. It changes visual rendering, not database data or access control. |
| 175 | Sets flex-wrap presentation property to wrap. It changes visual rendering, not database data or access control. |
| 178 | Starts presentation rules for selector .cards. These selectors do not define Java classes or routes. |
| 180 | Sets background-color presentation property to white. It changes visual rendering, not database data or access control. |
| 181 | Sets border presentation property to 1px solid #ddd. It changes visual rendering, not database data or access control. |
| 182 | Sets inside spacing to 10px. It changes visual rendering, not database data or access control. |
| 183 | Sets width presentation property to 450px. It changes visual rendering, not database data or access control. |
| 184 | Sets outside spacing to 10px. It changes visual rendering, not database data or access control. |
| 185 | Sets box-shadow presentation property to 0 4px 8px rgba(0, 0, 0, 0.1). It changes visual rendering, not database data or access control. |
| 186 | Sets corner rounding to 5px. It changes visual rendering, not database data or access control. |
| 187 | Sets state-change animation to transform 0.2s. It changes visual rendering, not database data or access control. |
| 191 | Starts presentation rules for selector .cards:hover. These selectors do not define Java classes or routes. |
| 192 | Sets transform presentation property to translateY(-10px). It changes visual rendering, not database data or access control. |
| 195 | Starts presentation rules for selector .d-img. These selectors do not define Java classes or routes. |
| 197 | Sets inside spacing to 15px 5px. It changes visual rendering, not database data or access control. |
| 198 | Sets background-color presentation property to white. It changes visual rendering, not database data or access control. |

<a id="file-98"></a>

## 98. scripts/smoke_test.py

Standard-library HTTP tests using independent cookie sessions and synthetic local-demo data.

Source SHA-256: `10d098cf3665ba05b3a95e66b0b08eeada2a05a4ef3eab78e7008c2e1cbd64fa`

<details>
<summary>Numbered source (192 physical lines)</summary>

~~~text
   1 | #!/usr/bin/env python3
   2 | """Exercise the local demo with synthetic data. Requires only Python's standard library."""
   3 | import datetime
   4 | import http.cookiejar
   5 | import re
   6 | import sys
   7 | import urllib.error
   8 | import urllib.parse
   9 | import urllib.request
  10 | import uuid
  11 |
  12 | BASE = sys.argv[1] if __name__ == "__main__" and len(sys.argv) > 1 else "http://localhost:8080"
  13 | assert urllib.parse.urlparse(BASE).hostname in ("localhost", "127.0.0.1"), "Run against the local demo only."
  14 |
  15 |
  16 | class Session:
  17 |     def __init__(self):
  18 |         self.client = urllib.request.build_opener(urllib.request.HTTPCookieProcessor(http.cookiejar.CookieJar()))
  19 |         self.token = None
  20 |
  21 |     def request(self, path, data=None, csrf=True):
  22 |         if data is not None:
  23 |             data = dict(data)
  24 |             if csrf:
  25 |                 data["csrfToken"] = self.token or ""
  26 |             data = urllib.parse.urlencode(data).encode()
  27 |         try:
  28 |             response = self.client.open(BASE + path, data=data, timeout=15)
  29 |         except urllib.error.HTTPError as error:
  30 |             response = error
  31 |         text = response.read().decode()
  32 |         token = re.search(r'name="csrfToken" value="([^"]+)"', text)
  33 |         if token:
  34 |             self.token = token.group(1)
  35 |         return response.status, response.url, text
  36 |
  37 |
  38 | checks = 0
  39 |
  40 |
  41 | def check(condition, label):
  42 |     global checks
  43 |     assert condition, label
  44 |     checks += 1
  45 |     print("PASS", label)
  46 |
  47 |
  48 | def login(session, role, email, password):
  49 |     page, action = {"patient": ("user_Login.jsp", "userLogin"), "doctor": ("doctor.jsp", "loginDoctor"), "admin": ("admin.jsp", "adminLogin")}[role]
  50 |     session.request("/" + page)
  51 |     return session.request("/" + action, {"email": email, "password": password})
  52 |
  53 |
  54 | def register(name):
  55 |     session = Session()
  56 |     session.request("/signup.jsp")
  57 |     email = "test-" + uuid.uuid4().hex + "@hospital.test"
  58 |     status, url, text = session.request("/register", {"Fullname": name, "email": email, "password": "Patient@123"})
  59 |     check(status == 200 and url.endswith("user_Login.jsp") and "account is ready" in text, "patient registration")
  60 |     status, url, text = login(session, "patient", email, "Patient@123")
  61 |     check(status == 200 and url.endswith("view_appointment.jsp"), "patient login")
  62 |     return session, email
  63 |
  64 |
  65 |
  66 | def main():
  67 |     # Run only against a local demo. These synthetic records remain for inspection.
  68 |     guest = Session()
  69 |     check("Local project demo" in guest.request("/")[2], "target is the local sample-data demo")
  70 |     for path in ("/", "/signup.jsp", "/user_Login.jsp", "/doctor.jsp", "/admin.jsp", "/component/app.css", "/health/live", "/health/ready"):
  71 |         check(guest.request(path)[0] == 200, "public page " + path)
  72 |     for path, destination in (("/user_appointment.jsp","user_Login.jsp"),("/admin/patient.jsp","admin.jsp"),("/doctor/patient.jsp","doctor.jsp")):
  73 |         check(guest.request(path)[1].endswith(destination), "protect "+path)
  74 |     check(guest.request("/api/slots")[0] == 401, "API requires authentication")
  75 |     check(guest.request("/unknown")[0] == 404, "unlisted routes are denied")
  76 |     check(guest.request("/register",{"email":"test@hospital.test"},csrf=False)[0] == 403, "reject missing CSRF")
  77 |     patient,email = register('<script>alert("test")</script>')
  78 |     check("&lt;script&gt;" in patient.request("/view_appointment.jsp")[2], "escape account names")
  79 |     check(patient.request("/admin/index.jsp")[1].endswith("admin.jsp"), "patient cannot access admin")
  80 |     text = patient.request("/user_appointment.jsp")[2]
  81 |     slot_markup = text.split('<select id="slot"',1)[1].split('</select>',1)[0]
  82 |     match = re.search(r'<option value="(\d+)">([^<]*Aditi[^<]*)</option>', slot_markup)
  83 |     check(match is not None, "booking page contains real published slots")
  84 |     slot = match.group(1)
  85 |     key = re.search(r'name="requestKey" value="([^"]+)"',text).group(1)
  86 |     reason = "Synthetic visit "+uuid.uuid4().hex[:10]
  87 |     booking = {"slotId":slot,"requestKey":key,"userid":"999999","fullname":"Synthetic Patient","gender":"other","age":"24","email":email,"phno":"9000000000","diseases":reason,"address":"Synthetic address"}
  88 |     bad=dict(booking,age="999")
  89 |     check(patient.request("/appAppointment",bad)[0] == 400, "server rejects invalid age")
  90 |     status,url,text = patient.request("/appAppointment",booking)
  91 |     check(status == 200 and reason in text and "PENDING" in text, "booking reserves a slot for session account")
  92 |     appointment_id = urllib.parse.parse_qs(urllib.parse.urlparse(url).query)["id"][0]
  93 |     check(patient.request("/appAppointment",booking)[1].endswith("id="+appointment_id), "repeated request key returns same appointment")
  94 |     check(patient.request("/appAppointment",dict(booking,fullname="Changed payload"))[0] == 409, "request key cannot identify different payload")
  95 |     other,_ = register("Another Synthetic Patient")
  96 |     check(other.request("/appointment.jsp?id="+appointment_id)[0] == 404, "patient cannot read another appointment")
  97 |     check(other.request("/api/appointments/"+appointment_id)[0] == 404, "API applies same object ownership")
  98 |     check(other.request("/appAppointment",dict(booking,requestKey=uuid.uuid4().hex))[0] == 409, "occupied slot returns conflict")
  99 |     check(patient.request("/appointmentState",{"id":appointment_id,"status":"CONFIRMED"})[0] == 403, "patient cannot confirm own appointment")
 100 |     doctor=Session()
 101 |     check(login(doctor,"doctor","aditi@hospital.test","Doctor@123")[1].endswith("doctor/index.jsp"), "doctor login")
 102 |     check(doctor.request("/doctor/comment.jsp?id="+appointment_id)[0] == 200, "assigned doctor can read consultation")
 103 |     unassigned=Session()
 104 |     login(unassigned,"doctor","arjun@hospital.test","Doctor@123")
 105 |     check(unassigned.request("/doctor/comment.jsp?id="+appointment_id)[0] == 404, "other doctor cannot read consultation")
 106 |     check(unassigned.request("/updateStatus",{"id":appointment_id,"did":"1","comm":"Unauthorized note"})[0] == 404, "forged doctor ID cannot write note")
 107 |     check(doctor.request("/appointmentState",{"id":appointment_id,"status":"CONFIRMED"})[0] == 200, "assigned doctor confirms appointment")
 108 |     check(doctor.request("/appointmentState",{"id":appointment_id,"status":"COMPLETED"})[0] == 409, "future appointment cannot complete early")
 109 |     doctor.request("/updateStatus",{"id":appointment_id,"comm":"First synthetic note <script>test</script>"})
 110 |     doctor.request("/updateStatus",{"id":appointment_id,"comm":"Second synthetic note"})
 111 |     text=patient.request("/appointment.jsp?id="+appointment_id)[2]
 112 |     check("First synthetic note &lt;script&gt;test&lt;/script&gt;" in text and "Second synthetic note" in text, "append-only escaped consultation history")
 113 |     check("CONFIRMED" in text, "notes do not overwrite status")
 114 |     check("NOTE_ADDED" in text and "STATUS_CHANGED" in text, "auditable activity history")
 115 |     check(patient.request("/appointmentState",{"id":appointment_id,"status":"CANCELLED"})[0] == 200, "patient can cancel future appointment")
 116 |     check(doctor.request("/updateStatus",{"id":appointment_id,"comm":"Late note"})[0] == 409, "cancelled record rejects new notes")
 117 |     status,url,text=other.request("/appAppointment",dict(booking,requestKey=uuid.uuid4().hex))
 118 |     check(status == 200 and "PENDING" in text, "cancellation releases slot for rebooking")
 119 |     other_id=urllib.parse.parse_qs(urllib.parse.urlparse(url).query)["id"][0]
 120 |     check(patient.request("/appointment.jsp?id="+appointment_id)[0] == 200, "cancelled history remains accessible")
 121 |
 122 |     # Complete HTTP concurrency test: separate sessions, the same slot, synchronized submissions.
 123 |     from concurrent.futures import ThreadPoolExecutor
 124 |     from threading import Barrier
 125 |     a,ae=register("Race A")
 126 |     b,be=register("Race B")
 127 |     slots=__import__("json").loads(a.request("/api/slots")[2])["slots"]
 128 |     race_slot=str(slots[0]["id"])
 129 |     barrier=Barrier(2)
 130 |     def race(session,email):
 131 |         form=dict(booking,slotId=race_slot,requestKey=uuid.uuid4().hex,email=email)
 132 |         barrier.wait()
 133 |         return session.request("/appAppointment",form)[0]
 134 |     with ThreadPoolExecutor(max_workers=2) as pool:
 135 |         futures=[pool.submit(race,a,ae),pool.submit(race,b,be)]
 136 |         check(sorted(f.result() for f in futures)==[200,409], "simultaneous HTTP bookings have exactly one winner")
 137 |
 138 |     admin=Session()
 139 |     import os
 140 |     check(login(admin,"admin","admin@hospital.test",os.environ.get("HMS_TEST_ADMIN_PASSWORD","Admin@123"))[1].endswith("admin/index.jsp"), "admin login")
 141 |     for path in ("/admin/doctor.jsp","/admin/view_doctor.jsp","/admin/edit_doctor.jsp?id=1","/admin/patient.jsp","/admin/slots.jsp","/doctor/edit_profile.jsp","/doctor/slots.jsp"):
 142 |         session=doctor if path.startswith("/doctor") else admin
 143 |         check(session.request(path)[0]==200, "render "+path)
 144 |     check(admin.request("/admin/metrics")[0]==200, "protected operational metrics")
 145 |     check("hms_http_requests_total" in admin.request("/admin/metrics")[2], "metrics include request counts")
 146 |     check(patient.request("/publishSlot",{"doctorId":"1","startsAt":"2030-01-01T10:00"})[0]==403, "patients cannot publish slots")
 147 |     check(admin.request("/delete_doctor",{"id":"1"})[0]==409, "doctor with open appointments cannot be archived")
 148 |     check(admin.request("/delete_doctor?id=1")[0]==405, "GET cannot archive doctors")
 149 |
 150 |     # Staff availability, soft archiving, and doctor credential/session checks.
 151 |     name="SyntheticDoctor"+uuid.uuid4().hex[:8]
 152 |     doctor_email=uuid.uuid4().hex+"@doctor.test"
 153 |     profile={"fullname":name,"dob":"1980-01-01","qualification":"MD","specialist":"General Medicine","email":doctor_email,"mobno":"9000000000","password":"Temporary@123"}
 154 |     status,url,text=admin.request("/addDoctor",profile)
 155 |     check(status==200 and name in text,"admin creates a validated doctor")
 156 |     temp_id=re.search(r'<tr><td>'+name+r'</td>.*?edit_doctor.jsp\?id=(\d+)',text,re.S).group(1)
 157 |     temp=Session()
 158 |     check(login(temp,"doctor",doctor_email,"Temporary@123")[1].endswith("doctor/index.jsp"),"new doctor can authenticate")
 159 |     starts=(datetime.date.today()+datetime.timedelta(days=2)).isoformat()+"T10:30"
 160 |     check(temp.request("/publishSlot",{"doctorId":"1","startsAt":starts})[0]==200,"doctor publishes own half-hour slot despite forged doctor field")
 161 |     available=__import__("json").loads(temp.request("/api/slots?doctorId="+temp_id)[2])["slots"]
 162 |     temp_slot=str(next(x["id"] for x in available if x["startsAt"].startswith(starts)))
 163 |     check(unassigned.request("/withdrawSlot",{"slotId":temp_slot})[0]==404,"doctor cannot withdraw another doctor's slot")
 164 |     check(temp.request("/withdrawSlot",{"slotId":temp_slot})[0]==200,"doctor withdraws an unreserved slot")
 165 |     check(temp.request("/publishSlot",{"startsAt":starts[:-2]+"15"})[0]==400,"slot start must align to half-hour boundary")
 166 |     check(temp.request("/docChangePassword",{"uid":"1","oldPassword":"Temporary@123","newPassword":"TemporaryNew@123"})[1].endswith("doctor.jsp"),"doctor password change signs out current session")
 167 |     check(login(temp,"doctor",doctor_email,"TemporaryNew@123")[1].endswith("doctor/index.jsp"),"new doctor password works")
 168 |     status,url,text=admin.request("/delete_doctor",{"id":temp_id})
 169 |     check(status==200 and name in text and "Archived" in text,"archiving retains the doctor record")
 170 |     check(temp.request("/doctor/index.jsp")[0]==401,"archiving revokes an active doctor session")
 171 |     check(login(Session(),"doctor",doctor_email,"TemporaryNew@123")[0]==401,"archived doctor cannot authenticate")
 172 |
 173 |     # Changing a password revokes a second pre-existing session as well.
 174 |     second=Session()
 175 |     login(second,"patient",email,"Patient@123")
 176 |     check(patient.request("/userChangePassword",{"uid":"999999","oldPassword":"wrong","newPassword":"Changed@123"})[0]==400, "wrong current password rejected")
 177 |     check(patient.request("/userChangePassword",{"uid":"999999","oldPassword":"Patient@123","newPassword":"Changed@123"})[1].endswith("user_Login.jsp"), "password change signs out current session")
 178 |     check(second.request("/view_appointment.jsp")[0]==401, "password change revokes other sessions")
 179 |     check(login(Session(),"patient",email,"Changed@123")[1].endswith("view_appointment.jsp"), "new password works for session-owned account")
 180 |     check(other.request("/userLogout")[0]==405, "GET cannot log out an authenticated patient")
 181 |     check(other.request("/userLogout",{})[1].endswith("user_Login.jsp"), "POST logout works")
 182 |     check(other.request("/view_appointment.jsp")[1].endswith("user_Login.jsp"), "logout removes access")
 183 |     throttled=Session()
 184 |     throttled.request("/user_Login.jsp")
 185 |     fake=uuid.uuid4().hex+"@hospital.test"
 186 |     for _ in range(5):
 187 |         check(throttled.request("/userLogin",{"email":fake,"password":"wrong"})[0]==401, "invalid login rejected")
 188 |     check(throttled.request("/userLogin",{"email":fake,"password":"wrong"})[0]==429, "failed login throttle")
 189 |     print(f"\n{checks} HTTP integration checks passed. Synthetic records remain in the local demo.")
 190 |
 191 | if __name__ == "__main__":
 192 |     main()
~~~

</details>

| Source line | What it does and why |
| --- | --- |
| 1 | Documents the local integration test and its operational scope. |
| 2 | Documents the local integration test and its operational scope. |
| 3 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 4 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 5 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 6 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 7 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 8 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 9 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 10 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 12 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 13 | Rejects a failed test/safety precondition; synthetic workflows are intended only for the local demo. |
| 16 | Defines an independent HTTP/cookie actor, simulating a separate browser session. |
| 17 | Defines the named HTTP/test helper; its body executes when called. |
| 18 | Gives this actor an independent cookie jar; accounts must not share authentication state. |
| 19 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 21 | Defines the named HTTP/test helper; its body executes when called. |
| 22 | Controls the explicit success/error branch shown. |
| 23 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 24 | Controls the explicit success/error branch shown. |
| 25 | Extracts/sends the hidden token so test POSTs exercise real CSRF checks. |
| 26 | Encodes named form data into HTTP request bytes. |
| 27 | Controls the explicit success/error branch shown. |
| 28 | Sends a real HTTP request and follows redirects using this actor cookies. |
| 29 | Captures an HTTP error response as test data so rejection statuses can be asserted. |
| 30 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 31 | Reads rendered output for status/body/escaping checks. |
| 32 | Extracts/sends the hidden token so test POSTs exercise real CSRF checks. |
| 33 | Controls the explicit success/error branch shown. |
| 34 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 35 | Returns HTTP status, final URL and rendered body to the assertion helpers. |
| 38 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 41 | Defines the named HTTP/test helper; its body executes when called. |
| 42 | Allows the helper to update the module-level passed-check counter. |
| 43 | Rejects a failed test/safety precondition; synthetic workflows are intended only for the local demo. |
| 44 | Increments the count after a successful assertion. |
| 45 | Prints progress or the final count to the test terminal. |
| 48 | Defines the named HTTP/test helper; its body executes when called. |
| 49 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 50 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 51 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 54 | Defines the named HTTP/test helper; its body executes when called. |
| 55 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 56 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 57 | Creates a unique synthetic test value so reruns do not collide with old accounts or requests. |
| 58 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 59 | Checks observable HTTP behavior: patient registration. |
| 60 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 61 | Checks observable HTTP behavior: patient login. |
| 62 | Returns this helper result to its caller. |
| 66 | Defines the named HTTP/test helper; its body executes when called. |
| 67 | Documents the local integration test and its operational scope. |
| 68 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 69 | Checks observable HTTP behavior: target is the local sample-data demo. |
| 70 | Repeats the listed route/workflow checks. |
| 71 | Checks observable HTTP behavior: the explicit status/body/redirect condition shown. |
| 72 | Repeats the listed route/workflow checks. |
| 73 | Checks observable HTTP behavior: the explicit status/body/redirect condition shown. |
| 74 | Checks observable HTTP behavior: API requires authentication. |
| 75 | Checks observable HTTP behavior: unlisted routes are denied. |
| 76 | Checks observable HTTP behavior: reject missing CSRF. |
| 77 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 78 | Checks observable HTTP behavior: escape account names. |
| 79 | Checks observable HTTP behavior: patient cannot access admin. |
| 80 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 81 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 82 | Extracts a token/reference from predictable HTML; this is a smoke test, not a visual DOM browser test. |
| 83 | Checks observable HTTP behavior: booking page contains real published slots. |
| 84 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 85 | Extracts a token/reference from predictable HTML; this is a smoke test, not a visual DOM browser test. |
| 86 | Creates a unique synthetic test value so reruns do not collide with old accounts or requests. |
| 87 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 88 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 89 | Checks observable HTTP behavior: server rejects invalid age. |
| 90 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 91 | Checks observable HTTP behavior: booking reserves a slot for session account. |
| 92 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 93 | Checks observable HTTP behavior: repeated request key returns same appointment. |
| 94 | Checks observable HTTP behavior: request key cannot identify different payload. |
| 95 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 96 | Checks observable HTTP behavior: patient cannot read another appointment. |
| 97 | Checks observable HTTP behavior: API applies same object ownership. |
| 98 | Checks observable HTTP behavior: occupied slot returns conflict. |
| 99 | Checks observable HTTP behavior: patient cannot confirm own appointment. |
| 100 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 101 | Checks observable HTTP behavior: doctor login. |
| 102 | Checks observable HTTP behavior: assigned doctor can read consultation. |
| 103 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 104 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 105 | Checks observable HTTP behavior: other doctor cannot read consultation. |
| 106 | Checks observable HTTP behavior: forged doctor ID cannot write note. |
| 107 | Checks observable HTTP behavior: assigned doctor confirms appointment. |
| 108 | Checks observable HTTP behavior: future appointment cannot complete early. |
| 109 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 110 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 111 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 112 | Checks observable HTTP behavior: append-only escaped consultation history. |
| 113 | Checks observable HTTP behavior: notes do not overwrite status. |
| 114 | Checks observable HTTP behavior: auditable activity history. |
| 115 | Checks observable HTTP behavior: patient can cancel future appointment. |
| 116 | Checks observable HTTP behavior: cancelled record rejects new notes. |
| 117 | Creates a unique synthetic test value so reruns do not collide with old accounts or requests. |
| 118 | Checks observable HTTP behavior: cancellation releases slot for rebooking. |
| 119 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 120 | Checks observable HTTP behavior: cancelled history remains accessible. |
| 122 | Documents the local integration test and its operational scope. |
| 123 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 124 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 125 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 126 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 127 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 128 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 129 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 130 | Defines the named HTTP/test helper; its body executes when called. |
| 131 | Creates a unique synthetic test value so reruns do not collide with old accounts or requests. |
| 132 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 133 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 134 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 135 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 136 | Checks observable HTTP behavior: simultaneous HTTP bookings have exactly one winner. |
| 138 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 139 | Imports standard-library HTTP, cookie, parsing, date or identifier support. |
| 140 | Checks observable HTTP behavior: admin login. |
| 141 | Repeats the listed route/workflow checks. |
| 142 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 143 | Checks observable HTTP behavior: the explicit status/body/redirect condition shown. |
| 144 | Checks observable HTTP behavior: protected operational metrics. |
| 145 | Checks observable HTTP behavior: metrics include request counts. |
| 146 | Checks observable HTTP behavior: patients cannot publish slots. |
| 147 | Checks observable HTTP behavior: doctor with open appointments cannot be archived. |
| 148 | Checks observable HTTP behavior: GET cannot archive doctors. |
| 150 | Documents the local integration test and its operational scope. |
| 151 | Creates a unique synthetic test value so reruns do not collide with old accounts or requests. |
| 152 | Creates a unique synthetic test value so reruns do not collide with old accounts or requests. |
| 153 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 154 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 155 | Checks observable HTTP behavior: admin creates a validated doctor. |
| 156 | Extracts a token/reference from predictable HTML; this is a smoke test, not a visual DOM browser test. |
| 157 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 158 | Checks observable HTTP behavior: new doctor can authenticate. |
| 159 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 160 | Checks observable HTTP behavior: doctor publishes own half-hour slot despite forged doctor field. |
| 161 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 162 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 163 | Checks observable HTTP behavior: doctor cannot withdraw another doctor's slot. |
| 164 | Checks observable HTTP behavior: doctor withdraws an unreserved slot. |
| 165 | Checks observable HTTP behavior: slot start must align to half-hour boundary. |
| 166 | Checks observable HTTP behavior: doctor password change signs out current session. |
| 167 | Checks observable HTTP behavior: new doctor password works. |
| 168 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 169 | Checks observable HTTP behavior: archiving retains the doctor record. |
| 170 | Checks observable HTTP behavior: archiving revokes an active doctor session. |
| 171 | Checks observable HTTP behavior: archived doctor cannot authenticate. |
| 173 | Documents the local integration test and its operational scope. |
| 174 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 175 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 176 | Checks observable HTTP behavior: wrong current password rejected. |
| 177 | Checks observable HTTP behavior: password change signs out current session. |
| 178 | Checks observable HTTP behavior: password change revokes other sessions. |
| 179 | Checks observable HTTP behavior: new password works for session-owned account. |
| 180 | Checks observable HTTP behavior: GET cannot log out an authenticated patient. |
| 181 | Checks observable HTTP behavior: POST logout works. |
| 182 | Checks observable HTTP behavior: logout removes access. |
| 183 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
| 184 | Runs this HTTP workflow step as the indicated session actor; some form IDs are deliberately forged to test ownership checks. |
| 185 | Creates a unique synthetic test value so reruns do not collide with old accounts or requests. |
| 186 | Repeats the listed route/workflow checks. |
| 187 | Checks observable HTTP behavior: invalid login rejected. |
| 188 | Checks observable HTTP behavior: failed login throttle. |
| 189 | Prints progress or the final count to the test terminal. |
| 191 | Controls the explicit success/error branch shown. |
| 192 | Prepares the synthetic input, URL, actor, expected value or result used by the adjacent assertions. |
