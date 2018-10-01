var popularIndex = 0; 
var newestIndex = 0;
var imageIndex = 0;

jQuery(document).ready(function(){
	createSlider('gallery');
	addSliderEvents('gallery');
});

function createSlider(sliderId){
	var slider = document.getElementById(sliderId);
	var elements = slider.getElementsByClassName('slider-element');
	var count = elements.length;
	var end = count;
	
	if(elements.length > 0){
		for(var i=0;i<=end;i++){
			var slide = document.createElement('div');
			for(var j=0;j<1;j++){
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
			case 'gallery':
				showSlides("gallery","dot",imageIndex,imageIndex);
				prevBtn.onclick = function(){
					imageIndex = plusSlides("gallery","dot",imageIndex,-1);
				};
				nextBtn.onclick = function(){
					imageIndex = plusSlides("gallery","dot",imageIndex,1);
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
	  var slides = slider.getElementsByClassName('slider-element');
	  if (index > slides.length-1) {index = 0} 
	    if (index < 0) {index = slides.length-1}
	    for (i = 0; i < slides.length; i++) {
	      slides[i].style.display = "none"; 
	    }
	    slides[index].style.display = "block";
	    return index;
} 