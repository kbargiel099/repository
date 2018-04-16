package com.auctions.system.portlet.user_profile.service.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.portlet.category.model.Category;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.user_profile.dao.UserProfileDAO;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionGrade;
import com.auctions.system.portlet.user_profile.model.AuctionType;
import com.auctions.system.portlet.user_profile.model.UserProfileAuction;
import com.auctions.system.portlet.user_profile.service.UserProfileService;

@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService{

	@Autowired
	UserProfileDAO dataSource;
	
	@Override
	public boolean isUserExist(String login, String password) {
		return dataSource.isUserExist(login, password);
	}
	
	@Override
	public List<UserProfileAuction> getUserBoughtSubjects(long userId){
		return dataSource.getUserBoughtSubjects(userId);
	}
	
	@Override
	public List<AuctionPresenter> getUserAuctions(long userId){
		return dataSource.getUserAuctions(userId);
	}
	
	@Override
	public boolean createAuctionVideo(long auctionId, String videoName){
		return dataSource.createAuctionVideo(auctionId, videoName);
	}
	
	@Override
	public List<Category> getCategories(){
		return dataSource.getCategories();
	}
	
	@Override
	public List<SubCategory> getSubCategories(){
		return dataSource.getSubCategories();
	}
	
	@Override
	public boolean createUserAuction(long userId, Auction a) throws ParseException{
		return dataSource.createUserAuction(userId, a);
	}
	
	@Override
	public boolean addAuctionGrade(long userId, AuctionGrade a){
		return dataSource.addAuctionGrade(userId, a);
	}
	
	@Override
	public List<AuctionType> getAuctionTypes(){
		return dataSource.getAuctionTypes();
	}

}
