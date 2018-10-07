var submitUserDataUrl = jQuery("#updateUserDetailsUrl").val();
var submitUrl = jQuery("#changePasswordUrl").val();
	
jQuery("#update-user-submit").click(function(){
	if (!jQuery("#update-user-data-form").valid()) {
		return;
	}
	
	var params = new Params().
		push('form', JSON.stringify(jQuery("#update-user-data-form").serializeObject())).
		getArray();
	
	submitUserData(params);
});

function submitUserData(params_){
	sendRequestParams(submitUserDataUrl, params_, userDataOnResponse);
}

var userDataOnResponse = function(res){
	if(JSON.parse(res.success) == true){
		responsiveNotify(Liferay.Language.get('user.has.been.updated'));
	}else{
		responsiveNotify(Liferay.Language.get('error.msg'));
	}
};
	
jQuery("#submit").click(function(){
	if (!jQuery("#change-password-form").valid()) {
		return;
	}
	
	var params = new Params().
		push('form', JSON.stringify(jQuery("#change-password-form").serializeObject())).
		getArray();
	
	submit(params);
});

function submit(params_){
	sendRequestParams(submitUrl, params_, onResponse);
}

var onResponse = function(res){
	var response = JSON.parse(res.data);
	
	if (response.success == true) {
		responsiveNotify(Liferay.Language.get('password.has.been.changed'));
	} else {
		if (response.includes('UserPasswordException')) {
			responsiveNotify(Liferay.Language.get('user.password.unique.error.msg'));
		} else {
			responsiveNotify(Liferay.Language.get('error.msg'));
		}
	}
};