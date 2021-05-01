package cat.itb.pixiv.Fragments.FavoriteFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cat.itb.pixiv.Adapater.AdaptersFirebase.AdapterFavFragmentNovel;
import cat.itb.pixiv.ClassesModels.IllustrationClass;
import cat.itb.pixiv.ClassesModels.MangaClass;
import cat.itb.pixiv.ClassesModels.NovelClass;
import cat.itb.pixiv.ClassesModels.User;
import cat.itb.pixiv.FireBase.FireBaseHelper;
import cat.itb.pixiv.R;


public class FavoriteFragmentNovel extends Fragment {

    public static FavoriteFragmentNovel getInstance(){
        return new FavoriteFragmentNovel();
    }
    RecyclerView recyclerView;
    AdapterFavFragmentNovel adapter;
    public static List<NovelClass> novel=new ArrayList<>();
    DatabaseReference dbref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_favorite_novel, container, false);

        recyclerView = v.findViewById(R.id.fav_recycler_novel);
        dbref= FireBaseHelper.getReferenceNovelsRecommended();
        adapter=new AdapterFavFragmentNovel(novel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = FireBaseHelper.getThisUser();
                NovelClass value;
                for (DataSnapshot noveltration:snapshot.getChildren()){
                    value=noveltration.getValue(NovelClass.class);
                    if(user.isFaved(value.getKey())){
                        novel.add(value);
                    }else {
                        for (NovelClass i:novel){
                            if (i.getKey().equals(value.getKey())){
                                novel.remove(i);
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return v;
    }
}