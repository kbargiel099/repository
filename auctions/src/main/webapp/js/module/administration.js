var table;
var operation;

function update(url,key){
	operation = key;
	sendRequest(url,dataTableCallback);
}

var dataTableCallback = function(data){
	if(data.success){
		table.ajax.reload();
		responsiveNotify(getMessage(operation));
	}else{
		responsiveNotify(getMessage('error'));
	}
}

function getMessage(key){
	switch(key){
		case 'lock':
			return jQuery('#locked-msg').val();
		case 'unlock':
			return jQuery('#unlocked-msg').val();
		case 'delete':
			return jQuery('#deleted-msg').val();
		case 'error':
			return jQuery('#error-msg').val();
		default:
			return jQuery('#error-msg').val();
	}
}