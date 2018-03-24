package com.auctions.system.module.profile.model;

import java.sql.Timestamp;

public class UserProfile {
	
	private long id;
	private String username;
	private String firstname;
	private String lastname;
	private String emailAddress;
	private Timestamp createDate;
	private Timestamp loginDate;

	public UserProfile(long id, String username, String firstname, String lastname, String emailAddress,
			Timestamp createDate, Timestamp loginDate) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailAddress = emailAddress;
		this.createDate = createDate;
		this.loginDate = loginDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	public Timestamp getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Timestamp loginDate) {
		this.loginDate = loginDate;
	}

}
