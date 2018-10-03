package com.auctions.system.portlet.auctions_management.controller;

import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

import com.auctions.system.module.HttpUtil;
import com.auctions.system.module.auction_process.controller.AuctionProcess;
import com.auctions.system.module.statistics.controller.Statistics;
import com.auctions.system.module.statistics.model.ViewType;
import com.auctions.system.portlet.auctions_management.service.AuctionsManagementService;
import com.auctions.system.portlet.category.model.AuctionDetails;

@Controller
@RequestMapping("VIEW")
public class AuctionsController implements AuctionsManagement{

	private final String auctionsView = "auctions";
		
	@Autowired
	AuctionProcess processing;
	
	@Autowired
	AuctionsManagementService service;
	
	@Autowired
	Statistics stats;
	
	
	@Override
	public ModelAndView defaultView(RenderRequest request, RenderResponse response) throws Exception{
		ModelAndView model = new ModelAndView(auctionsView);
		return model;
	}
	
	@Override
	public ModelAndView auctionStatsView(RenderRequest request, RenderResponse response, long auctionId){
		return stats.getAuctionStatsView(processing.getDetails(auctionId),ViewType.Administration);
	}
	
	@Override
	public void getAuctions(ResourceRequest request, ResourceResponse response) throws Exception {

		HttpUtil.createResponse(response).
			set("data", service.getAuctions()).
			prepare();
	}
	
	@Override
	public void activateAuction(ResourceRequest request, ResourceResponse response, long auctionId) throws Exception {
	    
		HttpUtil.createResponse(response).
			set("success", service.activateAuction(auctionId)).
			prepare();
	}
	
	@Override
	public void suspendAuction(ResourceRequest request, ResourceResponse response, long auctionId) throws Exception {
	    
		HttpUtil.createResponse(response).
			set("success", service.suspendAuction(auctionId)).
			prepare();
	}
	
	@Override
	public void deleteAuction(ResourceRequest request, ResourceResponse response, long auctionId) throws Exception {
	    
		HttpUtil.createResponse(response).
			set("success", service.deleteAuction(auctionId)).
			prepare();
	}
	
	@Override
	public ModelAndView detailsView(RenderRequest request, RenderResponse response, String message, long id)
			throws Exception {
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