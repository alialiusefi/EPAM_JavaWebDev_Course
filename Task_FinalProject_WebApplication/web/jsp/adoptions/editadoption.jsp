<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <title>Add Pet</title>
    <c:url value="/css/bootstrap.min.css" var="cssURL"/>
    <link href="${cssURL}" rel="stylesheet" type="text/css">
    <script src="${jsURL}" type="text/javascript"></script>
    <c:url value="/js/bootstrap.bundle.min.js" var="jsURL"/>
    <c:url value="/login.html" var="loginActionURL"/>
    <c:url value="/logout.html" var="logoutActionURL"/>
    <c:url value="/index.html" var="titleActionURL"/>
    <c:url value="/register.html" var="registerActionURL"/>
    <c:url value="/user/profile.html" var="profileActionURL"/>
    <fmt:setLocale value="${sessionLang}"/>
    <fmt:setBundle basename="by.training.finaltask.resource.localization"/>
    <script src="/js/formscript.js"></script>
    <title><fmt:message key="editAdoption"/></title>
</head>
<body>
<jsp:include page="/jsp/tags/menu.jsp" flush="true"/>
<form action="<c:url value="/adoptions/editadoption.html"/>" method="post">
    <select name="lang" class="custom-select-sm float-right">
        <option value="${sessionLang}"><fmt:message key="pickLanguage"/></option>
        <option value="en_US"><fmt:message key="english"/></option>
        <option value="ru_RU"><fmt:message key="russian"/></option>
        <option value="de_DE"><fmt:message key="german"/></option>
    </select>
    <button class="btn float-right" type="submit"><fmt:message key="changeLanguage"/></button>
</form>
<br>

<div class="container">
    <fieldset>
        <legend>
            <center><h2><b><fmt:message key="editAdoption"/> </b></h2></center>
        </legend>
        <br>
        <c:if test="${not empty successMessage}">
            <div class="text-center text-info">
                <p>Attention: <fmt:message key="${successMessage}"/></p>
            </div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="text-center text-warning">
                <p>Attention: <fmt:message key="${message}"/></p>
            </div>
        </c:if>
    </fieldset>
    <div class="form-inline" style="margin-left: 30%;">
        <div class="form-inline col-md-3">
            <label>
                <fmt:message key="petName"/>:
                ${pet.name}
            </label>
        </div>
            <form name="adoptionForm" action="<c:url value="/adoptions/editadoption.html"/>" method="post">
                <input type="hidden" name="adoptionID" value="${adoptionID}">
                <div class="form-inline p-md-2">

                        <label><fmt:message key="adoptionStart"/>:&emsp;</label>
                        <input type="text" value="<fmt:formatDate pattern="YYYY-MM-dd" type="date"
                               value="${adoption.adoptionStart.time}"/>" placeholder="YYYY-MM-DD"
                               name="adoptionStart"/>

                </div>
                <div class="form-inline p=md-2">
                    <label><fmt:message key="currentAdoptionEnd"/>:&emsp;
                    </label>
                    <c:choose>
                        <c:when test="${not empty adoption.adoptionEnd}">
                            <fmt:formatDate pattern="YYYY-MM-dd" type="date"
                                            value="${adoption.adoptionEnd.time}"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="indefinite"/>
                        </c:otherwise>
                    </c:choose>
                        </div>
                <div class="form-inline p-md-2">
                        <label><fmt:message key="newAdoptionEnd"/>:&emsp;</label>
                        <input type="text"
                               name="adoptionEnd" id="adoptionEndID" disabled/>

                </div>
                <div class="form-inline p-md-2">
                        <input type="checkbox" name="adoptForTime" id="adoptForTimeID"
                               onclick="toggle_adoptionEnd()"/>&emsp;
                        <label><fmt:message key="adoptForTime"/></label>
                        <input type="submit" class="btn btn-primary"
                               value="<fmt:message key="edit"/>">

                </div>
            </form>
        </div>
    </form>
</div>
</div>
<jsp:include page="/jsp/tags/footer.jsp" flush="true"/>
</body>
</html>
