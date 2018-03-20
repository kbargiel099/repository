var slideIndex = 1;
var newestIndex = 1; 

showSlides("mySlides","dot",slideIndex,slideIndex);
showSlides("newest","dot",newestIndex,newestIndex);

jQuery('#mySlidesPrev').click(function(){
	plusSlides(slideIndex,-1);
});
jQuery('#mySlidesNext').click(function(){
	plusSlides(slideIndex,1);
});
jQuery('#newestPrev').click(function(){
	plusSlides(newestIndex,-1);
});
jQuery('#newestNext').click(function(){
	plusSlides(newestIndex,1);
});

function plusSlides(index, value) {
  showSlides(index += value);
}

function currentSlide(index, value) {
  showSlides(index = value);
}

function showSlides(elemsId , dotsId , index, n) {
  var i;
  var slides = document.getElementsByClassName(elemsId);
  var dots = document.getElementsByClassName(dotsId);
  if (n > slides.length) {index = 1} 
    if (n < 1) {index = slides.length}
    for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none"; 
    }
    for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
    }
  slides[index-1].style.display = "block"; 
  //dots[index-1].className += " active";
} 