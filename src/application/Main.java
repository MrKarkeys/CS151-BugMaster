package application;
	
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.*;
import javafx.event.*;
import javafx.stage.Stage; 
import java.time.*;

public class Main extends Application {
	Scene sceneHome, scene2CreateProj,scene3ViewProj;
	@Override
	  public void start(Stage s) 
    { 
        // set title for the stage 
        s.setTitle("BugMaster"); 
  
        //home page
        VBox homePage = new VBox(20);
        homePage.setPadding(new Insets(10));
        homePage.setAlignment(Pos.CENTER);
        homePage.setStyle("-fx-background-color: cornsilk");
        Label label1= new Label("HOME PAGE");
        
        //navigation buttons
        Button goToProjectPage= new Button("Create New Project");              
        Button goToHomePage= new Button("Home");
        Button viewProjectPage = new Button("View Projects");
        
       
        
		//create scenes
        projectForm p1 = new projectForm();
        viewProject v1 = new viewProject();
        sceneHome = new Scene(homePage, 500, 450);
        scene2CreateProj = new Scene(p1.sceneView(goToHomePage),600,600);
        scene3ViewProj = new Scene(v1.sceneView(goToHomePage),500,450);
        
        //action buttons
        goToProjectPage.setOnAction(e -> s.setScene(scene2CreateProj));
        goToHomePage.setOnAction(e -> s.setScene(sceneHome));
        viewProjectPage.setOnAction(e -> s.setScene(scene3ViewProj));

        //adding to home page scene
        homePage.getChildren().addAll(label1, goToProjectPage, viewProjectPage);
        //set scene    
        s.setScene(sceneHome);
        s.show(); 
    } 
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
