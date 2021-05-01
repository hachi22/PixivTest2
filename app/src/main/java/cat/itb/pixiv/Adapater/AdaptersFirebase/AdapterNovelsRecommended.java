package cat.itb.pixiv.Adapater.AdaptersFirebase;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import cat.itb.pixiv.ClassesModels.IllustrationClass;
import cat.itb.pixiv.ClassesModels.ImatgesP;
import cat.itb.pixiv.ClassesModels.NovelClass;
import cat.itb.pixiv.ClassesModels.User;
import cat.itb.pixiv.FireBase.FireBaseHelper;
import cat.itb.pixiv.Fragments.onClickImage.FragmentOCIllustrations;
import cat.itb.pixiv.Fragments.onClickImage.FragmentOCNovels;
import cat.itb.pixiv.R;

public class AdapterNovelsRecommended extends FirebaseRecyclerAdapter<NovelClass, ViewHolderNovelsRecommended> {
    private Context context;

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public AdapterNovelsRecommended(@NonNull FirebaseRecyclerOptions<NovelClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolderNovelsRecommended holder, int position, @NonNull NovelClass model) {

        holder.bind(model,getContext());
    }

    @NonNull
    @Override
    public ViewHolderNovelsRecommended onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderNovelsRecommended(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_novels_recommended,parent,false));
    }

}

class ViewHolderNovelsRecommended extends RecyclerView.ViewHolder {

    ImageView imageViewimage, imageViewLike;
    TextView textViewTitle, textViewDescription, textViewNumlikes;

    public ViewHolderNovelsRecommended(@NonNull View itemView) {
        super(itemView);
        imageViewimage = itemView.findViewById(R.id.image_view_novels_recommended);
        imageViewLike = itemView.findViewById(R.id.image_view_novels_recommended_like);
        textViewTitle = itemView.findViewById(R.id.text_view_novels_recommended_title);
        textViewDescription = itemView.findViewById(R.id.text_view_novels_recommended_description);
        textViewNumlikes = itemView.findViewById(R.id.text_view_novels_recommended_numlikes);
    }


    public void bind(final NovelClass novel, Context context){
        Picasso.with(context).load(novel.getNovelImgUrl()).into(imageViewimage);
        textViewTitle.setText(novel.getTitle());
        textViewDescription.setText(novel.getDescription());
//         textViewNumlikes.setText(novel.getLikesNumber());
        User user = FireBaseHelper.getThisUser();

        if (user != null) {
            imageViewLike.setImageResource(user.isFaved(novel.getKey())?R.drawable.likeheartred:R.drawable.likeheartwhite);
        }

        imageViewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = FireBaseHelper.getThisUser();

                if (user == null) {
                    return;
                }
                String mangaId = novel.getKey();
                if (user.isFaved(mangaId)) {
                    imageViewLike.setImageResource(R.drawable.likeheartwhite);
                    user.removeFavorite(mangaId);
                } else {
                    imageViewLike.setImageResource(R.drawable.likeheartred);
                    user.addFavorite(mangaId);
                }
                FireBaseHelper.updateDatabase(novel);
                FireBaseHelper.updateDatabase(user);
            }
        });

        imageViewimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle argument=new Bundle();
                argument.putParcelable("novelRecomended",novel);
                AppCompatActivity context=(AppCompatActivity)v.getContext();
                FragmentOCNovels fragmentnovels=new FragmentOCNovels();
                fragmentnovels.setArguments(argument);
                context.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentnovels).commit();
            }
        });

    }
}
