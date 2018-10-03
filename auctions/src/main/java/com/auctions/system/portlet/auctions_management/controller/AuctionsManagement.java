package com.auctions.system.portlet.auctions_management.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.auction_process.controller.AuctionProcess;

public interface AuctionsManagement extends AuctionProcess{
	
	@RenderMapping
	public ModelAndView defaultView(RenderRequest request, RenderResponse response) throws Exception;
	
	@RenderMapping(params = "page=stats")
	public ModelAndView auctionStatsView(RenderRequest request, RenderResponse response,
			@RequestParam("auctionId") long auctionId);
	
	@RenderMapping(params = "page=edit")
	public ModelAndView editAuctionRender(RenderRequest request, RenderResponse response,
			@RequestParam("auctionId") long auctionId);
	
	@ResourceMapping(value="getAuctions")
	public void getAuctions(ResourceRequest request, ResourceResponse response) throws Exception;
	
	@ResourceMapping(value="activate")
	public void activateAuction(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long auctionId) throws Exception;
	
	@ResourceMapping(value="suspend")
	public void suspendAuction(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long auctionId) throws Exception;
	
	@ResourceMapping(value="delete")
	public void deleteAuction(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long auctionId) throws Exception;
}
