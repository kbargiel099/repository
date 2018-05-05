package com.auctions.system.portlet.nav_menu.controller;

import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.UserUtil;
import com.auctions.system.portlet.nav_menu.service.NavService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * Portlet implementation class Controller
 */
@Controller
@RequestMapping("VIEW")
public class NavMenuController {
	
	@Autowired
	private NavService service;
	
	private final String defaultView = "view";
	
	@RenderMapping
	public ModelAndView defaulView(RenderRequest request, RenderResponse response){
		long id = PortalUtil.getUserId(request);
		
		ModelAndView model = new ModelAndView(defaultView);	
		model.addObject("messages", service.getSenderIdsToNotify(id));
		model.addObject("username", UserUtil.getScreenName(id));
		return model;
	}
	
	@ResourceMapping("getMessagesFromUser")
	public void getMessagesFromUser(ResourceRequest request, ResourceResponse response,
			@RequestParam("userId") long id) throws IOException{
		Gson gson = new Gson();
		
		JsonObject obj = new JsonObject();
		obj.addProperty("messages",gson.toJson(service
				.getUnreadMessagesFromUser(id,PortalUtil.getUserId(request))));
		obj.addProperty("success", true);
		
		response.setContentType("application/json");
		response.getWriter().write(obj.toString());
	}

	@ResourceMapping("markMessagesAsRead")
	public void markMessagesAsRead(ResourceRequest request, ResourceResponse response,
			@RequestParam("userId") long id) throws IOException{
		
		JsonObject obj = new JsonObject();
		obj.addProperty("success",service.markMessagesAsRead(id,PortalUtil.getUserId(request)));
		
		response.setContentType("application/json");
		response.getWriter().write(obj.toString());
	}
}

