<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/custom_slider.css" />" >

<portlet:renderURL var="getCategoryRender">
	<portlet:param name="page" value="category"/>
</portlet:renderURL>

<div class="container-fluid">
	<!-- <div class="col-xs-12 col-sm-8 col-md-12">
		<h2 class="home-page-section-title text-center">Polecane aukcje</h2>
	</div> -->
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-3">
				<h2 class="text-center">Polecane</h2>
			</div>
			<div class="col-xs-12 col-sm-12 col-md-9">
				<c:forEach items="${electronicAuctions}" var="item">
					<div class="col-xs-12 col-sm-12 col-md-4">
						<a href="#">
							<img src="<c:url value="/images/${item.imageName}" />" height="200" width="100%" />
						</a>
						<h4>Cena - ${item.subjectPrice/100} zł</h4> 
						<strong><h4>${item.name}</h4></strong>
					</div>
				</c:forEach>
			</div>
		</div>
</div>

<!-- Slideshow container -->
<div class="slideshow-container">

  <!-- Full-width slides/quotes -->
  <div class="mySlides">
    <q>I love you the more in that I believe you had liked me for my own sake and for nothing else</q>
    <p class="author">- John Keats</p>
  </div>

  <div class="mySlides">
    <q>But man is not made for defeat. A man can be destroyed but not defeated.</q>
    <p class="author">- Ernest Hemingway</p>
  </div>

  <div class="mySlides">
    <q>I have not failed. I've just found 10,000 ways that won't work.</q>
    <p class="author">- Thomas A. Edison</p>
  </div>

  <!-- Next/prev buttons -->
  <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
  <a class="next" onclick="plusSlides(1)">&#10095;</a>
</div>

<!-- Dots/bullets/indicators -->
<div class="dot-container">
  <span class="dot" onclick="currentSlide(1)"></span> 
  <span class="dot" onclick="currentSlide(2)"></span> 
  <span class="dot" onclick="currentSlide(3)"></span> 
</div> 

<script src="<c:url value="/js/common/custom_slider.js" />"></script>
