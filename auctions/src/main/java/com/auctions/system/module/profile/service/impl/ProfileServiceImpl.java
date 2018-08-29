package com.auctions.system.module.profile.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.module.profile.dao.ProfileDAO;
import com.auctions.system.module.profile.model.Grade;
import com.auctions.system.module.profile.model.UserProfile;
import com.auctions.system.module.profile.service.ProfileService;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	ProfileDAO dataSource;
	
	@Override
	public UserProfile getUserProfile(long userId){
		return dataSource.getUserProfile(userId);
	}
	
	@Override
	public List<Grade> getUserGrades(long userId){
		return dataSource.getUserGrades(userId);
	}
	
	@Override
	public List<AuctionPresenter> getUserAuctions(long userId){
		return dataSource.getUserAuctions(userId);
	}
	
}
