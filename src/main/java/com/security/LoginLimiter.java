package com.security;

import com.service.ServiceException;
import java.util.concurrent.ConcurrentHashMap;

/** Bounded, process-local failed-login throttling. Use a shared store for multiple replicas. */
public final class LoginLimiter {
  private static final ConcurrentHashMap<String, Attempt> attempts = new ConcurrentHashMap<>();
  private static final long WINDOW = 300_000;

  private record Attempt(long since, int count) {}

  private LoginLimiter() {}

  private static String key(String ip, String email) {
    return ip + "|" + email;
  }

  public static void check(String ip, String email) {
    long now = System.currentTimeMillis();
    attempts.entrySet().removeIf(e -> now - e.getValue().since() > WINDOW);
    Attempt account = attempts.get(key(ip, email)), address = attempts.get(ip);
    if (account != null && account.count() >= 5
        || address != null && address.count() >= 25
        || attempts.size() >= 10000)
      throw new ServiceException(
          429, "Too many failed sign-in attempts. Wait five minutes and try again.");
  }

  public static void failed(String ip, String email) {
    long now = System.currentTimeMillis();
    for (String k : new String[] {key(ip, email), ip})
      attempts.compute(
          k,
          (ignored, a) ->
              a == null || now - a.since() > WINDOW
                  ? new Attempt(now, 1)
                  : new Attempt(a.since(), a.count() + 1));
  }

  public static void succeeded(String ip, String email) {
    attempts.remove(key(ip, email));
  }
}
