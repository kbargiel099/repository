var stompClientChat = null;
var senderClientChat = false;
var isWaitChat = false;
var username = jQuery('#username').val();
var userId = Liferay.ThemeDisplay.getUserId();
var notificationListIdPrefix = 'prefix_';

function addOfferToList2(res) {
	alert(res.message);
	jQuery('#notify-view-element').css('color','red');
}
	
function responsiveNotify2(message){
	var width = jQuery(window).width();
	if(width > 1000){
		showNotifyAlert2(message);
	}else{
		notifyBar(message);
	}
}

function showNotifyAlert2(message) {
	var box = bootbox.alert(message);
	setTimeout(function() {
		box.modal('hide');
	}, 2000);
}

function sendForm(receiverId,message) {
	if(!isWaitChat){
		isWaitChat = true;
	    senderClientChat = true;
	    stompClientChat.send("/app/conversation/" + receiverId, {}, JSON.stringify({'senderId': userId,'senderName': username,
	    	'receiverId': receiverId,'message': message}));
	}
}