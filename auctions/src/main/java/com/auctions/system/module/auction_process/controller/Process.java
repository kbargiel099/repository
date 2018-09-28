package com.auctions.system.module.auction_process.controller;

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

import com.auctions.system.module.profile.controller.Profile;
import com.auctions.system.portlet.category.model.AuctionDetails;

public interface Process extends Profile{

	@RequestMapping(params = "page=auctionDetails")
	public ModelAndView detailsView(RenderRequest request, RenderResponse response,
			@RequestParam(value = "message", defaultValue = "") String message,
			@RequestParam("id") long id) throws Exception;
	
	@RenderMapping(params = "page=confirmPurchase")
	public ModelAndView confirmPurchaseView(RenderRequest request, RenderResponse response,
			@RequestParam("form") String form) throws Exception;
	
	@RenderMapping(params = "page=getPurchaseInfo")
	public ModelAndView getConfirmPurchaseView(RenderRequest request, RenderResponse response,
			@RequestParam("id") long id,@RequestParam("type") String type) throws Exception;
	
	@ResourceMapping("getAllOffers")
	public void getAllOffers(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException;
	
	@ResourceMapping("getVideoName")
	public void getVideoName(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long id) throws IOException;
	
	@ResourceMapping("createObservation")
	public void createObservation(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException;
	
	@ResourceMapping("removeObservation")
	public void removeObservation(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException;
	
	public AuctionDetails getDetails(long id);
	
}
