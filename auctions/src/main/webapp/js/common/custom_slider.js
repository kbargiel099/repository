var popularIndex = 0; 
var newestIndex = 0; 

jQuery(document).ready(function(){
/*	var h1 = jQuery(window).height();   // returns height of browser viewport
	var h2 = jQuery(document).height(); // returns height of HTML document (same as pageHeight in screenshot)
	var h3 = jQuery(window).width();   // returns width of browser viewport
	var h4 = jQuery(document).width(); // returns width of HTML document (same as pageWidth in screenshot)
	console.log(h1);
	console.log(h2);
	console.log(h3);
	console.log(h4);*/
	//createSlider('popular-id');
	//createSlider('newest-id');
	//addSliderEvents('popular-id');
	//addSliderEvents('newest-id');
	setSizeOfImages(150);
/*	for(var i=0;i<images.length;i++){
		console.log(images[i].height +" "+ images[i].width);
	}console.log("po");
	for(var i=0;i<images.length;i++){
		var wsp = images[i].width/images[i].height;
		var scale = images[i].height/150;
		images[i].width = (images[i].height/scale) * wsp;
		images[i].height = 150;
		console.log(images[i].height +" "+ images[i].width);
	} */
});

function setSizeOfImages(height){
	var images = document.getElementsByClassName('image');
	var wsp;
	for(var i=0;i<images.length;i++){
		wsp = images[i].height/height;
		images[i].width = images[i].width/wsp;
		images[i].height = height;
	}
}

	function createSlider(sliderId){
		var slider = document.getElementById(sliderId);
		var elements = slider.getElementsByClassName('slider-element');
		var count = elements.length;
		var end = count%3 == 0 ? (count/3)-1 : Math.floor(count/3);

 		if(elements.length > 0){
			for(var i=0;i<=end;i++){
				var slide = document.createElement('div');
				slide.className = 'newest row';
				for(var j=0;j<3;j++){
					if(elements[0]){
						slide.appendChild(elements[0]);
					}
				}
				slider.appendChild(slide);
			}
		}  
 		
		var prevBtn = document.createElement('a');
		prevBtn.className = 'prev';
		prevBtn.innerHTML = '&#10094;';

		var nextBtn = document.createElement('a');
		nextBtn.className = 'next';
		nextBtn.innerHTML = '&#10095;';
		
		slider.appendChild(prevBtn);
		slider.appendChild(nextBtn);
		
	}

	function addSliderEvents(sliderId){
		var slider = document.getElementById(sliderId);
		var prevBtn = slider.getElementsByClassName('prev')[0];
		var nextBtn = slider.getElementsByClassName('next')[0];
		switch(sliderId){
			case 'popular-id':
				showSlides("popular-id","dot",popularIndex,popularIndex);
				prevBtn.onclick = function(){
					popularIndex = plusSlides("popular-id","dot",popularIndex,-1);
				};
				nextBtn.onclick = function(){
					popularIndex = plusSlides("popular-id","dot",popularIndex,1);
				};
				break;
			case 'newest-id':
				showSlides("newest-id","dot",newestIndex,newestIndex);
				prevBtn.onclick = function(){
					newestIndex = plusSlides("newest-id","dot",newestIndex,-1);
				};
				nextBtn.onclick = function(){
					newestIndex = plusSlides("newest-id","dot",newestIndex,1);
				};
				break;
		}
	}
	
function plusSlides(elemsId, dotsId, index, value) {
  return showSlides(elemsId, dotsId, index, value);
}

function currentSlide(elemsId, dotsId, index, value) {
  showSlides(elemsId, dotsId, index, index = value);
}

function showSlides(elemsId , dotsId , index, n) {
  var i; index += n;
  var slider = document.getElementById(elemsId);
  var slides = slider.getElementsByClassName('newest');
  //var dots = document.getElementsByClassName(dotsId);
  if (index > slides.length-1) {index = 0} 
    if (index < 0) {index = slides.length-1}
    for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none"; 
    }
/*    for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
    }*/
    slides[index].style.display = "block";
    return index;
} 