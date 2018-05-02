package com.auctions.system.portlet.nav_menu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.auctions.system.portlet.nav_menu.model.MessageModel;
import com.auctions.system.portlet.nav_menu.model.UserData;
import com.auctions.system.portlet.nav_menu.service.NavService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
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
	public ModelAndView defaulView(RenderRequest request, RenderResponse response) throws Exception{
		long id = PortalUtil.getUserId(request);
		
		ModelAndView model = new ModelAndView(defaultView);	
		model.addObject("messages", createUnreadMessages(id));
		return model;
	}
	
	private List<UserData> createUnreadMessages(long receiverId) throws PortalException{
		List<UserData> messages = new ArrayList<UserData>();
		User user;
		
		for(UserData m : service.getSenderIdsToNotify(receiverId)){
			user = UserLocalServiceUtil.getUserById(m.getSenderId());
			m.setScreenName(user.getScreenName());
			messages.add(m);
		}
		
		return messages;
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

	
}

