package com.neo.app.crud.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.neo.app.model.Admin;
import com.neo.app.service.FuncServiceWS;
import com.neo.app.utils.ConstantParams;

//@Transactional
@Controller
@RequestMapping(value = ConstantParams.ADMIN_PATH)
public class CrudController {

	@Autowired
	FuncServiceWS funcServiceWS;

	@RequestMapping(value = "/{path}/index.html", method = RequestMethod.GET)
	public String index(ModelMap model, @PathVariable("path") String path, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		// Admin userBO = (Admin) session.getAttribute("userBO");
		// List<Menu> list = funcServiceWS.refListModel(new TypeReference<List<Menu>>()
		// {
		// }, params, userBO.getToken());
		// model.addAttribute("list", list);

		return "admin/" + funcServiceWS.getEnv().getProperty(path) + "/index";

	}

	@RequestMapping(value = "/{type}/dt_ajax.html", method = RequestMethod.GET)
	@ResponseBody
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
		System.out.println(result);
		return result.toString();

	}

}