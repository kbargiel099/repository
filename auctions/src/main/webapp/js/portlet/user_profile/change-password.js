var submitUrl = jQuery("#changePasswordUrl").val();
var returnUrl = jQuery("#returnUrl").val();
	
jQuery("#submit").click(function(){
	var params = new Params().
		push('form', JSON.stringify(jQuery("#change-password-form").serializeObject())).
		getArray();
	
	submit(params);
});

function submit(params_){
	sendRequestParams(submitUrl, params_, onResponse);
}

var onResponse = function(res){
	if(res.success == true){
		window.location.href = buildUrl(returnUrl,'message',Liferay.Language.get('password.has.been.changed'));
	}else{
		responsiveNotify(Liferay.Language.get('error.msg'));
	}
};