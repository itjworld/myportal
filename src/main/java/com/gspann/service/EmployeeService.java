package com.gspann.service;


import com.gspann.beans.Message;
import com.gspann.entities.Employee;

public interface EmployeeService {
	
	public long createEmployee(Employee employee);
    public Employee updateEmployee(Employee employee);
    public void deleteEmployee(long id);
    public Message getAllEmployees(Integer page,Integer pageSize,Integer sort,String sortBy);
    public Employee getEmployee(long id);  
}
