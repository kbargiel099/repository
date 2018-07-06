package com.auctions.system.portlet.users_management.controller;

import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.auction_processing.controller.Processing;
import com.auctions.system.module.message_category.controller.MessageCategoryController;

public interface UserManagement extends Processing, MessageCategoryController{
	
	@RenderMapping
	public ModelAndView defaultView(RenderRequest request, RenderResponse response) throws Exception;
	
	@RenderMapping(params = "page=getUsers")
	public ModelAndView getUsersView(RenderRequest request, RenderResponse response);
	
	@RenderMapping(params = "page=getAuctions")
	public ModelAndView getAuctionsView(RenderRequest request, RenderResponse response);
	
	@RenderMapping(params = "page=edit")
	public ModelAndView editUserView(RenderRequest request, RenderResponse response,
			@RequestParam("userId") int userId);
	
	@RenderMapping(params = "page=details")
	public ModelAndView UserDetailsView(RenderRequest request, RenderResponse response,
			@RequestParam("userId") int userId);
	
	@RenderMapping(params = "page=stats")
	public ModelAndView auctionStatsView(RenderRequest request, RenderResponse response,
			@RequestParam("auctionId") int id);
	
	@ResourceMapping(value="getUsers")
	public void getUsers(ResourceRequest request, ResourceResponse response) throws Exception;
	
	@ResourceMapping(value="getAuctions")
	public void getAuctions(ResourceRequest request, ResourceResponse response) throws Exception;
	
	@ResourceMapping(value="activate")
	public void activateAuction(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long id) throws Exception;
	
	@ResourceMapping(value="suspend")
	public void suspendAuction(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long id) throws Exception;
	
	@ResourceMapping(value="delete")
	public void deleteAuction(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long id) throws Exception;
	
	@ResourceMapping(value="lock")
	public void lockUser(ResourceRequest request, ResourceResponse response,
			@RequestParam("userId") long id) throws IOException;
	
	@ResourceMapping(value="unlock")
	public void unlockUser(ResourceRequest request, ResourceResponse response,
			@RequestParam("userId") long id) throws IOException;
}
