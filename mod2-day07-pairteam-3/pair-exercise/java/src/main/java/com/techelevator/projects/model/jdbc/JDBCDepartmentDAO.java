package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.DepartmentDAO;

public class JDBCDepartmentDAO implements DepartmentDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCDepartmentDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Department> getAllDepartments() {
		List<Department> departments = new ArrayList<Department>();
		String sqlGetAllDepartments = "SELECT department_id, name " + "FROM department;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetAllDepartments);
		while(result.next()) {
			departments.add(mapRowToDepartment(result));
		}
		return departments;
	}

	@Override
	public List<Department> searchDepartmentsByName(String nameSearch) {
		ArrayList<Department> departments = new ArrayList<>();
		String sqlSearchDepartmentByName = "SELECT department_id, name " +
											"FROM department " +
											"WHERE name LIKE ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchDepartmentByName, "%" + nameSearch + "%");
		while (results.next()) {
			Department theDepartment = mapRowToDepartment(results);
			departments.add(theDepartment);
		}
		return departments;
	}

	@Override
	public void saveDepartment(Department updatedDepartment) {
		String sqlInsertDeparment = "UPDATE department SET name = ? WHERE department_id = ?";
		jdbcTemplate.update(sqlInsertDeparment, updatedDepartment.getName(), updatedDepartment.getId());
		
	}

	@Override
	public Department createDepartment(Department newDepartment) {
		String sqlInsertDepartment = "INSERT INTO department(department_id, name) " +
							"VALUES(?, ?)";
		newDepartment.setId(getNextDepartmentId());
		jdbcTemplate.update(sqlInsertDepartment, newDepartment.getId(), newDepartment.getName());
		
		return newDepartment;
	}

	@Override
	public Department getDepartmentById(Long id) {
		Department department = null;
		String sqlGetDepartmentById = "SELECT department_id, name " +
											"FROM department " +
											"WHERE department_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetDepartmentById, id);
		if (results.next()) {
			department = mapRowToDepartment(results);
		} 
		return department;
	}
	
	private long getNextDepartmentId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_department_id')");
		if (nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new city");
		}
	}

	
	private Department mapRowToDepartment(SqlRowSet result) {
		Department theDepartment;
		theDepartment = new Department();
		theDepartment.setId(result.getLong("department_id"));
		theDepartment.setName(result.getString("name"));
		return theDepartment;
	}

}
