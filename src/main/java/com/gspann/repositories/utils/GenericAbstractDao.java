package com.gspann.repositories.utils;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gspann.entities.Employee;


/**
 * @author Ashish Jaiswal
 *
 * @param <T>
 */
public abstract class GenericAbstractDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	protected  Serializable create(final T entity) {
        return getCurrentSession().save(entity);        
    }
    
	protected  T update(final T entity) {
    	getCurrentSession().update(entity);   
        return entity;
    }
    
	protected  void delete(final T entity) {
    	getCurrentSession().delete(entity);
    }

	protected  void delete(Serializable id, Class<T> clazz) {
        T entity = fetchById(id, clazz);
        delete(entity);
    }
    
    @SuppressWarnings("unchecked")  
    protected List<T> fetchAll(Class<T> clazz) {        
        return getCurrentSession().createQuery(" FROM "+clazz.getName()).list();        
    }
  
    @SuppressWarnings("rawtypes")
    protected List fetchAll(String hql) {        
        return getCurrentSession().createQuery(hql).list();        
    }
    
    protected T fetchById(Serializable id, Class<T> clazz) {
        return (T)getCurrentSession().get(clazz, id);
    }
    
	public long countRow(Class<T> clazz) {
		CriteriaQuery<Long> criteria = null;
		CriteriaBuilder criteriaBuilder = null;
		CriteriaQuery<Long> select =null;
		long result=0;
		Session session=null;
		Root<T> from=null;
		try {
			session = getCurrentSession();
			criteriaBuilder = session.getCriteriaBuilder();
			criteria = criteriaBuilder.createQuery(Long.class);
			from = criteria.from(clazz);
			select=criteria.select(criteriaBuilder.countDistinct(from));
			result= session.createQuery(select).getSingleResult();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			criteria = null;
			criteriaBuilder = null;
			from=null;
			select=null;
			session=null;
			
		}
		return result;
	}

}
