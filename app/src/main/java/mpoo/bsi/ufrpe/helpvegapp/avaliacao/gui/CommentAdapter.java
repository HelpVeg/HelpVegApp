package mpoo.bsi.ufrpe.helpvegapp.avaliacao.gui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.avaliacao.domain.Comment;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>  {
    private List<Comment> comments;
    private Context context;
    private ItemClickListener listener;

    public CommentAdapter(Context context, List<Comment> listComments, ItemClickListener listener){
        this.comments = listComments;
        this.context = context;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView comment;
        private TextView userName;
        private CircleImageView userPhoto;


        public ViewHolder(View view) {
            super(view);
            userName = view.findViewById(R.id.nameProfileCard);
            comment = view.findViewById(R.id.commentCard);
            userPhoto = view.findViewById(R.id.imageProfileCard);
        }

        @Override
        public void onClick(View view) {
        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_row, parent, false);
        final ViewHolder viewHolder = new ViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v , viewHolder.getPosition());
            }
        });
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder myHolder, int position) {
        Comment comment = comments.get(position);
        myHolder.userName.setText(comment.getUser().getUserName());
        myHolder.comment.setText(comment.getCommentText());
        if (comment.getUser().getUserPhoto() !=null) {
            myHolder.userPhoto.setImageBitmap(comment.getUser().getUserPhoto());
        }

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void insertItem(Comment comment) {
        comments.add(comment);
        notifyItemInserted(getItemCount());
    }
}
