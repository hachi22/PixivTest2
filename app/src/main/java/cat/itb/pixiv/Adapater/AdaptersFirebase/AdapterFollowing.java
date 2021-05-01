package cat.itb.pixiv.Adapater.AdaptersFirebase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import cat.itb.pixiv.ClassesModels.User;
import cat.itb.pixiv.FireBase.FireBaseHelper;
import cat.itb.pixiv.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterFollowing extends FirebaseRecyclerAdapter<String, AdapterFollowing.ViewHolderFollowing> {

    private String model;
    private Context context;
    boolean following;
    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public AdapterFollowing(@NonNull FirebaseRecyclerOptions<String> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdapterFollowing.ViewHolderFollowing holder, int position, @NonNull String model) {
        this.model = model;
        holder.bind();
    }

    @NonNull
    @Override
    public AdapterFollowing.ViewHolderFollowing onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    class ViewHolderFollowing extends RecyclerView.ViewHolder {

        CircleImageView imageViewFollowers;
        TextView textViewUsername;
        MaterialButton followButton;
        ImageView image1;
        ImageView image2;
        ImageView image3;

        public ViewHolderFollowing(@NonNull View itemView) {
            super(itemView);


        }

        public void bind(){
            String[] images;
            images = FireBaseHelper.buscar3Imagenes(model);
            boolean isfollow = FireBaseHelper.comprobarFollowing(model)[0];
            System.out.println("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");

            textViewUsername = itemView.findViewById(R.id.usernameFollowing);
            textViewUsername.setText(model.toString());
            followButton = itemView.findViewById(R.id.followButtonFollowing);
            image1 = itemView.findViewById(R.id.img_following_1);
            image2 = itemView.findViewById(R.id.img_following_2);
            image3 = itemView.findViewById(R.id.img_following_3);



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


                }
            }, 200);

            Picasso.with(getContext()).load(images[3]).into(imageViewFollowers);
            Picasso.with(getContext()).load(images[0]).into(image1);
            Picasso.with(getContext()).load(images[1]).into(image2);
            Picasso.with(getContext()).load(images[2]).into(image3);

            followButton.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {

                    following = !following;
                    if(following){
                        followButton.setText("following");
                        FireBaseHelper.subirUserFollow(model);
                    }else{
                        followButton.setText("follow");
                        FireBaseHelper.eliminarUserFollow(model);
                    }

                }
            });



        }
    }
}