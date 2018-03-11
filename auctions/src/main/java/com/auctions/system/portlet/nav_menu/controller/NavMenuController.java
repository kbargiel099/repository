package com.auctions.system.portlet.nav_menu.controller;

import java.io.IOException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import com.auctions.system.module.UserUtil;
import com.auctions.system.portlet.nav_menu.service.NavService;
import com.google.gson.JsonObject;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;
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
		ModelAndView model = new ModelAndView(defaultView);	
		return model;
	}

	
}

