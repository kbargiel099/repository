jQuery('#message-category-submit').click(function(){
		send({
			url: jQuery('#insertUrl').val(),
			params: [
				{'name': 'messageCategory', 'value': jQuery('#name').val()},
				{'name': 'type', 'value': jQuery('#type').val()}
			],
			callback: function(data){
				if(data.success == true){
					responsiveNotify('Kategoria dodana pomyślnie');
				}
			}
		});
});