<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

<portlet:resourceURL id="insert" var="insert">
</portlet:resourceURL>
<input type="hidden" id="insertUrl" value="${insert}"></input>

<portlet:resourceURL id="edit" var="edit">
</portlet:resourceURL>
<input type="hidden" id="editUrl" value="${edit}"></input>

<portlet:renderURL var="returnUrl">
</portlet:renderURL>
<input type="hidden" id="return" value="${returnUrl}"></input>

<div class="container-fluid">

	<%@include file="/WEB-INF/jsp/module/administration/default_menu.jsp" %>
	
	<div class="col-xs-12 col-sm-12 col-md-8">
	
		<div class="col-xs-12">
			<c:if test="${type == 'add'}">
				<h4 class="user-profile-section-title"><liferay-ui:message key="create.message.category.label" /></h4>
			</c:if>
			<c:if test="${type == 'edit'}">
				<h4 class="user-profile-section-title"><liferay-ui:message key="edit.message.category.label" /></h4>
			</c:if>
		</div>
		
	  	<c:set var="selectTitle">
	  		<liferay-ui:message key="choose" />
	  	</c:set>
	  	
      	<form id="message-category-form">
			<input type="hidden" name="id" value="${category.id}"></input>
			<div class="col-xs-12 col-sm-12 col-md-6">
				<div class="form-group">
		           <label class="label-control" for="name"><liferay-ui:message key="name" /></label>
		           <input type="text" class="form-control" id="name" name="name" value="${category.name}"></input>
				</div>
			</div> 
			<div class="col-xs-12 col-sm-12 col-md-4">
				<div class="col-xs-12 form-group">
			  		<a class="btn btn-primary pull-right" type="submit" id="message-category-submit"><liferay-ui:message key="submit"/></a>
			  	</div>
			</div> 
       </form>
	</div>
</div>

<input type="hidden" id="type" value="${type}">

<script src="<c:url value="/js/portlet/message_category/message-categories.js" />"></script>
