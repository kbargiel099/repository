package com.auctions.system.portlet.user_registration.validator;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.auctions.system.portlet.user_registration.service.RegistrationService;
import com.auctions.system.portlet.users_management.model.User;
import com.liferay.portal.kernel.util.LocaleUtil;

@Component("userRegistrationValidator")
public class UserValidator implements Validator {

	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;
	
	@Autowired
	RegistrationService service;
	
	private String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	
	private final String FieldIsRequired = "validation.required";
	private final String ValueIsIncorrect = "validation.incorrect";
	private final String ValueIsExist = "validation.value.exist";

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		Locale locale = LocaleUtil.getDefault();
		
		ValidationUtils.rejectIfEmpty(errors, "login", "login",
				messageSource.getMessage(FieldIsRequired,null,locale));
		ValidationUtils.rejectIfEmpty(errors, "password", "password", 
				messageSource.getMessage(FieldIsRequired,null,locale));
		ValidationUtils.rejectIfEmpty(errors, "firstname", "firstname", 
				messageSource.getMessage(FieldIsRequired,null,locale));
		ValidationUtils.rejectIfEmpty(errors, "lastname", "lastname", 
				messageSource.getMessage(FieldIsRequired,null,locale));
		ValidationUtils.rejectIfEmpty(errors, "email", "email", 
				messageSource.getMessage(FieldIsRequired,null,locale));
		
		if(service.checkIfEmailExist(user.getEmail())){
			errors.reject("email", messageSource.getMessage(ValueIsExist,null,locale));
		}
		
		if(service.checkIfLoginExist(user.getLogin())){
			errors.reject("login", messageSource.getMessage(ValueIsExist,null,locale));
		}
			
	}
}
