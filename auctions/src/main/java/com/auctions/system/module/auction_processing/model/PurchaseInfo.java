package com.auctions.system.module.auction_processing.model;

public class PurchaseInfo {

	private long userId;
	private long sellerId;
	private long price;
	private int quantity;
	private String name;
	private String endDate;
	
	public PurchaseInfo(long userId, long sellerId, long price, int quantity, String name, String endDate) {
		super();
		this.userId = userId;
		this.sellerId = sellerId;
		this.price = price;
		this.quantity = quantity;
		this.name = name;
		this.endDate = endDate;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getSellerId() {
		return sellerId;
	}

	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
