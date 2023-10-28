package views;

import java.util.*;

import controllers.ProjectController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import models.Project;

public class DisplayProjectsView extends Base{

    public BorderPane render(Button home, Button viewProj, Button projForm, Button ticForm, Button comForm) {
    	
    	//nav bar
        BorderPane mainPane = createBase(home, viewProj, projForm,ticForm, comForm);
        viewProj.setStyle("-fx-background-color: WHEAT");

        // table of projects
    	BorderPane centerPane = new BorderPane();
        Label label = new Label("list of projects");
        label.setStyle("-fx-font: normal bold 50px elephant; -fx-text-fill: darkolivegreen;");
        centerPane.setTop(label);
        BorderPane.setAlignment(label, Pos.CENTER);
        
        TilePane projectsTable = new TilePane();
        projectsTable.setHgap(50);
        projectsTable.setVgap(30);
        
        ProjectController controller = new ProjectController();
        List<Project> Projects = controller.getProjects();
        for(int i = 0; i < Projects.size(); i++) {
        	VBox projectBox = new VBox();
            Label projectName = new Label(Projects.get(i).getName());
            projectName.setStyle("-fx-font: normal bold 22.5px elephant; -fx-text-fill: darkolivegreen;");
            Label projectDesc = new Label(Projects.get(i).getDescription());
            projectDesc.setStyle("-fx-font: normal bold 17.5px elephant; -fx-text-fill: darkolivegreen;");
            projectDesc.setWrapText(true);
            projectDesc.setMaxWidth(400);
            Label projectLabel = new Label(Projects.get(i).getDate());
            projectLabel.setStyle("-fx-font: normal bold 15px elephant; -fx-text-fill: darkolivegreen;");
            
            projectBox.getChildren().addAll(projectName, projectDesc, projectLabel);
            projectsTable.getChildren().add(projectBox);
        }
        centerPane.setCenter(projectsTable);
        mainPane.setCenter(centerPane);

        return mainPane;
    }
}

