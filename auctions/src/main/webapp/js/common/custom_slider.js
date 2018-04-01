/*var slideIndex = 0;
var newestIndex = 0; 

//showSlides("mySlides","dot",slideIndex,slideIndex);
showSlides("newest","dot",newestIndex,newestIndex);

jQuery('#mySlidesPrev').click(function(){
	plusSlides("mySlides","dot",slideIndex,-1);
});
jQuery('#mySlidesNext').click(function(){
	plusSlides("mySlides","dot",slideIndex,1);
});
jQuery('#newestPrev').click(function(){
	newestIndex = plusSlides("newest","dot",newestIndex,-1);
});
jQuery('#newestNext').click(function(){
	newestIndex = plusSlides("newest","dot",newestIndex,1);
});
*/
var newestIndex = 0; 

jQuery(document).ready(function(){
	var h1 = jQuery(window).height();   // returns height of browser viewport
	var h2 = jQuery(document).height(); // returns height of HTML document (same as pageHeight in screenshot)
	var h3 = jQuery(window).width();   // returns width of browser viewport
	var h4 = jQuery(document).width(); // returns width of HTML document (same as pageWidth in screenshot)
	console.log(h1);
	console.log(h2);
	console.log(h3);
	console.log(h4);
	createSlider('newest-id');
});

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
					console.log(elements[0]);
					if(elements[0]){
						slide.appendChild(elements[0]);
					}
				}
				slider.appendChild(slide);
			}
		}  
 		
		//var prevBtn = document.createElement('a').innerHTML = '&#10094;';
		var prevBtn = document.createElement('a');
		prevBtn.className = 'prev';
		prevBtn.innerHTML = '&#10094;';
		//var nextBtn = document.createElement('a').innerHTML = '&#10095;';
		var nextBtn = document.createElement('a');
		nextBtn.className = 'next';
		nextBtn.innerHTML = '&#10095;';
		slider.appendChild(prevBtn);
		slider.appendChild(nextBtn);
		
		showSlides("newest","dot",newestIndex,newestIndex);
		prevBtn.click(function(){
			newestIndex = plusSlides("newest","dot",newestIndex,-1);
		});
		nextBtn.click(function(){
			newestIndex = plusSlides("newest","dot",newestIndex,1);
		});
		
	}

function plusSlides(elemsId, dotsId, index, value) {
  return showSlides(elemsId, dotsId, index, value);
}

function currentSlide(elemsId, dotsId, index, value) {
  showSlides(elemsId, dotsId, index, index = value);
}

function showSlides(elemsId , dotsId , index, n) {
  var i; index += n;
  var slides = document.getElementsByClassName(elemsId);
  var dots = document.getElementsByClassName(dotsId);
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