package com.neo.app.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.neo.app.model.Menu;
import com.neo.app.model.UserRole;
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

		/*
		 * kiem tra url co duoc quyen truy cap hay khong, neu ko thi reject
		 */
		// System.out.println("request.getRequestURI()" + request.getRequestURI() + "|"
		// + request.getRequestURL() + "|"
		// + request.getPathInfo());
		Long menuId = -1L;

		@SuppressWarnings("unchecked")
		List<UserRole> userRoles = (List<UserRole>) session.getAttribute("userRoles");

		@SuppressWarnings("unchecked")
		List<Menu> menus = (List<Menu>) request.getSession().getAttribute("menus");
		String path = request.getRequestURI().substring(request.getContextPath().length());
		String pathLang = request.getRequestURI()
				.substring(request.getContextPath().length() + request.getLocale().getLanguage().length());
		// System.out.println("path" + path + "| "+pathLang);
		if (menus != null) {
			for (Menu menu : menus) {
				if (menu.getDetail_file() != null && menu.getDetail_file().equals(ConstantParams.ADMIN_PATH)) {
					continue;
				}
				if (menu.getDetail_file() != null && !"".equals(menu.getDetail_file())) {
					if (path.contains(menu.getDetail_file()) || pathLang.contains(menu.getDetail_file())) {
						menuId = menu.getId();
						break;
					}
				}
			}
		}

		boolean isGranted = false;
		if (userRoles != null && menuId > 0L) {
			for (UserRole userRole : userRoles) {
				if (userRole.getMenu_id().longValue() == menuId.longValue()) {
					isGranted = true;
					break;
				}
			}
		}
		if (!isGranted) {
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