package com.auctions.system.portlet.users_management.model;

public class AuctionDatatable {

	private long id;
	private String name;
	private String createDate;
	private String imageName;
	private String status;
	
	public AuctionDatatable(long id, String name, String createDate, String imageName, String status) {
		super();
		this.id = id;
		this.name = name;
		this.createDate = createDate;
		this.imageName = imageName;
		this.status = status;
	}
	
	public AuctionDatatable(AuctionDatatable auction) {
		this.id = auction.getId();
		this.name = auction.getName();
		this.createDate = auction.getCreateDate();
		this.imageName = auction.getImageName();
		this.status = auction.getStatus();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
