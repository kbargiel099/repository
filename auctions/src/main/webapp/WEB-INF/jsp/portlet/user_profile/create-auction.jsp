<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

<portlet:resourceURL id="getSubCategories" var="getSubCategories">
</portlet:resourceURL>

<portlet:actionURL var="submit">
		<portlet:param name="action" value="createNewAuction"/>
</portlet:actionURL>


<input type="hidden" id="getSubCategoriesUrl" value="${getSubCategories}"></input>

<div class="container-fluid">

	<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>

		<div class="col-xs-12 col-sm-8 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="auction.form.label" /></h4>
		</div>
		  <div class="container">	
		  	<c:set var="selectTitle">
		  		<liferay-ui:message key="choose" />
		  	</c:set>
	      	<form:form id="create-new-auction-form" method="POST" action="${submit}" modelAttribute="newAuction">
			<form:input type="hidden" path="id" name="id"></form:input>
			<form:input type="hidden" path="imageName" id="imageName" name="imageName"></form:input>
			<form:input type="hidden" path="imageData" id="imageData" name="imageData"></form:input>
			<form:input type="hidden" path="videoName" id="videoName" name="videoName"></form:input>
			<form:input type="hidden" path="videoData" id="videoData" name="videoData"></form:input>
			<div class="col-xs-12 col-sm-8 col-md-4">
				<div class="form-group">
		           <form:label class="label-control" path = "name" name="name"><liferay-ui:message key="auction.name.label" /></form:label>
		           <form:input type="text" class="form-control" path = "name" id="name" name="name"></form:input>
				</div>
				<div class="form-group">
		           <form:label class="label-control" path="auctionTypeId" name="auctionTypeId"><liferay-ui:message key="auction.type.label" /></form:label>
					<select class="selectpicker form-control" id="auctionTypeIdSelect" title="${selectTitle}"> 		
						<c:forEach items="${auctionTypes}" var="item">
							<option value="${item.id}"><liferay-ui:message key="${item.name}" /></option>
						</c:forEach>
		           </select>
		           <form:input type="hidden" path="auctionTypeId" id="auctionTypeId" name="auctionTypeId" value=""/>
				</div>
				<div class="form-group">
		           <form:label class="label-control" path = "subjectQuantity" name="subjectQuantity"><liferay-ui:message key="auction.subjectQuantity.label" /></form:label>
		           <form:input type="text" class="form-control" path="subjectQuantity" id="subjectQuantity" name="subjectQuantity"></form:input>
				</div>
				<div class="form-group">
					<label class="label-control" name="attachImage" ><liferay-ui:message key="auction.attachImage.label" /></label>
				</div>
				<div>
					<input type="file" name="imageFilechooser" id="imageFilechooser" onchange="loadFile(event)"/>
					<div class="form-group">
						<img id="output" height="100%" width="100%"/>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-8 col-md-4">
				<div class="form-group">
		           <form:label class="label-control" path = "endDate" name="endDate"><liferay-ui:message key="auction.endDate.label" /></form:label>
		           <input type="date" class="form-control"></input>
		           <form:input type="hidden" path="endDate" id="endDate" name="endDate"></form:input>

				</div>
				<div class="form-group">
		           <form:label class="label-control" path = "subjectPrice" name="subjectPrice"><liferay-ui:message key="auction.subject.price.label" /></form:label>
		           <input type="text" class="form-control" id="price" name="price" value="0"></input>
		           <form:input type="hidden" path = "subjectPrice" id="subjectPrice" name="subjectPrice"></form:input>
				</div>
				<div class="form-group">
				    <form:label class="label-control" path = "categoryId" name="categoryId"><liferay-ui:message key="auction.category.label" /></form:label>
					<select class="selectpicker form-control" id="categoryIdSelect" title="${selectTitle}">
						<c:forEach items="${categories}" var="item">
							<option value="${item.id}"><liferay-ui:message key="${item.name}" /></option>
						</c:forEach>
						<form:input type="hidden" path="categoryId" id="categoryId" name="categoryId" value=""/>
					</select>
				</div>
				<div class="form-group">
				    <form:label class="label-control" path = "subCategoryId" name="subCategoryId"><liferay-ui:message key="auction.subcategory.label" /></form:label>
					<select class="selectpicker form-control" id="subCategoryIdSelect" title="${selectTitle}">
					</select>
					<form:input type="hidden" path="subCategoryId" id="subCategoryId" name="subCategoryId" value=""/>
				</div>
		  		<div class="form-group">
			        <form:label class="label-control" path = "description" name="description"><liferay-ui:message key="auction.description.label" /></form:label>
			        <form:textarea  rows="5" class="form-control" id="description" path = "description" name="description"></form:textarea>
				</div>
				<div class="form-group">
			  		<input class="btn btn-primary pull-right" type="submit" value="<liferay-ui:message key="submit"/> ">
			  	</div>
	  		</div>          
	      </form:form>
	   </div>
</div>
	 
<script  type="text/javascript">

	var reader = new FileReader();
	var subCategories;
	jQuery(document).ready(function(){
		jQuery('.selectpicker').selectpicker();
		sendRequest(jQuery("#getSubCategoriesUrl").val(),
				function(data){subCategories = data;});

	});
	
	jQuery("#categoryIdSelect").change(function(){
		var id = jQuery("#categoryIdSelect option:selected").val();
		jQuery("#categoryId").val(id);
		jQuery("#subCategoryIdSelect").html('');

		for(var i=0;i<subCategories.length;i++){
			var item = subCategories[i];
			if(item.categoryId == id){
				var option = '<option value="'+item.id+'">'+item.name+'</option>';
				jQuery("#subCategoryIdSelect").append(option);
			}
		}
		jQuery("#subCategoryIdSelect").selectpicker('refresh');
	});
	
	jQuery("#subCategoryIdSelect").change(function(){
		var id = jQuery("#subCategoryIdSelect option:selected").val();
		jQuery("#subCategoryId").val(id);
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
	
	var loadFile = function(event) {
	    reader.onload = function(){
	        var output = document.getElementById('output');
	        var file = document.getElementById("imageFilechooser").files[0];   
	        output.src = reader.result;   
			jQuery("#imageName").val(file.name);
			jQuery("#imageData").val(getBase64(reader.result));
	    };
	    reader.readAsDataURL(event.target.files[0]);
	};
</script>