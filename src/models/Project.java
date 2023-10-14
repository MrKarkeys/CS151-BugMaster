package models;

public class Project {
    private String name;
    private String description;
    private String date;

    public Project(String name, String description, String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }
    
    public String getName() {
    	return this.name;
    }

	public String getDescription() {
		return this.description;
	}
	
	public String getDate() {
		return this.date;
	}
}
