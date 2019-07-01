package com.neo.app.admin.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neo.app.model.Admin;
import com.neo.app.service.FuncServiceWS;
import com.neo.app.utils.ConstantParams;

@Controller
@RequestMapping(value = "/{locale:en|fr|vi}/")
public class LanguageController {

	@Autowired
	FuncServiceWS funcServiceWS;

	@RequestMapping(value = { ConstantParams.ADMIN_PATH, "neo/index.html", "neo/index",
			"neo/index.htm" }, method = RequestMethod.GET)
	public String index(Model model) {
		return "admin/index";
	}

	@RequestMapping(value = "neo/login.html", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("loginForm", new Admin());
		return "admin/login";
	}
	
	
	/*
	 * For Datatable
	 */
	@RequestMapping(value = "/i18n/dataTable/fr.json", method = RequestMethod.GET)
	@ResponseBody
	public void robots(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream in = new ClassPathResource("i18n/dataTable/fr.json",this.getClass().getClassLoader()).getInputStream();
		 IOUtils.copy(in, response.getOutputStream());
	}
	
	@RequestMapping(value = "/i18n/dataTable/vi.json", method = RequestMethod.GET)
	@ResponseBody
	public void dataTable_VI(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream in = new ClassPathResource("i18n/dataTable/vi.json",this.getClass().getClassLoader()).getInputStream();
		 IOUtils.copy(in, response.getOutputStream());
	}
	
	@RequestMapping(value = "/i18n/dataTable/en.json", method = RequestMethod.GET)
	@ResponseBody
	public void dataTable_EN(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream in = new ClassPathResource("i18n/dataTable/en.json",this.getClass().getClassLoader()).getInputStream();
		 IOUtils.copy(in, response.getOutputStream());
	}
}
