package com.auctions.system.portlet.category.model;

public class AuctionDetails {

	private int id;
	private String serialNumber;
	private String name;
	private String description;
	private String createDate;
	private String endDate;	
	private String imageName;
	private String imageData;
	private int subjectQuantity;
	private long subjectPrice;
	
	public AuctionDetails(int id,String serialNumber, String name, String description, String createDate,
			String endDate, String imageName,String imageData, int subjectQuantity, long subjectPrice) {
		super();
		this.id = id;
		this.serialNumber = serialNumber;
		this.name = name;
		this.description = description;
		this.createDate = createDate;
		this.endDate = endDate;
		this.imageName = imageName;
		this.imageData = imageData;
		this.subjectQuantity = subjectQuantity;
		this.subjectPrice = subjectPrice;
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

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}
	
}
