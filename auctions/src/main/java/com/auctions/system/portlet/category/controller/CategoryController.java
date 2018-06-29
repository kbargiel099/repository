package com.auctions.system.portlet.category.controller;

import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.HttpUtil;
import com.auctions.system.module.SearchingFormValidator;
import com.auctions.system.module.auction_processing.controller.AuctionProcessing;
import com.auctions.system.module.auction_processing.service.AuctionProcessingService;
import com.auctions.system.module.profile.service.ProfileService;
import com.auctions.system.portlet.category.model.SearchingForm;
import com.auctions.system.portlet.category.service.CategoryService;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "VIEW")
public class CategoryController implements AuctionProcessing{

	private final String defaultView = "category-view";
	private String currentCategory;
	
	@Autowired
	private CategoryService service;
	
	@Autowired
	private AuctionProcessingService processService;
	
	@Autowired
	private ProfileService profileService;
	
	@RenderMapping()
	public ModelAndView getSearch(RenderRequest request){
		HttpServletRequest originalRequest = HttpUtil.getOriginal(request);
		currentCategory = originalRequest.getParameter("name");
		
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("currentCategory",currentCategory);
		model.addObject("auctions",service.getBestAuctionsByCategory(currentCategory));
		model.addObject("subCategories", service.getSubCategories(currentCategory));
		return model;
	}
	
	@ResourceMapping("getBySubcategory")
	public void searchForMatching(ResourceRequest request, ResourceResponse response,
			@RequestParam("id") int id) throws IOException{		
		
		HttpUtil.createResponse(response).
			set("auctions", service.getAuctionsBySubcategory(id)).
			set("success", true).
			prepare();
	}
	
	@ResourceMapping("searchText")
	public void searchForMatching(ResourceRequest request, ResourceResponse response,
			@RequestParam("searchingForm") String searchingForm) throws IOException{
		Gson gson = new Gson();
		SearchingForm form = gson.fromJson(searchingForm, SearchingForm.class);
		SearchingFormValidator.prepare(form);
		
		HttpUtil.createResponse(response).
			set("auctions", service.getSearchingAuctions(form)).
			set("success", true).
			prepare();
	}

	@Override
	public ProfileService getProfileService() {
		return profileService;
	}

	@Override
	public AuctionProcessingService getService() {
		return processService;
	}

}
