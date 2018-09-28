package com.auctions.system.module.auction_process.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.module.auction_process.dao.AuctionProcessDAO;
import com.auctions.system.module.auction_process.model.AuctionOffer;
import com.auctions.system.module.auction_process.model.PaymentMethod;
import com.auctions.system.module.auction_process.model.PurchaseInfo;
import com.auctions.system.module.auction_process.service.AuctionProcessService;
import com.auctions.system.portlet.category.model.AuctionDetails;
import com.auctions.system.portlet.category.model.UserDetails;

@Service("auctionProcessService")
public class AuctionProcessServiceImpl implements AuctionProcessService {
	
	@Autowired
	AuctionProcessDAO dataSource;
	
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

	public List<AuctionOffer> getAllOffers(long auctionId){
		return dataSource.getAllOffers(auctionId);
	}
	
	public List<PaymentMethod> getPaymentMethods(){
		return dataSource.getPaymentMethods();
	}
	
	public PurchaseInfo getPurchaseInfo(long auctionId){
		return dataSource.getPurchaseInfo(auctionId);
	}
}
