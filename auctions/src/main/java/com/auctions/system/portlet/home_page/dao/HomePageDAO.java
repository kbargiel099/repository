package com.auctions.system.portlet.home_page.dao;

import java.util.List;

import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.user_profile.model.UserProfileAuction;

public interface HomePageDAO {
	
	public List<AuctionPresenter> getBestAuctionsByCategory(String category);
	
	public List<AuctionPresenter> getNewestAuction();

}
