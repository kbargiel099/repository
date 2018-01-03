package com.auctions.system.portlet.nav_menu.dao;

import com.auctions.system.portlet.users_management.model.User;

public interface NavDAO {

	public boolean isUserExist(String login, String password);

	public User getUser(String login);
}
