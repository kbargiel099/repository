package com.auctions.system.portlet.nav_menu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.portlet.category.model.Category;
import com.auctions.system.portlet.nav_menu.dao.NavDAO;
import com.auctions.system.portlet.nav_menu.model.MessageAndDate;
import com.auctions.system.portlet.nav_menu.model.UserData;
import com.auctions.system.portlet.nav_menu.service.NavService;

@Service("navService")
public class NavServiceImpl implements NavService{

	@Autowired
	NavDAO dataSource;

	@Override
	public List<UserData> getSenderIdsToNotify(long receiverId) {
		return dataSource.getSenderIdsToNotify(receiverId);
	}
	
	@Override
	public List<MessageAndDate> getUnreadMessagesFromUser(long senderId, long receiverId){
		return dataSource.getUnreadMessagesFromUser(senderId, receiverId);
	}
	
	@Override
	public boolean markMessagesAsRead(long senderId, long receiverId){
		return dataSource.markMessagesAsRead(senderId, receiverId);
	}

	@Override
	public List<Category> getCategories() {
		return dataSource.getCategories();
	}
}
