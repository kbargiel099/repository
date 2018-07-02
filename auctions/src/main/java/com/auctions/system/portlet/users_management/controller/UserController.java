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
import com.auctions.system.portlet.category.model.AuctionDetails;
import com.auctions.system.portlet.users_management.service.UsersManagementService;

@Controller
@RequestMapping("VIEW")
public class UserController implements UserManagement, Processing, MessageCategoryController{

	//private final String defaultView = "view";
	private final String usersView = "users";
	private final String addEditUserView = "add_edit_user"; 
	private final String userDetailsView = "details_user";
	private final String auctionsView = "auctions";
		
	@Autowired
	Processing processing;
	
	@Autowired
	MessageCategoryController messageCategory;
	
	@Autowired
	private UsersManagementService service;
	
	@Autowired
	private Statistics stats;
	
	@RenderMapping
	public ModelAndView defaultView(RenderRequest request, RenderResponse response) throws Exception{
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
	
	@Override
	public ModelAndView detailsView(RenderRequest request, RenderResponse response, String message, long id)
			throws Exception {
		return processing.detailsView(request, response, message, id);
	}

	@Override
	public ModelAndView confirmPurchaseView(RenderRequest request, RenderResponse response, long id, long sellerId,
			String name, long price, int quantity, String endDate) throws Exception {
		return processing.confirmPurchaseView(request, response, id, sellerId, name, price, quantity, endDate);
	}

	@Override
	public ModelAndView getConfirmPurchaseView(RenderRequest request, RenderResponse response, long id, String type)
			throws Exception {
		return processing.getConfirmPurchaseView(request, response, id, type);
	}

	@Override
	public void getAllOffers(ResourceRequest request, ResourceResponse response, int id) throws IOException {
		processing.getAllOffers(request, response, id);
	}

	@Override
	public void getVideoName(ResourceRequest request, ResourceResponse response, long id) throws IOException {
		processing.getVideoName(request, response, id);
	}

	@Override
	public void createObservation(ResourceRequest request, ResourceResponse response, int id) throws IOException {
		processing.createObservation(request, response, id);
	}

	@Override
	public void removeObservation(ResourceRequest request, ResourceResponse response, int id) throws IOException {
		processing.removeObservation(request, response, id);
	}

	@Override
	public AuctionDetails getDetails(long id) {
		return processing.getDetails(id);
	}

	@Override
	public ModelAndView getMessageCategoriesView(RenderRequest request, RenderResponse response) {
		return messageCategory.getMessageCategoriesView(request, response);
	}

	@Override
	public void insertAction(ResourceRequest request, ResourceResponse response, String form, String type)
			throws IOException {
		messageCategory.insertAction(request, response, form, type);
	}

}