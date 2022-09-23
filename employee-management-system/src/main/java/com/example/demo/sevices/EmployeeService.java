package com.example.demo.sevices;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.utils.ConnectionFactory;
import com.training.exceptions.EmployeeNotFoundException;

import com.training.ifaces.EmployeeRepository;
import com.training.model.Employee;

public class EmployeeService {
	Connection con;
	EmployeeRepository repo;
	private static final Logger logger = LogManager.getRootLogger();

	public EmployeeService() {
		super();
		this.con = ConnectionFactory.getMySqlConnection();
		this.repo = new EmployeeRepositoryImpl(con);
	}

	public void save(Employee obj) {
		logger.info("is Employee Created:=" + this.repo.save(obj));
	}

	public void findByFirstName(String employeeFirstName) {
		Collection<Employee> employeeList = new ArrayList<>();
		try {
			employeeList = this.repo.findByFirstName(employeeFirstName);
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
		}
		logger.info("List of employees who are having first name as: " + employeeFirstName);
		for (Employee employee : employeeList) {
			logger.error(employee);
		}
	}

	public void findFirstNameAndPhoneNumberOfAll() {
		Collection<Employee> employeeList = new ArrayList<>();
		logger.info("First name and PhoneNumber of all employees");
		try {
			employeeList = this.repo.findEmployeeFirstNameAndPhoneNumberOfAll();
		} catch (EmployeeNotFoundException e1) {
			e1.printStackTrace();
		}

		employeeList.forEach(e -> System.out.println(e.getEmployeeFirstName() + "::" + e.getEmployeePhoneNumber()));
	}

	public void updateByEmailAndPhoneNumberOfAnEmployee(String employeeUpdatedEmail, long employeePhoneNumber, String employeeEmail) {
		try {
			logger.info("Does an employee with email: " + employeeEmail + " get updated:="
					+ this.repo.updateByEmailAndPhoneNumberOfAnEmployee(employeeUpdatedEmail, employeePhoneNumber, employeeEmail));
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void deleteByFirstName(String employeeFirstName, String employeeEmail) {
		try {
			logger.info("Does an employee with email: " + employeeEmail + " get deleted:="
					+ this.repo.deleteByFirstName(employeeFirstName, employeeEmail));
		} catch (EmployeeNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void findByBirthday(LocalDate employeeDateOfBirth) {
		Collection<Employee> employeeList = new ArrayList<>();
		logger.info("First name and PhoneNumber of all employees who have born on=" + employeeDateOfBirth);
		try {
			employeeList = this.repo.findByBirthday(employeeDateOfBirth);
		} catch (EmployeeNotFoundException e1) {
			e1.printStackTrace();
		}
		employeeList.forEach(e -> System.out.println(e.getEmployeeFirstName() + "::" + e.getEmployeeEmail()));
	}

	public void findByWeddingDate(LocalDate employeeWeddingDate) {
		Collection<Employee> employeeList = new ArrayList<>();
		logger.info("First name and PhoneNumber of all employees who got married on=" + employeeWeddingDate);
		try {
			employeeList = this.repo.findByWeddingDate(employeeWeddingDate);

		} catch (EmployeeNotFoundException e1) {
			e1.printStackTrace();
		}

		employeeList.forEach(e -> System.out.println(e.getEmployeeFirstName() + "::" + e.getEmployeePhoneNumber()));
	}
}
