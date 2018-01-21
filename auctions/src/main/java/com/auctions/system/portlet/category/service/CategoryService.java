package com.auctions.system.portlet.category.service;

import java.util.List;

import com.auctions.system.portlet.category.model.AuctionDetails;
import com.auctions.system.portlet.category.model.UserDetails;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;

public interface CategoryService {
	
	public List<AuctionPresenter> getBestAuctionsByCategory(String category);
	
	public AuctionPresenter getBestAuctionsById(int auctionId);
	
	public UserDetails getSellerDetails(long userId);
	
	public AuctionDetails getAuctionDetails(int auctionId);

}