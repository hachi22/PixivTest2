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

import cat.itb.pixiv.ClassesModels.IllustrationClass;
import cat.itb.pixiv.R;


public class AdapterYourWorksIllustrations extends FirebaseRecyclerAdapter<IllustrationClass, AdapterYourWorksIllustrations.ViewHolderYourWorks> {

    private IllustrationClass model;
    private Context context;

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public AdapterYourWorksIllustrations(@NonNull FirebaseRecyclerOptions<IllustrationClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterYourWorksIllustrations.ViewHolderYourWorks holder, int position, @NonNull IllustrationClass model) {
        this.model = model;
        holder.bind();
    }

    @NonNull
    @Override
    public AdapterYourWorksIllustrations.ViewHolderYourWorks onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterYourWorksIllustrations.ViewHolderYourWorks(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_your_works,parent,false));

    }

    class ViewHolderYourWorks extends RecyclerView.ViewHolder {

        ImageView imageViewimage;
        TextView textViewTitle, textViewlike, textViewComm, textViewviews;

        public ViewHolderYourWorks(@NonNull View itemView) {
            super(itemView);
            imageViewimage = itemView.findViewById(R.id.image_your_works);
            textViewTitle = itemView.findViewById(R.id.text_your_works_title);

        }

        public void bind(){
            Picasso.with(getContext()).load(model.getIllustrationImgUrl()).into(imageViewimage);
            textViewTitle.setText(model.getTitle());


        }
    }
}
