
	function notifyBar(message) {
		if(! $('.alert-box').length) {
			$('<div class="alert-box success" >'+ message +'</div>').prependTo('body').delay(2000).fadeOut(1000, function() {
	    				$('.alert-box').remove();
	    				});
		};
	};
