package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.SqliteConnection;
import models.Project;

public class ProjectDAO {
	Connection connection;
	
	public ProjectDAO () {
		connection = SqliteConnection.Connector();
		if (connection == null) System.exit(1); // exit application if database fails to connect
	}
	
	public boolean validDBConnection() {
		try {
			return !connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/** 
	 * @return a list of projects
	 */
	public List<Project> getAllProjects() {
		List<Project> projects = new ArrayList<>();
		 try {
		        String sql = "SELECT * FROM projects";
		        Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery(sql);		        
	            while (resultSet.next()) {
	                Project project = new Project(resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("due_date"));
	                projects.add(project);
	            }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        }
		return projects;
	}

	/**
	 * @param project is the project that you want to save
	 * @return a boolean to show if it was successful
	 */
	public boolean insertProject(Project project) {
	    try {
	        String sql = "INSERT INTO projects (name, description, due_date) VALUES (?, ?, ?)";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, project.getName());
	        statement.setString(2, project.getDescription());
	        statement.setString(3, project.getDate());
	        int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
