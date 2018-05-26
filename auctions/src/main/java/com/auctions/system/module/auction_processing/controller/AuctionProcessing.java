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

import com.auctions.system.module.HttpUtil;
import com.auctions.system.module.UserUtil;
import com.auctions.system.module.auction_processing.model.TransactionSummary;
import com.auctions.system.module.auction_processing.service.AuctionProcessingService;
import com.auctions.system.module.profile.controller.ProfileController;
import com.auctions.system.portlet.category.model.AuctionDetails;
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
			@RequestParam("auctionId") long id,@RequestParam("sellerId") long sellerId,
			@RequestParam("auctionName") String name,@RequestParam("price") long price,
			@RequestParam("quantity") int quantity,@RequestParam("endDate") String endDate) throws Exception {
		TransactionSummary transactionInfo = new TransactionSummary(id,name,sellerId,price,quantity,endDate);
		
		ModelAndView model = new ModelAndView(confirmPurchaseView);
		model.addObject("seller", service.getSellerDetails(transactionInfo.getSellerId()));
		model.addObject("username", UserUtil.getScreenName(PortalUtil.getUserId(request)));
		model.addObject("paymentMethods",service.getPaymentMethods());
		model.addObject("info", transactionInfo);
		return model;
	}	
	
	@ResourceMapping("getAllOffers")
	public void getAllOffers(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{	
		HttpUtil.createResponse(response).
			set("offers", service.getAllOffers(id)).
			set("success", true).
			prepare();
	}
	
	@ResourceMapping("getVideoName")
	public void getVideoName(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{		
		
		HttpUtil.createResponse(response).
			set("name", service.getVideoName(id).split("\\.")[0]).
			prepare();
	}
	
	@ResourceMapping("createObservation")
	public void createObservation(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{	
		long userId = PortalUtil.getUserId(request);
		
		HttpUtil.createResponse(response).
			set("success", service.createObservation(userId, id)).
			prepare();
	}
	
	@ResourceMapping("removeObservation")
	public void removeObservation(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{
		long userId = PortalUtil.getUserId(request);
		
		HttpUtil.createResponse(response).
			set("success", service.removeObservation(userId, id)).
			prepare();
	}
	
	public AuctionDetails getDetails(long id){
		return service.getAuctionDetails(id);
	}
	
}

