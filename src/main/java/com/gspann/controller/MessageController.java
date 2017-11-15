package com.gspann.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gspann.pojo.Message;

@RestController
public class MessageController {

	@RequestMapping(value = "/sendMsg/", method = RequestMethod.POST)
	public ResponseEntity<Void> sendMsg(@RequestBody Message message) {
		System.out.println("inside method sendMsg with message : " + message.getMessage() + " :: mobile : " + message.getMobile());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
