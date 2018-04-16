package com.auctions.system.module.profile.controller;

import java.io.IOException;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.profile.service.ProfileService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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

