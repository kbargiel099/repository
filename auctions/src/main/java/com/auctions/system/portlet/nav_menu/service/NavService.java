package com.auctions.system.portlet.nav_menu.service;

import com.auctions.system.portlet.users_management.model.User;

public interface NavService {

	public boolean isUserExist(String login, String password);
	
	public User getUser(String login);
}
