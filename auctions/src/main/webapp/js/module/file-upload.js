var available = 0;
var isSent = false;
var size = 512*512;
var videoData;
var bytesSent = 0;
var url = jQuery('#submitDataUrl').val();
var file;

function callback(){
	isSent = false;
	available = videoData.length - bytesSent;
}

var reader = new FileReader();
var loadFileVideo = function(event) {
    reader.onload = function(){
        file = document.getElementById("video").files[0];      
		videoData = getBase64(reader.result);
		available = videoData.length;
		sentPackage(createDataPackage());
    };
    reader.readAsDataURL(event.target.files[0]);
};

function sentPackage(data){
	isSent = true;
    jQuery.ajax({
    	"url": url,
    	"type": "POST",
    	"data": {
    		"data":data,
    		"name": file.name
    	},
    	"success": function(data){
    		bytesSent += size;
    		callback();
			jQuery('#status').html(parseFloat((bytesSent/videoData.length)*100).toFixed(0));
			if(available > 0){
    			var data = createDataPackage();
    			sentPackage(data);
			}else{
				createReference();
			}
    	}
    });
    
}

function createReference(){
    jQuery.ajax({
    	"url": jQuery('#createAuctionVideoUrl').val(),
    	"type": "POST",
    	"data": {
    		"id": jQuery('#auctionId').val(),
    		"name": file.name
    	},
    	"success": function(data){
			alert("Plik został zapisany");
    	}
    });
}

function createDataPackage(){
	var temp = "";
	var limit = available >= size ? size : available;
	for(var j=bytesSent;j<bytesSent+limit;j++){
		temp += videoData[j];
	}
	return temp;
}

function getBase64(source) {
  	return source.replace(/^data:(image|video)\/(png|jpg|jpeg|mp4|webm);base64,/, "");
}