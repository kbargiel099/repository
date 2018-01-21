package com.auctions.system.portlet.user_registration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.portlet.user_registration.dao.RegistrationDAO;
import com.auctions.system.portlet.user_registration.service.RegistrationService;
import com.auctions.system.portlet.users_management.model.User;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	RegistrationDAO dataSource;
	
	@Override
	public boolean createUser(User user,boolean isAdmin){
		return dataSource.createUser(user,isAdmin);
	}

	@Override
	public boolean checkIfEmailExist(String email) {
		return dataSource.checkIfEmailExist(email);
	}

	@Override
	public boolean checkIfLoginExist(String login) {
		return dataSource.checkIfLoginExist(login);
	}

}
