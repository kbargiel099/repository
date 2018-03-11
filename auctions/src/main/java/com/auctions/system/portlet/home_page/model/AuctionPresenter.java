package com.auctions.system.portlet.home_page.model;

import java.io.Serializable;

public class AuctionPresenter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String imageName;
	private String imageData;
	private long subjectPrice;
	
	public AuctionPresenter(int id, String name,
			String imageName,String imageData,long subjectPrice) {
		super();
		this.id = id;
		this.name = name;
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

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}
	
}
