package com.auctions.system.portlet.nav_menu.model;

public class UserViewModel {

	private int id;
	private String login;
	
	public UserViewModel(int id, String login) {
		super();
		this.id = id;
		this.login = login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	
}
