package com.auctions.system.portlet.auctions_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.portlet.auctions_management.dao.AuctionsManagementDAO;
import com.auctions.system.portlet.auctions_management.service.AuctionsManagementService;
import com.auctions.system.portlet.category.model.Category;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionType;
import com.auctions.system.portlet.auctions_management.model.AuctionDatatable;

@Service("AuctionsManagementService")
public class AuctionsManagementServiceImpl implements AuctionsManagementService{

	@Autowired
	AuctionsManagementDAO dataSource;
	
	@Override
	public List<AuctionDatatable> getAuctions(){
		return dataSource.getAuctions();
	}
	@Override
	public boolean activateAuction(long auctionId){
		return dataSource.activateAuction(auctionId);
	}
	@Override
	public boolean suspendAuction(long auctionId){
		return dataSource.suspendAuction(auctionId);
	}
	@Override
	public boolean deleteAuction(long auctionId){
		return dataSource.deleteAuction(auctionId);
	}
	@Override
	public List<AuctionType> getAuctionTypes() {
		return dataSource.getAuctionTypes();
	}
	@Override
	public Auction getAuctionData(long id) {
		return dataSource.getAuctionData(id);
	}
	@Override
	public List<Category> getCategories() {
		return dataSource.getCategories();
	}

}
