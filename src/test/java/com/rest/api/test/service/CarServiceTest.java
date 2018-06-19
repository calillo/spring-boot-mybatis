package com.rest.api.test.service;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.api.db.mybatis.CarServiceMyBatisImpl;
import com.rest.api.exception.CarNotFoundException;
import com.rest.api.model.Car;
import com.rest.api.service.CarService;

@RunWith(SpringRunner.class)
@MybatisTest
@Import(CarServiceMyBatisImpl.class)
// @AutoConfigureTestDatabase(replace=Replace.NONE)
public class CarServiceTest {

	@Autowired
	private CarService carService;

	@Test
	public void getCar() throws Exception {
		Car car = carService.findById(3);
		Assert.assertEquals(3, car.getId());
		Assert.assertEquals("Mercedes", car.getBrand());
		Assert.assertEquals("A 220d", car.getModel());
		Assert.assertEquals(0, car.getVersion());
	}
	
	@Test(expected = CarNotFoundException.class)
	public void getCarNotFound() throws Exception {
		carService.findById(99);
	}
	
	@Test
	public void addCar() throws Exception {
		Car car = new Car();
		car.setBrand("Brand");
		car.setModel("Model");
		car.setVersion(1);

		carService.add(car);
		Assert.assertNotNull(car.getId());

		Car ins = carService.findById(car.getId());
		Assert.assertEquals(car.getBrand(), ins.getBrand());
		Assert.assertEquals(car.getModel(), ins.getModel());
		Assert.assertEquals(car.getVersion(), ins.getVersion());
	}

	@Test
	public void updateCar() throws Exception {
		Car car = carService.findById(2);
		car.setBrand("Brand");
		car.setModel("Model");
		car.setVersion(0);

		carService.update(2, car);

		Car upd = carService.findById(2);
		Assert.assertEquals(car.getBrand(), upd.getBrand());
		Assert.assertEquals(car.getModel(), upd.getModel());
		Assert.assertEquals(car.getVersion(), upd.getVersion());
	}
	
	@Test(expected = CarNotFoundException.class)
	public void updateCarNotFound() throws Exception {
		Car car = new Car();
		car.setId(99);
		car.setBrand("Brand");
		car.setModel("Model");
		car.setVersion(0);

		carService.update(car.getId(), car);
	}

	@Test
	public void deleteCar() {
		boolean isDel;
		isDel = carService.deleteById(2);
		Assert.assertTrue(isDel);
	}
	
	@Test
	public void deleteCarNotFound() {
		boolean isDel;
		isDel = carService.deleteById(99);
		Assert.assertFalse(isDel);
	}

	@Test
	public void listCars() {
		Collection<Car> carList = carService.findAll();
		Assert.assertEquals(3, carList.size());

		for (Car c : carList) {
			switch ((int) c.getId()) {
			case 1:
				Assert.assertEquals("BMW", c.getBrand());
				Assert.assertEquals("320d", c.getModel());
				Assert.assertEquals(1, c.getVersion());
				break;
			case 2:
				Assert.assertEquals("Audi", c.getBrand());
				Assert.assertEquals("A3 2.0 TDI", c.getModel());
				Assert.assertEquals(0, c.getVersion());
				break;
			case 3:
				Assert.assertEquals("Mercedes", c.getBrand());
				Assert.assertEquals("A 220d", c.getModel());
				Assert.assertEquals(0, c.getVersion());
				break;
			default:
				Assert.fail();
				break;
			}
		}

	}

}
