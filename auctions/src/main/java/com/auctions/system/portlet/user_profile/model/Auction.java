package com.auctions.system.portlet.user_profile.model;


public class Auction {

	private int id;
	private String name;
	private long serialNumber;
	private String endDate;
	private int auctionTypeId;
	private int categoryId;
	private int subCategoryId;
	private String imageName;
	private String imageData;
	private String videoName;
	private String videoData;
	private String description;
	private int subjectQuantity;
	private long subjectPrice;
	
	public Auction(){
		
	}
	
	public Auction(int id, String name, long serialNumber,
			String endDate, int auctionTypeId, int categoryId, int subCategoryId,
			String imageName, String imageData, String videoName, String videoData,
			String description, int subjectQuantity, long subjectPrice) {
		super();
		this.id = id;
		this.name = name;
		this.serialNumber = serialNumber;
		this.endDate = endDate;
		this.auctionTypeId = auctionTypeId;
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
		this.imageName = imageName;
		this.imageData = imageData;
		this.videoName = videoName;
		this.videoData = videoData;
		this.description = description;
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

	public long getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getAuctionTypeId() {
		return auctionTypeId;
	}

	public void setAuctionTypeId(int auctionTypeId) {
		this.auctionTypeId = auctionTypeId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSubjectQuantity() {
		return subjectQuantity;
	}

	public void setSubjectQuantity(int subjectQuantity) {
		this.subjectQuantity = subjectQuantity;
	}

	public long getSubjectPrice() {
		return subjectPrice;
	}

	public void setSubjectPrice(long subjectPrice) {
		this.subjectPrice = subjectPrice;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoData() {
		return videoData;
	}

	public void setVideoData(String videoData) {
		this.videoData = videoData;
	}
	
	//private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	
}
