package com.auctions.system.portlet.nav_menu.controller;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.exception.DataBaseException;
import com.auctions.system.portlet.nav_menu.service.NavService;
import com.auctions.system.portlet.users_management.model.User;
import com.google.gson.JsonObject;

/**
 * Portlet implementation class Controller
 */
@Controller
@RequestMapping("VIEW")
public class NavMenuController {
	
	@Autowired
	private NavService service;
	
	private final String defaultView = "view";
	private final String signUp = "/registration";
	
	@RenderMapping
	public ModelAndView defaulView(RenderRequest request, RenderResponse response) throws Exception{
		
		
		ModelAndView model = new ModelAndView(defaultView);	
		model.addObject("signUp", signUp);
		
		//throw new DataBaseException("błąd");
		
		return model;
	}
	
	@ResourceMapping(value = "signIn")
	public void signInAction(ResourceRequest request, ResourceResponse response,
		@RequestParam("login") String login,
		@RequestParam("pass") String password) throws IOException{

		JsonObject result = new JsonObject();
	
		boolean isExist = service.isUserExist(login, password);
		if(isExist){
			result.addProperty("success", true);
			result.addProperty("login", login);
		}else
			result.addProperty("success", false);
		
		response.setContentType("application/json");
		response.getWriter().write(result.toString());
	}
	
}

