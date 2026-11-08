package com.service;

public final class DataAccessException extends RuntimeException {
  public DataAccessException(Throwable cause) {
    super("The database operation could not be completed.", cause);
  }
}
