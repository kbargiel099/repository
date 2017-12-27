function createDropDownMenu(options){
	var menuData = '<div class="dropdown">'
		+'<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Dropdown Example'
		+'<span class="caret"></span></button>'
		+'<ul class="dropdown-menu">';

	for (var i = 0; i < options.length; i++) { 
		menuData += createOption(options[i]);
	} 
	
	menuData += '</ul></div>';
	return menuData;
}

function createOption(option){
	switch (option.type) {
	    case 'details':
	        return '<li><a href=\"'+ option.url +'\">'+ Liferay.Language.get("details") +'</a></li>';
	    case 'edit':
		    return '<li><a href=\"'+ option.url +'\">'+ Liferay.Language.get("edit") +'</a></li>';
	    case 'delete':
		    return '<li><a href=\"'+ option.url +'\">'+ Liferay.Language.get("delete") +'</a></li>';
	    default:
		    return '';
	} 
}