package com.auctions.system.portlet.message_category.service;

import java.util.List;

import com.auctions.system.portlet.message_category.model.MessageCategory;

public interface MessageCategoryService{
	
	public List<MessageCategory> getMessageCategories();
	
	public MessageCategory getMessageCategory(int id);
	
	public boolean insert(String name, long userId);
	
	public boolean edit(String name, int id);
	
	public boolean changeStatus(int id, boolean activated);
	
	public boolean delete(int id);

}
