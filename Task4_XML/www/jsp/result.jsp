<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head><title>
    Result Page</title>
    <fmt:setBundle basename="by.training.task4.resource.text" scope="session"/>
</head>
<body>
<h1>Results using ${parser}</h1>
<br>
<h2>Diamonds</h2>
<table border="5px"  align="center">
    <c:forEach var="elem" items="${diamonds}" >
        <tr>
            <th><fmt:message key="ID"/></th>
            <td><c:out value="${elem.id}"/></td>
            <th><fmt:message key="Origin"/></th>
            <td><c:out value="${elem.origin}"/></td>
            <th>Name</th>
            <td><c:out value="${elem.content[0]}"/> </td>
            <th>Preciousness</th>
            <td><c:out value="${elem.content[1]}"/> </td>
            <th>Weight(Value)</th>
            <td><c:out value="${elem.content[2].value}"/> </td>
            <th>Weight(Unit)</th>
            <td><c:out value="${elem.content[2].unit}"/> </td>
            <th>Gem Arrival</th>
            <td><c:out value="${elem.content[3]}"/> </td>
            <th>Transparency (0%-100%)</th>
            <td><c:out value="${elem.content[4]}"/> </td>
            <th>Amount Of Cuts </th>
            <td><c:out value="${elem.content[5]}"/> </td>
        </tr>
    </c:forEach>
</table>
<br>
<h2>Emeralds</h2>
<table border="5px"  align="center">
    <c:forEach var="elem" items="${emeralds}" >
        <tr>
            <th>ID</th>
            <td><c:out value="${elem.id}"/></td>
            <th>Origin</th>
            <td><c:out value="${elem.origin}"/></td>
            <th>Name</th>
            <td><c:out value="${elem.content[0]}"/> </td>
            <th>Preciousness</th>
            <td><c:out value="${elem.content[1]}"/> </td>
            <th>Weight(Value)</th>
            <td><c:out value="${elem.content[2].value}"/> </td>
            <th>Weight(Unit)</th>
            <td><c:out value="${elem.content[2].unit}"/> </td>
            <th>Gem Arrival</th>
            <td><c:out value="${elem.content[3]}"/> </td>
            <th>Color</th>
            <td><c:out value="${elem.content[4]}"/> </td>
            <th>Transparency (0%-100%)</th>
            <td><c:out value="${elem.content[5]}"/> </td>
            <th>Amount Of Cuts </th>
            <td><c:out value="${elem.content[6]}"/> </td>
        </tr>
    </c:forEach>
</table>
<br>
<h2>Pearls</h2>
<table border="5px"  align="center">
    <c:forEach var="elem" items="${pearls}" >
        <tr>
            <th>${rb.ID}</th>
            <td><c:out value="${elem.id}"/></td>
            <th>${rb.origin}</th>
            <td><c:out value="${elem.origin}"/></td>
            <th>Name</th>
            <td><c:out value="${elem.content[0]}"/> </td>
            <th>Preciousness</th>
            <td><c:out value="${elem.content[1]}"/> </td>
            <th>Weight(Value)</th>
            <td><c:out value="${elem.content[2].value}"/> </td>
            <th>Weight(Unit)</th>
            <td><c:out value="${elem.content[2].unit}"/> </td>
            <th>Gem Arrival</th>
            <td><c:out value="${elem.content[3]}"/> </td>
            <th>Color</th>
            <td><c:out value="${elem.content[4]}"/> </td>
            <th>Luster</th>
            <td><c:out value="${elem.content[5]}"/> </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>