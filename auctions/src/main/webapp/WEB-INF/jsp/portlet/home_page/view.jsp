<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/custom-btn.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/custom_slider.css" />" >

 <div class="container-fluid">
<%-- 	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-3">
			<div class='dad' style="background-color: mediumSeaGreen;">
			    <span></span>
			    <div class='b'><liferay-ui:message key="most-popular" /></div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-9">
			<div class="slideshow-container">
				<div class="mySlides">
				<div class="row">
				<c:forEach items="${electronicAuctions}" var="item" varStatus="i">
						<portlet:renderURL var="details">
							<portlet:param name="page" value="auctionDetails"/>
							<portlet:param name="id" value="${item.id}"/>
						</portlet:renderURL>
						<div class="col-xs-12 col-sm-6 col-md-4">
							<a href="${details}">
								<img src="<c:url value="/images/${item.imageName}" />" height="200" width="100%" />
							</a>
							<h4>Cena - ${item.subjectPrice/100} zł</h4> 
							<strong><h4>${item.name}</h4></strong>
						</div>
				</c:forEach>
				</div>
				</div>
				<a id="mySlidesPrev" class="prev" >&#10094;</a>
				<a id="mySlidesNext" class="next" >&#10095;</a>
					
			</div>
		</div>
	</div> --%>
<%-- 	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-3">
			<div class='dad' style="background-color: gray;">
			    <span></span>
			    <div class='b'><liferay-ui:message key="recent-added" /></div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-9">
			<div id="newest-id" class="slideshow-container">
				<c:forEach items="${newestAuctions}" var="item" varStatus="i">
						<div class="newest">
							<div class="slider-element">
								<div class="row">
									<portlet:renderURL var="details">
										<portlet:param name="page" value="auctionDetails"/>
										<portlet:param name="id" value="${item.id}"/>
									</portlet:renderURL>
									<div class="col-xs-12 col-sm-6 col-md-4">
										<a href="${details}">
											<img src="<c:url value="/images/${item.imageName}" />" height="200" width="100%" />
										</a>
										<h4>Cena - ${item.subjectPrice/100} zł</h4> 
										<strong><h4>${item.name}</h4></strong>
									</div>
								</div>
							</div>
						</div>
				</c:forEach>
				<a id="newestPrev" class="prev" >&#10094;</a>
				<a id="newestNext" class="next" >&#10095;</a>
					
			</div>
<!-- 			<div class="dot-container">
				<span class="dot" onclick="currentSlide(1)"></span> 
			</div>  -->
		</div>
	</div> --%>
		<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-3">
			<div class='dad' style="background-color: gray;">
			    <span></span>
			    <div class='b'><liferay-ui:message key="recent-added" /></div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-9">
			<div id="newest-id" class="slideshow-container">
				<c:forEach items="${newestAuctions}" var="item" varStatus="i">
							<div class="slider-element">
									<portlet:renderURL var="details">
										<portlet:param name="page" value="auctionDetails"/>
										<portlet:param name="id" value="${item.id}"/>
									</portlet:renderURL>
									<div class="col-xs-12 col-sm-6 col-md-4">
										<a href="${details}">
											<img src="<c:url value="/images/${item.imageName}" />" height="200" width="100%" />
										</a>
										<h4>Cena - ${item.subjectPrice/100} zł</h4> 
										<strong><h4>${item.name}</h4></strong>
									</div>
							</div>
				</c:forEach>
<!-- 				<a id="newestPrev" class="prev" >&#10094;</a>
				<a id="newestNext" class="next" >&#10095;</a> -->
					
			</div>
		</div>
	</div>
<!-- 	<div class="dot-container">
	  <span class="dot" onclick="currentSlide(1)"></span> 
 	  <span class="dot" onclick="currentSlide(2)"></span> 
	  <span class="dot" onclick="currentSlide(3)"></span>
	</div>  -->
</div> 

<script src="<c:url value="/js/common/custom_slider.js" />"></script>