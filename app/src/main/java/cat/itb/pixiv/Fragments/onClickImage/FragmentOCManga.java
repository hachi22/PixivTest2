package cat.itb.pixiv.Fragments.onClickImage;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import cat.itb.pixiv.ClassesModels.IllustrationClass;
import cat.itb.pixiv.ClassesModels.MangaClass;
import cat.itb.pixiv.ClassesModels.User;
import cat.itb.pixiv.FireBase.FireBaseHelper;
import cat.itb.pixiv.FireBase.FireBaseHelper;
import cat.itb.pixiv.Fragments.HomeFragment;
import cat.itb.pixiv.Fragments.HomeFragments.FragmentHomeManga;
import cat.itb.pixiv.R;
import de.hdodenhof.circleimageview.CircleImageView;


public class FragmentOCManga extends Fragment {
    ImageView image;
    CircleImageView userimage;
    TextView title,username,description;
    MaterialButton back;
    MaterialButton followButton;
    boolean following;
    FloatingActionButton favbutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_o_c_manga, container, false);
        image=v.findViewById(R.id.manga_oc_Image);
        userimage=v.findViewById(R.id.manga_oc_ProfileImage);
        title=v.findViewById(R.id.manga_text_view_oc_tittle);
        username=v.findViewById(R.id.manga_text_view_oc_username);
        description=v.findViewById(R.id.manga_text_view_oc_description);
        back = v.findViewById(R.id.backManga);
        followButton = v.findViewById(R.id.followButtonManga);

        favbutton=v.findViewById(R.id.floatingActionButton_manga);
        Bundle arguments=getArguments();
        MangaClass manga =arguments.getParcelable("mangaRecomended");
        if(manga!=null){
            setManga(manga);
        }else if(manga==null){
            manga=arguments.getParcelable("mangaranking");
            setManga(manga);
        }
        MangaClass finalManga = manga;
        User user = FireBaseHelper.getThisUser();
        String mangaId = finalManga.getKey();
        if (user.isFaved(mangaId)) {
            favbutton.setImageResource(R.drawable.likeheartred);

        } else {
            favbutton.setImageResource(R.drawable.likeheartwhite);

        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            }
        });

        followButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                following = !following;
                if(following){
                    followButton.setText("following");
                    FireBaseHelper.subirUserFollow(username.getText().toString());
                }else{
                    followButton.setText("follow");
                    FireBaseHelper.eliminarUserFollow(username.getText().toString());
                }

            }
        });

        favbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.isFaved(mangaId)) {
                    favbutton.setImageResource(R.drawable.likeheartwhite);
                    user.removeFavorite(mangaId);
                } else {
                    favbutton.setImageResource(R.drawable.likeheartred);
                    user.addFavorite(mangaId);
                }
                FireBaseHelper.updateDatabase(finalManga);
                FireBaseHelper.updateDatabase(user);
            }
        });

        return v;
    }

    private void setManga( MangaClass manga){
        final boolean isfollow = FireBaseHelper.comprobarFollowing(manga.getUserName())[0];
        final MangaClass manga2 = manga;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @SuppressLint("SetTextI18n")
            public void run() {
                System.out.println(isfollow);
                if(isfollow){
                    followButton.setText("following");
                    following = true;
                }else{
                    followButton.setText("follow");
                    following = false;
                }

                Picasso.with(getActivity()).load(manga2.getMangaImgUrl()).into(image);
                Picasso.with(getActivity()).load(manga2.getUserImgUrl()).into(userimage);
                username.setText(manga2.getUserName());
                title.setText(manga2.getTitle());
                description.setText(manga2.getDescription());

            }
        }, 200);


    }
}