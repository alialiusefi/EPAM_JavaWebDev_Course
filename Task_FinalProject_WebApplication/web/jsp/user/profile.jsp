<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <c:url value="" var="jspDirectory"/>
  <title>Ali's Pet Shelter</title>

  <!-- Bootstrap core CSS -->
  <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<!-- MenuItem -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
  <div class="container">
    <a class="navbar-brand" href="${jspDirectory}/index.html">Ali's Pet Shelter</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item active">
          <a class="nav-link" href="${jspDirectory}/index.html">Home<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${jspDirectory}/find/viewpets.html">Find a Pet<span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${jspDirectory}/find/adoptpets.html">Adopt a Pet<span class="sr-only">(current)</span></a>
        </li>
        <c:if test="${!empty sessionScope.authorizedUser} ">
          <li class="nav-item">
            <a class="nav-link" href="${jspDirectory}/login.html">Login</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${jspDirectory}/register.html">Register</a>
          </li>
        </c:if>
      </ul>
    </div>
  </div>
</nav>
<br>
<!-- TODO: Bind with session and with resource serviceinterface -->
<select class="custom-select" >
  <option selected>Pick a language</option>
  <option value="1">English</option>
  <option value="2">Russian</option>
  <option value="3">Deustch</option>
</select>
<br>

<!-- Page Content -->
<center>
  <img src="img/title1.jpg" height="600" width="1000" class="img-fluid" alt="Title Image">
</center>

<div class="container">
  <div class="row">
    <div class="col-lg-12 text-center">
      <h2>
        Why Adopt a Dog or Cat Over Buying?
      </h2>
      <p class="lead">
        Did you know that over 1,000 people per hour run a search right here looking to adopt a pet?
        Pet adoption is quickly becoming the preferred way to find a new dog, puppy, cat or kitten.
        Best of all, there are so many benefits when you adopt a dog or adopt a cat over buying.
        For instance, pet adoption will almost always be more affordable than buying a puppy for sale from a breeder
        or finding a kitten for sale from a litter. There are more benefits as well.
        Since pets in rescues and shelters usually come from a home where the owners ran out of money, got divorced, or had to move, it's common to find that the dogs and cats on our website are already housetrained, good with kids, or do well with other pets. People are finding out that buying a puppy for sale from a breeder isn't all it's cracked up to be, and the dogs and cats don't leave the organization without having their shots and being taken to the vet. That means less stress, and more savings! So what are you waiting for? Go find that perfect pet!
      </p>

    </div>
  </div>
</div>

<!-- Bootstrap core JavaScript -->
<script src="/js/bootstrap.bundle.min.js"></script>


</body>

</html>

