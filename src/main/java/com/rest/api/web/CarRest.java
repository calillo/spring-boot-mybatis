package com.rest.api.web;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;

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
import com.rest.api.model.Car;
import com.rest.api.service.CarService;

@RestController
public class CarRest extends ApiRest {

	@Autowired
	private CarService carService;

	@GetMapping(value = "cars", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Car> listCars() {
		return carService.findAll();
	}

	@GetMapping(value = "cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Car getCar(@PathVariable("id") long id) throws EntityNotFoundException {
		return carService.findById(id);
	}

	@PostMapping(value = "cars", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addCar(@Valid @RequestBody Car car) {
		carService.add(car);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(car.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "cars/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCar(@PathVariable("id") long id, @RequestBody Car car) throws EntityNotFoundException {
		carService.update(id, car);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "cars/{id}")
	public ResponseEntity<?> deleteCar(@PathVariable("id") long id) {
		carService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
