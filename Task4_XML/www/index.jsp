<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MyWebParser</title>
    <style>
        body{background-color: lightgrey;}
        h1{color: black;font-family: Verdana}
        h4{color: black; font-family: Verdana}
    </style>
</head>
<body>
<h1 align="center">XML Parser</h1>

<form name="XML WebParser" method="post" action="parse" >
    <input type="radio" name="parserType" value="sax" checked required> SAX Parser <br>
    <input type="radio" name="parserType" value="stax" required> StAX Parser <br>
    <input type="radio" name="parserType" value="dom"  required> DOM Parser <br>
    <textarea name="xmlText" rows="10" cols="30">xml file in tomcat bin folder</textarea> <br>
    <input type="radio" name="language" value="en_US" checked required> English <br>
    <input type="radio" name="language" value="ru_RU" required> Русский <br>
    <input type="radio" name="language" value="be_BY" required> Белорусский <br>
    <input type="submit" value="Parse!">
    <br>
</form>

<%--<jsp:useBean id="calendar" class="java.util.GregorianCalendar"/>
<form name="Simple" action="timeaction" method="POST">
    <input type="hidden" name="time" value="${calendar.timeInMillis}"/>
    <input type="submit" name="button" value="Посчитать время"/>
</form>--%>



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