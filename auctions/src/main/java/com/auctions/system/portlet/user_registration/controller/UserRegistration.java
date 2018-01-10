package com.auctions.system.portlet.user_registration.controller;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.auctions.system.portlet.user_registration.service.RegistrationService;
import com.auctions.system.portlet.users_management.model.User;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

/**
 * Portlet implementation class UserRegistration
 */
@Controller("userRegistration")
@RequestMapping(value = "VIEW")
public class UserRegistration{

	@Autowired
	RegistrationService service;
	
	private final String defaultView = "view";
	private final String home = "/home";
	
	@RenderMapping
	public ModelAndView defaulView(RenderRequest request, RenderResponse response) throws Exception{
		
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("user", new User());
		model.addObject("home", home);
		
		//com.liferay.portal.kernel.model.User user = UserLocalServiceUtil.getUser(65101);
		//user.setPasswordReset(false);
		//UserLocalServiceUtil.updateUser(user);
		
		return model;
	}
	
	@ActionMapping(params = "action=add")
	public void addUserAction(ActionRequest request, ActionResponse response,
		@ModelAttribute("user") @Valid User user, BindingResult result) throws IOException, PortalException{

		//if (result.hasErrors()){
		//	response.setRenderParameter("page", "add");
		//	return;
		//}
	
		//boolean isAdded = service.createUser(user,false);
		//com.liferay.portal.kernel.model.User user = UserLocalServiceUtil.createUser(userId)
		
		
		boolean autoPassword = false;
		long facebookId = 0;
		int prefixId = 0;
		int suffixId = 0;
		boolean male = true;
		int birthdayMonth = 2;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		String jobTitle = "jobtitle";
		long[] organizationIds = null;
		long[] roleIds = new long[]{20126};
		long[] userGroupIds = new long[]{};
		boolean sendMail = false;
		
		UserLocalServiceUtil.addUser(
			0, 20116, autoPassword, user.getPassword(), user.getPassword(),
			autoPassword, user.getFirstname(), user.getEmail(), facebookId, null,
			LocaleUtil.getDefault(), user.getFirstname(), null, user.getLastname(), prefixId, suffixId, male,
			birthdayMonth, birthdayDay, birthdayYear, jobTitle, null,
			organizationIds, roleIds, userGroupIds, sendMail, new ServiceContext());
		
	}

}
