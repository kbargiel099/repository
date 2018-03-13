<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<portlet:resourceURL id="signIn" var="signIn">
</portlet:resourceURL>

<portlet:resourceURL id="logout" var="logout">
</portlet:resourceURL>

<input type="hidden" id="signInUrl" value="${signIn}"></input>
<input type="hidden" id="logoutUrl" value="${logout}"></input>

  <div class="col-xs-12 col-sm-8 col-md-6" style="float: none;margin-left: auto;margin-right: auto; height: 100%;">
    <form id="login-form">
    	<div class="modal-body">
        	<h4 class="text-center"><liferay-ui:message key="users_management.sign.in.label" /></h4>
        	<div id="login-validation-info" class="alert alert-danger" style="display: none;">
  				<strong><liferay-ui:message key="validation.email.or.password.not.exist" /></strong>
		        <span id="login-modal-close" class="close">&times;</span>
			</div>
			<div class="form-group">
	           <label class="label-control" for="email"><liferay-ui:message key="users_management.email.label" /></label>
	           <input type="email" class="form-control" id="email" name="email"></input>
			</div>
			<div class="form-group">
	           <label class="label-control" for="password"><liferay-ui:message key="users_management.password.label" /></label>
	           <input type="password" class="form-control" id="password"  name="password"></input>
			</div>
			<div class="form-group">
	           <label class="label-control" for="remember_me"><liferay-ui:message key="users_management.remember.me.label" /></label>
	           <input type="checkbox" id="remember_me"  name="remember_me" value="false" style="padding-left: 10px;"></input>
			</div>
	    </div>
	    <div class="modal-footer">
<!-- 	    <p class="text-center"><a class="btn btn-primary" href="javascript:submitLogin();" id="submitLogin">Wyślij</a></p> -->
	     	<p class="text-center"><a class="btn btn-primary" type="submit" id="submitLogin">Wyślij</a></p>
	  	</div>
  	</form>
  </div>

<script src="<c:url value="/js/portlet/navigation_menu/modal.js" />"></script>

<script type="text/javascript">
	
 	jQuery(document).ready(function(){
		init();
	}); 
	
 	jQuery("#submitLogin").click(function(){
	     var isValid = jQuery("#login-form").valid();
	     if(isValid){
		      jQuery("#login-validation-info").hide();
	    		submitLogin();
	     }
 	});
 	
	jQuery(function() {
		  jQuery("#login-form").validate({
		    rules: {
		      email: {
		        required: true,
		        email: true
		      },
		      password: {
		        required: true,
		        minlength: 4
		      }
		    }
		  });
		});
	
</script>
