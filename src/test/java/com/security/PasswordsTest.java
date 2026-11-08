package com.security;

import static org.junit.Assert.*;

import org.junit.Test;

public class PasswordsTest {
  @Test
  public void saltsPasswordsAndRejectsIncorrectCredentials() {
    String first = Passwords.hash("CorrectPassword123");
    String second = Passwords.hash("CorrectPassword123");
    assertNotEquals(first, second);
    assertTrue(first.startsWith("pbkdf2$600000$"));
    assertFalse(Passwords.needsRehash(first));
    assertTrue(Passwords.needsRehash("pbkdf2$210000$old$representation"));
    assertTrue(Passwords.verify("CorrectPassword123", first));
    assertFalse(Passwords.verify("wrong", first));
    assertFalse(Passwords.verify(null, first));
    assertFalse(Passwords.verify("plain", "plain"));
    assertFalse(Passwords.verify("anything", "pbkdf2$-1$invalid$invalid"));
    assertFalse(Passwords.verify("anything", "pbkdf2$210000$invalid$invalid"));
  }

  @Test
  public void escapesHtmlAndAttributeBreakouts() {
    assertEquals("&lt;script&gt;&quot;&#39;&amp;", Html.escape("<script>\"'&"));
    assertEquals("", Html.escape(null));
  }
}
