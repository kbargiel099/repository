package com.auctions.system.portlet.user_registration.controller;

import java.io.IOException;
import java.net.ConnectException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.auctions.system.module.Properties;
import com.auctions.system.module.client.MailRestClient;
import com.auctions.system.portlet.user_registration.service.RegistrationService;
import com.auctions.system.portlet.user_registration.validator.UserValidator;
import com.auctions.system.portlet.users_management.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

@Controller("userRegistration")
@RequestMapping(value = "VIEW")
public class UserRegistration{

	@Autowired
	RegistrationService service;
	@Autowired
	UserValidator validator;
	
	private final String defaultView = "view";
	private final String createUserSuccessView = "create-user-success";
	
	@InitBinder("user")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}
	
	@RenderMapping
	public ModelAndView defaulView(RenderRequest request, RenderResponse response,
			@RequestParam(value ="message", defaultValue = "") String message) throws Exception{
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		ModelAndView model = new ModelAndView(defaultView);
		model.addObject("user", new User());
		model.addObject("home", themeDisplay.getURLHome());
		model.addObject("message", message);
		return model;
	}
	
	@RenderMapping(params = "page=createUserSuccess")
	public ModelAndView createUserSuccessView(RenderRequest request, RenderResponse response) throws Exception{
		
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
	
		ModelAndView model = new ModelAndView(createUserSuccessView);
		model.addObject("home", themeDisplay.getURLHome());
		
		return model;
	}
	
	@ResourceMapping(value = "checkData")
	public void checkDataAction(ResourceRequest request, ResourceResponse response,
			@RequestParam(value = "user") String userJson) throws IOException{
		Gson gson = new Gson();
		User user = gson.fromJson(userJson, User.class);
		
		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(user, "user");
		ValidationUtils.invokeValidator(validator, user, errors);
		JsonObject validationResponse = createValidationResponse(errors);

		response.setContentType("application/json");
		response.getWriter().write(validationResponse.toString());
	}
	
	private JsonObject createValidationResponse(Errors errors){
		JsonObject result = new JsonObject();
		if(errors.hasErrors()){
			for(ObjectError error : errors.getAllErrors())
				result.addProperty(error.getCode(), error.getDefaultMessage());
		}else
			result.addProperty("success", true);
		
		return result;
	}
	
	@ActionMapping(params = "action=add")
	public void addUserAction(ActionRequest request, ActionResponse response,
		@ModelAttribute("user") @Valid User user, BindingResult result) throws IOException{
	
		boolean isCreated = true;
		
		boolean autoPassword = false;
		long facebookId = 0;
		long companyId = PortalUtil.getCompanyId(request);
		int prefixId = 0;
		int suffixId = 0;
		boolean male = true;
		int birthdayMonth = 1;
		int birthdayDay = 10;
		int birthdayYear = 1980;
		
		String jobTitle = null;
		long[] organizationIds = null;
		long[] roleIds = new long[]{Properties.getUserRoleid(request)};
		long[] userGroupIds = new long[]{};
		boolean sendMail = false;
		
		com.liferay.portal.kernel.model.User newUser;
		try {
			newUser = UserLocalServiceUtil.addUser(
				0, companyId, autoPassword, user.getPassword(), user.getPassword(),
				autoPassword, user.getLogin(), user.getEmail(), facebookId, null,
				LocaleUtil.getDefault(), user.getFirstname(), null, user.getLastname(), prefixId, suffixId, male,
				birthdayMonth, birthdayDay, birthdayYear, jobTitle, null,
				organizationIds, roleIds, userGroupIds, sendMail, new ServiceContext());
			
			newUser.setAgreedToTermsOfUse(true);
			newUser.setReminderQueryQuestion("empty");
			newUser.setReminderQueryAnswer("empty");
			newUser.setPasswordReset(true);
			
			UserLocalServiceUtil.updateUser(newUser);
			
		} catch (PortalException e) {
			isCreated = false;
			e.printStackTrace();
		}
		
		try {
			MailRestClient.sendMail(user.getEmail());
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (isCreated) {
			response.setRenderParameter("page", "createUserSuccess");
		} else {
			response.setRenderParameter("message", "user.registration.error");
		}
	}

}
