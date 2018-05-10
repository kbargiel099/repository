package com.auctions.system.portlet.category.controller;

import java.io.IOException;

import javax.portlet.PortletRequest;
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

import com.auctions.system.module.SearchingFormValidator;
import com.auctions.system.module.auction_processing.controller.AuctionProcessing;
import com.auctions.system.portlet.category.model.SearchingForm;
import com.auctions.system.portlet.category.service.CategoryService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.liferay.portal.kernel.util.PortalUtil;

@Controller
@RequestMapping(value = "VIEW")
public class CategoryController extends AuctionProcessing{

	private final String defaultView = "category-view";
	private String currentCategory;
	
	@Autowired
	private CategoryService service;
	
	@RenderMapping()
	public ModelAndView getSearch(RenderRequest request){
		HttpServletRequest originalRequest = getOriginal(request);
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

	private HttpServletRequest getOriginal(PortletRequest request){
		return PortalUtil.getOriginalServletRequest(
				PortalUtil.getHttpServletRequest(request));
	}
}
