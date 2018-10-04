
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
		      console.log(form);
		    }
		  });
		});
		
 	function createElement(data){
		var url = jQuery('#detailsUrl').val();
		var elem =  '<div class="category-view-auction row">'
					+'<div class="col-xs-12 col-sm-12 col-md-4">'
					+'<a class="text-center" href="#">'
					+'<img class="image image-120 img-center" src="/images/'+ data.imageName +'" />'
					+'</a></div>'
					+'<div class="col-xs-12 col-sm-12 col-md-4">'
					+'<strong><h4>'+ data.name +'</h4></strong>'
					+'<h4>'+ jQuery('#price-label').val() +' - '+ currency(data.subjectPrice/100) +' '+ jQuery('#currency-label').val() +'</h4></div>'
					+'<div class="col-xs-12 col-sm-12 col-md-4">'
					+'<input type="hidden" name="id" value="'+ data.id +'" />'
					+'<button class="btn btn-info" onclick="showDetails(this)"><strong>'+ jQuery('#details-label').val() +'</strong></button>'
					+'</div></div>';
		return elem;	
 	} 
	
 	function showDetails(obj){
 		var url = jQuery('#detailsUrl').val();
 		var id = jQuery(obj).parent().find('input').val();
 		location.href = buildUrl(url,'id',id);
 	}
 	
 	function getBySubcategory(obj){
 		var url = jQuery('#getBySubcategoryUrl').val();
 		var id = jQuery(obj).parent().find('input').val();
  		jQuery.ajax({
			"url" : url,
			"type" : "POST",
			"data" : {
				"id" : id
			},
			"success" : function(data){
 				var elements = jQuery('#elements');
				elements.html('');
				if(data.auctions.length > 0){
					jQuery(data.auctions).each(function(index,res){
						elements.append(createElement(res));
					});
				}else{
					elements.append('<p>'+ Liferay.Language.get('empty.list.msg') +'<p/>');
				}
			} 
		});
 		
 	}
 	
	function searchForMatching(){
		var url = jQuery("#searchTextUrl").val();
		var searching = JSON.stringify(jQuery("#searchingForm")
				.serializeObject());
		console.log(searching);
  		jQuery.ajax({
			"url" : url,
			"type" : "POST",
			"data" : {
				"searchingForm" : searching
			},
			"success" : function(data){
				var elements = jQuery('#elements');
				elements.html('');
				if(data.auctions.length > 0){
					jQuery(data.auctions).each(function(index,res){
						elements.append(createElement(res));
					});
				}else{
					elements.append('<p>'+ Liferay.Language.get('empty.list.msg') +'<p/>');
				}
			}
		});
	}	