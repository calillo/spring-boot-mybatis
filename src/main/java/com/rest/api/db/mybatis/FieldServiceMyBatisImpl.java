package com.rest.api.db.mybatis;

import java.math.BigInteger;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.db.mybatis.mapper.FieldDaoMapper;
import com.rest.api.exception.CarNotFoundException;
import com.rest.api.exception.EntityNotFoundException;
import com.rest.api.exception.FieldNotFoundException;
import com.rest.api.model.Field;
import com.rest.api.service.FieldService;

@Service
public class FieldServiceMyBatisImpl implements FieldService {

	@Autowired
	private FieldDaoMapper fieldMapper;

	public Field findById(long id) throws FieldNotFoundException {
		Field field = fieldMapper.getField(BigInteger.valueOf(id));
		if (field == null)
			throw new FieldNotFoundException();
		else
			return field;
	}

	public Collection<Field> findAll() {
		return fieldMapper.listFields();
	}

	public Field add(Field field) {
		fieldMapper.addField(field);
		return field;
	}

	public void update(long id, Field field) throws FieldNotFoundException {
		Field findField = fieldMapper.getField(BigInteger.valueOf(id));
		if (findField == null)
			throw new FieldNotFoundException();
		else
			fieldMapper.updateField(BigInteger.valueOf(id), field);
		
	}

	public boolean deleteById(long id) {
		return fieldMapper.deleteField(BigInteger.valueOf(id));
	}

}
