	 function saveImages(){
			send({
					url: jQuery('#saveImageUrl').val(),
					params: [{'name':'data','value':JSON.stringify(files)}],
					callback: function(data){
						updateImages();
					}
			});
		};
	
	function updateImages(){
		jQuery('#imageName').val(JSON.parse(JSON.stringify(fileNames)));
		var url = jQuery("#updateImagesUrl").val();
		var params = new Params().
			push('images',jQuery('#imageName').val()).
			getArray();
		sendRequestParams(url,params,function(data){
			if(JSON.parse(data.success) == true){
				responsiveNotify("Aktualizacja poprawna");
			}else{
				responsiveNotify(Liferay.Language.get('error.msg'));
			}
		});
	}
	
	var reader = new FileReader();	
	var files = [];
	var fileNames = [];
	var loadFile = function(event) {
	    reader.onload = function(){
	    	fileNames.push(temp.name);
			files.push({'name':temp.name,'data':getBase64(reader.result)});
			var div = jQuery('<div class="col-xs-3"></div>');
			var img = jQuery('<img src="'+ reader.result +'" heigth="100%" width="100%"/>');
    		var input = jQuery('<input type="hidden" id="'+ temp.name +'" class="image_name" value="'+ temp.name +'"/>');
	    	var span = jQuery('<span class="delete_image btn-info" style="text-align: center;">Usun</span>');
			div.append(img,input,span);
			jQuery('#images').append(div);
			setDeleteImageListener();
	    };
	    var temp = event.target.files[0];
	    reader.readAsDataURL(temp);
 	};

 	jQuery("#submit").click(function(){
 		if(files.length > 0){
 			saveImages();
 		}else{
 			if(fileNames.length > 0){
 				updateImages();
 			}else{
 				responsiveNotify(Liferay.Language.get('empty.images.list'));
 			}
 		}
	});
	
	jQuery(document).ready(function(){
		var imagesNames = document.getElementsByClassName('image_name');
		for(var i=0;i<imagesNames.length;i++){
			fileNames.push(imagesNames[i].value);
		}
		setDeleteImageListener();
	});
	
	function setDeleteImageListener(){
		jQuery('span.delete_image').on("click",function(event){
			  var parent = jQuery(event.target).parent();
			  var value = parent.find('input').val();
			  parent.remove();
			  deleteImage(value);
		});
	}
	
	function deleteImage(value){
		for(var i=0;i<fileNames.length;i++){
			if(fileNames[i] == value){
				fileNames.splice(i,1);
			}
		}
		for(var i=0;i<files.length;i++){
			if(files[i].name == value){
				files.splice(i,1);
			}
		}
	}