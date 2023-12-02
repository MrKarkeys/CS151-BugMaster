package models;

public class Ticket {
	int id;
	String projectName;
	String name;
	String description;
	
	/**
	 * @param projectId the SQL key (id) for the project row
	 * @param name
	 * @param description
	 * @param date
	 */
	public Ticket(String projectName, String name, String description) {
		this.projectName = projectName;
		this.name = name;
		this.description = description;
	}
	
	public Ticket(int id, String projectName, String name, String description) {
		this(projectName, name, description);
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
	
	public Integer getId() {
		return id;
	}
}