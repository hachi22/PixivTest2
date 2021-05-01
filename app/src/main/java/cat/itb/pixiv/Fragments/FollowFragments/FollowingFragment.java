package cat.itb.pixiv.Fragments.FollowFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import cat.itb.pixiv.Adapater.AdaptersFirebase.AdapterFollowers;
import cat.itb.pixiv.Adapater.AdaptersFirebase.AdapterFollowing;
import cat.itb.pixiv.ClassesModels.User;
import cat.itb.pixiv.FireBase.FireBaseHelper;
import cat.itb.pixiv.Fragments.HomeFragment;
import cat.itb.pixiv.R;

public class FollowingFragment extends Fragment {

    MaterialToolbar topToolBar;

    MaterialButton followButton;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;

    RecyclerView recyclerView;
    AdapterFollowing adapterFollowing;

    String[] images;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_following, container, false);

        topToolBar = rootView.findViewById(R.id.top_appbar_following);
        recyclerView = rootView.findViewById(R.id.recycler_view_following);
        followButton = rootView.findViewById(R.id.followButtonFollowing);
        imageView1 = rootView.findViewById(R.id.img_following_1);
        imageView2 = rootView.findViewById(R.id.img_following_2);
        imageView3 = rootView.findViewById(R.id.img_following_3);



        //imageView1.setImageResource();
        //imageView2.setImageResource(); TODO: poner las imagenes del user al que sigues
        //imageView3.setImageResource();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<String> options = new FirebaseRecyclerOptions.Builder<String>()
                .setQuery(FireBaseHelper.getUserFollower(), String.class).build(); //TODO: cambiar el getUserFollower()
        adapterFollowing = new AdapterFollowing(options);
        adapterFollowing.setContext(getContext());
        recyclerView.setAdapter(adapterFollowing);

        topToolBar.setNavigationIcon(R.drawable.ic_arrow_back);
        topToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

            }
        });

        /*
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: seguir al usuario
            }
        });
        */


        return rootView;

    }
}

