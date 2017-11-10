package com.gspann.repositories.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gspann.entities.Users;
import com.gspann.repositories.dao.UserDao;
import com.gspann.repositories.utils.GenericAbstractDao;

/**
 * @author Ashish Jaiswal
 *
 */
@Repository(value="userDao")
public class UserDaoImpl extends GenericAbstractDao<Users> implements UserDao {

	@Override
	public Users findUserByUsername(final String username) {
		CriteriaQuery<Users> criteria = null;
		CriteriaBuilder criteriaBuilder = null;
		Users result=null;
		Session session=null;
		Root<Users> root=null;
		Predicate predicate=null;
		try {
			session = getCurrentSession();
			criteriaBuilder = session.getCriteriaBuilder();
			criteria = criteriaBuilder.createQuery(Users.class);
			root = criteria.from(Users.class);
			predicate = criteriaBuilder.equal(root.get("username"), username);
			criteria.where(predicate);
			result= session.createQuery(criteria).getSingleResult();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			criteria = null;
			criteriaBuilder = null;
			root=null;
			predicate=null;
			session=null;
			
		}
		return result;
	}

	@Override
	public boolean isExistUser(String username) {
		final Users result=findUserByUsername(username);
		return result!=null?true:false;
	}

	@Override
	public void createUser(Users users) {
		create(users);
	}

}
