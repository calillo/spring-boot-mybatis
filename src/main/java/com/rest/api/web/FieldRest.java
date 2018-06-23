package com.rest.api.web;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.api.exception.EntityNotFoundException;
import com.rest.api.model.Field;
import com.rest.api.service.FieldService;

@RestController
public class FieldRest extends ApiRest {

	@Autowired
	private FieldService fieldService;

	@GetMapping(value = "fields", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Field> listCars() {
		return fieldService.findAll();
	}

	@GetMapping(value = "fields/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Field getCar(@PathVariable("id") long id) throws EntityNotFoundException {
		return fieldService.findById(id);
	}

	@PostMapping(value = "fields", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addCar(@RequestBody Field field) {
		fieldService.add(field);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(field.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "fields/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCar(@PathVariable("id") long id, @RequestBody Field field) throws EntityNotFoundException {
		fieldService.update(id, field);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "fields/{id}")
	public ResponseEntity<?> deleteCar(@PathVariable("id") long id) {
		fieldService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
