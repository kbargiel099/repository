package com.auctions.system.portlet.message_category.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

import com.auctions.system.module.HttpUtil;
import com.auctions.system.portlet.message_category.model.MessageCategory;
import com.auctions.system.portlet.message_category.service.MessageCategoryService;
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
		model.addObject("category", new MessageCategory());
		model.addObject("type", "add");
		return model; 
	}
	
	@RequestMapping(params = "page=edit")
	public ModelAndView getEditMessageCategoryView(RenderRequest request, RenderResponse response, int id){
		ModelAndView model = new ModelAndView(messageCategoryView);
		model.addObject("category", service.getMessageCategory(id));
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
	public void insertAction(ResourceRequest request, ResourceResponse response, String messageCategory){				
		
		HttpUtil.createResponse(response).
			set("success", service.insert(messageCategory, PortalUtil.getUserId(request))).
			prepare();
	}
	
	@Override
	public void editAction(ResourceRequest request, ResourceResponse response, String messageCategory, int id){				
		
		HttpUtil.createResponse(response).
			set("success", service.edit(messageCategory, id)).
			prepare();
	}

	@Override
	public ModelAndView messagesView(RenderRequest request, RenderResponse response, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void activate(ResourceRequest request, ResourceResponse response, int id) throws Exception {
		
		HttpUtil.createResponse(response).
			set("success", service.changeStatus(id, true)).
			prepare();
	}

	@Override
	public void suspend(ResourceRequest request, ResourceResponse response, int id) throws Exception {
		
		HttpUtil.createResponse(response).
			set("success", service.changeStatus(id, false)).
			prepare();		
	}

	@Override
	public void delete(ResourceRequest request, ResourceResponse response, int id) throws Exception {
		
		HttpUtil.createResponse(response).
			set("success", service.delete(id)).
			prepare();		
	}

}