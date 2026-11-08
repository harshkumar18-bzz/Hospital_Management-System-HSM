package com.security;

import com.entity.Doctor;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public final class DoctorInputs {
  private DoctorInputs() {}

  public static Doctor read(
      HttpServletRequest req, int id, String specialtyField, boolean requirePassword) {
    String name = Inputs.required(req, "fullname", 120);
    String dob = Inputs.required(req, "dob", 10);
    try {
      if (LocalDate.parse(dob).isAfter(LocalDate.now())) throw new IllegalArgumentException();
    } catch (RuntimeException e) {
      throw new IllegalArgumentException("Enter a valid date of birth.");
    }
    String qualification = Inputs.required(req, "qualification", 160);
    String specialty = Inputs.required(req, specialtyField, 120);
    String email = Inputs.email(req);
    String phone = Inputs.required(req, "mobno", 20);
    if (!phone.matches("(?=.*[0-9])[+0-9 ()-]{7,20}"))
      throw new IllegalArgumentException("Enter a valid phone number.");
    String password = req.getParameter("password");
    if (requirePassword || (password != null && !password.isBlank()))
      password = Inputs.password(req, "password");
    return new Doctor(id, name, dob, qualification, specialty, email, phone, password);
  }
}
