package com.auctions.system.portlet.users_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.portlet.users_management.dao.UsersManagementDAO;
import com.auctions.system.portlet.users_management.model.User;
import com.auctions.system.portlet.users_management.service.UsersManagementService;

@Service("UsersManagementService")
public class UsersManagementServiceImpl implements UsersManagementService{

	@Autowired
	UsersManagementDAO dataSource;
	
	@Override
	public List<User> getUsers() {
		return dataSource.getUser();
	}
	@Override
	public User getUserById(int userId){
		return dataSource.getUserById(userId);
	}
	@Override
	public boolean addUser(User user){
		return dataSource.addUser(user);
	}

}
