package com.auctions.system.module.profile.dao;

import com.auctions.system.module.profile.model.UserProfile;

public interface ProfileDAO {
	
	public UserProfile getUserProfile(long userId);
	
}
