<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/custom-btn.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/custom_slider.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/image.css" />" >

 <div class="container-fluid">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12">
			<div class="container">
				<div class="jumbotron">
				    <h2><liferay-ui:message key="recent-added" /></h2>  
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-12">
			<div id="newest-id" class="slideshow-container">
			<div class="home-page-element">
				<c:forEach items="${newestAuctions}" var="item" varStatus="i">
							<div class="slider-element">
							
									<portlet:renderURL var="details">
										<portlet:param name="page" value="auctionDetails"/>
										<portlet:param name="id" value="${item.id}"/>
									</portlet:renderURL>
									
									<div class="col-xs-12 col-sm-6 col-md-4">
										<a href="${details}">
											<img class="image image-180" src="/images/${item.imageName}" />
										</a>
										<h4>
											<liferay-ui:message key="price" /> - 
											<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${item.subjectPrice/100}" type="number"/> 
											<liferay-ui:message key="currency" />
										</h4> 
										<strong><h4>${item.name}</h4></strong>
									</div>
							
							</div>
				</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div> 