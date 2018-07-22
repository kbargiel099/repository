package com.auctions.system.portlet.user_profile.service.impl;

import java.text.ParseException;
import java.util.List;

import javax.portlet.ResourceRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.portlet.category.model.Category;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.user_profile.dao.UserProfileDAO;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionGrade;
import com.auctions.system.portlet.user_profile.model.AuctionImages;
import com.auctions.system.portlet.user_profile.model.AuctionType;
import com.auctions.system.portlet.user_profile.model.TechnicalData;
import com.auctions.system.portlet.user_profile.model.UserMessage;
import com.auctions.system.portlet.user_profile.model.UserPassword;
import com.auctions.system.portlet.user_profile.model.UserProfileData;
import com.auctions.system.portlet.user_profile.model.UsernameAndId;
import com.auctions.system.portlet.user_profile.service.UserProfileService;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;

@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService{

	@Autowired
	UserProfileDAO dataSource;
	
	@Override
	public UserProfileData getUserSimpleData(final long id){
		return dataSource.getUserSimpleData(id);
	}
	
	@Override
	public List<AuctionPresenter> getUserBoughtSubjects(long userId){
		return dataSource.getUserBoughtSubjects(userId);
	}
	
	@Override
	public List<AuctionPresenter> getUserAuctions(long userId){
		return dataSource.getUserAuctions(userId);
	}
	
	@Override
	public List<AuctionPresenter> getUserObservation(long userId){
		return dataSource.getUserObservation(userId);
	}
	
	@Override
	public List<Category> getCategories(){
		return dataSource.getCategories();
	}
	
	@Override
	public List<SubCategory> getSubCategories(){
		return dataSource.getSubCategories();
	}
	
	@Override
	public boolean createUserAuction(long userId, Auction a) throws ParseException{
		return dataSource.createUserAuction(userId, a);
	}
	
	@Override
	public boolean addAuctionGrade(long userId, AuctionGrade a){
		return dataSource.addAuctionGrade(userId, a);
	}
	
	@Override
	public List<AuctionType> getAuctionTypes(){
		return dataSource.getAuctionTypes();
	}
	
	@Override
	public List<TechnicalData> getTechnicalData(int id){
		return dataSource.getTechnicalData(id);
	}
	
	@Override
	public Auction getAuctionData(final long id){
		return dataSource.getAuctionData(id);
	}
	
	@Override
	public boolean updateAuction(Auction a){
		return dataSource.updateAuction(a);
	}
	
	@Override
	public AuctionImages getAuctionImages(final long id){
		return dataSource.getAuctionImages(id);
	}
	
	@Override
	public boolean updateAuctionImages(String images, long id){
		return dataSource.updateAuctionImages(images, id);
	}
	
	@Override
	public boolean deleteVideo(long id){
		return dataSource.deleteVideo(id);
	}
	
	@Override
	public boolean makePaid(long id, int paymentMethodId){
		return dataSource.makePaid(id, paymentMethodId);
	}
	
	@Override
	public List<UsernameAndId> getUsersIdsForLastConversations(final long id){
		return dataSource.getUsersIdsForLastConversations(id);
	}
	
	@Override
	public List<UserMessage> getAllMessagesFromUser(long userId, long interlocutorId){
		return dataSource.getAllMessagesFromUser(userId, interlocutorId);
	}

	@Override
	public boolean changePassword(ResourceRequest request, UserPassword p) {
		try{
			 long userId = PortalUtil.getUserId(request);
			 UserLocalServiceUtil.updatePassword(userId, p.getPassword(), p.getRepeatedPassword(), false); 
		
			 return true;
			
		}catch(Exception e){
			 e.printStackTrace();
			 return false;
		}
	}

}
