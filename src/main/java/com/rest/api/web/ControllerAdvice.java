package com.rest.api.web;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rest.api.exception.CarNotFoundException;
import com.rest.api.model.error.Error;
import com.rest.api.model.error.FieldError;
import com.rest.api.model.error.ValidationError;

@RestControllerAdvice
public class ControllerAdvice {
	
	@Autowired
	private MessageSource messageSource;
	
	/*
	@Autowired
    public ControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
	*/
	
	@ResponseBody
	@ExceptionHandler(CarNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error handleCarNotFound(CarNotFoundException ex) {
		return new Error(ex.getCode(), ex.getMessage());
	}

	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ValidationError handleMethodArgumentNotValid(MethodArgumentNotValidException ex, Locale locale) {
		BindingResult result = ex.getBindingResult();
		ValidationError ve = new ValidationError(100, "Validation Error");
		for (org.springframework.validation.FieldError oe : result.getFieldErrors()) {
			//ve.addFieldError(new FieldError(1, oe.getField(), oe.));
			ve.addFieldError(new FieldError(1, oe.getField(), messageSource.getMessage(oe, locale)));
		}
        /*List<String> errorMessages = result.getAllErrors()
                .stream()
                .map(objectError -> messageSource.getMessage(objectError, locale))
                .collect(Collectors.toList());
        for (String string : errorMessages) {
			ve.addFieldError(new FieldError(1, string));
		}
		*/
		return ve;
	}
		
	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ValidationError handleConstraintViolationException(ConstraintViolationException ex, Locale locale) {
		ValidationError ve = new ValidationError(100, "Validation Error");
		for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {
			String field = StringUtils.substringAfter(cv.getPropertyPath().toString(), ".");
			//ve.addFieldError(new FieldError(1, field, messageSource.getMessage(cv.getMessageTemplate(), null, locale)));
			ve.addFieldError(new FieldError(1, field, cv.getMessage()));
		};

		return ve;
	}
	
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Error handleAllExceptions(Exception ex) {
		//TODO Hide Exception message
		return new Error(999, "Intern Server Error");
	}
	
	/*
	public static List<FieldError> getErrors(
			Set<ConstraintViolation<?>> constraintViolations) {
		
		return constraintViolations.stream()
				.map(FieldError::of).collect(Collectors.toList());	
	}
	
	private static FieldError of(ConstraintViolation<?> constraintViolation) {
		
		// Get the field name by removing the first part of the propertyPath.
		// (The first part would be the service method name)
		String field = StringUtils.substringAfter(
				constraintViolation.getPropertyPath().toString(), ".");
		
		return new FieldError(field,
				constraintViolation.getMessageTemplate(),
				constraintViolation.getMessage());		
	}
	*/
}
