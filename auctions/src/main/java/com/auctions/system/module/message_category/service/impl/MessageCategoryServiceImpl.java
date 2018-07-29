package com.auctions.system.module.message_category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.module.message_category.dao.MessageCategoryDAO;
import com.auctions.system.module.message_category.model.MessageCategory;
import com.auctions.system.module.message_category.service.MessageCategoryService;

@Service("messageCategoryService")
public class MessageCategoryServiceImpl implements MessageCategoryService{

	@Autowired
	MessageCategoryDAO dataSource;
	
	@Override
	public List<MessageCategory> getMessageCategories() {
		return dataSource.getMessageCategories();
	}
	
	@Override
	public boolean insert(String name, long userId){
		return dataSource.insert(name, userId);
	}

}
