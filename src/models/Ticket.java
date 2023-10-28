package models;

public class Ticket {
	int id;
	String projectName;
	String name;
	String description;
	String date;
	
	/**
	 * @param projectId the SQL key (id) for the project row
	 * @param name
	 * @param description
	 * @param date
	 */
	public Ticket(String projectName, String name, String description, String date) {
		this.projectName = projectName;
		this.name = name;
		this.description = description;
		this.date = date;
	}
	
	public Ticket(int id, String projectName, String name, String description, String date) {
		this(projectName, name, description, date);
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
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
	
	public Integer getId() {
		return id;
	}
}