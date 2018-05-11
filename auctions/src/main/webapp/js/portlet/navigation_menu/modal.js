function init(){
	var span = document.getElementById("login-modal-close");
		span.onclick = function() {
			jQuery("#login-validation-info").hide();
		}
	
	jQuery("#remember_me").click(function(){
		if(this.checked)
			jQuery("#remember_me").val(true);
		else
			jQuery("#remember_me").val(false);
	});
}

function submitLogin(){
	var url = jQuery("#signInUrl").val();
	var params = [{'name':'form','value':JSON.stringify(jQuery("#login-form").serializeObject())}];  
	sendRequestParams(url,params,signInCallback);
}

var signInCallback = function(data){
	if(data.success == true)
		window.location.href = themeDisplay.getURLHome();
	else
		jQuery("#login-validation-info").show();
};