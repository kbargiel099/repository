<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/custom-btn.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/custom_slider.css" />" >

 <div class="container-fluid">
	<div class="row">
		<div class="col-xs-12 col-sm-3 col-md-3">
			<div class='dad' style="background-color: mediumSeaGreen;">
			    <span class="btn-span"></span>
			    <div class='b'><liferay-ui:message key="most-popular" /></div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-9">
			<div id="popular-id" class="slideshow-container">
				<c:forEach items="${electronicAuctions}" var="item" varStatus="i">
					<div class="slider-element">
						<portlet:renderURL var="details">
							<portlet:param name="page" value="auctionDetails"/>
							<portlet:param name="id" value="${item.id}"/>
						</portlet:renderURL>
						<div class="col-xs-12 col-sm-4 col-md-4">
							<a href="${details}">
								<img class="image" src="/images/${item.imageName}" />
							</a>
							<h4>
								<liferay-ui:message key="price" /> - 
								${item.subjectPrice/100} 
								<liferay-ui:message key="currency" />
							<h4> 
							<strong><h4>${item.name}</h4></strong>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12 col-sm-3 col-md-3">
			<div class='dad' style="background-color: gray;">
			    <span class="btn-span"></span>
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
									<div class="col-xs-12 col-sm-4 col-md-4">
										<a href="${details}">
											<img class="image" src="/images/${item.imageName}" />
										</a>
										<h4>
											<liferay-ui:message key="price" /> - 
											${item.subjectPrice/100} 
											<liferay-ui:message key="currency" />
										</h4> 
										<strong><h4>${item.name}</h4></strong>
									</div>
							</div>
				</c:forEach>
					
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