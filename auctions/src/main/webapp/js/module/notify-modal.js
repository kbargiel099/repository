/*$("#success").click(function () {
  $(".notify").toggleClass("active");
  $("#notifyType").toggleClass("success");
  
  setTimeout(function(){
    $(".notify").removeClass("active");
    $("#notifyType").removeClass("success");
  },2000);
});*/

/*function showNotification() {
  $(".notify").toggleClass("active");
  $("#notifyType").toggleClass("success");
  
  setTimeout(function(){
    $(".notify").removeClass("active");
    $("#notifyType").removeClass("success");
  },2000);
}

$("#failure").click(function () {
  $(".notify").addClass("active");
  $("#notifyType").addClass("failure");
  
  setTimeout(function(){
    $(".notify").removeClass("active");
    $("#notifyType").removeClass("failure");
  },2000);
});*/

	function notifyBar(message) {
		if(! $('.alert-box').length) {
			$('<div class="alert-box success" >'+ message +'</div>').prependTo('body').delay(2000).fadeOut(1000, function() {
	    				$('.alert-box').remove();
	    				});
		};
	};
