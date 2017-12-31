<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

<portlet:renderURL var="returnUrl">
	<portlet:param name="page" value="default"/>
</portlet:renderURL>

	<portlet:actionURL var="submit">
		<portlet:param name="action" value="add"/>
</portlet:actionURL>


<html>
	<head>
		<title><liferay-ui:message key="users_management.main.label" /></title>
	</head>
	<body>
      <h2><liferay-ui:message key="users_management.data.label" /></h2>
      <form:form method = "POST" action = "${submit}" modelAttribute="user">

		<form:input type="hidden" path ="id" name="login"></form:input>
		
		<div class="form-group">
           <form:label class="label-control" path = "firstname" name="firstname"><liferay-ui:message key="users_management.firstname.label" /></form:label>
           <form:input class="form-control" path = "firstname" name="firstname"></form:input>
           <form:errors path="firstname" cssStyle="color: red;"/>
		</div>
		<div class="form-group">
           <form:label class="label-control" path = "lastname" name="lastname"><liferay-ui:message key="users_management.lastname.label" /></form:label>
           <form:input class="form-control" path = "lastname" name="lastname"></form:input>
           <form:errors path="lastname" cssStyle="color: red;"/>
		</div>
		<div class="form-group">
           <form:label class="label-control" path = "login" name="login"><liferay-ui:message key="users_management.login.label" /></form:label>
           <form:input class="form-control" path = "login" name="login"></form:input>
           <form:errors path="login" cssStyle="color: red;"/>
		</div>
		<div class="form-group">
           <form:label class="label-control" path = "password" name="password"><liferay-ui:message key="users_management.password.label" /></form:label>
           <form:input  type="password" class="form-control" path = "password" name="password"></form:input>
           <form:errors path="password" name="password" cssStyle="color: red;"/>
		</div>
		<div class="form-group">
           <form:label class="label-control" path = "email" name="email"><liferay-ui:message key="users_management.email.label" /></form:label>
           <form:input class="form-control" path = "email" name="email"></form:input>
           <form:errors path="email" cssStyle="color: red;"/>
		</div>
		<div class="form-group">
		   <a href="${returnUrl}"><liferay-ui:message key="return"/></a>
           <input class="btn btn-primary pull-right" type = "submit" value = "<liferay-ui:message key="submit"/> ">
  		</div>            
      </form:form>
	</body>
</html>
