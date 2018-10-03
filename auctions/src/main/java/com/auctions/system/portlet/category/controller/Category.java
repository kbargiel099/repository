package com.auctions.system.portlet.category.controller;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.auction_process.controller.AuctionProcess;

public interface Category extends AuctionProcess{

	@RenderMapping()
	public ModelAndView getSearch(RenderRequest request);
	
	@ResourceMapping("getBySubcategory")
	public void searchForMatching(ResourceRequest request, ResourceResponse response,
			@RequestParam("id") int id);
	
	@ResourceMapping("searchText")
	public void searchForMatching(ResourceRequest request, ResourceResponse response,
			@RequestParam("searchingForm") String searchingForm);
}
