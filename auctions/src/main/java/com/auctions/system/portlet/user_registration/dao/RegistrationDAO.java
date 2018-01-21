package com.auctions.system.portlet.user_registration.dao;

import com.auctions.system.portlet.users_management.model.User;

public interface RegistrationDAO {

	public boolean createUser(User user,boolean isAdmin);
	
	public boolean checkIfEmailExist(String email);
	
	public boolean checkIfLoginExist(String login);

}
