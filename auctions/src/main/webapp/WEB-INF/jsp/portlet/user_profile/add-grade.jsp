<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

<portlet:actionURL var="submit">
		<portlet:param name="action" value="addGrade"/>
</portlet:actionURL>

<div class="container-fluid">

<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>

		<div class="col-xs-12 col-sm-8 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="user.add.grade.label" /></h4>
			<div class="container">
				<form:form id="add-grade-form" method="POST" action="${submit}" modelAttribute="auctionGrade">
					<div class="row col-xs-12 col-sm-8 col-md-4">
						<form:input type="hidden" class="form-control" path="auctionId" id="auctionId" name="auctionId"></form:input>
						<div class="form-group">
				           <form:label class="label-control" path="grade" name="grade"><liferay-ui:message key="auction.grade.label" /></form:label>
				           <form:input type="text" class="form-control" path="grade" id="grade" name="grade"></form:input>
						</div>
						<div class="form-group">
				           <form:label class="label-control" path="comment" name="comment"><liferay-ui:message key="auction.comment.label" /></form:label>
				           <form:input type="text" class="form-control" path="comment" id="comment" name="comment"></form:input>
						</div>
					</div>
					<div class="row col-xs-12 col-sm-8 col-md-4">
						<div class="form-group">
						  	<input class="btn btn-primary pull-right" type="submit" value="<liferay-ui:message key="submit"/> "></input>
						</div>		  		
					</div>   
		  		</form:form>   
			</div>      
		</div>
	</div>
