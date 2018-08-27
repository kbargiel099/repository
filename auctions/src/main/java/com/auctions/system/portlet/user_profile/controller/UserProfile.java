package com.auctions.system.portlet.user_profile.controller;

import java.text.ParseException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.auction_processing.controller.Processing;

public interface UserProfile extends Processing{
	
	@RenderMapping
	public ModelAndView defaultView(RenderRequest request, RenderResponse response,
			@RequestParam(value ="message", defaultValue = "") String message) throws Exception;
	
	@RenderMapping(params = "page=messages")
    public ModelAndView getMessagesView(RenderRequest request, RenderResponse response);
	
	@RenderMapping(params = "page=stats")
	public ModelAndView auctionStatsView(RenderRequest request, RenderResponse response,
			@RequestParam("auctionId") int auctionId);
	
	@RequestMapping(params = "page=getBought")
	public ModelAndView getBoughtAction(RenderRequest request, RenderResponse response,
			@RequestParam(value ="message", defaultValue = "") String message);
	
	@RequestMapping(params = "page=getSold")
	public ModelAndView getSoldAction(RenderRequest request, RenderResponse response);
	
	@RequestMapping(params = "page=mySettings")
	public ModelAndView mySettingsAction(RenderRequest request, RenderResponse response);
	
	@ResourceMapping("changePassword")
	public void changePasswordAction(ResourceRequest request, ResourceResponse response,
			@RequestParam("form") String form) throws ParseException;
	
	@RequestMapping(params = "page=myAuctions")
	public ModelAndView userAuctionsAction(RenderRequest request, RenderResponse response);
	
	@RequestMapping(params = "page=conversations")
	public ModelAndView conversations(RenderRequest request, RenderResponse response);
	
	@RequestMapping(params = "page=observations")
	public ModelAndView userObservationAction(RenderRequest request, RenderResponse response);
	
	@RequestMapping(params = "page=addVideo")
	public ModelAndView addVideoView(RenderRequest request, RenderResponse response,
			@RequestParam("id") long id);
	
	@RequestMapping(params = "page=addImages")
	public ModelAndView addImagesView(RenderRequest request, RenderResponse response,
			@RequestParam("id") long id);
	
	@RenderMapping(params = "page=createNewAuction")
	public ModelAndView createNewAuctionRender(RenderRequest request, RenderResponse response);
	
	@RenderMapping(params = "page=editAuction")
	public ModelAndView editAuctionRender(RenderRequest request, RenderResponse response,
			@RequestParam("id") long id);
	
	@RequestMapping(params = "page=addGrade")
	public ModelAndView addGradeAction(RenderRequest request, RenderResponse response);
	
	@ResourceMapping(value = "getSubCategories")
	public void getSubCategories(ResourceRequest request, ResourceResponse response);
	
	@ResourceMapping("submitAuction")
	public void createNewAuctionAction(ResourceRequest request, ResourceResponse response,
			@RequestParam("newAuction") String newAuction, @RequestParam("type") String type) throws ParseException;
	
	@ResourceMapping("addGrade")
	public void addGradeAction(ResourceRequest request, ResourceResponse response,
			@RequestParam("grade") String grade) throws ParseException;
	
	@ResourceMapping(value = "saveImage")
	public void saveImages(ResourceRequest request, ResourceResponse response,
			@RequestParam("data") String data);
		
	@ResourceMapping("updateImages")
	public void updateImages(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long id,
			@RequestParam("images") String images);
	
	@ResourceMapping("checkConversionStatus")
	public void checkConversionStatus(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long id);
	
	@ResourceMapping(value = "getTechnicalData")
	public void getTechnicalData(ResourceRequest request, ResourceResponse response,
			@RequestParam("id") int id);
	
	@ResourceMapping("deleteVideo")
	public void deleteVideo(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long id);
	
	@ResourceMapping("getAllMessagesFromUser")
	public void getAllMessagesFromUser(ResourceRequest request, ResourceResponse response,
			@RequestParam("userId") long userId);

	@ResourceMapping(value = "makePaid")
	public void getSubCategories(ResourceRequest request, ResourceResponse response,
			@RequestParam("purchaseId") long id, @RequestParam("paymentMethodId") int methodId);

}
