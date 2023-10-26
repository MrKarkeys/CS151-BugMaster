package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import daos.ProjectDAO;
import models.Project;
import models.ProjectModel;
import models.Ticket;

public class TicketFormController {
	public String handleSubmitButtonClick(int projectId, String name, String description, LocalDate localDate) {
		// format date in mm/dd/yyyy format
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = localDate.format(format);
        
        boolean success = (new ProjectDAO()).insertTicket(new Ticket(projectId, name, description, formattedDate));

        if (success) {
            return "Project \"" + name + "\" was successfully added.";
        } else {
            return "Project \"" + name + "\" was not added.";
        }
	}
}
