package com.neo.app.crud.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.neo.app.model.Admin;
import com.neo.app.model.Menu;
import com.neo.app.service.FuncServiceWS;
import com.neo.app.utils.ConstantParams;
import com.neo.app.validator.MenuValidator;

//@Transactional
@Controller
@RequestMapping(value = ConstantParams.ADMIN_PATH)
public class CrudController {

	@Autowired
	FuncServiceWS funcServiceWS;

	@RequestMapping(value = "{path}/index.html", method = RequestMethod.GET)
	public String index(ModelMap model, @PathVariable("path") String path, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		model.addAttribute("menuEdit", new Menu());
		return "admin/" + funcServiceWS.getEnv().getProperty(path);
	}

	@RequestMapping(value = "{type}/dt_ajax.html", method = RequestMethod.GET)
	public String getDataTableAjaxObject(ModelMap model, @RequestParam Map<Object, Object> params,
			@PathVariable("type") String type, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		Admin userBO = (Admin) session.getAttribute("userBO");

		params.put("pUserId", userBO.getUsername());
		params.put("pUserIp", request.getRemoteHost());

		StringBuilder result = new StringBuilder("{\"data\":");
		switch (type) {
		case "qry":
			result.append(funcServiceWS.qryListModel(new TypeReference<String>() {
			}, params, userBO.getToken()));
			break;

		case "ref":
			result.append(funcServiceWS.refListModel(new TypeReference<String>() {
			}, params, userBO.getToken()));
			break;
		}
		result.append("}");
		model.addAttribute("result", result);
		return "admin/crud/ajax";

	}

	@RequestMapping(value = "crud_ajax/{type}", method = RequestMethod.POST)
	public String actionVal(ModelMap model, @RequestParam Map<Object, Object> params, @PathVariable("type") String type,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Admin userBO = (Admin) session.getAttribute("userBO");

		params.put("pUserId", userBO.getUsername());
		params.put("pUserIp", request.getRemoteHost());

		StringBuilder result = new StringBuilder();
		switch (type) {
		case "val":
			result.append(funcServiceWS.valPostParam(params, userBO.getToken()));
			break;

		case "update":
			result.append(funcServiceWS.updateParam(params, userBO.getToken()));
			break;
		}

		model.addAttribute("result", result);
		return "admin/" + params.get("crud_path") + "/action-edit-delete";
	}

	@RequestMapping(value = "menu/{type}", method = RequestMethod.POST)
	public String actionValWithValidateServer(ModelMap model, @ModelAttribute("menuEdit") Menu menu,
			@RequestParam Map<Object, Object> params, @PathVariable("type") String type, HttpServletRequest request,
			HttpServletResponse response, HttpSession session,BindingResult errors) {
		Admin userBO = (Admin) session.getAttribute("userBO");

		params.put("pUserId", userBO.getUsername());
		params.put("pUserIp", request.getRemoteHost());
		
		//validate on server here
		MenuValidator valid = new MenuValidator();
		
		StringBuilder result = new StringBuilder();
		switch (type) {
		case "val":
			result.append(funcServiceWS.valPostParam(params, userBO.getToken()));
			break;

		case "update":
			result.append(funcServiceWS.updateParam(params, userBO.getToken()));
			break;
		}

		model.addAttribute("result", result);
		return "admin/" + params.get("crud_path") + "/action-edit-delete";
	}

}