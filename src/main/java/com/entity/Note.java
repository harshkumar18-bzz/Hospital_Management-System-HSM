package com.entity;

public record Note(String author, String body, String createdAt) {
  public String getAuthor() {
    return author;
  }

  public String getBody() {
    return body;
  }

  public String getCreatedAt() {
    return createdAt;
  }
}
