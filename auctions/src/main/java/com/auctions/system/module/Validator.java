package com.auctions.system.module;

import com.auctions.system.portlet.category.model.SearchingForm;

public class Validator {

	public static SearchingForm prepare(SearchingForm form){
		long tempPrice;
		
		if(!form.getMinPrice().isEmpty()){
			tempPrice = Long.parseLong(form.getMinPrice())*100;
			form.setMinPrice(String.valueOf(tempPrice));
		}
		
		if(!form.getMaxPrice().isEmpty()){
			tempPrice = Long.parseLong(form.getMaxPrice())*100;
			form.setMaxPrice(String.valueOf(tempPrice));
		}
		
		return form;
	}
	
}
