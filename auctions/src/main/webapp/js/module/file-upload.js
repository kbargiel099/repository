var url;

var hasFile = false;

function showSaveBtn(event) {
	if(event.target.files.length > 0){
		jQuery('#submit-btn').show();
	}
};

jQuery('#submit-btn').click(function(){
	jQuery('form[name="fileUploader"]').submit();
	jQuery('#attach-video-label').hide();
    jQuery('#submit-btn').hide();
});

function sendPackage(name, data,callback){
	params = [{'name':'data','value':data},
		      {'name':'name','value':name}];
	sendRequestParams(url,params,callback);
}

var checkConversionStatus = function(data){
	var url = jQuery('#checkConversionStatusUrl').val();
	sendRequest(url,checkConversionStatusCallback);
};

var checkConversionStatusCallback = function(data){
	var info = JSON.parse(data.progress);
	console.log(info);
	
	if(info.progress == -1 && !hasFile){
		jQuery('#attach-video-label').show();
	}
	
	if(info.progress >= 0){
		jQuery('#conversion-div').show();
		conversionProgress = info.progress;
		jQuery('#video').show();
		jQuery('#filename').html('');
		jQuery('#filename-div').hide();
	}
	
	if(conversionProgress <= 1000 && conversionProgress >= 0){
		console.log(info.progress);
		jQuery('#video').hide();
		jQuery('#filename').html(info.name);
		jQuery('#filename-div').show();
		jQuery('#status').html("100%");
		jQuery('#conversion').html(parseFloat(info.progress/10).toFixed(0) + "%");
		
		setTimeout(function(){ 
			checkConversionStatus(1);}, 1500);
		
		if(conversionProgress == 1000){
			jQuery('#conversion').html("100%");
			responsiveNotify(Liferay.Language.get('file.successfully.added'));
			conversionProgress = -1;
			jQuery('#delete-btn').show();
			hasFile = true;
		}	
	}
};

jQuery('#delete-btn').on("click",function(event){
	var url = jQuery('#deleteVideoUrl').val();
	sendRequest(url,function(data){
		if(JSON.parse(data.success) == true){
			jQuery('#attach-video-label').show();
			jQuery('#video').show();
			jQuery('#delete-btn').hide();
			jQuery('#filename').html('');
			jQuery('#filename-div').hide();
			hasFile = false;
			responsiveNotify(Liferay.Language.get('video.file.removed.success'));
		}else{
			responsiveNotify(Liferay.Language.get('error.msg'));
		}
	});
});
