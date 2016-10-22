package com.hrp.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
 
public abstract class AbstractDao implements Dao {
 
    @Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
 
    public void save(Object entity) {
    	if(entity != null)
    		getSession().save(entity);
    }
    public void saveOrUpdate(Object entity) {
    	if(entity != null)
    		getSession().saveOrUpdate(entity);
    }
    
    public void update(Object entity) {
    	if(entity != null)
    		getSession().update(entity);
    }
 
    public void delete(Object entity) {
    	if(entity != null)
    		getSession().delete(entity);
    }
    
    public void deleteById(Class<?> classInstance, Serializable id) {
    	Session session = getSession();
    	Object entity = (Object)session.get(classInstance, id);
    	if(entity != null)
    		session.delete(entity);
    }
    
	public Object get(Class<?> classInstance, Serializable id) {
       return getSession().get(classInstance, id);
    }
    
	public List<?> getAll(Class<?> classInstance) {
		
		Session session = getSession();
		
		//For the early fetching of child entities, we need to instruct Hibernate Criteria to use distinct root entities
		Criteria criteria = session.createCriteria(classInstance)
									.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		return criteria.list();
	}
    
	public Object getUniqueEntityByMatchingProperties(Class<?> classInstance, 
    											Map<String, Object> properties) {
    	
    	Session session = getSession();
		
		Criteria criteria = session.createCriteria(classInstance);
		
		for(String propName :properties.keySet()) {
			criteria.add(Restrictions.eq(propName, properties.get(propName)));
		}
		
		return criteria.uniqueResult();
    }
    
    public List<?> getEntitiesByMatchingProperties(Class<?> classInstance, Map<String, Object> properties) {
    	
    	Session session = getSession();
		
		Criteria criteria = session.createCriteria(classInstance);
		
		for(String propName :properties.keySet()) {
			criteria.add(Restrictions.eq(propName, properties.get(propName)));
		}
		
		return criteria.list();
    }
    
}
