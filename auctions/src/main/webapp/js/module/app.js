var stompClient = null;
var auctionId = jQuery('#id').val();
var endDate = jQuery('#endDate').val();
var quantity = jQuery('#quantity').val();
var newPrice = jQuery('#currentPrice').val();
var senderClient = false;
var isWait = false;
var isQuickPurchase = jQuery('#type').val() == 'quick_purchase' ? true : false;

jQuery(document).ready(function(){
	connect2();
	provideValidation();
	showObservation();
	getAllOffers();
});

function getAllOffers(){
	if(!isQuickPurchase){
	    jQuery.ajax({
	    	"url": jQuery('#getAllOffersUrl').val(),
	    	"type": "POST",
	    	"success": function(data){
	    		if(data.success){
		    		var res = JSON.parse(data.offers);
		    		if(res.length == 0){
		    			jQuery('#auction-notify').hide();
		    			jQuery('#empty-list-msg').html('Jeszcze nikt nie dodał oferty');
		    		}else{
			    		for(var i=0;i<res.length;i++){
			    			addOfferToList(res[i]);
			    		}
		    		}
	    		}else{
	    			alert("Wystąpił błąd");
	    		}
	    	}
	    });
	}
}

function showObservation(){
	if(Liferay.ThemeDisplay.isSignedIn()){
		if(jQuery('#isObserved').val() == 'true'){
			jQuery('#removeObservation').show();
		}else{
			jQuery('#createObservation').show();
		}
	}
}

function proceedIfUserIsLogged(callback){
	if(Liferay.ThemeDisplay.isSignedIn()){
		callback();
	}else{
		responsiveNotify(jQuery('#userIsNotSignedInMsg').val());
	}
}

jQuery('#create-observe').click(function(){
	proceedIfUserIsLogged(function(){
	    jQuery.ajax({
	    	"url": jQuery('#createObservationUrl').val(),
	    	"type": "POST",
	    	"success": function(data){
	    		if(data.success){
		 			alert("Subskrybcja udana");
		 			jQuery('#createObservation').hide();
		 			jQuery('#removeObservation').show();
	    		}else{
	    			alert("Wystąpił błąd");
	    		}
	    	}
	    });
	});
});

jQuery('#remove-observe').click(function(){
	proceedIfUserIsLogged(function(){
	    jQuery.ajax({
	    	"url": jQuery('#removeObservationUrl').val(),
	    	"type": "POST",
	    	"success": function(data){
	    		if(data.success){
		 			alert("Subskrybcja usunięta");
		 			jQuery('#removeObservation').hide();
		 			jQuery('#createObservation').show();
	    		}else{
	    			alert("Wystąpił błąd");
	    		}
	    	}
	    });
	});
});


function connect2() {
    var socket = new SockJS('http://192.168.0.15:8143/notification');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/message/notify/'+jQuery('#id').val(), function (data) {
        	var res = JSON.parse(data.body);
        	console.log(res);
        	if(res.success == true){

        		if(isQuickPurchase){
        			showRefreshPageBtn();
        		}else{
        			addOfferToList(res);
            		jQuery('#currentPrice').val(res.price);
            		jQuery('#price').html('Aktualna cena - ' + currency(res.price/100) + ' ' + jQuery('#currency').val());
            		responsiveNotify(res.username + " " + jQuery('#successMsg').val());
        		}

        		if(senderClient){
        			isWait = false;
        		}
        		
        	}else if(senderClient == true){
        		responsiveNotify(jQuery('#errorCode1').val());
	        	senderClient = false;
	        	isWait = false;
        	}
        	updatePriceValidation();
        });
    });
}

function addOfferToList(res) {
	jQuery('#empty-list-msg').hide();
	jQuery('#auction-notify').show();
	row = jQuery("<tr></tr>");
	col1 = jQuery("<td>"+ res.username +"</td>");
	col2 = jQuery("<td>"+ res.quantity +"</td>");
	col3 = jQuery("<td>"+ currency(res.price/100) +"</td>");
	row.append(col1,col2,col3).prependTo('#auction-notify table tbody');
}

function sendForm2() {
	if(!isWait){
	    isWait = true;
	    senderClient = true;
	    stompClient.send("/app/update/" + auctionId, {}, JSON.stringify({'userId': userId,'username': username,
	    	'price': newPrice,'endDate': endDate,'quantity': quantity}));
	    jQuery('#validation-info').hide();
	}
}

function goToConfirmationView() {
	var params = [{'name':'auctionId',  'value':auctionId},
				  {'name':'sellerId',   'value':jQuery('#seller-id').val()},
				  {'name':'auctionName','value':jQuery('#auctionName').val()},
				  {'name':'price',      'value':newPrice},
				  {'name':'endDate',    'value':endDate},
				  {'name':'quantity',   'value':quantity}];
	window.location.href = buildWithParams(jQuery('#confirmPurchaseUrl').val(),params);
}

function buildWithParams(url,params){
	for(var i=0;i<params.length;i++){
		url = buildUrl(url,params[i].name,params[i].value);
	}
	return url;
}

jQuery('#price-input').change(function(){
	newPrice = jQuery('#price-input').val() * 100;
});

jQuery('#quantity-input').change(function(){
	quantity = jQuery('#quantity-input').val();
});

function updatePriceValidation(){
	jQuery("#price-input").rules("remove","min");
	jQuery("#price-input").rules("add",{
        min: (jQuery('#currentPrice').val()/100 + 1)
    });
}

	jQuery( "#raiseStakeBtn" ).click(function() { 
		if(Liferay.ThemeDisplay.isSignedIn()){
			if(validate()){
				sendForm2();
			}else{
			    jQuery('#validation-info').show();
			}
		}else{
			responsiveNotify(jQuery('#userIsNotSignedInMsg').val());
		}
	});
	jQuery("#quickPurchaseBtn" ).click(function() { 
		if(Liferay.ThemeDisplay.isSignedIn()){
			if(validate()){
				goToConfirmationView();
			}else{
			    jQuery('#validation-info').show();
			}
		}else{
			responsiveNotify(jQuery('#userIsNotSignedInMsg').val());
		}
	});

function showRefreshPageBtn(){
	jQuery('#refreshPage').show();
	jQuery("#refreshPage" ).click(function() { 
		window.location.reload();
	});
}

function provideValidation(){
	
	var priceInput = document.getElementById('price-input') ? true : false;
	var quantityInput = document.getElementById('quantity-input') ? true : false;
	
	if(priceInput && quantityInput){
		jQuery("#proceed-form").validate({
			    rules: {
			      "quantity-input": {
			        required: true,
			        number: true,
			        range: [1,quantity]
			      },
			      "price-input": {
			        required: true,
			        number: true
			      }
			    }
			  });
		
		jQuery("#price-input").rules("add",{
	        min: (jQuery('#currentPrice').val()/100 + 1)
	    });
		
	} else if(quantityInput){
		 jQuery("#proceed-form").validate({
				rules: {
			      "quantity-input": {
			        required: true,
			        number: true,
			        range: [1,quantity]
			      }
			    }
			  });

	} else if(priceInput){
		 jQuery("#proceed-form").validate({
		  		rules: {
			      "price-input": {
			        required: true,
			        number: true
			      }
			    }
			  });
		 
			jQuery("#price-input").rules("add",{
		        min: (jQuery('#currentPrice').val()/100 + 1)
		    });
	}
	
}

function validate(){
	return jQuery('#proceed-form').valid();
}
