package com.auctions.system.portlet.nav_menu.controller;

import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

public interface NavigationMenu {

	@RenderMapping
	public ModelAndView defaultView(RenderRequest request, RenderResponse response);
	
	@ResourceMapping("getMessagesFromUser")
	public void getMessagesFromUser(ResourceRequest request, ResourceResponse response,
			@RequestParam("userId") long id) throws IOException;

	@ResourceMapping("markMessagesAsRead")
	public void markMessagesAsRead(ResourceRequest request, ResourceResponse response,
			@RequestParam("userId") long id) throws IOException;
	
}
