<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<portlet:resourceURL id="getUsers" var="getUsers" />
<input type="hidden" id="getUsersUrl" value="${getUsers}"></input>

<portlet:renderURL var="addUser">
	<portlet:param name="page" value="add"/>
</portlet:renderURL>

<portlet:renderURL var="profile">
	<portlet:param name="page" value="userProfile"/>
</portlet:renderURL>
<input type="hidden" id="profileUrl" value="${profile}"/>

<portlet:renderURL var="edit">
	<portlet:param name="page" value="edit"/>
</portlet:renderURL>
<input type="hidden" id="editUrl" value="${edit}"/>

<portlet:resourceURL id="lock" var="lock">
</portlet:resourceURL>
<input type="hidden" id="lockUrl" value="${lock}"/>

<portlet:resourceURL id="unlock" var="unlock">
</portlet:resourceURL>
<input type="hidden" id="unlockUrl" value="${unlock}"/>

<div class="container">

	<%@include file="/WEB-INF/jsp/portlet/users_management/menu.jsp" %>
	
	<div class="col-xs-12 col-sm-8 col-md-9">
		<h4 class="user-profile-section-title"><liferay-ui:message key="adm.users.label" /></h4>
	</div>
	
	<div class="col-xs-12 col-sm-8 col-md-12">
<%-- 		<a class="btn btn-primary btn-sm" href="${addUser}">
			<liferay-ui:message key="add" />
		</a> --%>
		<table id="users" class="display">
		     <thead>
		            <tr>
		                <th><liferay-ui:message key="adm.login.label" /></th>
						<th><liferay-ui:message key="adm.firstname.label" /></th>
						<th><liferay-ui:message key="adm.lastname.label" /></th>
		                <th><liferay-ui:message key="adm.email.address.label" /></th>
						<th><liferay-ui:message key="status" /></th>
		                <th><liferay-ui:message key="options" /></th>
		            </tr>
		        </thead>
		        <tfoot>
		            <tr>
		                <th><liferay-ui:message key="adm.login.label" /></th>
						<th><liferay-ui:message key="adm.firstname.label" /></th>
						<th><liferay-ui:message key="adm.lastname.label" /></th>
		                <th><liferay-ui:message key="adm.email.address.label" /></th>
						<th><liferay-ui:message key="status" /></th>
		                <th><liferay-ui:message key="options" /></th>
		            </tr>
		        </tfoot>
		</table>
	</div>
</div>

<input type="hidden" id="locked-msg" value="<liferay-ui:message key="user.lock.success"/>"/>
<input type="hidden" id="unlocked-msg" value="<liferay-ui:message key="user.unlock.success"/>"/>
<input type="hidden" id="error-msg" value="<liferay-ui:message key="error.msg"/>"/>

<script src="<c:url value="/js/portlet/users_management/datatable.js"/>" /></script>
<script type="text/javascript" >
	jQuery(document).ready(function(){
		initUsers(jQuery("#getUsersUrl").val());
	});
</script>