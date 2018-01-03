package com.auctions.system.portlet.nav_menu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.portlet.nav_menu.dao.NavDAO;
import com.auctions.system.portlet.nav_menu.model.UserViewModel;
import com.auctions.system.portlet.nav_menu.service.NavService;
import com.auctions.system.portlet.users_management.model.User;

@Service("navService")
public class NavServiceImpl implements NavService{

	@Autowired
	NavDAO dataSource;
	
	@Override
	public boolean isUserExist(String login, String password) {
		return dataSource.isUserExist(login, password);
	}
	@Override
	public User getUser(String login){
		return dataSource.getUser(login);
	}

}
