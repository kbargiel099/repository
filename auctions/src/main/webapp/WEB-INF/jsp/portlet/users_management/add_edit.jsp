<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

<portlet:renderURL var="returnUrl">
	<portlet:param name="page" value="default"/>
</portlet:renderURL>


<c:choose>
    <c:when test="${action=='add'}">
		<portlet:actionURL var="submitUser">
			<portlet:param name="action" value="add"/>
		</portlet:actionURL>
    </c:when>    
    <c:otherwise>
		<portlet:actionURL var="submitUser">
			<portlet:param name="action" value="edit"/>
		</portlet:actionURL>
    </c:otherwise>
</c:choose>

<html>
	<head>
		<title><liferay-ui:message key="users_management.main.label" /></title>
	</head>
	<body>
      <h2><liferay-ui:message key="users_management.data.label" /></h2>
      <form:form method = "POST" action = "${submitUser}" modelAttribute="user">

		<form:input type="hidden" path ="id" name="login"></form:input>
		
		<div class="form-group">
           <form:label class="label-control" path = "login" name="login"><liferay-ui:message key="users_management.login.label" /></form:label>
           <form:input class="form-control" path = "login" name="login"></form:input>
           <form:errors path="login" cssStyle="color: red;"/>
		</div>
		<div class="form-group">
           <form:label class="label-control" path = "password" name="password"><liferay-ui:message key="users_management.password.label" /></form:label>
           <form:input class="form-control" path = "password" name="password"></form:input>
           <form:errors path="password" name="password" cssStyle="color: red;"/>
		</div>
		<div class="form-group">
		   <a href="${returnUrl}"><liferay-ui:message key="return"/></a>
           <input class="btn btn-primary" type = "submit" value = "<liferay-ui:message key="submit"/> ">
  		</div>            
      </form:form>
	</body>
</html>