package com.web;

import com.entity.*;
import com.security.*;
import com.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.*;
import java.util.*;

public abstract class ActionController extends HttpServlet {
  private void redirect(HttpServletRequest req, HttpServletResponse resp, String path) {
    resp.setStatus(303);
    resp.setHeader("Location", req.getContextPath() + "/" + path);
  }

  private void establish(HttpServletRequest req, String marker, Object principal) {
    req.changeSessionId();
    for (String key : List.of("adminObj", "userObj", "docObj"))
      req.getSession().removeAttribute(key);
    req.getSession().setAttribute(marker, principal);
    req.getSession().setAttribute("csrfToken", UUID.randomUUID().toString());
  }

  @Override
  protected final void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {
    String path = req.getServletPath();
    String destination;
    try {
      switch (path) {
        case "/register" -> {
          HospitalService.register(
              new User(
                  Inputs.required(req, "Fullname", 120),
                  Inputs.email(req),
                  Inputs.password(req, "password")));
          req.getSession().setAttribute("sucMsg", "Your account is ready. Sign in to book.");
          destination = "user_Login.jsp";
        }
        case "/userLogin", "/loginDoctor", "/adminLogin" -> {
          String email = Inputs.email(req);
          String password = req.getParameter("password");
          if (password == null || password.length() > 128)
            throw new ServiceException(401, "Email or password is incorrect.");
          LoginLimiter.check(req.getRemoteAddr(), email);
          Object principal =
              path.equals("/userLogin")
                  ? HospitalService.loginPatient(email, password)
                  : path.equals("/loginDoctor")
                      ? HospitalService.loginDoctor(email, password)
                      : HospitalService.adminLogin(email, password) ? new User() : null;
          if (principal == null) {
            LoginLimiter.failed(req.getRemoteAddr(), email);
            throw new ServiceException(401, "Email or password is incorrect.");
          }
          LoginLimiter.succeeded(req.getRemoteAddr(), email);
          String marker =
              path.equals("/userLogin")
                  ? "userObj"
                  : path.equals("/loginDoctor") ? "docObj" : "adminObj";
          establish(req, marker, principal);
          if (marker.equals("adminObj"))
            req.getSession().setAttribute("adminStamp", HospitalService.adminStamp());
          destination =
              marker.equals("userObj")
                  ? "view_appointment.jsp"
                  : marker.equals("docObj") ? "doctor/index.jsp" : "admin/index.jsp";
        }
        case "/appAppointment" -> {
          Actor actor = PageController.actor(req);
          long slot;
          try {
            slot = Long.parseLong(Inputs.required(req, "slotId", 20));
          } catch (NumberFormatException e) {
            throw new ServiceException(400, "Choose a published slot.");
          }
          var booking =
              new AppointmentService.Booking(
                  slot,
                  Inputs.required(req, "requestKey", 64),
                  Inputs.required(req, "fullname", 120),
                  Inputs.required(req, "gender", 30),
                  Inputs.required(req, "age", 3),
                  Inputs.email(req),
                  Inputs.required(req, "phno", 20),
                  Inputs.required(req, "diseases", 500),
                  Inputs.required(req, "address", 500));
          int id = HospitalService.appointments().book(actor.id(), booking);
          req.getSession().removeAttribute("appointmentForm");
          req.getSession()
              .setAttribute(
                  "succMsg", "Slot reserved. Your appointment is pending doctor confirmation.");
          destination = "appointment.jsp?id=" + id;
        }
        case "/appointmentState" -> {
          Actor actor = PageController.actor(req);
          int id = PageController.number(req, "id", 0);
          HospitalService.appointments().transition(actor, id, Inputs.required(req, "status", 16));
          req.getSession().setAttribute("succMsg", "Appointment status updated.");
          destination =
              actor.role().equals("PATIENT")
                  ? "appointment.jsp?id=" + id
                  : actor.role().equals("DOCTOR")
                      ? "doctor/comment.jsp?id=" + id
                      : "admin/appointment.jsp?id=" + id;
        }
        case "/updateStatus" -> {
          int id = PageController.number(req, "id", 0);
          HospitalService.appointments()
              .addNote(PageController.actor(req), id, Inputs.required(req, "comm", 1000));
          req.getSession().setAttribute("succMsg", "Consultation note added to the history.");
          destination = "doctor/comment.jsp?id=" + id;
        }
        case "/publishSlot" -> {
          Actor actor = PageController.actor(req);
          int doctor =
              actor.role().equals("DOCTOR")
                  ? actor.id()
                  : PageController.number(req, "doctorId", 0);
          LocalDateTime start;
          try {
            start = LocalDateTime.parse(Inputs.required(req, "startsAt", 25));
          } catch (java.time.format.DateTimeParseException e) {
            throw new ServiceException(400, "Choose a valid date and half-hour start.");
          }
          HospitalService.appointments().publish(actor, doctor, start);
          req.getSession().setAttribute("succMsg", "Half-hour slot published.");
          destination = actor.role().equals("DOCTOR") ? "doctor/slots.jsp" : "admin/slots.jsp";
        }
        case "/withdrawSlot" -> {
          Actor actor = PageController.actor(req);
          HospitalService.appointments()
              .disable(actor, Long.parseLong(Inputs.required(req, "slotId", 20)));
          req.getSession().setAttribute("succMsg", "Slot withdrawn.");
          destination = actor.role().equals("DOCTOR") ? "doctor/slots.jsp" : "admin/slots.jsp";
        }
        case "/userChangePassword", "/docChangePassword" -> {
          if (!HospitalService.changePassword(
              PageController.actor(req),
              req.getParameter("oldPassword"),
              Inputs.password(req, "newPassword")))
            throw new ServiceException(
                400, "The current password is incorrect or has changed. Try again.");
          boolean patient = path.equals("/userChangePassword");
          req.getSession().invalidate();
          req.getSession(true)
              .setAttribute(
                  "sucMsg",
                  "Password changed. Sign in again; previous sessions have been revoked.");
          destination = patient ? "user_Login.jsp" : "doctor.jsp";
        }
        case "/addDoctor", "/updateDoctor", "/doctorUpdateProfile" -> {
          boolean create = path.equals("/addDoctor"), profile = path.equals("/doctorUpdateProfile");
          int id =
              create
                  ? 0
                  : profile ? PageController.actor(req).id() : PageController.number(req, "id", 0);
          HospitalService.saveDoctor(
              DoctorInputs.read(req, id, profile ? "spec" : "specialist", create), create, profile);
          if (profile) req.getSession().setAttribute("docObj", HospitalService.doctor(id));
          req.getSession().setAttribute("sucMsg", "Doctor profile saved.");
          destination = profile ? "doctor/edit_profile.jsp" : "admin/view_doctor.jsp";
        }
        case "/delete_doctor" -> {
          HospitalService.archiveDoctor(PageController.number(req, "id", 0));
          req.getSession()
              .setAttribute("sucMsg", "Doctor archived. Historical records remain available.");
          destination = "admin/view_doctor.jsp";
        }
        case "/addSpecialist" -> {
          HospitalService.specialty(Inputs.required(req, "specName", 120));
          req.getSession().setAttribute("sucMsg", "Specialty added.");
          destination = "admin/index.jsp";
        }
        case "/userLogout", "/docLogout", "/adminLogout" -> {
          req.getSession().invalidate();
          destination =
              path.equals("/userLogout")
                  ? "user_Login.jsp"
                  : path.equals("/docLogout") ? "doctor.jsp" : "admin.jsp";
        }
        default -> throw new ServiceException(404, "Action not found.");
      }
      redirect(req, resp, destination);
    } catch (IllegalArgumentException e) {
      remember(req, path);
      throw new ServiceException(
          400, e.getMessage() == null ? "Invalid form input." : e.getMessage());
    } catch (ServiceException e) {
      remember(req, path);
      throw e;
    }
  }

  private void remember(HttpServletRequest req, String path) {
    if (!path.equals("/appAppointment")) return;
    Map<String, String> form = new HashMap<>();
    for (String field :
        List.of("fullname", "gender", "age", "email", "phno", "diseases", "address")) {
      String value = req.getParameter(field);
      if (value != null) form.put(field, value.substring(0, Math.min(500, value.length())));
    }
    req.getSession().setAttribute("appointmentForm", form);
  }
}
