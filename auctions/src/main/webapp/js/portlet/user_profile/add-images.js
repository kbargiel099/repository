	function saveImage(){
		fileName = files[saved].name;
        url = jQuery('#saveImageUrl').val();
		fileData = getBase64(files[saved].data);
		available = fileData.length;
		sentPackage(createDataPackage(),afterSuccessSendingPackage);
	}
	
	var afterSuccessSendingPackage = function(res){
		bytesSent += size;
		callback();
		if(available > 0){
			sentPackage(createDataPackage(),afterSuccessSendingPackage);
		}else{
			bytesSent = 0;
			saved = saved + 1;
			
			if(saved == files.length){
				jQuery('#imageName').val(JSON.parse(JSON.stringify(fileNames)));
			}else{
				saveImage();
			}
		}
	};
	
	var reader = new FileReader();	
	var files = [];
	var fileNames = [];
	var saved = 0;
	var loadFile = function(event) {
	    reader.onload = function(){
	    	fileNames.push(temp.name);
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
 	
 	jQuery("#submit").click(function(){
		saveImage();
	});