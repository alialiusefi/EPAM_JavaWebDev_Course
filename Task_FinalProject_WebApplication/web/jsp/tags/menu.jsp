<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionLang}"/>
<fmt:setBundle basename="by.training.finaltask.resource.localization"/>
<c:url value="/login.html" var="loginActionURL"/>
<c:url value="/logout.html" var="logoutActionURL"/>
<c:url value="/index.html" var="titleActionURL"/>
<c:url value="/register.html" var="registerActionURL"/>
<c:url value="/user/profile.html" var="profileActionURL"/>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
    <div class="container">
        <a class="navbar-brand" href="${titleActionURL}">
            <fmt:message key="title"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item ${pageContext.request.requestURI eq '/index.jsp' ? 'active' : ''}">
                    <a class="nav-link" href="${titleActionURL}">
                        <fmt:message key="home"/></a>
                    <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item ${pageContext.request.requestURI eq '/jsp/pets/findpet.jsp' ? 'active' : ''}">
                    <a class="nav-link" href="<c:url value="/pets/findpet.html?page=1"/>"><fmt:message key="findAPet"/></a><span class="sr-only">(current)</span></a>
                </li>
                <c:if test="${not empty authorizedUser}">
                    <c:choose>
                        <c:when test="${authorizedUser.userRole == 'ADMINISTRATOR'}">
                            <li class="nav-item ${pageContext.request.requestURI eq '/jsp/user/admin/findstaff.jsp' ? 'active' : ''}">
                                <a class="nav-link" href="<c:url value="/user/admin/findstaff.html?page=1"/>">
                                    <fmt:message key="findStaff"/></a>
                            </li>
                            <li class="nav-item ${pageContext.request.requestURI eq '/jsp/user/admin/addstaff.jsp' ? 'active' : ''}">
                                <a class="nav-link" href="<c:url value="/user/admin/addstaff.html"/>">
                                    <fmt:message key="addStaff"/></a>
                            </li>
                        </c:when>
                        <c:when test="${authorizedUser.userRole == 'GUEST'}">
                            <li class="nav-item">
                                <a class="nav-link" href="${jspDirectory}/adoptions/guest/adoptpet.html"><fmt:message key="adoptAPet"/><span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:when test="${authorizedUser.userRole == 'STAFF'}">
                            <li class="nav-item ${pageContext.request.requestURI eq '/jsp/pets/staff/addpet.jsp' ? 'active' : ''}">
                                <a class="nav-link" href="<c:url value="/pets/staff/addpet.html"/>">
                                    <fmt:message key="addPet"/><span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                    </c:choose>
                    <li class="nav-item ${pageContext.request.requestURI eq '/jsp/user/profile.jsp' ? 'active' : ''}">
                        <a class="nav-link" href="${profileActionURL}">
                            <fmt:message key="profile"/>(${authorizedUser.username})</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${logoutActionURL}"><fmt:message key="logout"/></a>
                    </li>
                </c:if>
                <c:if test="${empty authorizedUser}">
                    <li class="nav-item ${pageContext.request.requestURI eq '/jsp/login.jsp' ? 'active' : ''}" >
                        <a class="nav-link" href="${loginActionURL}"><fmt:message key="login"/></a>
                    </li>
                    <li class="nav-item ${pageContext.request.requestURI eq '/jsp/register.jsp' ? 'active' : ''}">
                        <a class="nav-link" href="${registerActionURL}"><fmt:message key="register"/></a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
${exception}