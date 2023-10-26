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
		connection = startConnection();
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
			if (!isProjectsTableExists()) {
				createProjectsTable();
			}
		    String query = "SELECT * FROM projects";
		    Statement statement = connection.createStatement();
	        ResultSet entries = statement.executeQuery(query);		        
	        while (entries.next()) {
	        	Project project = new Project(entries.getString("name"), entries.getString("description"), entries.getString("due_date"));
	        	projects.add(project);
	        }
		 } catch (SQLException e) {
		     e.printStackTrace();
		 } finally {
			 closeConnection();
	     }
		return projects;
	}

	/**
	 * @param project is the project that you want to save
	 * @return a boolean to show if it was successful
	 */
	public boolean insertProject(Project project) {
	    try {
	    	if (!isProjectsTableExists()) {
	    		createProjectsTable();
	        }
	        String query = "INSERT INTO projects (name, description, due_date) VALUES (?, ?, ?)";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, project.getName());
	        statement.setString(2, project.getDescription());
	        statement.setString(3, project.getDate());
	        int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
        	closeConnection();
        }
	}
	
	/**
	 * @return a boolean to show if table called "projects" exists in database
	 */
	private boolean isProjectsTableExists() {
        try {
        	// SQL query to check for a table called "projects" in user's local database
            String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='projects'";
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	private void createProjectsTable() {
	    try {
	        // Create the `projects` table
	        String createTableQuery = "CREATE TABLE projects (id INTEGER PRIMARY KEY, name TEXT, description TEXT, due_date DATE)";
	        PreparedStatement createTableStatement = connection.prepareStatement(createTableQuery);
	        createTableStatement.executeUpdate();

	        // Insert the projects
	        String[] insertQueries = {
	                "INSERT INTO projects (name, description, due_date) VALUES ('First', 'Really cool project', '12/31/2025')",
	                "INSERT INTO projects (name, description, due_date) VALUES ('Second', 'Kinda cool project', '12/31/2025')",
	                "INSERT INTO projects (name, description, due_date) VALUES ('Third', 'Not cool project', '12/31/2025')",
	        };

	        for (String insertQuery : insertQueries) {
	            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
	            insertStatement.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

    
    /**
     * @return a new connection to the database
     */
    private Connection startConnection() {
    	connection = SqliteConnection.Connector();
		if (connection == null) System.exit(1); // exit application if database fails to connect
		return connection;
    }
    
    private void closeConnection() {
    	if (connection != null) { // only try to close an existing connection
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
