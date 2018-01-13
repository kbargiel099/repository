package com.auctions.system.portlet.user_profile.model;

public class UserProfileAuction {

	private String name;
	private String subjectName;
	private String imageName;
	private String imageExt;
	
	public UserProfileAuction(String name, String subjectName, String imageName,String imageExt) {
		super();
		this.name = name;
		this.subjectName = subjectName;
		this.imageName = imageName;
		this.imageExt = imageExt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public String getImageExt() {
		return imageExt;
	}
	
	public void setImageExt(String imageExt) {
		this.imageExt = imageExt;
	}
	
}
