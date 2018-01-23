package com.auctions.system.portlet.home_page.model;

public class AuctionPresenter {

	private int id;
	private String auctionName;
	private String imageName;
	private long subjectPrice;
	
	public AuctionPresenter(int id, String auctionName,
			String imageName,long subjectPrice) {
		super();
		this.id = id;
		this.auctionName = auctionName;
		this.subjectPrice = subjectPrice;
		this.imageName = imageName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuctionName() {
		return auctionName;
	}
	public void setAuctionName(String auctionName) {
		this.auctionName = auctionName;
	}
	public long getSubjectPrice() {
		return subjectPrice;
	}
	public void setSubjectPrice(long subjectPrice) {
		this.subjectPrice = subjectPrice;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
}
