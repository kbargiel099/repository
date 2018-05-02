package com.auctions.system.portlet.nav_menu.model;

import java.util.Date;

public class MessageAndDate{
	
	private String message;
	private Date createDate;
	
	public MessageAndDate(String message, Date createDate) {
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
