package com.rest.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.db.mybatis.TransactionService;

@RestController
public class HelloRest extends ApiRest {
	
	@Autowired
	private TransactionService transactionService;

	@RequestMapping("hello")
	String home() {
		return "Hello World!";
	}
	
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public ResponseEntity<?> test() {
		transactionService.test();
		return ResponseEntity.noContent().build();
	}

}
