jQuery('#message-category-submit').click(function(){
	if(jQuery("#message-category-form").valid()){
		jQuery('#type').val() == 'add' ? insert() : edit();
	}
});

function insert(){
	send({
		url: jQuery('#insertUrl').val(),
		params: [
			{'name': 'messageCategory', 'value': jQuery('#name').val()}
		],
		callback: function(data){
			const message = data.success ? 'message.category.insert.success' : 'error.msg';
			responsiveNotify(Liferay.Language.get(message));
		}
	});
}

function edit(){
	send({
		url: jQuery('#editUrl').val(),
		params: [
			{'name': 'messageCategory', 'value': jQuery('#name').val()}
		],
		callback: function(data){
			const message = data.success ? 'message.category.edit.success' : 'error.msg';
			responsiveNotify(Liferay.Language.get(message));
		}
	});
}

jQuery(function() {
	  jQuery("#message-category-form").validate({
	    rules: {
	      name: {
	        required: true
	      }
	    }
	  });
	});