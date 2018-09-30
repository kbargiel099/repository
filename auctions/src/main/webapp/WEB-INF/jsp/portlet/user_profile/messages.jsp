<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/portlet/messages/messages.css" />" >

<div class="container-fluid">

	<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>
	
	<div class="container">	
		<div class="col-xs-12 col-sm-8 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="messages.label" /></h4>
			
	  		<div style="margin-bottom:20px;">
		  		<table>
		  			<thead>
		  			<tr>
		  				<th><liferay-ui:message key="messages.title.label" /></th>
		  				<th style="width:20%"><liferay-ui:message key="messages.category.label" /></th>
		  				<th style="width:22%"><liferay-ui:message key="messages.createDate.label" /></th>
		  			</tr>
		  			</thead>
		  			<tbody>
						<c:forEach items="${messages}" var="message">
							<tr class="pointer-item" onclick="changeVisibility('${message.id}')">
								<c:forEach items="${categories}" var="item">
									<c:if test="${item.id == message.messageCategoryId}">
										<td>${message.title}</td>
										<td><span style="padding-right: 30px;">${item.name}</span></td>
										<td>${message.createDate}</td>
									</c:if>
								</c:forEach>
							</tr>
							<tr style="display:none;" id="${message.id}">
								<td colspan="3">
									<p style="padding:20px; border-bottom:1px solid #cccccc;">${message.text}</p>
								</td>
							</tr>
						</c:forEach>
		  			</tbody>
		  		</table>
			</div>
		</div>
	</div>
</div>

<input type="hidden" id="type" value="${type}">

<script src="<c:url value="/js/portlet/user_profile/messages.js" />"></script>	 	 