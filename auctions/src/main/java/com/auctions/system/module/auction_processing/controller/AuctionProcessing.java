package com.auctions.system.module.auction_processing.controller;

import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.auction_processing.model.TransactionSummary;
import com.auctions.system.module.auction_processing.service.AuctionProcessingService;
import com.auctions.system.module.profile.controller.ProfileController;
import com.auctions.system.portlet.category.model.AuctionDetails;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.liferay.portal.kernel.util.PortalUtil;

@Component
public class AuctionProcessing extends ProfileController{
	
	private final String detailsView = "auction-details-view";
	private final String confirmPurchaseView = "confirm-purchase-view";
	
	@Autowired
	AuctionProcessingService service;
	
	@RequestMapping(params = "page=auctionDetails")
	public ModelAndView detailsView(RenderRequest request, RenderResponse response,
			@RequestParam("id") long id) throws Exception {
		ModelAndView model = new ModelAndView(detailsView);
		model.addObject("auction",service.getAuctionDetails(id));
		model.addObject("seller", service.getSellerDetails(20155));
		model.addObject("isObserved",service.isObserved(PortalUtil.getUserId(request), id));
		return model;
	}	
	
	@RequestMapping(params = "page=confirmPurchase")
	public ModelAndView confirmPurchaseView(RenderRequest request, RenderResponse response,
			@RequestParam("summary") String form) throws Exception {
		TransactionSummary t = new Gson().fromJson(form, TransactionSummary.class);
		
		ModelAndView model = new ModelAndView(confirmPurchaseView);
		model.addObject("seller", service.getSellerDetails(t.getSellerId()));
		model.addObject("paymentMethods",service.getPaymentMethods());
		model.addObject("summary", t);
		return model;
	}	
	
	@ResourceMapping("getAllOffers")
	public void getAllOffers(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{	
		JsonObject obj = new JsonObject();
		obj.addProperty("offers",new Gson().toJson(service.getAllOffers(id)));
		obj.addProperty("success", true);
		
		response.setContentType("application/json");
		response.getWriter().write(obj.toString());
	}
	
	@ResourceMapping("getVideoName")
	public void getVideoName(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{	
		String name = service.getVideoName(id);
		
		JsonObject obj = new JsonObject();
		obj.addProperty("name", name.split("\\.")[0]);
		
		response.setContentType("application/json");
		response.getWriter().write(obj.toString());
	}
	
	@ResourceMapping("createObservation")
	public void createObservation(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{	
		JsonObject obj = new JsonObject();
		obj.addProperty("success",service.createObservation(PortalUtil.getUserId(request), id));
		
		response.setContentType("application/json");
		response.getWriter().write(obj.toString());
	}
	
	@ResourceMapping("removeObservation")
	public void removeObservation(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{	
		JsonObject obj = new JsonObject();
		obj.addProperty("success",service.removeObservation(PortalUtil.getUserId(request), id));
		
		response.setContentType("application/json");
		response.getWriter().write(obj.toString());
	}
	
	public AuctionDetails getDetails(long id){
		return service.getAuctionDetails(id);
	}
	
}

