package com.example.demo.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.stream.Collectors;

import com.training.exceptions.EmployeeNotFoundException;

import com.training.ifaces.EmployeeRepository;
import com.training.model.Employee;

public  class EmployeeRepositoryImpl implements EmployeeRepository {

	private Connection con;

	public EmployeeRepositoryImpl(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public Collection<Employee> findAll() throws EmployeeNotFoundException {
		List<Employee> employeeList = new ArrayList<>();
		Employee employee;
		String sql = "select * from employee_database";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				employee = mapRowToObjectForAllColumns(resultSet);
				employeeList.add(employee);
				while (resultSet.next()) {
					employee = mapRowToObjectForAllColumns(resultSet);
					employeeList.add(employee);
				}
			} else {
				throw new EmployeeNotFoundException("ERR-100", "Employee is not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public boolean save(Employee obj) {
		String sql = "insert into employee_database values (?,?,?,?,?,?,?)";
		int rowUpdated = 0;
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, obj.getEmployeeFirstName());
			pstmt.setString(2, obj.getEmployeeLastName());
			pstmt.setString(3, obj.getEmployeeAddress());
			pstmt.setString(4, obj.getEmployeeEmail());
			pstmt.setLong(5, obj.getEmployeePhoneNumber());
			Date employeeDateOfBirth = Date.valueOf(obj.getEmployeeDateOfBirth());
			pstmt.setDate(6, employeeDateOfBirth);
			Date employeeWeddingDate = null;
			if (obj.getEmployeeWeddingDate() != null) {
				employeeWeddingDate = Date.valueOf(obj.getEmployeeWeddingDate());
			}
			pstmt.setDate(7, employeeWeddingDate);
			rowUpdated = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowUpdated == 1 ? true : false;
	}



	@Override
	public Collection<Employee> findEmployeeFirstNameAndPhoneNumberOfAll() throws EmployeeNotFoundException {
		Collection<Employee> employeeList = new ArrayList<>();
		Employee employee;
		String sql = "select employeeFirstName,employeePhoneNumber from employee_database";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				employee = mapRowToObjectForEmployeeFirstNameAndPhoneNumber(resultSet);
				employeeList.add(employee);
				while (resultSet.next()) {
					employee = mapRowToObjectForEmployeeFirstNameAndPhoneNumber(resultSet);
					employeeList.add(employee);
				}
			} else {
				throw new EmployeeNotFoundException("ERR-103", "No Employees Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public boolean updateByEmailAndPhoneNumberOfAnEmployee(String employeeUpdatedEmail, long employeePhoneNumber, String employeeEmail)
			throws EmployeeNotFoundException {
		String sql = "update employee_database SET employeeEmail=?, employeePhoneNumber=? where employeeEmail=?";
		int rowUpdated = 0;
		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, employeeUpdatedEmail);
			statement.setLong(2, employeePhoneNumber);
			statement.setString(3, employeeEmail);
			rowUpdated = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rowUpdated == 1) {
			return true;
		} else {
			throw new EmployeeNotFoundException("ERR-104",
				"Employee with the given email: " + employeeEmail + " cannot be found");
		}

	}

	

	

	private Employee mapRowToObjectForAllColumns(ResultSet resultSet) throws SQLException {
		String employeeFirstName = resultSet.getString("employeeFirstName");
		String employeeLastName = resultSet.getString("employeeLastName");
		String employeeAddress = resultSet.getString("employeeAddress");
		String employeeEmail = resultSet.getString("employeEmail");
		long employeePhoneNumber = resultSet.getLong("employeePhoneNumber");
		LocalDate employeeDateOfBirth = resultSet.getDate("employeeDateOfBirth").toLocalDate();
		LocalDate employeeWeddingDate = null;
		if (resultSet.getDate("employeeWeddingDate") != null) {
			employeeWeddingDate = resultSet.getDate("employeeWeddingDate").toLocalDate();
		}
		return new Employee(employeeFirstName, employeeLastName, employeeAddress, employeeEmail, employeePhoneNumber, employeeDateOfBirth, employeeWeddingDate);
	}

	private Employee mapRowToObjectForEmployeeFirstNameAndPhoneNumber(ResultSet resultSet) throws SQLException {
		String employeeFirstName = resultSet.getString("employeeFirstName");
		long employeePhoneNumber = resultSet.getLong("employeePhoneNumber");
		return new Employee(employeeFirstName, employeePhoneNumber);
	}

	private Employee mapRowToObjectForEmployeeFirstNameAndEmail(ResultSet resultSet) throws SQLException {
		String employeeFirstName = resultSet.getString("employeeFirstName");
		String employeeEmail = resultSet.getString("employeeEmail");
		return new Employee(employeeFirstName, employeeEmail);
	}

	@Override
	public Collection<Employee> findByFirstName(String employeeFirstName) throws EmployeeNotFoundException {
		Collection<Employee> employeeList = new ArrayList<>();
		employeeList = findAll().stream().filter(e -> e.getEmployeeFirstName().equals(employeeFirstName)).collect(Collectors.toList());
		if (employeeList.isEmpty()) {
			throw new EmployeeNotFoundException("ERR-102", "Employee Not found with the given name: " + employeeFirstName);
		} else {
			return employeeList;
		}
	}

	@Override
	public boolean deleteByFirstName(String employeeFirstName, String employeeEmail) throws EmployeeNotFoundException {
		int rowDeleted = 0;
		String sql = "delete from employee_database where employeeFirstName=? and employeeEmail=?";
		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, employeeFirstName);
			statement.setString(2, employeeEmail);
			rowDeleted = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rowDeleted == 1) {
			return true;
		} else {
			throw new EmployeeNotFoundException("ERR-105",
					"Particular Employee with the given name: " + employeeFirstName + " and email: " + employeeEmail + " is not found");
		}
	}

	@Override
	public Collection<Employee> findByWeddingDate(LocalDate employeeWeddingDate) throws EmployeeNotFoundException {
		List<Employee> employeeList = new ArrayList<>();
		Employee employee;
		String sql = "select employeeFirstName,employeePhoneNumber from employee_database where employeeWeddingDate=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			Date date = Date.valueOf(employeeWeddingDate);
			pstmt.setDate(1, date);
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				employee = mapRowToObjectForEmployeeFirstNameAndPhoneNumber(resultSet);
				employeeList.add(employee);
				while (resultSet.next()) {
					employee = mapRowToObjectForEmployeeFirstNameAndPhoneNumber(resultSet);
					employeeList.add(employee);
				}
			} else {
				throw new EmployeeNotFoundException("ERR-107",
						"No Employees have been found with the given date of birth: " + employeeWeddingDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public Collection<Employee> findByBirthday(LocalDate employeeDateOfBirth) throws EmployeeNotFoundException {
		List<Employee> employeeList = new ArrayList<>();
		Employee employee;
		String sql = "select employeeFirstName,employeeEmail from employee_database where employeeDateOfBirth=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			Date date = Date.valueOf(employeeDateOfBirth);
			pstmt.setDate(1, date);
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				employee = mapRowToObjectForEmployeeFirstNameAndEmail(resultSet);
				employeeList.add(employee);
				while (resultSet.next()) {
					employee = mapRowToObjectForEmployeeFirstNameAndEmail(resultSet);
					employeeList.add(employee);
				}
			} else {
				throw new EmployeeNotFoundException("ERR-106",
						"No Employees have been found with the given date of birth: " + employeeDateOfBirth);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	

	
	

	

	


	

	

}
