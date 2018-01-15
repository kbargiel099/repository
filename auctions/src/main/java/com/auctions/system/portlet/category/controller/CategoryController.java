package com.auctions.system.portlet.category.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

import com.auctions.system.portlet.category.service.CategoryService;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * Portlet implementation class CategoryController
 */
@Controller
@RequestMapping(value = "VIEW")
public class CategoryController {

	private final String defaultView = "category-view";
	private final String detailsView = "auction-details-view";
	
	@Autowired
	private CategoryService service;
	
	@RequestMapping()
	public ModelAndView getOrder(RenderRequest request){
		
		HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String categoryName = originalRequest.getParameter("name");
		
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("categoryAuctions",service.getBestAuctionsByCategory(categoryName));
		model.addObject("category", getCategoryNameBundle(categoryName));
		return model;
	}
	
	private String getCategoryNameBundle(String category){
		if(category.equals("electronic"))
			return "Elektronika";
		else if(category.equals("motorization"))
			return "Motoryzacja";
		else if(category.equals("clothing"))
			return "Odzież";
		else
			return "";	
	}
	
	@RequestMapping(params = "page=auctionDetails")
	public ModelAndView defaulView(RenderRequest request, RenderResponse response,
			@RequestParam("id") int auctionId) throws Exception{

		//HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		//int auctionId = Integer.parseInt(originalRequest.getParameter("id"));
		
		ModelAndView model = new ModelAndView(detailsView);
		model.addObject("auction",service.getAuctionDetails(auctionId));
		model.addObject("seller", service.getSellerDetails(20156));
		return model;
	}

}
