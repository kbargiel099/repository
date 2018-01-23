package com.auctions.system.portlet.home_page.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.auctions.system.portlet.home_page.service.HomePageService;

/**
 * Portlet implementation class HomePageController
 */
@Controller
@RequestMapping("VIEW")
public class HomePageController {

	private final String defaultView = "view";
	private final String categoryView = "category-view"; 
	private final String detailsView = "details"; 
	
	@Autowired
	private HomePageService service;
	
	@RenderMapping
	public ModelAndView defaulView(RenderRequest request, RenderResponse response) throws Exception{

		ModelAndView model = new ModelAndView(defaultView);
		//service.getImages(PortalUtil.getUserId(request));
		//service.createImage();
		model.addObject("electronicAuctions",service.getBestAuctionsByCategory("electronics"));
		return model;
	}

}
	
