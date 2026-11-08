package com.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

/** Authenticated read APIs share the same service authorization as rendered pages. */
@WebServlet(urlPatterns = {"/api/slots", "/api/appointments", "/api/appointments/*"})
public final class ApiController extends HttpServlet {
  private static final ObjectMapper JSON = new ObjectMapper();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("application/json;charset=UTF-8");
    if (req.getServletPath().equals("/api/slots")) {
      Integer doctor =
          req.getParameter("doctorId") == null ? null : PageController.number(req, "doctorId", 0);
      JSON.writeValue(
          resp.getWriter(),
          Map.of(
              "timeZone",
              HospitalService.zone().getId(),
              "slotDurationMinutes",
              30,
              "slots",
              HospitalService.appointments().available(doctor).stream()
                  .map(s ->
                      Map.of(
                          "id",
                          s.id(),
                          "doctorId",
                          s.doctorId(),
                          "label",
                          s.getLabel(),
                          "startsAt",
                          s.getStartsAt()))
                  .toList()));
      return;
    }
    Actor actor = PageController.actor(req);
    String info = req.getPathInfo();
    if (info == null || info.equals("/")) {
      int page = PageController.number(req, "page", 1);
      JSON.writeValue(
          resp.getWriter(),
          Map.of(
              "page",
              page,
              "pageSize",
              20,
              "appointments",
              HospitalService.appointments().list(actor, page)));
      return;
    }
    int id;
    try {
      id = Integer.parseInt(info.substring(1));
    } catch (NumberFormatException e) {
      throw new ServiceException(400, "Invalid appointment reference.");
    }
    JSON.writeValue(resp.getWriter(), HospitalService.appointments().detail(actor, id));
  }
}
