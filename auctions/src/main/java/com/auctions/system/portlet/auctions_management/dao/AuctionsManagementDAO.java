package com.auctions.system.portlet.auctions_management.dao;

import java.util.List;

import com.auctions.system.portlet.auctions_management.model.AuctionDatatable;
import com.auctions.system.portlet.category.model.Category;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionType;

public interface AuctionsManagementDAO {
	
	public List<AuctionDatatable> getAuctions();
	
	public List<AuctionType> getAuctionTypes();
	
	public Auction getAuctionData(final long id);
	
	public List<Category> getCategories();
	
	public boolean activateAuction(long auctionId);
	
	public boolean suspendAuction(long auctionId);
	
	public boolean deleteAuction(long auctionId) ;
	
}
