package com.neo.app.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.neo.app.model.Admin;
import com.neo.app.model.Menu;
import com.neo.app.model.UserRole;
import com.neo.app.service.FuncServiceWS;
import com.neo.app.utils.ConstantParams;

//@Transactional
@Controller
@RequestMapping(value = ConstantParams.ADMIN_PATH)
public class AdminController {

    @Autowired
    FuncServiceWS funcServiceWS;

    // @Autowired
    // LocaleResolver urlLocaleResolver;

    @RequestMapping({ "", "home.html", "index.html", "index" })
    public String index(ModelMap model) {
        return "admin/index";
    }

    // Form Login
    @RequestMapping(value = "login.html", method = RequestMethod.GET)
    public String login(ModelMap model) {
        model.addAttribute("loginForm", new Admin());
        return "admin/login";
    }

    // Form Login
    @RequestMapping(value = "login.html", method = RequestMethod.POST)
    public String login(ModelMap model, @ModelAttribute("loginForm") Admin admin, HttpServletRequest request,
                        HttpServletResponse response, BindingResult result) {

        if (admin.getUsername().trim().length() == 0) {
            result.rejectValue("reasonFail", "error.account.email");
            return "admin/login";
        }

        if (admin.getPassword().trim().length() == 0) {
            result.rejectValue("reasonFail", "error.account.password");
            return "admin/login";
        }
        Admin userBO = (Admin) funcServiceWS.login(new TypeReference<Admin>() {
        }, admin.getUsername().trim(), admin.getPassword().trim());

        if (userBO == null) {
            result.rejectValue("reasonFail", "error.account.usernameorpass");
            return "admin/login";
        }

        if (result.hasErrors()) {
            return "admin/login";
        } else {

            // neu ma co rememberme thi save user to cookie
            if (admin.getRememberme() != null) {
                if (admin.getRememberme().equals("on")) {
                    Cookie userId = new Cookie("userId", admin.getUsername());
                    Cookie userPass = new Cookie("userPass", admin.getPassword());
                    Cookie remember = new Cookie("userRemember", admin.getRememberme());
                    userId.setPath(request.getContextPath());
                    userId.setMaxAge(60 * 60 * 24 * 15); // 15 days
                    userPass.setPath(request.getContextPath());
                    userPass.setMaxAge(60 * 60 * 24 * 15); // 15 days
                    remember.setPath(request.getContextPath());
                    remember.setMaxAge(60 * 60 * 24 * 15); // 15 days
                    response.addCookie(userId);
                    response.addCookie(userPass);
                    response.addCookie(remember);
                }
            }

            // lay du lieu menu duoc phan quyen
            Map<Object, Object> params = new HashMap<>();
            params.put("constr", funcServiceWS.getEnv().getProperty("menu_service").trim());
            params.put("puserid", userBO.getUsername());

            List<Menu> menus = funcServiceWS.refListModel(new TypeReference<List<Menu>>() {
            }, params, userBO.getToken());

            // lay thong tin quyen de check cac link khi request
            params.put("constr", funcServiceWS.getEnv().getProperty("userRolesQuery").trim());
            params.put("puserid", userBO.getUsername());
            List<UserRole> userRoles = funcServiceWS.qryListModel(new TypeReference<List<UserRole>>() {
            }, params, userBO.getToken());

            HttpSession session = request.getSession();
            session.setAttribute("userBO", userBO);
            session.setAttribute("menus", menus);
            session.setAttribute("userRoles", userRoles);
            session.setAttribute("ctxAdmin", ConstantParams.ADMIN_PATH);
            return "redirect:home.html";
        }
    }

    @RequestMapping({ "logout" })
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        // Forces caches to obtain a new copy of the page from the origin server
        // Directs caches not to store the page under any circumstance
        // Causes the proxy cache to see the page as "stale"
        // HTTP 1.0 backward compatibility

        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        Admin userBO = (Admin) session.getAttribute("userBO");
        if (null == userBO) {
            request.setAttribute("Error", "Session has ended.  Please login.");
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue(null);
                cookie.setPath(request.getContextPath());
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        session.removeAttribute("userBO");
        session.removeAttribute("ctxAdmin");
        session.invalidate();
        return "redirect:login.html";
    }
}
