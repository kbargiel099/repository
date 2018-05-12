var table;

function initUsers(resourceUrl){
    table = jQuery('#users').DataTable( {
        "ajax": {
            "url": resourceUrl,
            "type": "POST"
        },
        "columns": [
            { "data": "login" },
			{ "data": "firstname" },
			{ "data": "lastname" },
			{ "data": "email" },
            { "data": "options","width": "30%" }
        ],
	    "columnDefs": [ {
		    "targets": 4,
		    "render": function(data,type,full,row){
			console.log(full);
				var optionForLock = full.lockout ? {type:'unlock',url:buildUrl(jQuery('#unlockUrl').val(),'userId',full.id)}
						: {type:'lock',url:buildUrl(jQuery('#lockUrl').val(),'userId',full.id)};
				var optionDetails = {type:'details',url:buildUrl(jQuery('#profileUrl').val(),'id',full.id)};
				var optionEdit = {type:'edit',url:buildUrl(jQuery('#editUrl').val(),'userId',full.id)};
				
				var array = [optionDetails, optionEdit, optionForLock];
				return createDropDownMenu(array);
			}
		  } ],
		//"language": language
		"language": {
			"url": "pl"
		}//},
		//responsive: true
    } );
}

function initAuctions(resourceUrl){
	table = jQuery('#auctions').DataTable( {
	        "ajax": {
	            "url": resourceUrl,
	            "type": "POST"
	        },
	        "columns": [
				{ "data": "id" },
	            { "data": "name" },
				{ "data": "createDate" },
				{ "data": "imageName" },
	            { "data": "options","width": "30%" }
	        ],
		    "columnDefs": [ {
			    "targets": 4,
			    "render": function(data,type,full,row){
					var optionForSuspending = (full.status === 'suspended') ? {type:'unlock',url:buildUrl(jQuery('#activateUrl').val(),'auctionId',full.id)}
							: {type:'lock',url:buildUrl(jQuery('#suspendUrl').val(),'auctionId',full.id)};
					var optionShow = {type:'stats',url:buildUrl(jQuery('#statsUrl').val(),'auctionId',full.id)};
					var optionDetails = {type:'details',url:buildUrl(jQuery('#detailsUrl').val(),'id',full.id)};
					var optionEdit = {type:'edit',url:buildUrl(jQuery('#editUrl').val(),'auctionId',full.id)};
					var optionDelete = {type:'delete',url:buildUrl(jQuery('#deleteUrl').val(),'auctionId',full.id)}; 
					
					var array = [optionShow, optionDetails, optionEdit, optionForSuspending, optionDelete];			 
					return createDropDownMenu(array);
				}
			  },{
				"targets": 3,
				"render": function(data){
					return '<img class="image image-120 img-center" style="display: block; margin: 0 auto;" src="/images/'+ data+ '" />';
				}
			} ],
			//"language": language
			"language": {
				"url": "pl"
			}//},
			//responsive: true
	    } );
	}

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