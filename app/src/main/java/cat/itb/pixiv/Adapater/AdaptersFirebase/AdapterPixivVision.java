package cat.itb.pixiv.Adapater.AdaptersFirebase;

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

import cat.itb.pixiv.ClassesModels.ImatgesP;
import cat.itb.pixiv.ClassesModels.MangaPixivVisionClass;
import cat.itb.pixiv.R;

public class AdapterPixivVision extends FirebaseRecyclerAdapter<MangaPixivVisionClass, AdapterPixivVision.ViewHolderPixivVision> {

    private MangaPixivVisionClass model;
    private Context context;

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public AdapterPixivVision(@NonNull FirebaseRecyclerOptions<MangaPixivVisionClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterPixivVision.ViewHolderPixivVision holder, int position, @NonNull MangaPixivVisionClass model) {
        this.model = model;
        holder.bind();
    }

    @NonNull
    @Override
    public AdapterPixivVision.ViewHolderPixivVision onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderPixivVision(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_manga_pixivision,parent,false));
    }

    class ViewHolderPixivVision extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewTitle;

        public ViewHolderPixivVision(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view_manga_pixivision);
            textViewTitle = itemView.findViewById(R.id.text_view_manga_pixivvidion);
        }

        public void bind(){
            Picasso.with(getContext()).load(model.getpVImgUrl()).into(imageView);
            textViewTitle.setText(model.getTitle());
        }


    }
}
