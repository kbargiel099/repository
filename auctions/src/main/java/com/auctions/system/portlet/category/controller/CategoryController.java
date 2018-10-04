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
import org.springframework.web.portlet.ModelAndView;

import com.auctions.system.module.HttpUtil;
import com.auctions.system.module.Serializer;
import com.auctions.system.module.Validator;
import com.auctions.system.module.auction_process.controller.AuctionProcess;
import com.auctions.system.portlet.category.model.AuctionDetails;
import com.auctions.system.portlet.category.model.SearchingForm;
import com.auctions.system.portlet.category.service.CategoryService;

@Controller
@RequestMapping(value = "VIEW")
public class CategoryController implements Category{

	private final String defaultView = "category-view";
	private final String defaultCategory = "electronics";
	
	@Autowired
	CategoryService service;
	
	@Autowired
	AuctionProcess processing;
	
	@Override
	public ModelAndView getSearch(RenderRequest request){
		HttpServletRequest originalRequest = HttpUtil.getOriginal(request);
		String category = originalRequest.getParameter("name");
		
		if (category == null) {
			category = defaultCategory;
		}
		
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("currentCategory", category);
		model.addObject("auctions",service.getBestAuctionsByCategory(category));
		model.addObject("subCategories", service.getSubCategories(category));
		return model;
	}
	
	@Override
	public void searchForMatching(ResourceRequest request, ResourceResponse response, int id){		
		
		HttpUtil.createResponse(response).
			set("auctions", service.getAuctionsBySubcategory(id)).
			set("success", true).
			prepare();
	}
	
	@Override
	public void searchForMatching(ResourceRequest request, ResourceResponse response, String searchingForm){
		SearchingForm form = Serializer.fromJson(searchingForm, SearchingForm.class);
		
		HttpUtil.createResponse(response).
			set("auctions", service.getSearchingAuctions(Validator.prepare(form))).
			set("success", true).
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
	public void getAllOffers(ResourceRequest request, ResourceResponse response, int auctionId) throws IOException {
		processing.getAllOffers(request, response, auctionId);
	}

	@Override
	public void getVideoName(ResourceRequest request, ResourceResponse response, long auctionId) throws IOException {
		processing.getVideoName(request, response, auctionId);
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
	public ModelAndView getUserProfile(RenderRequest request, RenderResponse response, long id) throws Exception {
		return processing.getUserProfile(request, response, id);
	}
	
	@Override
	public AuctionDetails getDetails(long id) {
		return processing.getDetails(id);
	}

}
