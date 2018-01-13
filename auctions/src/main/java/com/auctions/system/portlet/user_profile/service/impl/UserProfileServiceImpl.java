package com.auctions.system.portlet.user_profile.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.portlet.user_profile.dao.UserProfileDAO;
import com.auctions.system.portlet.user_profile.model.UserProfileAuction;
import com.auctions.system.portlet.user_profile.service.UserProfileService;

@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService{

	@Autowired
	UserProfileDAO dataSource;
	
	@Override
	public boolean isUserExist(String login, String password) {
		return dataSource.isUserExist(login, password);
	}

	@Override
	public void getImages(long userId) {
		dataSource.getImages(userId);
	}
	
	@Override
	public List<UserProfileAuction> getUserBoughtSubjects(long userId){
		return dataSource.getUserBoughtSubjects(userId);
	}

}
