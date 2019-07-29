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
<div class="table mx-auto" style="max-width: 90%">
    <table>
        <thead>
        <tr>
            <th><fmt:message key="petName"/></th>
            <th><fmt:message key="adoptedFrom"/></th>
            <th><fmt:message key="adoptedTo"/></th>
            <th><fmt:message key="firstName"/></th>
            <th><fmt:message key="email"/></th>
            <th><fmt:message key="address"/></th>
            <th><fmt:message key="contactNumber"/></th>
            <th><fmt:message key="actions"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${adoptionResults}" var="adoptions" varStatus="i">
            <tr>
                <td><c:out value="${petResults[i.index].name}"/></td>
                <td><fmt:formatDate type="date" dateStyle="medium"
                                    value="${adoptions.adoptionStart.time}"/>
                </td>
                <td><fmt:formatDate type="date" dateStyle="medium"
                                    value="${adoptions.adoptionEnd.time}"/>
                </td>
                <td><c:out value="${userInfoResults[i.index].firstName}"/></td>
                <td><c:out value="${userInfoResults[i.index].email}"/></td>
                <td><c:out value="${userInfoResults[i.index].address}"/></td>
                <td>+<c:out value="${userInfoResults[i.index].phone}"/></td>
                <td>
                    <form action="<c:url value="/adoptions/editadoption.html"/>"
                          method="post">
                        <input type="hidden" name="adoptionID" value="${adoptions.id}">
                        <input type="submit" value="<fmt:message key="edit"/>">
                    </form>
                    <form action="<c:url value="/adoptions/deleteadoption.html"/>"
                          method="post">
                        <input type="hidden" name="adoptionID" value="${adoptions.id}">
                        <input type="submit" value="<fmt:message key="delete"/>">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
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
<div class="container">
    <div class="row">
        <%--Search between 2 dates--%>
        <form method="post" class="form-inline"
              action="<c:url value="/adoptions/staff/findadoptionbetweendates.html?page=1"/>">
            <div class="col">
                <button class="btn btn-outline-success mx-sm-3"
                        type="submit"><fmt:message key="searchBetweenTwoDates"/></button>
                    <input class="form-control" name="dateFrom"
                           type="date" aria-label="Search"/>
                    <input class="form-control" name="dateTo"
                           type="date" aria-label="Search"/>
            </div>
        </form>
        <form method="post" class="form-inline"
              action="<c:url value="/adoptions/staff/findadoptionbypetname.html?page=1"/>">
            <div class="col">
                <input class="form-control mx-lg-3"
                       name="petName" type="search"
                       placeholder="<fmt:message key="petName"/>" aria-label="Search"/>
                <button class="btn btn-outline-success mx-sm-1" type="submit">
                    <fmt:message key="searchByPetName"/></button>
            </div>
        </form>
    </div>
</div>
<%--Search by pet name--%>

</div>
</div>

<jsp:include page="/jsp/tags/footer.jsp" flush="true"/>

</body>
</html>
