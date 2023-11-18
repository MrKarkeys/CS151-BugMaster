
package views;

import java.util.*;

import controllers.ProjectController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import models.Project;

public class DisplayProjectsView extends Base {

	public BorderPane render(Button home, Button viewProj, Button projForm, Button viewTic, Button ticForm, Button comForm) {
		// nav bar
		BorderPane mainPane = createBase(home, viewProj, projForm, viewTic, ticForm, comForm);
		viewProj.setStyle("-fx-background-color: WHEAT");

		// table of projects
		BorderPane centerPane = new BorderPane();
		
		// set up projectsTable
		TilePane projectsTable = new TilePane();
		projectsTable.setHgap(50);
		projectsTable.setVgap(30);
		
		// header panel
		VBox projectsViewHeader = new VBox();
		projectsViewHeader.setSpacing(5);
		projectsViewHeader.setAlignment(Pos.CENTER);
		Label label = new Label("list of projects");
		label.setStyle("-fx-font: normal bold 50px elephant; -fx-text-fill: darkolivegreen;");
		
		// search section
		HBox searchSection = new HBox();
		searchSection.setSpacing(5);
		searchSection.setAlignment(Pos.CENTER);
		TextField substringInput = new TextField();
        Button toSearchProj = new Button("search projects");
        searchSection.getChildren().addAll(substringInput, toSearchProj);

		// delete section
		HBox deleteSection = new HBox();
		deleteSection.setSpacing(5);
		deleteSection.setAlignment(Pos.CENTER);
        Button deleteButton = new Button("delete single project");
        deleteSection.getChildren().addAll(deleteButton);
        
        toSearchProj.setOnAction(e -> {
        	ProjectController controller = new ProjectController();
            List<Project> searchedProjects = controller.getProjects(substringInput.getText());
            
            // clear the previous projects in table
            projectsTable.getChildren().clear();
            
            // display new projects
            if (searchedProjects.size() == 0) { // not found
            	Label notFound = new Label("No projects found.");
            	projectsTable.getChildren().add(notFound);
            } else {
            	populateProjects(projectsTable, searchedProjects);
            }
        });
        
        deleteButton.setOnAction(e -> {
        	ProjectController controller = new ProjectController();
        	List<Project> projectToDelete = controller.getProjects(substringInput.getText());
            
            // delete listed projects (it should be one project)
        	if (projectToDelete.size() != 1) {
        		Label deleteStatus = new Label("Deletion failed. Narrow search to one project.");
        		deleteSection.getChildren().add(deleteStatus);
        	} else {
        		boolean deleted = controller.handleDeleteButton(projectToDelete.get(0));
        		if (!deleted) {
        			Label deleteStatus = new Label("Failed to delete project.");
        			deleteSection.getChildren().add(deleteStatus);
        		} else {
        			Label deleteStatus = new Label("Deleted project, tickets, and comments.");
        			deleteSection.getChildren().add(deleteStatus);
        		}
        	}
        });
        
        // add search, delete section to (top of) page
		projectsViewHeader.getChildren().addAll(label, searchSection, deleteSection);
		centerPane.setTop(projectsViewHeader);
		BorderPane.setAlignment(projectsViewHeader, Pos.CENTER);
		ProjectController controller = new ProjectController();
		List<Project> projects = controller.getProjects();
		populateProjects(projectsTable, projects);
		centerPane.setCenter(projectsTable);
		mainPane.setCenter(centerPane);

		return mainPane;
	}
	
	private void populateProjects(TilePane table, List<Project> projects) {
		if (projects.size() == 0) {
			Label notFound = new Label("No projects found.");
        	table.getChildren().add(notFound);
		}
		for (int i = 0; i < projects.size(); i++) {
			VBox projectBox = new VBox();
			Label projectName = new Label(projects.get(i).getName());
			projectName.setStyle("-fx-font: normal bold 22.5px elephant; -fx-text-fill: darkolivegreen;");
			Label projectDesc = new Label(projects.get(i).getDescription());
			projectDesc.setStyle("-fx-font: normal bold 17.5px elephant; -fx-text-fill: darkolivegreen;");
			projectDesc.setWrapText(true);
			projectDesc.setMaxWidth(400);
			Label projectLabel = new Label(projects.get(i).getDate());
			projectLabel.setStyle("-fx-font: normal bold 15px elephant; -fx-text-fill: darkolivegreen;");

			projectBox.getChildren().addAll(projectName, projectDesc, projectLabel);
			table.getChildren().add(projectBox);
		}
	}
}
