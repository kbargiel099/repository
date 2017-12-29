<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

<portlet:renderURL var="returnUrl">
	<portlet:param name="page" value="default"/>
</portlet:renderURL>

<portlet:actionURL var="submitUser">
	<portlet:param name="action" value="add"/>
</portlet:actionURL>

<html>
	<head>
		<title>Zarządzanie użytkownikiem</title>
	</head>
	<body>
      <h2>Dane użytkownika</h2>
      <form:form method = "POST" action = "${submitUser}" commandName="user"
      	modelAttribute="user">

		<div class="form-group">
           <form:label class="label-control" path = "login" name="login"><liferay-ui:message key="users_management.login.label" /></form:label>
           <form:input class="form-control" path = "login" name="login"></form:input>
           <form:errors path="login" cssStyle="color: red;"/>
		</div>
		<div class="form-group">
           <form:label class="label-control" path = "password" name="password"><liferay-ui:message key="users_management.password.label" /></form:label>
           <form:input class="form-control" path = "password" name="password"></form:input>
           <form:errors path="password" cssStyle="color: red;"/>
		</div>
		<div class="form-group">
		   <a href="${returnUrl}"><liferay-ui:message key="return"/></a>
           <input class="btn btn-primary" type = "submit" value = "<liferay-ui:message key="submit"/> ">
  		</div>            
      </form:form>
	</body>
</html>