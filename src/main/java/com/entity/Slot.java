package com.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Slot(
    long id, int doctorId, String doctorName, String specialty, LocalDateTime startsAt) {
  public long getId() {
    return id;
  }

  public int getDoctorId() {
    return doctorId;
  }

  public String getLabel() {
    return startsAt.format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy · HH:mm"))
        + " · "
        + doctorName
        + " · "
        + specialty;
  }

  public String getStartsAt() {
    return startsAt.toString();
  }
}
