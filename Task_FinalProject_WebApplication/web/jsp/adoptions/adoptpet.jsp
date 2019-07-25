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
    <title><fmt:message key="adoptAPet"/></title>
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <script src="js/popper.min.js"></script>
    <script src="/js/formscript.js"></script>
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
<center>
    <p class="h2"><b><fmt:message key="adoptAPet"/></b></p>
</center>
<c:if test="${not empty message}">
    <center>
        <label class="text text-danger"><fmt:message key="${message}"/></label>
    </center>
</c:if>

<br>
<div class="table" style="margin:auto; width: 70% !important;">
    <label class="text-center"><fmt:message key="petInfo"/></label>
    <table class="table align-content-center">
        <tbody>
        <tr>
            <td><img class="card-img-top"
                     src="data:image/jpeg;base64,${petPicture}" width="300" height="300"/>
            </td>
            <td><fmt:message key="petName"/>: ${pet.name}</td>
            <td>
                <form style="margin: auto; width: 80% !important;"
                      action="<c:url value="/pets/moreinfopet.html"/>" method="post">
                <input type="hidden" name="petID" value="${pet.id}">
                <input type="submit" value="<fmt:message key="moreInfo"/>"
                       class="btn-sm btn-primary">
            </form>
            </td>
        </tr>
        <tr>
            <td>
                <fmt:message key="weight"/>: ${pet.weight}
            </td>
            <td>
                <fmt:message key="dateofbirth"/>:
                <fmt:formatDate type="date" dateStyle="medium"
                                value="${pet.dateOfBirth.time}"/>
            </td>
            <td>
                <fmt:message key="dateSheltered"/>:
                <fmt:formatDate type="date" dateStyle="medium"
                                value="${pet.dateSheltered.time}"/>
            </td>
        </tr>
        <th>
            <label class="text-center"><fmt:message key="adoptionForm"/></label>
        </th>
        <tr>
            <form name="adoptionForm" action="<c:url value="/adoptions/adoptpet.html"/>" method="post">
                <input type="hidden" name="petID" value="${petID}">
                <td>
                    <label><fmt:message key="adoptionStart"/> :</label>
                    <input type="date" name="adoptionStart"/>
                </td>
                <td>
                    <label><fmt:message key="adoptionEnd"/> :</label>
                    <input type="date" name="adoptionEnd" id="adoptionEndID" disabled/>
                </td>
                <td>
                <input type="checkbox" name="adoptForTime" id="adoptForTimeID"
                       onclick="toggle_adoptionEnd()"/>
                <label><fmt:message key="adoptForTime"/></label>
                </td>
                <td>
                    <input type="submit" class="btn btn-primary"
                           value="<fmt:message key="adopt"/>">
                </td>
            </form>
        </tr>

        </tbody>
    </table>

</div>



<jsp:include page="/jsp/tags/footer.jsp" flush="true"/>
</body>
</html>
