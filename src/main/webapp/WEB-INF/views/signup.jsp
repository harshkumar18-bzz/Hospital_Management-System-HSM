<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Create an account | HMS</title><%@include file="component/allcss.jsp"%></head>
<body><%@include file="component/navbar.jsp"%>
<main id="main-content" class="auth-main"><div class="auth-layout">
 <div class="auth-copy"><h1>A healthier start<br>begins here.</h1><p>Create your patient account to request an appointment.</p></div>
 <section class="auth-panel"><h2>Create your account</h2><p class="subtext">A few details and you are ready to book.</p>
 <%@include file="component/messages.jsp"%>
 <form action="register" method="post">
<input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}">
 <div class="mb-3"><label class="form-label" for="fullname">Full name</label><input class="form-control" id="fullname" name="Fullname" autocomplete="name" required maxlength="120"></div>
 <div class="mb-3"><label class="form-label" for="email">Email address</label><input class="form-control" id="email" name="email" type="email" autocomplete="username" required maxlength="254"></div>
 <div class="mb-4"><label class="form-label" for="password">Password</label><input class="form-control" id="password" name="password" type="password" autocomplete="new-password" minlength="8" maxlength="128" required><p class="form-note">Use at least 8 characters.</p></div>
 <button class="btn btn-primary w-100" type="submit">Create account</button>
 </form>
 <p class="auth-switch">Already registered? <a href="user_Login.jsp">Sign in</a></p>
 </section>
</div></main><%@include file="component/footer.jsp"%></body></html>
