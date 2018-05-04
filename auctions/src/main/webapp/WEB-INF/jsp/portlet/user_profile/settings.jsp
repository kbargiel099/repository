<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

<portlet:actionURL var="submit">
		<portlet:param name="action" value="changePassword"/>
</portlet:actionURL>

<div class="container-fluid">

	<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>
	
	<div class="col-xs-12 col-sm-8 col-md-8">
		<h4 class="user-profile-section-title"><liferay-ui:message key="auction.my.settings.label" /></h4>
	</div>
	    
	<form:form id="change-password-form" method="POST" action="${submit}" modelAttribute="userPassword">
		<div class="col-xs-12 col-sm-8 col-md-4">
			<div class="form-group">
	           <form:label class="label-control" path="password" name="password"><liferay-ui:message key="user.password.label" /></form:label>
	           <form:input type="password" class="form-control" path="password" id="password" name="password"></form:input>
			</div>
			<div class="form-group">
	           <form:label class="label-control" path="repeatedPassword" name="repeatedPassword"><liferay-ui:message key="user.repeat.password.label" /></form:label>
	           <form:input type="password" class="form-control" path="repeatedPassword" id="repeatedPassword" name="repeatedPassword"></form:input>
			</div>
			<div class="form-group">
			   <input class="btn btn-primary pull-right" type="submit" value="<liferay-ui:message key="submit"/> ">
			</div>
		</div>
	</form:form>
</div>

<script>
jQuery(function() {
	  jQuery("#change-password-form").validate({
	    rules: {
	      password: {
	        required: true,
	        minlength: 8
	      },
	      repeatedPassword: {
	        required: true,
	        minlength: 8,
	        equalTo: "#password"
	      }
	    }, 
	    submitHandler: function(form) {
	        form.submit();
	    }
	  });
	});

</script>