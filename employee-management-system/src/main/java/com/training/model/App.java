package com.training.model;

import java.time.LocalDate;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.demo.sevices.EmployeeService;
import com.training.exceptions.EmployeeNotFoundException;

public class App {
	private static final Logger logger = LogManager.getRootLogger();

	public static LocalDate maritalStatus(Scanner input) {
		while (true) {
			logger.info("Please mention your marital status? y/n or Y/N");
			String weddingChoice = input.nextLine();
			if (weddingChoice.equalsIgnoreCase("y")) {
				logger.info("Wedding Date(YYYY-MM-DD):");
				LocalDate employeeWeddingDate = LocalDate.parse(input.nextLine());
				return employeeWeddingDate;
			} else if (weddingChoice.equalsIgnoreCase("n")) {
				return null;
			} else {
				logger.info("Kindly mention the correct option!!");
			}
		}

	}

	public static void enterEmployeeDetails() throws EmployeeNotFoundException {
		Scanner input = new Scanner(System.in);
		EmployeeService service = new EmployeeService();
		while (true) {
			logger.info("Enter your choice");
			logger.info("1->Save the Employee Details");
			logger.info("2->Get Employees By Their First Name");
			logger.info("3->Get First Name and Phone Number of all Employees");
			logger.info("4->Update Email and PhoneNumber of an Employee");
			logger.info("5->Delete Employee by First Name");
			logger.info("6->Get First Name and Email of all Employees by their Birthday");
			logger.info("7->Get First Name and Phone Number of all Employees by their Wedding Date");
			logger.info("Enter the choice");
			int choice = Integer.parseInt(input.nextLine());
			if (choice == 1) {
				logger.info("-------------Enter required details to add an Employee---------------------");
				logger.info("First Name:");
				String employeeFirstName = input.nextLine();
				logger.info("Last Name:");
				String employeeLastName = input.nextLine();
				logger.info("Address:");
				String employeeAddress = input.nextLine();
				logger.info("Email:");
				String employeeEmail = input.nextLine();
				logger.info("Phone Number:");
				long employeePhoneNumber = Long.parseLong(input.nextLine());
				logger.info("Date Of Birth in the format (YYYY-MM-DD):");
				LocalDate employeeDateOfBirth = LocalDate.parse(input.nextLine());
				LocalDate employeeWeddingDate = maritalStatus(input);
				service.save(new Employee(employeeFirstName, employeeLastName, employeeAddress, employeeEmail,
						employeePhoneNumber, employeeDateOfBirth, employeeWeddingDate));
			} else if (choice == 2) {
				logger.info("2->Find Employees By First Name");
				logger.info("First Name:");
				String employeeFirstName = input.nextLine();
				service.findByFirstName(employeeFirstName);
			} else if (choice == 3) {
				logger.info("3->Find First Name and Phone Number of all Employees");
				service.findFirstNameAndPhoneNumberOfAll();
			} else if (choice == 4) {
				logger.info("4->Update Email and PhoneNumber of a Particular Employee");
				logger.info("Updated Email:");
				String employeeUpdatedemail = input.nextLine();
				logger.info("Phone Number:");
				long employeePhoneNumber = Long.parseLong(input.nextLine());
				logger.info("Old Email:");
				String employeeEmail = input.nextLine();
				service.updateByEmailAndPhoneNumberOfAnEmployee(employeeUpdatedemail, employeePhoneNumber,
						employeeEmail);
			} else if (choice == 5) {
				logger.info("5->Delete Employee by First Name");
				logger.info("First Name:");
				String employeeFirstName = input.nextLine();
				logger.info("Email:");
				String employeeEmail = input.nextLine();
				service.deleteByFirstName(employeeFirstName, employeeEmail);
			} else if (choice == 6) {
				logger.info("6->Find First Name and Email of all Employees by Birthday");
				logger.info("Date Of Birth:");
				LocalDate employeeDateOfBirth = null;
				try {
					employeeDateOfBirth = LocalDate.parse(input.nextLine());
				} catch (Exception e) {
					e.printStackTrace();
				}
				service.findByBirthday(employeeDateOfBirth);
			} else if (choice == 7) {
				logger.info("7->Find First Name and Phone Number of all Employees by Wedding Date");
				logger.info("Wedding Date:");
				LocalDate employeeWeddingDate = LocalDate.parse(input.nextLine());
				service.findByWeddingDate(employeeWeddingDate);
			} else {
				logger.info("Kindly enter the option between 1 TO 7!!");
				continue;
			}

			if (choice >= 1 && choice <= 7) {

				logger.info("Do you want to continue? y/n or Y/N");
				String willingToContinue = input.nextLine();
				if (willingToContinue.equalsIgnoreCase("y")) {
					System.out.println("Continuing");
					continue;

				} else if (willingToContinue.equalsIgnoreCase("n")) {
					input.close();
					logger.info("Exit Success");
					break;

				} else {
					logger.info("Kindly mention the valid option!!");
				}
			}
		}

	}


	public static void main(String[] args) throws EmployeeNotFoundException {
		enterEmployeeDetails();
	}

}
