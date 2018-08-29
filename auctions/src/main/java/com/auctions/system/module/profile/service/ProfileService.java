package com.auctions.system.module.profile.service;

import java.util.List;

import com.auctions.system.module.profile.model.Grade;
import com.auctions.system.module.profile.model.UserProfile;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;

public interface ProfileService {
	
	public UserProfile getUserProfile(long userId);
	
	public List<Grade> getUserGrades(long userId);
	
	public List<AuctionPresenter> getUserAuctions(long userId);
	
}
