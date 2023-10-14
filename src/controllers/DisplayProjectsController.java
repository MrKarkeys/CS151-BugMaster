package controllers;

import java.util.List;
import models.Project;
import models.ProjectModel;

public class DisplayProjectsController {
    private ProjectModel projectModel;

    public DisplayProjectsController() {
        projectModel = new ProjectModel();        
    }
    
    /**
	 * @return a list of projects from DAO
	 */
	 public List<Project> getProjects() {
	        return projectModel.getAllProjects();
	 }
}
