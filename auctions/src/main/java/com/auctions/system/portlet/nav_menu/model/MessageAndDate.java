package com.auctions.system.portlet.nav_menu.model;

import java.util.Date;

public class MessageAndDate{
	
	private String message;
	private String createDate;
	
	public MessageAndDate(String message, String createDate) {
		super();
		this.message = message;
		this.createDate = createDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
