package cat.itb.pixiv.Adapater.AdaptersFirebase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class AdapterFollowers extends FirebaseRecyclerAdapter<String, AdapterFollowers.ViewHolderFollowers> {

private String model;
private Context context;

public Context getContext() {
        return context;
        }
public void setContext(Context context) {
        this.context = context;
        }

public AdapterFollowers(@NonNull FirebaseRecyclerOptions<String> options) {
        super(options);
        }

@Override
protected void onBindViewHolder(@NonNull ViewHolderFollowers holder, int position, @NonNull String model) {
        this.model = model;
        holder.bind();
        }

@NonNull
@Override
public ViewHolderFollowers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
        }

class ViewHolderFollowers extends RecyclerView.ViewHolder {

    CircleImageView imageViewFollowers;
    TextView textViewUsername;
    MaterialButton followButton;
    boolean following;

    public ViewHolderFollowers(@NonNull View itemView) {
        super(itemView);


    }

    public void bind(){

        String [] imageprofile;
        followButton = itemView.findViewById(R.id.followButton);
        boolean isfollow = FireBaseHelper.comprobarFollowing(model)[0];
        imageprofile = FireBaseHelper.buscarImagenPerfil(model);

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
                System.out.println(imageprofile[0]);
                Picasso.with(getContext()).load(imageprofile[0]).into(imageViewFollowers);

            }
        }, 200);

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






        textViewUsername.setText(model);



    }
}
}

