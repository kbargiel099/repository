<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >
<div id="alert-notify" class="alert alert-success" style="display: none;">
</div>

<portlet:renderURL var="getUserProfile">
	<portlet:param name="page" value="userProfile"/>
	<portlet:param name="id" value="${seller.id}"/>
</portlet:renderURL>

<div class="container">
  	<div class="col-xs-12 col-sm-12 col-md-5">
		<div class="details-section row">
			<div class="col-xs-12 col-sm-12 col-md-12">
				<a class="text-center" href="#">
					<img src="<c:url value="/images/${auction.imageName}" />" style="heigh:400px; width:400px;" />
				</a>
			</div>
		</div>
		<div class="details-section row">
			<div class="col-xs-12 col-sm-12 col-md-12">
				<h4 class="text-center"><strong><liferay-ui:message key="seller.details" /></strong></h4>
				<div class="padding-left">
				<a href="${getUserProfile}">
					<h5>${seller.username}</h5>
				</a>
					<h5>${seller.firstname} ${seller.lastname}</h5>
					<h5>${seller.emailAddress}</h5>
					<h5>${seller.phoneNumber}</h5>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-12">
			<div id="auction-notify" class="mygrid-wrapper-div" style=" border: solid Gray 1px;overflow: scroll;height: 200px;">
				<ul>
				</ul>
			</div>
		</div>
	</div>
	
	<div class="col-xs-12 col-sm-12 col-md-7">
  		<div class="details-section row">
			<div class="col-xs-12 col-sm-12 col-md-5">
				<h4><strong>${auction.name}</strong></h4>
				<c:set var = "balance" value = "${auction.subjectPrice/100}" />
				<h5 id="price">
					<liferay-ui:message key="actual.price" /> - 
					<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${balance}" type="number"/> 
					<liferay-ui:message key="currency" />
				</h5>
			</div>
			<div class="col-xs-12 col-sm-12 col-md-5">
				<h5><strong><liferay-ui:message key="create.date" /></strong>  - ${auction.createDate}</h5>
				<h5><strong><liferay-ui:message key="end.date" /></strong> - ${auction.endDate}</h5>
			</div>
		</div>
		<div class="details-section row">
			<div class="col-xs-12 col-sm-12 col-md-5">
				<h5><strong><liferay-ui:message key="available.quantity" /></strong> - ${auction.subjectQuantity} sztuk</h5>
			</div>
			<div class="col-xs-12 col-sm-12 col-md-5">
				<h5><strong><liferay-ui:message key="enter.quantity" /></strong> </h5><input  type="text" /> 
				<input type="button" id="raiseStakeBtn" class="btn btn-primary" value="<liferay-ui:message key="raise.stake.btn" />"/>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-7">
		<div class="details-section-border">
			<h5><strong><liferay-ui:message key="subject.description" /></strong></h5>
			<h5>${auction.description}</h5>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-7">
		<div>
			<h5><strong><liferay-ui:message key="technical.data" /></strong></h5>
		</div>
	</div>
</div>

<input type="hidden" id="id" value="${auction.id}"/>
<input type="hidden" id="currentPrice" value="${auction.subjectPrice}"/>
<input type="hidden" id="endDate" value="${auction.endDate}"/>

<input type="hidden" id="successMsg" value="<liferay-ui:message key="raise.stake.success.msg" />"/>
<input type="hidden" id="userIsNotSignedInMsg" value="<liferay-ui:message key="user.is.not.signed.in.msg" />"/>
<input type="hidden" id="errorCode1" value="<liferay-ui:message key="add.to.database.error.msg" />"/>
<input type="hidden" id="errorCode2" value="<liferay-ui:message key="auction.has.ended.msg" />"/>

<script src="<c:url value="/js/module/sockjs.min.js" />"></script>
<script src="<c:url value="/js/module/stomp.min.js" />"></script>
<script src="<c:url value="/js/module/app.js" />"></script>