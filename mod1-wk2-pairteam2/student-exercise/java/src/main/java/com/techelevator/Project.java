package com.techelevator;

import java.util.List;
import java.util.ArrayList;

public class Project {
	private String name;
	private String description;
	private String startDate;
	private String dueDate;
	
	List<Employee> teamMembers = new ArrayList<>();
	
	public Project(String name , String description, String startDate , String dueDate) {
		this.name=name;
		this.description=description;
		this.startDate=startDate;
		this.dueDate=dueDate;
	}
	public String getProjectName() {
		return this.name;
	}
	public String getDescription() {
		return this.description;
	}
	public String getStartDate() {
		return this.startDate;
	}
	public String dueDate() {
		return this.dueDate;
	}
	public List<Employee> getTeamMembers() {
		return this.teamMembers;
	}
	public void addTeamMember(Employee name) {
		this.teamMembers.add(name);
	}
	public int getTeamCount() {
		return teamMembers.size();
	}
}
