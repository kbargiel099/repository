package com.auctions.system.portlet.user_profile.model;

public class UserProfileDetails {
	
	private String firstname;
	private String lastname;
	private String phoneNumber;
	
	public UserProfileDetails(){}

	public UserProfileDetails(String firstname, String lastname, String phoneNumber) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	};
}
