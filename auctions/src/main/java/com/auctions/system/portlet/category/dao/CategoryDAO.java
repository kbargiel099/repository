package com.auctions.system.portlet.category.dao;

import java.util.List;

import com.auctions.system.portlet.category.model.AuctionDetails;
import com.auctions.system.portlet.category.model.SearchingForm;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.category.model.UserDetails;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;

public interface CategoryDAO {
	
	public List<AuctionPresenter> getBestAuctionsByCategory(String category);
	
	public AuctionPresenter getBestAuctionsById(int auctionId);
	
	public List<SubCategory> getSubCategories(String categoryName);
	
	public List<AuctionPresenter> getAuctionsBySubcategory(int id);
	
	public List<AuctionPresenter> getSearchingAuctions(SearchingForm searchingForm);

}
