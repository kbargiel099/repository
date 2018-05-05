package com.auctions.system.module.auction_processing.controller;

import java.io.IOException;

import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.portlet.ModelAndView;

import com.auctions.system.module.auction_processing.service.AuctionProcessingService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
	
	public void getAllOffers(long auctionId, ResourceResponse response) throws IOException{
		JsonObject obj = new JsonObject();
		obj.addProperty("offers",new Gson().toJson(service.getAllOffers(auctionId)));
		obj.addProperty("success", true);
		
		response.setContentType("application/json");
		response.getWriter().write(obj.toString());
	}
	
	public void getVideoName(long id, ResourceResponse response) throws IOException{
		String name = service.getVideoName(id);
		
		JsonObject obj = new JsonObject();
		obj.addProperty("name", name.split("\\.")[0]);
		
		response.setContentType("application/json");
		response.getWriter().write(obj.toString());
	}
	
	public void createObservation(long userId, long auctionId, ResourceResponse response) throws IOException{
		JsonObject obj = new JsonObject();
		obj.addProperty("success",service.createObservation(userId, auctionId));
		
		response.setContentType("application/json");
		response.getWriter().write(obj.toString());
	}
	
	public void removeObservation(long userId, long auctionId, ResourceResponse response) throws IOException{
		JsonObject obj = new JsonObject();
		obj.addProperty("success",service.removeObservation(userId, auctionId));
		
		response.setContentType("application/json");
		response.getWriter().write(obj.toString());
	}	
}

