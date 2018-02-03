package com.auctions.system.portlet.home_page.model;

public class AuctionPresenter {

	private int id;
	private String auctionName;
	private String imageName;
	private String imageData;
	private long subjectPrice;
	
	public AuctionPresenter(int id, String auctionName,
			String imageName,String imageData,long subjectPrice) {
		super();
		this.id = id;
		this.auctionName = auctionName;
		this.subjectPrice = subjectPrice;
		this.imageName = imageName;
		this.imageData = imageData;
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

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}
	
}
