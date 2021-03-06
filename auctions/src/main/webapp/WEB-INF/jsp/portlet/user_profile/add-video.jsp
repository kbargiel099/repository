<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

<portlet:renderURL var="details">
	<portlet:param name="page" value="auctionDetails"/>
	<portlet:param name="id" value="${auctionId}"/>
</portlet:renderURL>

<portlet:resourceURL id="checkConversionStatus" var="checkConversionStatus">
	<portlet:param name="auctionId" value="${auctionId}"/>
</portlet:resourceURL>
<input type="hidden" id="checkConversionStatusUrl" value="${checkConversionStatus}"></input>

<portlet:resourceURL id="getVideoName" var="getVideoName">
	<portlet:param name="auctionId" value="${auctionId}"/>
</portlet:resourceURL>
<input type="hidden" id="getVideoNameUrl" value="${getVideoName}"></input>

<portlet:resourceURL id="deleteVideo" var="deleteVideo">
	<portlet:param name="auctionId" value="${auctionId}"/>
</portlet:resourceURL>
<input type="hidden" id="deleteVideoUrl" value="${deleteVideo}"></input>

<portlet:actionURL var="fileUploadURL">
    <portlet:param name="formAction" value="fileUpload" />      
</portlet:actionURL>

<div class="container-fluid">

	<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>

	<div class="col-xs-12 col-sm-8 col-md-8">
		<h4 class="user-profile-section-title"><liferay-ui:message key="auction.add.video.label" /></h4>
			
			<form:form name="fileUploader" commandName="springFileVO" method="post"
                 action="${fileUploadURL}"  enctype="multipart/form-data">
                <form:input type="hidden" path="auctionId" value="${auctionId}"/>
                <form:input id="video" path="fileData" type="file" onchange="showSaveBtn(event)"/>
      		</form:form>
      		
		    <div id="file-info">
		    	<div id="filename-div" style="display:none;">
			   		<strong><liferay-ui:message key="upload.filename" /> </strong><p id="filename"></p>
			    </div>
			    <div id="conversion-div" style="display:none;">
			    	<strong><liferay-ui:message key="upload.state" /> </strong><p id="status"></p>
			    	<strong><liferay-ui:message key="conversion.state" /> </strong><p id="conversion"></p>
			    </div>
		    </div>
	
	    <button id="delete-btn" class="btn btn-info" style="display: none;">
			<strong><liferay-ui:message key="delete" /></strong>
		</button>	
	    <a class="btn btn-primary" href="${details}" >
			<strong><liferay-ui:message key="show.current" /></strong>
		</a>
		<button id="submit-btn" class="btn btn-primary" type="submit" style="display: none;">
			<strong><liferay-ui:message key="save" /></strong>
		</button>	
	</div>

<script src="<c:url value="/js/module/file-upload.js" />"></script>
<script>
	jQuery(document).ready(function(){
		url = jQuery('#submitDataUrl').val();
			sendRequest(jQuery('#getVideoNameUrl').val(),function(data){
				var name = JSON.parse(data.name);
				if(name != ''){
					jQuery('#video').hide();
					jQuery('#delete-btn').show();
					jQuery('#filename').html(name);
					jQuery('#filename-div').show();
					hasFile = true;
				}else{
					checkConversionStatus(1);
				}
			});
		
	});
</script>