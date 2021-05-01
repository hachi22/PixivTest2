package cat.itb.pixiv;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;


import cat.itb.pixiv.Fragments.LoginFragments.FragmentFirst;
import cat.itb.pixiv.Fragments.LoginFragments.FragmentLogin;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new FragmentFirst())
                .commit();
    }

}
