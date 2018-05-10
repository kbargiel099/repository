package com.auctions.system.module.profile.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

import com.auctions.system.module.profile.service.ProfileService;

@Controller
@RequestMapping("VIEW")
public class ProfileController {
	
	private final String userDetailsView = "user-details-view";
	
	@Autowired
	ProfileService service;
	
	@RequestMapping(params = "page=userProfile")
	public ModelAndView getUserProfile(RenderRequest request, RenderResponse response,
			@RequestParam("id") long id) throws Exception{
		ModelAndView model = new ModelAndView(userDetailsView);
		model.addObject("user", service.getUserProfile(id));
		return model;
	}
	
}

