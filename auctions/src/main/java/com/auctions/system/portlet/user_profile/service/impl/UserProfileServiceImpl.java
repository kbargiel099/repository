package com.auctions.system.portlet.user_profile.service.impl;

import java.text.ParseException;
import java.util.List;

import javax.portlet.ResourceRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.auctions.system.portlet.category.model.Category;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.message_category.model.MessageCategory;
import com.auctions.system.portlet.messages.model.Message;
import com.auctions.system.portlet.user_profile.dao.UserProfileDAO;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionForGrade;
import com.auctions.system.portlet.user_profile.model.AuctionGrade;
import com.auctions.system.portlet.user_profile.model.AuctionImages;
import com.auctions.system.portlet.user_profile.model.AuctionType;
import com.auctions.system.portlet.user_profile.model.ChangePasswordResponse;
import com.auctions.system.portlet.user_profile.model.TechnicalData;
import com.auctions.system.portlet.user_profile.model.UserMessage;
import com.auctions.system.portlet.user_profile.model.UserPassword;
import com.auctions.system.portlet.user_profile.model.UserProfileAddress;
import com.auctions.system.portlet.user_profile.model.UserProfileData;
import com.auctions.system.portlet.user_profile.model.UserProfileDetails;
import com.auctions.system.portlet.user_profile.model.UsernameAndId;
import com.auctions.system.portlet.user_profile.service.UserProfileService;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.AddressLocalServiceUtil;
import com.liferay.portal.kernel.service.PhoneLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.AddressUtil;
import com.liferay.portal.kernel.service.persistence.PhoneUtil;
import com.liferay.portal.kernel.util.PortalUtil;

@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService{

	@Autowired
	UserProfileDAO dataSource;
	
	@Override
	public UserProfileData getUserSimpleData(final long id){
		try{	
			return dataSource.getUserSimpleData(id);
			
		} catch (EmptyResultDataAccessException e) {
			return new UserProfileData();
		}
	}
	
	@Override
	public List<AuctionPresenter> getUserBoughtSubjects(long userId){
		return dataSource.getUserBoughtSubjects(userId);
	}
	
	@Override
	public List<AuctionPresenter> getUserSoldSubjects(long userId){
		return dataSource.getUserSoldSubjects(userId);
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
	public ChangePasswordResponse changePassword(ResourceRequest request, UserPassword p) {
		ChangePasswordResponse response = new ChangePasswordResponse();
		
		try{
			 long userId = PortalUtil.getUserId(request);
			 UserLocalServiceUtil.updatePassword(userId, p.getPassword(), p.getRepeatedPassword(), false); 
		
			 response.setSuccess(true);
			 return response;
			
		} catch(Exception e){
			 e.printStackTrace();
			 
			 response.setError(e.getClass().getCanonicalName());
			 response.setSuccess(false);
			 return response;
		} 
	}
	
	@Override
	public List<MessageCategory> getMessageCategories(){
		return dataSource.getMessageCategories();
	}
	
	@Override
	public List<Message> getMessages(){
		return dataSource.getMessages();
	}
	
	@Override
	public List<AuctionForGrade> getAuctionsForGrade(long userId){
		return dataSource.getAuctionsForGrade(userId);
	}

	@Override
	public boolean updateUserDetails(ResourceRequest request, UserProfileDetails userDetails) {
		try {
			User user = PortalUtil.getUser(request);
			user.setFirstName(userDetails.getFirstname());
			user.setLastName(userDetails.getLastname());
		
			Phone newPhone = PhoneUtil.create(CounterLocalServiceUtil.increment(Phone.class.getName()));
			newPhone.setNumber(userDetails.getPhoneNumber());
			newPhone.setUserId(user.getUserId());
			newPhone.setPrimary(true);
			
			Address address = AddressUtil.create(CounterLocalServiceUtil.increment(Address.class.getName()));
			address.setCity(userDetails.getCity());
			address.setStreet1(userDetails.getStreet());
			address.setStreet2(userDetails.getHouseNumber());
			address.setZip(userDetails.getZipCode());
			address.setUserId(user.getUserId());
			address.setPrimary(true);
			
			PhoneLocalServiceUtil.addPhone(newPhone);
			AddressLocalServiceUtil.addAddress(address);
			UserLocalServiceUtil.updateUser(user);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public UserProfileDetails getUserDataForSettings(long id) {
		try {
			return dataSource.getUserDataForSettings(id);
		} catch (Exception e) {
			return new UserProfileDetails();
		}
	}

	@Override
	public UserProfileAddress getUserAddressForSettings(long id) {
		try {
		return dataSource.getUserAddressForSettings(id);
		} catch (Exception e) {
			return new UserProfileAddress();
		}
	}

}
