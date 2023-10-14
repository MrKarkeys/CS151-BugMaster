package views;

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

public class DisplayProjectsView extends Base{

    public BorderPane render(Button toHomepage, Button toViewProj, Button toProjForm) {
        
    	//content
        Label label = new Label("list of projs");
        
        StackPane centerPane = new StackPane();
        centerPane.getChildren().add(label);
    
        //nav bar
        BorderPane mainPane = createBase(toHomepage, toViewProj, toProjForm);
       
        mainPane.setCenter(centerPane);

        return mainPane;
    }
}

