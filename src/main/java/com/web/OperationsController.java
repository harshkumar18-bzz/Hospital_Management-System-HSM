package com.web;

import com.db.DBConnect;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = {"/health/live", "/health/ready", "/admin/metrics"})
public final class OperationsController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/plain;charset=UTF-8");
    if (req.getServletPath().equals("/admin/metrics")) {
      resp.getWriter().print(Metrics.prometheus());
      return;
    }
    if (req.getServletPath().endsWith("/ready"))
      try (Connection c = DBConnect.open();
          PreparedStatement ps = c.prepareStatement("SELECT 1")) {
        ps.executeQuery().close();
      } catch (SQLException | RuntimeException e) {
        resp.setStatus(503);
        resp.getWriter().println("not ready");
        return;
      }
    resp.getWriter().println("ok");
  }
}
