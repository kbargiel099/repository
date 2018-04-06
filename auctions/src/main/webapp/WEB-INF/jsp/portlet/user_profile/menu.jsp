<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

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
<portlet:renderURL var="addGradeRender">
	<portlet:param name="page" value="addGrade"/>
</portlet:renderURL>
   
<div style="max-height: 300px;">
	<div id="user-profile-menu" class="col-xs-12 col-sm-8 col-md-3">	
		<ul class="horizontal-menu">
		  <li class="horizontal-menu-item"><a href="${getBoughtRender}"><liferay-ui:message key="user.bought.label" /></a></li>
		  <li class="horizontal-menu-item"><a href="${getSoldRender}"><liferay-ui:message key="user.sold.label" /></a></li>
		  <li class="horizontal-menu-item"><a href="${mySettingsRender}"><liferay-ui:message key="user.settings.label" /></a></li>
		  <li class="horizontal-menu-item"><a href="${createNewAuctionRender}"><liferay-ui:message key="user.create.auction.label" /></a></li>
		  <li class="horizontal-menu-item"><a href="${addGradeRender}"><liferay-ui:message key="user.add.grade.label" /></a></li>
		</ul>
	</div>
</div>