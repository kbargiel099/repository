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

<portlet:renderURL var="returnToDetails">
<portlet:param name="page" value="auctionDetails"/>
<portlet:param name="id" value="${info.auctionId}"/>
</portlet:renderURL>
<input type="hidden" id="returnUrl" value="${returnToDetails}"/>

<div class="container">
 	<div class="col-xs-12 col-sm-5 col-md-5">
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
	</div>
	<div class="col-xs-12 col-sm-7 col-md-7">
		<h4 class="text-center"><strong><liferay-ui:message key="summary" /></strong></h4>
		<div class="col-xs-12 col-sm-5 col-md-5">
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
		</div>
		<div class="col-xs-12 col-sm-5 col-md-5">
			<div class="form-group">
				<label class="label-control"><liferay-ui:message key="auction.subjectQuantity.label" /></label>
				<p>${info.quantity}</p>
			</div>
		</div>
		<div class="col-xs-12 col-sm-5 col-md-5">
			<div class="form-group">
				<label class="label-control"><liferay-ui:message key="choose.payment.method" /></label>
				<select class="selectpicker form-control" title="<liferay-ui:message key="choose" />"> 		
					<c:forEach items="${paymentMethods}" var="item">
						<option value="${item.id}"><liferay-ui:message key="${item.name}" /></option>
					</c:forEach>
	           </select>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-5 col-md-5">
		<button class="btn btn-primary pull-right" onclick="sendFormQuickPurchase()"><liferay-ui:message key="send" /></button>
		<a class="pull-left" href="${returnToDetails}"><liferay-ui:message key="return" /></a>
	</div>
</div>

<input type="hidden" id="id" value="${info.auctionId}"/>
<input type="hidden" id="username" value="${username}"/>
<input type="hidden" id="price" value="${info.price}"/>
<input type="hidden" id="endDate" value="${info.endDate}"/>
<input type="hidden" id="quantity" value="${info.quantity}"/>

<script>
var stompClient = null;

jQuery(document).ready(function(){
	connect();
});

function connect() {
    var socket = new SockJS('http://192.168.0.15:8143/notification');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/message/notify/'+jQuery('#id').val(), function(data){
        	var res = JSON.parse(data.body);
        	console.log(res);
        	if(res.success == true){
        		window.location.href = jQuery('#returnUrl').val();
        	}else{
        		responsiveNotify('Wystąpił błąd, spróbuj ponownie');
        	}
        });
    });
}

function sendFormQuickPurchase() {
	//stompClient.send("/app/purchase/" + jQuery('#id').val(), {}, 
	//    JSON.stringify(jQuery('#purchase-form').serializeObject()));   
 	    stompClient.send("/app/purchase/" + jQuery('#id').val(), {}, JSON.stringify({'userId': Liferay.ThemeDisplay.getUserId(),'username': jQuery('#username').val(),
	    	'price': jQuery('#price').val(),'endDate': jQuery('#endDate').val(),'quantity': jQuery('#quantity').val() }));    

}
</script>