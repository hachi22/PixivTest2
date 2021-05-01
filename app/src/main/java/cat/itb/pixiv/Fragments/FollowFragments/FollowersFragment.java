package cat.itb.pixiv.Fragments.FollowFragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import cat.itb.pixiv.Adapater.AdaptersFirebase.AdapterFollowers;
import cat.itb.pixiv.Adapater.AdaptersFirebase.AdapterYourWorksIllustrations;
import cat.itb.pixiv.Adapater.AdaptersFirebase.AdapterYourWorksManga;
import cat.itb.pixiv.Adapater.AdaptersFirebase.AdapterYourWorksNovels;
import cat.itb.pixiv.ClassesModels.IllustrationClass;
import cat.itb.pixiv.ClassesModels.User;
import cat.itb.pixiv.FireBase.FireBaseHelper;
import cat.itb.pixiv.Fragments.HomeFragment;
import cat.itb.pixiv.R;

public class FollowersFragment extends Fragment {

    MaterialToolbar topToolBar;

    MaterialButton followButton;

    RecyclerView recyclerView;
    AdapterFollowers adapterFollowers;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_followers, container, false);

        topToolBar = rootView.findViewById(R.id.top_appbar_followers);
        recyclerView = rootView.findViewById(R.id.recycler_view_followers);
        followButton = rootView.findViewById(R.id.followButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<String> options = new FirebaseRecyclerOptions.Builder<String>()
                .setQuery(FireBaseHelper.getUserFollower(), String.class).build(); //getUserFollower() la he puesto en el firebaseHelper, pero mal
        adapterFollowers = new AdapterFollowers(options);
        adapterFollowers.setContext(getContext());
        recyclerView.setAdapter(adapterFollowers);

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
