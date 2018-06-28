package com.auctions.system.portlet.messages.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.portlet.category.model.SearchingForm;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.messages.dao.MessagesDAO;
import com.auctions.system.portlet.messages.service.MessagesService;

@Service("messagesService")
public class MessagesServiceImpl implements MessagesService{

	@Autowired
	MessagesDAO dataSource;
	
	@Override
	public List<AuctionPresenter> getBestAuctionsByCategory(String category){
		return dataSource.getBestAuctionsByCategory(category);
	}
	
	@Override
	public List<SubCategory> getSubCategories(String categoryName){
		return dataSource.getSubCategories(categoryName);
	}
	
	@Override
	public List<AuctionPresenter> getAuctionsBySubcategory(int id){
		return dataSource.getAuctionsBySubcategory(id);
	}

	@Override
	public List<AuctionPresenter> getSearchingAuctions(SearchingForm searchingForm) {
		return dataSource.getSearchingAuctions(searchingForm);
	}

}
