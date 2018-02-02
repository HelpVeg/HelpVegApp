package mpoo.bsi.ufrpe.helpvegapp.restaurant.gui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.infra.MyApp;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.business.CommentBusiness;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.Comment;
import mpoo.bsi.ufrpe.helpvegapp.restaurant.domain.ItemClickListener;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private List<Comment> listComments;
    private CommentBusiness commentBusiness = new CommentBusiness();
    private CommentAdapter commentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        this.mViewHolder.recyclerView =  findViewById(R.id.commentList);
        this.mViewHolder.btnAddComment = findViewById(R.id.btnAddComment);
        this.mViewHolder.btnAddComment.setOnClickListener(this);

        createAdapter();
        createCommentList();
    }

    public void createAdapter(){
        listComments = commentBusiness.getAllCommentsFromRestautant(Session.getCurrentRestaurant().getRestaurantId());
        commentAdapter = new CommentAdapter(MyApp.getContext(), listComments, new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }
        });
    }

    public void createCommentList(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        this.mViewHolder.recyclerView.setLayoutManager(mLayoutManager);
        this.mViewHolder.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.mViewHolder.recyclerView.setAdapter(commentAdapter);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnAddComment){
            createCommentDialog();
        }
    }


    public void createCommentDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.insert_comentario, null);
        final TextView comment = view.findViewById(R.id.comment);
        builder.setTitle("Adicione um comentário");
        builder.setPositiveButton("Adicionar comentário", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                confirmComment(comment.getText().toString());
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
               dialog.dismiss();
            }
        });
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void confirmComment(String comment){
        commentBusiness.registerComment(comment);
    }

    private static class ViewHolder{
        private RecyclerView recyclerView;
        private FloatingActionButton btnAddComment;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,RestaurantActivity.class);
        startActivity(intent);
        finish();
    }

}
