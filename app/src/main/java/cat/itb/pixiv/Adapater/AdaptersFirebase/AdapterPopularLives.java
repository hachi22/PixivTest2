package cat.itb.pixiv.Adapater.AdaptersFirebase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import cat.itb.pixiv.ClassesModels.IllustrationPLClass;
import cat.itb.pixiv.ClassesModels.ImatgesP;
import cat.itb.pixiv.R;

public class AdapterPopularLives extends FirebaseRecyclerAdapter<IllustrationPLClass,AdapterPopularLives.ViewHolderPopularLives> {

    private IllustrationPLClass model;
    private Context context;


    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public AdapterPopularLives(@NonNull FirebaseRecyclerOptions<IllustrationPLClass> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterPopularLives.ViewHolderPopularLives holder, int position, @NonNull IllustrationPLClass model) {
        this.model = model;
        holder.bind();
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public AdapterPopularLives.ViewHolderPopularLives onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderPopularLives(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_illustrations_popular_lives,parent,false));
    }

    class ViewHolderPopularLives extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewTitle, textViewViews;

        public ViewHolderPopularLives(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view_illustrations_popular_lives);
            textViewTitle = itemView.findViewById(R.id.text_view_illustrations_name_popular_lives);
            textViewViews = itemView.findViewById(R.id.text_view_illustrations_views_popular_lives);
        }

        public void bind(){
            Picasso.with(getContext()).load(model.getpLImageUrl()).into(imageView);
            textViewTitle.setText(model.getTitle());
//            textViewViews.setText(model.getNumViews());
        }
    }
}
