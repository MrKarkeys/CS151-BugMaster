package application;
	
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.*; 
import javafx.geometry.*;
import javafx.event.*;
import javafx.stage.Stage; 
import java.time.*;

public class Main extends Application {
	Scene scene1, scene2;
	@Override
	  public void start(Stage s) 
    { 
        // set title for the stage 
        s.setTitle("bugmaster"); 
  
        //home page
        VBox homePage = new VBox(20);
        homePage.setPadding(new Insets(10));
        homePage.setAlignment(Pos.CENTER);
        Label label1= new Label("home page");
        Button goToProjectPage= new Button("create new project");              
       
       //create new project page
        VBox newProjectPage= new VBox(20);
        newProjectPage.setPadding(new Insets(10));
        newProjectPage.setAlignment(Pos.CENTER);
        Label pName= new Label("enter the name");
        Label pDesc= new Label("enter the description");
        Label pDate= new Label("enter the starting date");

        //text fields and date picker for project info
        TextField projectName = new TextField();
        TextArea projectDescription = new TextArea();
        DatePicker projectStartDate= new DatePicker();
        projectStartDate.setValue(java.time.LocalDate.now());
        
        //save button
        Button submitProj = new Button("Submit");   
        
		//create scene
        scene1 = new Scene(homePage, 500, 450);
        scene2 = new Scene(newProjectPage,500,450);
        
        //action buttons
        goToProjectPage.setOnAction(e -> s.setScene(scene2));  
        submitProj.setOnAction(e -> {projectName.clear(); projectDescription.clear(); projectStartDate.setValue(java.time.LocalDate.now());});

        
        //adding to scene
        homePage.getChildren().addAll(label1, goToProjectPage);
        newProjectPage.getChildren().addAll(pName, projectName, pDesc, projectDescription, pDate, projectStartDate, submitProj);

        //set scene    
        s.setScene(scene1);
        s.show(); 
    } 
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
