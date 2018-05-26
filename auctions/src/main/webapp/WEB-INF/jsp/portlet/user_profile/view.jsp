<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<div class="container-fluid">

	<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>
		<div class="col-xs-12 col-sm-12 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="user.account.data.label" /></h4>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-8">
			<div class="col-xs-12 col-sm-12 col-md-6">
				<div class="form-group">
		           <label class="label-control"><liferay-ui:message key="users_management.firstname.label" /></label>
		           <p>${user.firstName}</p>
				</div>
				<div class="form-group">
		           <label class="label-control"><liferay-ui:message key="users_management.login.label" /></label>
		           <p>${user.screenName}</p>
				</div>
				<div class="form-group">
		           <label class="label-control"><liferay-ui:message key="user.account.create.date" /></label>
		           <p>${user.createDate}</p>
				</div>
				<div class="form-group">
					<label class="label-control"><liferay-ui:message key="user.account.state" /></label>
					<c:choose>
						<c:when test="${user.lockout == true}">
							<p><liferay-ui:message key="user.account.locked" /></p>
						</c:when>
						<c:otherwise>
							<p><liferay-ui:message key="user.account.active" /></p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="col-xs-12 col-sm-12 col-md-6">
				<div class="form-group">
		           <label class="label-control"><liferay-ui:message key="users_management.lastname.label" /></label>
		           <p>${user.lastName}</p>
				</div>
				<div class="form-group">
		           <label class="label-control" name="endDate"><liferay-ui:message key="user.email.address" /></label>
		           <p>${user.emailAddress}</p>
				</div>
				<div class="form-group">
		           <label class="label-control"><liferay-ui:message key="user.account.last.modified.date" /></label>
		           <p>${user.modifiedDate}</p>
				</div>
	  			<div class="form-group">
		           <label class="label-control" for="subjectPrice"><liferay-ui:message key="user.last.login.date" /></label>
				   <p>${user.lastLoginDate}</p>
				</div>
			</div>
	 	</div>
</div>

<input type="hidden" id="message" value="${message}"/>

<script>

jQuery(document).ready(function(){
	var msg = jQuery('#message').val();
	if(msg != ""){
		showNotifyAlert(msg);
	}
});

</script>
