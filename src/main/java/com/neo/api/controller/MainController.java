package com.neo.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.neo.api.model.Employee;
import com.neo.api.service.ObjectService;

@RestController

public class MainController {

	@Autowired
	private ObjectService objectService;

	@RequestMapping("/")
	public String welcome() {
		return "index";
	}

	// @RequestMapping(value = "/employees", method = RequestMethod.GET, produces =
	// { MediaType.APPLICATION_JSON_VALUE,
	// MediaType.APPLICATION_XML_VALUE })
	// @ResponseBody
	// public List<Map<Object, Object>> getEmployees() {
	// List<Map<Object, Object>> list = objectService.ref(1);
	// return list;
	// }

	// @RequestMapping(value = "/employee/{empNo}", method = RequestMethod.GET, //
	// produces = { MediaType.APPLICATION_JSON_VALUE,
	// MediaType.APPLICATION_XML_VALUE })
	// @ResponseBody
	// public Employee getEmployee(@PathVariable("empNo") String empNo) {
	// return objectService.getEmployee(empNo);
	// }
	//
	// @RequestMapping(value = "/employee", method = RequestMethod.POST, produces =
	// { MediaType.APPLICATION_JSON_VALUE,
	// MediaType.APPLICATION_XML_VALUE })
	// @ResponseBody
	// public Employee addEmployee(@RequestBody Employee emp) {
	// System.out.println("(Service Side) Creating employee: " + emp.getEmpNo());
	// return objectService.addEmployee(emp);
	// }
	//
	// @RequestMapping(value = "/employee", method = RequestMethod.PUT, produces = {
	// MediaType.APPLICATION_JSON_VALUE,
	// MediaType.APPLICATION_XML_VALUE })
	// @ResponseBody
	// public Employee updateEmployee(@RequestBody Employee emp) {
	// System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());
	// return objectService.updateEmployee(emp);
	// }
	//
	// @RequestMapping(value = "/employee/{empNo}", method = RequestMethod.DELETE,
	// produces = {
	// MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	// @ResponseBody
	// public void deleteEmployee(@PathVariable("empNo") String empNo) {
	// System.out.println("(Service Side) Deleting employee: " + empNo);
	// objectService.deleteEmployee(empNo);
	// }

}
