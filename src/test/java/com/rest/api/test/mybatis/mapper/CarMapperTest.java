package com.rest.api.test.mybatis.mapper;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.api.db.mybatis.mapper.CarDaoMapper;
import com.rest.api.model.Car;

@RunWith(SpringRunner.class)
@MybatisTest
public class CarMapperTest {

	@Autowired
	private CarDaoMapper carMapper;
	
	@Test
	public void findById() throws Exception {
		Car car = carMapper.getCar(3);
		assertThat(3L, equalTo(car.getId()));
		assertThat("Mercedes", equalTo(car.getBrand()));
		assertThat("A 220d", equalTo(car.getModel()));
		assertThat(0, equalTo(car.getVersion()));
		assertThat(new BigDecimal("25000.00"), equalTo(car.getPrice()));
	}
}
