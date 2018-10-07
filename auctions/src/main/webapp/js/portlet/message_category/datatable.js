function initMessageCategories(resourceUrl){
	table = jQuery('#message-categories').DataTable( {
	        "ajax": {
	            "url": resourceUrl,
	            "type": "POST"
	        },
	        "columns": [
	            { "data": "name" },
				{ "data": "createDate" },
				{ "data": "isActivated"},
	            { "data": "options","width": "30%" }
	        ],
		    "columnDefs": [ {
			    "targets": 3,
			    "render": function(data,type,full,row){
					var optionForSuspending = !full.isActivated ? {type:'unlock',url:buildUrl(jQuery('#activateUrl').val(),'id',full.id)}
							: {type:'lock',url:buildUrl(jQuery('#suspendUrl').val(),'id',full.id)};
					var optionEdit = {type:'edit',url:buildUrl(jQuery('#editUrl').val(),'id',full.id)};
					var optionDelete = {type:'delete',url:buildUrl(jQuery('#deleteUrl').val(),'id',full.id)}; 
					
					var array = [optionEdit, optionForSuspending, optionDelete];			 
					return createDropDownMenu(array);
				}
			  },
			{
				"targets": 2,
				"render": function(data){
					return data ?  Liferay.Language.get('category.active') : Liferay.Language.get('category.locked');
				}
			}],
			"responsive": true,
			"language": {
				"url": "pl"
			}
	    } );
	}