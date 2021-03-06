package com.auctions.system.portlet.category.model;

import java.sql.Array;
import java.sql.SQLException;

public class AuctionDetails {

	private long id;
	private long userId;
	private String serialNumber;
	private String name;
	private String description;
	private String statusName;
	private String createDate;
	private String endDate;	
	private String[] images;
	private String typeName;
	private String video;
	private int subjectQuantity;
	private long subjectPrice;
	private long minimalPrice;
	private String technicalParameters;
	
	public AuctionDetails(long id, long userId, String serialNumber, String name, String description, String statusName, String createDate, String endDate, 
			Array images, String typeName, String video, int subjectQuantity, long subjectPrice, long minimalPrice, String technicalParameters) throws SQLException {
		super();
		this.id = id;
		this.userId = userId;
		this.serialNumber = serialNumber;
		this.name = name;
		this.description = description;
		this.statusName = statusName;
		this.createDate = createDate;
		this.endDate = endDate;
		this.images = (String[]) images.getArray();
		this.typeName = typeName;
		this.video = video;
		this.subjectQuantity = subjectQuantity;
		this.subjectPrice = subjectPrice;
		this.minimalPrice = minimalPrice;
		this.technicalParameters = technicalParameters;
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
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
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

	public String getVideo() {
		return video;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public long getMinimalPrice() {
		return minimalPrice;
	}

	public void setMinimalPrice(long minimalPrice) {
		this.minimalPrice = minimalPrice;
	}

	public String getTechnicalParameters() {
		return technicalParameters;
	}

	public void setTechnicalParameters(String technicalParameters) {
		this.technicalParameters = technicalParameters;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
