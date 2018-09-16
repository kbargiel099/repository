<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/image.css" />" >

<portlet:renderURL var="details">
	<portlet:param name="page" value="auctionDetails"/>
</portlet:renderURL>
<input type="hidden" id="detailsUrl" value="${details}"/>

<portlet:renderURL var="getPurchaseInfo">
	<portlet:param name="page" value="getPurchaseInfo"/>
	<portlet:param name="type" value="pay"/>
</portlet:renderURL>
<input type="hidden" id="getPurchaseInfoUrl" value="${getPurchaseInfo}"/>

<div class="container-fluid">

	<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>
	<div class="col-xs-12 col-sm-8 col-md-8">
		<h4 class="user-profile-section-title"><liferay-ui:message key="user.bought.label" /></h4>
	</div>
	<div class="col-xs-12 col-sm-8 col-md-8">
		<div id="elements">
			<c:forEach items="${auctions}" var="item">
			
			<div class="category-view-auction row">
				<div class="col-xs-12 col-sm-12 col-md-4">
					<div style="position: relative;">
						<a href="#">
							<img class="image image-120 img-center" src="/images/${item.imageName}" />
						</a>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-4">
					<strong>${item.name}</strong>
					<br><c:set var = "balance" value = "${item.subjectPrice/100}" />
					<liferay-ui:message key="price" /> - 
					<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${balance}" type="number"/> <liferay-ui:message key="currency" />
					<br><liferay-ui:message key="create.date" /> <p>${item.createDate}</p>
					<br><liferay-ui:message key="status" /> <p><liferay-ui:message key="${item.paymentStatusName}" /></p>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-4">
					<input type="hidden" name="id" value="${item.id}" />
					<button class="btn btn-info" onclick="showDetails(this)" >
						<strong><liferay-ui:message key="auction.details" /></strong>
					</button>	
					<c:if test="${item.paymentStatusName != 'payed'}">
						<input type="hidden" name="purchaseId" value="${item.purchaseId}" />
						<button class="btn btn-primary" onclick="showConfirmPurchase(this)" >
							<strong><liferay-ui:message key="make.paid" /></strong>
						</button>	
					</c:if>
				</div>
			</div>
			</c:forEach>
			<c:if test="${fn:length(auctions) eq 0}">
   				<p><liferay-ui:message key="empty.list.msg" /></p>
			</c:if>
		</div>
	</div>
</div>

<input type="hidden" id="message" value="${message}"/>
<script>

	jQuery(document).ready(function(){
		var msg = jQuery('#message').val();
		if(msg != ""){
			responsiveNotify(msg);
		}
	});

 	function showDetails(obj){
 		var url = jQuery('#detailsUrl').val();
 		var id = jQuery(obj).parent().find('input[name="id"]').val();
 		window.location.href = buildUrl(url,'id',id);
 	}
 	
 	function showConfirmPurchase(obj){
/*  		var url = jQuery('#getPurchaseInfoUrl').val();
 		var id = jQuery(obj).parent().find('input[name="purchaseId"]').val();
 		window.location.href = buildUrl(url,'id',id); */
 		alert("Do zrobienia");
 	}
</script>