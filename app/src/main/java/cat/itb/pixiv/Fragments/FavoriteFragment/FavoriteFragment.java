package cat.itb.pixiv.Fragments.FavoriteFragment;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import cat.itb.pixiv.Adapater.SlideViewAdapter;
import cat.itb.pixiv.Fragments.HomeFragment;
import cat.itb.pixiv.Fragments.HomeFragments.FragmentHomeIllustrations;
import cat.itb.pixiv.Fragments.HomeFragments.FragmentHomeManga;
import cat.itb.pixiv.Fragments.HomeFragments.FragmentHomeNovels;
import cat.itb.pixiv.R;

public class FavoriteFragment extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;
    private MaterialToolbar topAppBar;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_favorite, container, false);
        viewPager = v.findViewById(R.id.slide_view_pager_fav);
        tabLayout= v.findViewById(R.id.tablayout_favorite);
        topAppBar= v.findViewById(R.id.top_appbar_favorite);

        SlideViewAdapter slideViewAdapter=new SlideViewAdapter(getFragmentManager());
        slideViewAdapter.addFragment(FavoriteFragmentIlusManga.getInstance(),"Illust/Manga");
        slideViewAdapter.addFragment(FavoriteFragmentNovel.getInstance(),"Novels");
        viewPager.setAdapter(slideViewAdapter);
        tabLayout.setupWithViewPager(viewPager);

        topAppBar.setNavigationIcon(R.drawable.ic_arrow_back);
        topAppBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            }
        });
       return v;
    }
}