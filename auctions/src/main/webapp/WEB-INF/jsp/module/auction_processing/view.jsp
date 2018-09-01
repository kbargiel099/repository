<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/module/notify-modal.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/custom_slider.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/image.css" />" >

<portlet:renderURL var="getUserProfile">
	<portlet:param name="page" value="userProfile"/>
	<portlet:param name="id" value="${seller.id}"/>
</portlet:renderURL>

<portlet:renderURL var="confirmPurchase">
	<portlet:param name="page" value="confirmPurchase"/>
</portlet:renderURL>
<input type="hidden" id="confirmPurchaseUrl" value="${confirmPurchase}"></input>

<portlet:resourceURL id="getVideoName" var="getVideoName">
	<portlet:param name="auctionId" value="${auction.id}" />
</portlet:resourceURL>
<input type="hidden" id="getVideoNameUrl" value="${getVideoName}"></input>

<portlet:resourceURL id="getAllOffers" var="getAllOffers">
	<portlet:param name="auctionId" value="${auction.id}" />
</portlet:resourceURL>
<input type="hidden" id="getAllOffersUrl" value="${getAllOffers}"></input>

<portlet:resourceURL id="createObservation" var="createObservation">
	<portlet:param name="auctionId" value="${auction.id}" />
</portlet:resourceURL>
<input type="hidden" id="createObservationUrl" value="${createObservation}"></input>

<portlet:resourceURL id="removeObservation" var="removeObservation">
	<portlet:param name="auctionId" value="${auction.id}" />
</portlet:resourceURL>
<input type="hidden" id="removeObservationUrl" value="${removeObservation}"></input>

<div class="container">
	<div id="validation-info" class="alert alert-danger" style="display: none;">
  		<strong><liferay-ui:message key="auction.proceed.validation.info" /></strong>
		<span id="modal-close" class="close">&times;</span>
	</div>
	<div id="notify-message"></div>
  		<div class="col-xs-12 col-sm-5 col-md-5">
			<div class="details-section row">
				<div class="col-xs-12 col-sm-12 col-md-12">
					<div id="gallery" style="display:none;">
						<c:forEach items="${auction.images}" var="item" varStatus="i">
							<div class="slider-element" style="text-align:center;">
								<a class="text-center" href="#">
									<img class="image-240" src="/images/${item}" />
								</a>
							</div>
						</c:forEach>
					</div>
					<div id="video" style="display: none;">
					</div>
					<c:choose>
						<c:when test="${auction.video != ''}">
							<div id="show-video-div" style="text-align: center;">
								<h5>
									<strong>
										<a id="showVideo" href="javascript:void(0);"><liferay-ui:message key="see.video" /></a>
									</strong>
								</h5>
							</div>
							<div id="show-gallery-div" style="text-align: center; display: none;">
								<h5>
									<strong>
										<a id="showGallery" href="javascript:void(0);"><liferay-ui:message key="see.gallery" /></a>
									</strong>
								</h5>
							</div>
						</c:when>
						 <c:otherwise>
							<div style="text-align: center;">
								<h5>
									<strong><liferay-ui:message key="seller.does.not.add.video" /></strong>
								</h5>
							</div>
				         </c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="details-section row">
				<div class="col-xs-12 col-sm-12 col-md-12">
					<h4 class="text-center"><strong><liferay-ui:message key="seller.details" /></strong></h4>
<!-- 					<div class="padding-left"> -->
						<table>
							<tbody>
							  <tr class="user-data-tr">
 							    <td class="user-data-td">
 							    	<liferay-ui:message key="username" /><br>				
									<a href="${getUserProfile}">${seller.username}</a>
								</td>
 							  </tr>
 							  <tr class="user-data-tr">
							    <td class="user-data-td">
							    	<liferay-ui:message key="name.and.surname" /><br>	
									${seller.firstname} ${seller.lastname}
								</td>					  
 							  </tr>
 							  <tr class="user-data-tr">
 							    <td class="user-data-td">
 							    	<liferay-ui:message key="email.address" /><br>	
 									${seller.emailAddress}
 								</td>
 							  </tr>
 							  <tr class="user-data-tr">
 							    <td class="user-data-td">
 							    	<liferay-ui:message key="phone.number" /><br>	
 									${seller.phoneNumber}
 								</td>
							</tbody>
						</table>
<!-- 					</div> -->
					<div id="createChatInAuction" style="text-align: center;display:none;">
					</div>
				</div>
			</div>
			<c:if test="${auction.typeName != 'quick_purchase'}">
				<div class="col-xs-12 col-sm-12 col-md-12">
					<h4 class="text-center"><strong><liferay-ui:message key="auction.actual.offers" /></strong></h4>
					<div id="auction-notify" class="mygrid-wrapper-div" style="overflow-y: scroll;height: 200px;width: 100%;">
						<table>
							<thead>
							  <tr>
							    <th><liferay-ui:message key="auction.username.theader" /></th>
							    <th><liferay-ui:message key="auction.quantity.theader" /></th>
							    <th><liferay-ui:message key="auction.price.theader" /></th>
							  </tr>
							</thead>
							<tbody>
							
							</tbody>
						</table>
					</div>
					<p id="empty-list-msg" style="text-align: center;"></p>
				</div>
			</c:if>
		</div>
	
	<div class="col-xs-12 col-sm-7 col-md-7">
  		<div class="details-section row">
			<div class="col-xs-12 col-sm-5 col-md-5">
				<h4><strong>${auction.name}</strong></h4>
				<c:set var = "balance" value = "${auction.subjectPrice/100}" />
				<h5 id="price">
					<liferay-ui:message key="actual.price" /> - 
					<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${balance}" type="number"/> 
					<liferay-ui:message key="currency" />
				</h5>
			</div>
			<div class="col-xs-12 col-sm-5 col-md-5">
				<div id="removeObservation" style="display: none;">
					<strong><liferay-ui:message key="currently.observe" /></strong>  
					<a id="remove-observe" href="javascript:void(0);">
						<strong><liferay-ui:message key="remove.observe" /></strong>
					</a>
				</div>
				<div id="createObservation" style="display: none;">
					<a id="create-observe" href="javascript:void(0);">
						<strong><liferay-ui:message key="create.observe" /></strong>
					</a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-5 col-md-5">
				<h5><strong><liferay-ui:message key="create.date" /></strong>  - ${auction.createDate}</h5>
				<h5><strong><liferay-ui:message key="end.date" /></strong> - ${auction.endDate}</h5>
			</div>
		</div>
		<div class="details-section row">
			<div class="col-xs-12 col-sm-5 col-md-5">
				<h5><strong><liferay-ui:message key="available.quantity" /></strong> - ${auction.subjectQuantity} <liferay-ui:message key="items" /></h5>
			</div>
			<div class="col-xs-12 col-sm-5 col-md-5">
					<c:choose>
						<c:when test="${auction.typeName == 'quick_purchase'}">
							<form id="proceed-form">
								<c:if test="${auction.subjectQuantity > 1}">
									<div class="form-group">
										<h5><strong><liferay-ui:message key="enter.quantity" /></strong> </h5><input id="quantity-input" name="quantity-input" type="text" />
									</div>
								</c:if>
								<input type="submit" id="quickPurchaseBtn" class="btn btn-primary" value="<liferay-ui:message key="purchase.btn" />"/>
								<div id ="refreshPage" style="display: none;">
									<strong><liferay-ui:message key="refresh.notify" /></strong>
									<input type="button" id="refreshBtn" class="btn btn-info" value="<liferay-ui:message key="refresh.btn" />"/>
								</div>
							</form>
						</c:when>
						<c:otherwise>
							<form id="proceed-form">
								<div class="form-group">
									<h5><strong><liferay-ui:message key="enter.price" /></strong> </h5><input id="price-input" name="price-input" type="text" />
								</div>
								<c:if test="${auction.subjectQuantity > 1}">
									<div class="form-group">
										<h5><strong><liferay-ui:message key="enter.quantity" /></strong> </h5><input id="quantity-input" name="quantity-input" type="text" />
									</div>
								</c:if>
								<input type="submit" id="raiseStakeBtn" class="btn btn-primary" value="<liferay-ui:message key="raise.stake.btn" />"/>
							</form>
						</c:otherwise>
					</c:choose>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-7 col-md-7">
		<div class="details-section-border">
			<h5><strong><liferay-ui:message key="subject.description" /></strong></h5>
			<h5>${auction.description}</h5>
		</div>
	</div>
	<div class="col-xs-12 col-sm-7 col-md-7">
		<div id="technicalDataList">
			<h5><strong><liferay-ui:message key="technical.data" /></strong></h5>
		</div>
	</div>
</div>

<input type="hidden" id="seller-id" value="${seller.id}"/>
<input type="hidden" id="seller-username" value="${seller.username}"/>
<input type="hidden" id="type" value="${auction.typeName}"/>
<input type="hidden" id="id" value="${auction.id}"/>
<input type="hidden" id="currentPrice" value="${auction.subjectPrice}"/>
<input type="hidden" id="endDate" value="${auction.endDate}"/>
<input type="hidden" id="quantity" value="${auction.subjectQuantity}"/>
<input type="hidden" id="isObserved" value="${isObserved}"/>
<input type="hidden" id="auctionName" value="${auction.name}"/>

<input type="hidden" id="successMsg" value="<liferay-ui:message key="raise.stake.success.msg" />"/>
<input type="hidden" id="userIsNotSignedInMsg" value="<liferay-ui:message key="user.is.not.signed.in.msg" />"/>
<input type="hidden" id="errorCode1" value="<liferay-ui:message key="add.to.database.error.msg" />"/>
<input type="hidden" id="errorCode2" value="<liferay-ui:message key="auction.has.ended.msg" />"/>
<input type="hidden" id="currency" value="<liferay-ui:message key="currency" />"/>
<input type="hidden" id="message" value="${message}"/>

<script src="<c:url value="/js/common/custom_slider.js" />"></script>
<script src="<c:url value="/js/module/app.js" />"></script>
<script type="text/javascript">
var data = ${auction.technicalParameters};
data = JSON.parse(data);

var canPlayMPEG4;
	jQuery(document).ready(function(){
			var testEl = document.createElement( "video" );
			if ( testEl.canPlayType ) {
			    // Check for MPEG-4 support
			    canPlayMPEG4 = "" !== testEl.canPlayType( 'video/mp4; codecs="mp4v.20.8"' );
			    // Check for h264 support
/* 			    h264 = "" !== ( testEl.canPlayType( 'video/mp4; codecs="avc1.42E01E"' )
			        && testEl.canPlayType( 'video/mp4; codecs="avc1.42E01E, mp4a.40.2"' ) );  */
/* 			    // Check for Ogg support
			    ogg = "" !== testEl.canPlayType( 'video/ogg; codecs="theora"' ); */
/* 			    // Check for Webm support
			    webm = "" !== testEl.canPlayType( 'video/webm; codecs="vp8, vorbis"' ); */
			}
	});

	jQuery('#showVideo').click(function(){
	    jQuery.ajax({
	    	"url": jQuery('#getVideoNameUrl').val(),
	    	"type": "POST",
	    	"success": function(data){
	    		var ext = canPlayMPEG4 ? 'mp4' : 'ogg';
	    		var media = canPlayMPEG4 ? 'mp4' : 'ogg';
 	    		var videoElement = '<video width="100%" controls>'
/*  	    				+ '<source src="/videos/'+ data.name +'.mp4" type="video/mp4">' */
/*	    				+ '<source src="/videos/'+ data.name +'.webm" type="video/webm">' */
	    				+ '<source src="/videos/'+ JSON.parse(data.name) +'.'+ ext +'" type="video/'+ media +'">'
/* 	    				+ '<source src="/videos/'+ data.name +'" type="video/ogg">'
 	    				+ "<source src="+"/videos/"+ data.name +" type='video/webm;codecs="+"vp8, vorbis"'>"  */
	    				+ '</video>';  	 
		   		jQuery('#gallery').hide();
	    		jQuery('#show-video-div').hide();
 	    		jQuery('#video').html(videoElement);
	    		jQuery('#video').show();
	    		jQuery('#show-gallery-div').show();
	    	}
	    });
	});
	
	jQuery('#showGallery').click(function(){
		jQuery('#video').hide();
		jQuery('#show-gallery-div').hide();
		jQuery('#gallery').show();
		jQuery('#show-video-div').show();
	});
	
	jQuery(document).ready(function(){
		for(var i=0;i<data.length;i++){
			var name = jQuery('<div class="col-xs-6">'+ Liferay.Language.get(data[i].name) +'</div>');
			var value = jQuery('<div class="col-xs-6">'+ Liferay.Language.get(data[i].value) +'</div>');
			jQuery('#technicalDataList').append(name,value);
		}
		if(Liferay.ThemeDisplay.getUserId() != parseInt(jQuery('#seller-id').val())){
			jQuery('#createChatInAuction').append(createChatLink(jQuery('#seller-id').val(),
					jQuery('#seller-username').val(),Liferay.Language.get('write.message')));
			jQuery('#createChatInAuction').show();
		}
		jQuery('#gallery').show();
		var msg = jQuery('#message').val();
		if(msg != ""){
			responsiveNotify(msg);
		}
	});
</script>