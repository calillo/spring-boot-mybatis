package com.rest.api.test.mybatis.mapper;

import org.junit.Assert;
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
		Assert.assertEquals(3, car.getId());
		Assert.assertEquals("Mercedes", car.getBrand());
		Assert.assertEquals("A 220d", car.getModel());
		Assert.assertEquals(0, car.getVersion());
	}
}
