<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

<portlet:resourceURL id="getAuctionTypes" var="getAuctionTypes">
</portlet:resourceURL>
<input type="hidden" id="getAuctionTypesUrl" value="${getAuctionTypes}"></input>

<portlet:resourceURL id="getSubCategories" var="getSubCategories">
</portlet:resourceURL>
<input type="hidden" id="getSubCategoriesUrl" value="${getSubCategories}"></input>

<portlet:resourceURL id="submitAuction" var="submitAuction">
</portlet:resourceURL>
<input type="hidden" id="submitAuctionUrl" value="${submitAuction}"></input>

<portlet:resourceURL id="saveImage" var="saveImage">
</portlet:resourceURL>
<input type="hidden" id="saveImageUrl" value="${saveImage}"></input>

<portlet:resourceURL id="getTechnicalData" var="getTechnicalData">
</portlet:resourceURL>
<input type="hidden" id="getTechnicalDataUrl" value="${getTechnicalData}"></input>

<portlet:renderURL var="returnUrl">
</portlet:renderURL>
<input type="hidden" id="return" value="${returnUrl}"></input>

<div class="container-fluid">

	<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>
	
		<div class="col-xs-12 col-sm-8 col-md-8">
			<c:if test="${type == 'add'}">
				<h4 class="user-profile-section-title"><liferay-ui:message key="auction.form.label" /></h4>
			</c:if>
			<c:if test="${type == 'edit'}">
				<h4 class="user-profile-section-title"><liferay-ui:message key="auction.edition.label" /></h4>
			</c:if>
		</div>
		  <div class="container">	
		  	<c:set var="selectTitle">
		  		<liferay-ui:message key="choose" />
		  	</c:set>
		  	
	      	<form id="create-new-auction-form">
				<input type="hidden" name="id" value="${auction.id}"></input>
				<input type="hidden" id="imageName" name="imageName" value=""/>
				<input type="hidden" id="categoryId" name="categoryId" value="${auction.categoryId}"/>
				<input type="hidden" id="auctionTypeId" name="auctionTypeId" value="${auction.auctionTypeId}"/>
			    <input type="hidden" id="endDate" name="endDate" value="${auction.endDate}"></input>
			    <input type="hidden" id="subjectPrice" name="subjectPrice" value="${auction.subjectPrice}"></input>
				<input type="hidden" id="subCategoryId" name="subCategoryId" value="${auction.subCategoryId}"/>
				<input type="hidden" id="minimalPrice" name="minimalPrice" value="${auction.minimalPrice}"/>
				<input type="hidden" id="technicalData" name="technicalData" value=""/>
				<div class="col-xs-12 col-sm-12 col-md-8">
					<div class="col-xs-12 col-sm-12 col-md-6">
						<div class="form-group">
				           <label class="label-control" for="name"><liferay-ui:message key="auction.name.label" /></label>
				           <input type="text" class="form-control" id="name" name="name" value="${auction.name}"></input>
						</div>
						<div class="form-group">
				           <label class="label-control" for="auctionTypeId"><liferay-ui:message key="auction.type.label" /></label>
							<select class="selectpicker form-control" id="auctionTypeIdSelect" name="auctionTypeIdSelect" class="required"> 		
								<option value="">${selectTitle}</option>
								<c:forEach items="${auctionTypes}" var="item">
									<option value="${item.id}"><liferay-ui:message key="${item.name}" /></option>
								</c:forEach>
				           </select>
						</div>
						<div id="subjectQuantityDiv" class="form-group">
				           <label class="label-control" for="subjectQuantity"><liferay-ui:message key="auction.subjectQuantity.label" /></label>
				           <input type="text" class="form-control" id="subjectQuantity" name="subjectQuantity" value="${auction.subjectQuantity}"></input>
						</div>
						<c:if test="${type == 'add'}">
							<div class="form-group">
								<label class="label-control" for="attachImage" ><liferay-ui:message key="auction.attachImage.label" /></label>
							</div>
							<div>
								<input type="file" name="imageFilechooser" id="imageFilechooser" onchange="loadFile(event)"/>
								<div id="images" class="form-group"></div>
							</div>
						</c:if>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-6">
						<div class="form-group">
				           <label class="label-control" for="endDate"><liferay-ui:message key="auction.endDate.label" /></label>
				           <input type="date" id="endDateInput" name="endDateInput" class="form-control" value="${auction.endDate}"></input>
						</div>
						<div class="form-group">
				           <label class="label-control" for="subjectPrice"><liferay-ui:message key="auction.subject.price.label" /></label>
				           <input type="text" class="form-control" id="price" name="price" value="${auction.subjectPrice}"></input>
						</div>
						<div id="minimalPriceDiv" class="form-group">
				           <label class="label-control" for="minimalPrice"><liferay-ui:message key="auction.minimal.price" /></label>
				           <input type="text" class="form-control" id="minimalPrice_" name="minimalPrice_" value="${auction.minimalPrice}"></input>
						</div>
						<div class="form-group">
						    <label class="label-control" for="categoryId"><liferay-ui:message key="auction.category.label" /></label>
							<select class="selectpicker form-control" id="categoryIdSelect" name="categoryIdSelect" class="required">
								<option value="">${selectTitle}</option>
								<c:forEach items="${categories}" var="item">
									<option value="${item.id}"><liferay-ui:message key="${item.name}" /></option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
						    <label class="label-control" for="subCategoryId"><liferay-ui:message key="auction.subcategory.label" /></label>
							<select class="selectpicker form-control" id="subCategoryIdSelect" name="subCategoryIdSelect" class="required">
								<option value="">${selectTitle}</option>
							</select>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-12">
				  		<div class="form-group">
					        <label class="label-control" for="description"><liferay-ui:message key="auction.description.label" /></label>
					        <textarea rows="5" class="form-control" id="description" name="description">${auction.description}</textarea>
						</div>
						<div class="col-md-6 form-group">
							<label class="label-control" for="technicalDataList"><liferay-ui:message key="auction.technical.data.label" /></label>
							<div id="technicalDataList"></div>
						</div>
						<div class="col-xs-12 form-group">
					  		<a class="btn btn-primary pull-right" type="submit" id="create-auction-submit"><liferay-ui:message key="submit"/></a>
					  	</div>
					</div>
		  	 	</div>     
	     </form>
	</div>
</div>

<input type="hidden" id="type" value="${type}">
<script>
	var technicalDataJson = ${auction.technicalData};
</script>
<script src="<c:url value="/js/module/file-upload.js" />"></script>	 
<script src="<c:url value="/js/module/create-auction.js" />"></script>	 