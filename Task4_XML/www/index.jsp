<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello World!</title>

</head>
<body>
<header>HELLO</header>

<jsp:useBean id="calendar" class="java.util.GregorianCalendar"/>
<form name="Simple" action="timeaction" method="POST">
    <input type="hidden" name="time" value="${calendar.timeInMillis}"/>
    <input type="submit" name="button" value="Посчитать время"/>
</form>
<%--

<table>
    <c:forEach var="elem" items="${lst}" varStatus="status">
        <tr>
            <td><c:out value="${ elem }" /></td>
            <td><c:out value="${ elem.id }" /></td>
            <td><c:out value="${ elem.text }" /></td>
            <td><c:out value="${ status.count }" /></td>
        </tr>
    </c:forEach>
</table>
--%>

</body>
</html>