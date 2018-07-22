<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

<portlet:resourceURL id="changePassword" var="changePassword">
</portlet:resourceURL>
<input type="hidden" id="changePasswordUrl" value="${changePassword}"></input>

<portlet:renderURL var="returnUrl">
</portlet:renderURL>
<input type="hidden" id="return" value="${returnUrl}"></input>

<div class="container-fluid">

	<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>
	
	<div class="col-xs-12 col-sm-8 col-md-8">
		<h4 class="user-profile-section-title"><liferay-ui:message key="auction.my.settings.label" /></h4>
	</div>
	    
	<form id="change-password-form" method="POST">
		<div class="col-xs-12 col-sm-8 col-md-4">
			<div class="form-group">
	           <label class="label-control" name="password"><liferay-ui:message key="user.password.label" /></label>
	           <input type="password" class="form-control" id="password" name="password"></input>
			</div>
			<div class="form-group">
	           <label class="label-control" name="repeatedPassword"><liferay-ui:message key="user.repeat.password.label" /></label>
	           <input type="password" class="form-control" id="repeatedPassword" name="repeatedPassword"></input>
			</div>
			<div class="form-group">
			   <input class="btn btn-primary pull-right" id="submit" type="submit" value="<liferay-ui:message key="submit"/> ">
			</div>
		</div>
	</form>
</div>

<script src="<c:url value="/js/portlet/user_profile/change-password.js" />"></script>	 
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