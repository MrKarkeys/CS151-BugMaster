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
    
    public boolean addProject(Project project) {
        return projectDAO.insertProject(project);
    }
    
    public boolean editProject(String name, Project project) {
    	return projectDAO.editProject(name, project);
    }
    
    public boolean deleteProject(Project project) {
    	return projectDAO.deleteProject(project);
    }
    
    public List<Project> getAllProjects() {
        return projectDAO.getAllProjects();
    }
    
    public List<Project> getAllProjects(String substring) {
    	return projectDAO.getAllProjects(substring);
    }
    
    public void createtables() {
		projectDAO.createAllTables();
	}
}
