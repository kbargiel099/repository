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
<div class="container">

	<%@include file="/WEB-INF/jsp/portlet/users_management/menu.jsp" %>
	
	<div class="col-xs-12 col-sm-8 col-md-9">
		<h4 class="user-profile-section-title"><liferay-ui:message key="adm.users.label" /></h4>
	</div>
	
	<div class="col-xs-12 col-sm-8 col-md-9">
		<a class="btn btn-primary btn-sm" href="${addUser}">
			<liferay-ui:message key="add" />
		</a>
		<table id="users" class="display">
		     <thead>
		            <tr>
		                <th>Login</th>
						<th>Imię</th>
						<th>Nazwisko</th>
		                <th>Email</th>
		                <th>Opcje</th>
		            </tr>
		        </thead>
		        <tfoot>
		            <tr>
		                <th>Login</th>
						<th>Imię</th>
						<th>Nazwisko</th>
		                <th>Email</th>
		                <th>Opcje</th>
		            </tr>
		        </tfoot>
		</table>
	</div>
</div>
<script src="<c:url value="/js/portlet/users_management/datatable.js"/>" /></script>

<script type="text/javascript" >
	jQuery(document).ready(function(){
		initUsers(jQuery("#getUsersUrl").val());
	});
</script>