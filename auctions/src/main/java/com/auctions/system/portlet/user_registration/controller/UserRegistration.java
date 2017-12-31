package com.auctions.system.portlet.user_registration.controller;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.auctions.system.portlet.users_management.model.User;

/**
 * Portlet implementation class UserRegistration
 */
@Controller("userRegistration")
@RequestMapping(value = "VIEW")
public class UserRegistration{

	
	private final String defaultView = "view";
	
	@RenderMapping
	public ModelAndView defaulView(RenderRequest request, RenderResponse response) throws Exception{
		
		ModelAndView model = new ModelAndView(defaultView);
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
	
		//boolean isAdded = service.createUser(user,false);
	}

}
