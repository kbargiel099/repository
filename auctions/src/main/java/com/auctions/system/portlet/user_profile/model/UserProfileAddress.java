package com.auctions.system.portlet.user_profile.model;

public class UserProfileAddress {

	private String city;
	private String street;
	private String houseNumber;
	private String zipCode;
	
	public UserProfileAddress(String city, String street, String houseNumber, String zipCode) {
		super();
		this.city = city;
		this.street = street;
		this.houseNumber = houseNumber;
		this.zipCode = zipCode;
	}
	
	public UserProfileAddress() { }

	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
}
