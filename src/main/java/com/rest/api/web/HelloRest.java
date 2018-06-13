package com.rest.api.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRest extends ApiRest {

	@RequestMapping("hello")
	String home() {
		return "Hello World!";
	}

}
