package com.auctions.system.module.profile.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

import com.auctions.system.module.profile.service.ProfileService;

@Controller
@RequestMapping("VIEW")
public class ProfileController implements Profile{
	
	private final String userDetailsView = "user-details-view";
	
	@Autowired
	private ProfileService service;

	@Override
	public ModelAndView getUserProfile(RenderRequest request, RenderResponse response, long id) throws Exception{
		ModelAndView model = new ModelAndView(userDetailsView);
		model.addObject("user", service.getUserProfile(id));
		model.addObject("grades", service.getUserGrades(id));
		model.addObject("auctions", service.getUserAuctions(id));
		return model; 
	}

}

