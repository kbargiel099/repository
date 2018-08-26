package com.auctions.system.portlet.messages.model;

public class Message {

	private int id;
	private int messageCategoryId;
	private String title;
	private String text;
	private String createDate;
	private String editDate;
	private long userId;
	private boolean isSent;
	
	public Message(int id, int messageCategoryId, String title, String text, String createDate, String editDate, long userId, boolean isSent) {
		super();
		this.id = id;
		this.messageCategoryId = messageCategoryId;
		this.title = title;
		this.text = text;
		this.createDate = createDate;
		this.editDate = editDate;
		this.userId = userId;
		this.isSent = isSent;
	}

	public Message(){
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMessageCategoryId() {
		return messageCategoryId;
	}

	public void setMessageCategoryId(int messageCategoryId) {
		this.messageCategoryId = messageCategoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEditDate() {
		return editDate;
	}

	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean isSent() {
		return isSent;
	}

	public void setSent(boolean isSent) {
		this.isSent = isSent;
	}
	
}
