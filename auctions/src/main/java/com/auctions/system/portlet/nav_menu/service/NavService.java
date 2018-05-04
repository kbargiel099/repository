package com.auctions.system.portlet.nav_menu.service;

import java.util.List;

import com.auctions.system.portlet.nav_menu.model.MessageAndDate;
import com.auctions.system.portlet.nav_menu.model.UserData;

public interface NavService {
	
	public List<UserData> getSenderIdsToNotify(long receiverId);
	
	public List<MessageAndDate> getUnreadMessagesFromUser(long senderId, long receiverId);
	
	public boolean markMessagesAsRead(long senderId, long receiverId);

}
