package com.auctions.system.module.auction_processing.model;

import com.auctions.system.module.UserUtil;

public class AuctionOffer{

	private long userId;
	private long price;
	private int quantity;
	private String username = "";
	
	public AuctionOffer(long userId, long price, int quantity) {
		this.userId = userId;
		this.price = price;
		this.quantity = quantity;
		this.username = UserUtil.getScreenName(userId);
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String screenName) {
		this.username = screenName;
	}
	
}
