package com.rest.api.test.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collection;

import org.exparity.hamcrest.date.ZonedDateTimeMatchers;
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
		assertThat(3L, equalTo(car.getId()));
		assertThat("Mercedes", equalTo(car.getBrand()));
		assertThat("A 220d", equalTo(car.getModel()));
		assertThat(0, equalTo(car.getVersion()));
		assertThat(new BigDecimal("25000.00"), equalTo(car.getPrice()));
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
		car.setPrice(new BigDecimal("1000.00"));

		carService.add(car);
		assertThat(car.getId(), notNullValue());

		Car ins = carService.findById(car.getId());
		assertThat(car.getBrand(), equalTo(ins.getBrand()));
		assertThat(car.getModel(), equalTo(ins.getModel()));
		assertThat(car.getVersion(), equalTo(ins.getVersion()));
		assertThat(car.getPrice(), equalTo(ins.getPrice()));
		assertThat(ins.getInsertDate(), notNullValue());
		assertThat(ins.getUpdateDate(), notNullValue());
		assertThat(ins.getInsertDate(), ZonedDateTimeMatchers.before(ZonedDateTime.now()));
		assertThat(ins.getUpdateDate(), ZonedDateTimeMatchers.before(ZonedDateTime.now()));
	}

	@Test
	public void updateCar() throws Exception {
		Car car = carService.findById(2);
		car.setBrand("Brand");
		car.setModel("Model");
		car.setVersion(0);
		car.setPrice(new BigDecimal("1000.00"));

		carService.update(2, car);

		Car upd = carService.findById(2);
		assertThat(car.getBrand(), equalTo(upd.getBrand()));
		assertThat(car.getModel(), equalTo(upd.getModel()));
		assertThat(car.getVersion(), equalTo(upd.getVersion()));
		assertThat(car.getPrice(), equalTo(upd.getPrice()));
		assertThat(car.getInsertDate(), is(notNullValue()));
		assertThat(car.getUpdateDate(), is(notNullValue()));
		assertThat(car.getUpdateDate(), ZonedDateTimeMatchers.after(car.getInsertDate()));
		assertThat(car.getUpdateDate(), ZonedDateTimeMatchers.before(ZonedDateTime.now()));
	}
	
	@Test(expected = CarNotFoundException.class)
	public void updateCarNotFound() throws Exception {
		Car car = new Car();
		car.setId(99);
		car.setBrand("Brand");
		car.setModel("Model");
		car.setVersion(0);
		car.setPrice(new BigDecimal("1000.00"));

		carService.update(car.getId(), car);
	}

	@Test
	public void deleteCar() {
		boolean isDel;
		isDel = carService.deleteById(2);
		assertThat(isDel, is(true));
	}
	
	@Test
	public void deleteCarNotFound() {
		boolean isDel;
		isDel = carService.deleteById(99);
		assertThat(isDel, is(false));
	}

	@Test
	public void listCars() {
		Collection<Car> carList = carService.findAll();
		assertThat(3, equalTo(carList.size()));

		for (Car c : carList) {
			switch ((int) c.getId()) {
			case 1:
				assertThat("BMW", equalTo(c.getBrand()));
				assertThat("320d", equalTo(c.getModel()));
				assertThat(1, equalTo(c.getVersion()));
				assertThat(new BigDecimal("40000.00"), equalTo(c.getPrice()));
				break;
			case 2:
				assertThat("Audi", equalTo(c.getBrand()));
				assertThat("A3 2.0 TDI", equalTo(c.getModel()));
				assertThat(0, equalTo(c.getVersion()));
				assertThat(new BigDecimal("35000.00"), equalTo(c.getPrice()));
				break;
			case 3:
				assertThat("Mercedes", equalTo(c.getBrand()));
				assertThat("A 220d", equalTo(c.getModel()));
				assertThat(0, equalTo(c.getVersion()));
				assertThat(new BigDecimal("25000.00"), equalTo(c.getPrice()));
				break;
			default:
				fail();
				break;
			}
		}

	}

}
