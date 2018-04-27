package com.auctions.system.module;

import com.auctions.system.portlet.category.model.SearchingForm;

public class SearchingFormValidator {

	public static void prepare(SearchingForm form){
		long temp;
		
		if(!form.getMinPrice().isEmpty()){
			temp = Long.parseLong(form.getMinPrice())*100;
			form.setMinPrice(String.valueOf(temp));
		}
		
		if(!form.getMaxPrice().isEmpty()){
			temp = Long.parseLong(form.getMaxPrice())*100;
			form.setMaxPrice(String.valueOf(temp));
		}
	}
	
}
