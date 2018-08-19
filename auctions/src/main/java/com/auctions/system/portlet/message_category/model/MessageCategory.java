package com.auctions.system.portlet.message_category.model;

public class MessageCategory {
	
	private int id;
	private String name;
	private long userId;
	private String createDate;
	private boolean isActivated;
	
	public MessageCategory(int id, String name, String createDate, long userId, boolean isActivated) {
		super();
		this.id = id;
		this.name = name;
		this.createDate = createDate;
		this.userId = userId;
		this.isActivated = isActivated;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}
	
}
