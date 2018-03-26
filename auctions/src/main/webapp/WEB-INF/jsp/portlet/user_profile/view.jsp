<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<div class="container-fluid">

<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>

	<c:if test="${boughtView}">
		<div class="col-xs-12 col-sm-8 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="auction.bought.subjects.label" /></h4>
			<div>
				  <c:forEach items="${subjects}" var="i">
					 <div class="row user-subject">
						 <div class="col-xs-12 col-sm-8 col-md-4">
			          	 	<h4><strong>${i.name}</strong></h4>
			          	 </div>
			          	 <div class="col-xs-12 col-sm-8 col-md-4 pull-right">
							<img src="<c:url value="/images/${i.imageName}" />" alt="obrazek" height="160" width="100%">
						 </div>
					 </div>
		     	  </c:forEach>
			</div>
		</div>
	</c:if>
	<c:if test="${soldView}">
		<div class="col-xs-12 col-sm-8 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="auction.sold.subjects.label" /></h4>
		</div>
	</c:if>
	<c:if test="${mySettingsView}">
		<div class="col-xs-12 col-sm-8 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="auction.my.settings.label" /></h4>
		</div>
	</c:if>
	
</div>

<input type="hidden" id="message" value="${message}"/>

<script>

jQuery(document).ready(function(){
	var msg = jQuery('#message').val();
	if(msg != ""){
		showNotifyAlert(msg);
	}
});
function showNotifyAlert(message) {
	var box = bootbox.alert(message);
	setTimeout(function() {
		box.modal('hide');
	   }, 2000);
}

</script>
