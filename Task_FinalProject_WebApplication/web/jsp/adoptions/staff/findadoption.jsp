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
    <title><fmt:message key="allAdoptions"/></title>
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <script src="js/popper.min.js"></script>
</head>

<body>
<jsp:include page="/jsp/tags/menu.jsp" flush="true"/>
<form action="" method="post">
    <select name="lang" class="custom-select-sm float-right">
        <option value="${sessionLang}"><fmt:message key="pickLanguage"/></option>
        <option value="en_US"><fmt:message key="english"/></option>
        <option value="ru_RU"><fmt:message key="russian"/></option>
        <option value="de_DE"><fmt:message key="german"/></option>
    </select>
    <button class="btn float-right" type="submit"><fmt:message key="changeLanguage"/></button>
</form>

<br>

<legend>
    <center><h2><b><fmt:message key="allAdoptions"/> </b></h2></center>
</legend>
<c:if test="${not empty message}">
    <center>
        <label class="text text-danger">
            <fmt:message key="${message}"/>
        </label>
    </center>
</c:if>

<br>
<%--<div class="table mx-auto">
    <table>
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
            <th><fmt:message key="actions"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${resultUsers}" var="users" varStatus="i">
            <tr>
                <td><c:out value="${users.id}"/></td>
                <td><c:out value="${users.username}"/></td>
                <td><c:out value="${users.userRole.getName()}"/></td>
                <td><c:out value="${resultsUserInfo[i.index].email}"/></td>
                <td><c:out value="${resultsUserInfo[i.index].firstName}"/></td>
                <td><c:out value="${resultsUserInfo[i.index].lastName}"/></td>
                <td><fmt:formatDate type="date" dateStyle="medium"
                                    value="${resultsUserInfo[i.index].dateOfBirth.time}"/>
                </td>
                <td><c:out value="${resultsUserInfo[i.index].address}"/></td>
                <td>+<c:out value="${resultsUserInfo[i.index].phone}"/></td>
                <td>
                    <form action="<c:url value="/user/userdelete.html"/>"
                          method="post">
                        <input type="hidden" name="userToDelete" value="${users.id}">
                        <input type="submit" value="<fmt:message key="deleteUser"/>">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>--%>
<%--
<c:out value="${param.page}"/>
--%>
<nav aria-label="Page navigation">
    <ul class="pagination">
        <c:if test="${param.page > 1}">
            <li class="page-item">
                <c:choose>
                    <c:when test="${not empty searchParameter}">
                        <a class="page-link" href="<c:url value="${paginationURL += '?page=' += (param.page - 1)
                    += '&' += 'search=' += searchParameter}"/>">
                            Previous</a>
                    </c:when>
                    <c:otherwise>
                        <a class="page-link" href="<c:url value="${paginationURL += '?page=' += (param.page - 1)}"/>">
                            Previous</a>
                    </c:otherwise>
                </c:choose>
            </li>
        </c:if>
        <c:forEach var="i" begin="1" end="${amountOfPages}">
            <c:choose>
                <c:when test="${not empty searchParameter}">
                    <a class="page-link" href="<c:url value="${paginationURL += '?page=' += i
                    += '&' += 'search=' += searchParameter}"/>">
                        <c:out value="${i}"/></a>
                </c:when>
                <c:otherwise>
                    <a class="page-link" href="<c:url value="${paginationURL += '?page=' += i}"/>">
                        <c:out value="${i}"/></a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${param.page < amountOfPages}">
            <li class="page-item">
                <c:choose>
                    <c:when test="${not empty searchParameter}">
                        <a class="page-link" href="<c:url value="${paginationURL += '?page=' += (param.page + 1)
                    += '&' += 'search=' += searchParameter}"/>">
                            Next</a>
                    </c:when>
                    <c:otherwise>
                        <a class="page-link" href="<c:url value="${paginationURL += '?page=' += (param.page + 1)}"/>">
                            Next</a>
                    </c:otherwise>
                </c:choose>
            </li>
        </c:if>
    </ul>
</nav>

<%--Search--%>


<%--
<div class="table-light ">
    <div class="row">
        <div class="col-md-4">
            &lt;%&ndash;Search by first name&ndash;%&gt;
            <form class="form-inline" method="post" action="<c:url value="/user/admin/findstaffbyfirstname.html?page=1"/>">
                <div class="form-group">
                    <label class="col-form-label"><fmt:message key="searchByFirstName"/></label>
                    <input class="form-control mr-sm-2" name="search"
                           type="search" placeholder="Search" aria-label="Search">
                    <button style="margin-left: 0; z-index: 999;" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </div>
            </form>
        </div>
        <div class="col-md-4">
            &lt;%&ndash;Search by phone&ndash;%&gt;
            <form class="form-inline" method="post" action="<c:url value="/user/admin/findstaffbyphone.html?page=1"/>">
                <div class="form-group">
                    <label class="col-form-label"><fmt:message key="searchByPhone"/></label>
                    <input class="form-control mr-sm-2" name="search" type="search" placeholder="Search" aria-label="Search">
                    <button style="margin-left: 0; z-index: 999;" class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </div>
            </form>
        </div>
        <div class="col-md-4">
            <form action="<c:url value="/user/admin/findstaff.html?page=1"/>" method="post" class="form-inline">
                <div class="form-group">
                    <button style="margin-left: 0; z-index: 999;" class="btn btn-outline-success my-2 my-sm-0" type="submit">
                        <fmt:message key="reset"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
--%>

<jsp:include page="/jsp/tags/footer.jsp" flush="true"/>

</body>
</html>
