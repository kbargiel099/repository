function init(resourceUrl){
    jQuery('#users').DataTable( {
        "ajax": {
            "url": resourceUrl,
            "type": "POST"
        },
        "columns": [
            { "data": "id" },
            { "data": "login" },
            { "data": "password" },
            { "data": "options" }
        ],
	    "columnDefs": [ {
		    "targets": 3,
		    "render": function(data){
				return createDropDownMenu(data);
			}
		  } ]
    } );
}