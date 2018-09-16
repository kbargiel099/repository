package com.auctions.system.portlet.nav_menu.controller;

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
import com.auctions.system.module.Properties;
import com.auctions.system.module.UserUtil;
import com.auctions.system.portlet.nav_menu.service.NavService;
import com.liferay.portal.kernel.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class NavigationMenuController implements NavigationMenu{
	
	@Autowired
	private NavService service;
	
	private final String defaultView = "view";
	
	@Override
	public ModelAndView defaultView(RenderRequest request, RenderResponse response){
		long id = PortalUtil.getUserId(request);
		
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("messages", service.getSenderIdsToNotify(id));
		model.addObject("username", UserUtil.getScreenName(id));
		model.addObject("restServiceEndpoint", Properties.getRestServiceEndpoint());
		return model;
	}
	
	@Override
	public void getMessagesFromUser(ResourceRequest request, ResourceResponse response, long userId) throws IOException{
		long current = PortalUtil.getUserId(request);
		
		HttpUtil.createResponse(response).
			set("messages", service.getUnreadMessagesFromUser(userId, current)).
			set("success", true).
			prepare();
	}

	@Override
	public void markMessagesAsRead(ResourceRequest request, ResourceResponse response, long userId) throws IOException{
		long current = PortalUtil.getUserId(request);
		
		HttpUtil.createResponse(response).
			set("success", service.markMessagesAsRead(userId, current)).
			prepare();
	}
}

