<c:if test="${not empty errorMsg}"><div class="alert alert-danger" role="alert"><c:out value="${errorMsg}"/></div><c:remove var="errorMsg" scope="session"/></c:if>
<c:if test="${not empty sucMsg}"><div class="alert alert-success" role="status"><c:out value="${sucMsg}"/></div><c:remove var="sucMsg" scope="session"/></c:if>
<c:if test="${not empty succMsg}"><div class="alert alert-success" role="status"><c:out value="${succMsg}"/></div><c:remove var="succMsg" scope="session"/></c:if>
