package com.security;

import com.entity.*;
import com.service.*;
import com.web.Metrics;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

@WebFilter("/*")
public final class RequestFilter implements Filter {
  private static final Logger LOG = Logger.getLogger(RequestFilter.class.getName());
  private static final Set<String> PUBLIC =
      Set.of(
          "/",
          "/index.jsp",
          "/signup.jsp",
          "/user_Login.jsp",
          "/doctor.jsp",
          "/admin.jsp",
          "/register",
          "/userLogin",
          "/loginDoctor",
          "/adminLogin",
          "/health/live",
          "/health/ready");
  private static final Set<String> ADMIN =
      Set.of("/addDoctor", "/updateDoctor", "/delete_doctor", "/addSpecialist", "/adminLogout");
  private static final Set<String> DOCTOR =
      Set.of("/doctorUpdateProfile", "/docChangePassword", "/updateStatus", "/docLogout");
  private static final Set<String> PATIENT =
      Set.of(
          "/user_appointment.jsp",
          "/view_appointment.jsp",
          "/appointment.jsp",
          "/change_password.jsp",
          "/appAppointment",
          "/userChangePassword",
          "/userLogout");
  private static final Set<String> SHARED =
      Set.of(
          "/appointmentState", "/publishSlot", "/withdrawSlot", "/api/slots", "/api/appointments");

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    long start = System.nanoTime();
    String requestId = UUID.randomUUID().toString();
    req.setAttribute("requestId", requestId);
    resp.setHeader("X-Request-ID", requestId);
    resp.setHeader("X-Content-Type-Options", "nosniff");
    resp.setHeader("X-Frame-Options", "DENY");
    resp.setHeader("Referrer-Policy", "same-origin");
    resp.setHeader(
        "Content-Security-Policy",
        "default-src 'self'; script-src 'self' https://cdn.jsdelivr.net; style-src 'self'"
            + " 'unsafe-inline' https://cdn.jsdelivr.net https://cdnjs.cloudflare.com; font-src"
            + " 'self' https://cdnjs.cloudflare.com data:; img-src 'self' data:; form-action"
            + " 'self'; frame-ancestors 'none'; base-uri 'self'");
    if (req.isSecure()) resp.setHeader("Strict-Transport-Security", "max-age=31536000");
    req.setCharacterEncoding("UTF-8");
    String path = req.getServletPath();
    try {
      if (path.startsWith("/img/") || path.startsWith("/component/") && !path.endsWith(".jsp")) {
        chain.doFilter(req, resp);
        return;
      }
      resp.setHeader("Cache-Control", "no-store");
      if (path.startsWith("/health/")) {
        chain.doFilter(req, resp);
        return;
      }
      if (req.getContentLengthLong() > 16384)
        throw new ServiceException(413, "This form is too large.");
      req.setAttribute(
          "demoMode", Boolean.parseBoolean(com.db.DBConnect.config("DEMO_MODE", "false")));
      HttpSession session = req.getSession();
      if (session.getAttribute("csrfToken") == null)
        session.setAttribute("csrfToken", UUID.randomUUID().toString());
      String role =
          path.startsWith("/admin/") || ADMIN.contains(path)
              ? "adminObj"
              : path.startsWith("/doctor/") || DOCTOR.contains(path)
                  ? "docObj"
                  : PATIENT.contains(path) ? "userObj" : null;
      boolean shared = SHARED.contains(path);
      if (!PUBLIC.contains(path) && role == null && !shared)
        throw new ServiceException(404, "Page not found.");
      Object principal = role == null ? null : session.getAttribute(role);
      if (role != null && principal == null) {
        if (req.getMethod().equals("GET")) {
          resp.sendRedirect(
              req.getContextPath()
                  + (role.equals("adminObj")
                      ? "/admin.jsp"
                      : role.equals("docObj") ? "/doctor.jsp" : "/user_Login.jsp"));
          return;
        }
        throw new ServiceException(401, "Sign in before submitting this form.");
      }
      if (session.getAttribute("userObj") instanceof User u
          && !HospitalService.validSession(Actor.patient(u.getId()), u.getAuthVersion())) {
        session.invalidate();
        throw new ServiceException(401, "Your session expired. Sign in again.");
      }
      if (session.getAttribute("docObj") instanceof Doctor d
          && !HospitalService.validSession(Actor.doctor(d.getId()), d.getAuthVersion())) {
        session.invalidate();
        throw new ServiceException(401, "Your doctor session expired. Sign in again.");
      }
      if (session.getAttribute("adminObj") != null
          && !HospitalService.adminStamp().equals(session.getAttribute("adminStamp"))) {
        session.invalidate();
        throw new ServiceException(401, "Your administrator session expired.");
      }
      if (shared
          && session.getAttribute("userObj") == null
          && session.getAttribute("docObj") == null
          && session.getAttribute("adminObj") == null)
        throw new ServiceException(401, "Please sign in.");
      if ((path.equals("/publishSlot") || path.equals("/withdrawSlot"))
          && session.getAttribute("userObj") != null)
        throw new ServiceException(403, "Only hospital staff can manage slots.");
      if (req.getMethod().equals("POST")
          && !Objects.equals(session.getAttribute("csrfToken"), req.getParameter("csrfToken")))
        throw new ServiceException(403, "Your form expired. Reload the form and try again.");
      chain.doFilter(req, resp);
    } catch (ServiceException e) {
      error(req, resp, e.getStatus(), e.getMessage());
    } catch (DataAccessException e) {
      LOG.severe("requestId=" + requestId + " database_failure");
      error(req, resp, 503, "The database is temporarily unavailable. Please try again.");
    } catch (Exception e) {
      LOG.severe("requestId=" + requestId + " failure_type=" + e.getClass().getSimpleName());
      error(req, resp, 500, "We could not complete this request. Please try again.");
    } finally {
      long elapsed = System.nanoTime() - start;
      Metrics.requests.increment();
      Metrics.nanos.add(elapsed);
      if (resp.getStatus() >= 500) Metrics.errors.increment();
      LOG.info(
          "requestId="
              + requestId
              + " method="
              + req.getMethod()
              + " path="
              + path
              + " status="
              + resp.getStatus()
              + " duration_ms="
              + (elapsed / 1_000_000));
    }
  }

  private void error(HttpServletRequest req, HttpServletResponse resp, int status, String message)
      throws ServletException, IOException {
    if (resp.isCommitted()) return;
    resp.setStatus(status);
    req.setAttribute("errorMessage", message);
    if (status == 429) resp.setHeader("Retry-After", "300");
    if (req.getServletPath().startsWith("/api/")) {
      resp.setContentType("application/json;charset=UTF-8");
      new com.fasterxml.jackson.databind.ObjectMapper()
          .writeValue(
              resp.getWriter(),
              Map.of("error", message, "requestId", req.getAttribute("requestId")));
      return;
    }
    req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
  }
}
