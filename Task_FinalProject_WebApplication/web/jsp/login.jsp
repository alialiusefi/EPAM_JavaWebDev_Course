<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <c:url value="/css/bootstrap.min.css" var="cssURL"/>
    <link href="${cssURL}" rel="stylesheet" type="text/css">
    <c:url value="/js/bootstrap.bundle.min.js" var="jsURL"/>
    <script src="${jsURL}" type="text/javascript"></script>
    <fmt:setLocale value="${sessionLang}"/>
    <fmt:setBundle basename="by.training.finaltask.resource.localization"/>
    <title><fmt:message key="login"/></title>
</head>
<body>
<jsp:include page="/jsp/tags/menu.jsp" flush="true"/>
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
<jsp:include page="/jsp/tags/footer.jsp" flush="true"/>

</body>
</html>
