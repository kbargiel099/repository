<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<portlet:renderURL var="details">
	<portlet:param name="page" value="auctionDetails"/>
	<portlet:param name="id" value="${auctionId}"/>
</portlet:renderURL>

<portlet:resourceURL id="submitData" var="submitData">
</portlet:resourceURL>
<input type="hidden" id="submitDataUrl" value="${submitData}"></input>

<portlet:resourceURL id="createAuctionVideo" var="createAuctionVideo">
</portlet:resourceURL>
<input type="hidden" id="createAuctionVideoUrl" value="${createAuctionVideo}"></input>

<portlet:resourceURL id="convertVideo" var="convertVideo">
</portlet:resourceURL>
<input type="hidden" id="convertVideoUrl" value="${convertVideo}"></input>

<portlet:resourceURL id="checkConversionStatus" var="checkConversionStatus">
</portlet:resourceURL>
<input type="hidden" id="checkConversionStatusUrl" value="${checkConversionStatus}"></input>

<div class="container-fluid">

	<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>

	<div class="col-xs-12 col-sm-8 col-md-8">
		<h4 class="user-profile-section-title"><liferay-ui:message key="auction.edition.label" /></h4>
<!-- 		<div class="form-group"> -->
			<label class="label-control" for="video"><liferay-ui:message key="auction.attachVideo.label" /></label>
		    <input type="file" id="video" name="video" onchange="loadFileVideo(event)"></input>
		    <div id="file-info">
		    	<div id="filename-div" style="display:none;">
			   		<strong><liferay-ui:message key="upload.filename" /> </strong><p id="filename"></p>
			    </div>
			    <strong><liferay-ui:message key="upload.state" /> </strong><p id="status"></p>
			    <strong><liferay-ui:message key="conversion.state" /> </strong><p id="conversion"></p>
		    </div>
<!-- 	    </div> -->
	 
	   	<form id="video-form">
	   		<input type="hidden" id="vidName" name="vidName"></input>
	   		<input type="hidden" id="vid" name="vid"></input>
	    </form>
	    <a class="btn btn-info" href="${details}" >
			<strong><liferay-ui:message key="show.current" /></strong>
		</a>	
	</div>
</div>

<input type="hidden" id="auctionId" value="${auctionId}" ></input>

<script src="<c:url value="/js/module/file-upload.js" />"></script>