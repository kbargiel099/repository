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
				
				var array = [{type:'details',url:buildUrl(jQuery('#profileUrl').val(),'id',full.id)},
							 {type:'edit',url:buildUrl(jQuery('#editUrl').val(),'userId',full.id)},
							  optionForLock];
				
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
							
					var array = [{type:'details',url:buildUrl(jQuery('#detailsUrl').val(),'id',full.id)},
								 {type:'edit',url:buildUrl(jQuery('#editUrl').val(),'auctionId',full.id)},
								 optionForSuspending,
								 {type:'delete',url:buildUrl(jQuery('#deleteUrl').val(),'auctionId',full.id)}];
								 
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

function sendRequest(url){
		jQuery.ajax({
			"url" : url,
			"type" : "POST",
			"success" : function(data){
				if(data.success){
					table.ajax.reload();
					responsiveNotify("Udało się");
				}
			} 
		});
}