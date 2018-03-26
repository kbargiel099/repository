package com.auctions.system.portlet.user_profile.model;

public class AuctionGrade {
	
	private int grade;
	private String comment;
	private long auctionId;
	
	public AuctionGrade(int grade, String comment, long auctionId) {
		super();
		this.grade = grade;
		this.comment = comment;
		this.auctionId = auctionId;
	}

	public AuctionGrade() {
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(long auctionId) {
		this.auctionId = auctionId;
	}

}
