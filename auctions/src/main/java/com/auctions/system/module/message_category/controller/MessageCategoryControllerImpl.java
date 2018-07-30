package com.auctions.system.module.message_category.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.HttpUtil;
import com.auctions.system.module.message_category.service.MessageCategoryService;
import com.liferay.portal.kernel.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class MessageCategoryControllerImpl implements MessageCategoryController{

	final String defaultView = "message-categories";
	final String messageCategoryView = "message-category-view";
	
	@Autowired
	private MessageCategoryService service;
	
	@Override
	public ModelAndView getMessageCategoriesView(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(defaultView);
		return model; 
	}
	
	@RequestMapping(params = "page=add")
	public ModelAndView getCreateMessageCategoryView(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(messageCategoryView);
		model.addObject("type", "add");
		return model; 
	}
	
	@RequestMapping(params = "page=edit")
	public ModelAndView getEditMessageCategoryView(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(messageCategoryView);
		model.addObject("type", "edit");
		return model; 
	}
	
	@Override
	public void getMessageCategories(ResourceRequest request, ResourceResponse response){		
		
		HttpUtil.createResponse(response).
			set("data", service.getMessageCategories()).
			prepare();
	}
	
	@Override
	public void insertAction(ResourceRequest request, ResourceResponse response,
			String messageCategory, String type){		
		//MessageCategory messageCategory = Serializer.fromJson(form, MessageCategory.class);
		
		HttpUtil.createResponse(response).
			set("success", service.insert(messageCategory, PortalUtil.getUserId(request))).
			prepare();
	}

}