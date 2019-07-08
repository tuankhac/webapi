package com.neo.app.validator;

import org.springframework.validation.Errors;

import com.neo.app.model.Menu;

public class MenuValidator implements ObjectValidator {

	public boolean supports(Class<?> arg0) {
		return Menu.class.equals(arg0);
	}

	public void validate(Object object, Errors errors) {
		Menu menu = (Menu) object;
		
		System.out.println(menu.getId());
	}

}
