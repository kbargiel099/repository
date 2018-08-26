package com.auctions.system.portlet.messages.service;

import java.util.List;

import com.auctions.system.portlet.message_category.model.MessageCategory;
import com.auctions.system.portlet.messages.model.Message;

public interface MessagesService{
	
	public List<Message> getMessages();
	
	public Message getMessage(int id);
	
	public List<MessageCategory> getMessageCategories();
	
	public boolean insert(Message message, long userId);
	
	public boolean edit(Message message, long userId);

}
