package com.entity;

public record AuditEvent(
    String actorRole, String action, String fromStatus, String toStatus, String createdAt) {
  public String getActorRole() {
    return actorRole;
  }

  public String getAction() {
    return action;
  }

  public String getFromStatus() {
    return fromStatus;
  }

  public String getToStatus() {
    return toStatus;
  }

  public String getCreatedAt() {
    return createdAt;
  }
}
