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
				{ "data": "status"},
				{ "data": "imageName" },
	            { "data": "options","width": "30%" }
	        ],
		    "columnDefs": [ {
			    "targets": 5,
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
				"targets": 4,
				"render": function(data){
					return '<img class="image image-120 img-center" style="display: block; margin: 0 auto;" src="/images/'+ data+ '" />';
				}
			},
			{
				"targets": 3,
				"render": function(data){
					return Liferay.Language.get('auction.' + data);
				}
			}],
			"language": {
				"url": "pl"
			}
	    } );
	}