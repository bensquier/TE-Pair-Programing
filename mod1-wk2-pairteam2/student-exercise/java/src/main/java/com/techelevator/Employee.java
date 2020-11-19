package com.techelevator;

public class Employee {
	long employeeID;
	String firstName;
	String lastName;
	String email;
	double salary;
	Department department;
	String hireDate;
	static double startingSalary = 60000;
	
	public Employee(long employeeID, String firstName, String lastName,
			String email,Department department, String hireDate) {
		this.employeeID=employeeID;
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.department=department;
		this.hireDate=hireDate;
		this.salary=startingSalary;
	}
	
	public Employee() {
		this.salary=startingSalary;
		
	}
	public String getFullName() {
		return this.lastName + ", " + this.firstName;
	}
	public double raiseSalary(double percent) {
		this.salary = this.salary*(1+(percent/100));
		return this.salary;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public long getEmployeeID() {
		return this.employeeID;
	}
	
	public String getEMail() {
		return this.email;
	}
	public String getHireDate() {
		return this.hireDate;
	}
	public Department getEmployeeDepartment() {
		return this.department;
	}
	
	public void setEmployeeID(long identification) {
		this.employeeID=identification;
	}
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}
	public void setEMail(String email) {
		this.email=email;
	}
	public void setSalary(double salary) {
		this.salary=salary;
	}
	public void setDepartment(Department department) {
		this.department=department;
	}
	public void setHireDate(String hireDate) {
		this.hireDate=hireDate;
	}
	
}
