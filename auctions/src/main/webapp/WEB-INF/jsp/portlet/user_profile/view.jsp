<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<div class="container-fluid">

	<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>
	
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
