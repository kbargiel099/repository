package com.auctions.system.module.message_category.controller;

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
	
	@RenderMapping(params = "page=getMessageCategories")
	public ModelAndView getMessageCategoriesView(RenderRequest request, RenderResponse response);
	
	@RequestMapping(params = "page=add")
	public ModelAndView getCreateMessageCategoryView(RenderRequest request, RenderResponse response);
	
	@RequestMapping(params = "page=edit")
	public ModelAndView getEditMessageCategoryView(RenderRequest request, RenderResponse response);
	
	@ResourceMapping("getMessageCategories")
	public void getMessageCategories(ResourceRequest request, ResourceResponse response);
	
	@ResourceMapping("insert")
	public void insertAction(ResourceRequest request, ResourceResponse response,
			@RequestParam("messageCategory") String messageCategory, @RequestParam("type") String type);
	
}
