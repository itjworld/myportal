package com.gspann.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gspann.beans.Message;
import com.gspann.entities.Employee;
import com.gspann.repositories.dao.EmployeeDAO;
import com.gspann.service.EmployeeService;

/**
 * @author Ashish Jaiswal
 *
 */
@Service(value="employeeService")
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
    private EmployeeDAO employeeDAO;
	
	@Override
	@Transactional(readOnly=false)
	public long createEmployee(Employee employee) {
		return employeeDAO.addEmployee(employee);
	}

	@Override
	@Transactional(readOnly=false)
	public Employee updateEmployee(Employee employee) {
		return employeeDAO.updateEmployee(employee);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteEmployee(long id) {
		employeeDAO.deleteEmployee(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Message getAllEmployees(Integer page,Integer pageSize,Integer sort,String sortBy) {
		Message result= new Message();
		result.setId(employeeDAO.countRow(Employee.class));
		result.setResults(employeeDAO.getAllEmployees(page,pageSize,sort,sortBy));
		return result;
	}

	@Override
	@Transactional(readOnly=true)
	public Employee getEmployee(long id) {
		return null;
	}

}
