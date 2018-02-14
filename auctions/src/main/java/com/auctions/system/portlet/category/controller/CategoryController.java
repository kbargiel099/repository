package com.auctions.system.portlet.category.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.auctions.system.module.auction_processing.controller.AuctionProcessing;
import com.auctions.system.portlet.category.model.SubCategory;
import com.auctions.system.portlet.category.service.CategoryService;
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
	
	@Autowired
	private CategoryService service;
	@Autowired
	private ReloadableResourceBundleMessageSource messageSrc;
	@Autowired
	AuctionProcessing auctionProcessing;
	
	@RenderMapping()
	public ModelAndView getSearch(RenderRequest request){
		
		HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String categoryName = originalRequest.getParameter("name");
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("auctions",service.getBestAuctionsByCategory(categoryName));
		model.addObject("category", getNameFromBundle(categoryName,themeDisplay.getLocale()));
		model.addObject("subCategories", createSubCategories(categoryName,themeDisplay.getLocale()));
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
	
	/*@RenderMapping(params = "page")
	public ModelAndView getOrder(RenderRequest request){
		
		HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String categoryName = originalRequest.getParameter("name");
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("auctions",service.getBestAuctionsByCategory(categoryName));
		model.addObject("category", getNameFromBundle(categoryName,themeDisplay.getLocale()));
		model.addObject("subCategories", createSubCategories(categoryName,themeDisplay.getLocale()));
		return model;
	}*/
	
	@RequestMapping(params = "page=auctionDetails")
	public ModelAndView defaulView(RenderRequest request, RenderResponse response,
			@RequestParam("id") int auctionId) throws Exception{

		//HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		//int auctionId = Integer.parseInt(originalRequest.getParameter("id"));
		
		//ModelAndView model = new ModelAndView(detailsView);
		//model.addObject("auction",service.getAuctionDetails(auctionId));
		//model.addObject("seller", service.getSellerDetails(20156));
		//return model;
		return auctionProcessing.createAuctionDetailsView(auctionId);
	}

}
