package com.auctions.system.portlet.home_page.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.auctions.system.module.auction_processing.controller.AuctionProcessing;
import com.auctions.system.module.auction_processing.service.AuctionProcessingService;
import com.auctions.system.module.profile.service.ProfileService;
import com.auctions.system.portlet.home_page.service.HomePageService;

@Controller
@RequestMapping("VIEW")
public class HomePageController implements AuctionProcessing {

	private final String defaultView = "view";
	
	@Autowired
	private HomePageService service;
	
	@Autowired
	private AuctionProcessingService processService;
	
	@Autowired
	private ProfileService profileService;
	
	@RenderMapping()
	public ModelAndView defaulView(RenderRequest request, RenderResponse response) throws Exception{

		ModelAndView model = new ModelAndView(defaultView);
		//model.addObject("electronicAuctions",service.getBestAuctionsByCategory("electronics"));
		model.addObject("newestAuctions", service.getNewestAuction());
		return model;
	}

	@Override
	public ProfileService getProfileService() {
		return profileService;
	}

	@Override
	public AuctionProcessingService getService() {
		return processService;
	}
	
}
	