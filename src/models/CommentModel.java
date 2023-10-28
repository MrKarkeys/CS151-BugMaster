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
}
