function createDropDownMenu(options){
	var menuData = '<div class="dropdown">'
		+'<button class="btn btn-primary btn-sm dropdown-toggle" type="button" data-toggle="dropdown">'+ Liferay.Language.get("options")
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
		case 'user_account':
			return '<li><a href=\"'+ option.url +'\">'+ Liferay.Language.get("user_main") +'</a></li>';
		case 'bought':
			return '<li><a href=\"'+ option.url +'\">'+ Liferay.Language.get("bought") +'</a></li>';
		case 'followed':
			return '<li><a href=\"'+ option.url +'\">'+ Liferay.Language.get("followed") +'</a></li>';
		case 'sold':
			return '<li><a href=\"'+ option.url +'\">'+ Liferay.Language.get("sold") +'</a></li>';
	    default:
		    return '';
	} 
}