<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >

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
						<strong><h4>${item.auctionName}</h4></strong>
					</div>
				</c:forEach>
			</div>
		</div>
</div>
