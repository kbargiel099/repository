package com.auctions.system.portlet.user_profile.controller;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.UserProfileAuction;
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
	
	private final String imagesPath = "E:\\Szkoła\\Praca inżynierska\\Repozytorium\\repository\\auctions\\images\\";
	
	@Autowired
	private UserProfileService service;
	
	@InitBinder("newAuction")
	private void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}
	
	@RenderMapping
	public ModelAndView defaulView(RenderRequest request, RenderResponse response) throws Exception{

		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("exist", service.isUserExist("test@liferay.com", "jecky"));
		service.getImages(PortalUtil.getUserId(request));
		return model;
	}
	
	@RequestMapping(params = "page=getBought")
	public ModelAndView getBoughtAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("exist", "getBoughtView");
		model.addObject("boughtView", true);
		
		List<UserProfileAuction> userBoughtSubjects = service.getUserBoughtSubjects(
				PortalUtil.getUserId(request));
				
		model.addObject("subjects", userBoughtSubjects);
		model.addObject("path", imagesPath);
		return model;
	}
	
	@RequestMapping(params = "page=getSold")
	public ModelAndView getSoldAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("exist", "getSoldView");
		model.addObject("soldView", true);
		return model;
	}
	
	@RequestMapping(params = "page=mySettings")
	public ModelAndView mySettingsAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("exist", "mySettingsView");
		model.addObject("mySettingsView", true);
		return model;
	}
	
	@RequestMapping(params = "page=createNewAuction")
	public ModelAndView createNewAuctionRender(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("exist", "createNewAuction");
		model.addObject("createNewAuctionView", true);
		model.addObject("newAuction", new Auction());
		return model;
	}
	
	@ActionMapping(params = "action=createNewAuction")
	public void createNewAuctionAction(ActionRequest request, ActionResponse response,
			@ModelAttribute("newAuction") Auction newAuction){
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("exist", "mySettingsView");
		model.addObject("mySettingsView", true);
		//return model;
	}



}
