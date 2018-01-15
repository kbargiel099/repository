<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/selectpicker-custom.css" />" >

<portlet:renderURL var="getBoughtRender">
	<portlet:param name="page" value="getBought"/>
</portlet:renderURL>
<portlet:renderURL var="getSoldRender">
	<portlet:param name="page" value="getSold"/>
</portlet:renderURL>
<portlet:renderURL var="mySettingsRender">
	<portlet:param name="page" value="mySettings"/>
</portlet:renderURL>
<portlet:renderURL var="createNewAuctionRender">
	<portlet:param name="page" value="createNewAuction"/>
</portlet:renderURL>

<portlet:actionURL var="submit">
		<portlet:param name="action" value="createNewAuction"/>
</portlet:actionURL>

<input type="hidden" id="getBoughtUrl" value="${getBoughtRender}"></input>
<input type="hidden" id="getSoldUrl" value="${getSoldRender}"></input>
<input type="hidden" id="mySettingsUrl" value="${mySettingsRender}"></input>


<div class="container-fluid">

<!--  <div class="col-xs-12 col-sm-8 col-md-3">
		<div class="btn-group btn-group-vertical">
		 	<a href="${getBoughtRender}" class="btn btn-link">Zakupione</a>
		 	<a href="${getSoldRender}" class="btn btn-link">Sprzedane</a>
		 	<a href="${mySettingsRender}" class="btn btn-link">Moje ustawienia</a>
		</div>
	</div> -->
 <div id="user-profile-menu" class="col-xs-12 col-sm-8 col-md-3">	
		<ul class="horizontal-menu">
		  <li class="horizontal-menu-item"><a href="${getBoughtRender}">Zakupione</a></li>
		  <li class="horizontal-menu-item"><a href="${getSoldRender}">Sprzedane</a></li>
		  <li class="horizontal-menu-item"><a href="${mySettingsRender}">Moje ustawienia</a></li>
		  <li class="horizontal-menu-item"><a href="${createNewAuctionRender}">Wystaw przedmiot</a></li>
		</ul>
	</div>
	
	<c:if test="${boughtView}">
		<div class="col-xs-12 col-sm-8 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="auction.bought.subjects.label" /></h4>
			<div>
				  <c:forEach items="${subjects}" var="i">
					 <div class="row user-subject">
						 <div class="col-xs-12 col-sm-8 col-md-4">
			          	 	<h4><strong>${i.name}</strong> - ${i.subjectName}</h4>
			          	 </div>
			          	 <div class="col-xs-12 col-sm-8 col-md-4 pull-right">
							<img src="<c:url value="/images/${i.imageName}" />" alt="obrazek" height="160" width="100%">
						 </div>
					 </div>
		     	  </c:forEach>
			</div>
		</div>
	</c:if>
	<c:if test="${soldView}">
		<div class="col-xs-12 col-sm-8 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="auction.sold.subjects.label" /></h4>
		</div>
	</c:if>
	<c:if test="${mySettingsView}">
		<div class="col-xs-12 col-sm-8 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="auction.my.settings.label" /></h4>
		</div>
	</c:if>
	<c:if test="${createNewAuctionView}">
		<div class="col-xs-12 col-sm-8 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="auction.form.label" /></h4>
		</div>
		  <div class="container">	
	      	<form:form id="create-new-auction-form" method = "POST" action = "${submit}" modelAttribute="newAuction">
			<form:input type="hidden" path ="id" name="login"></form:input>
			<div class="col-xs-12 col-sm-8 col-md-4">
				<div class="form-group">
		           <form:label class="label-control" path = "name" name="name"><liferay-ui:message key="auction.name.label" /></form:label>
		           <form:input type="text" class="form-control" path = "name" id="name" name="name"></form:input>
				</div>
				<div class="form-group">
		           <form:label class="label-control" path="auctionType" name="auctionType"><liferay-ui:message key="auction.type.label" /></form:label>
		           <form:select class="selectpicker form-control" path="auctionType" id="auctionType" name="auctionType">   		
		         		<form:option value="0">Szybki zakup</form:option>
		           		<form:option value="1">Klasyczna</form:option>
		           		<form:option value="2">Z ceną minimalną</form:option>
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
		           <form:input type="date" class="form-control" path="endDate" id="endDate" name="endDate"></form:input>
				</div>
				<div class="form-group">
				 <form:label class="label-control" path = "acceptedPaymentMethods" name="acceptedPaymentMethods"><liferay-ui:message key="auction.accepted.payment.method.label" /></form:label>
					<select class="selectpicker form-control" name = "acceptedPaymentMethods" id="paymentMethodSelect" multiple>
					  <option value="0"><liferay-ui:message key="auction.bank.transfer.label" /></option>
					  <option value="1"><liferay-ui:message key="auction.courier.delivery.label" /></option>
					  <option value="2"><liferay-ui:message key="auction.upon.receipt.label" /></option>
					</select>
				</div>
				<div class="form-group">
		           <form:label class="label-control" path = "subjectPrice" name="subjectPrice"><liferay-ui:message key="auction.subject.price.label" /></form:label>
		           <form:input type="text" class="form-control" path = "subjectPrice" id="subjectPrice" name="subjectPrice"></form:input>
				</div>
				<div class="form-group">
					<img id="output" height="100%" width="100%"/>
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
			        <input class="btn btn-primary pull-right" type="submit" onclick="createComfirmation(event)" value="<liferay-ui:message key="submit"/> ">
			  	</div>
		  	</div>            
	      </form:form>
	   </div>
	</c:if>
</div>

<script  type="text/javascript">

function open(_str_tag) {
	return document.getElementsByTagName(_str_tag);
	}

	function create(_str_tag) {
	    return document.createElement(_str_tag);
	}


	function open_file() {
	    _el_upload = $create("input");
	    _el_body = tag("body")[0];
	    _el_upload.setAttribute("type", "file");
	    _el_upload.style.visibility = "hidden";
	    _el_upload.setAttribute("multiple", "multiple");
	    _el_upload.setAttribute("position", "absolute");
	    _el_body.appendChild(_el_upload);
	    _el_upload.click();
	    _el_body.removeChild(_el_upload);
	    return _el_upload.files;
	}

	function startRead() {
		var file = document.getElementById("imageFilechooser").files[0];
		if (file) {
		        //  getAsText(file);
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
		      output.src = reader.result;
		    };
		    reader.readAsDataURL(event.target.files[0]);
	  };
</script>