<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Login</title>
    <c:url value="/css/bootstrap.min.css" var="cssURL"/>
    <link href="${cssURL}" rel="stylesheet" type="text/css">
    <c:url value="/js/bootstrap.bundle.min.js" var="jsURL"/>
    <script src="${jsURL}" type="text/javascript"></script>
    <c:url value="/login.html" var="loginActionURL"/>
</head>
<body>
<!-- MenuItem -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
    <div class="container">
        <a class="navbar-brand" href="${jspDirectory}/index.html">Ali's Pet Shelter</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${jspDirectory}/index.html">Home<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${jspDirectory}/find/viewpets.html">Find a Pet<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${jspDirectory}/find/adoptpets.html">Adopt a Pet<span class="sr-only">(current)</span></a>
                </li>
                <c:if test="${not empty authorizedUser}">
                    <li class="nav-item">
                        <a class="nav-link" href="${jspDirectory}/user/profile.html">${username}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${jspDirectory}/logout.html">Logout</a>
                    </li>
                </c:if>
                <c:if test="${empty authorizedUser}">
                <li class="nav-item active" >
                    <a class="nav-link" href="${jspDirectory}/login.html">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${jspDirectory}/register.html">Register</a>
                </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<br>
<!-- TODO: Bind with session and with resource serviceinterface -->
<select class="custom-select" >
    <option selected>Pick a language</option>
    <option value="1">English</option>
    <option value="2">Russian</option>
    <option value="3">Polish</option>
</select>
<br>
<form action="${loginActionURL}" method="post">
    <div class="form-group" >
    <label>Username</label>
    <input type="text" class="form-control" name="login" placeholder="Enter username" value="${param.login}">
    </div>
    <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" name="password" class="form-control" id="exampleInputPassword1"
           placeholder="Password" aria-describedby="passwordHelp" value="">
    </div>
    <button type="submit" class="btn btn-primary">Login</button>
    <br>
    <center>
    <label class="text" for="navbarResponsive">${message}</label>
    </center>
</form>
</body>
</html>
