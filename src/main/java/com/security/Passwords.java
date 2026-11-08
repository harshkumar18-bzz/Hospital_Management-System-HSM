package com.security;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class Passwords {
  private static final int ITERATIONS = 600_000;
  private static final SecureRandom RANDOM = new SecureRandom();

  private Passwords() {}

  public static String hash(String password) {
    byte[] salt = new byte[16];
    RANDOM.nextBytes(salt);
    return "pbkdf2$"
        + ITERATIONS
        + "$"
        + Base64.getEncoder().encodeToString(salt)
        + "$"
        + Base64.getEncoder().encodeToString(derive(password, salt, ITERATIONS));
  }

  public static boolean verify(String password, String stored) {
    if (password == null || stored == null || !stored.startsWith("pbkdf2$")) return false;
    try {
      String[] parts = stored.split("\\$");
      if (parts.length != 4) return false;
      int iterations = Integer.parseInt(parts[1]);
      if (iterations < 1 || iterations > 1_000_000) return false;
      return MessageDigest.isEqual(
          Base64.getDecoder().decode(parts[3]),
          derive(password, Base64.getDecoder().decode(parts[2]), iterations));
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  public static boolean needsRehash(String stored) {
    try {
      return Integer.parseInt(stored.split("\\$")[1]) < ITERATIONS;
    } catch (RuntimeException e) {
      return true;
    }
  }

  public static void main(String[] args) throws java.io.IOException {
    java.io.Console console = System.console();
    String password =
        console == null
            ? new java.io.BufferedReader(new java.io.InputStreamReader(System.in)).readLine()
            : new String(console.readPassword("Administrator password: "));
    if (password == null || password.length() < 12)
      throw new IllegalArgumentException(
          "Use at least 12 characters for an administrator password.");
    System.out.println(hash(password));
  }

  private static byte[] derive(String password, byte[] salt, int iterations) {
    PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, 256);
    try {
      return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
    } catch (Exception e) {
      throw new IllegalStateException("Password hashing unavailable", e);
    } finally {
      spec.clearPassword();
    }
  }
}
