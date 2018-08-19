function initMessageCategories(resourceUrl){
	table = jQuery('#message-categories').DataTable( {
	        "ajax": {
	            "url": resourceUrl,
	            "type": "POST"
	        },
	        "columns": [
				{ "data": "id" },
	            { "data": "name" },
				{ "data": "createDate" },
				{ "data": "isActivated"},
				{ "data": "userId" },
	            { "data": "options","width": "30%" }
	        ],
		    "columnDefs": [ {
			    "targets": 5,
			    "render": function(data,type,full,row){
					var optionForSuspending = (full.isActivated === 'suspended') ? {type:'unlock',url:buildUrl(jQuery('#activateUrl').val(),'id',full.id)}
							: {type:'lock',url:buildUrl(jQuery('#suspendUrl').val(),'id',full.id)};
					var optionShow = {type:'stats',url:buildUrl(jQuery('#statsUrl').val(),'id',full.id)};
					var optionDetails = {type:'details',url:buildUrl(jQuery('#detailsUrl').val(),'id',full.id)};
					var optionEdit = {type:'edit',url:buildUrl(jQuery('#editUrl').val(),'id',full.id)};
					var optionDelete = {type:'delete',url:buildUrl(jQuery('#deleteUrl').val(),'id',full.id)}; 
					
					var array = [optionShow, optionDetails, optionEdit, optionForSuspending, optionDelete];			 
					return createDropDownMenu(array);
				}
			  },
			{
				"targets": 3,
				"render": function(data){
					if(data == true){
						return Liferay.Language.get('user.active');
					}else{
						return Liferay.Language.get('suspended');
					}
				}
			}],
			//"language": language
			"language": {
				"url": "pl"
			}//},
			//responsive: true
	    } );
	}