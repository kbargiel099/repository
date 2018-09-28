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

<portlet:renderURL var="returnToDetails">
	<portlet:param name="page" value="auctionDetails"/>
	<portlet:param name="id" value="${info.auctionId}"/>
</portlet:renderURL>
<input type="hidden" id="returnUrl" value="${returnToDetails}"/>

<div class="container">
	<div class="col-xs-12" style="text-align: center;">
		<h4 class="text-center"><strong><liferay-ui:message key="summary" /></strong></h4>
		<div class="col-xs-12">
			<div>
				<label><liferay-ui:message key="auction.name.label" /></label>
				<p>${info.auctionName}</p>
			</div>
			<div>
				<label><liferay-ui:message key="auction.subject.price.label" /></label>					
				<c:set var = "balance" value = "${info.price/100}" />
				<p>
					<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${balance}" type="number"/> 
					<liferay-ui:message key="currency" />
				</p>
			</div>
		</div>
		<div class="col-xs-12">
			<div>
				<label class="label-control"><liferay-ui:message key="auction.subjectQuantity.label" /></label>
				<p>${info.quantity}</p>
			</div>
		</div>
		<c:if test="${type == 'pay'}">
			<div class="col-xs-12">
				<div>
					<label class="label-control"><liferay-ui:message key="choose.payment.method" /></label>
					<select class="selectpicker form-control" title="<liferay-ui:message key="choose" />"> 		
						<c:forEach items="${paymentMethods}" var="item">
							<option value="${item.id}"><liferay-ui:message key="${item.name}" /></option>
						</c:forEach>
		           </select>
				</div>
			</div>
		</c:if>
	</div>
	<div class="col-xs-12" style="text-align: center;">
		<div class="details-section row">
			<div class="col-xs-12">
				<h4 class="text-center"><strong><liferay-ui:message key="seller.details" /></strong></h4>
				<div>
					<label><liferay-ui:message key="username" /></label><br>
					<a href="${getUserProfile}">${seller.username}</a>
				</div>
				<div>
					<label><liferay-ui:message key="name.and.surname" /></label>
					<p>${seller.firstname} ${seller.lastname}</p>
				</div>
				<div>
					<label><liferay-ui:message key="email.address" /></label>
					<p>${seller.emailAddress}</p>
				</div>
				<div>
					<label><liferay-ui:message key="phone.number" /></label>
					<p>${seller.phoneNumber}</p>
				</div>
			</div>
		</div>
	</div>
	<div class="col-xs-12">
		<c:if test="${type != 'pay'}">
			<div style="text-align: center; margin-top: 20px;">
				<label class="label-control"><liferay-ui:message key="confirm.purchase" /></label>
			</div>
		</c:if>
		<div style="text-align: center;">
			<a class="btn btn-info" href="${returnToDetails}"><liferay-ui:message key="return" /></a>
			<button class="btn btn-primary" onclick="sendFormQuickPurchase()"><liferay-ui:message key="send" /></button>
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