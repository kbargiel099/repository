package com.auctions.system.module.statistics.dao;

import java.util.List;

import com.auctions.system.module.auction_processing.model.AuctionOffer;

public interface StatisticsDAO {
	
	public List<AuctionOffer> getPurchases(long auctionId);
	
	public List<AuctionOffer> getWonOffers(long auctionId);

}
