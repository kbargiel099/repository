package com.auctions.system.portlet.nav_menu.model;

import com.auctions.system.module.UserUtil;

public class UserData {

	private long senderId;
	private String screenName = "";
	
	public UserData(long senderId) {
		this.senderId = senderId;
		this.screenName = UserUtil.getScreenName(senderId);
	}
	
	public UserData(long senderId, String screenName) {
		this.senderId = senderId;
		this.screenName = screenName;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
}
