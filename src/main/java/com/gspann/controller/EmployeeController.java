package com.gspann.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gspann.beans.Message;
import com.gspann.entities.Employee;
import com.gspann.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/employee/", method = RequestMethod.GET)
	public ResponseEntity<Message> listAllEmployees(@RequestBody @RequestParam(name = "page", required = true) Integer page,
			@RequestParam(name = "pageSize", required = true) Integer pageSize,
			@RequestParam(name = "sort", required = true) Integer sort,
			@RequestParam(name = "sortBy", required = true) String sortBy) {
		Message message = employeeService.getAllEmployees(page, pageSize, sort, sortBy);
		if (null == message || null == message.getResults() || message.getResults().isEmpty()) {
			return new ResponseEntity<Message>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/", method = RequestMethod.POST)
	public ResponseEntity<Void> createEmployee(@RequestBody Employee employee) {
		System.out.println("Creating Employee " + employee.getName());
		employeeService.createEmployee(employee);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
		System.out.println("Updating Employee " + id);

		if (employee.getId() == 0) {
			System.out.println("Employee with id " + id + " not found");
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		employeeService.updateEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Employee with id " + id);
		if (id == 0) {
			System.out.println("Unable to delete. Employee with id " + id + " not found");
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		employeeService.deleteEmployee(id);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

}
