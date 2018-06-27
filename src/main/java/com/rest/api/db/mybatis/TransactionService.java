package com.rest.api.db.mybatis;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.api.db.mybatis.mapper.CarDaoMapper;
import com.rest.api.db.mybatis.mapper.FieldDaoMapper;
import com.rest.api.model.Car;
import com.rest.api.model.Field;

@Service
public class TransactionService {
	
	@Autowired
	private CarDaoMapper carMapper;
	
	@Autowired
	private FieldDaoMapper fieldMapper;
	
	@Transactional
	public void test() {
		carMapper.addCar(new Car(0, "A", "B", 0, new BigDecimal(1000.00), null, null));
		
		Field field = new Field("insert", (short)1, 2, (long)3, (float)4.1, 5.1, new BigDecimal("6.002"), "", new byte[0], LocalDate.now(), LocalTime.now(), ZonedDateTime.now());
		field.setfString("insert");
		fieldMapper.addField(field);
		
		throw new RuntimeException();
	}
}
