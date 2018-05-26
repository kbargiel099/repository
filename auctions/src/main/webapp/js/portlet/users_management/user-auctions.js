	jQuery(document).ready(function(){
		createOptions();
	});

	function createOptions(){
		var elements = document.getElementsByClassName('dropdown-class');
		for(var i=0;i<elements.length;i++){
			var id = elements[i].getElementsByTagName('input')[0].value;
			var array = [{type:'edit',url:buildUrl(jQuery('#editUrl').val(),'id',id)},
				 		 {type:'addVideo',url:buildUrl(jQuery('#addVideoUrl').val(),'id',id)},
				 		 {type:'addImages',url:buildUrl(jQuery('#addImagesUrl').val(),'id',id)}];
			elements[i].innerHTML += createDropDownMenu(array);
		}
	}