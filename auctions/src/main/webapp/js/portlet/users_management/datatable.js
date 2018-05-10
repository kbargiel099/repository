var table;

function initUsers(resourceUrl){
    table = jQuery('#users').DataTable( {
        "ajax": {
            "url": resourceUrl,
            "type": "POST"
        },
        "columns": [
            { "data": "login" },
			{ "data": "email" },
			{ "data": "firstname" },
			{ "data": "lastname" },
            { "data": "options" }
        ],
	    "columnDefs": [ {
		    "targets": 4,
		    "render": function(data,type,full,row){
			console.log(full);
				var array = [{type:'details',url:buildUrl(jQuery('#detailsUrl').val(),'userId',full.id)},
							 {type:'edit',url:buildUrl(jQuery('#editUrl').val(),'userId',full.id)}];
				if(full.lockout){
					array.push({type:'unlock',url:buildUrl(jQuery('#unlockUrl').val(),'userId',full.id)});
				}else{
					array.push({type:'lock',url:buildUrl(jQuery('#lockUrl').val(),'userId',full.id)});
				}
				return createDropDownMenu(array);
			}
		  } ],
		//"language": language
		"language": {
			"url": "pl"
		},
		responsive: true
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
	            { "data": "options" }
	        ],
		    "columnDefs": [ {
			    "targets": 4,
			    "render": function(data,type,full,row){
					var array = [{type:'details',url:buildUrl(jQuery('#detailsUrl').val(),'auctionId',full.id)},
								 {type:'edit',url:buildUrl(jQuery('#editUrl').val(),'auctionId',full.id)},
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
			},
			responsive: true
	    } );
	}

function sendRequest(url){
		jQuery.ajax({
			"url" : url,
			"type" : "POST",
			"success" : function(data){
				if(data.success){
					table.ajax.reload();
				}
			} 
		});
}

var buildUrl = function(base, key, value) {
    var separator = (base.indexOf('?') > -1) ? '&' : '?';
    return base + separator + key + '=' + value;
}