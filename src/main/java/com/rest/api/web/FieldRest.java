package com.rest.api.web;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.api.exception.EntityNotFoundException;
import com.rest.api.model.Car;
import com.rest.api.model.Field;
import com.rest.api.service.FieldService;

@RestController
public class FieldRest extends ApiRest {

	@Autowired
	private FieldService fieldService;

	@RequestMapping(value = "fields", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Field> listCars() {
		return fieldService.findAll();
	}

	@RequestMapping(value = "fields/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Field getCar(@PathVariable("id") long id) throws EntityNotFoundException {
		return fieldService.findById(id);
	}

	@RequestMapping(value = "fields", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addCar(@RequestBody Field field) {
		fieldService.add(field);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(field.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "fields/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCar(@PathVariable("id") long id, @RequestBody Field field) throws EntityNotFoundException {
		fieldService.update(id, field);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "fields/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCar(@PathVariable("id") long id) {
		fieldService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
