jQuery('#message-submit').click(function(){
	jQuery('#type').val() == 'add' ? insert() : edit();
});

function insert(){
	send({
		url: jQuery('#insertUrl').val(),
		params: [
			{'name': 'message', 'value': JSON.stringify(jQuery("#message-form").serializeObject())}
		],
		callback: function(data){
			const message = data ? 'message.insert.success' : 'error.msg';
			responsiveNotify(Liferay.Language.get(message));
		}
	});
}

function edit(){
	send({
		url: jQuery('#editUrl').val(),
		params: [
			{'name': 'message', 'value': JSON.stringify(jQuery("#message-form").serializeObject())}
		],
		callback: function(data){
			const message = data ? 'message.edit.success' : 'error.msg';
			responsiveNotify(Liferay.Language.get(message));
		}
	});
}