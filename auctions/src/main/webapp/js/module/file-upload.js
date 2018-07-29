var available = 0;
var isSent = false;
var size = 1024*1024;
var fileData;
var bytesSent = 0;
var url;
var fileName;
var conversionProgress = -1;
var hasFile = false;

function callback(){
	isSent = false;
	available = fileData.length - bytesSent;
}

var reader = new FileReader();
var loadFileVideo = function(event) {
    reader.onload = function(){
        file = document.getElementById("video").files[0];
        fileName = file.name;
		fileData = getBase64(reader.result);
		available = fileData.length;
		sentPackage(createDataPackage(),afterSuccessSending);
		jQuery('#conversion-div').show();
    };
    reader.readAsDataURL(event.target.files[0]);
};

function sentPackage(data,callback){
	isSent = true;
	params = [{'name':'data','value':data},
		      {'name':'name','value':fileName}];
	sendRequestParams(url,params,callback);
}

var afterSuccessSending = function(res){
	bytesSent += size;
	callback();
	jQuery('#status').html(parseFloat((bytesSent/fileData.length)*100).toFixed(0) + "%");
	if(available > 0){
		sentPackage(createDataPackage(),afterSuccessSending);
	}else{
		jQuery('#status').html("100%");
		jQuery('#attach-video-label').hide();
		convertVideo();
	}
};

function convertVideo(){
	var url = jQuery('#convertVideoUrl').val();
	params = [{'name':'videoName','value':fileName}];
	sendRequestParams(url,params,checkConversionStatus);
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

function createDataPackage(){
	var temp = "";
	var limit = available >= size ? size : available;
	for(var j=bytesSent;j<bytesSent+limit;j++){
		temp += fileData[j];
	}
	return temp;
}

function invoke(){
	console.log('zmiana jest sukces');
	jQuery('#conversion-div').show();
	jQuery('#status').html("100%");
	jQuery('#attach-video-label').hide();
	convertVideo();
}
