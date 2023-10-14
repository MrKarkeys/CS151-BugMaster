package models;

import java.sql.*;
import config.SqliteConnection;

public class ProjectModel {
	Connection connection;
	
	public ProjectModel () {
		connection = SqliteConnection.Connector();
		if (connection == null) System.exit(1); // exit application if database fails to connect
	}
	
	public boolean isDBConnected() {
		try {
			return !connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
