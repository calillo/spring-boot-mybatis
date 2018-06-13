package com.rest.api.db.mybatis;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.api.db.mybatis.mapper.CarDaoMapper;
import com.rest.api.exception.CarNotFoundException;
import com.rest.api.model.Car;
import com.rest.api.service.CarService;

@Service
public class CarServiceMyBatisImpl implements CarService {

	@Autowired
	private CarDaoMapper carMapper;

	public Car getCar(long id) throws CarNotFoundException {
		Car car = carMapper.getCar(id);
		if (car == null)
			throw new CarNotFoundException();
		else
			return car;
	}

	public Car addCar(Car car) {
		carMapper.addCar(car);
		return car;
	}

	public void updateCar(long id, Car car) throws CarNotFoundException {
		Car findCar = carMapper.getCar(id);
		if (findCar == null)
			throw new CarNotFoundException();
		else
			carMapper.updateCar(id, car);
	}

	public boolean deleteCar(long id) {
		return carMapper.deleteCar(id);
	}

	public Collection<Car> listCars() {
		return carMapper.listCars();
	}
	
	@Transactional
	public void test() {
		carMapper.addCar(new Car(0, "A", "B", 0));
		throw new RuntimeException();
	}

}
