package cat.itb.pixiv.Fragments.HomeFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import cat.itb.pixiv.Adapater.AdaptersFirebase.AdapterMangaRecommended;
import cat.itb.pixiv.Adapater.AdaptersFirebase.AdapterPixivVision;


import cat.itb.pixiv.Adapater.AdaptersFirebase.AdapterRankingMangas;
import cat.itb.pixiv.ClassesModels.MangaClass;
import cat.itb.pixiv.ClassesModels.MangaPixivVisionClass;
import cat.itb.pixiv.FireBase.FireBaseHelper;

import cat.itb.pixiv.R;

public class FragmentHomeManga extends Fragment {

    public static FragmentHomeManga getInstance(){
        return new FragmentHomeManga();
    }

    RecyclerView recyclerView;
    AdapterRankingMangas adapterRanking;
    AdapterPixivVision adapterPixivVision;
    AdapterMangaRecommended adapterRecommended;
    DatabaseReference myRef;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_manga, container, false);

//        List<ImatgesP> imageslist = new ArrayList<>();
//        imageslist.add(new ImatgesP("title","description","user",R.raw.manga1,2,2,2,R.raw.manga6,R.raw.manga11));
//        imageslist.add(new ImatgesP("title","description","user",R.raw.manga2,2,2,2,R.raw.manga7,R.raw.manga12));
//        imageslist.add(new ImatgesP("title","description","user",R.raw.manga3,2,2,2,R.raw.manga8,R.raw.manga13));
//        imageslist.add(new ImatgesP("title","description","user",R.raw.manga4,2,2,2,R.raw.manga9,R.raw.manga14));
//        imageslist.add(new ImatgesP("title","description","user",R.raw.manga5,2,2,2,R.raw.manga10,R.raw.manga15));



        recyclerView = rootView.findViewById(R.id.recycler_view_manga_ranking);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        FirebaseRecyclerOptions<MangaClass> options = new FirebaseRecyclerOptions.Builder<MangaClass>()
                .setQuery(FireBaseHelper.getReferenceMangaRanking(), MangaClass.class).build();
        adapterRanking = new AdapterRankingMangas(options);
        adapterRanking.setContext(getContext());
        recyclerView.setAdapter(adapterRanking);



        recyclerView = rootView.findViewById(R.id.recycler_view_manga_pixi_vision);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        FirebaseRecyclerOptions<MangaPixivVisionClass> options2 = new FirebaseRecyclerOptions.Builder<MangaPixivVisionClass>()
                .setQuery(FireBaseHelper.getReferenceMangaPixivVision(), MangaPixivVisionClass.class).build();
        adapterPixivVision = new AdapterPixivVision(options2);
        adapterPixivVision.setContext(getContext());
        recyclerView.setAdapter(adapterPixivVision);




        recyclerView = rootView.findViewById(R.id.recycler_view_manga_mangas);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        FirebaseRecyclerOptions<MangaClass> options3 = new FirebaseRecyclerOptions.Builder<MangaClass>()
                .setQuery(FireBaseHelper.getReferenceMangaRecommended(), MangaClass.class).build();
        adapterRecommended = new AdapterMangaRecommended(options3);
        adapterRecommended.setContext(getContext());
        recyclerView.setAdapter(adapterRecommended);

//        recyclerView = rootView.findViewById(R.id.recycler_view_manga_mangas);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        nAdaptersMangaRecommended = new NAdaptersMangaRecommended(mangaRecommendeds);
//        recyclerView.setAdapter(nAdaptersMangaRecommended);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterRanking.startListening();
        adapterRecommended.startListening();
        adapterPixivVision.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterRanking.stopListening();
        adapterRecommended.stopListening();
        adapterPixivVision.stopListening();
    }


}