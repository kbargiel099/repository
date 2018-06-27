<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />
<%-- <portlet:resourceURL id="makePaid" var="makePaid">
</portlet:resourceURL>
<input type="hidden" id="makePaidUrl" value="${makePaid}"/> --%>

<div class="container-fluid">

	<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>
		<div class="col-xs-12 col-sm-12 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="conversations.label" /></h4>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-8">
			<div class="col-xs-12 col-sm-12 col-md-3 pull-right">
				<div class="form-group">
		            <label class="label-control"><liferay-ui:message key="users.label" /></label>
		            <table>
		            	<tbody>
							<c:forEach items="${users}" var="item">
								<portlet:resourceURL id="getAllMessagesFromUser" var="getAllMessagesFromUser">
									<portlet:param name="userId" value="${item.id}"/>
								</portlet:resourceURL>
								
								<tr>
									<%-- <td>${item.id}</td> --%>
									<td><a href="javascript:getMessages('${getAllMessagesFromUser}');">${item.username}</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
	 		</div>
			<div class="col-xs-12 col-sm-12 col-md-9">
				<h4 class="text-center"><strong><liferay-ui:message key="conversation.history" /></strong></h4>
				<div id="messages" class="mygrid-wrapper-div" style=" border: solid Gray 1px;overflow-y: scroll;height: 500px;width: 100%;">				
<!-- 					<div id="auction-notify" class="mygrid-wrapper-div" style="overflow-y: scroll;height: 200px;width: 100%;"> -->
						<table>
							<tbody>
							</tbody>
						</table>
					</div>
			</div>
		</div>
	</div>

<script src="<c:url value="/js/portlet/user_profile/conversations.js" />"></script>
