package controllers;

import java.time.LocalDate;
import java.util.List;

import models.Project;
import models.ProjectModel;

public class DisplayProjectsController {
    private ProjectModel projectModel;

    public DisplayProjectsController() {
        projectModel = new ProjectModel();
    }

    public List<Project> getProjects() {
        return projectModel.getAllProjects();
    }

	public String handleSubmitButtonClick(String name, String description, LocalDate localDate) {
		ProjectModel projectModel = new ProjectModel();
        boolean success = projectModel.addProject(new Project(name, description, localDate.toString()));

        if (success) {
            return "Project \"" + name + "\" was successfully added.";
        } else {
            return "Project \"" + name + "\" was not added.";
        }
	}
}
