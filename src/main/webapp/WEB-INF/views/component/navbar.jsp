<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page isELIgnored="false" %>
<a class="skip-link" href="#main-content">Skip to content</a>
<nav class="navbar navbar-expand-lg hms-nav" aria-label="Main navigation" data-demo-mode="${demoMode}">
 <div class="container">
  <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp"><span class="brand-mark" aria-hidden="true">+</span><span>HMS<span class="brand-sub">Hospital Management System</span></span></a>
  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#main-nav" aria-controls="main-nav" aria-expanded="false" aria-label="Toggle navigation"><i class="fa-solid fa-bars" aria-hidden="true"></i></button>
  <div class="collapse navbar-collapse" id="main-nav">
   <ul class="navbar-nav ms-auto align-items-lg-center gap-lg-3">
    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/index.jsp#care">Our care</a></li>
    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/doctor.jsp">Doctor portal</a></li>
    <c:choose><c:when test="${not empty userObj}">
     <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/view_appointment.jsp">My appointments</a></li>
     <li class="nav-item dropdown"><button class="nav-link dropdown-toggle account-toggle" data-bs-toggle="dropdown" aria-expanded="false"><c:out value="${userObj.fullName}"/></button>
      <ul class="dropdown-menu"><li><a class="dropdown-item" href="${pageContext.request.contextPath}/change_password.jsp">Change password</a></li><li><form action="${pageContext.request.contextPath}/userLogout" method="post"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><button class="dropdown-item">Sign out</button></form></li></ul>
     </li>
    </c:when><c:otherwise><li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/user_Login.jsp">Patient sign in</a></li></c:otherwise></c:choose>
    <li class="nav-item"><a class="btn btn-primary" href="${pageContext.request.contextPath}/user_appointment.jsp">Book an appointment <i class="fa-solid fa-arrow-right ms-2" aria-hidden="true"></i></a></li>
   </ul>
  </div>
 </div>
</nav>
