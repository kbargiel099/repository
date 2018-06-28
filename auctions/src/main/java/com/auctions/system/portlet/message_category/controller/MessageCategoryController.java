package com.auctions.system.portlet.message_category.controller;

import java.io.IOException;

import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.HttpUtil;
import com.auctions.system.module.SearchingFormValidator;
import com.auctions.system.portlet.category.model.SearchingForm;
import com.auctions.system.portlet.message_category.service.MessageCategoryService;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "VIEW")
public class MessageCategoryController{

	private final String defaultView = "view";
	
	@Autowired
	private MessageCategoryService service;
	
	@RenderMapping()
	public ModelAndView defaultView(RenderRequest request){
		
		ModelAndView model = new ModelAndView(defaultView);
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

}
