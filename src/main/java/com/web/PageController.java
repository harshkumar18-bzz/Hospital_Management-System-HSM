package com.web;

import com.entity.*;
import com.service.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.*;
import java.util.*;

@WebServlet(
    urlPatterns = {
      "/index.jsp",
      "/signup.jsp",
      "/user_Login.jsp",
      "/doctor.jsp",
      "/admin.jsp",
      "/user_appointment.jsp",
      "/view_appointment.jsp",
      "/appointment.jsp",
      "/change_password.jsp",
      "/admin/index.jsp",
      "/admin/doctor.jsp",
      "/admin/view_doctor.jsp",
      "/admin/edit_doctor.jsp",
      "/admin/patient.jsp",
      "/admin/slots.jsp",
      "/admin/appointment.jsp",
      "/doctor/index.jsp",
      "/doctor/patient.jsp",
      "/doctor/comment.jsp",
      "/doctor/edit_profile.jsp",
      "/doctor/slots.jsp"
    })
public final class PageController extends HttpServlet {
  public static Actor actor(HttpServletRequest req) {
    HttpSession session = req.getSession();
    if (session.getAttribute("adminObj") != null) return Actor.admin();
    if (session.getAttribute("docObj") instanceof Doctor d) return Actor.doctor(d.getId());
    if (session.getAttribute("userObj") instanceof User u) return Actor.patient(u.getId());
    throw new ServiceException(401, "Please sign in.");
  }

  public static int number(HttpServletRequest req, String key, int fallback) {
    String value = req.getParameter(key);
    if (value == null || value.isBlank()) return fallback;
    try {
      int n = Integer.parseInt(value.trim());
      if (n < 1 || n > 1000000) throw new NumberFormatException();
      return n;
    } catch (NumberFormatException e) {
      throw new ServiceException(400, "Invalid " + key + ".");
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String path = req.getServletPath();
    int page = number(req, "page", 1);
    req.setAttribute("page", page);
    switch (path) {
      case "/user_appointment.jsp" -> {
        Integer doctor = req.getParameter("doctorId") == null ? null : number(req, "doctorId", 0);
        req.setAttribute("doctors", HospitalService.doctors(false));
        req.setAttribute("slots", HospitalService.appointments().available(doctor));
        req.setAttribute("bookingKey", UUID.randomUUID().toString());
        req.setAttribute("hospitalZone", HospitalService.zone().getId());
      }
      case "/view_appointment.jsp", "/doctor/patient.jsp", "/admin/patient.jsp" -> {
        var rows = HospitalService.appointments().list(actor(req), page);
        req.setAttribute("appointments", rows);
        req.setAttribute("hasNext", rows.size() == 20);
        req.setAttribute(
            "detailsPath",
            path.startsWith("/admin/")
                ? "appointment.jsp"
                : path.startsWith("/doctor/") ? "comment.jsp" : "appointment.jsp");
      }
      case "/appointment.jsp", "/doctor/comment.jsp", "/admin/appointment.jsp" -> {
        int id = number(req, "id", 0);
        Actor actor = actor(req);
        var service = HospitalService.appointments();
        req.setAttribute("appointment", service.detail(actor, id));
        req.setAttribute("notes", service.notes(actor, id));
        req.setAttribute("events", service.events(actor, id));
        req.setAttribute(
            "allowedActions", service.allowedActions(actor, service.detail(actor, id)));
      }
      case "/admin/index.jsp" -> {
        req.setAttribute("stats", HospitalService.counts(null));
        req.setAttribute("specialties", HospitalService.specialties());
      }
      case "/doctor/index.jsp" -> {
        req.setAttribute("stats", HospitalService.counts(actor(req).id()));
      }
      case "/admin/view_doctor.jsp" -> req.setAttribute("doctors", HospitalService.doctors(true));
      case "/admin/doctor.jsp", "/admin/edit_doctor.jsp" -> {
        req.setAttribute("specialties", HospitalService.specialties());
        if (path.contains("edit_"))
          req.setAttribute("doctor", HospitalService.doctor(number(req, "id", 0)));
      }
      case "/doctor/edit_profile.jsp" -> {
        req.setAttribute("specialties", HospitalService.specialties());
        req.setAttribute("doctor", HospitalService.doctor(actor(req).id()));
        req.setAttribute("profileOnly", true);
      }
      case "/doctor/slots.jsp", "/admin/slots.jsp" -> {
        Integer id = path.startsWith("/doctor") ? actor(req).id() : null;
        req.setAttribute("slots", HospitalService.appointments().available(id));
        req.setAttribute("doctors", HospitalService.doctors(false));
        req.setAttribute("hospitalZone", HospitalService.zone().getId());
      }
    }
    req.getRequestDispatcher("/WEB-INF/views" + path).forward(req, resp);
  }
}
