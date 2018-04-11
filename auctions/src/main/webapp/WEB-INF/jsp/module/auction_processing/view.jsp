<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/module/custom-table.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/module/notify-modal.css" />" >

<portlet:renderURL var="getUserProfile">
	<portlet:param name="page" value="userProfile"/>
	<portlet:param name="id" value="${seller.id}"/>
</portlet:renderURL>

<portlet:resourceURL id="getVideoName" var="getVideoName">
	<portlet:param name="auctionId" value="${auction.id}" />
</portlet:resourceURL>

<input type="hidden" id="getVideoNameUrl" value="${getVideoName}"></input>

<div class="container">
	<div id="notify-message"></div>
  		<div class="col-xs-12 col-sm-5 col-md-5">
			<div class="details-section row">
				<div class="col-xs-12 col-sm-12 col-md-12">
					<div id="gallery">
						<a class="text-center" href="#">
							<img src="/images/${auction.imageName}" style="height:auto; width:100%;" />
						</a>
					</div>
					<div id="video" style="display: none;">
					</div>
					<div id="show-video-div">
						<h5>
							<strong>Zobacz wideo 
								<a id="showVideo" href="javascript:void(0);">link</a>
							</strong>
						</h5>
					</div>
					<div id="show-gallery-div" style="display: none;">
						<h5>
							<strong>Wróć do galerii 
								<a id="showGallery" href="javascript:void(0);">link</a>
							</strong>
						</h5>
					</div>
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
				<div id="auction-notify" class="mygrid-wrapper-div" style=" border: solid Gray 1px;overflow: scroll;height: 200px;width: 100%;">
	<!-- 				<ul>
					</ul> -->
					<h4 class="text-center"><strong><liferay-ui:message key="auction.actual.offers" /></strong></h4>
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
			<div class="col-xs-12 col-sm-5 col-md-5">
				<h5><strong><liferay-ui:message key="create.date" /></strong>  - ${auction.createDate}</h5>
				<h5><strong><liferay-ui:message key="end.date" /></strong> - ${auction.endDate}</h5>
			</div>
		</div>
		<div class="details-section row">
			<div class="col-xs-12 col-sm-5 col-md-5">
				<h5><strong><liferay-ui:message key="available.quantity" /></strong> - ${auction.subjectQuantity} sztuk</h5>
			</div>
			<div class="col-xs-12 col-sm-5 col-md-5">
				<h5><strong><liferay-ui:message key="enter.quantity" /></strong> </h5><input  type="text" /> 
				<input type="button" id="raiseStakeBtn" class="btn btn-primary" value="<liferay-ui:message key="raise.stake.btn" />"/>
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
		<div>
			<h5><strong><liferay-ui:message key="technical.data" /></strong></h5>
		</div>
	</div>
</div>

<input type="hidden" id="id" value="${auction.id}"/>
<input type="hidden" id="currentPrice" value="${auction.subjectPrice}"/>
<input type="hidden" id="endDate" value="${auction.endDate}"/>
<input type="hidden" id="quantity" value="${auction.subjectQuantity}"/>

<input type="hidden" id="successMsg" value="<liferay-ui:message key="raise.stake.success.msg" />"/>
<input type="hidden" id="userIsNotSignedInMsg" value="<liferay-ui:message key="user.is.not.signed.in.msg" />"/>
<input type="hidden" id="errorCode1" value="<liferay-ui:message key="add.to.database.error.msg" />"/>
<input type="hidden" id="errorCode2" value="<liferay-ui:message key="auction.has.ended.msg" />"/>
<input type="hidden" id="currency" value="<liferay-ui:message key="currency" />"/>

<script src="<c:url value="/js/module/notify-modal.js" />"></script>
<script src="<c:url value="/js/module/sockjs.min.js" />"></script>
<script src="<c:url value="/js/module/stomp.min.js" />"></script>
<script src="<c:url value="/js/module/app.js" />"></script>
<script>
var canPlayMPEG4;
	jQuery(document).ready(function(){
			var testEl = document.createElement( "video" );
/* 		    mpeg4, h264, ogg, webm; */
			if ( testEl.canPlayType ) {
			    // Check for MPEG-4 support
			    canPlayMPEG4 = "" !== testEl.canPlayType( 'video/mp4; codecs="mp4v.20.8"' );
		
/* 			    // Check for h264 support
			    h264 = "" !== ( testEl.canPlayType( 'video/mp4; codecs="avc1.42E01E"' )
			        && testEl.canPlayType( 'video/mp4; codecs="avc1.42E01E, mp4a.40.2"' ) ); */
		
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
	    		var ext = canPlayMPEG4 ? 'mp4' : 'ogv';
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
</script>