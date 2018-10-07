<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/selectpicker-custom.css" />" >

<portlet:renderURL var="getUserProfile">
	<portlet:param name="page" value="userProfile"/>
	<portlet:param name="id" value="${seller.id}"/>
</portlet:renderURL>

<portlet:resourceURL id="makePaid" var="makePaid">
</portlet:resourceURL>
<input type="hidden" id="makePaidUrl" value="${makePaid}"/>

<portlet:renderURL var="getBought">
	<portlet:param name="page" value="getBought"/>
</portlet:renderURL>
<input type="hidden" id="getBoughtUrl" value="${getBought}"/>

<div class="container">

	<%@include file="/WEB-INF/jsp/module/administration/confirm_purchase_menu.jsp" %>
	
	<div class="col-xs-12 col-sm-12 col-md-8"">
		<div class="col-xs-12">
			<h4 class="user-profile-section-title"><liferay-ui:message key="summary" /></h4>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-6">
			<label><strong><liferay-ui:message key="purchase.data" /></strong></label>
			<div class="form-group">
				<label><liferay-ui:message key="auction.name.label" /></label>
				<p>${info.auctionName}</p>
			</div>
			<div class="form-group">
				<label><liferay-ui:message key="auction.subject.price.label" /></label>					
				<c:set var = "balance" value = "${info.price/100}" />
				<p>
					<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${balance}" type="number"/> 
					<liferay-ui:message key="currency" />
				</p>
			</div>
			<div class="form-group">
				<label class="label-control"><liferay-ui:message key="auction.subjectQuantity.label" /></label>
				<p>${info.quantity}</p>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-6 row">
				<label><strong><liferay-ui:message key="seller.data" /></strong></label>
				<div class="form-group">
					<label><liferay-ui:message key="username" /></label><br/>
					<p><a href="${getUserProfile}">${seller.username}</a></p>
				</div>
				<div class="form-group">
					<label><liferay-ui:message key="name.and.surname" /></label>
					<p>${seller.firstname} ${seller.lastname}</p>
				</div>
				<div class="form-group">
					<label><liferay-ui:message key="email.address" /></label>
					<p>${seller.emailAddress}</p>
				</div>
		</div>
	<div class="col-xs-12">
		<div style="text-align: center;">
			<button class="btn btn-primary" onclick="sendFormQuickPurchase()"><liferay-ui:message key="confirm.purchase" /></button>
		</div>
	</div>
</div>

<input type="hidden" id="id" value="${info.auctionId}"/>
<input type="hidden" id="username" value="${username}"/>
<input type="hidden" id="price" value="${info.price}"/>
<input type="hidden" id="endDate" value="${info.endDate}"/>
<input type="hidden" id="quantity" value="${info.quantity}"/>
<input type="hidden" id="type" value="${type}"/>

<script src="<c:url value="/js/module/confirm-purchase.js" />"></script>