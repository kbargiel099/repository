package com.auctions.system.portlet.users_management.dao;

import java.util.List;

import com.auctions.system.portlet.users_management.model.AuctionDatatable;
import com.auctions.system.portlet.users_management.model.User;

public interface UsersManagementDAO {

	public List<User> getUsers();
	
	public User getUserById(int userId);
	
	public boolean createUser(User user,boolean isAdmin);
	
	public List<AuctionDatatable> getAuctions();
	
	public boolean deleteAuction(long auctionId) ;
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(int userId);
	
}
