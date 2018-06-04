var available = 0;
var isSent = false;
var size = 1024*1024;
var fileData;
var bytesSent = 0;
var url;
var fileName;

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
	if(info.progress == -1){
		console.log("Koniec"); 
	}else {
		if(info.progress <= 1000){
			console.log(info.progress);
			jQuery('#video').hide();
			jQuery('#attach-video-label').hide();
			jQuery('#filename').html(info.name);
			jQuery('#filename-div').show();
			jQuery('#status').html("100%");
			jQuery('#conversion').html(parseFloat(info.progress/10).toFixed(0) + "%");
			
    		setTimeout(function(){ 
    			checkConversionStatus(1);}, 2000);
    		
    		if(info.progress == 1000){
    			console.log("create reference");
    			createReference(info.name);
    		}
		}
	}
};

function createReference(name){
	var url = jQuery('#createAuctionVideoUrl').val();
	params = [{'name':'id','value':jQuery('#auctionId').val()},
		      {'name':'name','value':name}];
	sendRequestParams(url,params,function(data){alert("Plik dodany poprawnie");});
}

function createDataPackage(){
	var temp = "";
	var limit = available >= size ? size : available;
	for(var j=bytesSent;j<bytesSent+limit;j++){
		temp += fileData[j];
	}
	return temp;
}
