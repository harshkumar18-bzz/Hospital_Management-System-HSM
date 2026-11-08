FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B -q dependency:go-offline
COPY src ./src
RUN mvn -B clean verify

FROM tomcat:10.1.61-jdk17-temurin
RUN apt-get update && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/* /usr/local/tomcat/webapps/* \
    && groupadd --gid 10001 hospital && useradd --uid 10001 --gid hospital --no-create-home hospital
COPY deploy/server.xml /usr/local/tomcat/conf/server.xml
COPY --from=build /app/target/Hospital_Management_System.war /usr/local/tomcat/webapps/ROOT.war
RUN chown -R hospital:hospital /usr/local/tomcat
USER 10001:10001
EXPOSE 8080
HEALTHCHECK --interval=10s --timeout=3s --start-period=40s --retries=12 CMD curl --fail --silent http://127.0.0.1:8080/health/ready || exit 1
CMD ["catalina.sh","run"]
