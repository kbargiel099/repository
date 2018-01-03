<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/portlet/navigation_menu/main.css"/> ">

<portlet:resourceURL id="signIn" var="signIn">
	<portlet:param name="login" value="XXXXX"/>
	<portlet:param name="pass" value="MMMMM"/>
</portlet:resourceURL>

<portlet:resourceURL id="logout" var="logout">
</portlet:resourceURL>

<input type="hidden" id="signInUrl" value="${signIn}"></input>
<input type="hidden" id="logoutUrl" value="${logout}"></input>

<nav class="navbar navbar-inverse">
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a class="navbar-brand" href="#"><img src="<c:url value="/logo.png"/>" alt="logo" class="img-responsive center-block"></a></li>
        <li class="active"><a href="#">Home</a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Page 1-1</a></li>
            <li><a href="#">Page 1-2</a></li>
            <li><a href="#">Page 1-3</a></li>
          </ul>
        </li>
        <li><a href="#">Page 2</a></li>
        <li><a href="#">Page 3</a></li>
      </ul>
      <ul id="userNavbar" class="nav navbar-nav navbar-right">
      	<c:if test="${isGuest}">      	</c:if>
      	<c:choose>
         
         <c:when test = "${isGuest}">
            <li><a href="${signUp}"><span class="glyphicon glyphicon-user"></span> <liferay-ui:message key="users_management.sign.up.label" /></a></li>
        	<li><a href="javascript:void(0);" id="myBtn"><span class="glyphicon glyphicon-log-in"></span><liferay-ui:message key="users_management.sign.in.label" /></a></li>
         </c:when>
         <c:otherwise>
         	<li class="dropdown">
          		<a class="dropdown-toggle glyphicon glyphicon-sunglasses" data-toggle="dropdown" href="#">${login}<span class="caret"></span></a>
          		<ul class="dropdown-menu">
            		<li><a href="${userSection}" id="myBtn"><span class=""></span>Zarządzaj</a></li>
            		<li><a class="glyphicon glyphicon-off" href="javascript:logout();">Wyloguj</a></li>
          		</ul>
        	</li>
            <input type="hidden" id="userId" value="${userId}"/>
         </c:otherwise>
      </c:choose>
      </ul>
  </div>
  
</nav>
	
<h2>Animated Modal with Header and Footer</h2>

<!-- Trigger/Open The Modal -->


<!-- The Modal -->
<div id="myModal" class="modal-custom col-xs-12 col-sm-8 col-md-6" >

  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
        <span class="close">&times;</span>
    </div>
    <form id="login-form">
    	<div class="modal-body">
        	<h2 class="modal-center"><liferay-ui:message key="users_management.sign.in.label" /></h2>
			<div class="form-group">
	           <label class="label-control" for="login"><liferay-ui:message key="users_management.login.label" /></label>
	           <input class="form-control" id="login" name="login"></input>
			</div>
			<div class="form-group">
	           <label class="label-control" for="password"><liferay-ui:message key="users_management.password.label" /></label>
	           <input  type="password" class="form-control" id="password"  name="password"></input>
			</div>
	    </div>
	    <div class="modal-footer">
	     	<p class="modal-center"><a href="javascript:submitLogin();" id="submitLogin">Wyślij</a></p>
	  	</div>
  	</form>
  </div>

</div>

<script src="<c:url value="/js/portlet/navigation_menu/modal.js"/>" /></script>	  
<script type="text/javascript">
	jQuery(document).ready(function(){
		custom();
	});
</script>
