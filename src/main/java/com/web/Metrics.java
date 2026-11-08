package com.web;

import java.util.concurrent.atomic.LongAdder;

public final class Metrics {
  public static final LongAdder requests = new LongAdder(),
      errors = new LongAdder(),
      nanos = new LongAdder();

  private Metrics() {}

  public static String prometheus() {
    return "hms_http_requests_total "
        + requests.sum()
        + "\nhms_http_errors_total "
        + errors.sum()
        + "\nhms_http_duration_seconds_sum "
        + (nanos.sum() / 1_000_000_000.0)
        + "\n";
  }
}
