package com.auctions.system.module.statistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.portlet.ModelAndView;

import com.auctions.system.module.UserUtil;
import com.auctions.system.module.auction_process.model.AuctionOffer;
import com.auctions.system.module.statistics.model.ViewType;
import com.auctions.system.module.statistics.service.StatisticsService;
import com.auctions.system.portlet.category.model.AuctionDetails;

@Component
public class Statistics {
	
	private final String defaultView = "auction_stats";
	
	@Autowired
	StatisticsService service;
	
	public ModelAndView getAuctionStatsView(AuctionDetails auction, ViewType type){
		long id = auction.getId();
		List<AuctionOffer> transactions = auction.getTypeName().equals("quick_purchase") ?
				service.getPurchases(id) : service.getWonOffers(id);
				
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("transactions", transactions);
		model.addObject("username", UserUtil.getScreenName(auction.getUserId()));
		model.addObject("auction", auction);
		model.addObject("view", getViewType(type));
		return model;
	}
	
	private String getViewType(ViewType type){
		switch(type){
			case Profile:
				return "profile";
			case Administration:
				return "administration";
			default:
				return "";
		}
	}
	
}

