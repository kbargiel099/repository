package com.auctions.system.portlet.user_profile.dao;

import java.text.ParseException;
import java.util.List;

import com.auctions.system.portlet.category.model.Category;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionGrade;
import com.auctions.system.portlet.user_profile.model.AuctionType;
import com.auctions.system.portlet.user_profile.model.TechnicalData;

public interface UserProfileDAO {
	
	public List<AuctionPresenter> getUserBoughtSubjects(long userId);
	
	public List<AuctionPresenter> getUserAuctions(long userId);
	
	public List<AuctionPresenter> getUserObservation(long userId);
	
	public boolean createAuctionVideo(long auctionId, String videoName);
	
	public List<Category> getCategories();
	
	public List<SubCategory> getSubCategories();
	
	public boolean createUserAuction(long userId, Auction a) throws ParseException;
	
	public boolean addAuctionGrade(long userId, AuctionGrade a);
	
	public List<AuctionType> getAuctionTypes();
	
	public List<TechnicalData> getTechnicalData();
}
