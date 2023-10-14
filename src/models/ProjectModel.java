package models;

import java.util.List;
import daos.ProjectDAO;

public class ProjectModel {
	private ProjectDAO projectDAO;

    public ProjectModel() {
        projectDAO = new ProjectDAO();
    }

    public boolean isDBConnected() {
        return projectDAO.validDBConnection();
    }
    
    public List<Project> getAllProjects() {
        return projectDAO.getAllProjects();
    }

    public boolean addProject(Project project) {
        return projectDAO.insertProject(project);
    }
}