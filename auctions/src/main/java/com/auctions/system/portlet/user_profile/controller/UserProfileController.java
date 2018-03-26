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

import com.auctions.system.module.Properties;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.user_profile.model.Auction;
import com.auctions.system.portlet.user_profile.model.AuctionGrade;
import com.auctions.system.portlet.user_profile.model.UserProfileAuction;
import com.auctions.system.portlet.user_profile.service.UserProfileService;
import com.google.gson.Gson;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * Portlet implementation class UserProfileController
 */

@Controller
@RequestMapping("VIEW")
public class UserProfileController {

	private final String defaultView = "view";
	private final String createAuctionView = "create-auction"; 
	private final String addGradeView = "add-grade";
	private final String detailsView = "details"; 

	
	@Autowired
	private UserProfileService service;
	
	@Autowired
	ReloadableResourceBundleMessageSource messageSrc;
	
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
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("boughtView", true);
		
		List<UserProfileAuction> userBoughtSubjects = service.getUserBoughtSubjects(
				PortalUtil.getUserId(request));
				
		model.addObject("subjects", userBoughtSubjects);
		model.addObject("path", Properties.getImagesPath());
		return model;
	}
	
	@RequestMapping(params = "page=getSold")
	public ModelAndView getSoldAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("soldView", true);
		return model;
	}
	
	@RequestMapping(params = "page=mySettings")
	public ModelAndView mySettingsAction(RenderRequest request, RenderResponse response){
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("mySettingsView", true);
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
	
	@ResourceMapping(value = "getImage")
	public void getImageAction(ResourceRequest request, ResourceResponse response) throws IOException{
		//ModelAndView model = new ModelAndView(defaultView);
		HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String file =  originalRequest.getParameter("image");
		
		response.getWriter().write("odebrano obrazek " + file);
		//service.createImage(file);
		//model.addObject("mySettingsView", true);
		//return model;
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



}
