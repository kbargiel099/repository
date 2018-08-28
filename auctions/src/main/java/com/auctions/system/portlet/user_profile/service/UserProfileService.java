package com.auctions.system.portlet.user_profile.service;

import java.text.ParseException;
import java.util.List;

import javax.portlet.ResourceRequest;

import com.auctions.system.portlet.category.model.Category;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.message_category.model.MessageCategory;
import com.auctions.system.portlet.messages.model.Message;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionForGrade;
import com.auctions.system.portlet.user_profile.model.AuctionGrade;
import com.auctions.system.portlet.user_profile.model.AuctionImages;
import com.auctions.system.portlet.user_profile.model.AuctionType;
import com.auctions.system.portlet.user_profile.model.TechnicalData;
import com.auctions.system.portlet.user_profile.model.UserMessage;
import com.auctions.system.portlet.user_profile.model.UserPassword;
import com.auctions.system.portlet.user_profile.model.UserProfileData;
import com.auctions.system.portlet.user_profile.model.UsernameAndId;

public interface UserProfileService {
	
	public UserProfileData getUserSimpleData(final long id);
	
	public List<AuctionPresenter> getUserBoughtSubjects(long userId);
	
	public List<AuctionPresenter> getUserSoldSubjects(long userId);
	
	public List<AuctionPresenter> getUserAuctions(long userId);
	
	public List<AuctionPresenter> getUserObservation(long userId);
	
	public List<Category> getCategories();
	
	public List<SubCategory> getSubCategories();
	
	public boolean createUserAuction(long userId, Auction a) throws ParseException;
	
	public boolean addAuctionGrade(long userId, AuctionGrade a);
	
	public List<AuctionType> getAuctionTypes();
	
	public List<TechnicalData> getTechnicalData(int id);
	
	public Auction getAuctionData(final long id);
	
	public boolean updateAuction(Auction a);
	
	public AuctionImages getAuctionImages(final long id);
	
	public boolean updateAuctionImages(String images, long id);
	
	public boolean deleteVideo(long id);
	
	public boolean makePaid(long id, int paymentMethodId);
	
	public List<UsernameAndId> getUsersIdsForLastConversations(final long id);
	
	public List<UserMessage> getAllMessagesFromUser(long userId, long interlocutorId);
	
	public boolean changePassword(ResourceRequest request, UserPassword p);
	
	public List<MessageCategory> getMessageCategories();
	
	public List<Message> getMessages();
	
	public List<AuctionForGrade> getAuctionsForGrade(long userId);

}
