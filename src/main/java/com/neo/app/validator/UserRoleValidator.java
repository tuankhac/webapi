package com.neo.app.validator;

import org.springframework.validation.Errors;

import com.neo.app.model.UserRole;

public class UserRoleValidator implements ObjectValidator {

	public boolean supports(Class<?> arg0) {
		return UserRole.class.equals(arg0);
	}

	public void validate(Object object, Errors errors) {
		UserRole customer = (UserRole) object;
		
	}

}
