package com.auctions.system.portlet.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.portlet.category.dao.CategoryDAO;
import com.auctions.system.portlet.category.model.AuctionDetails;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.category.model.UserDetails;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.category.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryDAO dataSource;
	
	@Override
	public List<AuctionPresenter> getBestAuctionsByCategory(String category){
		return dataSource.getBestAuctionsByCategory(category);
	}
	
	@Override
	public AuctionPresenter getBestAuctionsById(int auctionId){
		return dataSource.getBestAuctionsById(auctionId);
	}
	
	@Override
	public UserDetails getSellerDetails(long userId){
		return dataSource.getSellerDetails(userId);
	}
	
	@Override
	public AuctionDetails getAuctionDetails(int auctionId){
		return dataSource.getAuctionDetails(auctionId);
	}
	@Override
	public List<SubCategory> getSubCategories(String categoryName){
		return dataSource.getSubCategories(categoryName);
	}

}
