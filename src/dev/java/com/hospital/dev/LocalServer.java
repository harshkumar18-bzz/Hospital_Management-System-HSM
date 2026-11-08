package com.hospital.dev;

import java.nio.file.Path;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.scan.StandardJarScanner;

/** Local-only runner, excluded from the normal WAR build. */
public final class LocalServer {
  public static void main(String[] args) throws Exception {
    if (com.db.DBConnect.config("DB_URL", "").isBlank())
      System.setProperty(
          "DB_URL", "jdbc:h2:file:./.local/hospital;MODE=MySQL;DATABASE_TO_LOWER=TRUE");
    if (System.getenv("DB_USER") == null && System.getProperty("DB_USER") == null)
      System.setProperty("DB_USER", "sa");
    if (System.getenv("DB_PASSWORD") == null && System.getProperty("DB_PASSWORD") == null)
      System.setProperty("DB_PASSWORD", "");
    System.setProperty("DB_MIGRATE", "true");
    if (System.getenv("DEMO_MODE") == null && System.getProperty("DEMO_MODE") == null)
      System.setProperty("DEMO_MODE", "true");
    Tomcat tomcat = new Tomcat();
    tomcat.setBaseDir(
        Path.of(".local/tomcat-" + System.getProperty("port", "8080")).toAbsolutePath().toString());
    int port = Integer.parseInt(System.getProperty("port", "8080"));
    tomcat.setPort(port);
    tomcat.getConnector().setMaxPostSize(16384);
    tomcat.getConnector().setMaxParameterCount(50);
    tomcat.getConnector().setProperty("address", "127.0.0.1");
    Context context = tomcat.addWebapp("", Path.of("src/main/webapp").toAbsolutePath().toString());
    context.setParentClassLoader(LocalServer.class.getClassLoader());
    StandardRoot resources = new StandardRoot(context);
    resources.addPreResources(
        new DirResourceSet(
            resources,
            "/WEB-INF/classes",
            Path.of("target/classes").toAbsolutePath().toString(),
            "/"));
    context.setResources(resources);
    ((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(() -> {
              try {
                tomcat.stop();
                tomcat.destroy();
              } catch (Exception e) {
                e.printStackTrace();
              }
            }));
    tomcat.start();
    if (!context.getState().isAvailable()) {
      tomcat.stop();
      tomcat.destroy();
      throw new IllegalStateException("Web application failed to start");
    }
    System.out.println("Hospital Management System: http://localhost:" + port);
    tomcat.getServer().await();
  }
}
