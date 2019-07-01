package com.neo.app.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/*
 * design pattern factory
 */
public interface ObjectValidator extends Validator {
	boolean supports(Class<?> clazz);

	void validate(Object target, Errors errors);
}
