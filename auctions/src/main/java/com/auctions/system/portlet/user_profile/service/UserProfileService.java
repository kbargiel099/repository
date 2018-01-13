package com.auctions.system.portlet.user_profile.service;

import java.util.List;

import com.auctions.system.portlet.user_profile.model.UserProfileAuction;

public interface UserProfileService {

	public boolean isUserExist(String login, String password);
	
	public void getImages(long userId);
	
	public List<UserProfileAuction> getUserBoughtSubjects(long userId);

}
