package com.auctions.system.portlet.message_category.dao;

import java.util.List;

import com.auctions.system.portlet.message_category.model.MessageCategory;

public interface MessageCategoryDAO {
	
	public List<MessageCategory> getMessageCategories();
	
	public boolean insert(String name, long userId);

}
