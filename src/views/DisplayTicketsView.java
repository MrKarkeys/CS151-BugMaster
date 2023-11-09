

package views;

import java.util.*;

import controllers.ProjectController;
import controllers.TicketController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import models.Project;
import models.Ticket;

public class DisplayTicketsView extends Base{

    public BorderPane render(Button home, Button viewProj, Button projForm, Button viewTic, Button ticForm, Button comForm) {
    	
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
        Button toSearchProj = new Button("search projects");
        searchSection.getChildren().addAll(substringInput, toSearchProj);
        ticketsViewHeader.getChildren().addAll(label, searchSection);
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
            Label ticketLabel = new Label(tickets.get(i).getDate());
            ticketLabel.setStyle("-fx-font: normal bold 15px elephant; -fx-text-fill: darkolivegreen;");
            
            ticketBox.getChildren().addAll(ProjectName, TicketName, ticketDesc, ticketLabel);
            table.getChildren().add(ticketBox);
        }
	}
}

