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

	<%@include file="/WEB-INF/jsp/module/administration/menu.jsp" %>
	
		<div class="col-xs-12 col-sm-8 col-md-8">
			<c:if test="${type == 'add'}">
				<h4 class="user-profile-section-title"><liferay-ui:message key="create.message.label" /></h4>
			</c:if>
			<c:if test="${type == 'edit'}">
				<h4 class="user-profile-section-title"><liferay-ui:message key="edit.message.label" /></h4>
			</c:if>
		</div>
		  <div class="container">	
		  	<c:set var="selectTitle">
		  		<liferay-ui:message key="choose" />
		  	</c:set>
		  	
	      	<form id="message-form">
				<input type="hidden" name="id" value="${message.id}"></input>
<%-- 				<input type="hidden" name="isSent" value="${message.isSent}"></input> --%>
				<div class="col-xs-12 col-sm-12 col-md-8">
					<div class="col-xs-12 col-sm-12 col-md-6">
						<div class="form-group">
				           <label class="label-control" name="name"><liferay-ui:message key="name" /></label>
				           <input type="text" class="form-control" name="title" value="${message.title}"></input>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-6">
						<div class="form-group">
						    <label class="label-control" for="messageCategoryId"><liferay-ui:message key="message.category" /></label>
							<select class="selectpicker form-control" id="messageCategoryId" name="messageCategoryId" title="${selectTitle}">
								<c:forEach items="${categories}" var="item">
									<option value="${item.id}"><liferay-ui:message key="${item.name}" /></option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-12">
						<div class="form-group">
				           <label class="label-control" name="name"><liferay-ui:message key="text" /></label>
				           <input type="text" class="form-control" name="text" value="${message.text}"></input>
						</div>
					</div>   
					<div class="col-xs-12 col-sm-12 col-md-12">
						<div class="col-xs-12 form-group">
					  		<a class="btn btn-primary pull-right" type="submit" id="message-submit"><liferay-ui:message key="submit"/></a>
					  	</div>
					</div>
		  	 	</div>     
	     </form>
	</div>
</div>

<input type="hidden" id="type" value="${type}">

<script src="<c:url value="/js/portlet/messages/messages.js" />"></script>
