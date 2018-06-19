package com.rest.api.service;

import java.util.Collection;

import com.rest.api.exception.EntityNotFoundException;

public interface CrudService<T> {
	
	T findById(long id) throws EntityNotFoundException;
	Collection<T> findAll();
	T add(T entity);
	void update(long id, T entity) throws EntityNotFoundException;
	//void delete(T entity);
	boolean deleteById(long id);
}
