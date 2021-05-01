package cat.itb.pixiv.Fragments.HomeFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import cat.itb.pixiv.Adapater.AdaptersFirebase.AdapterNovelsRecommended;
import cat.itb.pixiv.Adapater.AdaptersFirebase.AdapterRankingNovels;

import cat.itb.pixiv.ClassesModels.NovelClass;
import cat.itb.pixiv.FireBase.FireBaseHelper;

import cat.itb.pixiv.R;

public class FragmentHomeNovels extends Fragment {

    public static FragmentHomeNovels getInstance(){
        return new FragmentHomeNovels();
    }

    RecyclerView recyclerView;
    AdapterNovelsRecommended adapterRecommended;
    AdapterRankingNovels adapterRanking;
    DatabaseReference myRef;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_home_novels, container, false);


        recyclerView = rootView.findViewById(R.id.recycler_view_novels_ranking);




      
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        FirebaseRecyclerOptions<NovelClass> options = new FirebaseRecyclerOptions.Builder<NovelClass>()
                .setQuery(FireBaseHelper.getReferenceNovelsRanking(), NovelClass.class).build();
        adapterRanking = new AdapterRankingNovels(options);
        adapterRanking.setContext(getContext());
        recyclerView.setAdapter(adapterRanking);




        recyclerView = rootView.findViewById(R.id.recycler_view_novels_recommended);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<NovelClass> options2 = new FirebaseRecyclerOptions.Builder<NovelClass>()
                .setQuery(FireBaseHelper.getReferenceNovelsRecommended(), NovelClass.class).build();
        adapterRecommended = new AdapterNovelsRecommended(options2);
        adapterRecommended.setContext(getContext());
        recyclerView.setAdapter(adapterRecommended);

        
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterRanking.startListening();
        adapterRecommended.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterRanking.stopListening();
        adapterRecommended.stopListening();
    }
}