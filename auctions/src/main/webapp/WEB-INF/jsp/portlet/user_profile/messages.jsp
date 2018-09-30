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
		  	<c:forEach items="${categories}" var="item">
		  		<div style="margin-bottom:20px;" class="col-xs-12">
			  		<div class="pointer">
			  			<div style="min-height:50px; padding-left:20px; padding-top:5px;" onclick="changeVisibility('${item.name}')">
							<strong>
								<label class="pointer-title" style="font-size: 24px; color:white;" class="label-control">${item.name}</label>
							</strong>
						</div>
					</div>
					<div style="display:none; margin-top:20px;" id="${item.name}">
						<c:forEach items="${messages}" var="message">
							<c:if test="${item.id == message.messageCategoryId}">
								<div style="margin-bottom:10px;" class="col-xs-12 col-sm-8-col-md-8">
									<div onclick="changeVisibility('${message.id}')">
										<b class="pointer-item">${message.title}</b>
										<p class="pull-right">${message.createDate}</p>
									</div>
									<div style="display:none;" id="${message.id}">
										<p>${message.text}</p>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<input type="hidden" id="type" value="${type}">

<script src="<c:url value="/js/portlet/user_profile/messages.js" />"></script>	 	 