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
		jQuery('#video').show();
		jQuery('#filename').html('');
		jQuery('#filename-div').hide();
	}
	
	if(info.progress <= 1000 && info.progress >= 0){
		console.log(info.progress);
		jQuery('#video').hide();
		jQuery('#filename').html(info.name);
		jQuery('#filename-div').show();
		jQuery('#status').html("100%");
		jQuery('#conversion').html(parseFloat(info.progress/10).toFixed(0) + "%");
		
		setTimeout(function(){ 
			checkConversionStatus(1);}, 1500);
		
		if(info.progress == 1000){
			jQuery('#conversion').html("100%");
			responsiveNotify(Liferay.Language.get('file.successfully.added'));
			info.progress = -1;
			jQuery('#delete-btn').show();
			hasFile = true;
		}	
	}
};

jQuery('#delete-btn').on("click",function(event){ 
	var url = jQuery('#deleteVideoUrl').val();
	sendRequest(url,function(data){
		if(JSON.parse(data.success) == true){
			jQuery('#video').show();
			jQuery('#delete-btn').hide();
			jQuery('#filename').html('');
			jQuery('#filename-div').hide();
			jQyery('#conversion-div').hide();
			hasFile = false;
			responsiveNotify(Liferay.Language.get('video.file.removed.success'));
		}else{
			responsiveNotify(Liferay.Language.get('error.msg'));
		}
	});
});
