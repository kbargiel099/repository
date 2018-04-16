<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >

<div class="container">
  	<div class="col-xs-12 col-sm-12 col-md-5">
		<div class="details-section row">
			<div class="col-xs-12 col-sm-12 col-md-12">
				<h4 class="text-center"><strong><liferay-ui:message key="seller.details" /></strong></h4>
				<div class="padding-left">
					<h5><strong>${user.username} </strong></h5>
					<h5><strong>${user.firstname} ${user.lastname}</strong></h5>
					<h5><strong>${user.emailAddress}</strong></h5>
					<h5><strong>${user.createDate}</strong></h5>
					<h5><strong>${user.loginDate}</strong></h5>
				</div>
			</div>
		</div>
	</div>
</div>