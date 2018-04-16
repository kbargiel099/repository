package com.auctions.system.portlet.home_page.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.portlet.home_page.dao.HomePageDAO;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.home_page.service.HomePageService;
import com.auctions.system.portlet.user_profile.model.UserProfileAuction;

@Service("homePageService")
public class HomePageServiceImpl implements HomePageService{

	@Autowired
	HomePageDAO dataSource;
	
	@Override
	public List<AuctionPresenter> getBestAuctionsByCategory(String category){
		return dataSource.getBestAuctionsByCategory(category);
	}
	
	@Override
	public List<AuctionPresenter> getNewestAuction(){
		return dataSource.getNewestAuction();
	}

}
