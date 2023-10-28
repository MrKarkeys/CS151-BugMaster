package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import daos.ProjectDAO;
import models.Ticket;

public class TicketController {
	public String handleSubmitButtonClick(String projectName, String name, String description, LocalDate localDate) {
		// format date in mm/dd/yyyy format
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = localDate.format(format);
        
        boolean success = (new ProjectDAO()).insertTicket(new Ticket(projectName, name, description, formattedDate));

        if (success) {
            return "Ticket \"" + name + "\" was successfully added.";
        } else {
            return "Ticket \"" + name + "\" was not added.";
        }
	}
}