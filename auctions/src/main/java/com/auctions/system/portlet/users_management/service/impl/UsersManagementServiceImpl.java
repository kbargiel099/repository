package com.auctions.system.portlet.users_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.portlet.users_management.dao.UsersManagementDAO;
import com.auctions.system.portlet.users_management.model.AuctionDatatable;
import com.auctions.system.portlet.users_management.model.User;
import com.auctions.system.portlet.users_management.service.UsersManagementService;

@Service("UsersManagementService")
public class UsersManagementServiceImpl implements UsersManagementService{

	@Autowired
	UsersManagementDAO dataSource;
	
	@Override
	public List<User> getUsers() {
		return dataSource.getUsers();
	}
	@Override
	public User getUserById(int userId){
		return dataSource.getUserById(userId);
	}
	@Override
	public boolean createUser(User user,boolean isAdmin){
		return dataSource.createUser(user,isAdmin);
	}
	@Override
	public List<AuctionDatatable> getAuctions(){
		return dataSource.getAuctions();
	}
	@Override
	public boolean deleteAuction(long auctionId){
		return dataSource.deleteAuction(auctionId);
	}
	@Override
	public boolean updateUser(User user){
		return dataSource.updateUser(user);
	}
	@Override
	public boolean deleteUser(int userId) {
		return dataSource.deleteUser(userId);
	}

}
