package com.gspann.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gspann.beans.Message;
import com.gspann.entities.MobileDetail;
import com.gspann.service.MobileDetailService;

@RestController
public class MessageController {

	@Autowired
	private MobileDetailService mobileDetailService;

	@RequestMapping(value = "/info/", method = RequestMethod.GET)
	public ResponseEntity<Message> getDetails(@RequestParam Integer page, @RequestParam Integer pageSize,
			@RequestParam Integer sort, @RequestParam String sortBy, @RequestParam(required=false) String searchParam) {
		System.out.println("inside method getDetails with page : " + page + " : pageSize : " + pageSize + " : sort : "
				+ sort + " : sortBy : " + sortBy + " : search : " + searchParam);
		Message message = mobileDetailService.getDetails(page, pageSize, sort, sortBy, searchParam);
		System.out.println("details : " + message);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@RequestMapping(value = "/sendMsg/", method = RequestMethod.POST)
	public ResponseEntity<Void> sendMsg(@RequestBody com.gspann.pojo.Message message) {
		System.out.println(
				"inside method sendMsg with message : " + message.getMessage() + " :: mobile : " + message.getMobile());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/addDesc/", method = RequestMethod.POST)
	public ResponseEntity<Void> addDesc(@RequestBody MobileDetail desc) {
		System.out
				.println("inside method addDesc with details : " + desc.getName() + " :: mobile : " + desc.getMobile());
		mobileDetailService.saveDesc(desc);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/info/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable long id) {
		System.out.println("inside method delete with id : " + id);
		mobileDetailService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/info/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody MobileDetail desc) {
		System.out
				.println("inside method addDesc with details : " + desc.getName() + " :: mobile : " + desc.getMobile());
		mobileDetailService.updateDesc(desc);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
