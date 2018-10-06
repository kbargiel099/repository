package com.auctions.system.portlet.user_profile.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.auctions.system.module.HttpUtil;
import com.auctions.system.module.Properties;
import com.auctions.system.module.Serializer;
import com.auctions.system.module.auction_process.controller.AuctionProcess;
import com.auctions.system.module.file_converter.FileUtil;
import com.auctions.system.module.file_converter.Worker;
import com.auctions.system.module.statistics.controller.Statistics;
import com.auctions.system.module.statistics.model.ViewType;
import com.auctions.system.portlet.category.model.AuctionDetails;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionGrade;
import com.auctions.system.portlet.user_profile.model.Image;
import com.auctions.system.portlet.user_profile.model.SpringFileVO;
import com.auctions.system.portlet.user_profile.model.UserPassword;
import com.auctions.system.portlet.user_profile.model.UserProfileDetails;
import com.auctions.system.portlet.user_profile.service.UserProfileService;
import com.liferay.portal.kernel.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class UserProfileController implements UserProfile{

	private final String defaultView = "view";
	private final String createEditAuctionView = "create-auction"; 
	private final String addGradeView = "add-grade";
	private final String settingsView = "settings";
	private final String userBoughtView = "user-bought";
	private final String userSoldView = "user-sold";
	private final String userAuctionsView = "user-auctions";
	private final String userObservationView = "user-observation";
	private final String addVideoView = "add-video";
	private final String addImagesView = "add-images";
	private final String messagesView = "messages";
	private final String conversationsView = "conversations";
	
	@Autowired
	private UserProfileService service;
	
	@Autowired
	private Statistics stats;
	
	@Autowired
	private AuctionProcess process;
	
	@Autowired
	private Worker worker;
	
    @ModelAttribute("springFileVO")
    public SpringFileVO getCommandObject() 
    {
        return new SpringFileVO();
    }
	
	@Override
	public ModelAndView defaultView(RenderRequest request, RenderResponse response, String message) throws Exception{		
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("user", service.getUserSimpleData(PortalUtil.getUserId(request)));
		model.addObject("message", message);
		return model;
	}
	
    @ActionMapping(params="formAction=fileUpload")
    public void fileUpload(@ModelAttribute SpringFileVO file, BindingResult bndingResult,
            ActionRequest request, ActionResponse response, SessionStatus sessionStatus) {
        
        response.setRenderParameter("page", "addVideo");
        response.setRenderParameter("id", String.valueOf(file.getAuctionId()));
    	
    	if(file != null && file.getFileData() != null){
            FileUtil.create(file.getFileData().get(0), Properties.getVideosPath());
            worker.convertVideo(file.getAuctionId(), file.getFileData().get(0).getOriginalFilename());
        
            sessionStatus.setComplete();  		
    	}
    }
     
    @Override
    public ModelAndView getMessagesView(RenderRequest request, RenderResponse response){
    	ModelAndView model = new ModelAndView(messagesView);
    	model.addObject("categories", service.getMessageCategories());
    	model.addObject("messages", service.getMessages());
    	return model;
    }
    
	@Override
	public ModelAndView auctionStatsView(RenderRequest request, RenderResponse response, int auctionId){
		return stats.getAuctionStatsView(process.getDetails(auctionId), ViewType.Profile);
	}
	
	@Override
	public ModelAndView getBoughtAction(RenderRequest request, RenderResponse response, String message){
		ModelAndView model = new ModelAndView(userBoughtView);
		model.addObject("auctions", service.getUserBoughtSubjects(PortalUtil.getUserId(request)));
		model.addObject("message", message);
		return model;
	}
	
	@Override
	public ModelAndView getSoldAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(userSoldView);
		model.addObject("auctions", service.getUserSoldSubjects(PortalUtil.getUserId(request)));
		return model;
	}
	
	@Override
	public ModelAndView mySettingsAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(settingsView);
		model.addObject("userPassword", new UserPassword());
		return model;
	}
	
	@Override
	public void changePasswordAction(ResourceRequest request, ResourceResponse response, String form) throws ParseException{
		
		HttpUtil.createResponse(response).
			set("success", service.changePassword(request, Serializer.fromJson(form, UserPassword.class))).
			prepare();
	}
	
	@Override
	public void updateUserDetailsAction(ResourceRequest request, ResourceResponse response, String form) throws ParseException{
		
		HttpUtil.createResponse(response).
			set("success", service.updateUserDetails(request, Serializer.fromJson(form, UserProfileDetails.class))).
			prepare();
	}
	
	@Override
	public ModelAndView userAuctionsAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(userAuctionsView);
		model.addObject("auctions", service.getUserAuctions(PortalUtil.getUserId(request)));
		return model;
	}
	
	@Override
	public ModelAndView conversations(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(conversationsView);
		model.addObject("users", service.getUsersIdsForLastConversations(PortalUtil.getUserId(request)));
		return model;
	}
	
	@Override
	public ModelAndView userObservationAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(userObservationView);
		model.addObject("auctions", service.getUserObservation(PortalUtil.getUserId(request)));
		return model;
	}
	
	@Override
	public ModelAndView addVideoView(RenderRequest request, RenderResponse response, long id){
		ModelAndView model = new ModelAndView(addVideoView);
		model.addObject("auctionId", id);
		return model;
	}
	
	@Override
	public ModelAndView addImagesView(RenderRequest request, RenderResponse response, long id){
		ModelAndView model = new ModelAndView(addImagesView);
		model.addObject("model", service.getAuctionImages(id));
		return model;
	}
	
	@Override
	public ModelAndView createNewAuctionRender(RenderRequest request, RenderResponse response){
		
		ModelAndView model = new ModelAndView(createEditAuctionView);
		model.addObject("auction", new Auction());
		model.addObject("type", "add");
		model.addObject("categories", service.getCategories());
		model.addObject("auctionTypes", service.getAuctionTypes());
		return model;
	}
	
	@Override
	public ModelAndView editAuctionRender(RenderRequest request, RenderResponse response, long id){		
		ModelAndView model = new ModelAndView(createEditAuctionView);
		model.addObject("auction",service.getAuctionData(id));
		model.addObject("type", "edit");
		model.addObject("categories", service.getCategories());
		model.addObject("auctionTypes", service.getAuctionTypes());
		return model;
	}
	
	@Override
	public ModelAndView addGradeAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(addGradeView);
		model.addObject("auctionGrade", new AuctionGrade());
		model.addObject("auctions", service.getAuctionsForGrade(PortalUtil.getUserId(request)));
		return model;
	}
	
	@Override
	public void getAuctionTypes(ResourceRequest request, ResourceResponse response){
		
		HttpUtil.createResponse(response).
			set("result", service.getAuctionTypes()).
			prepare();
	}
	
	@Override
	public void getSubCategories(ResourceRequest request, ResourceResponse response){
		
		HttpUtil.createResponse(response).
			set("result", service.getSubCategories()).
			prepare();
	}
	
	@Override
	public void createNewAuctionAction(ResourceRequest request, ResourceResponse response, String newAuction, String type) throws ParseException{
		Auction auction = Serializer.fromJson(newAuction, Auction.class);
		long userId = PortalUtil.getUserId(request);
		
		HttpUtil.createResponse(response).
			set("success", type.equals("add") ? service.createUserAuction(userId, auction)
				: service.updateAuction(auction)).
				prepare();
	}
	
	@Override
	public void addGradeAction(ResourceRequest request, ResourceResponse response, String grade){
		AuctionGrade form = Serializer.fromJson(grade, AuctionGrade.class);
		
		HttpUtil.createResponse(response).
			set("success", service.addAuctionGrade(PortalUtil.getUserId(request), form)).
			prepare();
	}
	
	@Override
	public void saveImages(ResourceRequest request, ResourceResponse response, String data){
		createFiles(data, Properties.getImagesPath());
		
		HttpUtil.createResponse(response).
			set("success", true).
			prepare();
	}
	
	private void createFiles(String data, String path){
		Image[] images = Serializer.fromJson(data, Image[].class);
        
		for(Image image : images){
			FileUtil.create(image.getData(), path + image.getName());
		}
	}
	
	@Override
	public void updateImages(ResourceRequest request, ResourceResponse response, long id, String images){		
		
		HttpUtil.createResponse(response).
			set("success", service.updateAuctionImages(images, id)).
			prepare();
	}
	
	@Override
	public void checkConversionStatus(ResourceRequest request, ResourceResponse response, long id){		
		
		HttpUtil.createResponse(response).
			set("progress", worker.checkProgress(id)).
			prepare();
	}
	
	@Override
	public void getTechnicalData(ResourceRequest request, ResourceResponse response, int id){
		
		HttpUtil.createResponse(response).
			set("data", service.getTechnicalData(id)).
			set("success",true).
			prepare();
	}
	
	@Override
	public void deleteVideo(ResourceRequest request, ResourceResponse response, long id){		
		
		HttpUtil.createResponse(response).
			set("success", service.deleteVideo(id)).
			prepare();
	}
	
	@Override
	public void getAllMessagesFromUser(ResourceRequest request, ResourceResponse response, long userId){		
		long id = PortalUtil.getUserId(request);
		
		HttpUtil.createResponse(response).
			set("messages", service.getAllMessagesFromUser(id, userId)).
			prepare();
	}

	@Override
	public void getSubCategories(ResourceRequest request, ResourceResponse response, long id, int methodId){
		
		HttpUtil.createResponse(response).
			set("success", service.makePaid(id, methodId)).
			prepare();
	}

	@Override
	public ModelAndView detailsView(RenderRequest request, RenderResponse response, String message, long id)
			throws Exception {
		return process.detailsView(request, response, message, id);
	}

	@Override
	public ModelAndView confirmPurchaseView(RenderRequest request, RenderResponse response, String form) throws Exception {
		return process.confirmPurchaseView(request, response, form);
	}

	@Override
	public ModelAndView getConfirmPurchaseView(RenderRequest request, RenderResponse response, long id, String type)
			throws Exception {
		return process.getConfirmPurchaseView(request, response, id, type);
	}

	@Override
	public void getAllOffers(ResourceRequest request, ResourceResponse response, int auctionId) throws IOException {
		process.getAllOffers(request, response, auctionId);
	}

	@Override
	public void getVideoName(ResourceRequest request, ResourceResponse response, long auctionId) throws IOException {
		process.getVideoName(request, response, auctionId);
	}

	@Override
	public void createObservation(ResourceRequest request, ResourceResponse response, int id) throws IOException {
		process.createObservation(request, response, id);
	}

	@Override
	public void removeObservation(ResourceRequest request, ResourceResponse response, int id) throws IOException {
		process.removeObservation(request, response, id);
	}

	@Override
	public ModelAndView getUserProfile(RenderRequest request, RenderResponse response, long id) throws Exception {
		return process.getUserProfile(request, response, id);
	}
	
	@Override
	public AuctionDetails getDetails(long id) {
		return process.getDetails(id);
	}
	
}
