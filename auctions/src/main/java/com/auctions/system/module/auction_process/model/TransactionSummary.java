package com.auctions.system.module.auction_process.model;

public class TransactionSummary {
	
	private long auctionId;
	private String auctionName;
	private long sellerId;
	private long price;
	private int quantity;
	private String endDate;
	
	public TransactionSummary(long auctionId, String auctionName, long sellerId, long price, int quantity, String endDate) {
		super();
		this.auctionId = auctionId;
		this.auctionName = auctionName;
		this.sellerId = sellerId;
		this.price = price;
		this.quantity = quantity;
		this.endDate = endDate;
	}

	public long getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(long auctionId) {
		this.auctionId = auctionId;
	}

	public String getAuctionName() {
		return auctionName;
	}

	public void setAuctionName(String auctionName) {
		this.auctionName = auctionName;
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

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
