package com.auctions.system.module.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.portlet.ModelAndView;

import com.auctions.system.module.profile.service.ProfileService;

@Component
public class ProfileController {
	
	private final String userDetailsView = "user-details-view";
	
	@Autowired
	ProfileService service;
	
	public ModelAndView createUserProfileView(long id){
		ModelAndView model = new ModelAndView(userDetailsView);
		model.addObject("user", service.getUserProfile(id));
		return model;
	}
	
}

