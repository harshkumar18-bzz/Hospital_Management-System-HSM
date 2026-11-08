<%@taglib prefix="c" uri="jakarta.tags.core"%>
<nav class="navbar navbar-expand-lg hms-nav" aria-label="Administration navigation"><div class="container">
<a class="navbar-brand" href="index.jsp"><span class="brand-mark" aria-hidden="true">+</span><span>HMS<span class="brand-sub">Administration</span></span></a>
<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#portal-nav" aria-controls="portal-nav" aria-expanded="false" aria-label="Toggle navigation"><i class="fa-solid fa-bars" aria-hidden="true"></i></button>
<div class="collapse navbar-collapse" id="portal-nav"><ul class="navbar-nav ms-auto align-items-lg-center gap-lg-3">
<li class="nav-item"><a class="nav-link" href="index.jsp">Overview</a></li>
<li class="nav-item"><a class="nav-link" href="view_doctor.jsp">Doctors</a></li><li class="nav-item"><a class="nav-link" href="doctor.jsp">Add doctor</a></li>
<li class="nav-item"><a class="nav-link" href="patient.jsp">Appointments</a></li><li class="nav-item"><a class="nav-link" href="slots.jsp">Slots</a></li>
<li class="nav-item"><form action="../adminLogout" method="post"><input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}"><button class="nav-link account-toggle">Sign out</button></form></li>
</ul></div></div></nav>