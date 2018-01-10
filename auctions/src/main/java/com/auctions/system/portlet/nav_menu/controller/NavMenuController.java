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
import com.auctions.system.portlet.nav_menu.model.UserViewModel;
import com.auctions.system.portlet.nav_menu.service.NavService;
import com.auctions.system.portlet.users_management.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

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
	private final String userSection = "/my_profile";
	
	@RenderMapping
	public ModelAndView defaulView(RenderRequest request, RenderResponse response) throws Exception{
		ModelAndView model = new ModelAndView(defaultView);	
		//model.addObject("signUp", signUp);
		
		//if(UserUtil.isGuest()){
		//	model.addObject("isGuest", true);
		//}else{
		//	//model.addObject("isGuest", false);
		//	model.addObject("login",UserUtil.getUserLogin());
		//	model.addObject("userId", UserUtil.getUserId());
		//	model.addObject("userSection", userSection);
		//}	
		
		return model;
	}
	
	@ResourceMapping(value = "signIn")
	public void signInAction(ResourceRequest request, ResourceResponse response,
		@RequestParam("email") String login,
		@RequestParam("pass") String password) throws IOException{

		Gson gson = new Gson();
		JsonObject result = new JsonObject();
	
		/*boolean isExist = service.isUserExist(login, password);
		if(isExist){
			UserUtil.setUser(service.getUser(login));
			result.addProperty("success", true);
		}else
			result.addProperty("success", false);
		*/
		try {
			AuthenticatedSessionManagerUtil.login(PortalUtil.getHttpServletRequest(request), PortalUtil.getHttpServletResponse(response),
					login, password, false, CompanyConstants.AUTH_TYPE_EA);
			result.addProperty("success", true);
			
		} catch (Exception e) {
			result.addProperty("success", false);
			e.printStackTrace();
		}
		
		response.setContentType("application/json");
		response.getWriter().write(result.toString());
	}
	
	@ResourceMapping(value = "logout")
	public void logoutAction(ResourceRequest request, ResourceResponse response) throws IOException{

		UserUtil.setIsAdmin(false);
		UserUtil.setUser(null);
	}
	
	/*private List<Option> createOptions(int userId,ResourceResponse response){
		
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
		
	}*/
	
}

