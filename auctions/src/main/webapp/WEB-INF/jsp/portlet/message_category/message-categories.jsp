<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/image.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >

<portlet:resourceURL id="getMessageCategories" var="getMessageCategories" />
<input type="hidden" id="getMessageCategoriesUrl" value="${getMessageCategories}"></input>

<portlet:renderURL var="add">
	<portlet:param name="page" value="add"/>
</portlet:renderURL>

<portlet:renderURL var="messages">
	<portlet:param name="page" value="messages"/>
</portlet:renderURL>
<input type="hidden" id="messagesUrl" value="${messages}"/>

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

<%-- 	<%@include file="/WEB-INF/jsp/module/administration/menu.jsp" %> --%>
	
	<div class="col-xs-12">
		<h4 class="user-profile-section-title"><liferay-ui:message key="adm.message-categories.label" /></h4>
	</div>
	
	<div class="col-xs-12">
 		<a class="btn btn-primary btn-sm" href="${add}">
			<liferay-ui:message key="add" />
		</a>
		<table id="message-categories" class="display">
		     <thead>
		            <tr>
						<th><liferay-ui:message key="adm.name.label" /></th>
						<th><liferay-ui:message key="adm.create.date.label" /></th>
						<th><liferay-ui:message key="status" /></th>
		                <th><liferay-ui:message key="options" /></th>
		            </tr>
		        </thead>
		        <tfoot>
		            <tr>
						<th><liferay-ui:message key="adm.name.label" /></th>
						<th><liferay-ui:message key="adm.create.date.label" /></th>
						<th><liferay-ui:message key="status" /></th>
		                <th><liferay-ui:message key="options" /></th>
		            </tr>
		        </tfoot>
		</table>
	</div>
</div>

<input type="hidden" id="locked-msg" value="<liferay-ui:message key="message.category.lock.success"/>"/>
<input type="hidden" id="unlocked-msg" value="<liferay-ui:message key="message.category.unlock.success"/>"/>
<input type="hidden" id="deleted-msg" value="<liferay-ui:message key="message.category.delete.success"/>"/>
<input type="hidden" id="error-msg" value="<liferay-ui:message key="error.msg"/>"/>

<script src="<c:url value="/js/portlet/message_category/datatable.js"/>" /></script>
<script src="<c:url value="/js/module/administration.js"/>" /></script>
<script type="text/javascript" >
	jQuery(document).ready(function(){
		initMessageCategories(jQuery("#getMessageCategoriesUrl").val());
	});
</script>