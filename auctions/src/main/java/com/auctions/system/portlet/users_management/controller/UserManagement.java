package com.auctions.system.portlet.users_management.controller;

import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

public interface UserManagement{
	
	@RenderMapping
	public ModelAndView defaultView(RenderRequest request, RenderResponse response) throws Exception;
	
	@RenderMapping(params = "page=edit")
	public ModelAndView editUserView(RenderRequest request, RenderResponse response,
			@RequestParam("userId") int userId);
	
	@RenderMapping(params = "page=details")
	public ModelAndView UserDetailsView(RenderRequest request, RenderResponse response,
			@RequestParam("userId") int userId);
	
	@ResourceMapping(value="getUsers")
	public void getUsers(ResourceRequest request, ResourceResponse response) throws Exception;
	
	@ResourceMapping(value="lock")
	public void lockUser(ResourceRequest request, ResourceResponse response,
			@RequestParam("userId") long userId) throws IOException;
	
	@ResourceMapping(value="unlock")
	public void unlockUser(ResourceRequest request, ResourceResponse response,
			@RequestParam("userId") long userId) throws IOException;
}
