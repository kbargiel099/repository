package com.auctions.system.portlet.users_management.service;

import java.util.List;

import com.auctions.system.portlet.users_management.model.User;

public interface UsersManagementService {

	public List<User> getUsers();
	
	public User getUserById(int userId);
	
	public boolean createUser(User user);
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(int userId);
}
