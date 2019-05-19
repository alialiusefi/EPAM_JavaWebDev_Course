<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <c:url value="/index.html" var="indexActionURL"/>
  <c:url value="/login.html" var="loginActionURL"/>
  <c:url value="/register.html" var="registerActionURL"/>
  <c:url value="/logout.html" var="logoutActionURL"/>
  <fmt:setLocale value="${sessionLang}"/>
  <fmt:setBundle basename="by.training.finaltask.resource.localization"/>
  <title><fmt:message key="title"/></title>
  <!-- Bootstrap core CSS -->
  <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<script src="js/popper.min.js"></script>
<!-- MenuItem -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
  <div class="container">

    <a class="navbar-brand" href="${indexActionURL}">
      <fmt:message key="title"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item active">
          <a class="nav-link" href="${indexActionURL}">
            <fmt:message key="home"/></a>
          <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/pets/findpet.html"><fmt:message key="findAPet"/></a><span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/adoptions/guest/adoptpet.html"><fmt:message key="adoptAPet"/><span class="sr-only">(current)</span></a>
        </li>
        <c:if test="${not empty authorizedUser}">
          <li class="nav-item">
            <a class="nav-link" href="/user/profile.html">${username}</a>
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
            <a class="nav-link" href="${registerActionURL}"><fmt:message key="register"/></a>
          </li>
        </c:if>
      </ul>
    </div>
  </div>
</nav>
<!-- TODO: Bind with session and with resource serviceinterface -->
<form action="${indexActionURL}" method="post" >
  <select name="lang" class="custom-select-sm float-right" >
    <option value ="${sessionLang}" ><fmt:message key="pickLanguage"/></option>
    <option value="en_US"><fmt:message key="english"/></option>
    <option value="ru_RU"><fmt:message key="russian"/></option>
    <option value="de_DE"><fmt:message key="german"/></option>
  </select>
  <button class="btn float-right" type="submit"><fmt:message key="changeLanguage"/></button>
</form>

<br>

<!-- Page Content -->
<center>
  <img src="img/title1.jpg" height="600" width="1000" class="img-fluid" alt="Title Image">
</center>

<div class="container">
  <div class="row">
    <div class="col-lg-12 text-center">
      <h2>
        <fmt:message key="whyAdopt"/>
      </h2>
      <p class="lead"><fmt:message key="didyouknow"/></p>

    </div>
  </div>
</div>

<!-- Bootstrap core JavaScript -->
<script src="/js/bootstrap.bundle.min.js"></script>


</body>

</html>

