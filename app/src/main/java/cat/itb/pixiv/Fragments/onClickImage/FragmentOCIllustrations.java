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

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import cat.itb.pixiv.ClassesModels.IllustrationClass;
import cat.itb.pixiv.FireBase.FireBaseHelper;
import cat.itb.pixiv.Fragments.HomeFragment;
import cat.itb.pixiv.ClassesModels.User;
import cat.itb.pixiv.R;
import de.hdodenhof.circleimageview.CircleImageView;


public class FragmentOCIllustrations extends Fragment {
    ImageView image;
    CircleImageView userimage;
    TextView username,title;
    MaterialButton backIllus;
    MaterialButton followButton;
    boolean following;
    FloatingActionButton favbutton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_o_c_illustrations, container, false);

        image=v.findViewById(R.id.illustratrion_oc_Image);
        userimage=v.findViewById(R.id.illustration_oc_ProfileImage);
        username=v.findViewById(R.id.illustration_text_view_oc_username);
        title=v.findViewById(R.id.illustration_text_view_oc_tittle);
        backIllus = v.findViewById(R.id.backIllustration);
        followButton = v.findViewById(R.id.followButtonIllustration);
        favbutton=v.findViewById(R.id.floatingActionButton_illustration);


        Bundle arguments=getArguments();
       IllustrationClass ilus=arguments.getParcelable("illustrationRecommended");

        if(ilus!=null){
            setViews(ilus);
        }else {
          ilus=arguments.getParcelable(  "illustrationRanking");
          setViews(ilus);
        }

        final IllustrationClass finalIlus = ilus;
        final User user = FireBaseHelper.getThisUser();
        final String ilusId = finalIlus.getKey();
        if (user.isFaved(ilusId)) {
            favbutton.setImageResource(R.drawable.likeheartred);
        } else {
            favbutton.setImageResource(R.drawable.likeheartwhite);
        }
        favbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(ilusId);
                if (user.isFaved(ilusId)) {
                    favbutton.setImageResource(R.drawable.likeheartwhite);
                    user.removeFavorite(ilusId);
                } else {
                    favbutton.setImageResource(R.drawable.likeheartred);
                    user.addFavorite(ilusId);
                }
                FireBaseHelper.updateDatabase(finalIlus);
                FireBaseHelper.updateDatabase(user);

        }});

        backIllus.setOnClickListener(new View.OnClickListener() {
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
        return v;
    }




    @SuppressLint("SetTextI18n")
    private void setViews( IllustrationClass ilustration) {
        final boolean isfollow = FireBaseHelper.comprobarFollowing(ilustration.getUserName())[0];
        final IllustrationClass illustration = ilustration;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                System.out.println(isfollow);
                if(isfollow){
                    followButton.setText("following");
                    following = true;
                }else{
                    followButton.setText("follow");
                    following = false;
                }

                Picasso.with(getActivity()).load(illustration.getIllustrationImgUrl()).into(image);
                Picasso.with(getActivity()).load(illustration.getUserImgUrl()).into(userimage);
                username.setText(illustration.getUserName());
                title.setText(illustration.getTitle());

            }
        }, 200);
    }
}