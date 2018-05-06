var available = 0;
var isSent = false;
var size = 512*512;
var videoData;
var bytesSent = 0;
var url = jQuery('#submitDataUrl').val();
var file;

jQuery(document).ready(function(){
	checkConversionStatus();
});

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
			jQuery('#status').html(parseFloat((bytesSent/videoData.length)*100).toFixed(0) + "%");
			if(available > 0){
    			var data = createDataPackage();
    			sentPackage(data);
			}else{
				jQuery('#status').html("100%");
				convertVideo();
			}
    	}
    });
    
}

function convertVideo(){
    jQuery.ajax({
    	"url": jQuery('#convertVideoUrl').val(),
    	"type": "POST",
    	"data": {
    		"videoName": file.name
    	},
    	"success": function(data){
    		checkConversionStatus();
    	}
    });
}

function checkConversionStatus(){
    jQuery.ajax({
    	"url": jQuery('#checkConversionStatusUrl').val(),
    	"type": "POST",
    	"success": function(data){
    		var info = JSON.parse(data.progress);
    		console.log(info);
    		if(info.progress == -1){
    			console.log("Koniec"); 
    		}else {
    			if(info.progress <= 1000){
        			console.log(info.progress);
        			jQuery('#video').hide();
        			jQuery('#filename').html(info.name);
        			jQuery('#filename-div').show();
        			jQuery('#status').html("100%");
        			jQuery('#conversion').html(parseFloat(info.progress/10).toFixed(0) + "%");
        			
            		setTimeout(function(){ 
            			checkConversionStatus();}, 2000);
            		
            		if(info.progress == 1000){
            			console.log("create reference");
            			createReference(info.name);
            		}
    			}
    		}
    	}
    });
}

function createReference(name){
    jQuery.ajax({
    	"url": jQuery('#createAuctionVideoUrl').val(),
    	"type": "POST",
    	"data": {
    		"id": jQuery('#auctionId').val(),
    		"name": name
    	},
    	"success": function(data){
    		//jQuery('#status').html("Plik został zapisany");
    		alert("Plik dodany poprawnie");
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