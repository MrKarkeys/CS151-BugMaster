package controllers;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import models.Comment;
import models.Ticket;
import models.TicketModel;

public class TicketController {
	private TicketModel ticketModel;

	public TicketController() {
        ticketModel = new TicketModel();        
    }

	public String handleSubmitButtonClick(String projectName, String name, String description) {
		// format date in mm/dd/yyyy format
		boolean success = (new TicketModel()).addTicket(new Ticket(projectName, name, description));

		if (success) {
			return "Ticket \"" + name + "\" was successfully added.";
		} else {
			return "Ticket \"" + name + "\" was not added.";
		}
	}

	public List<Ticket> getTickets() {
		return ticketModel.getAllTickets();
	}
	
	public List<Ticket> getTickets(String substring) {
		return ticketModel.getAllTickets(substring);
	}
	
	public List<String> getTicketName(String projName){
		ArrayList<String> tickNames = new ArrayList<String>();
		List<Ticket> tickets = ticketModel.getAllTickets();
		for(int i = 0; i < tickets.size(); i++) {
			if(tickets.get(i).getProjectName().equals(projName)) {
				tickNames.add(tickets.get(i).getName());
			}
		}
		return tickNames;
	}
	
	public String getTicketDes(String ticketName){
		List<Ticket> tickets = ticketModel.getAllTickets();
		String tickDes = null;
		for(int i = 0; i < tickets.size(); i++) {
			if(tickets.get(i).getName().equals(ticketName)) {
				tickDes = tickets.get(i).getDescription();
				return tickDes;
			}
		}
		return tickDes;
	}

	public String handleEditButtonClick(int id, String projectName, String name, String description) {
		ticketModel = new TicketModel();
		boolean success = ticketModel.editTicket(id, new Ticket(projectName, name, description));
		
		if (success) {
            return "Ticket \"" + name + "\" was successfully edited.";
        } else {
            return "Ticket \"" + name + "\" was not edited.";
        }
	}

	public boolean handleDeleteButton(Ticket ticket) {
		ticketModel = new TicketModel();
		boolean success = ticketModel.deleteTicket(ticket);
		return success;
	}

	public List<Comment> getComments(String ticket) {
		ticketModel = new TicketModel();
		return ticketModel.getComments(ticket);
	}
}