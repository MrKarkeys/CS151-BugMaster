package models;

public class Ticket {
	int id;
	int projectId;
	String name;
	String description;
	String date;
	
	/**
	 * @param projectId the SQL key (id) for the project row
	 * @param name
	 * @param description
	 * @param date
	 */
	public Ticket(int projectId, String name, String description, String date) {
		this.projectId = projectId;
		this.name = name;
		this.description = description;
		this.date = date;
	}
	
	public Ticket(int id, int projectId, String name, String description, String date) {
		this(projectId, name, description, date);
		this.id = id;
	}

	public int getProjectId() {
		return projectId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getDate() {
		return date;
	}
}
