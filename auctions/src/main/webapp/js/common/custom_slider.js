var slideIndex = 0;
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

function plusSlides(elemsId, dotsId, index, value) {
  return showSlides(elemsId, dotsId, index, value);
}

function currentSlide(elemsId, dotsId, index, value) {
  showSlides(elemsId, dotsId, index, index = value);
}

function showSlides(elemsId , dotsId , index, n) {
  var i;
  var slides = document.getElementsByClassName(elemsId);
  var dots = document.getElementsByClassName(dotsId);
  index += n;
  console.log(index +" "+ n +" "+ slides.length);
  if (index > slides.length-1) {index = 0} 
    if (index < 0) {index = slides.length-1}
    for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none"; 
    }
    for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
    }
  //slides[index-1].style.display = "block";
    console.log(index +" "+ n +" "+ slides.length);
    slides[index].style.display = "block";
    return index;
  //dots[index-1].className += " active";
} 