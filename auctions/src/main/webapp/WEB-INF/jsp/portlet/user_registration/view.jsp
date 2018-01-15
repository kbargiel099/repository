<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

<portlet:actionURL var="submit">
		<portlet:param name="action" value="add"/>
</portlet:actionURL>


  <div class="container">
  	  <div class="col-sm-1 col-md-1" style="height: 100vh;">
		
	  </div>
	  <div class="col-xs-12 col-sm-8 col-md-10">
	        <h5><strong><liferay-ui:message key="users_management.data.label" /></strong></h5>
	  </div>
      <form:form id="registration-form" method = "POST" action = "${submit}" modelAttribute="user">
		<form:input type="hidden" path ="id" name="login"></form:input>
		<div class="col-xs-12 col-sm-8 col-md-5">
			<div class="form-group">
	           <form:label class="label-control" path = "firstname" name="firstname"><liferay-ui:message key="users_management.firstname.label" /></form:label>
	           <form:input type="text" class="form-control" path = "firstname" id="firstname" name="firstname"></form:input>
	           <form:errors path="firstname" cssStyle="color: red;"/>
			</div>
			<div class="form-group">
	           <form:label class="label-control" path = "lastname" name="lastname"><liferay-ui:message key="users_management.lastname.label" /></form:label>
	           <form:input type="text" class="form-control" path = "lastname" id="lastname" name="lastname"></form:input>
	           <form:errors path="lastname" cssStyle="color: red;"/>
			</div>
			<div class="form-group">
	           <form:label class="label-control" path = "login" name="login"><liferay-ui:message key="users_management.login.label" /></form:label>
	           <form:input type="text" class="form-control" path = "login" id="login" name="login"></form:input>
	           <form:errors path="login" cssStyle="color: red;"/>
			</div>
		</div>
		<div class="col-xs-12 col-sm-8 col-md-5">
			<div class="form-group">
	           <form:label class="label-control" path = "password" name="password"><liferay-ui:message key="users_management.password.label" /></form:label>
	           <form:input type="password" class="form-control" id="password" path = "password" name="password"></form:input>
	           <form:errors path="password" name="password" cssStyle="color: red;"/>
			</div>
			<div class="form-group">
	           <form:label class="label-control" path = "email" name="email"><liferay-ui:message key="users_management.email.label" /></form:label>
	           <form:input type="email" class="form-control" id="email" path = "email" name="email"></form:input>
	           <form:errors path="email" cssStyle="color: red;"/>
			</div>
  		</div>
  		<div class="col-xs-12 col-sm-8 col-md-8">
			<div class="row form-group">
				<a href="${home}"><liferay-ui:message key="return"/></a>
		        <input class="btn btn-primary pull-right" type="submit" value="<liferay-ui:message key="submit"/> ">
		  	</div>
	  	</div>            
      </form:form>
   </div>
   
<script type="text/javascript">
	
	jQuery(document).ready(function(){
		jQuery(function() {
			  jQuery("#registration-form").validate({
			    rules: {
				  firstname: {
					required: true
				  },	
				  lastname: {
					required: true
					  },	
			      login: {
			    	required: true
			      },
			      email: {
			        required: true,
			        email: true
			      },
			      password: {
			        required: true,
			        minlength: 4
			      }
			    },
			    submitHandler: function(form) {
			      form.submit();
			    }
			  });
		});
	});
</script>

