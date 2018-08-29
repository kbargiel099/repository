package com.auctions.system.module.profile.model;

public class Grade {

	private String screenname;
	private int grade;
	private String comment;
	private String createDate;
	
	public Grade(String screenname, int grade, String comment, String createDate) {
		super();
		this.screenname = screenname;
		this.grade = grade;
		this.comment = comment;
		this.createDate = createDate;
	}

	public String getScreenname() {
		return screenname;
	}

	public void setScreenname(String screenname) {
		this.screenname = screenname;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
