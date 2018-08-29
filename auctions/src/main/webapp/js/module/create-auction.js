	var reader = new FileReader();	
	var files = [];
	var fileNames = [];
	var type = jQuery('#type').val();
	var message = Liferay.Language.get('auction.message.success');
	var loadFile = function(event) {
	    reader.onload = function(){
	    	fileNames.push(temp.name);
			files.push({'name':temp.name,'data':getBase64(reader.result)});
			var div = jQuery('<div class="col-xs-4"></div>');
			var img = jQuery('<img src="'+ reader.result +'" height="100%" width="100%"/>');
			div.append(img);
			jQuery('#images').append(div);
			console.log(files);
	    };
	    var temp = event.target.files[0];
	    reader.readAsDataURL(temp);
 	};
	
 	jQuery(function() {
 		  jQuery("#create-new-auction-form").validate({
 		    rules: {
 		      name: {
 		        required: true
 		      },
 		      auctionTypeIdSelect: {
 		        required: true
 		      },
 			  subjectQuantity: {
 				required: true,
 				number: true,
 				minValue: 1
 			  },
 			  endDateInput: {
 	 		    required: true
 	 		  },
 	 		  price: {
 	 	 		required: true,
 				number: true,
 	 	 		minValue: 1
 	 	 	  },
 	 	 	  categoryIdSelect: {
 	 	 		required: true
 	 	 	  },
 	 	 	  subCategoryIdSelect: {
 	 	 		required: true  
 	 	 	  },
 	 	 	  description: {
 	 	 		required: true 
 	 	 	  }
 		    }
 		  });
 		});
 	
 	function setForEdit(selectId,inputId){
 		var options = jQuery(selectId + ' option');
 		var categoryId = jQuery(inputId).val();
 		for(var i=0;i<options.length;i++){
 			if(options[i].value == categoryId){
 				jQuery(selectId).val((options[i].value));
 				jQuery(selectId).selectpicker('refresh');
 			}
 		}
 
 	}
 	
 	function setSubcategoryForEdit(){
 		var id = jQuery('#categoryId').val();
		for(var i=0;i<subCategories.length;i++){
			var item = subCategories[i];
			if(item.categoryId == id){
				var option = '<option value="'+item.id+'">'+ Liferay.Language.get(item.name) +'</option>';
				jQuery("#subCategoryIdSelect").append(option);
			}
		}
		jQuery("#subCategoryIdSelect").selectpicker('refresh');
		setForEdit('#subCategoryIdSelect','#subCategoryId');
		getTechnicalData(jQuery('#subCategoryId').val());
 	}
 	
 	function setTechnicalDataForEdit(){
		var res = JSON.parse(technicalDataJson);
		for(var i=0;i<res.length;i++){
			var temp = jQuery('#' + res[i].name);
				temp.val(res[i].value);
				console.log(temp);
		}
		jQuery('#technicalDataList .selectpicker').selectpicker('refresh');
 	}
 	
	 function saveImages(){
			send({
				url: jQuery('#saveImageUrl').val(),
				params: [{'name':'data','value':JSON.stringify(files)}],
				callback: function(data){
						jQuery('#imageName').val(JSON.parse(JSON.stringify(fileNames)));
						submitAuction(type);
					}
			});
	 }
	
 	jQuery("#create-auction-submit").click(function(){
 		if(jQuery("#create-new-auction-form").valid()){
 			if(type == 'add'){
 				saveImages();
 			}else{
 				message = Liferay.Language.get('changes.successfully.saved');
 				submitAuction(type);
 			}
 		}
	});
	
	function submitAuction(actionType){
		prepareTechnicalData();
		var url = jQuery("#submitAuctionUrl").val();
		var params = [{'name':'newAuction','value':JSON.stringify(jQuery("#create-new-auction-form").serializeObject())},
					  {'name':'type','value': actionType}];
		sendRequestParams(url,params,function(data){
			window.location.href = buildUrl(jQuery('#return').val(),'message',message);
		});
	}
	
	function getTechnicalData(id){
		var url = buildUrl(jQuery("#getTechnicalDataUrl").val(),'id',id);  
		sendRequest(url,getTechnicalDataCallback);
	}
	
	var getTechnicalDataCallback = function(data){
		if(JSON.parse(data.success) == true){
			jQuery('#technicalDataList').html('');
			var res = data.data;
			console.log(res);
			for(var i=0;i<res.length;i++){
				var group = jQuery('<div class="form-group"></div');
				var col1 = jQuery('<label class="label-control">'+ Liferay.Language.get(res[i].name) +'</label>');
				var col2 = jQuery(createElement(res[i].type,res[i].name,res[i].value));
				group.append(col1,col2).appendTo('#technicalDataList');
			}
			jQuery('#technicalDataList .selectpicker').selectpicker();
		}
	};
	
	function createElement(type,name,value){
		switch(type){
			case 'input':
				return '<input type="text" class="form-control" id="'+ name +'" value=""></input>';
			case 'select':
				var res =  '<select class="selectpicker form-control" id="'+ name +'" title="Wybierz">'
				for(var i=0;i<value.length;i++){
					res += '<option value="'+ value[i] +'">'+ Liferay.Language.get(value[i]) +'</option>';
				}
				res += '</select>';
				return res;
		}						
	}
	
	function prepareTechnicalData(){
		var json = [];
		var div = jQuery('#technicalDataList input, #technicalDataList select');
		for(var i=0;i<div.length;i++){
			json.push({'name':div[i].id,'value':div[i].value});
		}
		jQuery('#technicalData').val('\''+ JSON.stringify(json) +'\'');
	}
	
	var subCategories;
	jQuery(document).ready(function(){
		sendRequest(jQuery("#getSubCategoriesUrl").val(),
				function(data){subCategories = data.result;});
		jQuery('.selectpicker').selectpicker();
		
		if(type == 'edit'){
	 		setTimeout(function(){
				setForEdit('#categoryIdSelect','#categoryId');
				setForEdit('#auctionTypeIdSelect','#auctionTypeId');
				setSubcategoryForEdit();
				
				var tempDate = jQuery('#endDate').val();
				jQuery('input[type="date"]').val(tempDate);
				jQuery('#endDate').val(new Date(tempDate).getTime());
			},200); 
	 		
	 		setTimeout(function(){
	 			setTechnicalDataForEdit();
	 		},2000);
		}
	});
	
	jQuery("#categoryIdSelect").change(function(){
		var id = jQuery("#categoryIdSelect option:selected").val();
		jQuery("#categoryId").val(id);
		jQuery("#subCategoryIdSelect").html('');
		
		for(var i=0;i<subCategories.length;i++){
			var item = subCategories[i];
			if(item.categoryId == id){
				var option = '<option value="'+item.id+'">'+ Liferay.Language.get(item.name) +'</option>';
				jQuery("#subCategoryIdSelect").append(option);
			}
		}
		jQuery("#subCategoryIdSelect").selectpicker('refresh');
	});
	
	jQuery("#subCategoryIdSelect").change(function(){
		var id = jQuery("#subCategoryIdSelect option:selected").val();
		jQuery("#subCategoryId").val(id);
		getTechnicalData(id);
	});
	
	jQuery("#auctionTypeIdSelect").change(function(){
		var id = jQuery("#auctionTypeIdSelect option:selected").val();
		jQuery("#auctionTypeId").val(id);
	});
	
	jQuery("input[type='date']").change(function(){
		var date = new Date(this.value);
		var timestamp = date.getTime();
		jQuery("#endDate").val(timestamp);
	});
	
	jQuery("input[id='price']").change(function(){
		var value = jQuery("#price").val();
		jQuery('#subjectPrice').val(value * 100);
	});