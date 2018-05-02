package com.auctions.system.portlet.nav_menu.model;

import java.util.Date;

public class MessageModel {

	protected long senderId;
	protected Date createDate;
	
	public MessageModel(long senderId, Date createDate) {
		super();
		this.senderId = senderId;
		this.createDate = createDate;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
