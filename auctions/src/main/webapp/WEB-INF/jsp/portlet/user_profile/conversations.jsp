<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<div class="container-fluid">

	<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>
		<div class="col-xs-12 col-sm-12 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="conversations.label" /></h4>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-8">
			<div class="col-xs-12 col-sm-12 col-md-6">
				<div class="form-group">
		            <label class="label-control"><liferay-ui:message key="users.label" /></label>
		            <table>
		            	<tbody>
							<c:forEach items="${users}" var="item">
								<tr>
									<td>${item.id}</td>
									<td>${item.username}</td>
								</tr>
							</c:forEach>
						</table>
					</table>
				</div>
	 	</div>
</div>
