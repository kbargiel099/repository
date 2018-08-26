package com.auctions.system.portlet.message_category.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

public interface MessageCategoryController {
	
	@RenderMapping()
	public ModelAndView getMessageCategoriesView(RenderRequest request, RenderResponse response);
	
	@RequestMapping(params = "page=add")
	public ModelAndView getCreateMessageCategoryView(RenderRequest request, RenderResponse response);
	
	@RequestMapping(params = "page=edit")
	public ModelAndView getEditMessageCategoryView(RenderRequest request, RenderResponse response,
			@RequestParam("id") int id);
	
	@RenderMapping(params = "page=messages")
	public ModelAndView messagesView(RenderRequest request, RenderResponse response,
			@RequestParam("id") int id);
	
	@ResourceMapping("getMessageCategories")
	public void getMessageCategories(ResourceRequest request, ResourceResponse response);
	
	@ResourceMapping("insert")
	public void insertAction(ResourceRequest request, ResourceResponse response,
			@RequestParam("messageCategory") String messageCategory);
	
	@ResourceMapping("edit")
	public void editAction(ResourceRequest request, ResourceResponse response, 
			@RequestParam("messageCategory") String messageCategory, @RequestParam("id") int id);
	
	@ResourceMapping(value="activate")
	public void activate(ResourceRequest request, ResourceResponse response,
			@RequestParam("id") int id) throws Exception;
	
	@ResourceMapping(value="suspend")
	public void suspend(ResourceRequest request, ResourceResponse response,
			@RequestParam("id") int id) throws Exception;
	
	@ResourceMapping(value="delete")
	public void delete(ResourceRequest request, ResourceResponse response,
			@RequestParam("id") int id) throws Exception;
	
}
