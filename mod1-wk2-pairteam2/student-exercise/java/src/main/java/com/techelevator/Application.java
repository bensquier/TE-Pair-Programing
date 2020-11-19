package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
	
	static List<Department> departments = new ArrayList<>();
	static List<Employee> employees = new ArrayList<>();
	static Map<String,Project> projectMap = new HashMap<>();

    /**
     * The main entry point in the application
     * @param args
     */	

    public static void main(String[] args) {


        // create some departments
        createDepartments();

        // print each department by name
        printDepartments();

        // create employees
        createEmployees();
        
        employees.get(1).raiseSalary(10);
        // give Angie a 10% raise, she is doing a great job!

        // print all employees
        printEmployees();

        // create the TEams project
        createTeamsProject();
        
        // create the Marketing Landing Page Project
        createLandingPageProject();

        // print each project name and the total number of employees on the project
        printProjectsReport();

    }

    /**
     * Create departments and add them to the collection of departments
     */

    private static void createDepartments() {
    	Department marketing = new Department(001,"Marketing");
    	departments.add(marketing);
    	Department sales = new Department(002,"Sales");
    	departments.add(sales);
    	Department engineering = new Department(003,"Engineering");
    	departments.add(engineering);
}


	/**
     * Print out each department in the collection.
     */
    private static void printDepartments() {
        System.out.println("------------- DEPARTMENTS ------------------------------");
        for (Department department:departments) {
        	System.out.println(department.name);
        }
    }

    /**
     * Create employees and add them to the collection of employees
     */
    private static void createEmployees() {
    	Employee dj = new Employee();
    	dj.setEmployeeID(001);
    	dj.setFirstName("Dean");
    	dj.setLastName("Johnson");
    	dj.setEMail("djohnson@teams.com");
    	dj.setSalary(60000);
    	dj.setDepartment(departments.get(2));
    	dj.setHireDate("08/21/2020");
    	employees.add(dj);
    	Employee angie = new Employee(002,"Angie","Smith","asmith@teams.com",departments.get(2),"08/21/2020");
    	employees.add(angie);
    	Employee margie = new Employee(003,"Margaret","Thompson","mthompson@teams.com",departments.get(0),"08/21/2020");
    	employees.add(margie);
    }

    /**
     * Print out each employee in the collection.
     */
    private static void printEmployees() {
        System.out.println("\n------------- EMPLOYEES ------------------------------");
        for (Employee worker:employees) {
        	System.out.println(worker.getFullName()+"("+worker.salary+") "+worker.getEmployeeDepartment().getName());
        }
    }

    /**
     * Create the 'TEams' project.
     */
    private static void createTeamsProject() {
    	Project teams = new Project("TEams","Project Management Software","10/10/2020","11/10/2020");
    	for (Employee worker:employees) {
        	if (worker.getEmployeeDepartment().getName().equals("Engineering")) {
        		teams.addTeamMember(worker);
        	}
        }
        projectMap.put("TEams",teams);
}

    /**
     * Create the 'Marketing Landing Page' project.
     */
    private static void createLandingPageProject() {
    	Project landingPage = new Project("Marketing Landing Page","Lead Capture Landing Page for Marketing","10/10/2020","10/17/2020");
    	for (Employee worker:employees) {
        	if (worker.getEmployeeDepartment().getName().equals("Marketing")) {
        		landingPage.addTeamMember(worker);
        	}
        }
        projectMap.put("Marketing Landing Page",landingPage);
    }

    /**
     * Print out each project in the collection.
     */
    private static void printProjectsReport() {
        System.out.println("\n------------- PROJECTS ------------------------------");
        for (String name:projectMap.keySet()) {
        	System.out.println(name+": "+projectMap.get(name).getTeamCount());
        }
    }
}
