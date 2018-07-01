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

import com.auctions.system.module.HttpUtil;
import com.auctions.system.module.UserUtil;
import com.auctions.system.module.auction_processing.controller.Processing;
import com.auctions.system.module.message_category.controller.MessageCategoryController;
import com.auctions.system.module.statistics.controller.Statistics;
import com.auctions.system.module.statistics.model.ViewType;
import com.auctions.system.portlet.users_management.service.UsersManagementService;

@Controller
@RequestMapping("VIEW")
public class UserController implements UserManagement{

	//private final String defaultView = "view";
	private final String usersView = "users";
	private final String addEditUserView = "add_edit_user"; 
	private final String userDetailsView = "details_user";
	private final String auctionsView = "auctions";
		
	@Autowired
	MessageCategoryController controller;
	
	@Autowired
	Processing processing;
	
	@Autowired
	private UsersManagementService service;
	
	@Autowired
	private Statistics stats;
	
	@RenderMapping
	public ModelAndView defaulView(RenderRequest request, RenderResponse response) throws Exception{
		ModelAndView model = new ModelAndView(auctionsView);
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
	
	@RenderMapping(params = "page=edit")
	public ModelAndView editUserView(RenderRequest request, RenderResponse response,
			@RequestParam("userId") int userId){
		ModelAndView model = new ModelAndView(addEditUserView);
		model.addObject("action", "edit");
		model.addObject("user", service.getUserById(userId));
		return model;
	}
	
	@RenderMapping(params = "page=details")
	public ModelAndView UserDetailsView(RenderRequest request, RenderResponse response,
			@RequestParam("userId") int userId){
		ModelAndView model = new ModelAndView(userDetailsView);
		model.addObject("user", service.getUserById(userId));
		return model;
	}
	
	@RenderMapping(params = "page=stats")
	public ModelAndView auctionStatsView(RenderRequest request, RenderResponse response,
			@RequestParam("auctionId") int id){
		return stats.getAuctionStatsView(processing.getDetails(id),ViewType.Administration);
	}
	
	@ResourceMapping(value="getUsers")
	public void getUsers(ResourceRequest request, ResourceResponse response) throws Exception {
	    
		HttpUtil.createResponse(response).
			set("data", service.getUsers()).
			prepare();
	}
	
	@ResourceMapping(value="getAuctions")
	public void getAuctions(ResourceRequest request, ResourceResponse response) throws Exception {
	    
		HttpUtil.createResponse(response).
			set("data", service.getAuctions()).
			prepare();
	}
	
	@ResourceMapping(value="activate")
	public void activateAuction(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long id) throws Exception {
	    
		HttpUtil.createResponse(response).
			set("success", service.activateAuction(id)).
			prepare();
	}
	
	@ResourceMapping(value="suspend")
	public void suspendAuction(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long id) throws Exception {
	    
		HttpUtil.createResponse(response).
			set("success", service.suspendAuction(id)).
			prepare();
	}
	
	@ResourceMapping(value="delete")
	public void deleteAuction(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long id) throws Exception {
	    
		HttpUtil.createResponse(response).
			set("success", service.deleteAuction(id)).
			prepare();
	}
	
	@ResourceMapping(value="lock")
	public void lockUser(ResourceRequest request, ResourceResponse response,
			@RequestParam("userId") long id) throws IOException {
	    
		HttpUtil.createResponse(response).
			set("success", UserUtil.updateLockoutUser(id,true)).
			prepare();
	}
	
	@ResourceMapping(value="unlock")
	public void unlockUser(ResourceRequest request, ResourceResponse response,
			@RequestParam("userId") long id) throws IOException {
	    
		HttpUtil.createResponse(response).
			set("success", UserUtil.updateLockoutUser(id,false)).
			prepare();
	}

}