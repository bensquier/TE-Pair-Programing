package com.techelevator;

public class Department {
	int departmentID;
	String name;
	
	public Department(int departmentID, String name) {
		this.departmentID = departmentID;
		this.name = name;
	}


	public int getDepartmentID() {
		return this.departmentID;
	}
	
	public String getName() {
		return this.name;
	}
	
}
