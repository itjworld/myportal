package com.gspann.repositories.dao.impl;

import java.util.List;

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
	public List<Employee> getAllEmployees() {
		return fetchAll(Employee.class);
	}

	@Override
	public Employee getEmployeeById(long id) {
		return fetchById(id, Employee.class);
	}

	@Override
	public List<Employee> searchEmployee(String searchPara) {
		return null;
	}

}
