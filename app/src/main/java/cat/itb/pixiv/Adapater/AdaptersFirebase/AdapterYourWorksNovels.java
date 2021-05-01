package cat.itb.pixiv.Adapater.AdaptersFirebase;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import cat.itb.pixiv.ClassesModels.NovelClass;

public class AdapterYourWorksNovels extends FirebaseRecyclerAdapter<NovelClass, AdapterYourWorksNovels.ViewHolderYourWorks> {

    private NovelClass model;
    private Context context;

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public AdapterYourWorksNovels(@NonNull FirebaseRecyclerOptions<NovelClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolderYourWorks holder, int position, @NonNull NovelClass model) {
        this.model = model;
        holder.bind();
    }

    @NonNull
    @Override
    public ViewHolderYourWorks onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    class ViewHolderYourWorks extends RecyclerView.ViewHolder {

        ImageView imageViewimage;
        TextView textViewTitle, textViewlike, textViewComm, textViewviews;

        public ViewHolderYourWorks(@NonNull View itemView) {
            super(itemView);


        }

        public void bind(){
            Picasso.with(getContext()).load(model.getNovelImgUrl()).into(imageViewimage);
            textViewTitle.setText(model.getTitle());


        }
    }
}
