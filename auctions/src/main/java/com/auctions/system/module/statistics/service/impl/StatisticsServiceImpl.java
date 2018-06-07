package com.auctions.system.module.statistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.module.auction_processing.model.AuctionOffer;
import com.auctions.system.module.statistics.dao.StatisticsDAO;
import com.auctions.system.module.statistics.service.StatisticsService;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {
	
	@Autowired
	StatisticsDAO dataSource;

	@Override
	public List<AuctionOffer> getPurchases(long auctionId) {
		return dataSource.getPurchases(auctionId);
	}

	@Override
	public List<AuctionOffer> getWonOffers(long auctionId) {
		return dataSource.getWonOffers(auctionId);
	}

}
