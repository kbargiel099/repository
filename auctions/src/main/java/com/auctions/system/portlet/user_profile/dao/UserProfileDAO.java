package com.auctions.system.portlet.user_profile.dao;

import com.auctions.system.portlet.users_management.model.User;

public interface UserProfileDAO {

	public boolean isUserExist(String login, String password);
}
