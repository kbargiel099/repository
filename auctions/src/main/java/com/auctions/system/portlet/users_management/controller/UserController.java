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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.Option;
import com.auctions.system.portlet.users_management.model.User;
import com.auctions.system.portlet.users_management.model.UserOptions;
import com.auctions.system.portlet.users_management.service.UsersManagementService;
import com.auctions.system.portlet.users_management.validator.UserValidator;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("VIEW")
public class UserController {

	private final String defaultView = "view";
	private final String addEditView = "add_edit"; 
	private final String detailsView = "details"; 
	
	private final String imagesPath = "E:\\Szkoła\\Praca inżynierska\\Repozytorium\\repository\\auctions\\images\\";
	
	@Autowired
	private UsersManagementService service;
	
	@Autowired
	private UserValidator Validator;
	
	@InitBinder("user")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(Validator);
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}
	
	@RenderMapping
	public ModelAndView defaulView(RenderRequest request, RenderResponse response) throws Exception{

		ModelAndView model = new ModelAndView(defaultView);
		//AuthenticatedSessionManagerUtil.login(PortalUtil.getHttpServletRequest(request), PortalUtil.getHttpServletResponse(response), "test@liferay.com", "test", false, CompanyConstants.AUTH_TYPE_EA);
		//AuthenticatedSessionManagerUtil.logout(PortalUtil.getHttpServletRequest(request), PortalUtil.getHttpServletResponse(response));
		//UserLocalServiceUtil.deleteUser(62409);
		//add();
		
		return model;
	}
	
	@RenderMapping(params = "page=add")
	public ModelAndView addUserView(RenderRequest request, RenderResponse response){

		ModelAndView model = new ModelAndView(addEditView);
		model.addObject("action", "add");
		model.addObject("user", new User());
		
		return model;
	}
	
	@ActionMapping(params = "action=add")
	public void addUserAction(ActionRequest request, ActionResponse response,
		@ModelAttribute("user") @Valid User user, BindingResult result) throws IOException{

		if (result.hasErrors()){
			response.setRenderParameter("page", "add");
			return;
		}
	
		//TODO dodac mozliwosc ustawienia user jako admin
		boolean isAdded = service.createUser(user,false);
	}
	
	@RenderMapping(params = "page=edit")
	public ModelAndView editUserView(RenderRequest request, RenderResponse response,
			@RequestParam("userId") int userId){

		ModelAndView model = new ModelAndView(addEditView);
		model.addObject("action", "edit");
		model.addObject("user", service.getUserById(userId));
		
		return model;
	}
	
	@ActionMapping(params = "action=edit")
	public void editUserAction(ActionRequest request, ActionResponse response,
		@ModelAttribute("user") @Valid User user, BindingResult result) throws IOException{

		if (result.hasErrors()){
			response.setRenderParameter("page", "edit");
			return;
		}
	
		boolean isUpdated = service.updateUser(user);
		//response.setRenderParameter("page","default");
	}
	
	@RenderMapping(params = "page=details")
	public ModelAndView UserDetailsView(RenderRequest request, RenderResponse response,
			@RequestParam("userId") int userId){

		ModelAndView model = new ModelAndView(detailsView);
		model.addObject("user", service.getUserById(userId));
		
		return model;
	}
	
	@ActionMapping(params = "action=delete")
	public void deleteUserAction(ActionRequest request, ActionResponse response,
		@RequestParam("userId") int userId) throws IOException{

		boolean isdeleted = service.deleteUser(userId);
		response.setRenderParameter("page","default");
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