package com.rest.api.service;

import java.util.Collection;

import javax.validation.Valid;

import com.rest.api.exception.EntityNotFoundException;

public interface CrudService<T> {
	
	T findById(long id) throws EntityNotFoundException;
	Collection<T> findAll();
	T add(@Valid T entity);
	void update(long id, @Valid T entity) throws EntityNotFoundException;
	//void delete(T entity);
	boolean deleteById(long id);
}
