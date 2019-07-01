package com.neo.app.crud.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@Autowired
	private ResourceLoader resourceLoader;

	@RequestMapping(value = "/dt-json-data/arrays.txt", method = RequestMethod.GET)
	@ResponseBody
	//E:/Project/Code/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/web.server/WEB-INF/classes/
	public void robots(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream in = new ClassPathResource("dt_json_data/arrays.txt",this.getClass().getClassLoader()).getInputStream();
		 IOUtils.copy(in, response.getOutputStream());
//		System.out.println(in.toString() + "anaksh");
		// response.getWriter().write(in.toString() +"alaksjsjs");
	}
}