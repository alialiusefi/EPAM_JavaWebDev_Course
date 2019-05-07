<%--
  Created by IntelliJ IDEA.
  User: Cyber
  Date: 5/7/2019
  Time: 12:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Login</title>
    <c:url value="/css/bootstrap.min.css" var="cssURL"/>
    <link href="${cssURL}" rel="stylesheet" type="text/css">
    <c:url value="/js/bootstrap.bundle.min.js" var="jsURL"/>
    <script src="${jsURL}" type="text/javascript"></script>
    <c:url value="/jsp/" var="jspDirectory"/>
</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
    <div class="container">
        <a class="navbar-brand" href="/web">Ali's Pet Shelter</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/web">Home<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${jspDirectory}/find/viewpets.jsp">Find a Pet<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${jspDirectory}/find/adoptpets.jsp">Adopt a Pet<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="${jspDirectory}/login.jsp">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${jspDirectory}/register">Register</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<br>
<!-- TODO: Bind with session and with resource interface -->
<select class="custom-select" >
    <option selected>Pick a language</option>
    <option value="1">English</option>
    <option value="2">Russian</option>
    <option value="3">Deustch</option>
</select>
<br>

<form action="${jspDirectory}/login.jsp" method="post">
<div class="form-group" >
    <label for="exampleInputEmail1">Email address</label>
    <input type="email" class="form-control" id="exampleInputEmail1"
           aria-describedby="emailHelp" placeholder="Enter email" value="${param.login}">
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
</div>
<div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" aria-describedby="passwordHelp">
</div>
<button type="submit" class="btn btn-primary">Login</button>
</form>


</body>
</html>
