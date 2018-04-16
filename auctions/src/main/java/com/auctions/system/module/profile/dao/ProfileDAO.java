package com.auctions.system.module.profile.dao;

import com.auctions.system.module.profile.model.UserProfile;
import com.auctions.system.portlet.category.model.AuctionDetails;

public interface ProfileDAO {
	
	public UserProfile getUserProfile(long userId);
	
}
