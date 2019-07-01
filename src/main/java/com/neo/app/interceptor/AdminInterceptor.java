package com.neo.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.neo.app.resolver.UrlLocaleResolver;
import com.neo.app.utils.ConstantParams;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	// @Autowired
	// UrlLocaleResolver localeResolver;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("userBO") == null) {
			response.sendRedirect(request.getContextPath() + ConstantParams.SLASH + response.getLocale().getLanguage()
					+ ConstantParams.SLASH + ConstantParams.ADMIN_PATH + "login.html");
			return false;
		}

		System.out.println("AdminInterceptor.preHandler()");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("AdminInterceptor.postHandler()");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		System.out.println("AdminInterceptor.afterCompletion()");
	}

}