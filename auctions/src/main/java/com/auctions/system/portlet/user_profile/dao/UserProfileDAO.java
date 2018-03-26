package com.auctions.system.portlet.user_profile.dao;

import java.text.ParseException;
import java.util.List;

import com.auctions.system.portlet.category.model.Category;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionGrade;
import com.auctions.system.portlet.user_profile.model.AuctionType;
import com.auctions.system.portlet.user_profile.model.UserProfileAuction;

public interface UserProfileDAO {

	public boolean isUserExist(String login, String password);
	
	public void getImages(long userId);
	
	public List<UserProfileAuction> getUserBoughtSubjects(long userId);
	
	public List<Category> getCategories();
	
	public List<SubCategory> getSubCategories();
	
	public boolean createUserAuction(long userId, Auction a) throws ParseException;
	
	public boolean addAuctionGrade(long userId, AuctionGrade a);
	
	public List<AuctionType> getAuctionTypes();
}
