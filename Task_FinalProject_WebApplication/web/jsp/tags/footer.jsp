<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
<%@ taglib prefix = "dt" uri = "tld/livedate.tld"%>
--%>
<fmt:setLocale value="${sessionLang}"/>
<fmt:setBundle basename="by.training.finaltask.resource.localization"/>
<!-- Footer -->
<footer class="bg-dark text-white mt-4">
    <div class="container-fluid py-3 px-xl-5">
        <div class="row">
            <div class="col-md-3">
                <h5><fmt:message key="title"/></h5></div>
            <div class="col-md-3"></div>
            <div class="col-md-3"></div>
            <div class="col-md-3"></div>
        </div>
        <div class="row">
            <div class="col-md-6"><fmt:message key="createdBy"/>
                <a class="small text-white" href="https://github.com/alialiusefi/"><br>
                Github
                    </a>
                <a class="small text-white" href="https://vk.com/id278491843">
                    VK
                </a>
            </div>
            <div class="col-md-3"></div>
<%--
            <dt:DateTag/>
--%>
            <div class="col-md-3 text-right small align-self-end">
                ©2019 PetShelter, Inc.
            </div>
        </div>
    </div>
</footer>
<!-- Footer -->