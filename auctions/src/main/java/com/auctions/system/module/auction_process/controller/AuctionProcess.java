package com.auctions.system.module.auction_process.controller;

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

import com.auctions.system.module.HttpUtil;
import com.auctions.system.module.Serializer;
import com.auctions.system.module.UserUtil;
import com.auctions.system.module.auction_process.model.PurchaseInfo;
import com.auctions.system.module.auction_process.model.TransactionSummary;
import com.auctions.system.module.auction_process.service.AuctionProcessService;
import com.auctions.system.module.profile.controller.ProfileController;
import com.auctions.system.portlet.category.model.AuctionDetails;
import com.liferay.portal.kernel.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class AuctionProcess implements Process{
	
	final String detailsView = "auction-details-view";
	final String confirmPurchaseView = "confirm-purchase-view";
	
	@Autowired
	private AuctionProcessService service;
	
	@Autowired 
	ProfileController profile;
	
	@RequestMapping(params = "page=auctionDetails")
	public ModelAndView detailsView(RenderRequest request, RenderResponse response,
			@RequestParam(value = "message", defaultValue = "") String message,
			@RequestParam("id") long id) throws Exception {
		AuctionDetails details = service.getAuctionDetails(id);
		
		ModelAndView model = new ModelAndView(detailsView);
		model.addObject("auction",details);
		model.addObject("message",message);
		model.addObject("seller", service.getSellerDetails(details.getUserId()));
		model.addObject("isObserved",service.isObserved(PortalUtil.getUserId(request), id));
		return model;
	}	
	
	@RenderMapping(params = "page=confirmPurchase")
	public ModelAndView confirmPurchaseView(RenderRequest request, RenderResponse response,
			@RequestParam("form") String form) throws Exception {
		TransactionSummary transactionInfo = Serializer.fromJson(form, TransactionSummary.class);
		
		ModelAndView model = new ModelAndView(confirmPurchaseView);
		model.addObject("seller", service.getSellerDetails(transactionInfo.getSellerId()));
		model.addObject("username", UserUtil.getScreenName(PortalUtil.getUserId(request)));
		model.addObject("paymentMethods", service.getPaymentMethods());
		model.addObject("info", transactionInfo);
		model.addObject("type", "purchase");
		return model;
	}
	
	@RenderMapping(params = "page=getPurchaseInfo")
	public ModelAndView getConfirmPurchaseView(RenderRequest request, RenderResponse response,
			@RequestParam("id") long id,@RequestParam("type") String type) throws Exception{
		PurchaseInfo a = service.getPurchaseInfo(id);
		ModelAndView model = confirmPurchaseView(request,response,Serializer.toJson(a));
		model.addObject("type", type);
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
			@RequestParam("auctionId") long id) throws IOException{		
		
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
	
	@Override
	public ModelAndView getUserProfile(RenderRequest request, RenderResponse response, long id) throws Exception {
		return profile.getUserProfile(request, response, id);
	}
	
	public AuctionDetails getDetails(long id){
		return service.getAuctionDetails(id);
	}
	
}
