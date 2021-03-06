package com.auctions.system.portlet.home_page.controller;

import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

import com.auctions.system.module.auction_process.controller.AuctionProcess;
import com.auctions.system.portlet.category.model.AuctionDetails;
import com.auctions.system.portlet.home_page.service.HomePageService;

@Controller
@RequestMapping("VIEW")
public class HomePageController implements HomePage{

	private final String defaultView = "view";
	
	@Autowired
	private HomePageService service;
	
	@Autowired
	AuctionProcess processing;
	
	@Override
	public ModelAndView defaultView(RenderRequest request, RenderResponse response) throws Exception{
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("newestAuctions", service.getNewestAuction());
		return model;
	}
	
	@Override
	public ModelAndView detailsView(RenderRequest request, RenderResponse response, String message, long id) throws Exception {
		return processing.detailsView(request, response, message, id);
	}

	@Override
	public ModelAndView confirmPurchaseView(RenderRequest request, RenderResponse response, String form) throws Exception {
		return processing.confirmPurchaseView(request, response, form);
	}

	@Override
	public ModelAndView getConfirmPurchaseView(RenderRequest request, RenderResponse response, long id, String type)
			throws Exception {
		return processing.getConfirmPurchaseView(request, response, id, type);
	}

	@Override
	public void getAllOffers(ResourceRequest request, ResourceResponse response, int auctionId) throws IOException {
		processing.getAllOffers(request, response, auctionId);
	}

	@Override
	public void getVideoName(ResourceRequest request, ResourceResponse response, long auctionId) throws IOException {
		processing.getVideoName(request, response, auctionId);
	}

	@Override
	public void createObservation(ResourceRequest request, ResourceResponse response, int id) throws IOException {
		processing.createObservation(request, response, id);
	}

	@Override
	public void removeObservation(ResourceRequest request, ResourceResponse response, int id) throws IOException {
		processing.removeObservation(request, response, id);
	}

	@Override
	public ModelAndView getUserProfile(RenderRequest request, RenderResponse response, long id) throws Exception {
		return processing.getUserProfile(request, response, id);
	}
	
	@Override
	public AuctionDetails getDetails(long id) {
		return processing.getDetails(id);
	}
	
}
	