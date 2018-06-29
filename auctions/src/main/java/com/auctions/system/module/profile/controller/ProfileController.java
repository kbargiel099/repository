package com.auctions.system.module.profile.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

import com.auctions.system.module.profile.service.ProfileService;

public interface ProfileController {
	
	final String userDetailsView = "user-details-view";
	
	public ProfileService getProfileService();
	
	@RequestMapping(params = "page=userProfile")
	public default ModelAndView getUserProfile(RenderRequest request, RenderResponse response,
			@RequestParam("id") long id) throws Exception{
		ModelAndView model = new ModelAndView(userDetailsView);
		model.addObject("user", getProfileService().getUserProfile(id));
		return model;
	}

}

