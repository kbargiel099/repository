package com.auctions.system.module;

import org.springframework.web.portlet.ModelAndView;

public class ViewBuilder {
	
	private ModelAndView model;
	
	public ViewBuilder(ModelAndView model){
		this.model = model;	
	}
	
	public <T> ViewBuilder set(String name, T value){
		model.addObject(name, value);
		return this;
	}
	
}
