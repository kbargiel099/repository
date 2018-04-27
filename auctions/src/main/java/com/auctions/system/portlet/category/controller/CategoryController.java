package com.auctions.system.portlet.category.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.SearchingFormValidator;
import com.auctions.system.module.auction_processing.controller.AuctionProcessing;
import com.auctions.system.module.profile.controller.ProfileController;
import com.auctions.system.portlet.category.model.SearchingForm;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.category.service.CategoryService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * Portlet implementation class CategoryController
 */
@Controller
@RequestMapping(value = "VIEW")
public class CategoryController {

	private final String defaultView = "category-view";
	private String currentCategory;
	
	@Autowired
	private CategoryService service;
	@Autowired
	private ReloadableResourceBundleMessageSource messageSrc;
	@Autowired
	AuctionProcessing auctionProcessing;
	@Autowired
	ProfileController profile;
	
	@RenderMapping()
	public ModelAndView getSearch(RenderRequest request){
		
		HttpServletRequest originalRequest = getOriginal(request);
		currentCategory = originalRequest.getParameter("name");
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("currentCategory",currentCategory);
		model.addObject("auctions",service.getBestAuctionsByCategory(currentCategory));
		model.addObject("category", getNameFromBundle(currentCategory,themeDisplay.getLocale()));
		model.addObject("subCategories", createSubCategories(currentCategory,themeDisplay.getLocale()));
		return model;
	}
	
	private List<SubCategory> createSubCategories(String categoryName,Locale locale){
		List<SubCategory> subCategories = new ArrayList<SubCategory>();
		
		for(SubCategory sub : service.getSubCategories(categoryName)){
			String nameFromBundle = getNameFromBundle(sub.getName(), locale);
			sub.setName(nameFromBundle);
			subCategories.add(sub);
		}
		return subCategories;
	}
	
	private String getNameFromBundle(String name,Locale locale){
		return messageSrc.getMessage(name , null, locale);
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
	
	@ResourceMapping("getBySubcategory")
	public void searchForMatching(ResourceRequest request, ResourceResponse response,
			@RequestParam("id") int id) throws IOException{		
		Gson gson = new Gson();
		JsonObject res = new JsonObject();
		
		res.addProperty("auctions", gson.toJson(
				service.getAuctionsBySubcategory(id)).toString());

		res.addProperty("success", true);
		response.setContentType("application/json");
		response.getWriter().write(res.toString());
	}
	
	@ResourceMapping("searchText")
	public void searchForMatching(ResourceRequest request, ResourceResponse response,
			@RequestParam("searchingForm") String searchingForm) throws IOException{
		Gson gson = new Gson();
		SearchingForm form = gson.fromJson(searchingForm, SearchingForm.class);
		JsonObject res = new JsonObject();
		SearchingFormValidator.prepare(form);
		
		res.addProperty("auctions", gson.toJson(
				service.getSearchingAuctions(form)).toString());
		res.addProperty("success", true);
		response.setContentType("application/json");
		response.getWriter().write(res.toString());
	}
	
	@ResourceMapping("getVideoName")
	public void getVideoName(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{	
		JsonObject obj = new JsonObject();
		obj.addProperty("name", auctionProcessing.getVideoName(id));
		response.setContentType("application/json");
		response.getWriter().write(obj.toString());
	}
	
	@ResourceMapping("createObservation")
	public void createObservation(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{	
		JsonObject obj = new JsonObject();
		obj.addProperty("success",auctionProcessing
				.createObservation(PortalUtil.getUserId(request), id));
		
		response.setContentType("application/json");
		response.getWriter().write(obj.toString());
	}
	
	@ResourceMapping("removeObservation")
	public void removeObservation(ResourceRequest request, ResourceResponse response,
			@RequestParam("auctionId") int id) throws IOException{	
		JsonObject obj = new JsonObject();
		obj.addProperty("success",auctionProcessing
				.removeObservation(PortalUtil.getUserId(request), id));
		
		response.setContentType("application/json");
		response.getWriter().write(obj.toString());
	}

	private HttpServletRequest getOriginal(PortletRequest request){
		return PortalUtil.getOriginalServletRequest(
				PortalUtil.getHttpServletRequest(request));
	}
}
