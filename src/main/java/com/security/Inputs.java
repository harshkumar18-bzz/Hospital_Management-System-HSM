package com.security;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public final class Inputs {
  private Inputs() {}

  public static String required(HttpServletRequest req, String name, int max) {
    String value = req.getParameter(name);
    if (value == null || value.trim().isEmpty() || value.trim().length() > max)
      throw new IllegalArgumentException("Please complete all fields within the allowed lengths.");
    return value.trim();
  }

  public static String email(HttpServletRequest req) {
    String value = required(req, "email", 254).toLowerCase(java.util.Locale.ROOT);
    if (!value.matches("[^\\s@]+@[^\\s@]+\\.[^\\s@]+"))
      throw new IllegalArgumentException("Enter a valid email address.");
    return value;
  }

  public static String password(HttpServletRequest req, String field) {
    String value = req.getParameter(field);
    if (value == null || value.length() < 8 || value.length() > 128)
      throw new IllegalArgumentException("Use a password between 8 and 128 characters.");
    return value;
  }

  public static String appointmentDate(HttpServletRequest req) {
    try {
      LocalDate date = LocalDate.parse(required(req, "appoint_date", 10));
      if (date.isBefore(LocalDate.now())) throw new IllegalArgumentException();
      return date.toString();
    } catch (RuntimeException e) {
      throw new IllegalArgumentException("Choose today or a future appointment date.");
    }
  }
}
