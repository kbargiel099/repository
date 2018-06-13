var stompClient = null;

jQuery(document).ready(function(){
	if(jQuery('#type').val() == 'purchase'){
		connect();
	}
});

function connect() {
    var socket = new SockJS('http://192.168.0.15:8143/notification');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/message/notify/'+jQuery('#id').val(), function(data){
        	var res = JSON.parse(data.body);
        	if(res.success == true){
        		window.location.href = buildUrl(jQuery('#returnUrl').val(),'message',
        			Liferay.Language.get('subject.successfully.purchased'));
        	}else{
        		responsiveNotify(Liferay.Language.get('error.msg'));
        	}
        });
    });
}

function sendFormQuickPurchase() {
	if(jQuery('#type').val() == 'purchase'){
 		stompClient.send("/app/purchase/" + jQuery('#id').val(), {}, JSON.stringify({'userId': Liferay.ThemeDisplay.getUserId(),'username': jQuery('#username').val(),
	    	'price': jQuery('#price').val(),'endDate': jQuery('#endDate').val(),'quantity': jQuery('#quantity').val() }));    
	}else{
		var params = new Params().
			push('purchaseId',jQuery('#id').val()).
			push('paymentMethodId',jQuery('select').val()).
			getArray();
		sendRequestParams(jQuery('#makePaidUrl').val(),params,function(data){
			console.log(data);
			if(JSON.parse(data.success) == true){
        		window.location.href = buildUrl(jQuery('#getBoughtUrl').val(),'message',
        			Liferay.Language.get('paid.successfully'));
			}else{
				responsiveNotify(Liferay.Language.get('error.msg'));
			}
		});
	}
}