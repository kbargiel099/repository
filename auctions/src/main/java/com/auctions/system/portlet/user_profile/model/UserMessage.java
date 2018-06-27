package com.auctions.system.portlet.user_profile.model;

import java.util.Date;

import com.auctions.system.portlet.nav_menu.model.MessageAndDate;

public class UserMessage extends MessageAndDate{

	private long senderId;
	
	public UserMessage(long senderId, String message, Date createDate) {
		super(message, createDate);
		this.senderId = senderId;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}
	
}
