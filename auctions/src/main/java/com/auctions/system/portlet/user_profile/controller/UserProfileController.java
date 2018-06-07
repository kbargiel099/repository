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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.HttpUtil;
import com.auctions.system.module.Properties;
import com.auctions.system.module.auction_processing.controller.AuctionProcessing;
import com.auctions.system.module.file_converter.FileUtil;
import com.auctions.system.module.file_converter.Worker;
import com.auctions.system.portlet.home_page.model.AuctionPresenter;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionGrade;
import com.auctions.system.portlet.user_profile.model.UserPassword;
import com.auctions.system.portlet.user_profile.service.UserProfileService;
import com.google.gson.Gson;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class UserProfileController extends AuctionProcessing{

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
	
	@Autowired
	private UserProfileService service;
	
	@Autowired
	Worker worker;
	
	@InitBinder("auctionGrade")
	private void initBinderGrade(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}
	
	@RenderMapping
	public ModelAndView defaultView(RenderRequest request, RenderResponse response,
			@RequestParam(value ="message", defaultValue = "") String message) throws Exception{
		long id = PortalUtil.getUserId(request);
		
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("user", service.getUserSimpleData(id));
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
	
	@RequestMapping(params = "page=addVideo")
	public ModelAndView addVideoView(RenderRequest request, RenderResponse response,
			@RequestParam("id") long id){
		ModelAndView model = new ModelAndView(addVideoView);
		model.addObject("auctionId", id);
		return model;
	}
	
	@RequestMapping(params = "page=addImages")
	public ModelAndView addImagesView(RenderRequest request, RenderResponse response,
			@RequestParam("id") long id){
		ModelAndView model = new ModelAndView(addImagesView);
		model.addObject("model", service.getAuctionImages(id));
		return model;
	}
	
	@RenderMapping(params = "page=createNewAuction")
	public ModelAndView createNewAuctionRender(RenderRequest request, RenderResponse response){
		
		ModelAndView model = new ModelAndView(createEditAuctionView);
		model.addObject("auction", new Auction());
		model.addObject("type", "add");
		model.addObject("categories", service.getCategories());
		model.addObject("auctionTypes", service.getAuctionTypes());
		return model;
	}
	
	@RenderMapping(params = "page=editAuction")
	public ModelAndView editAuctionRender(RenderRequest request, RenderResponse response,
			@RequestParam("id") long id){
		Auction a = service.getAuctionData(id);
		
		ModelAndView model = new ModelAndView(createEditAuctionView);
		model.addObject("auction",a);
		model.addObject("type", "edit");
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
		
		HttpUtil.createResponse(response).
			set("result", service.getSubCategories()).
			prepare();
	}
	
	@ResourceMapping("submitAuction")
	public void createNewAuctionAction(ResourceRequest request, ResourceResponse response,
			@RequestParam("newAuction") String form, @RequestParam("type") String type) throws ParseException, IOException{
		Auction auction = new Gson().fromJson(form, Auction.class);
		long userId = PortalUtil.getUserId(request);
		
		HttpUtil.createResponse(response).
			set("success", type.equals("add") ? service.createUserAuction(userId, auction)
				: service.updateAuction(auction)).
				prepare();
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

	@ResourceMapping(value = "submitData")
	public void submitData(ResourceRequest request, ResourceResponse response) throws IOException{
		HttpServletRequest originalRequest = HttpUtil.getOriginal(request);
		String data =  originalRequest.getParameter("data");
		String name =  originalRequest.getParameter("name");
        
		FileUtil.create(data,Properties.getVideosPath() + name);
		
		HttpUtil.createResponse(response).
			set("success", true).
			prepare();
	}
	
	@ResourceMapping(value = "saveImage")
	public void saveImage(ResourceRequest request, ResourceResponse response) throws IOException{
		HttpServletRequest originalRequest = HttpUtil.getOriginal(request);
		String data =  originalRequest.getParameter("data");
		String name =  originalRequest.getParameter("name");
        
		FileUtil.create(data,Properties.getImagesPath() + name);
		
		HttpUtil.createResponse(response).
			set("success", true).
			prepare();
	}
	
	@ResourceMapping("convertVideo")
	public void convertVideo(ResourceRequest request, ResourceResponse response,
			@RequestParam("videoName") String name,
			@RequestParam("auctionId") long id) throws IOException{	
		worker.convertVideo(id,name);
	}
	
	@ResourceMapping("checkConversionStatus")
	public void checkConversionStatus(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") long id) throws IOException{		
		
		HttpUtil.createResponse(response).
			set("progress", worker.checkProgress(id)).
			prepare();
	}
	
	@ResourceMapping(value = "getTechnicalData")
	public void getTechnicalData(ResourceRequest request, ResourceResponse response,
			@RequestParam("id") int id) throws IOException{
		
		HttpUtil.createResponse(response).
			set("data", service.getTechnicalData(id)).
			set("success",true).
			prepare();
	}

}
