package models;

public class Project {
    private String name;
    private String description;
    private String date;
	private int id;

    public Project(String name, String description, String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }
    
    public Project(int id, String name, String description, String date) {
    	this(name, description, date);
    	this.id = id;
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

	public int getId() {
		return this.id;
	}
}
