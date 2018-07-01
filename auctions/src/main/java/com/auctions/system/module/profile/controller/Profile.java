package com.auctions.system.module.profile.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

public interface Profile {

	@RequestMapping(params = "page=userProfile")
	public ModelAndView getUserProfile(RenderRequest request, RenderResponse response,
			@RequestParam("id") long id) throws Exception;
}
