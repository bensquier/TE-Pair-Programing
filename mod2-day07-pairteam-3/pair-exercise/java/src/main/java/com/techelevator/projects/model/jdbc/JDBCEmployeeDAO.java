package com.techelevator.projects.model.jdbc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.EmployeeDAO;

public class JDBCEmployeeDAO implements EmployeeDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCEmployeeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		String sqlGetAllEmployees = "SELECT employee_id, department_id, first_name, last_name, birth_date, gender, hire_date " 
				+ "FROM employee;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetAllEmployees);
		while(result.next()) {
			employees.add(mapRowToEmployee(result));
		}
		return employees;
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {
		ArrayList<Employee> employees = new ArrayList<>();
		String sqlSearchEmployeesByName = "SELECT employee_id, department_id, first_name, last_name, birth_date, gender, hire_date " +
											"FROM employee " +
											"WHERE first_name LIKE ? AND last_name LIKE ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchEmployeesByName, "%" + firstNameSearch + "%", "%" + lastNameSearch + "%");
		while (results.next()) {
			Employee theEmployee = mapRowToEmployee(results);
			employees.add(theEmployee);
		}
		return employees;
	}

	@Override
	public List<Employee> getEmployeesByDepartmentId(long id) {
		List<Employee> employees = new ArrayList<Employee>();
		String sqlGetEmployeesDepartmentById = "SELECT employee_id, department_id, first_name, last_name, birth_date, gender, hire_date " +
												"FROM employee " +
												"WHERE department_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetEmployeesDepartmentById, id);
		while (results.next()) {
			Employee employee = mapRowToEmployee(results);
			employees.add(employee);
		} 
		return employees;
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		List<Employee> employees = new ArrayList<Employee>();
		String sqlGetEmployeesWithoutProjects = "SELECT e.employee_id, e.department_id, e.first_name, e.last_name, e.birth_date, e.gender, e.hire_date " +
												"FROM employee e LEFT JOIN project_employee pe ON e.employee_id = pe.employee_id " +
												"WHERE pe.employee_id IS NULL";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetEmployeesWithoutProjects);
		while (results.next()) {
			Employee employee = mapRowToEmployee(results);
			employees.add(employee);
		} 
		return employees;
	}

	@Override
	public List<Employee> getEmployeesByProjectId(Long projectId) {
		List<Employee> employees = new ArrayList<Employee>();
		String sqlGetEmployeesByProjectId = "SELECT e.employee_id, e.department_id, e.first_name, e.last_name, e.birth_date, e.gender, e.hire_date " +
												"FROM employee e INNER JOIN project_employee pe ON e.employee_id = pe.employee_id " +
												"WHERE pe.project_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetEmployeesByProjectId, projectId);
		while (results.next()) {
			Employee employee = mapRowToEmployee(results);
			employees.add(employee);
		} 
		return employees;
	}

	@Override
	public void changeEmployeeDepartment(Long employeeId, Long departmentId) {
		String sqlChangeEmployeeDepartment = "UPDATE employee SET department_id = ? WHERE employee_id = ?";
		jdbcTemplate.update(sqlChangeEmployeeDepartment, departmentId, employeeId);
	}
	
	private Employee mapRowToEmployee(SqlRowSet result) {
		Employee theEmployee;
		theEmployee = new Employee();
		theEmployee.setId(result.getLong("employee_id"));
		theEmployee.setDepartmentId(result.getLong("department_id"));
		theEmployee.setFirstName(result.getString("first_name"));
		theEmployee.setLastName(result.getString("last_name"));
		theEmployee.setBirthDay(Date.valueOf(result.getString("birth_date")).toLocalDate());
		theEmployee.setGender(result.getString("gender").charAt(0));
		theEmployee.setHireDate(Date.valueOf(result.getString("hire_date")).toLocalDate());
		return theEmployee;
	}

}
