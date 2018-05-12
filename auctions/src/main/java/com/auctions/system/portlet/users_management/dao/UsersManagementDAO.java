package com.auctions.system.portlet.users_management.dao;

import java.util.List;

import com.auctions.system.module.auction_processing.model.AuctionOffer;
import com.auctions.system.portlet.users_management.model.AuctionDatatable;
import com.auctions.system.portlet.users_management.model.User;

public interface UsersManagementDAO {

	public List<User> getUsers();
	
	public User getUserById(int userId);
	
	public List<AuctionDatatable> getAuctions();
	
	public boolean activateAuction(long auctionId);
	
	public boolean suspendAuction(long auctionId);
	
	public boolean deleteAuction(long auctionId) ;
	
	public List<AuctionOffer> getPurchases(long auctionId);
	
	public List<AuctionOffer> getWonOffers(long auctionId);
	
}
