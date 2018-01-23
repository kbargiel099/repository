package com.auctions.system.portlet.user_profile.model;

public class UserProfileAuction {

	private String name;
	private String imageName;
	
	public UserProfileAuction(String name, String imageName) {
		super();
		this.name = name;
		this.imageName = imageName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

}
