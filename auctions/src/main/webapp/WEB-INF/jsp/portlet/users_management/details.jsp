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

<div class="container">
	
	<div class="col-xs-12 col-sm-8 col-md-9">
		<h4 class="user-profile-section-title"><liferay-ui:message key="user.add.grade.label" /></h4>
	</div>
	
	<div class="col-xs-12 col-sm-8 col-md-9">
	      <h2>Dane użytkownika</h2>
			<div class="form-group">
	           <label class="label-control"><liferay-ui:message key="users_management.login.label" /></label>
	           <label class="label-control">${user.login}</label>
			</div>
			<div class="form-group">
	           <label class="label-control"><liferay-ui:message key="users_management.password.label" /></label>
	           <label class="label-control">${user.password}</label>
			</div>
			<div class="form-group">
			   <a href="${returnUrl}"><liferay-ui:message key="return"/></a>
	  		</div> 
  	</div>           
</div>