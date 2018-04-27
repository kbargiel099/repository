package com.auctions.system.module.auction_processing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.module.auction_processing.dao.AuctionProcessingDAO;
import com.auctions.system.module.auction_processing.service.AuctionProcessingService;
import com.auctions.system.portlet.category.model.AuctionDetails;
import com.auctions.system.portlet.category.model.UserDetails;

@Service("auctionProcessingService")
public class AuctionProcessingServiceImpl implements AuctionProcessingService {
	
	@Autowired
	AuctionProcessingDAO dataSource;
	
	public UserDetails getSellerDetails(long userId){
		return dataSource.getSellerDetails(userId);
	}
	
	public AuctionDetails getAuctionDetails(long auctionId){
		return dataSource.getAuctionDetails(auctionId);
	}
	
	public String getVideoName(long id){
		return dataSource.getVideoName(id);
	}
	
	public boolean createObservation(long userId,long auctionId){
		return dataSource.createObservation(userId, auctionId);
	}
	
	public boolean removeObservation(long userId,long auctionId){
		return dataSource.removeObservation(userId, auctionId);
	}
	
	public boolean isObserved(long userId,long auctionId){
		return dataSource.isObserved(userId, auctionId);
	}

}
