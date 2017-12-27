package com.auctions.system.portlet.users_management.model;

import java.util.List;

import com.auctions.system.module.Option;

public class UserOptions extends User{

	private List<Option> options;
	
	public UserOptions(User user,List<Option> options) {
		super(user);
		this.options = options;
	}
	
	public UserOptions(int id, String login, String password,List<Option> options) {
		super(id, login, password);
		this.options = options;
	}

}
