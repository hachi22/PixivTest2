package cat.itb.pixiv.Adapater.AdaptersFirebase;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import cat.itb.pixiv.ClassesModels.IllustrationClass;
import cat.itb.pixiv.ClassesModels.ImatgesP;
import cat.itb.pixiv.ClassesModels.User;
import cat.itb.pixiv.FireBase.FireBaseHelper;
import cat.itb.pixiv.Fragments.LoginFragments.FragmentLogin;
import cat.itb.pixiv.Fragments.onClickImage.FragmentOCIllustrations;
import cat.itb.pixiv.R;


public class AdapterIlustrationsRecomended extends FirebaseRecyclerAdapter<IllustrationClass, ViewHolderIllustrationsRecommended> {

    private IllustrationClass model;
    private Context context;

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public AdapterIlustrationsRecomended(@NonNull FirebaseRecyclerOptions<IllustrationClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolderIllustrationsRecommended holder, int position, @NonNull final IllustrationClass model) {
        this.model = model;
        holder.bind(model,getContext());
    }

    @NonNull
    @Override
    public ViewHolderIllustrationsRecommended onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderIllustrationsRecommended(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_illustrations_recommended,parent,false));
    }
}

 class ViewHolderIllustrationsRecommended extends RecyclerView.ViewHolder {

    ImageView imageViewimage, imageViewLike;

    public ViewHolderIllustrationsRecommended(@NonNull View itemView) {
        super(itemView);
        imageViewimage = itemView.findViewById(R.id.image_view_illustrations_recommended);
        imageViewLike = itemView.findViewById(R.id.image_view_illustrations_recommended_like);
    }

    public void bind(final IllustrationClass ilus, Context context){
        Picasso.with(context).load(ilus.getIllustrationImgUrl()).into(imageViewimage);
        User user = FireBaseHelper.getThisUser();

        if (user != null) {
            imageViewLike.setImageResource(user.isFaved(ilus.getKey())?R.drawable.likeheartred:R.drawable.likeheartwhite);
        }


        imageViewLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = FireBaseHelper.getThisUser();
                System.out.println(user);

                if (user == null) {
                    return;
                }
                String ilusId = ilus.getKey();
                if (user.isFaved(ilusId)) {
                    imageViewLike.setImageResource(R.drawable.likeheartwhite);
                    user.removeFavorite(ilusId);
                } else {
                    imageViewLike.setImageResource(R.drawable.likeheartred);
                    user.addFavorite(ilusId);
                }
                FireBaseHelper.updateDatabase(ilus);
                FireBaseHelper.updateDatabase(user);
            }
        });

        imageViewimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle argument=new Bundle();
                argument.putParcelable("illustrationRecommended",ilus);
                AppCompatActivity context=(AppCompatActivity)v.getContext();
                FragmentOCIllustrations fragmentOCIllustrations=new FragmentOCIllustrations();
                fragmentOCIllustrations.setArguments(argument);
                context.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentOCIllustrations).commit();
            }
        });

    }
}
