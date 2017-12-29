package com.auctions.system.portlet.users_management.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.auctions.system.portlet.users_management.model.User;

@Component("userValidator")
public class UserValidator implements Validator {

	//private final static String EMPLOYEES_NUMBER = "emplNumber";

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;

		ValidationUtils.rejectIfEmpty(errors, "login", "Pole jest puste");
		ValidationUtils.rejectIfEmpty(errors, "password", "Pole jest puste");
	}
}
