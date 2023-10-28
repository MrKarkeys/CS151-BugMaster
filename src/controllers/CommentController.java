package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import models.CommentModel;
import models.Comment;

public class CommentController {
	private CommentModel commentModel;

	public CommentController() {
		commentModel = new CommentModel();        
    }

	public String handleSubmitButtonClick(String ticketName, String description, LocalDate localDate) {
		// format date in mm/dd/yyyy format
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedDate = localDate.format(format);

		boolean success = (new CommentModel()).addComment(new Comment(ticketName, description, formattedDate));
		if (success) {
			return "Comment was successfully added.";
		} else {
			return "Comment was not added.";
		}

		
		
	}

	public List<Comment> getComments() {
		return commentModel.getAllComments();
	}
}