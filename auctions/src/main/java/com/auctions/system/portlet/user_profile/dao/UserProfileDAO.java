package com.auctions.system.portlet.user_profile.dao;

import java.util.List;

import com.auctions.system.portlet.user_profile.model.UserProfileAuction;

public interface UserProfileDAO {

	public boolean isUserExist(String login, String password);
	
	public void getImages(long userId);
	
	public List<UserProfileAuction> getUserBoughtSubjects(long userId);
}
