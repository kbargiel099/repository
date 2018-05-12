package com.auctions.system.portlet.users_management.controller;

import java.io.IOException;
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

import com.auctions.system.module.UserUtil;
import com.auctions.system.module.auction_processing.controller.AuctionProcessing;
import com.auctions.system.module.auction_processing.model.AuctionOffer;
import com.auctions.system.portlet.category.model.AuctionDetails;
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
	private final String auctionStatsView = "auction_stats";
		
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
	
	@RenderMapping(params = "page=stats")
	public ModelAndView auctionStatsView(RenderRequest request, RenderResponse response,
			@RequestParam("auctionId") int id){
		AuctionDetails auction = getDetails(id);
		List<AuctionOffer> transactions = auction.getTypeName().equals("quick_purchase") ?
				service.getPurchases(id) : service.getWonOffers(id);
				
		ModelAndView model = new ModelAndView(auctionStatsView);
		model.addObject("transactions", transactions);
		model.addObject("username", UserUtil.getScreenName(auction.getUserId()));
		model.addObject("auction", auction);
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