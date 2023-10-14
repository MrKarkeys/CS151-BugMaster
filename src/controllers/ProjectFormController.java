package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import models.Project;
import models.ProjectModel;

public class ProjectFormController {
	private ProjectModel projectModel;
	
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
	
}
