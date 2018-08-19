package com.auctions.system.portlet.auctions_management.service;

import java.util.List;

import com.auctions.system.portlet.auctions_management.model.AuctionDatatable;

public interface AuctionsManagementService {
	
	public List<AuctionDatatable> getAuctions();
	
	public boolean activateAuction(long auctionId);
	
	public boolean suspendAuction(long auctionId);
	
	public boolean deleteAuction(long auctionId);
	
}
