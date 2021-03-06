package com.auctions.system.portlet.home_page.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.auctions.system.module.auction_process.controller.AuctionProcess;

public interface HomePage extends AuctionProcess{

	@RenderMapping()
	public ModelAndView defaultView(RenderRequest request, RenderResponse response) throws Exception;
	
}
