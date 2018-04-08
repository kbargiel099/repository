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
			<div class="col-xs-12 col-sm-8 col-md-4">
				<div class="form-group">
		           <form:label class="label-control" path = "name" name="name"><liferay-ui:message key="auction.name.label" /></form:label>
		           <form:input type="text" class="form-control" path = "name" id="name" name="name"></form:input>
				</div>
				<div class="form-group">
		           <form:label class="label-control" path="auctionTypeId" name="auctionTypeId"><liferay-ui:message key="auction.type.label" /></form:label>
		           <form:select class="selectpicker form-control" path="auctionTypeId" id="auctionTypeId" name="auctionTypeId" title="${selectTitle}">   		
						<c:forEach items="${auctionTypes}" var="item">
							<option value="${item.id}"><liferay-ui:message key="${item.name}" /></option>
						</c:forEach>
		           </form:select>
				</div>
				<div class="form-group">
		           <form:label class="label-control" path = "subjectQuantity" name="subjectQuantity"><liferay-ui:message key="auction.subjectQuantity.label" /></form:label>
		           <form:input type="text" class="form-control" path="subjectQuantity" id="subjectQuantity" name="subjectQuantity"></form:input>
				</div>
				<div class="form-group">
					<label class="label-control" name="attachImage" ><liferay-ui:message key="auction.attachImage.label" /></label>
				</div>
				<div>
					<!-- <input type="file" name="imageFilechooser" id="imageFilechooser" onchange="startRead()"/> -->
					<input type="file" name="imageFilechooser" id="imageFilechooser" onchange="loadFile(event)"/>
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
					<img id="output" height="100%" width="100%"/>
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
	  		</div>
	  		<div class="col-xs-12 col-sm-8 col-md-8">
	  			<div class="form-group">
		           <form:label class="label-control" path = "description" name="description"><liferay-ui:message key="auction.description.label" /></form:label>
		           <form:textarea  rows="5" class="form-control" id="description" path = "description" name="description"></form:textarea>
				</div>
	  		</div>
	  		<div class="row col-xs-12 col-sm-8 col-md-8">
				<div class="form-group">
			        <!-- <input class="btn btn-primary pull-right" type="submit" onclick="createComfirmation(event)" value="<liferay-ui:message key="submit"/> "> -->
			  		<input class="btn btn-primary pull-right" type="submit" value="<liferay-ui:message key="submit"/> ">
			  	</div>
		  	</div>            
	      </form:form>
	   </div>
</div>
	 
<script  type="text/javascript">

	var subCategories;
	jQuery(document).ready(function(){
		jQuery('.selectpicker').selectpicker();
		var url = jQuery("#getSubCategoriesUrl").val();
	    jQuery.ajax({
	    	"url": url,
	    	"type": "POST",
	    	"success": function(data){
	    		subCategories = data;
	    	}
	    });
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
	
	jQuery("input[type='date']").change(function(){
		var date = new Date(this.value);
		var timestamp = date.getTime();
		jQuery("#endDate").val(timestamp);
	});
	
	jQuery("input[id='price']").change(function(){
		var value = jQuery("#price").val();
		jQuery('#subjectPrice').val(value * 100);
	});
	
	function startRead() {
		var file = document.getElementById("imageFilechooser").files[0];
		if (file) {
		        alert("Name: " + file.name + "\n" + "Last Modified Date :" + file.lastModifiedDate);
		    }
	}
	
	function createComfirmation(event){
		event.preventDefault();
		var message = Liferay.Language.get("auction.message.success");
		var url = jQuery("#getBoughtUrl").val();
		//showAlert("alert alert-success",message,url,false);
		bootbox.alert(message)
		//showSaving();
	}
	
	var loadFile = function(event) {
	    var reader = new FileReader();
	    reader.onload = function(){
	        var output = document.getElementById('output');
	        var file = document.getElementById("imageFilechooser").files[0];   
	        output.src = reader.result;   
			jQuery("#imageName").val(file.name);
			jQuery("#imageData").val(getBase64Image(reader.result));
	    };
	    reader.readAsDataURL(event.target.files[0]);
	};

	 /* var loadFile = function(event) {
		    var reader = new FileReader();
		    reader.onload = function(){
		      var output = document.getElementById('output');
		      output.src = reader.result;
		      console.log(reader.result);
		      console.log(output.src);
			    jQuery.ajax({
			    	"url": jQuery("#getImageUrl").val(),
			    	"type": "POST",
			    	"data":{
			    		"image" : JSON.stringify(getBase64Image(reader.result))
			    	},
			    	"success": function(data){
			    		console.log(data);
			    	}
			    });
		    };
		    reader.readAsDataURL(event.target.files[0]);
	  };*/
	  
	  function getBase64Image(img) {
	  	return img.replace(/^data:(image|video)\/(png|jpg|jpeg|mp4);base64,/, "");
	}
	  
</script>