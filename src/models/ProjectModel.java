package models;

import java.util.List;
import daos.ProjectDAO;

public class ProjectModel {
	private static ProjectDAO projectDAO;

    public ProjectModel() {
        projectDAO = new ProjectDAO();
    }

    public boolean isDBConnected() {
        return projectDAO.validDBConnection();
    }
    
    public static List<Project> getAllProjects() {
        return projectDAO.getAllProjects();
    }

    public boolean addProject(Project project) {
        return projectDAO.insertProject(project);
    }
}
