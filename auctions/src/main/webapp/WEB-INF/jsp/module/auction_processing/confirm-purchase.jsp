<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<portlet:renderURL var="getUserProfile">
	<portlet:param name="page" value="userProfile"/>
	<portlet:param name="id" value="${seller.id}"/>
</portlet:renderURL>

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
						<div id="createChatInAuction" style="display:none;">
				
						</div>
					</div>
				</div>
			</div>
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
		</div>
		<div class="details-section row">
			<div class="col-xs-12 col-sm-5 col-md-5">
				<label><strong><liferay-ui:message key="available.quantity" /></strong></label>
				<p>10 sztuk</p>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-7 col-md-7">
		<div class="details-section-border">
			<label class="label-control"><strong><liferay-ui:message key="choose.payment.method" /></strong></label>
						<select class="selectpicker form-control" id="auctionTypeIdSelect" title="<liferay-ui:message key="choose" />"> 		
							<c:forEach items="${auctionTypes}" var="item">
								<option value="${item.id}"><liferay-ui:message key="${item.name}" /></option>
							</c:forEach>
			           </select>
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

<input type="hidden" id="successMsg" value="<liferay-ui:message key="raise.stake.success.msg" />"/>
<input type="hidden" id="userIsNotSignedInMsg" value="<liferay-ui:message key="user.is.not.signed.in.msg" />"/>
<input type="hidden" id="errorCode1" value="<liferay-ui:message key="add.to.database.error.msg" />"/>
<input type="hidden" id="errorCode2" value="<liferay-ui:message key="auction.has.ended.msg" />"/>
<input type="hidden" id="currency" value="<liferay-ui:message key="currency" />"/>

<script src="<c:url value="/js/module/app.js" />"></script>
<script>
var canPlayMPEG4;
	jQuery(document).ready(function(){
			var testEl = document.createElement( "video" );
/* 		    mpeg4, h264, ogg, webm; */
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
	    				+ '<source src="/videos/'+ data.name +'.'+ ext +'" type="video/'+ media +'">'
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

		if(Liferay.ThemeDisplay.getUserId() != parseInt(jQuery('#seller-id').val())){
			jQuery('#createChatInAuction').append(createChatLink(jQuery('#seller-id').val(),
					jQuery('#seller-username').val(),'Napisz do'));
			jQuery('#createChatInAuction').show();
		}
	});
</script>
