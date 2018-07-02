package com.auctions.system.portlet.category.controller;

import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
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
import com.auctions.system.module.auction_processing.controller.Processing;
import com.auctions.system.portlet.category.model.AuctionDetails;
import com.auctions.system.portlet.category.model.SearchingForm;
import com.auctions.system.portlet.category.service.CategoryService;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "VIEW")
public class CategoryController implements Processing{

	private final String defaultView = "category-view";
	private String currentCategory;
	
	@Autowired
	private CategoryService service;
	
	@Autowired
	Processing processing;
	
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
	public ModelAndView detailsView(RenderRequest request, RenderResponse response, String message, long id)
			throws Exception {
		return processing.detailsView(request, response, message, id);
	}

	@Override
	public ModelAndView confirmPurchaseView(RenderRequest request, RenderResponse response, long id, long sellerId,
			String name, long price, int quantity, String endDate) throws Exception {
		return processing.confirmPurchaseView(request, response, id, sellerId, name, price, quantity, endDate);
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
