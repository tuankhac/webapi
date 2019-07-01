package com.neo.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class DataSourceIntercetor extends HandlerInterceptorAdapter {

	// Request:

	// /publisher/list
	// /advertiser/list
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String contextPath = request.getServletContext().getContextPath();

		// /SpringMVCMultiDS/publisher
		String prefixPublisher = contextPath + "/publisher";

		// /SpringMVCMultiDS/advertiser
		String prefixAdvertiser = contextPath + "/advertiser";

		// /SpringMVCMultiDS/publisher/dashboard
		// /SpringMVCMultiDS/advertiser/dashboard

		String uri = request.getRequestURI();
		System.out.println("URI:" + uri);

		if (uri.startsWith(prefixPublisher)) {
			request.setAttribute("keyDS", "PUBLISHER_DS");
		}

		else if (uri.startsWith(prefixAdvertiser)) {
			request.setAttribute("keyDS", "ADVERTISER_DS");
		}

		return true;
	}

}