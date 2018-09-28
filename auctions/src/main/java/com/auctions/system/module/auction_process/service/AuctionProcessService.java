package com.auctions.system.module.auction_process.service;

import java.util.List;

import com.auctions.system.module.auction_process.model.AuctionOffer;
import com.auctions.system.module.auction_process.model.PaymentMethod;
import com.auctions.system.module.auction_process.model.PurchaseInfo;
import com.auctions.system.portlet.category.model.AuctionDetails;
import com.auctions.system.portlet.category.model.UserDetails;

public interface AuctionProcessService {
	
	public UserDetails getSellerDetails(long userId);
	
	public AuctionDetails getAuctionDetails(long id);
	
	public String getVideoName(long id);
	
	public boolean createObservation(long userId,long auctionId);
	
	public boolean removeObservation(long userId,long auctionId);
	
	public boolean isObserved(long userId,long auctionId);
	
	public List<AuctionOffer> getAllOffers(long auctionId);
	
	public List<PaymentMethod> getPaymentMethods();
	
	public PurchaseInfo getPurchaseInfo(long auctionId);

}
