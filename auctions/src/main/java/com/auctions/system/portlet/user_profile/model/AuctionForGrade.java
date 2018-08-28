package com.auctions.system.portlet.user_profile.model;

public class AuctionForGrade {
	
	private long id;
	private String name;
	
	public AuctionForGrade(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
