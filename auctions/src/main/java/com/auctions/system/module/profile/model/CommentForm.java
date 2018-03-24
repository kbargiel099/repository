package com.auctions.system.module.profile.model;

public class CommentForm {

	private long userId;
	private String commentText;
	
	public CommentForm(long userId, String commentText) {
		super();
		this.userId = userId;
		this.commentText = commentText;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getCommentText() {
		return commentText;
	}
	
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
}
