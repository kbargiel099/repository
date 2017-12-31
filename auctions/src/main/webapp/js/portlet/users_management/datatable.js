function init(resourceUrl){
    jQuery('#users').DataTable( {
        "ajax": {
            "url": resourceUrl,
            "type": "POST"
        },
        "columns": [
            { "data": "login" },
            { "data": "firstname" },
            { "data": "lastname" },
			{ "data": "email" },
            { "data": "options" }
        ],
	    "columnDefs": [ {
		    "targets": 4,
		    "render": function(data){
				return createDropDownMenu(data);
			}
		  } ]
    } );
}