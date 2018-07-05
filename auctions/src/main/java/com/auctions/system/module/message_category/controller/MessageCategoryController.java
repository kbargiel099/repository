package com.auctions.system.module.message_category.controller;

import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

public interface MessageCategoryController {
	
	@RenderMapping(params = "page=getMessageCategories")
	public ModelAndView getMessageCategoriesView(RenderRequest request, RenderResponse response);
	
	@ResourceMapping("insert")
	public void insertAction(ResourceRequest request, ResourceResponse response,
			@RequestParam("messageCategory") String form, @RequestParam("type") String type) throws IOException;
	
}
