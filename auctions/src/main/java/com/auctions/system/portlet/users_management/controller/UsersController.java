/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.auctions.system.portlet.users_management.controller;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.Option;
import com.auctions.system.portlet.users_management.model.User;
import com.auctions.system.portlet.users_management.model.UserOptions;
import com.auctions.system.portlet.users_management.service.UsersManagementService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("VIEW")
public class UsersController {

	@Autowired
	UsersManagementService service;
	
	@RenderMapping
	public ModelAndView question(RenderRequest request, RenderResponse response) throws Exception{
		
		ModelAndView model = new ModelAndView("view");
		
		return model;
	}
	
	@ResourceMapping(value="getUsers")
	public void getUsers(ResourceRequest request, ResourceResponse response) throws Exception {
 
		JsonObject json = new JsonObject();
		json.add("data",createUserOptions(response));
		
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json.toString());
	}
	
	private JsonElement createUserOptions(ResourceResponse response){
		Gson gson = new Gson();
		List<UserOptions> users = new ArrayList<UserOptions>();
		
		for(User user : service.getUsers()){
			users.add(new UserOptions(user,createOptions(user.getId(),response)));
		}
		return gson.toJsonTree(users);
	}
	
	private List<Option> createOptions(int userId,ResourceResponse response){
		List<Option> options = new ArrayList<Option>();
		
		PortletURL showDetailsUrl = response.createRenderURL();
		showDetailsUrl.setParameter("userId", Integer.toString(userId));
		showDetailsUrl.setParameter("page", "details");
		
		PortletURL editUrl = response.createRenderURL();
		editUrl.setParameter("userId", Integer.toString(userId));
		editUrl.setParameter("page", "edit");
		
		PortletURL deleteUrl = response.createActionURL();
		deleteUrl.setParameter("userId", Integer.toString(userId));
		deleteUrl.setParameter("action", "delete");
		
		options.add(new Option("details",showDetailsUrl.toString()));
		options.add(new Option("edit",editUrl.toString()));
		options.add(new Option("delete",deleteUrl.toString()));
		return options;
		
	}

}