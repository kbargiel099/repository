package com.auctions.system.portlet.messages.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

public interface Messages {

	@RenderMapping()
	public ModelAndView getSearch(RenderRequest request);
	
	@RequestMapping(params = "page=add")
	public ModelAndView getCreateMessageView(RenderRequest request, RenderResponse response);
	
	@RequestMapping(params = "page=edit")
	public ModelAndView getEditMessageView(RenderRequest request, RenderResponse response,
			@RequestParam("id") int id);
	
	@ResourceMapping("insert")
	public void insertAction(ResourceRequest request, ResourceResponse response,
			@RequestParam("message") String message);
	
	@ResourceMapping("edit")
	public void editAction(ResourceRequest request, ResourceResponse response, 
			@RequestParam("message") String message, @RequestParam("id") int id);
	
	@ResourceMapping("getMessages")
	public void getMessages(ResourceRequest request, ResourceResponse response);
	
	@ResourceMapping(value="delete")
	public void delete(ResourceRequest request, ResourceResponse response,
			@RequestParam("id") int id) throws Exception;
}
