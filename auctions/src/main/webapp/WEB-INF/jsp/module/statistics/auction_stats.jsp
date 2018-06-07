<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/image.css" />" >

<portlet:renderURL var="profile">
	<portlet:param name="page" value="userProfile"/>
	<portlet:param name="id" value="${auction.userId}"/>
</portlet:renderURL>

<div class="container">

	<c:choose>
		<c:when test="${view == 'profile'}">
			<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>
		</c:when>
		<c:otherwise>
			<%@include file="/WEB-INF/jsp/portlet/users_management/menu.jsp" %>
		</c:otherwise>
	</c:choose>
	<div class="col-xs-12 col-sm-8 col-md-9">
		<h4 class="user-profile-section-title"><liferay-ui:message key="adm.auction.stats.label" /></h4>
	</div>
	
	<div class="container">	

		<div class="col-xs-12 col-sm-12 col-md-8">
			<div class="col-xs-12 col-sm-12 col-md-6">
				<div class="form-group">
		           <label class="label-control"><liferay-ui:message key="auction.name.label" /></label>
		           <p>${auction.name}</p>
				</div>
				<div class="form-group">
		           <label class="label-control"><liferay-ui:message key="auction.type.label" /></label>
		           <p><liferay-ui:message key="${auction.typeName}" /></p>
				</div>
				<div class="form-group">
		           <label class="label-control"><liferay-ui:message key="auction.subjectQuantity.label" /></label>
		           <p>${auction.subjectQuantity}</p>
				</div>
			</div>
			<div class="col-xs-12 col-sm-12 col-md-6">
				<div class="form-group">
		           <label class="label-control"><liferay-ui:message key="auction.username.label" /></label>
		           <p><a href="${profile}">${username}</a></p>
				</div>
				<div class="form-group">
		           <label class="label-control"><liferay-ui:message key="auction.createDate.label" /></label>
		           <p>${auction.createDate}</p>
				</div>
				<div class="form-group">
		           <label class="label-control" name="endDate"><liferay-ui:message key="auction.endDate.label" /></label>
		           <p>${auction.endDate}</p>
				</div>
	  		</div>
	  		<div class="col-xs-12 col-sm-12 col-md-6">
	  			<div class="form-group">
		           <label class="label-control" for="subjectPrice"><liferay-ui:message key="auction.subject.price.label" /></label>
				   <p>${auction.subjectPrice/100}</p>
				</div>
			</div>
	  		<div class="col-xs-12 col-sm-12 col-md-6">
				<div class="form-group">
					<label class="label-control"><liferay-ui:message key="auction.minimal.price.label" /></label>
					<c:choose>
					<c:when test="${auction.typeName == 'with_minimal_price'}">

						<p>${auction.minimalPrice}</p>
					</c:when>
					<c:otherwise>
						<p><liferay-ui:message key="not.defined" /></p>
					</c:otherwise>
					</c:choose>
				</div>
  			</div>
	  		<div class="col-xs-12 col-sm-12 col-md-12">
	  			<div class="form-group">
			        <label class="label-control"><liferay-ui:message key="auction.description.label" /></label>
			        <p>${auction.description}</p>
				</div>
			</div>
	  		<div class="col-xs-12 col-sm-12 col-md-12">
				<div id="elements">
					<div id="transactions" class="mygrid-wrapper-div" style="overflow-y: scroll;height: 200px;width: 100%;">
						<label style="text-align: center;"><strong><liferay-ui:message key="auction.transactions.label" /></strong></label>
						<table>
							<thead>
							  <tr>
							    <th><liferay-ui:message key="auction.username.theader" /></th>
							    <th><liferay-ui:message key="auction.quantity.theader" /></th>
							    <th><liferay-ui:message key="auction.price.theader" /></th>
							    <th><liferay-ui:message key="auction.createDate.theader" /></th>
							    <th></th>
							  </tr>
							</thead>
							<tbody>
								<c:forEach items="${transactions}" var="item">
									
									<tr>
										<td>${item.username}</td>
										<td>${item.quantity}</td>
										<td>${item.price/100}</td>
										<td>${item.createDate}</td>
										<td></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>          
		    </div>
	 	</div>
	</div>
</div>