package controllers;

import java.time.LocalDate;
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
		projectModel = new ProjectModel();
        boolean success = projectModel.addProject(new Project(name, description, localDate.toString()));

        if (success) {
            return "Project \"" + name + "\" was successfully added.";
        } else {
            return "Project \"" + name + "\" was not added.";
        }
	}
	
}
