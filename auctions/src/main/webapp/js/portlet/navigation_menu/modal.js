function custom(){
	// Get the modal
	var modal = document.getElementById('myModal');

	// Get the button that opens the modal
	var btn = document.getElementById("myBtn");

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks the button, open the modal 
	btn.onclick = function() {
	    modal.style.display = "block";
	}

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
	    modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	//window.onclick = function(event) {
	//    if (event.target == modal) {
	//        modal.style.display = "none";
	//    }
	//}
}

function submitLogin(){
	
	//var ns = "_nav_menu_WAR_auctions_";
	var resUrl = jQuery("#signInUrl").val();
	resUrl = resUrl.replace("XXXXX",jQuery("#login").val());
	resUrl = resUrl.replace("MMMMM",jQuery("#password").val());
	console.log(resUrl);
	
	jQuery.ajax({
		url:resUrl,
		type: "POST",
		success: function(data){
			console.log(data);
			if(data.success == true)
				window.location.reload();
		}
	});
}

function logout(){

	var logoutUrl = jQuery("#logoutUrl").val();
	jQuery.ajax({
		url:logoutUrl,
		type: "POST",
		success: function(data){
			//if(data.success == true)
			//	console.log(data);
				window.location.reload();
		}
	});
}