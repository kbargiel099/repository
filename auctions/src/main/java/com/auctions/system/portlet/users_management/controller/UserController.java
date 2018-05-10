package com.auctions.system.portlet.users_management.controller;

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

import com.auctions.system.module.auction_processing.controller.AuctionProcessing;
import com.auctions.system.portlet.users_management.service.UsersManagementService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

@Controller
@RequestMapping("VIEW")
public class UserController extends AuctionProcessing{

	private final String defaultView = "view";
	private final String usersView = "users";
	private final String addEditUserView = "add_edit_user"; 
	private final String userDetailsView = "details_user";
	private final String auctionsView = "auctions";
	private final String addEditAuctionView = "add_edit_auction"; 
	private final String auctionDetailsView = "details_auction"; 
		
	@Autowired
	private UsersManagementService service;
	
	@RenderMapping
	public ModelAndView defaulView(RenderRequest request, RenderResponse response) throws Exception{
		ModelAndView model = new ModelAndView(defaultView);
		return model;
	}
	
	@RenderMapping(params = "page=getUsers")
	public ModelAndView getUsersView(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(usersView);
		return model;
	}
	
	@RenderMapping(params = "page=getAuctions")
	public ModelAndView getAuctionsView(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(auctionsView);
		return model;
	}
	
/*	@RenderMapping(params = "page=addAuction")
	public ModelAndView addUserView(RenderRequest request, RenderResponse response){

		ModelAndView model = new ModelAndView(addEditAuctionView);
		model.addObject("action", "add");
		model.addObject("user", new User());
		
		return model;
	}*/
	
/*	@ActionMapping(params = "action=add")
	public void addUserAction(ActionRequest request, ActionResponse response,
		@ModelAttribute("user") @Valid User user, BindingResult result) throws IOException{

		if (result.hasErrors()){
			response.setRenderParameter("page", "add");
			return;
		}
	
		//TODO dodac mozliwosc ustawienia user jako admin
		boolean isAdded = service.createUser(user,false);
	}
	*/
	@RenderMapping(params = "page=edit")
	public ModelAndView editUserView(RenderRequest request, RenderResponse response,
			@RequestParam("userId") int userId){
		ModelAndView model = new ModelAndView(addEditUserView);
		model.addObject("action", "edit");
		model.addObject("user", service.getUserById(userId));
		
		return model;
	}
	
/*	@ActionMapping(params = "action=edit")
	public void editUserAction(ActionRequest request, ActionResponse response,
		@ModelAttribute("user") @Valid User user, BindingResult result) throws IOException{

		if (result.hasErrors()){
			response.setRenderParameter("page", "edit");
			return;
		}
	
		boolean isUpdated = service.updateUser(user);
		//response.setRenderParameter("page","default");
	}*/
	
	@RenderMapping(params = "page=details")
	public ModelAndView UserDetailsView(RenderRequest request, RenderResponse response,
			@RequestParam("userId") int userId){
		ModelAndView model = new ModelAndView(userDetailsView);
		model.addObject("user", service.getUserById(userId));
		
		return model;
	}
	
/*	@ActionMapping(params = "action=delete")
	public void deleteUserAction(ActionRequest request, ActionResponse response,
		@RequestParam("userId") int userId) throws IOException{

		boolean isdeleted = service.deleteUser(userId);
		response.setRenderParameter("page","default");
	}*/
	
	@ResourceMapping(value="getUsers")
	public void getUsers(ResourceRequest request, ResourceResponse response) throws Exception {
		JsonObject json = new JsonObject();
		json.add("data",new Gson().toJsonTree(service.getUsers()));
		
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json.toString());
	}
	
	@ResourceMapping(value="getAuctions")
	public void getAuctions(ResourceRequest request, ResourceResponse response) throws Exception {
		JsonObject json = new JsonObject();
		json.add("data",new Gson().toJsonTree(service.getAuctions()));
		
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json.toString());
	}
	
	@ResourceMapping(value="activate")
	public void activateAuction(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long id) throws Exception {
		JsonObject json = new JsonObject();
		json.addProperty("success",service.activateAuction(id));
		
	    response.setContentType("application/json");
	    response.getWriter().write(json.toString());
	}
	
	@ResourceMapping(value="suspend")
	public void suspendAuction(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long id) throws Exception {
		JsonObject json = new JsonObject();
		json.addProperty("success",service.suspendAuction(id));
		
	    response.setContentType("application/json");
	    response.getWriter().write(json.toString());
	}
	
	@ResourceMapping(value="delete")
	public void deleteAuction(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long id) throws Exception {
		JsonObject json = new JsonObject();
		json.addProperty("success",service.deleteAuction(id));
		
	    response.setContentType("application/json");
	    response.getWriter().write(json.toString());
	}
	
	@ResourceMapping(value="lock")
	public void lockUser(ResourceRequest request, ResourceResponse response,
			@RequestParam("userId") long id) throws IOException {
		JsonObject json = new JsonObject();
		
		try{
			UserLocalServiceUtil.updateLockoutById(id, true);
			json.addProperty("success", true);
			
		}catch(PortalException e){
			e.printStackTrace();
			json.addProperty("success", false);
		}
		
	    response.setContentType("application/json");
	    response.getWriter().write(json.toString());
	}
	
	@ResourceMapping(value="unlock")
	public void unlockUser(ResourceRequest request, ResourceResponse response,
			@RequestParam("userId") long id) throws IOException {
		JsonObject json = new JsonObject();
		
		try{
			UserLocalServiceUtil.updateLockoutById(id, false);
			json.addProperty("success", true);
			
		}catch(PortalException e){
			e.printStackTrace();
			json.addProperty("success", false);
		}
		
	    response.setContentType("application/json");
	    response.getWriter().write(json.toString());
	}

}