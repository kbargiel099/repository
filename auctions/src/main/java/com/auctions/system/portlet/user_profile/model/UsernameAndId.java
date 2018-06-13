package com.auctions.system.portlet.user_profile.model;

import com.auctions.system.module.UserUtil;

public class UsernameAndId {
	
	private long id;
	private String username;
	
	public UsernameAndId(long id) {
		super();
		this.id = id;
		this.username = UserUtil.getScreenName(id);
	}
	
	public UsernameAndId(long id, String username) {
		super();
		this.id = id;
		this.username = username;
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

}
