<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Patient sign in | HMS</title><%@include file="component/allcss.jsp"%></head>
<body><%@include file="component/navbar.jsp"%>
<main id="main-content" class="auth-main"><div class="auth-layout">
 <div class="auth-copy"><h1>Your care,<br>in one place.</h1><p>Book your next visit and follow updates from your doctor.</p></div>
 <section class="auth-panel"><h2>Sign in to your account</h2><p class="subtext">Use the email you registered with.</p>
 <%@include file="component/messages.jsp"%>
 <form action="userLogin" method="post">
<input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">

 <div class="mb-3"><label class="form-label" for="email">Email address</label><input class="form-control" id="email" name="email" type="email" autocomplete="username" required maxlength="254"></div>
 <div class="mb-4"><label class="form-label" for="password">Password</label><input class="form-control" id="password" name="password" type="password" autocomplete="current-password"  maxlength="128" required></div>
 <button class="btn btn-primary w-100" type="submit">Sign in</button>
 </form>
 <p class="auth-switch">New here? <a href="signup.jsp">Create an account</a></p>
 </section>
</div></main><%@include file="component/footer.jsp"%></body></html>
