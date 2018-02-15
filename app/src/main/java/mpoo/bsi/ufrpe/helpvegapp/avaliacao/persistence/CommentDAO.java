package mpoo.bsi.ufrpe.helpvegapp.avaliacao.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.DatabaseHelper;
import mpoo.bsi.ufrpe.helpvegapp.infra.persistence.QueriesSQL;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.business.RestaurantBusiness;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain.Comment;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.persistence.RestaurantDAO;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;

public class CommentDAO {

    public ArrayList<Comment> getAllCommentsFromRestaurant(int restaurantId) {
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        ArrayList<Comment> comments = new ArrayList<>();
        Cursor cursor = db.rawQuery(QueriesSQL.sqlCommentFromRestaurant(), new String[] {Integer.toString(restaurantId)});

        while (cursor.moveToNext()) {
            Comment comment = new Comment();
            comment.setId(cursor.getInt(0));
            int userId = (cursor.getInt(1));
            comment.setUser(new UserBusiness().getUserById(userId));
            int restaurantsId = (cursor.getInt(2));
            comment.setRestaurant(new RestaurantDAO().getRestaurantById(restaurantsId));

            comment.setCommentText(cursor.getString(3));

            comments.add(comment);
        }

        cursor.close();
        db.close();
        return comments;
    }

    public boolean createComment(Comment comment){
        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.getColumnCommentUserId(), comment.getUser().getUserId());
        values.put(DatabaseHelper.getColumnCommentRestaurantsId(), comment.getRestaurant().getRestaurantId());
        values.put(DatabaseHelper.getColumnCommentText(), comment.getCommentText());

        Boolean response = db.insert(DatabaseHelper.getTableComments(), null, values) != -1;
        db.close();
        return response;
    }

    public void updateComment(Comment comment) {
        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();
        String where = DatabaseHelper.getColumnCommentUserId() + " = " + Integer.toString(Session.getUserIn().getUserId()) + ";";
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.getColumnCommentText(), comment.getCommentText());

        db.update(DatabaseHelper.getTableComments(), values, where, null);
        db.close();
    }

    public void deleteComment(){
        SQLiteDatabase db = DatabaseHelper.getDb().getWritableDatabase();

        String userId = Integer.toString(Session.getUserIn().getUserId());
        String restaurantId = Integer.toString(Session.getCurrentRestaurant().getRestaurantId());

        String where = QueriesSQL.sqlDeleteComment(userId,restaurantId);

        db.delete(DatabaseHelper.getTableComments(), where, null);
        db.close();
    }

    public ArrayList<Comment> getCommentsFromUser(){
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        ArrayList<Comment> comments = new ArrayList<>();
        Cursor cursor = db.rawQuery(QueriesSQL.sqlCommentFromUser(), new String[] {Integer.toString(Session.getUserIn().getUserId())});
        if (cursor.moveToFirst()){
            Comment comment = new Comment();
            comment.setId(cursor.getInt(0));
            comment.setUser(new UserBusiness().getUserById(cursor.getInt(1)));
            comment.setRestaurant(new RestaurantBusiness().getRestaurantFromId(cursor.getInt(2)));
            comment.setCommentText(cursor.getString(3));
            comments.add(comment);
        }
        cursor.close();
        db.close();
        return comments;
    }

    public Comment getCommentFromUserAndRestaurant(){
        SQLiteDatabase db = DatabaseHelper.getDb().getReadableDatabase();
        Comment comment = null;
        String restaurantId = Integer.toString(Session.getCurrentRestaurant().getRestaurantId());
        String userId = Integer.toString(Session.getUserIn().getUserId());
        Cursor cursor = db.rawQuery(QueriesSQL.sqlCommentFromUserAndRestaurant(), new String[] {restaurantId, userId});

        if (cursor.moveToFirst()){
            comment.setId(cursor.getInt(0));
            comment.setUser(new UserBusiness().getUserById(cursor.getInt(1)));
            comment.setRestaurant(new RestaurantBusiness().getRestaurantFromId(cursor.getInt(2)));
            comment.setCommentText(cursor.getString(3));
        }
        cursor.close();
        db.close();
        return comment;
    }

}
