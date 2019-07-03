<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <c:url value="/css/bootstrap.min.css" var="cssURL"/>
    <link href="${cssURL}" rel="stylesheet" type="text/css">
    <script src="${jsURL}" type="text/javascript"></script>
    <c:url value="/js/bootstrap.bundle.min.js" var="jsURL"/>
    <c:url value="/login.html" var="loginActionURL"/>
    <c:url value="/logout.html" var="logoutActionURL"/>
    <c:url value="/index.html" var="titleActionURL"/>
    <c:url value="/register.html" var="registerActionURL"/>
    <c:url value="/user/profile.html" var="profileActionURL"/>
    <fmt:setLocale value="${sessionLang}"/>
    <fmt:setBundle basename="by.training.finaltask.resource.localization"/>
    <title><fmt:message key="editProfile"/></title>
</head>
<body>
<!-- MenuItem -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
    <div class="container">
        <a class="navbar-brand" href="${titleActionURL}">
            <fmt:message key="title"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${titleActionURL}">
                        <fmt:message key="home"/></a>
                    <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${jspDirectory}/pets/findpet.html"><fmt:message key="findAPet"/></a><span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${jspDirectory}/adoptions/guest/adoptpet.html"><fmt:message key="adoptAPet"/><span class="sr-only">(current)</span></a>
                </li>
                <c:if test="${not empty authorizedUser}">
                    <c:choose>
                        <c:when test="${authorizedUser.userRole == 'ADMINISTRATOR'}">
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/user/admin/findstaff.html"/>">
                                    <fmt:message key="findStaff"/></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/user/admin/addstaff.html"/>">
                                    <fmt:message key="addStaff"/></a>
                            </li>
                        </c:when>
                    </c:choose>
                    <li class="nav-item">
                        <a class="nav-link" href="${profileActionURL}">
                            <fmt:message key="profile"/>(${authorizedUser.username})</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${logoutActionURL}"><fmt:message key="logout"/></a>
                    </li>
                </c:if>
                <c:if test="${empty authorizedUser}">
                    <li class="nav-item" >
                        <a class="nav-link" href="${loginActionURL}"><fmt:message key="login"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="${registerActionURL}"><fmt:message key="register"/></a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<form action="<c:url value="/user/useredit.html" var="profileActionURL"/>" method="post" >
    <select name="lang" class="custom-select-sm float-right" >
        <option value ="${sessionLang}" ><fmt:message key="pickLanguage"/></option>
        <option value="en_US"><fmt:message key="english"/></option>
        <option value="ru_RU"><fmt:message key="russian"/></option>
        <option value="de_DE"><fmt:message key="german"/></option>
    </select>
    <button class="btn float-right" type="submit"><fmt:message key="changeLanguage"/></button>
</form>
<br>

<div class="container">
    <form class="form" action=" " method="post"  id="contact_form">
        <fieldset>
            <!-- Form Name -->
            <legend><center><h2><b><fmt:message key="editProfile"/> </b></h2></center></legend><br>
            <c:if test="${not empty message}" >
                <div class="text-center text-warning">
                    <p>Attention: <fmt:message key="${message}"/></p>
                </div>
            </c:if>
            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-6 control-label"><fmt:message key="firstName"/></label>
                <div class="col-md-6 inputGroupContainer">
                    <div class="input-group">
                        <input name="firstname" placeholder="<fmt:message key="firstName"/>"
                               class="form-control" value="${authorizedUserInfo.firstName}" type="text">
                    </div>
                </div>
            </div>
            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-6 control-label" ><fmt:message key="lastName"/> </label>
                <div class="col-md-6 inputGroupContainer">
                    <div class="input-group">
                        <input name="lastname" placeholder="<fmt:message key="lastName"/>"
                               class="form-control" value="${authorizedUserInfo.lastName}" type="text">
                    </div>
                </div>
            </div>
            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-6 control-label"><fmt:message key="username"/></label>
                <div class="col-md-6 inputGroupContainer">
                    <div class="input-group">
                        <input name="username" placeholder="<fmt:message key="username"/>"
                               class="form-control" value="${authorizedUser.username}" type="text">
                    </div>
                </div>
            </div>

            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-6 control-label" ><fmt:message key="password"/></label>
                <div class="col-md-6 inputGroupContainer">
                    <div class="input-group">
                        <input name="password" placeholder="<fmt:message key="password"/>" class="form-control"  type="password">
                    </div>
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-6 control-label"><fmt:message key="email"/></label>
                <div class="col-md-6 inputGroupContainer">
                    <div class="input-group">
                        <input name="email" placeholder="<fmt:message key="email"/>"
                               class="form-control" value="${authorizedUserInfo.email}" type="text">
                    </div>
                </div>
            </div>


            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-6 control-label"><fmt:message key="contactNumber"/></label>
                <div class="col-md-6 inputGroupContainer">
                    <div class="input-group">
                        <input name="contactnumber" placeholder="<fmt:message key="contactNumberFormat"/>"
                               class="form-control" value="+${authorizedUserInfo.phone}"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-6 control-label"><fmt:message key="address"/></label>
                <div class="col-md-6 inputGroupContainer">
                    <div class="input-group">
                        <input name="address" placeholder="<fmt:message key="address"/>"
                               class="form-control" value="${authorizedUserInfo.address}" type="text">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-6 control-label">
                    ${authorizedUserInfo.firstName}
                    <fmt:message key="dateofbirth"/>:
                    <fmt:formatDate type="date" dateStyle="long" value="${authorizedUserInfo.dateOfBirth.time}"/></label>
                <label class="col-md-6 control-label"><fmt:message key="dateofbirth"/></label>
                <div class="col-md-6 inputGroupContainer">
                    <div class="input-group">
                        <input name="dateofbirth"
                               class="form-control" placeholder="YYYY-MM-dd" value="<fmt:formatDate pattern="YYYY-MM-dd" type="date"
                               value="${authorizedUserInfo.dateOfBirth.time}"/>" type="text" max="${java.time.LocalDate.now()}"/>
                    </div>
                </div>
            </div>
            <!-- Button -->
            <div class="form-group">
                <label class="col-md-6 control-label"></label>
                <div class="col-md-6"><br>
                    <button type="submit" class="btn btn-primary" ><fmt:message key="updateProfile"/></button>
                </div>
            </div>

        </fieldset>
    </form>
</div>


</body>
</html>