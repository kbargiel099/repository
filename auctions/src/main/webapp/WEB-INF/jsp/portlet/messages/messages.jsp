<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/image.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >

<portlet:resourceURL id="getMessages" var="getMessages" />
<input type="hidden" id="getMessagesUrl" value="${getMessages}"></input>

<portlet:renderURL var="add">
	<portlet:param name="page" value="add"/>
</portlet:renderURL>

<portlet:renderURL var="edit">
	<portlet:param name="page" value="edit"/>
</portlet:renderURL>
<input type="hidden" id="editUrl" value="${edit}"/>

<portlet:resourceURL id="suspend" var="suspend">
</portlet:resourceURL>
<input type="hidden" id="suspendUrl" value="${suspend}"/>

<portlet:resourceURL id="activate" var="activate">
</portlet:resourceURL>
<input type="hidden" id="activateUrl" value="${activate}"/>

<portlet:resourceURL id="delete" var="delete">
</portlet:resourceURL>
<input type="hidden" id="deleteUrl" value="${delete}"/>

<div class="container">
	
	<div class="col-xs-12">
		<h4 class="user-profile-section-title"><liferay-ui:message key="adm.messages.label" /></h4>
	</div>
	
	<div class="col-xs-12">
 		<a class="btn btn-primary btn-sm" href="${add}">
			<liferay-ui:message key="add" />
		</a>
		<table id="messages" class="display">
		     <thead>
		            <tr>
		                <th><liferay-ui:message key="adm.id.label" /></th>
						<th><liferay-ui:message key="topic" /></th>
						<th><liferay-ui:message key="adm.create.date.label" /></th>
						<th><liferay-ui:message key="status" /></th>
		                <th><liferay-ui:message key="options" /></th>
		            </tr>
		        </thead>
		        <tfoot>
		            <tr>
		                <th><liferay-ui:message key="adm.id.label" /></th>
						<th><liferay-ui:message key="topic" /></th>
						<th><liferay-ui:message key="adm.create.date.label" /></th>
						<th><liferay-ui:message key="status" /></th>
		                <th><liferay-ui:message key="options" /></th>
		            </tr>
		        </tfoot>
		</table>
	</div>
</div>

<input type="hidden" id="locked-msg" value="<liferay-ui:message key="message.lock.success"/>"/>
<input type="hidden" id="unlocked-msg" value="<liferay-ui:message key="message.unlock.success"/>"/>
<input type="hidden" id="deleted-msg" value="<liferay-ui:message key="message.delete.success"/>"/>
<input type="hidden" id="error-msg" value="<liferay-ui:message key="error.msg"/>"/>

<script src="<c:url value="/js/portlet/messages/datatable.js"/>" /></script>
<script src="<c:url value="/js/module/administration.js"/>" /></script>
<script type="text/javascript" >
	jQuery(document).ready(function(){
		initMessages(jQuery("#getMessagesUrl").val());
	});
</script>