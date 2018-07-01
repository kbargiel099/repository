package com.auctions.system.module.message_category.controller;

import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.HttpUtil;
import com.auctions.system.module.message_category.model.MessageCategory;
import com.auctions.system.module.message_category.service.MessageCategoryService;
import com.google.gson.Gson;

@Controller
@RequestMapping("VIEW")
public class MessageCategoryControllerImpl implements MessageCategoryController{

	final String messageCategoryView = "message-category-view";
	
	@Autowired
	private MessageCategoryService service;
	
	@RenderMapping(params = "page=getMessageCategories")
	public ModelAndView getMessageCategoriesView(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(messageCategoryView);
		return model; 
	}
	
	@ResourceMapping("insert")
	public void insertAction(ResourceRequest request, ResourceResponse response,
			@RequestParam("messageCategory") String form, @RequestParam("type") String type) throws IOException{		
		MessageCategory messageCategory = new Gson().fromJson(form, MessageCategory.class);
		
		HttpUtil.createResponse(response).
			set("success", service.insert(messageCategory)).
			prepare();
	}

}