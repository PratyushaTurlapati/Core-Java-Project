package com.training.model;

import java.time.LocalDate;

public class Employee {
	private String employeeFirstName;
	private String employeeLastName;
	private String employeeAddress;
	private String employeeEmail;
	private long employeePhoneNumber;
	private LocalDate employeeDateOfBirth;
	private LocalDate employeeWeddingDate;


	public Employee() {
		super();
	}

	public Employee(String employeeFirstName, long employeePhoneNumber) {
		super();
		this.employeeFirstName = employeeFirstName;
		this.employeePhoneNumber = employeePhoneNumber;
	}

	public Employee(String employeeFirstName, String employeeEmail) {
		super();
		this.employeeFirstName = employeeFirstName;
		this.employeeEmail = employeeEmail;
	}

	public Employee(String employeeFirstName, String employeeLastName, String employeeAddress, String employeeEmail, long employeePhoneNumber,
			LocalDate employeeDateOfBirth, LocalDate employeeWeddingDate) {
		super();
		this.employeeFirstName = employeeFirstName;
		this.employeeLastName = employeeLastName;
		this.employeeAddress = employeeAddress;
		this.employeeEmail = employeeEmail;
		this.employeePhoneNumber = employeePhoneNumber;
		this.employeeDateOfBirth = employeeDateOfBirth;
		this.employeeWeddingDate = employeeWeddingDate;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public String getEmployeeAddress() {
		return employeeEmail;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeEmail = employeeAddress;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail_address) {
		this.employeeEmail = employeeEmail_address;
	}

	public long getEmployeePhoneNumber() {
		return employeePhoneNumber;
	}

	public void setEmployeePhoneNumber(long employeePhoneNumber) {
		this.employeePhoneNumber = employeePhoneNumber;
	}

	public LocalDate getEmployeeDateOfBirth() {
		return employeeDateOfBirth;
	}

	public void setEmployeeDateOfBirth(LocalDate employeeDateOfBirth) {
		this.employeeDateOfBirth = employeeDateOfBirth;
	}

	public LocalDate getEmployeeWeddingDate() {
		return employeeWeddingDate;
	}

	public void setEmployeeWeddingDate(LocalDate employeeWeddingDate) {
		this.employeeWeddingDate = employeeWeddingDate;
	}

	@Override
	public String toString() {
		return "Employee [employeeFirstName=" + employeeFirstName + ", employeeLastName=" + employeeLastName + ", employeeAddress=" + employeeAddress
				+ ", email_address=" + employeeEmail + ", phoneNumber=" + employeePhoneNumber + ", dateOfBirth=" + employeeDateOfBirth
				+ ", weddingDate=" + employeeWeddingDate + "]";
	}

	



}
