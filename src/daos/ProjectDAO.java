package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.SqliteConnection;
import models.Comment;
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
	 * @return a list of projects based on a user input (substring)
	 */
	public List<Project> getAllProjects(String substring) {
		List<Project> projects = new ArrayList<>();
		try {
			if (!isProjectsTableExists()) {
				createProjectsTable();
			}
		    String query = "SELECT * FROM projects WHERE name LIKE ?";
		    PreparedStatement preparedStatement = connection.prepareStatement(query); // used type PreparedStatement due to dynamic substring
	        preparedStatement.setString(1, "%" + substring + "%");
	        ResultSet entries = preparedStatement.executeQuery();		        
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
	public boolean isProjectsTableExists() {
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

	public void createProjectsTable() {
	    try {
	        // Create the `projects` table
	        String createTableQuery = "CREATE TABLE projects (id INTEGER PRIMARY KEY, name TEXT, description TEXT, due_date DATE)";
	        PreparedStatement createTableStatement = connection.prepareStatement(createTableQuery);
	        createTableStatement.executeUpdate();

	        // Insert the projects
	        String[] insertQueries = {
	                "INSERT INTO projects (name, description, due_date) VALUES ('P1 Name', 'Really cool project', '12/31/2025')",
	                "INSERT INTO projects (name, description, due_date) VALUES ('P2 Name', 'Kinda cool project', '12/31/2025')",
	                "INSERT INTO projects (name, description, due_date) VALUES ('P3 Name', 'Super cool project', '12/31/2025')",
	                "INSERT INTO projects (name, description, due_date) VALUES ('P4 Name', 'Not a cool project', '12/31/2025')"
	        };

	        for (String insertQuery : insertQueries) {
	            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
	            insertStatement.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
    
    //TICKET DAO
    /** 
	 * @return a list of projects
	 */
	public List<Ticket> getAllTickets() {
		List<Ticket> tickets = new ArrayList<>();
		try {
			if (!isTicketsTableExists()) {
				createTicketsTable();
			}
		    String query = "SELECT * FROM tickets";
		    Statement statement = connection.createStatement();
	        ResultSet entries = statement.executeQuery(query);		        
	        while (entries.next()) {
	        	Ticket ticket = new Ticket(entries.getInt("id"), entries.getString("projectName"), entries.getString("name"), entries.getString("description"), entries.getString("due_date"));
	        	tickets.add(ticket);
	        }
		 } catch (SQLException e) {
		     e.printStackTrace();
		 } finally {
			 closeConnection();
	     }
		return tickets;
	}
	
	/** 
	 * @return a list of projects
	 */
	public List<Ticket> getAllTickets(String substring) {
		List<Ticket> tickets = new ArrayList<>();
		try {
			if (!isTicketsTableExists()) {
				createTicketsTable();
			}
			// get tickets based on ticket title
		    String query = "SELECT * FROM tickets WHERE name LIKE ? OR projectName LIKE ?";
		    PreparedStatement preparedStatement = connection.prepareStatement(query);
		    preparedStatement.setString(1, "%" + substring + "%");
		    preparedStatement.setString(2, "%" + substring + "%");
		    ResultSet entries = preparedStatement.executeQuery();
	        while (entries.next()) {
	        	Ticket ticket = new Ticket(entries.getInt("id"), entries.getString("projectName"), entries.getString("name"), entries.getString("description"), entries.getString("due_date"));
	        	tickets.add(ticket);
	        }
	        
	        // get tickets based on project title
		 } catch (SQLException e) {
		     e.printStackTrace();
		 } finally {
			 closeConnection();
	     }
		return tickets;
	}
	
	
    public boolean insertTicket(Ticket ticket) {
		try {
	    	if (!isTicketsTableExists()) {
	    		createTicketsTable();
	        }
	        String query = "INSERT INTO tickets (projectName, name, description, due_date) VALUES (?, ?, ?, ?)";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, ticket.getProjectName());
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

	public boolean isTicketsTableExists() {
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

	
	public void createTicketsTable() {
		try {
	        // Create the `tickets` table
	        String createTableQuery = "CREATE TABLE tickets (id INTEGER PRIMARY KEY, projectName TEXT , name TEXT, description TEXT, due_date DATE)";
	        PreparedStatement createTableStatement = connection.prepareStatement(createTableQuery);
	        createTableStatement.executeUpdate();
	        
	        String[] insertQueries = {
	        		"INSERT INTO tickets (projectName, name, description, due_date) VALUES ('P1 Name', 'TicketName1', 'first really cool ticket', '12/31/2025')",
	        		"INSERT INTO tickets (projectName, name, description, due_date) VALUES ('P1 Name', 'TicketName2', 'Second really cool ticket', '12/31/2025')",
	        		"INSERT INTO tickets (projectName, name, description, due_date) VALUES ('P2 Name', 'TicketName3', 'first really cool ticket', '12/31/2025')",
	        		"INSERT INTO tickets (projectName, name, description, due_date) VALUES ('P2 Name', 'TicketName4', 'Second really cool ticket', '12/31/2025')",
	        		"INSERT INTO tickets (projectName, name, description, due_date) VALUES ('P3 Name', 'TicketName5', 'first really cool ticket', '12/31/2025')",
	        		"INSERT INTO tickets (projectName, name, description, due_date) VALUES ('P3 Name', 'TicketName6', 'Second really cool ticket', '12/31/2025')",
	        		"INSERT INTO tickets (projectName, name, description, due_date) VALUES ('P4 Name', 'TicketName7', 'first really cool ticket', '12/31/2025')",
	        		"INSERT INTO tickets (projectName, name, description, due_date) VALUES ('P4 Name', 'TicketName8', 'Second really cool ticket', '12/31/2025')"
	        };

	        for (String insertQuery : insertQueries) {
	            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
	            insertStatement.executeUpdate();
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
    //COMMENT DAO
    /** 
	 * @return a list of projects
	 */
	public List<Comment> getAllComments() {
		List<Comment> comments = new ArrayList<>();
		try {
			if (!isCommentsTableExists()) {
				createCommentsTable();
			}
		    String query = "SELECT * FROM comments";
		    Statement statement = connection.createStatement();
	        ResultSet entries = statement.executeQuery(query);		        
	        while (entries.next()) {
	        	Comment comment = new Comment(entries.getInt("id"), entries.getString("TicketName"), entries.getString("description"), entries.getString("due_date"));
	        	comments.add(comment);
	        }
		 } catch (SQLException e) {
		     e.printStackTrace();
		 } finally {
			 closeConnection();
	     }
		return comments;
	}
	
    public boolean insertComments(Comment comment) {
		try {
	    	if (!isCommentsTableExists()) {
	    		createCommentsTable();
	        }
	        String query = "INSERT INTO comments (ticketName, description, due_date) VALUES (?, ?, ?)";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, comment.getticketName());
	        statement.setString(2, comment.getDescription());
	        statement.setString(3, comment.getDate());
	        int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
        	closeConnection();
        }
	}

	public boolean isCommentsTableExists() {
		try {
        	// SQL query to check for a table called "projects" in user's local database
            String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='comments'";
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

	
	public void createCommentsTable() {
		try {
	        // Create the `tickets` table
	        String createTableQuery = "CREATE TABLE comments (id INTEGER PRIMARY KEY, ticketName TEXT, description TEXT, due_date DATE)";
	        PreparedStatement createTableStatement = connection.prepareStatement(createTableQuery);
	        createTableStatement.executeUpdate();
	        
	        String[] insertQueries = {
	        		"INSERT INTO comments (ticketName, description, due_date) VALUES ('TicketName1', 'first really cool comment', '12/31/2025 15:11:20')",
	        		"INSERT INTO comments (ticketName, description, due_date) VALUES ('TicketName1', 'Second really cool comment', '12/31/2025 15:11:30')",
	        		"INSERT INTO comments (ticketName, description, due_date) VALUES ('TicketName2', 'first really cool comment', '12/31/2025 15:11:40')",
	        		"INSERT INTO comments (ticketName, description, due_date) VALUES ('TicketName2', 'Second really cool comment', '12/31/2025 15:11:50')",
	        		"INSERT INTO comments (ticketName, description, due_date) VALUES ('TicketName3', 'first really cool comment', '12/31/2025 15:12:20')",
	        		"INSERT INTO comments (ticketName, description, due_date) VALUES ('TicketName3', 'Second really cool comment', '12/31/2025 15:12:30')",
	        		"INSERT INTO comments (ticketName, description, due_date) VALUES ('TicketName4', 'first really cool comment', '12/31/2025 15:12:40')",
	        		"INSERT INTO comments (ticketName, description, due_date) VALUES ('TicketName4', 'Second really cool comment', '12/31/2025 15:15:50')",
	        		"INSERT INTO comments (ticketName, description, due_date) VALUES ('TicketName5', 'first really cool comment', '12/31/2025 15:16:40')",
	        		"INSERT INTO comments (ticketName, description, due_date) VALUES ('TicketName5', 'Second really cool comment', '12/31/2025 15:17:50')",
	        		"INSERT INTO comments (ticketName, description, due_date) VALUES ('TicketName6', 'first really cool comment', '12/31/2025 15:18:40')",
	        		"INSERT INTO comments (ticketName, description, due_date) VALUES ('TicketName6', 'Second really cool comment', '12/31/2025 15:18:50')",
	        		"INSERT INTO comments (ticketName, description, due_date) VALUES ('TicketName7', 'first really cool comment', '12/31/2025 15:19:40')",
	        		"INSERT INTO comments (ticketName, description, due_date) VALUES ('TicketName7', 'Second really cool comment', '12/31/2025 15:19:50')",
	        		"INSERT INTO comments (ticketName, description, due_date) VALUES ('TicketName8', 'first really cool comment', '12/31/2025 15:20:40')",
	        		"INSERT INTO comments (ticketName, description, due_date) VALUES ('TicketName8', 'Second really cool comment', '12/31/2025 15:20:50')"
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
