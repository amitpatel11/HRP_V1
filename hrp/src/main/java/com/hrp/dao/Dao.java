package com.hrp.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface Dao {

	void saveOrUpdate(Object entity);
	void save(Object entity);
	void update(Object entity);
	void delete(Object entity);
	void deleteById(Class<?> classInstance, Serializable id);
	
	Object get(Class<?> classInstance, Serializable id);
	
	List<?> getAll(Class<?> classInstance);
	
	Object getUniqueEntityByMatchingProperties(Class<?> classInstance, Map<String, Object> properties);
	
	List<?> getEntitiesByMatchingProperties(Class<?> classInstance, Map<String, Object> properties);
}
