package com.auctions.system.module.profile.service;

import com.auctions.system.module.profile.model.UserProfile;
import com.auctions.system.portlet.category.model.AuctionDetails;

public interface ProfileService {
	
	public UserProfile getUserProfile(long userId);
	
}
