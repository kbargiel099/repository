<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/jquery.dataTables.min.css"/> ">

<portlet:resourceURL id="getUsers" var="getUsers" />
<input type="hidden" id="getUsersUrl" value="${getUsers}"></input>

<portlet:renderURL var="addUser">
	<portlet:param name="page" value="add"/>
</portlet:renderURL>

<a class="btn btn-primary" href="${addUser}">
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

<script src="<c:url value="/js/common/jquery-3.2.1.min.js"/>" ></script>
<script src="<c:url value="/js/common/jquery.dataTables.min.js"/>" /></script>
<script src="<c:url value="/js/module/dropdown.js"/>" /></script>
<script src="<c:url value="/js/portlet/users_management/datatable.js"/>" /></script>

<script type="text/javascript" >
	jQuery(document).ready(function(){
		init(jQuery("#getUsersUrl").val());
	});
</script>