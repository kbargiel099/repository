package com.auctions.system.portlet.users_management.controller;

import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

import com.auctions.system.module.HttpUtil;
import com.auctions.system.module.UserUtil;
import com.auctions.system.portlet.users_management.service.UsersManagementService;

@Controller
@RequestMapping("VIEW")
public class UserController implements UserManagement{

	private final String usersView = "users";
	private final String addEditUserView = "add_edit_user"; 
	private final String userDetailsView = "details_user";
	
	@Autowired
	UsersManagementService service;
	
	@Override
	public ModelAndView defaultView(RenderRequest request, RenderResponse response) throws Exception{
		ModelAndView model = new ModelAndView(usersView);
		return model;
	}
	
	@Override
	public ModelAndView editUserView(RenderRequest request, RenderResponse response, int userId){
		ModelAndView model = new ModelAndView(addEditUserView);
		model.addObject("action", "edit");
		model.addObject("user", service.getUserById(userId));
		return model;
	}
	
	@Override
	public ModelAndView UserDetailsView(RenderRequest request, RenderResponse response, int userId){
		ModelAndView model = new ModelAndView(userDetailsView);
		model.addObject("user", service.getUserById(userId));
		return model;
	}
	
	@Override
	public void getUsers(ResourceRequest request, ResourceResponse response) throws Exception {
	    
		HttpUtil.createResponse(response).
			set("data", service.getUsers()).
			prepare();
	}
	
	@Override
	public void lockUser(ResourceRequest request, ResourceResponse response, long userId) throws IOException {
	    
		HttpUtil.createResponse(response).
			set("success", UserUtil.updateLockoutUser(userId,true)).
			prepare();
	}
	
	@Override
	public void unlockUser(ResourceRequest request, ResourceResponse response, long userId) throws IOException {
	    
		HttpUtil.createResponse(response).
			set("success", UserUtil.updateLockoutUser(userId,false)).
			prepare();
	}

}