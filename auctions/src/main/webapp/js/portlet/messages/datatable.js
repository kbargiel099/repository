function initMessages(resourceUrl){
	table = jQuery('#messages').DataTable( {
	        "ajax": {
	            "url": resourceUrl,
	            "type": "POST"
	        },
	        "columns": [
				{ "data": "id" },
	            { "data": "title" },
				{ "data": "createDate" },
				{ "data": "isSent" },
	            { "data": "options","width": "30%" }
	        ],
		    "columnDefs": [ {
			    "targets": 4,
			    "render": function(data,type,full,row){
					var optionEdit = {type:'edit',url:buildUrl(jQuery('#editUrl').val(),'id',full.id)};
					var optionDelete = {type:'delete',url:buildUrl(jQuery('#deleteUrl').val(),'id',full.id)}; 
					
					var array = [optionEdit, optionDelete];			 
					return createDropDownMenu(array);
				}
			  },
			{
				"targets": 3,
				"render": function(data){
					return data ?  Liferay.Language.get('message.sent') : Liferay.Language.get('message.not_sent');
				}
			}],
			"language": {
				"url": "pl"
			}
	    } );
	}