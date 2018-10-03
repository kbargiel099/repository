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
			{ "data": "lockout"},
            { "data": "options","width": "30%" }
        ],
	    "columnDefs": [ {
		    "targets": 5,
		    "render": function(data,type,full,row){
			console.log(full);
				var optionForLock = full.lockout ? {type:'unlock',url:buildUrl(jQuery('#unlockUrl').val(),'userId',full.id)}
						: {type:'lock',url:buildUrl(jQuery('#lockUrl').val(),'userId',full.id)};
				var optionDetails = {type:'details',url:buildUrl(jQuery('#profileUrl').val(),'id',full.id)};
				
				var array = [optionDetails, optionForLock];
				return createDropDownMenu(array);
			}
		  },
		  {
			    "targets": 4,
			    "render": function(data,type,full,row){
					if(data == true){
						return Liferay.Language.get('user.locked');
					}else{
						return Liferay.Language.get('user.active');
					}
				}
		}],
		"language": {
			"url": "pl"
		}
    } );
}