function custom(){
	var modal = document.getElementById('myModal');
	var btn = document.getElementById("sign-in");
	var span = document.getElementById("login-modal-close");
	if(btn){
		btn.onclick = function() {
		    modal.style.display = "block";
		}
		span.onclick = function() {
		    modal.style.display = "none";
		}
	}
	jQuery("#remember_me").click(function(){
		if(this.checked)
			jQuery("#remember_me").val(true);
		else
			jQuery("#remember_me").val(false);
	});
	jQuery("#login-validation-info").hide();
}

function submitLogin(){
	var queryParameters = {};
	var url = jQuery("#signInUrl").val();

	queryParameters['email'] = jQuery("#email").val();
	queryParameters['pass'] = jQuery("#password").val();
	queryParameters['remember_me'] = jQuery("#remember_me").val();
	url += "&" + jQuery.param(queryParameters);
	console.log(url);
	
	jQuery.ajax({
		"url":url,
		"type": "POST",
		"success": function(data){
			if(data.success == true)
				window.location.reload();
			else
				jQuery("#login-validation-info").show();
		}
	});
}