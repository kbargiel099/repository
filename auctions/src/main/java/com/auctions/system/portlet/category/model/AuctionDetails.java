package com.auctions.system.portlet.category.model;

public class AuctionDetails {

	private long id;
	private long userId;
	private String serialNumber;
	private String name;
	private String description;
	private String createDate;
	private String endDate;	
	private String imageName;
	private String typeName;
	private long videoId;
	private int subjectQuantity;
	private long subjectPrice;
	private long minimalPrice;
	
	public AuctionDetails(long id, long userId, String serialNumber, String name, String description, String createDate, String endDate, 
			String imageName, String typeName, long videoId, int subjectQuantity, long subjectPrice, long minimalPrice) {
		super();
		this.id = id;
		this.userId = userId;
		this.serialNumber = serialNumber;
		this.name = name;
		this.description = description;
		this.createDate = createDate;
		this.endDate = endDate;
		this.imageName = imageName;
		this.typeName = typeName;
		this.videoId = videoId;
		this.subjectQuantity = subjectQuantity;
		this.subjectPrice = subjectPrice;
		this.minimalPrice = minimalPrice;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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

	public long getVideoId() {
		return videoId;
	}

	public void setHasVideo(long videoId) {
		this.videoId = videoId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setVideoId(long videoId) {
		this.videoId = videoId;
	}

	public long getMinimalPrice() {
		return minimalPrice;
	}

	public void setMinimalPrice(long minimalPrice) {
		this.minimalPrice = minimalPrice;
	}
	
}
