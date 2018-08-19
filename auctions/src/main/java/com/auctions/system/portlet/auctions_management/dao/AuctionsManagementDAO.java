package com.auctions.system.portlet.auctions_management.dao;

import java.util.List;

import com.auctions.system.portlet.auctions_management.model.AuctionDatatable;

public interface AuctionsManagementDAO {
	
	public List<AuctionDatatable> getAuctions();
	
	public boolean activateAuction(long auctionId);
	
	public boolean suspendAuction(long auctionId);
	
	public boolean deleteAuction(long auctionId) ;
	
}
