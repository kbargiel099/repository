<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<!-- <link rel="stylesheet" type="text/css" href="<c:url value="/css/portlet/navigation_menu/nav-main.css" />" /> -->

	<nav class="navbar navbar-inverse" style="background-color: #2d67f6;">
	    <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav">
	        <li><a href="#"><liferay-ui:message key="home-page"/></a></li>
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
	  </div>
	</nav>

