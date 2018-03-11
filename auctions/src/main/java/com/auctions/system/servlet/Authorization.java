package com.auctions.system.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.servlet.LiferayFilter;
import com.liferay.portal.kernel.util.PortalUtil;

@WebServlet()
public class Authorization extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JsonParser parser;
	private String email;
	private String password;
	private Boolean rememberMe;
	
	@Override
	public void init(){
		parser = new JsonParser();
	}
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        email = request.getParameter("email");
        password = request.getParameter("password");
        rememberMe = Boolean.parseBoolean(request.getParameter("remember_me"));
		JsonObject result = new JsonObject();
		result.addProperty("success", authenticate(request,response));
		
		response.setContentType("application/json");
		response.getWriter().write(result.toString());
    	//response.getWriter().write(email+" "+password+" "+rememberMe);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletRequest requesttt = ServiceContextThreadLocal.getServiceContext().getRequest();
        //HttpServletRequest originalRequest = unwrapOriginalRequest(request);
        //HttpSession originalSession = originalRequest.getSession();
    	
    	JsonObject form = parse(request.getParameter("form"));
        String email = getParam(form,"email");
        String password = getParam(form,"password");
        Boolean rememberMe = Boolean.parseBoolean(getParam(form,"remember_me"));
		JsonObject result = new JsonObject();
		result.addProperty("success", authenticate(requesttt,response));
		
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
					email, password, rememberMe, CompanyConstants.SYSTEM_STRING);
			isLogged = true;
			
		} catch (Exception e) {
			isLogged = false;
			e.printStackTrace();
		}
		return isLogged;
    }
    
    private String getParam(JsonObject form, String param){
        if(form.has(param)){
        	return form.get(param).toString();
        }
        else{
        	return null;
        }
    }
   
}
