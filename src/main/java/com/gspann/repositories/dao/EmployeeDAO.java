package com.gspann.repositories.dao;

import java.util.List;

import com.gspann.entities.Employee;

/**
 * @author Ashish Jaiswal
 *
 */
public interface EmployeeDAO {
	public long addEmployee(Employee employee);
    public Employee updateEmployee(Employee employee);
    public void deleteEmployee(long id);
    public List<Employee> getAllEmployees(Integer page,Integer pageSize,Integer sort,String sortBy);
    public long countRow(Class<Employee> class1);
    public Employee getEmployeeById(long id);   
    public List<Employee> searchEmployee(String searchPara);

}
