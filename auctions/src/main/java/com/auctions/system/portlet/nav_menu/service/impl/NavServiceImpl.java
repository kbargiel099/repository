package com.auctions.system.portlet.nav_menu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.portlet.nav_menu.dao.NavDAO;
import com.auctions.system.portlet.nav_menu.service.NavService;

@Service("navService")
public class NavServiceImpl implements NavService{

	@Autowired
	NavDAO dataSource;
	
	@Override
	public boolean isUserExist(String login, String password) {
		return dataSource.isUserExist(login, password);
	}

}
