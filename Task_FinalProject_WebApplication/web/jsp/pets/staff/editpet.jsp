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
    <title><fmt:message key="editPet"/></title>
</head>
<body>
<jsp:include page="/jsp/tags/menu.jsp" flush="true"/>
<form action="<c:url value="/pets/staff/editpet.html"/>" method="post">
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
    <form action="" enctype="multipart/form-data" method="post">
        <fieldset>
            <legend>
                <center><h2><b><fmt:message key="editPet"/> </b></h2></center>
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
        <div class="container">
            <label>
                <fmt:message key="currentPetPicture"/>:
            </label>
            <img alt="pic" width="300" height="200"
                 src="data:image/jpeg;base64,${currentPetPicture}"/><br>
            <label>
                <fmt:message key="uploadPetPicture"/>:
            </label>
            <input type="file" name="petPicture">
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label>
                    <fmt:message key="petName"/>:
                </label>
                <input name="petName"
                       type="text"
                       value="${pet.name}"
                       class="form-control"
                       placeholder="<fmt:message key="petName"/>">
            </div>
            <div class="form-group col-md-6">
                <label><fmt:message key="weight"/>:</label>
                <input name="petWeight" value="${pet.weight}"
                       type="text" class="form-control" placeholder="6.9">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label>
                    <fmt:message key="currentDateOfBirth"/>:
                </label>
                <input name="dateOfBirth" value="<fmt:formatDate pattern="YYYY-MM-dd" type="date"
                               value="${pet.dateOfBirth.time}"/>" type="text"
                       class="form-control"/>
            </div>
            <div class="form-group col-md-6">
                <label>
                    <fmt:message key="currentDateSheltered"/>:
                </label>
                <input name="dateOfBirth" value="<fmt:formatDate pattern="YYYY-MM-dd" type="date"
                               value="${pet.dateSheltered.time}"/>" type="text"
                       class="form-control"/>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputShelter">
                    <fmt:message key="shelter"/>:
                </label>
                <select name="shelter" id="inputShelter" class="form-control">
                    <c:forEach items="${shelterList}" var="i">
                        <c:choose>
                            <c:when test="${i.id == pet.shelterID}">
                                <option selected value="${i.id}">${i.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${i.id}">${i.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label for="inputBreed">
                    <fmt:message key="breed"/>:
                </label>
                <select name="breed" id="inputBreed" class="form-control">
                    <c:forEach items="${breedList}" var="q">
                        <c:choose>
                            <c:when test="${q.id == pet.breedID}">
                                <option selected value="${q.id}">${q.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${q.id}">${q.name}</option>
                            </c:otherwise>

                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-2">
                <label for="inputStatus">
                    <fmt:message key="petStatus"/>:
                </label>
                    <select name="petStatus" id="inputStatus" class="form-control">
                    <option ${pet.status == 'SHELTERED' ? 'selected=selected' : ''} value="SHELTERED">
                        <fmt:message key="sheltered"/>
                    </option>
                    <option ${pet.status == 'ADOPTED' ? 'selected=selected' : ''} value="ADOPTED">
                        <fmt:message key="adopted"/>
                    </option>
                    <option ${pet.status == 'DEAD' ? 'selected=selected' : ''} value="DEAD">
                        <fmt:message key="dead"/>
                    </option>
                </select>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">
            <fmt:message key="editPet"/></button>
    </form>
</div>
<jsp:include page="/jsp/tags/footer.jsp" flush="true"/>
</body>
</html>
