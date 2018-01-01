package com.auctions.system.portlet.user_registration.service;

import com.auctions.system.portlet.users_management.model.User;

public interface RegistrationService {

	public boolean createUser(User user,boolean isAdmin);
	
	//public boolean isUserExist();

}
