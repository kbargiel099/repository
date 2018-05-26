package com.auctions.system.portlet.user_profile.model;

public class UserProfileData {

	private long id;
	private String firstName;
	private String lastName;
	private String screenName;
	private String createDate;
	private String modifiedDate;
	private String emailAddress;
	private String lastLoginDate;
	private boolean lockout;
	
	public UserProfileData(long id, String firstName, String lastName, String screenName, String createDate,
			String modifiedDate, String emailAddress, String lastLoginDate, boolean lockout) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.screenName = screenName;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.emailAddress = emailAddress;
		this.lastLoginDate = lastLoginDate;
		this.lockout = lockout;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public boolean isLockout() {
		return lockout;
	}

	public void setLockout(boolean lockout) {
		this.lockout = lockout;
	}

}
