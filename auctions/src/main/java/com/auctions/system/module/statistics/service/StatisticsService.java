package com.auctions.system.module.statistics.service;

import java.util.List;

import com.auctions.system.module.auction_process.model.AuctionOffer;

public interface StatisticsService {
	
	public List<AuctionOffer> getPurchases(long auctionId);
	
	public List<AuctionOffer> getWonOffers(long auctionId);
}
