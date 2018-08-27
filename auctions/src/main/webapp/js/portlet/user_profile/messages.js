function changeVisibility(id){
	let elem = jQuery('#'+id);
	elem.is(':visible') ? elem.hide() : elem.show();
}