package com.auctions.system.portlet.home_page.service;

import java.util.List;

import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.user_profile.model.UserProfileAuction;

public interface HomePageService {

	public boolean isUserExist(String login, String password);
	
	public void getImages(long userId);
	
	public List<AuctionPresenter> getBestAuctionsByCategory(String category);
	
	public List<AuctionPresenter> getNewestAuction();
	
	public List<UserProfileAuction> getUserBoughtSubjects(long userId);
	
	public void createImage();

}
