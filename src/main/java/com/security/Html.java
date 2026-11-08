package com.security;

/** Escapes legacy JSP expressions in both HTML text and quoted attributes. */
public final class Html {
  private Html() {}

  public static String escape(Object value) {
    if (value == null) return "";
    return value
        .toString()
        .replace("&", "&amp;")
        .replace("<", "&lt;")
        .replace(">", "&gt;")
        .replace("\"", "&quot;")
        .replace("'", "&#39;");
  }
}
