package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import models.Project;
import models.ProjectModel;

public class ProjectController {
	private ProjectModel projectModel;
	
	public ProjectController() {
        projectModel = new ProjectModel();        
    }
	
	/**
     * @param name
     * @param description
     * @param localDate
     * @return string showing whether project was successfully added or not
     */
	public String handleSubmitButtonClick(String name, String description, LocalDate localDate) {
		// format date in mm/dd/yyyy format
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = localDate.format(format);
        
		projectModel = new ProjectModel();
        boolean success = projectModel.addProject(new Project(name, description, formattedDate));

        if (success) {
            return "Project \"" + name + "\" was successfully added.";
        } else {
            return "Project \"" + name + "\" was not added.";
        }
	}
	
	public boolean handleDeleteButton(Project project) {
		projectModel = new ProjectModel();
		boolean success = projectModel.deleteProject(project);
		return success;
	}
	
	public List<Project> getProjects() {
        return projectModel.getAllProjects();
	}
	
	public List<Project> getProjects(String substring) {
		return projectModel.getAllProjects(substring);
	}
	
	public List<String> getProjectName(){
		ArrayList<String> projNames = new ArrayList<String>();
		List<Project> projs = projectModel.getAllProjects();
		for(int i = 0; i < projs.size(); i++) {
			projNames.add(projs.get(i).getName());
		}
		return projNames;
	}
	
}
