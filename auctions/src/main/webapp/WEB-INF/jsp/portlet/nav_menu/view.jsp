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
	        <li><a href="#">Strona domowa</a></li>
	        <li class="dropdown">
	          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Kategorie<span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li><a href="/category?name=electronics">Eletronika</a></li>
	            <li><a href="/category?name=clothing">Odzież</a></li>
	            <li><a href="/category?name=motorization">Motoryzacja</a></li>
	          </ul>
	        </li>
	        <li><a href="#">Kontakt</a></li>
	        <li><a href="#">O serwisie</a></li>
	        <li><a href="#">Okazje</a></li>
	      </ul>
	  </div>
	</nav>

