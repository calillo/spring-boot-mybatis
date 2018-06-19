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

import com.rest.api.exception.CarNotFoundException;
import com.rest.api.exception.EntityNotFoundException;
import com.rest.api.model.Car;
import com.rest.api.service.CarService;

@RestController
public class CarRest extends ApiRest {

	@Autowired
	private CarService carService;

	@RequestMapping(value = "cars", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Car> listCars() {
		return carService.findAll();
	}

	@RequestMapping(value = "cars/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Car getCar(@PathVariable("id") long id) throws EntityNotFoundException {
		return carService.findById(id);
	}

	@RequestMapping(value = "cars", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addCar(@RequestBody Car car) {
		carService.add(car);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(car.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@RequestMapping(value = "cars/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCar(@PathVariable("id") long id, @RequestBody Car car) throws EntityNotFoundException {
		carService.update(id, car);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "cars/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCar(@PathVariable("id") long id) {
		carService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
