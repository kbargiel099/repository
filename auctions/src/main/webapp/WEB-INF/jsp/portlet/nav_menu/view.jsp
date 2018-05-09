<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<portlet:defineObjects/>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/portlet/navigation_menu/nav-main.css" />" /> 
<link rel="stylesheet" type="text/css" href="<c:url value="/css/chat/_message_popup.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/module/custom-table.css" />" /> 

<portlet:resourceURL id="getMessagesFromUser" var="getMessagesFromUser">
</portlet:resourceURL>
<input type="hidden" id="getMessagesFromUserUrl" value="${getMessagesFromUser}" />

<portlet:resourceURL id="markMessagesAsRead" var="markMessagesAsRead">
</portlet:resourceURL>
<input type="hidden" id="markMessagesAsReadUrl" value="${markMessagesAsRead}" />

<nav class="navbar navbar-inverse" style="background-color: #2d67f6;">
 	<div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">Menu</a>
      </div>
    	<div class="collapse navbar-collapse" id="myNavbar">
		    <ul class="nav navbar-nav">
		        <li class="dropdown">
		        	<a class="dropdown-toggle" data-toggle="dropdown" href="#"><liferay-ui:message key="categories"/><span class="caret"></span></a>
		        	<ul class="dropdown-menu">
			            <li><a href="/category?name=electronics"><liferay-ui:message key="electronics"/></a></li>
			            <li><a href="/category?name=clothing"><liferay-ui:message key="clothing"/></a></li>
			            <li><a href="/category?name=motorization"><liferay-ui:message key="motorization"/></a></li>
		            </ul>
		        </li>
		        <li><a href="#"><liferay-ui:message key="contact"/></a></li>
		        <li><a href="#"><liferay-ui:message key="about-service"/></a></li>
		        <li><a href="#"><liferay-ui:message key="occasions"/></a></li>
		    </ul>
		    <ul class="nav navbar-nav navbar-right">
		    	<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#"><span id="notify-view-element" class=" glyphicon glyphicon-envelope"></span>
					<!-- <span class="caret"></span> --></a>
					<ul class="dropdown-menu" id="notification-list">
					<c:choose>
						<c:when test="${fn:length(messages) gt 0}">
							<c:forEach items="${messages}" var="it">
							
								<li id="prefix_${it.senderId}"><a href="javascript:register_popup('${it.senderId}', '${it.screenName}','${getMessagesFromUser}&userId=${it.senderId}');">Wiadomość od ${it.screenName}</a></li>
							
							</c:forEach>
						</c:when>
						<c:otherwise>
							<li id="no-message-elem"><a href="javascript:void(0);">Brak nowych wiadomości</a></li>
						</c:otherwise>
					</c:choose>
					</ul>
				</li>
				<li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#"><liferay-ui:message key="language" />
					<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a id="pl" href="/c/portal/update_language?languageId=pl_PL"><liferay-ui:message key="lang.polish" /></a></li>
						<li><a id="en" href="/c/portal/update_language?languageId=en_US"><liferay-ui:message key="lang.english" /></a></li>
					</ul>
				</li>
		    </ul>
    </div>
  </div>
</nav>

<input type="hidden" id="username" value="${username}"/>

<script src="<c:url value="/js/portlet/navigation_menu/notify-proceed.js" />"></script>
<script src="<c:url value="/js/portlet/navigation_menu/message_popup.js" />"></script>
<script>

function createChatLink(senderId,senderName,text){
	var url = jQuery('#getMessagesFromUserUrl').val() + '&userId=' + senderId;
	
	var a = document.createElement('a');
	a.href = 'javascript:register_popup("'+ senderId +'","'+ senderName +'","'+ url +'");';
	a.innerHTML = text + ' ' + senderName;
	
	return a;
}

</script>

