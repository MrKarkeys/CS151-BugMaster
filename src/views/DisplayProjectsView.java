package views;

import java.util.*;

import controllers.DisplayProjectsController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import models.Project;

public class DisplayProjectsView extends Base{

    public BorderPane render(Button toHomepage, Button toViewProj, Button toProjForm) {
        
    	//content
        Label label = new Label("list of projs");
        
        VBox centerPane = new VBox();
        centerPane.getChildren().add(label);
    
        //nav bar
        BorderPane mainPane = createBase(toHomepage, toViewProj, toProjForm);
        DisplayProjectsController controller = new DisplayProjectsController();
        List<Project> Projects = controller.getProjects();
        for(int i = 0; i < Projects.size(); i++) {
            Label Projectlable = new Label(Projects.get(i).getName() + " " 
        + Projects.get(i).getDescription() + " " + Projects.get(i).getDate());
        	centerPane.getChildren().add(Projectlable);
        }
        mainPane.setCenter(centerPane);
        

        return mainPane;
    }
}

