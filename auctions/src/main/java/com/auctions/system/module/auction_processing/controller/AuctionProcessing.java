package com.auctions.system.module.auction_processing.controller;

import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.HttpUtil;
import com.auctions.system.module.UserUtil;
import com.auctions.system.module.auction_processing.model.PurchaseInfo;
import com.auctions.system.module.auction_processing.model.TransactionSummary;
import com.auctions.system.module.auction_processing.service.AuctionProcessingService;
import com.auctions.system.module.profile.controller.ProfileController;
import com.auctions.system.portlet.category.model.AuctionDetails;
import com.liferay.portal.kernel.util.PortalUtil;

public interface AuctionProcessing extends ProfileController{
	
	final String detailsView = "auction-details-view";
	final String confirmPurchaseView = "confirm-purchase-view";
	
	public AuctionProcessingService getService();
	
	@RequestMapping(params = "page=auctionDetails")
	public default ModelAndView detailsView(RenderRequest request, RenderResponse response,
			@RequestParam(value = "message", defaultValue = "") String message,
			@RequestParam("id") long id) throws Exception {
		AuctionDetails details = getService().getAuctionDetails(id);
		
		ModelAndView model = new ModelAndView(detailsView);
		model.addObject("auction",details);
		model.addObject("message",message);
		model.addObject("seller", getService().getSellerDetails(details.getUserId()));
		model.addObject("isObserved",getService().isObserved(PortalUtil.getUserId(request), id));
		return model;
	}	
	
	@RenderMapping(params = "page=confirmPurchase")
	public default ModelAndView confirmPurchaseView(RenderRequest request, RenderResponse response,
			@RequestParam("auctionId") long id,@RequestParam("sellerId") long sellerId,
			@RequestParam("auctionName") String name,@RequestParam("price") long price,
			@RequestParam("quantity") int quantity,@RequestParam("endDate") String endDate) throws Exception {
		TransactionSummary transactionInfo = new TransactionSummary(id,name,sellerId,price,quantity,endDate);
		
		ModelAndView model = new ModelAndView(confirmPurchaseView);
		model.addObject("seller", getService().getSellerDetails(transactionInfo.getSellerId()));
		model.addObject("username", UserUtil.getScreenName(PortalUtil.getUserId(request)));
		model.addObject("paymentMethods",getService().getPaymentMethods());
		model.addObject("info", transactionInfo);
		model.addObject("type", "purchase");
		return model;
	}
	
	@RenderMapping(params = "page=getPurchaseInfo")
	public default ModelAndView getConfirmPurchaseView(RenderRequest request, RenderResponse response,
			@RequestParam("auctionId") long id,@RequestParam("type") String type) throws Exception{
		PurchaseInfo a = getService().getPurchaseInfo(id);
		ModelAndView model = confirmPurchaseView(request,response,id,a.getSellerId(),a.getName(),
				a.getPrice(),a.getQuantity(),a.getEndDate());
		model.addObject("type", type);
		return model;
	}
	
	@ResourceMapping("getAllOffers")
	public default void getAllOffers(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{	
		HttpUtil.createResponse(response).
			set("offers", getService().getAllOffers(id)).
			set("success", true).
			prepare();
	}
	
	@ResourceMapping("getVideoName")
	public default void getVideoName(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long id) throws IOException{		
		
		HttpUtil.createResponse(response).
			set("name", getService().getVideoName(id).split("\\.")[0]).
			prepare();
	}
	
	@ResourceMapping("createObservation")
	public default void createObservation(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{	
		long userId = PortalUtil.getUserId(request);
		
		HttpUtil.createResponse(response).
			set("success", getService().createObservation(userId, id)).
			prepare();
	}
	
	@ResourceMapping("removeObservation")
	public default void removeObservation(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{
		long userId = PortalUtil.getUserId(request);
		
		HttpUtil.createResponse(response).
			set("success", getService().removeObservation(userId, id)).
			prepare();
	}
	
	public default AuctionDetails getDetails(long id){
		return getService().getAuctionDetails(id);
	}
	
}
