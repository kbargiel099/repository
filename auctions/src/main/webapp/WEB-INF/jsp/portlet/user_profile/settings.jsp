<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

<portlet:resourceURL id="changePassword" var="changePassword">
</portlet:resourceURL>
<input type="hidden" id="changePasswordUrl" value="${changePassword}"></input>

<portlet:resourceURL id="updateUserDetails" var="updateUserDetails">
</portlet:resourceURL>
<input type="hidden" id="updateUserDetailsUrl" value="${updateUserDetails}"></input>

<portlet:renderURL var="returnUrl">
</portlet:renderURL>
<input type="hidden" id="return" value="${returnUrl}"></input>

<div class="container-fluid">

	<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>
	
	<div class="col-xs-12 col-sm-8 col-md-8">
		<h4 class="user-profile-section-title"><liferay-ui:message key="auction.my.settings.label" /></h4>
	</div>

	<form id="update-user-data-form" method = "POST">
		<div class="col-xs-12 col-sm-12 col-md-4">
			<div class="form-group">
	           <label class="label-control" for = "firstname"><liferay-ui:message key="users_management.firstname.label" /></label>
	           <input class="form-control" id = "firstname" name="firstname" value="${user.firstname}"></input>
			</div>
			<div class="form-group">
	           <label class="label-control" for = "lastname"><liferay-ui:message key="users_management.lastname.label" /></label>
	           <input class="form-control" id = "lastname" name="lastname" value="${user.lastname}"></input>
			</div>
			<div class="form-group">
	           <label class="label-control" for = "phoneNumber"><liferay-ui:message key="phone.number" /></label>
	           <input class="form-control" id = "phoneNumber" name="phoneNumber" value="${user.phoneNumber}"></input>
			</div>
			<div class="form-group">
	           <label class="label-control" for = "city"><liferay-ui:message key="city" /></label>
	           <input class="form-control" id = "city" name="city" value="${user.city}"></input>
			</div>
			<div class="form-group">
	           <label class="label-control" for = "street"><liferay-ui:message key="street" /></label>
	           <input class="form-control" id = "street" name="street" value="${user.street}"></input>
			</div>
			<div class="form-group">
	           <label class="label-control" for = "houseNumber"><liferay-ui:message key="house.number" /></label>
	           <input class="form-control" id = "houseNumber" name="houseNumber" value="${user.houseNumber}"></input>
			</div>
			<div class="form-group">
	           <label class="label-control" for = "zipCode"><liferay-ui:message key="zip" /></label>
	           <input class="form-control" id = "zipCode" name="zipCode" value="${user.zipCode}"></input>
			</div>
			<div class="form-group">
	           <input class="btn btn-primary pull-right" id="update-user-submit" type = "submit" value = "<liferay-ui:message key="update"/> ">
	  		</div> 
		</div>           
	</form>
	<form id="change-password-form" method="POST">
		<div class="col-xs-12 col-sm-12 col-md-4">
			<div class="form-group">
	           <label class="label-control" for="password"><liferay-ui:message key="user.password.label" /></label>
	           <input type="password" class="form-control" id="password" name="password"></input>
			</div>
			<div class="form-group">
	           <label class="label-control" for="repeatedPassword"><liferay-ui:message key="user.repeat.password.label" /></label>
	           <input type="password" class="form-control" id="repeatedPassword" name="repeatedPassword"></input>
			</div>
			<div class="form-group">
			   <input class="btn btn-primary pull-right" id="submit" type="submit" value="<liferay-ui:message key="change.password"/> ">
			</div>
		</div>
	</form>
</div>
 
<script src="<c:url value="/js/portlet/user_profile/update-user-details.js" />"></script>	

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
	        //form.submit();
	    }
	  });
	});
	
jQuery(function() {
	  jQuery("#update-user-data-form").validate({
	    rules: {
	      firstname: {
	        required: true
	      },
	      lastname: {
	        required: true
	      },
	      phoneNumber: {
		    number: true,
		    minlength: 9
		  },
	      city: {
			required: true
		  },
		  street: {
		    required: true
		  },
		  houseNumber: {
		    required: true
		 },
		  zipCode: {
			required: true
		 }
	    }, 
	    submitHandler: function(form) {
	        //form.submit();
	    }
	  });
	});

</script>