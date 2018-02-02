package mpoo.bsi.ufrpe.helpvegapp.avaliacao.business;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain.Comment;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.persistence.CommentDAO;

public class CommentBusiness {

    private CommentDAO commentDAO = new CommentDAO();

    public CommentDAO getCommentDAO() {
        return commentDAO;
    }

    public Comment generateComment(String commentText){
        Comment comment = new Comment();
        comment.setUser(Session.getUserIn());
        comment.setRestaurant(Session.getCurrentRestaurant());
        comment.setCommentText(commentText);
        return comment;
    }

    public boolean registerComment(String commentText){
        Comment comment = generateComment(commentText);
        return getCommentDAO().createComment(comment);
    }

    public ArrayList<Comment> getAllCommentsFromRestautant(int restaurantId){
        return getCommentDAO().getAllCommentsFromRestaurant(restaurantId);
    }
}
