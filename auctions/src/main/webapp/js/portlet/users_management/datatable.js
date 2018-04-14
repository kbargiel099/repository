function initUsers(resourceUrl){
    jQuery('#users').DataTable( {
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
		    "render": function(data){
				return createDropDownMenu(data);
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
	    jQuery('#auctions').DataTable( {
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
			    "render": function(data){
					return createDropDownMenu(data);
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