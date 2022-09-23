package com.training.ifaces;

import java.time.LocalDate;
import java.util.Collection;
import com.training.exceptions.EmployeeNotFoundException;
import com.training.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee> {
	public Collection<Employee> findByFirstName(String employeeFirstName) throws EmployeeNotFoundException;

	public Collection<Employee> findEmployeeFirstNameAndPhoneNumberOfAll() throws EmployeeNotFoundException;

	public boolean updateByEmailAndPhoneNumberOfAnEmployee(String employeeUpdatedEmail, long employeePhoneNumber, String employeeEmail) throws EmployeeNotFoundException;

	

	public boolean deleteByFirstName(String employeeFirstName, String employeeEmail) throws EmployeeNotFoundException;




	public Collection<Employee> findByWeddingDate(LocalDate employeeWeddingDate)
			throws EmployeeNotFoundException;

	public Collection<Employee> findByBirthday(LocalDate employeeDateOfBirth)
			throws EmployeeNotFoundException;

	
	

}
