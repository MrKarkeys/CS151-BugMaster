package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import config.SqliteConnection;
import models.Project;
import models.ProjectModel;

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

	public List<Project> getAllProjects() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean insertProject(Project project) {
	    try {
	        if (!isProjectsTableExists()) {
	            createProjectsTable();
	        }

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

	private boolean isProjectsTableExists() {
	    try {
	        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='projects'";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        return statement.executeQuery().next();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	private void createProjectsTable() {
	    try {
	        String sql = "CREATE TABLE projects (id INTEGER PRIMARY KEY, name TEXT, description TEXT, due_date DATE)";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
