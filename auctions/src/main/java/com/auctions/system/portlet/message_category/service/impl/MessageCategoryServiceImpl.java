package com.auctions.system.portlet.message_category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.portlet.message_category.dao.MessageCategoryDAO;
import com.auctions.system.portlet.message_category.model.MessageCategory;
import com.auctions.system.portlet.message_category.service.MessageCategoryService;

@Service("messageCategoryService")
public class MessageCategoryServiceImpl implements MessageCategoryService{

	@Autowired
	MessageCategoryDAO dataSource;
	
	@Override
	public List<MessageCategory> getMessageCategories() {
		return dataSource.getMessageCategories();
	}
	
	@Override
	public MessageCategory getMessageCategory(int id){
		return dataSource.getMessageCategory(id);
	}
	
	@Override
	public boolean insert(String name, long userId){
		return dataSource.insert(name, userId);
	}
	
	@Override
	public boolean edit(String name, int id){
		return dataSource.edit(name, id);
	}

	@Override
	public boolean changeStatus(int id, boolean activated) {
		return dataSource.changeStatus(id, activated);
	}

	@Override
	public boolean delete(int id) {
		return dataSource.delete(id);
	}

}
