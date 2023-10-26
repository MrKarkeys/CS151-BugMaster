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
import models.Ticket;

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
	        	Project project = new Project(entries.getInt("id"), entries.getString("name"), entries.getString("description"), entries.getString("due_date"));
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
	    	if (getProjectByName(project.getName()) != null) {
	    		return false;
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
	
	private Project getProjectByName(String name) {
	    try {
	        // Create a prepared statement to get the project by name
	        String query = "SELECT * FROM projects WHERE name = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, name);

	        // Execute the prepared statement and get the results
	        ResultSet results = statement.executeQuery();

	        // Check if the results contain a row
	        if (results.next()) {
	            // Create a new Project object from the results
	            Project project = new Project(
	            		results.getInt("id"),
	            		results.getString("name"),
	            		results.getString("description"),
	            		results.getString("due_date")
	            	);

	            return project;
	        } else {
	            return null;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
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

	public boolean insertTicket(Ticket ticket) {
		try {
	    	if (!isTicketsTableExists()) {
	    		createTicketsTable();
	        }
	    	if (getTicketByName(ticket.getName()) != null) {
	    		return false;
	    	}
	        String query = "INSERT INTO tickets (projectId, name, description, due_date) VALUES (?, ?, ?, ?)";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, ticket.getProjectId());
	        statement.setString(2, ticket.getName());
	        statement.setString(3, ticket.getDescription());
	        statement.setString(4, ticket.getDate());
	        int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
        	closeConnection();
        }
	}

	private boolean isTicketsTableExists() {
		try {
        	// SQL query to check for a table called "projects" in user's local database
            String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='tickets'";
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

	private void createTicketsTable() {
		try {
	        // Create the `tickets` table
	        String createTableQuery = "CREATE TABLE tickets (id INTEGER PRIMARY KEY, projectId INTEGER, name TEXT, description TEXT, due_date DATE)";
	        PreparedStatement createTableStatement = connection.prepareStatement(createTableQuery);
	        createTableStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private Object getTicketByName(String name) {
		try {			
	        // Create a prepared statement to get the project by name
	        String query = "SELECT * FROM tickets WHERE name = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, name);

	        // Execute the prepared statement and get the results
	        ResultSet results = statement.executeQuery();

	        // Check if the results contain a row
	        if (results.next()) {
	            // Create a new Project object from the results
	            Ticket ticket = new Ticket(
	            		results.getInt("id"),
	            		results.getInt("projectId"),
	            		results.getString("name"),
	            		results.getString("description"),
	            		results.getString("due_date")
	            	);

	            return ticket;
	        } else {
	            return null;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
