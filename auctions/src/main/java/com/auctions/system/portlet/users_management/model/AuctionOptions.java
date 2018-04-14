package com.auctions.system.portlet.users_management.model;

import java.util.List;

import com.auctions.system.module.Option;

public class AuctionOptions extends AuctionDatatable{

	private List<Option> options;
	
	public AuctionOptions(AuctionDatatable auction,List<Option> options) {
		super(auction);
		this.options = options;
	}
	
	public AuctionOptions(long id, String name, String createDate,String imageName,List<Option> options) {
		super(id, name, createDate,imageName);
		this.options = options;
	}

}