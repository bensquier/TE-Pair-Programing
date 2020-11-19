package com.techelevator.projects.model.jdbc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.ProjectDAO;

public class JDBCProjectDAO implements ProjectDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCProjectDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Project> getAllActiveProjects() {
		List<Project> activeProjects = new ArrayList<Project>();
		String sqlGetAllActiveProjects = "SELECT project_id, name, from_date, to_date " + 
										"FROM project " +
										"WHERE from_date IS NOT NULL AND to_date >= CURRENT_DATE OR from_date IS NOT NULL AND to_date IS NULL";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetAllActiveProjects);
		while (result.next()) {
			activeProjects.add(mapRowToProject(result));
		}
		return activeProjects;
	}

	@Override
	public void removeEmployeeFromProject(Long projectId, Long employeeId) {
		String sqlRemoveEmployeeFromProject = "DELETE FROM project_employee WHERE project_id = ? AND employee_id = ?";
		jdbcTemplate.update(sqlRemoveEmployeeFromProject, projectId, employeeId);
	}

	@Override
	public void addEmployeeToProject(Long projectId, Long employeeId) {
		String sqlAddEmployeeToProject = "INSERT INTO project_employee(project_id, employee_id) " +
											"VALUES(?, ?)";
		jdbcTemplate.update(sqlAddEmployeeToProject, projectId, employeeId);	
	}
	
	private Project mapRowToProject(SqlRowSet result) {
		Project theProject;
		theProject = new Project();
		theProject.setId(result.getLong("project_id"));
		theProject.setName(result.getString("name"));
		if (theProject.getStartDate() != null) {
			theProject.setStartDate(Date.valueOf(result.getString("from_date")).toLocalDate());
		} else {
			theProject.setStartDate(Date.valueOf(result.getString("from_date")).toLocalDate());
		}
		if (theProject.getEndDate() != null) {
			theProject.setEndDate(Date.valueOf(result.getString("to_date")).toLocalDate());
		} else {
			theProject.setEndDate(Date.valueOf(result.getString("to_date")).toLocalDate());
		}
		return theProject;
	}

}
