package com.auctions.system.portlet.category.service;

import java.util.List;

import com.auctions.system.portlet.category.model.SearchingForm;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;

public interface CategoryService{
	
	public List<AuctionPresenter> getBestAuctionsByCategory(String category);
	
	public AuctionPresenter getBestAuctionsById(int auctionId);
	
	public List<SubCategory> getSubCategories(String categoryName);
	
	public List<AuctionPresenter> getSearchingAuctions(SearchingForm searchingForm);

}
