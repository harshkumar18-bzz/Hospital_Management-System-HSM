package com.web;

import jakarta.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/appointmentState", "/publishSlot", "/withdrawSlot"})
public final class SchedulingActions extends ActionController {}
