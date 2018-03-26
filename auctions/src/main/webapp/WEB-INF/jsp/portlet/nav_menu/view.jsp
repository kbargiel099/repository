<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/portlet/navigation_menu/nav-main.css" />" /> 

<%-- 	<nav class="navbar navbar-inverse" style="background-color: #2d67f6;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"><liferay-ui:message key="home-page"/></a>
			</div>
			
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
					<a class="dropdown-toggle" data-toggle="dropdown" href="#"><liferay-ui:message key="language" />
					<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a id="pl" href="/c/portal/update_language?languageId=pl_PL"><liferay-ui:message key="lang.polish" /></a></li>
						<li><a id="en" href="/c/portal/update_language?languageId=en_US"><liferay-ui:message key="lang.english" /></a></li>
					</ul>
				</li>
		    </ul>
   	    </div>
   	</nav> --%>

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
