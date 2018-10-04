<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/image.css" />" >

<portlet:renderURL var="details">
	<portlet:param name="page" value="auctionDetails"/>
</portlet:renderURL>
<input type="hidden" id="detailsUrl" value="${details}"/>

<portlet:resourceURL id="searchText" var="searchText">
</portlet:resourceURL>
<input type="hidden" id="searchTextUrl" value="${searchText}"/>

<portlet:resourceURL id="getBySubcategory" var="getBySubcategory">
</portlet:resourceURL>
<input type="hidden" id="getBySubcategoryUrl" value="${getBySubcategory}"/>

<div class="notify"><span id="notifyType" class=""></span></div>

<div class="container-fluid">
	<div id="category-view-menu" class="col-xs-12 col-sm-4 col-md-3">
		<h2 class="category-view-title text-center"><liferay-ui:message key="${currentCategory}" /></h2>	
		<ul class="horizontal-menu">
		  <c:forEach items="${subCategories}" var="item">
		  	<li class="horizontal-menu-item">
		  		<a href="javascript:void()" onclick="getBySubcategory(this)"><liferay-ui:message key="${item.name}"/></a>
		  		<input type="hidden" name="id" value="${item.id}"/>
		  	</li>
		  </c:forEach>
		</ul>
		<form id="searchingForm">
			<input type="hidden" id="currentCategory" name="currentCategory" value="${currentCategory}"/>
			<div class="form-group text-center">
				<label class="label-control" for="searchingText"><liferay-ui:message key="searching.text" /></label>
				<input type="text" id="searchingText" name="searchingText" class="form-control"/>
			</div>
			<div class="form-group text-center">
				<label class="label-control" for="minPrice"><liferay-ui:message key="price.range" /></label>
				<div class="row">
					<div class="col-xs-5">
						<input type="text" id="minPrice" name="minPrice" class="form-control"/>
					</div>
					<div class="col-xs-2">
						<label class="text-center"> - </label>
					</div>
					<div class="col-xs-5">
						<input type="text" id="maxPrice" name="maxPrice" class="form-control"/>
					</div>
				</div>
			</div>
			<button id="searchingBtn" class="btn btn-primary form-control" onclick="searchForMatching()"><liferay-ui:message key="search" /></button>
		</form>
	</div>
		<div class="col-xs-12 col-sm-8 col-md-8">
			<div id="elements">
				<c:forEach items="${auctions}" var="item">
				
				<div class="category-view-auction row">
					<div class="col-xs-12 col-sm-4 col-md-4">
						<div style="position: relative;">
							<a href="#">
								<img class="image image-120 img-center" style="display: block; margin: 0 auto;" src="/images/${item.imageName}" />
							</a>
						</div>
					</div>
					<div class="col-xs-12 col-sm-4 col-md-4">
						<strong><h4>${item.name}</h4></strong>
						<c:set var = "balance" value = "${item.subjectPrice/100}" />
						<h4>
							<liferay-ui:message key="price" /> - 
							<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${balance}" type="number"/> <liferay-ui:message key="currency" />
						</h4> 
					</div>
					<div class="col-xs-12 col-sm-4 col-md-4">
						<input type="hidden" name="id" value="${item.id}" />
						<button class="btn btn-info" onclick="showDetails(this)" >
							<strong><liferay-ui:message key="auction.details" /></strong>
						</button>	
					</div>
				</div>
				</c:forEach>
				<c:if test="${fn:length(auctions) eq 0}">
	   				<p><liferay-ui:message key="empty.list.msg" /></p>
				</c:if>
			</div>
		</div>
</div>

<input type="hidden" id="price-label" value="<liferay-ui:message key="price" />"/>
<input type="hidden" id="details-label" value="<liferay-ui:message key="auction.details" />"/>
<input type="hidden" id="currency-label" value="<liferay-ui:message key="currency" />"/>

<script src="<c:url value="/js/portlet/category/category.js"/>" /></script>
