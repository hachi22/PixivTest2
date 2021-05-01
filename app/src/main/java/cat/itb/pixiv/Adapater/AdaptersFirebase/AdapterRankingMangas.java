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
import cat.itb.pixiv.ClassesModels.MangaClass;
import cat.itb.pixiv.ClassesModels.User;
import cat.itb.pixiv.FireBase.FireBaseHelper;
import cat.itb.pixiv.Fragments.onClickImage.FragmentOCIllustrations;
import cat.itb.pixiv.Fragments.onClickImage.FragmentOCManga;
import cat.itb.pixiv.R;

public class AdapterRankingMangas extends FirebaseRecyclerAdapter<MangaClass, AdapterRankingMangas.GeneralViewHolder> {

    private MangaClass model;
    private Context context;

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public AdapterRankingMangas(@NonNull FirebaseRecyclerOptions<MangaClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull GeneralViewHolder holder, int position, @NonNull MangaClass model) {
        this.model = model;
        holder.bind(model,getContext());
    }


    @NonNull
    @Override
    public AdapterRankingMangas.GeneralViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GeneralViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_illustrations_ranking,parent,false));

    }

    class GeneralViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle;
        TextView textViewUser;
        ImageView imageViewImage;
        ImageView imageLike;

        public GeneralViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_illustrations_ranking_name);
            textViewUser = itemView.findViewById(R.id.text_view_illustrations_ranking_user);
            imageViewImage = itemView.findViewById(R.id.image_view_illustrations_ranking);
            imageLike = itemView.findViewById(R.id.heart_illustrations_ranking);
        }

        public void bind(final MangaClass manga, Context context){
            textViewTitle.setText(model.getTitle());
            textViewUser.setText(model.getUserName());
            Picasso.with(context).load(manga.getMangaImgUrl()).into(imageViewImage);

            User user = FireBaseHelper.getThisUser();

            if (user != null) {
                imageLike.setImageResource(user.isFaved(manga.getKey())?R.drawable.likeheartred:R.drawable.likeheartwhite);
            }

            imageLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User user = FireBaseHelper.getThisUser();

                    if (user == null) {
                        return;
                    }
                    String mangaId = manga.getKey();
                    if (user.isFaved(mangaId)) {
                        imageLike.setImageResource(R.drawable.likeheartwhite);
                        user.removeFavorite(mangaId);
                    } else {
                        imageLike.setImageResource(R.drawable.likeheartred);
                        user.addFavorite(mangaId);
                    }
                    FireBaseHelper.updateDatabase(manga);
                    FireBaseHelper.updateDatabase(user);
                }
            });

            imageViewImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(manga!=null){
                        Bundle argument=new Bundle();
                        argument.putParcelable("mangaranking",manga);
                        AppCompatActivity context=(AppCompatActivity)v.getContext();
                        FragmentOCManga fragmentOCManga=new FragmentOCManga();
                        fragmentOCManga.setArguments(argument);
                        context.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentOCManga).commit();
                    }
                }
            });

        }

    }



}
