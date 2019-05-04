package com.neo.api.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.neo.api.utils.ConstantParams;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandlerController {

	@ExceptionHandler(EzException.class)
	public ModelAndView handleCustomException(EzException ex) {
		ModelAndView errorView = new ModelAndView();

		errorView.setViewName(ConstantParams.ERROR_VIEW_NAME);
		errorView.addObject("errCode", ex.getCode());
		errorView.addObject("errMsg", ex.getMessage());
		return errorView;

	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handleError404(HttpServletRequest request, Exception e) {
		ModelAndView mav = new ModelAndView("404");
		return mav;
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ModelAndView handleResourceNotFound(HttpServletRequest request, Exception e, ResourceNotFoundException ex) {
		ModelAndView mav = new ModelAndView("404_resource_not_found");
		mav.addObject("errMsg", ex.getMessage());
		return mav;
	}
}