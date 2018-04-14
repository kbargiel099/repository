function setSizeOfImages(height){
	var images = document.getElementsByClassName('image');
	var wsp;
	for(var i=0;i<images.length;i++){
		wsp = images[i].height/height;
		images[i].width = images[i].width/wsp;
		images[i].height = height;
	}
}