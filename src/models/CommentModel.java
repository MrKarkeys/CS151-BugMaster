package models;

import java.util.List;
import daos.ProjectDAO;

public class CommentModel {
	private static ProjectDAO projectDAO;

    public CommentModel() {
        projectDAO = new ProjectDAO();
    }

    public boolean isDBConnected() {
        return projectDAO.validDBConnection();
    }
    
    public List<Comment> getAllComments(){
        return projectDAO.getAllComments();
    }

    public boolean addComment(Comment comment) {
        return projectDAO.insertComments(comment);
    }

	public boolean delete(int id) {
		return projectDAO.deleteComment(id);
	}

	public boolean edit(int id, String description, String date) {
		return projectDAO.editComment(id, description, date);
	}
}
