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

import cat.itb.pixiv.ClassesModels.ImatgesP;
import cat.itb.pixiv.ClassesModels.MangaClass;
import cat.itb.pixiv.ClassesModels.NovelClass;
import cat.itb.pixiv.ClassesModels.User;
import cat.itb.pixiv.FireBase.FireBaseHelper;
import cat.itb.pixiv.Fragments.onClickImage.FragmentOCIllustrations;
import cat.itb.pixiv.Fragments.onClickImage.FragmentOCManga;
import cat.itb.pixiv.Fragments.onClickImage.FragmentOCNovels;
import cat.itb.pixiv.R;

public class AdapterMangaRecommended extends FirebaseRecyclerAdapter<MangaClass, ViewHolderMangaRecommended> {
    private Context context;

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public AdapterMangaRecommended(@NonNull FirebaseRecyclerOptions<MangaClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolderMangaRecommended holder, int position, @NonNull MangaClass model) {
        holder.bind(model,getContext(),false);
    }

    @NonNull
    @Override
    public ViewHolderMangaRecommended onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderMangaRecommended(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_manga_recommended,parent,false),false);
    }

}

class ViewHolderMangaRecommended extends RecyclerView.ViewHolder {

    ImageView imageViewimage, imageViewLike;
    TextView textViewTitle, textViewDescription, textViewNumlikes;

    public ViewHolderMangaRecommended(@NonNull View itemView,boolean isimage) {
        super(itemView);
        if(!isimage){
            imageViewimage = itemView.findViewById(R.id.image_view_manga_recommended);
            imageViewLike = itemView.findViewById(R.id.image_view_manga_recommended_like);
            textViewTitle = itemView.findViewById(R.id.text_view_manga_recommended_title);
            textViewDescription = itemView.findViewById(R.id.text_view_manga_recommended_description);
            textViewNumlikes = itemView.findViewById(R.id.text_view_manga_recommended_numlikes);
        }else {
            imageViewimage = itemView.findViewById(R.id.image_view_illustrations_recommended);
            imageViewLike=itemView.findViewById(R.id.image_view_illustrations_recommended_like);
        }


    }



    public void bind(final MangaClass manga, Context context,boolean isImage){
        Picasso.with(context).load(manga.getMangaImgUrl()).into(imageViewimage);
        if(!isImage){
            textViewTitle.setText(manga.getTitle());
            textViewDescription.setText(manga.getDescription());
//         textViewNumlikes.setText(manga.getLikesNumber());
        }

        User user = FireBaseHelper.getThisUser();

        if (user != null) {
            imageViewLike.setImageResource(user.isFaved(manga.getKey())?R.drawable.likeheartred:R.drawable.likeheartwhite);
        }

        imageViewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = FireBaseHelper.getThisUser();

                if (user == null) {
                    return;
                }
                String mangaId = manga.getKey();
                if (user.isFaved(mangaId)) {
                    imageViewLike.setImageResource(R.drawable.likeheartwhite);
                    user.removeFavorite(mangaId);
                } else {
                    imageViewLike.setImageResource(R.drawable.likeheartred);
                    user.addFavorite(mangaId);
                }
                FireBaseHelper.updateDatabase(manga);
                FireBaseHelper.updateDatabase(user);
            }
        });

            imageViewimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle argument=new Bundle();
                    argument.putParcelable("mangaRecomended",manga);
                    AppCompatActivity context=(AppCompatActivity)v.getContext();
                    FragmentOCManga fragmentOCManga=new FragmentOCManga();
                    fragmentOCManga.setArguments(argument);
                    context.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentOCManga).commit();
                }
            });
    }
}
