package com.auctions.system.portlet.users_management.controller;

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
import com.auctions.system.module.UserUtil;
import com.auctions.system.module.auction_processing.controller.Processing;
import com.auctions.system.module.message_category.controller.MessageCategoryController;
import com.auctions.system.module.statistics.controller.Statistics;
import com.auctions.system.module.statistics.model.ViewType;
import com.auctions.system.portlet.category.model.AuctionDetails;
import com.auctions.system.portlet.users_management.service.UsersManagementService;

@Controller
@RequestMapping("VIEW")
public class UserController implements UserManagement{

	private final String usersView = "users";
	private final String addEditUserView = "add_edit_user"; 
	private final String userDetailsView = "details_user";
	private final String auctionsView = "auctions";
		
	@Autowired
	Processing processing;
	
	@Autowired
	MessageCategoryController messageCategoryCtrl;
	
	@Autowired
	UsersManagementService service;
	
	@Autowired
	Statistics stats;
	
	
	@Override
	public ModelAndView defaultView(RenderRequest request, RenderResponse response) throws Exception{
		ModelAndView model = new ModelAndView(auctionsView);
		return model;
	}
	
	@Override
	public ModelAndView getUsersView(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(usersView);
		return model;
	}
	
	@Override
	public ModelAndView getAuctionsView(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(auctionsView);
		return model;
	}
	
	@Override
	public ModelAndView editUserView(RenderRequest request, RenderResponse response, int userId){
		ModelAndView model = new ModelAndView(addEditUserView);
		model.addObject("action", "edit");
		model.addObject("user", service.getUserById(userId));
		return model;
	}
	
	@Override
	public ModelAndView UserDetailsView(RenderRequest request, RenderResponse response, int userId){
		ModelAndView model = new ModelAndView(userDetailsView);
		model.addObject("user", service.getUserById(userId));
		return model;
	}
	
	@Override
	public ModelAndView auctionStatsView(RenderRequest request, RenderResponse response, long auctionId){
		return stats.getAuctionStatsView(processing.getDetails(auctionId),ViewType.Administration);
	}
	
	@Override
	public void getUsers(ResourceRequest request, ResourceResponse response) throws Exception {
	    
		HttpUtil.createResponse(response).
			set("data", service.getUsers()).
			prepare();
	}
	
	@Override
	public void getAuctions(ResourceRequest request, ResourceResponse response) throws Exception {
	    
		HttpUtil.createResponse(response).
			set("data", service.getAuctions()).
			prepare();
	}
	
	@Override
	public void activateAuction(ResourceRequest request, ResourceResponse response, long auctionId) throws Exception {
	    
		HttpUtil.createResponse(response).
			set("success", service.activateAuction(auctionId)).
			prepare();
	}
	
	@Override
	public void suspendAuction(ResourceRequest request, ResourceResponse response, long auctionId) throws Exception {
	    
		HttpUtil.createResponse(response).
			set("success", service.suspendAuction(auctionId)).
			prepare();
	}
	
	@Override
	public void deleteAuction(ResourceRequest request, ResourceResponse response, long auctionId) throws Exception {
	    
		HttpUtil.createResponse(response).
			set("success", service.deleteAuction(auctionId)).
			prepare();
	}
	
	@Override
	public void lockUser(ResourceRequest request, ResourceResponse response, long userId) throws IOException {
	    
		HttpUtil.createResponse(response).
			set("success", UserUtil.updateLockoutUser(userId,true)).
			prepare();
	}
	
	@Override
	public void unlockUser(ResourceRequest request, ResourceResponse response, long userId) throws IOException {
	    
		HttpUtil.createResponse(response).
			set("success", UserUtil.updateLockoutUser(userId,false)).
			prepare();
	}
	
	@Override
	public ModelAndView detailsView(RenderRequest request, RenderResponse response, String message, long id)
			throws Exception {
		return processing.detailsView(request, response, message, id);
	}

	@Override
	public ModelAndView confirmPurchaseView(RenderRequest request, RenderResponse response, String form) throws Exception {
		return processing.confirmPurchaseView(request, response, form);
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
		return messageCategoryCtrl.getMessageCategoriesView(request, response);
	}

	@Override
	public ModelAndView getCreateMessageCategoryView(RenderRequest request, RenderResponse response) {
		return messageCategoryCtrl.getCreateMessageCategoryView(request, response);
	}
	
	@Override
	public void insertAction(ResourceRequest request, ResourceResponse response, String messageCategory, String type){
		messageCategoryCtrl.insertAction(request, response, messageCategory, type);
	}

	@Override
	public void getMessageCategories(ResourceRequest request, ResourceResponse response) {
		messageCategoryCtrl.getMessageCategories(request, response);
	}

}