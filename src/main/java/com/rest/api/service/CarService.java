package com.rest.api.service;


import java.util.Collection;

import com.rest.api.exception.CarNotFoundException;
import com.rest.api.model.Car;

public interface CarService {
	Car getCar(long id) throws CarNotFoundException;
    Car addCar(Car car);
    void updateCar(long id, Car car) throws CarNotFoundException;
    boolean deleteCar(long id);
    Collection<Car> listCars();
    
    void test();
}
