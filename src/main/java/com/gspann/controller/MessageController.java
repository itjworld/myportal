package com.gspann.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gspann.entities.MobileDetail;
import com.gspann.pojo.Message;
import com.gspann.service.MobileDetailService;

@RestController
public class MessageController {
	
	@Autowired
	private MobileDetailService mobileDetailService;
	
	@RequestMapping(value = "/info/", method = RequestMethod.GET)
	public ResponseEntity<List<MobileDetail>> getDetails() {
		System.out.println("inside method getDetails");
		List<MobileDetail> details = mobileDetailService.getDetails();
		System.out.println("details : " + details);
		return new ResponseEntity<List<MobileDetail>>(details, HttpStatus.OK);
	}

	@RequestMapping(value = "/sendMsg/", method = RequestMethod.POST)
	public ResponseEntity<Void> sendMsg(@RequestBody Message message) {
		System.out.println("inside method sendMsg with message : " + message.getMessage() + " :: mobile : " + message.getMobile());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addDesc/", method = RequestMethod.POST)
	public ResponseEntity<Void> addDesc(@RequestBody MobileDetail desc) {
		System.out.println("inside method addDesc with details : " + desc.getName() + " :: mobile : " + desc.getMobile());
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
		System.out.println("inside method addDesc with details : " + desc.getName() + " :: mobile : " + desc.getMobile());
		mobileDetailService.updateDesc(desc);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
