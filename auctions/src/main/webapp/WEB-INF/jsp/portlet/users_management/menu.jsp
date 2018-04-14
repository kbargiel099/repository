<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/selectpicker-custom.css" />" >

<portlet:renderURL var="getUsersRender">
	<portlet:param name="page" value="getUsers"/>
</portlet:renderURL>
<portlet:renderURL var="getAuctionsRender">
	<portlet:param name="page" value="getAuctions"/>
</portlet:renderURL>
    
<div style="max-height: 300px;">
	<div id="user-profile-menu" class="col-xs-12 col-sm-8 col-md-3">	
		<ul class="horizontal-menu">
		  <li class="horizontal-menu-item"><a href="${getUsersRender}"><liferay-ui:message key="adm.show.users.label" /></a></li>
		  <li class="horizontal-menu-item"><a href="${getAuctionsRender}"><liferay-ui:message key="adm.show.auctions.label" /></a></li>
		</ul>
	</div>
</div>