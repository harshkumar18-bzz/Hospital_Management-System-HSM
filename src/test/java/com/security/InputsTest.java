package com.security;

import static org.junit.Assert.*;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.util.Map;
import org.junit.Test;

public class InputsTest {
  private HttpServletRequest request(Map<String, String> values) {
    return (HttpServletRequest)
        Proxy.newProxyInstance(
            getClass().getClassLoader(),
            new Class<?>[] {HttpServletRequest.class},
            (proxy, method, args) ->
                method.getName().equals("getParameter") ? values.get(args[0]) : null);
  }

  @Test
  public void normalizesEmailAndAcceptsFutureDate() {
    assertEquals(
        "patient@example.test", Inputs.email(request(Map.of("email", " PATIENT@example.test "))));
    String date = LocalDate.now().plusDays(2).toString();
    assertEquals(date, Inputs.appointmentDate(request(Map.of("appoint_date", date))));
  }

  @Test
  public void rejectsPastDatesMalformedDatesAndMissingFields() {
    for (Map<String, String> fields :
        java.util.List.of(
            Map.of("appoint_date", "2000-01-01"),
            Map.of("appoint_date", "invalid"),
            Map.<String, String>of())) {
      assertThrows(IllegalArgumentException.class, () -> Inputs.appointmentDate(request(fields)));
    }
    assertThrows(
        IllegalArgumentException.class, () -> Inputs.email(request(Map.of("email", "invalid"))));
    assertThrows(
        IllegalArgumentException.class,
        () -> Inputs.password(request(Map.of("password", "short")), "password"));
  }
}
