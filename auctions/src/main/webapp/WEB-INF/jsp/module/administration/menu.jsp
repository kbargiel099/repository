<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/selectpicker-custom.css" />" >

<div style="max-height: 300px;">
	<div id="user-profile-menu" class="col-xs-12 col-sm-8 col-md-3">	
		<ul class="horizontal-menu">
		  <li class="horizontal-menu-item"><a href="/uzytkownicy"><liferay-ui:message key="adm.show.users.label" /></a></li>
		  <li class="horizontal-menu-item"><a href="/aukcje"><liferay-ui:message key="adm.show.auctions.label" /></a></li>
		  <li class="horizontal-menu-item"><a href="/kategorie_komunikatow"><liferay-ui:message key="adm.show.message.categories.label" /></a></li>
		  <li class="horizontal-menu-item"><a href="/komunikaty"><liferay-ui:message key="adm.show.messages.label" /></a></li>
		</ul>
	</div>
</div>