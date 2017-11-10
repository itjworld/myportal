package com.gspann.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gspann.entities.Users;
import com.gspann.service.UserService;

/**
 * @author Ashish Jaiswal
 *
 */
@RestController
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/addUser/", method = RequestMethod.POST)
	public ResponseEntity<String> createEmployee(@RequestBody Users users) {
		boolean result=userService.isExistUser(users.getUsername());
		if(result) {
			return new ResponseEntity<String>("FOUND",HttpStatus.FOUND);
		}
		userService.createUser(users.getUsername(), users.getPassword());
		return new ResponseEntity<String>("CREATED",HttpStatus.CREATED);
	}

}
