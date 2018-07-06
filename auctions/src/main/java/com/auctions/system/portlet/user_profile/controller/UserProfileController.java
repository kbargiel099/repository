package com.auctions.system.portlet.user_profile.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

import com.auctions.system.module.HttpUtil;
import com.auctions.system.module.Properties;
import com.auctions.system.module.Serializer;
import com.auctions.system.module.auction_processing.controller.Processing;
import com.auctions.system.module.file_converter.FileUtil;
import com.auctions.system.module.file_converter.Worker;
import com.auctions.system.module.statistics.controller.Statistics;
import com.auctions.system.module.statistics.model.ViewType;
import com.auctions.system.portlet.category.model.AuctionDetails;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionGrade;
import com.auctions.system.portlet.user_profile.model.UserPassword;
import com.auctions.system.portlet.user_profile.model.UsernameAndId;
import com.auctions.system.portlet.user_profile.service.UserProfileService;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
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
	private final String conversationsView = "conversations";
	
	@Autowired
	private UserProfileService service;
	
	@Autowired
	private Statistics stats;
	
	@Autowired
	Processing processing;
	
	@Autowired
	Worker worker;
	
	@InitBinder("auctionGrade")
	private void initBinderGrade(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}
	
	@Override
	public ModelAndView defaultView(RenderRequest request, RenderResponse response, String message) throws Exception{
		long id = PortalUtil.getUserId(request);
		
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("user", service.getUserSimpleData(id));
		model.addObject("message", message);
		return model;
	}
	
	@Override
	public ModelAndView auctionStatsView(RenderRequest request, RenderResponse response, int id){
		return stats.getAuctionStatsView(processing.getDetails(id),ViewType.Profile);
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
		return model;
	}
	
	@Override
	public ModelAndView mySettingsAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(settingsView);
		model.addObject("userPassword", new UserPassword());
		return model;
	}
	
	@Override
	public void changePasswordAction(ActionRequest request, ActionResponse response, UserPassword p) throws ParseException{
		try{
			 long userId = PortalUtil.getUserId(request);
			 UserLocalServiceUtil.updatePassword(userId, p.getPassword(), p.getRepeatedPassword(), false); 
		
			 response.setRenderParameter("message", "Hasło zostało zmienione");
			
		}catch(Exception e){
			 e.printStackTrace();
			 response.setRenderParameter("page", "mySettings");
		}
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
		List<UsernameAndId> users = service.getUsersIdsForLastConversations(PortalUtil.getUserId(request));
		model.addObject("users", users);
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
		return model;
	}
	
	@Override
	public void getSubCategories(ResourceRequest request, ResourceResponse response){
		
		HttpUtil.createResponse(response).
			set("result", service.getSubCategories()).
			prepare();
	}
	
	@Override
	public void createNewAuctionAction(ResourceRequest request, ResourceResponse response, String form, String type) throws ParseException{
		Auction auction = Serializer.fromJson(form, Auction.class);
		long userId = PortalUtil.getUserId(request);
		
		HttpUtil.createResponse(response).
			set("success", type.equals("add") ? service.createUserAuction(userId, auction)
				: service.updateAuction(auction)).
				prepare();
	}
	
	@Override
	public void addGradeAction(ActionRequest request, ActionResponse response, AuctionGrade form) throws ParseException{

		boolean isCreated = service.addAuctionGrade(PortalUtil.getUserId(request), form);
		if(isCreated){
			response.setRenderParameter("message", "Pomyślnie dodano ocenę");
		}
	}

	@Override
	public void submitData(ResourceRequest request, ResourceResponse response){
		saveFile(request, response, Properties.getVideosPath());
	}
	
	@Override
	public void saveImage(ResourceRequest request, ResourceResponse response){
		saveFile(request, response, Properties.getImagesPath());
	}
	
	private void saveFile(ResourceRequest request, ResourceResponse response, String path){
		HttpServletRequest originalRequest = HttpUtil.getOriginal(request);
		String data =  originalRequest.getParameter("data");
		String name =  originalRequest.getParameter("name");
        
		FileUtil.create(data, path + name);
		
		HttpUtil.createResponse(response).
			set("success", true).
			prepare();
	}
	
	@Override
	public void updateImages(ResourceRequest request, ResourceResponse response, long id, String images){		
		
		HttpUtil.createResponse(response).
			set("success", service.updateAuctionImages(images, id)).
			prepare();
	}
	
	@Override
	public void convertVideo(ResourceRequest request, ResourceResponse response, String name, long id){	
		worker.convertVideo(id,name);
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
	
}
