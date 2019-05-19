<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <c:url value="/css/bootstrap.min.css" var="cssURL"/>
    <link href="${cssURL}" rel="stylesheet" type="text/css">
    <c:url value="/js/bootstrap.bundle.min.js" var="jsURL"/>
    <script src="${jsURL}" type="text/javascript"></script>
    <c:url value="/login.html" var="loginActionURL"/>
    <c:url value="/logout.html" var="logoutActionURL"/>
    <c:url value="/index.html" var="titleActionURL"/>
    <c:url value="/register.html" var="registerActionURL"/>
    <fmt:setLocale value="${sessionLang}"/>
    <fmt:setBundle basename="by.training.finaltask.resource.localization"/>
    <title><fmt:message key="login"/></title>
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
                    <li class="nav-item">
                        <a class="nav-link" href="${jspDirectory}/user/profile.html">${username}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${logoutActionURL}"><fmt:message key="logout"/></a>
                    </li>
                </c:if>
                <c:if test="${empty authorizedUser}">
                    <li class="nav-item" >
                        <a class="nav-link active" href="${loginActionURL}"><fmt:message key="login"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${registerActionURL}"><fmt:message key="register"/></a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<!-- TODO: Bind with session and with resource service interface -->
<form action="${loginActionURL}" method="post" >
    <select name="lang" class="custom-select-sm float-right" >
        <option value ="${sessionLang}" ><fmt:message key="pickLanguage"/></option>
        <option value="en_US"><fmt:message key="english"/></option>
        <option value="ru_RU"><fmt:message key="russian"/></option>
        <option value="de_DE"><fmt:message key="german"/></option>
    </select>
    <button class="btn float-right" type="submit"><fmt:message key="changeLanguage"/></button>
</form>
<br>
<form action="${loginActionURL}" method="post">
    <legend><center><h2><b><fmt:message key="login"/> </b></h2></center></legend><br>
    <div class="form-group" >
    <label><fmt:message key="username"/></label>
    <input type="text" class="form-control" name="login" placeholder="Enter username" value="${param.login}">
    </div>
    <div class="form-group">
    <label for="exampleInputPassword1"><fmt:message key="password"/></label>
    <input type="password" name="password" class="form-control" id="exampleInputPassword1"
           placeholder="Password" aria-describedby="passwordHelp" value="">
    </div>
    <button type="submit" class="btn btn-primary"><fmt:message key="login"/> </button>
    <br>
    <c:if test="${not empty message}">
    <center>
            <label class="text" for="navbarResponsive"><fmt:message key="${message}"/></label>
        </center>
    </c:if>
</form>
</body>
</html>
