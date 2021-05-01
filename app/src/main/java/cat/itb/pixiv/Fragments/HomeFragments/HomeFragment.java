package cat.itb.pixiv.Fragments.HomeFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import cat.itb.pixiv.Adapater.SlideViewAdapter;
import cat.itb.pixiv.BlankFragment;
import cat.itb.pixiv.Fragments.FavoriteFragment.FavoriteFragment;
import cat.itb.pixiv.Fragments.FavoriteFragment.FavoriteFragmentIlusManga;
import cat.itb.pixiv.Fragments.LoginFragments.FragmentLogin;
import cat.itb.pixiv.Fragments.NavigationDrawerFragments.YourWorksFragment;
import cat.itb.pixiv.R;

public class HomeFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    TabLayout tabLayout;
    private MaterialToolbar topAppBar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        viewPager = v.findViewById(R.id.slide_view_pager);
        tabLayout= v.findViewById(R.id.tablayout);
        topAppBar= v.findViewById(R.id.top_appbar);
        navigationView= v.findViewById(R.id.navigator_view);
        drawerLayout= v.findViewById(R.id.drawer_layout);

        SlideViewAdapter slideViewAdapter=new SlideViewAdapter(getFragmentManager());
        slideViewAdapter.addFragment(FragmentHomeIllustrations.getInstance(),"Illustrations");
        slideViewAdapter.addFragment(FragmentHomeManga.getInstance(),"Manga");
        slideViewAdapter.addFragment(FragmentHomeNovels.getInstance(),"Novels");
        viewPager.setAdapter(slideViewAdapter);
        tabLayout.setupWithViewPager(viewPager);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),
                drawerLayout,
                topAppBar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        return v;

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.home:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlankFragment()).commit();
                break;
            case R.id.newest:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlankFragment()).commit();
                break;
            case R.id.search:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlankFragment()).commit();
                break;
                case R.id.submitWork:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlankFragment()).commit();
                break;
                case R.id.yourWorks:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new YourWorksFragment()).commit();
                break;
            case R.id.collection:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavoriteFragment()).commit();
                break;
            case R.id.browsingHistory:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlankFragment()).commit();
                break;
            case R.id.markers:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlankFragment()).commit();
                break;
            case R.id.following:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlankFragment()).commit();
                break;
            case R.id.followers:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlankFragment()).commit();
                break;
            case R.id.myPixiv:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlankFragment()).commit();
                break;
            case R.id.feedback:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlankFragment()).commit();
                break;
            case R.id.help:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlankFragment()).commit();
                break;
            case R.id.settings:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlankFragment()).commit();
                break;
            case R.id.muteSettings:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new BlankFragment()).commit();
                break;
            case R.id.logout:
                Bundle bundle = new Bundle();
                bundle.putInt("id",1);
                FragmentLogin fragmentLogin = new FragmentLogin();
                fragmentLogin.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentLogin).commit();

        }

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
