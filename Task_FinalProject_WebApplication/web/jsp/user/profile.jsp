<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <c:url value="/user/useredit.html" var="userEditActionURL"/>
    <c:url value="/user/userdelete.html" var="userDeleteActionURL"/>
    <fmt:setLocale value="${sessionLang}"/>
    <fmt:setBundle basename="by.training.finaltask.resource.localization"/>
    <title><fmt:message key="title"/></title>
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <script src="js/popper.min.js"></script>
</head>

<body>

<jsp:include page="/jsp/tags/menu.jsp" flush="true"/>
<form action="${profileActionURL}" method="post">
    <select name="lang" class="custom-select-sm float-right">
        <option value="${sessionLang}"><fmt:message key="pickLanguage"/></option>
        <option value="en_US"><fmt:message key="english"/></option>
        <option value="ru_RU"><fmt:message key="russian"/></option>
        <option value="de_DE"><fmt:message key="german"/></option>
    </select>
    <button class="btn float-right" type="submit"><fmt:message key="changeLanguage"/></button>
</form>

<br>
<c:if test="${not empty message}">
    <center>
        <label class="text text-danger" for="navbarResponsive"><fmt:message key="${message}"/></label>
    </center>
</c:if>

<legend>
    <center><h2><b><fmt:message key="profile"/> </b></h2></center>
</legend>
<br>

<div class="table">

<label class="text-center"><fmt:message key="userCredentials"/></label>
<table class="table align-content-center">
    <tbody>
    <tr>
        <td><fmt:message key="username"/>: ${user.username}</td>
        <td><fmt:message key="role"/>: ${user.userRole.getName()}</td>
    </tr>
    </tbody>
</table>
</div>

<div class="table">
<label class="text-center"><fmt:message key="userInfo"/></label>
<table class="table align-content-center ">
    <tr>
        <td><fmt:message key="firstName"/>: ${userinfo.firstName}</td>
        <td><fmt:message key="lastName"/>: ${userinfo.lastName}</td>
    </tr>
    <tr>
        <td><fmt:message key="email"/>: ${userinfo.email}</td>
        <td><fmt:message key="contactNumber"/>: +${userinfo.phone}</td>
    </tr>
    <tr>
        <td><fmt:message key="dateofbirth"/>:
<fmt:formatDate type="date" dateStyle="medium" value="${userinfo.dateOfBirth.time}"/>
        </td>
        <td><fmt:message key="address"/>: ${userinfo.address}</td>
    </tr>
</table>
</div>
<div class="table">
<label class="text-center"><fmt:message key="userActions"/></label>
    <table class="table align-content-center">
        <tr>
            <td>
                <a class="btn btn-secondary" href="${userEditActionURL}">
                    <fmt:message key="editProfile"/>
                </a>
            </td>
            <c:if test="${authorizedUser.userRole != 'STAFF'}">
                <td>
                    <form action="${userDeleteActionURL}" type="post">
                        <input type="hidden" name="userToDelete" value="${authorizedUser.id}">
                        <input type="submit" class="btn btn-secondary" value="<fmt:message key="deleteProfile"/>">
                    </form>
                </td>
            </c:if>


        </tr>
    </table>
</div>

<jsp:include page="/jsp/tags/footer.jsp" />
</body>
</html>
