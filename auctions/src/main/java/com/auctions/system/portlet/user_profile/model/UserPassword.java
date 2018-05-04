package com.auctions.system.portlet.user_profile.model;

public class UserPassword {
	
	private String password;
	private String repeatedPassword;
	
	public UserPassword(){};
	
	public UserPassword(String password, String repeatedPassword) {
		super();
		this.password = password;
		this.repeatedPassword = repeatedPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}
	
}
