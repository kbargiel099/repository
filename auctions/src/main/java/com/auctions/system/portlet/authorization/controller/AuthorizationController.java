package com.auctions.system.portlet.authorization.controller;

import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;
import com.liferay.portal.kernel.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class AuthorizationController {
	
	private final String defaultView = "view";
	private JsonParser parser = new JsonParser();
	private String email;
	private String password;
	private Boolean rememberMe;
	
	@RenderMapping
	public ModelAndView defaulView(RenderRequest request, RenderResponse response) throws Exception{
		ModelAndView model = new ModelAndView(defaultView);	
		return model;
	}
	
	@ResourceMapping(value = "signIn")
	public void signInAction(ResourceRequest request, ResourceResponse response,
			@RequestParam("form") String formJson) throws IOException{
    	JsonObject form = parse(formJson);
        email = getParam(form,"email");
        password = getParam(form,"password");
        rememberMe = Boolean.parseBoolean(getParam(form,"remember_me"));
        
		JsonObject result = new JsonObject();
		result.addProperty("success", authenticate(
				PortalUtil.getHttpServletRequest(request),
				PortalUtil.getHttpServletResponse(response)));
		result.addProperty("email", email);
		result.addProperty("password", password);
		result.addProperty("rememberMe", rememberMe);
		
		response.setContentType("application/json");
		response.getWriter().write(result.toString());
	}
	
    private JsonObject parse(String form){
    	return (JsonObject) parser.parse(form);
    }
    
    private boolean authenticate(HttpServletRequest request, HttpServletResponse response){
    	boolean isLogged;
		try {
			AuthenticatedSessionManagerUtil.login(request,response,
					email, password, rememberMe, CompanyConstants.AUTH_TYPE_EA);
			isLogged = true;
			
		} catch (Exception e) {
			isLogged = false;
			e.printStackTrace();
		}
		return isLogged;
    }
    
    private String getParam(JsonObject form, String param){
        if(form.has(param)){
        	return form.get(param).getAsString();
        }
        return "";
    }
	
}
