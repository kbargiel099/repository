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
	private final String detailsView = "auction-details-view";
	
	@Autowired
	private CategoryService service;
	@Autowired
	ReloadableResourceBundleMessageSource messageSrc;
	
	@RequestMapping()
	public ModelAndView getOrder(RenderRequest request){
		
		HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String categoryName = originalRequest.getParameter("name");
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("auctions",service.getBestAuctionsByCategory(categoryName));
		model.addObject("category", getNameFromBundle(categoryName,themeDisplay.getLocale()));
		model.addObject("subCategories", createSubCategories(categoryName,themeDisplay.getLocale()));
		return model;
	}
	
	/*private String getCategoryNameBundle(String category,Locale locale){
		if(category.equals("electronics"))
			return messageSrc.getMessage("electronics", null, locale);
		else if(category.equals("motorization"))
			return messageSrc.getMessage("motorization", null, locale);
		else if(category.equals("clothing"))
			return messageSrc.getMessage("clothing", null, locale);
		else
			return "";	
	}*/
	
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
