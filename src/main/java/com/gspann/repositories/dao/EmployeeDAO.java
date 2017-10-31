package com.gspann.repositories.dao;

import java.util.List;

import com.gspann.entities.Employee;

public interface EmployeeDAO {
	public long addEmployee(Employee employee);
    public Employee updateEmployee(Employee employee);
    public void deleteEmployee(long id);
    public List<Employee> getAllEmployees();
    public Employee getEmployeeById(long id);   
    public List<Employee> searchEmployee(String searchPara);

}
