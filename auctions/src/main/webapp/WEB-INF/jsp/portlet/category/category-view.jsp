<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >

<portlet:renderURL var="details">
	<portlet:param name="page" value="auctionDetails"/>
</portlet:renderURL>
<input type="hidden" id="detailsUrl" value="${details}"/>

<portlet:resourceURL id="searchText" var="searchText">
</portlet:resourceURL>
<input type="hidden" id="searchTextUrl" value="${searchText}"/>

<div class="container-fluid">
	<div id="category-view-menu" class="col-xs-12 col-sm-8 col-md-3">
		<h2 class="category-view-title text-center">${category}</h2>	
		<ul class="horizontal-menu">
		  <c:forEach items="${subCategories}" var="item">
		  	<li class="horizontal-menu-item"><a value="" href="javascript:void()" onclick="">${item.name}</a></li>
		  </c:forEach>
		</ul>
		<form id="searchingForm">
			<div class="form-group text-center">
				<label class="label-control" for="searchingText">Szukana fraza</label>
				<input type="text" id="searchingText" name="searchingText" class="form-control"/>
			</div>
			<div class="form-group text-center">
				<label class="label-control" for="minPrice">Przedział cenowy</label>
				<div class="row">
					<div class="col-xs-5">
						<input type="text" id="minPrice" name="minPrice" class="form-control"/>
					</div>
					<div class="col-xs-2">
						<label class="text-center"> - </label>
					</div>
					<div class="col-xs-5">
						<input type="text" id="maxPrice" name="maxPrice" class="form-control"/>
					</div>
				</div>
			</div>
			<button id="searchingBtn" class="btn btn-primary form-control" onclick="searchForMatching()">Szukaj</button>
		</form>
	</div>
		<div class="col-xs-12 col-sm-12 col-md-8">
			<div id="elements">
				<c:forEach items="${auctions}" var="item">
				
				<div class="category-view-auction row">
					<div class="col-xs-12 col-sm-12 col-md-4">
						<a class="text-center" href="#">
							<img src="<c:url value="/images/${item.imageName}" />" height="160" width="100%" />
						</a>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4">
						<strong><h4>${item.name}</h4></strong>
						<c:set var = "balance" value = "${item.subjectPrice/100}" />
						<h4>Cena - <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${balance}" type="currency"/></h4> 
					</div>
					<div class="col-xs-12 col-sm-12 col-md-4">
						<input type="hidden" name="id" value="${item.id}" />
						<button class="btn btn-info" onclick="showDetails(this)" ><strong>Szczegóły oferty</strong></button>	
					</div>
				</div>
				</c:forEach>
			</div>
		</div>
</div>

<script type="text/javascript">

	//jQuery(document).ready(function(){
	//	var inputs = jQuery('input.auction');
	//	var imgs = jQuery('img.auction');
	//	for(var i=0;i<inputs.length;i++){
	//		imgs[i].src = "data:image/jpg;base64,"+inputs[i].value;
	//	}
	//});
		jQuery(function() {
		  jQuery("#searchingForm").validate({
		    rules: {
		      minPrice: {
		        number: true
		      },
		      maxPrice: {
		        number: true
		      }
		    },
		    submitHandler: function(form) {
		      //form.submit();
		      console.log(form);
		    }
		  });
		});
		
	function getDetailsUrl(){
		return jQuery('#detailsUrl').val();
	}
 	function createElement(data){
		var url = getDetailsUrl();
		var elem =  '<div class="category-view-auction row">'
					+'<div class="col-xs-12 col-sm-12 col-md-4">'
					+'<a class="text-center" href="#">'
					+'<img src="<c:url value="/images/'+ data.imageName +'" />" height="160" width="100%" />'
					+'</a></div>'
					+'<div class="col-xs-12 col-sm-12 col-md-4">'
					+'<strong><h4>'+ data.name +'</h4></strong>'
					+'<h4>Cena - '+ data.subjectPrice/100 +'</h4></div>'
					+'<div class="col-xs-12 col-sm-12 col-md-4">'
					+'<input type="hidden" name="id" value="'+ data.id +'" />'
					+'<button class="btn btn-info" onclick="showDetails(this)"><strong>Szczegóły oferty</strong></button>'
					+'</div></div>';
		return elem;	
 	} 
	
 	function showDetails(obj){
 		var url = getDetailsUrl();
 		var id = jQuery(obj).parent().find('input').val();
 		location.href = buildUrl(url,'id',id);
 	}
 	
	function searchForMatching(){
		var url = jQuery("#searchTextUrl").val();
		var searching = JSON.stringify(jQuery("#searchingForm")
				.serializeObject());
  		jQuery.ajax({
			"url" : url,
			"type" : "POST",
			"data" : {
				"searchingForm" : searching
			},
			"success" : function(data){
				var elements = jQuery('#elements');
				elements.html('');
				jQuery(JSON.parse(data.auctions)).each(function(index,res){
					elements.append(createElement(res));
				});
			}
		});
	}
	
	var buildUrl = function(base, key, value) {
	    var separator = (base.indexOf('?') > -1) ? '&' : '?';
	    return base + separator + key + '=' + value;
	}
	
</script>
