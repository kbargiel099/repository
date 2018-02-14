package com.auctions.system.module.auction_processing.service;

import com.auctions.system.portlet.category.model.AuctionDetails;
import com.auctions.system.portlet.category.model.UserDetails;

public interface AuctionProcessingService {
	
	public UserDetails getSellerDetails(long userId);
	
	public AuctionDetails getAuctionDetails(int auctionId);

}
