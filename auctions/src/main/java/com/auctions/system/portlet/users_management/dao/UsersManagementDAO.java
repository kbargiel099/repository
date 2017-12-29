package com.auctions.system.portlet.users_management.dao;

import java.util.List;

import com.auctions.system.portlet.users_management.model.User;

public interface UsersManagementDAO {

	public List<User> getUser();
	
	public User getUserById(int userId);
	
	public boolean addUser(User user);
}
