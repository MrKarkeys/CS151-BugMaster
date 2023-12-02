

package views;

import java.util.*;
import controllers.TicketController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import models.Ticket;

public class DisplayTicketsView extends Base{

    public BorderPane render(Scene scene, Button home, Button viewProj, Button projForm, Button viewTic, Button ticForm, Button comForm) {
    	final int MAX_COMPONENTS = 4;
    	
    	//nav bar
        BorderPane mainPane = createBase(home, viewProj, projForm, viewTic, ticForm, comForm);
        viewTic.setStyle("-fx-background-color: WHEAT");

        // table of projects
    	BorderPane centerPane = new BorderPane();
        
        // set up ticketsTable
        TilePane ticketsTable = new TilePane();
        ticketsTable.setHgap(50);
        ticketsTable.setVgap(30);
        
        // header panel 
        VBox ticketsViewHeader = new VBox();
		ticketsViewHeader.setSpacing(5);
		ticketsViewHeader.setAlignment(Pos.CENTER);
        Label label = new Label("list of tickets");
        label.setStyle("-fx-font: normal bold 50px elephant; -fx-text-fill: darkolivegreen;");
        
     // search section
     	HBox searchSection = new HBox();
     	searchSection.setSpacing(5);
     	searchSection.setAlignment(Pos.CENTER);
     	TextField substringInput = new TextField();
        Button toSearchProj = new Button("search tickets by ticket/project name");
        searchSection.getChildren().addAll(substringInput, toSearchProj);
     	
     // edit + delete section
     	HBox editAndDeleteSection = new HBox();
 		editAndDeleteSection.setSpacing(5);
 		editAndDeleteSection.setAlignment(Pos.CENTER);
 		Button editButton = new Button("edit single ticket");
 		Button deleteButton = new Button("delete single ticket");
 		editAndDeleteSection.getChildren().addAll(editButton, deleteButton);
 		
 	// adding items to header
 		ticketsViewHeader.getChildren().addAll(label, searchSection, editAndDeleteSection);
     	centerPane.setTop(ticketsViewHeader);
     	BorderPane.setAlignment(ticketsViewHeader, Pos.CENTER);
     		
     	toSearchProj.setOnAction(e -> {
     		TicketController controller = new TicketController();
            List<Ticket> searchedProjects = controller.getTickets(substringInput.getText());
                 
            // clear the previous projects in table
            ticketsTable.getChildren().clear();
                 
            // display new projects
            if (searchedProjects.size() == 0) { // not found
            	Label notFound = new Label("No tickets found.");
                ticketsTable.getChildren().add(notFound);
            } else {
            	populateTickets(ticketsTable, searchedProjects);
            }

            mainPane.setCenter(centerPane);
        });
     	
     	editButton.setOnAction(e -> {
     		TicketController controller = new TicketController();
     		List<Ticket> ticketToEdit = controller.getTickets(substringInput.getText());
     		Label editStatus;
     		
     		// remove bottom text in preperation for deletion message (if necessary)
     		if (ticketsViewHeader.getChildren().size() >= MAX_COMPONENTS) {
     			ticketsViewHeader.getChildren().remove(MAX_COMPONENTS-1);
     		}
     		
     		// delete listed projects (it should be one project)
     		if (ticketToEdit.size() != 1) {
     			editStatus = new Label("Edit failed. Narrow search to one ticket.");
     			ticketsViewHeader.getChildren().add(editStatus);
     		} else {
     			// redirect to editing form view/page
     			EditTicketFormView editTicketFormView = new EditTicketFormView();
     			scene.setRoot(editTicketFormView.render(home, viewProj, projForm, viewTic, ticForm, comForm, ticketToEdit.get(0)));
     		}
     		
     	});
     	
     	deleteButton.setOnAction(e -> {
     		TicketController controller = new TicketController();
     		List<Ticket> ticketToDelete= controller.getTickets(substringInput.getText());
     		Label deleteStatus;     
     		
     		// remove bottom text in preparation for deletion message (if necessary)
     		if (ticketsViewHeader.getChildren().size() >= MAX_COMPONENTS) {
     			ticketsViewHeader.getChildren().remove(MAX_COMPONENTS-1);
     		}
     		
     		// delete listed projects (it should be one project)
     		if (ticketToDelete.size() != 1) {
     			deleteStatus = new Label("Deletion failed. Narrow search to one ticket.");
     			ticketsViewHeader.getChildren().add(deleteStatus);
     		} else {
     			boolean deleted = controller.handleDeleteButton(ticketToDelete.get(0));
        		if (!deleted) {
        			deleteStatus = new Label("Failed to delete project.");
        			ticketsViewHeader.getChildren().add(deleteStatus);
        		} else {
        			deleteStatus = new Label("Successful deletion of project + it's tickets and comments.");
        			ticketsViewHeader.getChildren().add(deleteStatus);
        			
        			// clear table to give visual representation of deletion
        			ticketsTable.getChildren().clear();
        			populateTickets(ticketsTable, new ArrayList<>());
        		}
     		}
     	});
        
        TicketController controller = new TicketController();
        List<Ticket> tickets = controller.getTickets();
        populateTickets(ticketsTable, tickets);
        centerPane.setCenter(ticketsTable);
        mainPane.setCenter(centerPane);

        return mainPane;
    }
    
    private void populateTickets(TilePane table, List<Ticket> tickets) {
    	for(int i = 0; i < tickets.size(); i++) {
        	VBox ticketBox = new VBox();
        	Label ProjectName = new Label(tickets.get(i).getProjectName());
        	ProjectName.setStyle("-fx-font: normal bold 25.0px elephant; -fx-text-fill: darkolivegreen;");
            Label TicketName = new Label(tickets.get(i).getName());
            TicketName.setStyle("-fx-font: normal bold 22.5px elephant; -fx-text-fill: darkolivegreen;");
            Label ticketDesc = new Label(tickets.get(i).getDescription());
            ticketDesc.setStyle("-fx-font: normal bold 17.5px elephant; -fx-text-fill: darkolivegreen;");
            ticketDesc.setWrapText(true);
            ticketDesc.setMaxWidth(400);
            
            ticketBox.getChildren().addAll(ProjectName, TicketName, ticketDesc);
            table.getChildren().add(ticketBox);
        }
	}
}

