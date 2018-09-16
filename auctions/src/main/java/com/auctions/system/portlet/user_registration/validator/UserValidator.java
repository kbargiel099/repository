package com.auctions.system.portlet.user_registration.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.auctions.system.portlet.user_registration.service.RegistrationService;
import com.auctions.system.portlet.users_management.model.User;

@Component("userRegistrationValidator")
public class UserValidator implements Validator {

	@Autowired
	RegistrationService service;
	
	private final String FieldIsRequired = "validation.required";
	private final String ValueIsExist = "validation.value.exist";

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		ValidationUtils.rejectIfEmpty(errors, "login", "login", FieldIsRequired);
		ValidationUtils.rejectIfEmpty(errors, "password", "password", FieldIsRequired);
		ValidationUtils.rejectIfEmpty(errors, "firstname", "firstname", FieldIsRequired);
		ValidationUtils.rejectIfEmpty(errors, "lastname", "lastname", FieldIsRequired);
		ValidationUtils.rejectIfEmpty(errors, "email", "email", FieldIsRequired);
		
		if(service.checkIfEmailExist(user.getEmail())){
			errors.reject("email", ValueIsExist);
		}
		
		if(service.checkIfLoginExist(user.getLogin())){
			errors.reject("login", ValueIsExist);
		}
			
	}
}
