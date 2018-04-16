var stompClient = null;
var username = Liferay.ThemeDisplay.getUserName();
var userId = Liferay.ThemeDisplay.getUserId();
var auctionId;
var endDate;
var quantity;
var newPrice;
var senderClient = false;
var isWait = false;
var isQuickPurchase = false;

jQuery(document).ready(function(){
	connect();
	auctionId = jQuery('#id').val();
	endDate = jQuery('#endDate').val();
	quantity = jQuery('#quantity').val();
	newPrice = jQuery('#currentPrice').val();
	isQuickPurchase = jQuery('#type').val() == 'quick_purchase' ? true : false;
	provideValidation();
});

function connect() {
    var socket = new SockJS('http://192.168.0.15:8143/notification');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/notify/'+jQuery('#id').val(), function (data) {
        	var res = JSON.parse(data.body);
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
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function addOfferToList(res) {
	row = jQuery("<tr></tr>");
	col1 = jQuery("<td>"+ res.username +"</td>");
	col2 = jQuery("<td>"+ res.quantity +"</td>");
	col3 = jQuery("<td>"+ currency(res.price/100) +"</td>");
	row.append(col1,col2,col3).prependTo('#auction-notify table tbody');
}

function sendForm() {
	if(!isWait){
	    isWait = true;
	    senderClient = true;
	    stompClient.send("/app/update/" + auctionId, {}, JSON.stringify({'userId': userId,'username': username,
	    	'price': newPrice,'endDate': endDate,'quantity': quantity}));
	    jQuery('#validation-info').hide();
	}
}

function sendFormQuickPurchase() {
	if(!isWait){
	    isWait = true;
	    senderClient = true;
	    stompClient.send("/app/purchase/" + auctionId, {}, JSON.stringify({'userId': userId,'username': username,
	    	'price': newPrice,'endDate': endDate,'quantity': quantity }));
	    jQuery('#validation-info').hide();	
	}
}


	jQuery( "#raiseStakeBtn" ).click(function() { 
		if(Liferay.ThemeDisplay.isSignedIn()){
			if(validate()){
				newPrice = jQuery('#price-input').val() * 100;
				sendForm();
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
				if(quantity > 1){
					quantity = jQuery('#quantity-input').val();
				}
				sendFormQuickPurchase();
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
	
function responsiveNotify(message){
	var width = jQuery(window).width();
	if(width > 1000){
		showNotifyAlert(message);
	}else{
		notifyBar(message);
	}
}
function showNotifyAlert(message) {
	var box = bootbox.alert(message);
	setTimeout(function() {
		box.modal('hide');
	}, 2000);
}

function provideValidation(){
	if(jQuery('#quantity-input') && jQuery('#price-input')){
			jQuery("#proceed-form").validate({
			    rules: {
			      "quantity-input": {
			        required: true,
			        number: true,
			        min: 1
			      },
			      "price-input": {
			        required: true,
			        number: true,
			        min: jQuery('#price-input').val() + 1
			      }
			    }
			  });
		
	} else if(jQuery('#quantity-input')){
			jQuery("#proceed-form").validate({
				rules: {
			      "quantity-input": {
			        required: true,
			        number: true,
			        min: 1
			      }
			    }
			  });
	} else if(jQuery('#price-input')){
		  	jQuery("#proceed-form").validate({
		  		rules: {
			      "price-input": {
			        required: true,
			        number: true,
			        min: jQuery('#price-input').val() + 1
			      }
			    }
			  });
	}
	
}

function validate(){
	return jQuery("#proceed-form").valid();
}

function currency(n){n=parseFloat(n);return isNaN(n)?false:n.toFixed(2);}
