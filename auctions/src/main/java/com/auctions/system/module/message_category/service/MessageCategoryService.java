package com.auctions.system.module.message_category.service;

import java.util.List;

import com.auctions.system.module.message_category.model.MessageCategory;

public interface MessageCategoryService{
	
	public List<MessageCategory> getMessageCategories();
	
	public boolean insert(String name, long userId);

}
