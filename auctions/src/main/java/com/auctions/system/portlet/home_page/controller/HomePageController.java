package com.auctions.system.portlet.home_page.controller;

import java.io.IOException;

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

import com.auctions.system.module.auction_processing.controller.AuctionProcessing;
import com.auctions.system.module.profile.controller.ProfileController;
import com.auctions.system.portlet.home_page.service.HomePageService;
import com.google.gson.JsonObject;

/**
 * Portlet implementation class HomePageController
 */
@Controller
@RequestMapping("VIEW")
public class HomePageController {

	private final String defaultView = "view";
	
	@Autowired
	private HomePageService service;
	@Autowired
	AuctionProcessing auctionProcessing;
	@Autowired
	ProfileController profile;
	
	@RenderMapping
	public ModelAndView defaulView(RenderRequest request, RenderResponse response) throws Exception{

		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("electronicAuctions",service.getBestAuctionsByCategory("electronics"));
		model.addObject("newestAuctions", service.getNewestAuction());
		return model;
	}
	
	@RequestMapping(params = "page=auctionDetails")
	public ModelAndView defaulView(RenderRequest request, RenderResponse response,
			@RequestParam("id") long id) throws Exception{
		
		return auctionProcessing.createAuctionDetailsView(id);
	}
	
	@RequestMapping(params = "page=userProfile")
	public ModelAndView getUserProfile(RenderRequest request, RenderResponse response,
			@RequestParam("id") long id) throws Exception{
		
		return profile.createUserProfileView(id);
	}
	
	@ResourceMapping("getVideoName")
	public void getVideoName(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{	
		JsonObject obj = new JsonObject();
		obj.addProperty("name", auctionProcessing.getVideoName(id));
		response.setContentType("application/json");
		response.getWriter().write(obj.toString());
	}

}
	
