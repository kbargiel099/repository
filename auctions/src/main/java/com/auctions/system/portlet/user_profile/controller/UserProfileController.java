package com.auctions.system.portlet.user_profile.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.portlet.user_profile.service.UserProfileService;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * Portlet implementation class UserProfileController
 */

@Controller
@RequestMapping("VIEW")
public class UserProfileController {

	private final String defaultView = "view";
	private final String addEditView = "add_edit"; 
	private final String detailsView = "details"; 
	
	@Autowired
	private UserProfileService service;
	
	@RenderMapping
	public ModelAndView defaulView(RenderRequest request, RenderResponse response) throws Exception{

		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("userId", PortalUtil.getUserId(request));
		model.addObject("exist", service.isUserExist("test@liferay.com", "jecky"));
		return model;
	}
	
	@RequestMapping(params = "page=getBought")
	public ModelAndView getBoughtAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("exist", "getBoughtView");
		return model;
	}
	
	@RequestMapping(params = "page=getSold")
	public ModelAndView getSoldAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("exist", "getSoldtView");
		return model;
	}
	
	@RequestMapping(params = "page=mySettings")
	public ModelAndView mySettingsAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("exist", "mySettingsView");
		return model;
	}



}
