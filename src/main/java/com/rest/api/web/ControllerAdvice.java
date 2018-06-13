package com.rest.api.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rest.api.exception.CarNotFoundException;
import com.rest.api.model.error.Error;

@RestControllerAdvice
public class ControllerAdvice {

	@ResponseBody
	@ExceptionHandler(CarNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error handleCarNotFound(CarNotFoundException ex) {
		return new Error(ex.getCode(), ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Error handleAllExceptions(Exception ex) {
		//TODO Hide Exception message
		return new Error(999, "Intern Server Error");
	}
}
