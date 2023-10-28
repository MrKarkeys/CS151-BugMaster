package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import models.Ticket;
import models.TicketModel;

public class TicketController {
	private TicketModel ticketModel;

	public TicketController() {
        ticketModel = new TicketModel();        
    }

	public String handleSubmitButtonClick(String projectName, String name, String description, LocalDate localDate) {
		// format date in mm/dd/yyyy format
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedDate = localDate.format(format);

		boolean success = (new TicketModel()).addTicket(new Ticket(projectName, name, description, formattedDate));

		if (success) {
			return "Ticket \"" + name + "\" was successfully added.";
		} else {
			return "Ticket \"" + name + "\" was not added.";
		}
	}

	public List<Ticket> getTickets() {
		return ticketModel.getAllTickets();
	}
}