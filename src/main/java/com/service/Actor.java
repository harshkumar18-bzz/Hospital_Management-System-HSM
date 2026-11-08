package com.service;

public record Actor(String role, int id) {
  public Actor {
    if (!java.util.Set.of("PATIENT", "DOCTOR", "ADMIN").contains(role)
        || (!role.equals("ADMIN") && id < 1)) throw new IllegalArgumentException("Invalid actor");
  }

  public static Actor patient(int id) {
    return new Actor("PATIENT", id);
  }

  public static Actor doctor(int id) {
    return new Actor("DOCTOR", id);
  }

  public static Actor admin() {
    return new Actor("ADMIN", 0);
  }
}
