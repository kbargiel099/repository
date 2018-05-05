package com.auctions.system.portlet.user_profile.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.auction_processing.controller.AuctionProcessing;
import com.auctions.system.module.file_converter.FileUtil;
import com.auctions.system.module.file_converter.Worker;
import com.auctions.system.module.profile.controller.ProfileController;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionGrade;
import com.auctions.system.portlet.user_profile.model.UserPassword;
import com.auctions.system.portlet.user_profile.service.UserProfileService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class UserProfileController {

	private final String defaultView = "view";
	private final String createAuctionView = "create-auction"; 
	private final String addGradeView = "add-grade";
	private final String settingsView = "settings";
	private final String userBoughtView = "user-bought";
	private final String userSoldView = "user-sold";
	private final String userAuctionsView = "user-auctions";
	private final String userObservationView = "user-observation";
	private final String managementView = "management";
	
	@Autowired
	private UserProfileService service;
	@Autowired
	AuctionProcessing auctionProcessing;
	@Autowired
	ProfileController profile;
	
	@Autowired
	ReloadableResourceBundleMessageSource messageSrc;
	
	Worker worker = new Worker();
	
	@InitBinder("newAuction")
	private void initBinderAuction(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}
	
	@InitBinder("auctionGrade")
	private void initBinderGrade(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}
	
	@RenderMapping
	public ModelAndView defaulView(RenderRequest request, RenderResponse response,
			@RequestParam(value ="message", defaultValue = "") String message) throws Exception{
		
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("message", message);
		return model;
	}
	
	@RequestMapping(params = "page=getBought")
	public ModelAndView getBoughtAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(userBoughtView);
		
		List<AuctionPresenter> userBoughtSubjects = service.getUserBoughtSubjects(
				PortalUtil.getUserId(request));
				
		model.addObject("auctions", userBoughtSubjects);
		return model;
	}
	
	@RequestMapping(params = "page=getSold")
	public ModelAndView getSoldAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(userSoldView);
		return model;
	}
	
	@RequestMapping(params = "page=mySettings")
	public ModelAndView mySettingsAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(settingsView);
		model.addObject("userPassword", new UserPassword());
		return model;
	}
	
	@ActionMapping(params = "action=changePassword")
	public void changePasswordAction(ActionRequest request, ActionResponse response,
			@ModelAttribute("userPassword") UserPassword p) throws ParseException{
		try{
			 long userId = PortalUtil.getUserId(request);
			 UserLocalServiceUtil.updatePassword(userId, p.getPassword(), p.getRepeatedPassword(), false); 
		
			 response.setRenderParameter("message", "Hasło zostało zmienione");
			
		}catch(Exception e){
			 e.printStackTrace();
			 response.setRenderParameter("page", "mySettings");
		}
	}
	
	@RequestMapping(params = "page=myAuctions")
	public ModelAndView userAuctionsAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(userAuctionsView);
		model.addObject("auctions", service.getUserAuctions(
				PortalUtil.getUserId(request)));
		return model;
	}
	
	@RequestMapping(params = "page=observations")
	public ModelAndView userObservationAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(userObservationView);
		model.addObject("auctions", service.getUserObservation(
				PortalUtil.getUserId(request)));
		return model;
	}
	
	@RequestMapping(params = "page=management")
	public ModelAndView createManagementView(RenderRequest request, RenderResponse response,
			@RequestParam("auctionId") long id){
		ModelAndView model = new ModelAndView(managementView);
		model.addObject("auctionId", id);
		return model;
	}
	
	@RenderMapping(params = "page=createNewAuction")
	public ModelAndView createNewAuctionRender(RenderRequest request, RenderResponse response){
		
		ModelAndView model = new ModelAndView(createAuctionView);
		model.addObject("newAuction", new Auction());
		model.addObject("categories", service.getCategories());
		model.addObject("auctionTypes", service.getAuctionTypes());
		return model;
	}
	
	@RequestMapping(params = "page=addGrade")
	public ModelAndView addGradeAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(addGradeView);
		model.addObject("auctionGrade", new AuctionGrade());
		return model;
	}
	
	@ResourceMapping(value = "getSubCategories")
	public void getSubCategories(ResourceRequest request, ResourceResponse response) throws IOException{
		Gson gson = new Gson();
		Locale locale = PortalUtil.getLocale(request);
		String result = gson.toJson(createSubCategories(locale));
		response.setContentType("application/json");
		response.getWriter().write(result);
	}
	
	private List<SubCategory> createSubCategories(Locale locale){
		List<SubCategory> subCategories = service.getSubCategories();
		for(SubCategory sub : subCategories){
			String nameBundle = messageSrc.getMessage(sub.getName() , null, locale);
			sub.setName(nameBundle);
		}
		return subCategories;
	}
	
	@ActionMapping(params = "action=createNewAuction")
	public void createNewAuctionAction(ActionRequest request, ActionResponse response,
			@ModelAttribute("newAuction") Auction auction) throws ParseException{
		String videoName = auction.getVideoName();
		final boolean hasVideo = videoName.isEmpty() ? false : true;	
		
		worker.createFiles(auction, hasVideo);
		
		long userId = PortalUtil.getUserId(request);
		boolean isCreated = service.createUserAuction(userId, auction);
		if(isCreated){
			response.setRenderParameter("message", "Pomyślnie utworzono aukcję");
		}
	}
	
	@ActionMapping(params = "action=addGrade")
	public void addGradeAction(ActionRequest request, ActionResponse response,
			@ModelAttribute("auctionGrade") AuctionGrade form) throws ParseException{
		long userId = PortalUtil.getUserId(request);
		boolean isCreated = service.addAuctionGrade(userId, form);
		if(isCreated){
			response.setRenderParameter("message", "Pomyślnie dodano ocenę");
		}
	}
	
	@ResourceMapping(value = "createAuctionVideo")
	public void createAuctionVideo(ResourceRequest request, ResourceResponse response) throws IOException{
		HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		long id =  Long.parseLong(originalRequest.getParameter("id"));
		String name =  originalRequest.getParameter("name");
		
		JsonObject result = new JsonObject();
		result.addProperty("success", service.createAuctionVideo(id, name));
		response.setContentType("application/json");
		response.getWriter().write(result.toString());
	}

	@ResourceMapping(value = "submitData")
	public void submitData(ResourceRequest request, ResourceResponse response) throws IOException{
		HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String data =  originalRequest.getParameter("data");
		String name =  originalRequest.getParameter("name");
        
		FileUtil.createVideo(data, name);
		JsonObject result = new JsonObject();
		result.addProperty("success", true);
		response.setContentType("application/json");
		response.getWriter().write(result.toString());
	}
	
	@RequestMapping(params = "page=auctionDetails")
	public ModelAndView getAuctionDetails(RenderRequest request, RenderResponse response,
			@RequestParam("id") long id) throws Exception{
		long userId = PortalUtil.getUserId(request);
		return auctionProcessing.createAuctionDetailsView(id,userId);
	}
	
	@RequestMapping(params = "page=userProfile")
	public ModelAndView getUserProfile(RenderRequest request, RenderResponse response,
			@RequestParam("id") long id) throws Exception{
		
		return profile.createUserProfileView(id);
	}
	
	@ResourceMapping("getVideoName")
	public void getVideoName(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{	
		auctionProcessing.getVideoName(id, response);
	}
	
	@ResourceMapping("convertVideo")
	public void getVideoName(ResourceRequest request, ResourceResponse response,
			@RequestParam("videoName") String name) throws IOException{	
		worker.convertVideoToWebm(name);
	}
	
	@ResourceMapping("createObservation")
	public void createObservation(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{	
		auctionProcessing.createObservation(PortalUtil.getUserId(request), id, response);
	}
	
	@ResourceMapping("removeObservation")
	public void removeObservation(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{	
		auctionProcessing.removeObservation(PortalUtil.getUserId(request), id, response);
	}
	
	@ResourceMapping("getAllOffers")
	public void getAllOffers(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{	
		auctionProcessing.getAllOffers(id, response);
	}

}
