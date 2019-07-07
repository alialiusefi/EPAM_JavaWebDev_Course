<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <fmt:setLocale value="${sessionLang}"/>
    <fmt:setBundle basename="by.training.finaltask.resource.localization"/>
    <title><fmt:message key="findStaff"/></title>
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <script src="js/popper.min.js"></script>

</head>
<body>
<jsp:include page="/jsp/tags/menu.jsp" flush="true"/>

<form action="<c:url value="/user/admin/findstaff.html?page=1"/>" method="post">
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
    <center><h2><b><fmt:message key="findStaff"/> </b></h2></center>
</legend>
<br>
<div class="table">
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th><fmt:message key="username"/></th>
            <th><fmt:message key="role"/></th>
            <th><fmt:message key="email"/></th>
            <th><fmt:message key="firstName"/></th>
            <th><fmt:message key="lastName"/></th>
            <th><fmt:message key="dateofbirth"/></th>
            <th><fmt:message key="address"/></th>
            <th><fmt:message key="contactNumber"/></th>
            <th><fmt:message key="actions"/> </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${resultUsers}" var="users" varStatus="status">
            <tr>
            <td><c:out value="${users.id}"/></td>
            <td><c:out value="${users.username}"/></td>
                <td><c:out value="${users.userRole.getName()}"/></td>
            <td><c:out value="${resultsUserInfo[status.index].email}"/></td>
            <td><c:out value="${resultsUserInfo[status.index].firstName}"/></td>
            <td><c:out value="${resultsUserInfo[status.index].lastName}"/></td>
            <td><fmt:formatDate type="date" dateStyle="medium"
                                value="${resultsUserInfo[status.index].dateOfBirth.time}"/>
            </td>
            <td><c:out value="${resultsUserInfo[status.index].address}"/></td>
            <td>+<c:out value="${resultsUserInfo[status.index].phone}"/></td>
                <td><form action="" method="POST">
                    <select>
                        <option value="/user/useredit.html">Edit Staff</option>
                        <option value="/user/userdelete.html">Delete Staff</option>
                    </select>
                    <input type="submit" value="Apply">
                </form></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${param.page > 1}">
            <li class="page-item"><a class="page-link" href="<c:url value="/user/admin/findstaff.html?page=${param.page - 1}"/>">Previous</a></li>
            </c:if>
            <c:forEach var="i" begin="1" end="${amountOfPages}">
            <li class="page-item"><a class="page-link" href="<c:url value="/user/admin/findstaff.html?page=${i}"/>">
                <c:out value="${i}"/>
            </a></li>
            </c:forEach>
            <c:if test="${param.page < amountOfPages}">
                <li class="page-item"><a class="page-link" href="<c:url value="/user/admin/findstaff.html?page=${param.page + 1}"/>">Next</a></li>
            </c:if>
        </ul>
    </nav>
<jsp:include page="/jsp/tags/footer.jsp" flush="true"/>

</body>
</html>
