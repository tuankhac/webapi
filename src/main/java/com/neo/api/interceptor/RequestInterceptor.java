package com.neo.api.interceptor;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//import com.neo.api.model.ObjectId;
import com.neo.api.service.TokenService;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

//https://o7planning.org/vi/11689/huong-dan-su-dung-spring-boot-interceptor
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private TokenService tokenService;

	private static Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);
	InetAddress ip;
	String hostname;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		LOGGER.info("\n-------- LogInterception.preHandle --- ");
		LOGGER.info("Request URL: " + request.getRequestURI());

		// System.out.println("\n-------- LogInterception.preHandle --- ");
		// System.out.println("Request url: " + request.getRequestURI());

		String token = request.getHeader("Authorization");

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.sendError(HttpServletResponse.SC_OK, "success");
			return true;
		}

		if (allowRequestWithoutToken(request)) {
			response.setStatus(HttpServletResponse.SC_OK);
			System.out.println("Request url: " + request.getRequestURI() + " by pass");
			// filterChain.doFilter(req, res);
			return true;
		} else {
			if (token == null || !tokenService.isTokenValid(token)) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			} else {
				// ObjectId userId = new ObjectId(tokenService.getUserIdFromToken(token));
				// request.setAttribute("userId", userId);
				// filterChain.doFilter(req, res);
				return true;
			}
		}
		// check url permit
		// neu khong co quyen thi redirect den trang 403
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("\n-------- LogInterception.postHandle --- ");
		System.out.println("Request URL: " + request.getRequestURL());

		// Ở đây, bạn có thể add các attribute vào modelAndView
		// Và sử dụng nó trong các View (jsp,..)
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("\n-------- LogInterception.afterCompletion --- ");
		System.out.println("Request URL: " + request.getRequestURL());
	}

	public boolean allowRequestWithoutToken(HttpServletRequest request) {
		System.out.println("request.getRequestURI():========>"+request.getRequestURI());
		if (request.getRequestURI().contains("/login") || request.getRequestURI().contains("/error")
				|| request.getRequestURI().contains("/api")) {
			return true;
		}
		return false;
	}
}