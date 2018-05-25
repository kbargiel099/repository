<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

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


<div class="container-fluid">

	<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>
	
		<div class="col-xs-12 col-sm-8 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="auction.form.label" /></h4>
		</div>
		  <div class="container">	
		  	<c:set var="selectTitle">
		  		<liferay-ui:message key="choose" />
		  	</c:set>
		  	
	      	<form id="create-new-auction-form">
				<input type="hidden" name="id" value="0"></input>
				<input type="hidden" id="imageName" name="imageName" value=""/>
				<input type="hidden" id="categoryId" name="categoryId" value=""/>
				<input type="hidden" id="auctionTypeId" name="auctionTypeId" value=""/>
			    <input type="hidden" id="endDate" name="endDate"></input>
			    <input type="hidden" id="subjectPrice" name="subjectPrice"></input>
				<input type="hidden" id="subCategoryId" name="subCategoryId" value=""/>
				<input type="hidden" id="technicalData" name="technicalData" value=""/>
				<div class="col-xs-12 col-sm-12 col-md-8">
					<div class="col-xs-12 col-sm-12 col-md-6">
						<div class="form-group">
				           <label class="label-control" name="name"><liferay-ui:message key="auction.name.label" /></label>
				           <input type="text" class="form-control" id="name" name="name"></input>
						</div>
						<div class="form-group">
				           <label class="label-control" name="auctionTypeId"><liferay-ui:message key="auction.type.label" /></label>
							<select class="selectpicker form-control" id="auctionTypeIdSelect" title="${selectTitle}"> 		
								<c:forEach items="${auctionTypes}" var="item">
									<option value="${item.id}"><liferay-ui:message key="${item.name}" /></option>
								</c:forEach>
				           </select>
						</div>
						<div class="form-group">
				           <label class="label-control" name="subjectQuantity"><liferay-ui:message key="auction.subjectQuantity.label" /></label>
				           <input type="text" class="form-control" id="subjectQuantity" name="subjectQuantity"></input>
						</div>
						<div class="form-group">
							<label class="label-control" name="attachImage" ><liferay-ui:message key="auction.attachImage.label" /></label>
						</div>
						<div>
							<input type="file" name="imageFilechooser" id="imageFilechooser" onchange="loadFile(event)"/>
							<div id="images" class="form-group">
<!-- 								<img id="output" height="100%" width="100%"/> -->
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-6">
						<div class="form-group">
				           <label class="label-control" name="endDate"><liferay-ui:message key="auction.endDate.label" /></label>
				           <input type="date" class="form-control"></input>
						</div>
						<div class="form-group">
				           <label class="label-control" for="subjectPrice"><liferay-ui:message key="auction.subject.price.label" /></label>
				           <input type="text" class="form-control" id="price" value="0"></input>
						</div>
						<div class="form-group">
						    <label class="label-control" for="categoryId"><liferay-ui:message key="auction.category.label" /></label>
							<select class="selectpicker form-control" id="categoryIdSelect" title="${selectTitle}">
								<c:forEach items="${categories}" var="item">
									<option value="${item.id}"><liferay-ui:message key="${item.name}" /></option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
						    <label class="label-control" for="subCategoryId"><liferay-ui:message key="auction.subcategory.label" /></label>
							<select class="selectpicker form-control" id="subCategoryIdSelect" title="${selectTitle}">
							</select>
						</div>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-12">
				  		<div class="form-group">
					        <label class="label-control" for="description"><liferay-ui:message key="auction.description.label" /></label>
					        <textarea rows="5" class="form-control" id="description" name="description"></textarea>
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
<script src="<c:url value="/js/module/file-upload.js" />"></script>	 
<script src="<c:url value="/js/module/create-auction.js" />"></script>	 
<script type="text/javascript">

	var subCategories;
	jQuery(document).ready(function(){
		sendRequest(jQuery("#getSubCategoriesUrl").val(),
				function(data){subCategories = data;});
		jQuery('.selectpicker').selectpicker();
	});
	
	jQuery("#categoryIdSelect").change(function(){
		var id = jQuery("#categoryIdSelect option:selected").val();
		jQuery("#categoryId").val(id);
		jQuery("#subCategoryIdSelect").html('');
		
		for(var i=0;i<subCategories.length;i++){
			var item = subCategories[i];
			if(item.categoryId == id){
				var option = '<option value="'+item.id+'">'+ Liferay.Language.get(item.name) +'</option>';
				jQuery("#subCategoryIdSelect").append(option);
			}
		}
		jQuery("#subCategoryIdSelect").selectpicker('refresh');
	});
	
	jQuery("#subCategoryIdSelect").change(function(){
		var id = jQuery("#subCategoryIdSelect option:selected").val();
		jQuery("#subCategoryId").val(id);
		getTechnicalData(id);
		//files.splice(1,1);
	});
	
	jQuery("#auctionTypeIdSelect").change(function(){
		var id = jQuery("#auctionTypeIdSelect option:selected").val();
		jQuery("#auctionTypeId").val(id);
	});
	
	jQuery("input[type='date']").change(function(){
		var date = new Date(this.value);
		var timestamp = date.getTime();
		jQuery("#endDate").val(timestamp);
	});
	
	jQuery("input[id='price']").change(function(){
		var value = jQuery("#price").val();
		jQuery('#subjectPrice').val(value * 100);
	});
</script>