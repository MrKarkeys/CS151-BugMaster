package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class viewProject {
	public VBox sceneView(Button button) {
		 //create new project page
       VBox newViewProjPage= new VBox(20);
       newViewProjPage.setPadding(new Insets(10));
       newViewProjPage.setAlignment(Pos.CENTER);
       newViewProjPage.setStyle("-fx-background-color: cornsilk");
      
       
       newViewProjPage.getChildren().addAll(button);

       return newViewProjPage;
	}
}
