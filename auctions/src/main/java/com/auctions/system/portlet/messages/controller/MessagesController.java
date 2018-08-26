package com.auctions.system.portlet.messages.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

import com.auctions.system.module.HttpUtil;
import com.auctions.system.portlet.messages.model.Message;
import com.auctions.system.portlet.messages.service.MessagesService;
import com.google.gson.Gson;
import com.liferay.portal.kernel.util.PortalUtil;

@Controller
@RequestMapping(value = "VIEW")
public class MessagesController implements Messages{

	private final String defaultView = "messages";
	private final String addEditView = "view";
	
	@Autowired
	private MessagesService service;
	
	@Override
	public ModelAndView getSearch(RenderRequest request){
		ModelAndView model = new ModelAndView(defaultView);
		return model;
	}
	
	@Override
	public ModelAndView getCreateMessageView(RenderRequest request, RenderResponse response) {
		ModelAndView model = new ModelAndView(addEditView);
		model.addObject("message", new Message());
		model.addObject("categories", service.getMessageCategories());
		model.addObject("type", "add");
		return model;
	}

	@Override
	public ModelAndView getEditMessageView(RenderRequest request, RenderResponse response, int id) {
		ModelAndView model = new ModelAndView(addEditView);
		model.addObject("message", service.getMessage(id));
		model.addObject("categories", service.getMessageCategories());
		model.addObject("type", "add");
		return model;
	}
	
	@Override
	public void getMessages(ResourceRequest request, ResourceResponse response){		
		
		HttpUtil.createResponse(response).
			set("data", service.getMessages()).
			prepare();
	}

	@Override
	public void insertAction(ResourceRequest request, ResourceResponse response, String message) {
		Message form = new Gson().fromJson(message, Message.class);
		HttpUtil.createResponse(response).
			set("data", service.insert(form, PortalUtil.getUserId(request))).
			prepare();
	}

	@Override
	public void editAction(ResourceRequest request, ResourceResponse response, String message, int id) {
		Message form = new Gson().fromJson(message, Message.class);
		HttpUtil.createResponse(response).
			set("data", service.edit(form, PortalUtil.getUserId(request))).
			prepare();
		
	}

}
