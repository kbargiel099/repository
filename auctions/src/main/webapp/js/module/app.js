var stompClient = null;
var username = Liferay.ThemeDisplay.getUserName();
//var username = Liferay.ThemeDisplay.getScreenName();
var userId = Liferay.ThemeDisplay.getUserId();
var auctionId = jQuery('#id').val();
var endDate = jQuery('#endDate').val();
//var endDate = '2018.03.24 00.06.00';
var quantity = jQuery('#quantity').val();
var auctionTypeId = 1;
var senderClient = false;
var isWait = false;
jQuery(document).ready(function(){
	connect();
});

/*function connect() {
    var socket = new SockJS('http://localhost:8143/notification');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/notify/'+jQuery('#id').val(), function (data) {
        	var res = JSON.parse(data.body);
        	if(res.success == true){
        		jQuery("#auction-notify ul").append("<li>" + res.username + " przebił </li>");
        		jQuery('#currentPrice').val(res.price);
        		jQuery('#price').html('Aktualna cena - ' + currency(res.price/100));
        		showNotifyAlert(res.username + " " + jQuery('#successMsg').val());
        	}else if(senderClient == true){
        		showNotifyAlert(jQuery('#errorCode1').val());
	        	senderClient = false;
        	}
        });
    });
}*/

function connect() {
    var socket = new SockJS('http://192.168.0.15:8143/notification');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/notify/'+jQuery('#id').val(), function (data) {
        	var res = JSON.parse(data.body);
        	if(res.success == true){
        		row = jQuery("<tr></tr>");
        		col1 = jQuery("<td>"+ res.username +"</td>");
        		col2 = jQuery("<td>"+ res.quantity +"</td>");
        		col3 = jQuery("<td>"+ res.price +"</td>");
        		row.append(col1,col2,col3).prependTo('#auction-notify table tbody');
        		jQuery('#currentPrice').val(res.price);
        		jQuery('#price').html('Aktualna cena - ' + currency(res.price/100) + ' ' + jQuery('#currency').val());
        		//showNotifyAlert(res.username + " " + jQuery('#successMsg').val());
        		notifyBar(res.username + " " + jQuery('#successMsg').val());
        		if(senderClient){
        			isWait = false;
        		}
        	}else if(senderClient == true){
        		//showNotifyAlert(jQuery('#errorCode1').val());
        		notifyBar(jQuery('#errorCode1').val());
	        	senderClient = false;
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

function sendForm() {
	if(!isWait){
	    isWait = true;
	    senderClient = true;
		var newPrice = parseInt(jQuery('#currentPrice').val()) + 100;
	    stompClient.send("/app/update/" + auctionId, {}, JSON.stringify({'userId': userId,'username': username,
	    	'price': newPrice,'endDate': endDate,'quantity': quantity,'auctionTypeId': auctionTypeId}));
	    //senderClient = true;
	    //isWait = true;
	}
}

$(function () {
	jQuery("form").on('submit', function (e) {
        e.preventDefault(); 
    });
	jQuery( "#raiseStakeBtn" ).click(function() { 
		if(Liferay.ThemeDisplay.isSignedIn()){
			sendForm();
		}else{
			//showNotifyAlert(jQuery('#userIsNotSignedInMsg').val());
			notifyBar(jQuery('#userIsNotSignedInMsg').val());
		}
	});
});

function showNotifyAlert(message) {
	var box = bootbox.alert(message);
	setTimeout(function() {
		box.modal('hide');
	   }, 2000);
}


function currency(n){n=parseFloat(n);return isNaN(n)?false:n.toFixed(2);}
