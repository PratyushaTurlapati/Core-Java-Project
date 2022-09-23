package com.training.ifaces;

import java.time.LocalDate;
import java.util.Collection;

import com.training.exceptions.EmployeeNotFoundException;
import com.training.model.Employee;


public interface CrudRepository<T> {
	public boolean save(T obj);
	public Collection<T> findAll() throws Exception;
	public Collection<Employee> findByFirstName(String employeeFirstName) throws EmployeeNotFoundException;
	public Collection<Employee> findEmployeeFirstNameAndPhoneNumberOfAll() throws EmployeeNotFoundException;
	
	public boolean deleteByFirstName(String employeeFirstName, String employeeEmail) throws EmployeeNotFoundException;
	public Collection<Employee> findByWeddingDate(LocalDate employeeWeddingDate)
			throws EmployeeNotFoundException;
	public Collection<Employee> findByBirthday(LocalDate employeeDateOfBirth)
			throws EmployeeNotFoundException;
}
