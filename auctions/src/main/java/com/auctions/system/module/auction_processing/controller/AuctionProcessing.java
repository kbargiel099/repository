package com.auctions.system.module.auction_processing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.portlet.ModelAndView;

import com.auctions.system.module.auction_processing.service.AuctionProcessingService;

@Component
public class AuctionProcessing {
	
	private final String detailsView = "auction-details-view";
	
	@Autowired
	AuctionProcessingService service;
	
	public ModelAndView createAuctionDetailsView(long id,long userId){
		ModelAndView model = new ModelAndView(detailsView);
		model.addObject("auction",service.getAuctionDetails(id));
		model.addObject("seller", service.getSellerDetails(20155));
		model.addObject("isObserved",service.isObserved(userId, id));
		return model;
		
	}	
	
	public String getVideoName(long id){
		String name = service.getVideoName(id);
		return name.split("\\.")[0];
	}
	
	public boolean createObservation(long userId, long auctionId){
		return service.createObservation(userId, auctionId);
	}
	
	public boolean removeObservation(long userId, long auctionId){
		
		return service.removeObservation(userId, auctionId);
	}	
}

