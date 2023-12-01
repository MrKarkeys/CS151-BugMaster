
package views;

import java.util.*;

import controllers.ProjectController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import models.Project;

public class DisplayProjectsView extends Base {

	public BorderPane render(Scene scene, Button home, Button viewProj, Button projForm, Button viewTic, Button ticForm, Button comForm) {
		final int MAX_COMPONENTS = 4;
		
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

		// edit + delete section
		HBox editAndDeleteSection = new HBox();
		editAndDeleteSection.setSpacing(5);
		editAndDeleteSection.setAlignment(Pos.CENTER);
		Button editButton = new Button("edit single project");
        Button deleteButton = new Button("delete single project");
        editAndDeleteSection.getChildren().addAll(editButton, deleteButton);
        
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
        
        editButton.setOnAction(e -> {
        	ProjectController controller = new ProjectController();
        	List<Project> projectToEdit = controller.getProjects(substringInput.getText());
        	Label editStatus;
        	
        	// remove bottom text in preparation for deletion message (if necessary)
        	if (projectsViewHeader.getChildren().size() >= MAX_COMPONENTS) {
        		projectsViewHeader.getChildren().remove(MAX_COMPONENTS-1);
        	}
            
            // delete listed projects (it should be one project)
        	if (projectToEdit.size() != 1) {
        		editStatus = new Label("Edit failed. Narrow search to one project.");
        		projectsViewHeader.getChildren().add(editStatus);
        	} else {
        		// redirect to editing form view/page
        		EditProjectFormView editFormView = new EditProjectFormView();
				scene.setRoot(editFormView.render(home, viewProj, projForm, viewTic, ticForm, comForm, projectToEdit.get(0)));
        	}
        });
        
        deleteButton.setOnAction(e -> {
        	ProjectController controller = new ProjectController();
        	List<Project> projectToDelete = controller.getProjects(substringInput.getText());
        	Label deleteStatus;
        	
        	// remove bottom text in preparation for deletion message (if necessary)
        	if (projectsViewHeader.getChildren().size() >= MAX_COMPONENTS) {
        		projectsViewHeader.getChildren().remove(MAX_COMPONENTS-1);
        	}
            
            // delete listed projects (it should be one project)
        	if (projectToDelete.size() != 1) {
        		deleteStatus = new Label("Deletion failed. Narrow search to one project.");
        		projectsViewHeader.getChildren().add(deleteStatus);
        	} else {
        		boolean deleted = controller.handleDeleteButton(projectToDelete.get(0));
        		if (!deleted) {
        			deleteStatus = new Label("Failed to delete project.");
        			projectsViewHeader.getChildren().add(deleteStatus);
        		} else {
        			deleteStatus = new Label("Successful deletion of project + it's tickets and comments.");
        			projectsViewHeader.getChildren().add(deleteStatus);
        			
        			// clear table to give visual representation of deletion
        			projectsTable.getChildren().clear();
        			populateProjects(projectsTable, new ArrayList<>());
        		}
        	}
        });
        
        // add search, delete section to (top of) page
		projectsViewHeader.getChildren().addAll(label, searchSection, editAndDeleteSection);
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
