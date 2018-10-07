package com.auctions.system.portlet.messages.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auctions.system.portlet.message_category.model.MessageCategory;
import com.auctions.system.portlet.messages.dao.MessagesDAO;
import com.auctions.system.portlet.messages.model.Message;
import com.auctions.system.portlet.messages.service.MessagesService;

@Service("messagesService")
public class MessagesServiceImpl implements MessagesService{

	@Autowired
	MessagesDAO dataSource;

	@Override
	public List<Message> getMessages() {
		return dataSource.getMessages();
	}
	
	@Override
	public Message getMessage(int id){
		return dataSource.getMessage(id);
	}
	
	@Override
	public List<MessageCategory> getMessageCategories(){
		return dataSource.getMessageCategories();
	}

	@Override
	public boolean insert(Message message, long userId) {
		return dataSource.insert(message, userId);
	}

	@Override
	public boolean edit(Message message, long userId) {
		return dataSource.edit(message, userId);
	}

	@Override
	public boolean delete(int id) {
		return dataSource.delete(id);
	}
}
