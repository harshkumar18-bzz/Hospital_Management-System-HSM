package com.entity;

public record AppointmentRow(
    int id,
    int userId,
    int doctorId,
    Long slotId,
    String fullName,
    String gender,
    String age,
    String date,
    String email,
    String phone,
    String reason,
    String address,
    String status,
    String doctorName,
    String slotTime,
    String latestNote) {
  public int getId() {
    return id;
  }

  public int getUserId() {
    return userId;
  }

  public int getDoctorId() {
    return doctorId;
  }

  public Long getSlotId() {
    return slotId;
  }

  public String getFullName() {
    return fullName;
  }

  public String getGender() {
    return gender;
  }

  public String getAge() {
    return age;
  }

  public String getDate() {
    return date;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getReason() {
    return reason;
  }

  public String getAddress() {
    return address;
  }

  public String getStatus() {
    return status;
  }

  public String getDoctorName() {
    return doctorName;
  }

  public String getSlotTime() {
    return slotTime;
  }

  public String getLatestNote() {
    return latestNote;
  }

  public boolean isOpen() {
    return status.equals("PENDING") || status.equals("CONFIRMED");
  }
}
