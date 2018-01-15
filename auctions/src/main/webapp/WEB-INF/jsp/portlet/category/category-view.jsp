<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >


<div class="container-fluid">
	<div id="category-view-menu" class="col-xs-12 col-sm-8 col-md-3">
		<h2 class="category-view-title text-center">${category}</h2>	
		<ul class="horizontal-menu">
		  <li class="horizontal-menu-item"><a href="#">Komputery PC</a></li>
		  <li class="horizontal-menu-item"><a href="#">Laptopy</a></li>
		  <li class="horizontal-menu-item"><a href="#">Smartfony</a></li>
		  <li class="horizontal-menu-item"><a href="#">Telewizory</a></li>
		</ul>
	</div>
		<div class="col-xs-12 col-sm-12 col-md-8">
				
			<c:forEach items="${categoryAuctions}" var="item">
			
			<portlet:renderURL var="details">
				<portlet:param name="page" value="auctionDetails"/>
				<portlet:param name="id" value="${item.id}"/>
			</portlet:renderURL>
			
			<div class="category-view-auction row">
				<div class="col-xs-12 col-sm-12 col-md-4">
					<a class="text-center" href="#">
						<img src="<c:url value="/images/${item.imageName}" />" height="160" width="100%" />
					</a>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-4">
					<strong><h4>${item.auctionName}</h4></strong>
					<c:set var = "balance" value = "${item.subjectPrice/100}" />
					<h4>Cena - <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${balance}" type="currency"/></h4> 
				</div>
				<div class="col-xs-12 col-sm-12 col-md-4">
					<a class="btn btn-info" href="${details}"><strong>Szczegóły oferty</strong></a>	
				</div>
			</div>
			</c:forEach>
		</div>
</div>
