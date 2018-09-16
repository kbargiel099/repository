var stompClientChat = null;
var senderClientChat = false;
var isWaitChat = false;
var username = jQuery('#username').val();
var userId = Liferay.ThemeDisplay.getUserId();
var notificationListIdPrefix = 'prefix_';

jQuery(document).ready(function(){
	if(document.getElementById("no-message-elem") == null){
		jQuery('#notify-view-element').addClass('mail_notify');
	}
});

jQuery('#notify-view-element').click(function(){
	jQuery('#notify-view-element').removeClass('mail_notify');
});

function addOfferToList2(res) {
	alert(res.message);
	jQuery('#notify-view-element').css('color','red');
}

function sendForm(receiverId,message) {
	if(!isWaitChat){
	    stompClientChat.send("/app/conversation/" + receiverId, {}, JSON.stringify({'senderId': userId,'senderName': username,
	    	'receiverId': receiverId,'message': message}));
	}
}