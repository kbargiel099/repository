package com.auctions.system.portlet.home_page.service;

import java.util.List;

import com.auctions.system.portlet.home_page.model.AuctionPresenter;

public interface HomePageService {
	
	public List<AuctionPresenter> getBestAuctionsByCategory(String category);
	
	public List<AuctionPresenter> getNewestAuction();

}
