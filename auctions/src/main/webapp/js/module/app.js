var stompClient = null;
var username = Liferay.ThemeDisplay.getUserName();
var userId = Liferay.ThemeDisplay.getUserId();
var auctionId = jQuery('#id').val();
var endDate = jQuery('#endDate').val();
var auctionTypeId = 1;

jQuery(document).ready(function(){
	connect();
});

function setConnected(connected) {
	jQuery("#connect").prop("disabled", connected);
	jQuery("#disconnect").prop("disabled", !connected);
    if (connected) {
    	jQuery("#conversation").show();
    }
    else {
    	jQuery("#conversation").hide();
    }
    jQuery("#greetings").html("");
}

function connect() {
    var socket = new SockJS('http://localhost:8143/notification');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/notify/'+jQuery('#id').val(), function (data) {
        	var res = JSON.parse(data.body);
        	if(res.success == true){
        		jQuery("#auction-notify ul").append("<li>" + res.username + " przebił </li>");
        		jQuery('#currentPrice').val(res.price);
        		jQuery('#price').html('Aktualna cena - ' + currency(res.price/100));
        		showNotifyAlert(res.username);
        	}
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendForm() {
	console.log(jQuery('#endDate').val());
	var newPrice = parseInt(jQuery('#currentPrice').val()) + 100;
    stompClient.send("/app/update/" + auctionId, {}, JSON.stringify({'userId': userId,'username': username,
    	'price': newPrice,'endDate': endDate,'auctionTypeId': auctionTypeId}));
}

$(function () {
	jQuery("form").on('submit', function (e) {
        e.preventDefault(); 
    });
	jQuery( "#connect" ).click(function() { connect(); });
	jQuery( "#disconnect" ).click(function() { disconnect(); });
	jQuery( "#send" ).click(function() { sendForm(); });
	jQuery( "#raiseStakeBtn" ).click(function() { sendForm(); });
});

function showNotifyAlert(username) {
	var box = bootbox.alert("Użytkownik " + username + " przebił.");
	setTimeout(function() {
		box.modal('hide');
	   }, 2000);
}


function currency(n){n=parseFloat(n);return isNaN(n)?false:n.toFixed(2);}
