var langId = Liferay.ThemeDisplay.getLanguageId();

var language = langId === 'pl_PL' ? 'Polish' : 'English';


function initAuctions(resourceUrl){
	table = jQuery('#auctions').DataTable( {
	        "ajax": {
	            "url": resourceUrl,
	            "type": "POST"
	        },
	        "columns": [
	            { "data": "name" },
				{ "data": "createDate" },
				{ "data": "status"},
				{ "data": "imageName" },
	            { "data": "options","width": "30%" }
	        ],
		    "columnDefs": [ {
			    "targets": 4,
			    "render": function(data,type,full,row){
					var optionForSuspending = (full.status === 'suspended') ? {type:'unlock',url:buildUrl(jQuery('#activateUrl').val(),'auctionId',full.id)}
							: {type:'lock',url:buildUrl(jQuery('#suspendUrl').val(),'auctionId',full.id)};
					var optionShow = {type:'stats',url:buildUrl(jQuery('#statsUrl').val(),'auctionId',full.id)};
					var optionDetails = {type:'details',url:buildUrl(jQuery('#detailsUrl').val(),'id',full.id)};
					var optionDelete = {type:'delete',url:buildUrl(jQuery('#deleteUrl').val(),'auctionId',full.id)}; 
					
					var array = [optionShow, optionDetails, optionForSuspending, optionDelete];			 
					return createDropDownMenu(array);
				}
			  },{
				"targets": 3,
				"render": function(data){
					return '<img class="image image-120 img-center" style="display: block; margin: 0 auto;" src="/images/'+ data+ '" />';
				}
			},
			{
				"targets": 2,
				"render": function(data){
					return Liferay.Language.get('auction.' + data);
				}
			}],
			"responsive": true,
			"language": {
				"url": 'http://cdn.datatables.net/plug-ins/1.10.19/i18n/'+ language +'.json'
			}
	    } );
	}