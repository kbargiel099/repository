	var reader = new FileReader();	
	var files = [];
	var saved = 0;
	var loadFile = function(event) {
	    reader.onload = function(){
			files.push({'name':temp.name,'data':reader.result});
			var div = jQuery('<div class="col-xs-4"></div>');
			var img = jQuery('<img src="'+ reader.result +'" heigth="100%" width="100%"/>');
			div.append(img);
			jQuery('#images').append(div);
			console.log(files);
	    };
	    var temp = event.target.files[0];
	    reader.readAsDataURL(temp);
 	};
	
	var afterSuccessSendingPackage = function(res){
		bytesSent += size;
		callback();
		if(available > 0){
			sentPackage(createDataPackage(),afterSuccessSendingPackage);
		}else{
			bytesSent = 0;
			saved = saved + 1;
			
			if(saved == files.length){
				submitAuction();
			}else{
				saveImage();
			}
		}
	};
	
 	jQuery("#create-auction-submit").click(function(){
	     //var isValid = jQuery("#login-form").valid();
	     //if(isValid){
		      //jQuery("#login-validation-info").hide();
 				saveImage();
	    		//submitAuction();
	    // }
	});
	
	function submitAuction(){
		prepareTechnicalData();
		var url = jQuery("#submitAuctionUrl").val();
		var params = [{'name':'newAuction','value':JSON.stringify(jQuery("#create-new-auction-form").serializeObject())}];
		sendRequestParams(url,params,function(data){alert("Udało się");});
	}
	
	function saveImage(){
		fileName = files[saved].name;
        url = jQuery('#saveImageUrl').val();
		fileData = getBase64(files[saved].data);
		available = fileData.length;
		sentPackage(createDataPackage(),afterSuccessSendingPackage);
	}
	
	function getTechnicalData(id){
		var url = buildUrl(jQuery("#getTechnicalDataUrl").val(),'id',id);  
		sendRequest(url,getTechnicalDataCallback);
	}
	
	var getTechnicalDataCallback = function(data){
		if(data.success == true){
			jQuery('#technicalDataList').html('');
			var res = data.data;
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
			case 'checkbox':
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