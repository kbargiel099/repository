<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<div class="container-fluid">

<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>

		<div class="col-xs-12 col-sm-8 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="auction.sold.subjects.label" /></h4>
<%-- 			<div>
				  <c:forEach items="${subjects}" var="i">
					 <div class="row user-subject">
						 <div class="col-xs-12 col-sm-8 col-md-4">
			          	 	<h4><strong>${i.name}</strong></h4>
			          	 </div>
			          	 <div class="col-xs-12 col-sm-8 col-md-4 pull-right">
							<img src="/images/${i.imageName}" alt="obrazek" height="160" width="100%">
						 </div>
					 </div>
		     	  </c:forEach>
			</div> --%>
		</div>
</div>