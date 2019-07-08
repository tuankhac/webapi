package com.neo.app.crud.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
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

@Controller
public class TestController {

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	FuncServiceWS funcServiceWS;

	@RequestMapping(value = "/dt-json-data/arrays.txt", method = RequestMethod.GET)
	//@ResponseBody
	//E:/Project/Code/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/web.server/WEB-INF/classes/
	public void robots(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream in = new ClassPathResource("dt_json_data/arrays.txt",this.getClass().getClassLoader()).getInputStream();
		 IOUtils.copy(in, response.getOutputStream());
//		System.out.println(in.toString() + "anaksh");
		// response.getWriter().write(in.toString() +"alaksjsjs");
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String a(ModelMap model, @RequestParam Map<Object, Object> params, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		Admin userBO = (Admin) session.getAttribute("userBO");
		params.put("constr", "search_menu");
		params.put("pUserId", userBO.getUsername());
		params.put("pUserIp", request.getRemoteHost());
		
		
		StringBuilder result = new StringBuilder("{\"data\":");
		result.append(funcServiceWS.refListModel(new TypeReference<String>() {
		}, params, userBO.getToken()));
		result.append("}");
		model.addAttribute("result", result);
		return "admin/test";
	}
}