package com.gspann.repositories.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.gspann.entities.Employee;
import com.gspann.repositories.dao.EmployeeDAO;
import com.gspann.repositories.utils.GenericAbstractDao;

@Repository(value="employeeDAO")
public class EmployeeDAOImpl extends GenericAbstractDao<Employee> implements EmployeeDAO{

	@Override
	public long addEmployee(Employee employee) {
		return (long) create(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return update(employee);
	}

	@Override
	public void deleteEmployee(long id) {
		 delete(id,Employee.class);
	}

	@Override
	public List<Employee> getAllEmployees(Integer page,Integer pageSize,Integer sort,String sortBy) {
		CriteriaQuery<Employee> criteria = null;
		CriteriaBuilder criteriaBuilder = null;
		List<Employee> results=null;
		Session session=null;
		Root<Employee> from=null;
		Order order=null;
		String column="id";
		try {
			session = getCurrentSession();
			criteriaBuilder = session.getCriteriaBuilder();
			criteria = criteriaBuilder.createQuery(Employee.class);
			from = criteria.from(Employee.class);
			switch (sort) {
			case 0:
				column="name";
				break;
			case 1:
				column="email";
				break;
			case 2:
				column="age";
			case 3:
				column="salary";
				break;
			default:
				column="id";
				break;
			}
			if("DESC".equalsIgnoreCase(sortBy)) {
				order=criteriaBuilder.desc(from.get(column));
			}else {
				order=criteriaBuilder.asc(from.get(column));
			}
			criteria.select(from).orderBy(order);
			results= session.createQuery(criteria).setFirstResult(page*pageSize).setMaxResults(pageSize).getResultList();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			criteria = null;
			criteriaBuilder = null;
			from=null;
			order=null;
			session=null;
			
		}
		return results;
	}

	@Override
	public Employee getEmployeeById(long id) {
		return fetchById(id, Employee.class);
	}

	@Override
	public List<Employee> searchEmployee(String searchPara) {
		return null;
	}

	@Override
	public long countRow() {
		CriteriaQuery<Long> criteria = null;
		CriteriaBuilder criteriaBuilder = null;
		CriteriaQuery<Long> select =null;
		long result=0;
		Session session=null;
		Root<Employee> from=null;
		try {
			session = getCurrentSession();
			criteriaBuilder = session.getCriteriaBuilder();
			criteria = criteriaBuilder.createQuery(Long.class);
			from = criteria.from(Employee.class);
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
