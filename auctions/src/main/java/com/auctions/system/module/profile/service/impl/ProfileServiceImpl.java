package com.auctions.system.module.profile.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.module.profile.dao.ProfileDAO;
import com.auctions.system.module.profile.model.UserProfile;
import com.auctions.system.module.profile.service.ProfileService;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	ProfileDAO dataSource;
	
	public UserProfile getUserProfile(long userId){
		return dataSource.getUserProfile(userId);
	}
	
}
