<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/selectpicker-custom.css" />" >

<portlet:renderURL var="getBoughtRender">
	<portlet:param name="page" value="getBought"/>
</portlet:renderURL>
<portlet:renderURL var="getSoldRender">
	<portlet:param name="page" value="getSold"/>
</portlet:renderURL>
<portlet:renderURL var="mySettingsRender">
	<portlet:param name="page" value="mySettings"/>
</portlet:renderURL>
<portlet:renderURL var="createNewAuctionRender">
	<portlet:param name="page" value="createNewAuction"/>
</portlet:renderURL>
<portlet:resourceURL id="getImage" var="getImage">
</portlet:resourceURL>
<portlet:resourceURL id="getSubCategories" var="getSubCategories">
</portlet:resourceURL>

<portlet:actionURL var="submit">
		<portlet:param name="action" value="createNewAuction"/>
</portlet:actionURL>

<input type="hidden" id="getBoughtUrl" value="${getBoughtRender}"></input>
<input type="hidden" id="getSoldUrl" value="${getSoldRender}"></input>
<input type="hidden" id="mySettingsUrl" value="${mySettingsRender}"></input>
<input type="hidden" id="getImageUrl" value="${getImage}"></input>


<div class="container-fluid">

 	<div id="user-profile-menu" class="col-xs-12 col-sm-8 col-md-3">	
		<ul class="horizontal-menu">
		  <li class="horizontal-menu-item"><a href="${getBoughtRender}">Zakupione</a></li>
		  <li class="horizontal-menu-item"><a href="${getSoldRender}">Sprzedane</a></li>
		  <li class="horizontal-menu-item"><a href="${mySettingsRender}">Moje ustawienia</a></li>
		  <li class="horizontal-menu-item"><a href="${createNewAuctionRender}">Wystaw przedmiot</a></li>
		</ul>
	</div>
	
	<c:if test="${boughtView}">
		<div class="col-xs-12 col-sm-8 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="auction.bought.subjects.label" /></h4>
			<div>
				  <c:forEach items="${subjects}" var="i">
					 <div class="row user-subject">
						 <div class="col-xs-12 col-sm-8 col-md-4">
			          	 	<h4><strong>${i.name}</strong></h4>
			          	 </div>
			          	 <div class="col-xs-12 col-sm-8 col-md-4 pull-right">
							<img src="<c:url value="/images/${i.imageName}" />" alt="obrazek" height="160" width="100%">
						 </div>
					 </div>
		     	  </c:forEach>
			</div>
		</div>
	</c:if>
	<c:if test="${soldView}">
		<div class="col-xs-12 col-sm-8 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="auction.sold.subjects.label" /></h4>
		</div>
	</c:if>
	<c:if test="${mySettingsView}">
		<div class="col-xs-12 col-sm-8 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="auction.my.settings.label" /></h4>
		</div>
	</c:if>
</div>
