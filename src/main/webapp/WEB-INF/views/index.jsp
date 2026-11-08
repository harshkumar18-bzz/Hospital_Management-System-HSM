<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><meta name="description" content="Book hospital appointments, choose your doctor, and follow your care in one place."><title>HMS | Care starts here</title><%@include file="component/allcss.jsp"%></head>
<body>
<%@include file="component/navbar.jsp"%>
<main id="main-content">
 <section class="container home-hero">
  <div class="hero-copy"><p class="eyebrow">YOUR HEALTH, MADE SIMPLER</p><h1>Good care.<br>Less waiting.</h1><p class="hero-description">Find your doctor, request an appointment, and keep your care in one place.</p><a class="btn btn-primary btn-lg" href="user_appointment.jsp">Book an appointment <i class="fa-solid fa-arrow-right ms-3" aria-hidden="true"></i></a></div>
  <div class="hero-photo"><img src="img/hosp2.jpg" width="1200" height="800" alt="Hospital building and surrounding grounds"></div>
 </section>
 <section class="container journey" aria-label="How appointments work">
  <div><span class="step-number">01</span><div><h2>Create your account</h2><p>Your details, ready for your next visit.</p></div></div>
  <div><span class="step-number">02</span><div><h2>Choose your doctor</h2><p>Find the specialty that suits your needs.</p></div></div>
  <div><span class="step-number">03</span><div><h2>Request your visit</h2><p>Track your request and your doctor's notes.</p></div></div>
 </section>
 <section id="care" class="container care-section">
  <div class="section-heading"><h2>A place to start feeling better.</h2><p>Choose a specialty when you book. Your doctor can guide the next steps.</p></div>
  <div class="specialty-grid">
   <a href="user_appointment.jsp"><i class="fa-solid fa-stethoscope" aria-hidden="true"></i><h3>General medicine</h3><p>Everyday concerns and routine consultations.</p><span>Request a visit <i class="fa-solid fa-arrow-right" aria-hidden="true"></i></span></a>
   <a href="user_appointment.jsp"><i class="fa-solid fa-heart-pulse" aria-hidden="true"></i><h3>Cardiology</h3><p>Consultations focused on heart health.</p><span>Request a visit <i class="fa-solid fa-arrow-right" aria-hidden="true"></i></span></a>
   <a href="user_appointment.jsp"><i class="fa-solid fa-child" aria-hidden="true"></i><h3>Pediatrics</h3><p>Care for children at every stage of growth.</p><span>Request a visit <i class="fa-solid fa-arrow-right" aria-hidden="true"></i></span></a>
   <a href="user_appointment.jsp"><i class="fa-solid fa-hand-holding-medical" aria-hidden="true"></i><h3>Dermatology</h3><p>Support for skin and related concerns.</p><span>Request a visit <i class="fa-solid fa-arrow-right" aria-hidden="true"></i></span></a>
  </div>
 </section>
 <section class="container portal-section"><div><h2>Your next visit, without the back-and-forth.</h2><p>Already have an account? Check your appointment requests and read updates from your doctor.</p></div><a class="btn btn-outline-primary" href="view_appointment.jsp">View my appointments <i class="fa-solid fa-arrow-right ms-2" aria-hidden="true"></i></a></section>
</main>
<%@include file="component/footer.jsp"%>
</body></html>
