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
	jQuery("#login-validation-info").hide();
}

function submitLogin(){
	var resUrl = jQuery("#signInUrl").val();
	resUrl = resUrl.replace("XXXXX",jQuery("#email").val());
	resUrl = resUrl.replace("MMMMM",jQuery("#password").val());
	
	jQuery.ajax({
		url:resUrl,
		type: "POST",
		success: function(data){
			if(data.success == true)
				window.location.reload();
			else
				jQuery("#login-validation-info").show();
		}
	});
}