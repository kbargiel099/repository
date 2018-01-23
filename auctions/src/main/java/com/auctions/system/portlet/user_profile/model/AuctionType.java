package com.auctions.system.portlet.user_profile.model;

public class AuctionType {
	
	private int id;
	private String name;
	
	public AuctionType(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
