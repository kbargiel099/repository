<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<portlet:renderURL var="details">
	<portlet:param name="page" value="auctionDetails"/>
	<portlet:param name="id" value="${auctionId}"/>
</portlet:renderURL>

<portlet:resourceURL id="saveImage" var="saveImage">
</portlet:resourceURL>
<input type="hidden" id="saveImageUrl" value="${saveImage}"></input>

<div class="container-fluid">

	<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>

	<div class="col-xs-12 col-sm-8 col-md-8">
		<h4 class="user-profile-section-title"><liferay-ui:message key="auction.add.images.label" /></h4>
 		<div class="form-group">
			<label class="label-control" for="video"><liferay-ui:message key="auction.attachImage.label" /></label>
		</div>
		    <input type="file" id="video" name="video" onchange="loadFile(event)"></input>
		    <div id="images" class="form-group">
		    	<c:forEach items="${model.images}" var="item">
			    	<div class="col-xs-4">
			    		<img src="/images/${item}" width="100%" height="100%"/>
		    		</div>
		    	</c:forEach>
		    </div>
	 
	    <a class="btn btn-info" href="${details}" >
			<strong><liferay-ui:message key="show.current" /></strong>
		</a>
		<button id="submit" class="btn btn-primary"><liferay-ui:message key="submit" /></button>	
	</div>
</div>

<input type="hidden" id="auctionId" value="${model.id}" ></input>

<script src="<c:url value="/js/module/file-upload.js" />"></script>
<script src="<c:url value="/js/portlet/user_profile/add-images.js" />"></script>
<script>
	jQuery(document).ready(function(){
		var imagesNames = ${model.images};
		console.log(imagesNames);
		url = jQuery('#submitDataUrl').val();
	});
</script>