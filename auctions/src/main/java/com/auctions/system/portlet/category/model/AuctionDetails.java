package com.auctions.system.portlet.category.model;

import java.sql.Timestamp;

public class AuctionDetails {

	private int id;
	private String serialNumber;
	private String auctionName;
	private String subjectName;
	private String description;
	private String createDate;
	private String endDate;	
	private String imageName;
	private int subjectQuantity;
	private long subjectPrice;
	
	public AuctionDetails(int id,String serialNumber, String auctionName, String subjectName, String description, String createDate,
			String endDate, String imageName, int subjectQuantity, long subjectPrice) {
		super();
		this.id = id;
		this.serialNumber = serialNumber;
		this.auctionName = auctionName;
		this.subjectName = subjectName;
		this.description = description;
		this.createDate = createDate;
		this.endDate = endDate;
		this.imageName = imageName;
		this.subjectQuantity = subjectQuantity;
		this.subjectPrice = subjectPrice;
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

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getSubjectQuantity() {
		return subjectQuantity;
	}

	public void setSubjectQuantity(int subjectQuantity) {
		this.subjectQuantity = subjectQuantity;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
}