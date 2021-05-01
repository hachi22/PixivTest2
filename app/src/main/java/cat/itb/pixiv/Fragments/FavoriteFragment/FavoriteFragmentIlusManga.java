package cat.itb.pixiv.Fragments.FavoriteFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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

import cat.itb.pixiv.Adapater.AdaptersFirebase.AdapterFavFragmentIlus;
import cat.itb.pixiv.Adapater.AdaptersFirebase.AdapterFavManga;
import cat.itb.pixiv.Adapater.AdaptersFirebase.AdapterMangaRecommended;
import cat.itb.pixiv.ClassesModels.IllustrationClass;
import cat.itb.pixiv.ClassesModels.MangaClass;
import cat.itb.pixiv.ClassesModels.User;
import cat.itb.pixiv.FireBase.FireBaseHelper;
import cat.itb.pixiv.R;


public class FavoriteFragmentIlusManga extends Fragment {
    RecyclerView recyclerViewIlus,recyclerManga;
    AdapterFavFragmentIlus adapterIlus;
    AdapterFavManga adapterManga;
    public static List<IllustrationClass> ilus=new ArrayList<>();
    public static List<MangaClass>manga=new ArrayList<>();
    DatabaseReference drefIlus,dbrefManga;
    public static FavoriteFragmentIlusManga getInstance(){
        return new FavoriteFragmentIlusManga();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v= inflater.inflate(R.layout.fragment_favorite_ilus_manga, container, false);
        recyclerViewIlus = v.findViewById(R.id.fav_recycler);
        recyclerManga=v.findViewById(R.id.fav_recycler_manga);
        drefIlus =FireBaseHelper.getReferenceIllustrationsRecommended();
        dbrefManga=FireBaseHelper.getReferenceMangaRecommended();
        adapterIlus =new AdapterFavFragmentIlus(ilus);
        adapterManga=new AdapterFavManga(manga);
        recyclerViewIlus.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerViewIlus.setAdapter(adapterIlus);
        recyclerManga.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerManga.setAdapter(adapterManga);
        drefIlus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = FireBaseHelper.getThisUser();
                IllustrationClass value;
                for (DataSnapshot ilustration:snapshot.getChildren()){
                    value=ilustration.getValue(IllustrationClass.class);
                    if(user.isFaved(value.getKey())){
                        ilus.add(value);
                    }else{
                        for (IllustrationClass i:ilus){
                            if (i.getKey().equals(value.getKey())){
                                ilus.remove(i);
                            }
                        }
                    }
                }
                adapterIlus.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        dbrefManga.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = FireBaseHelper.getThisUser();
                MangaClass value;
                for (DataSnapshot ilustration:snapshot.getChildren()){
                    value=ilustration.getValue(MangaClass.class);
                    if(user.isFaved(value.getKey())){
                        manga.add(value);
                    }else {
                        for (MangaClass i:manga){
                            if (i.getKey().equals(value.getKey())){
                                manga.remove(i);
                            }
                        }
                    }
                }
                adapterManga.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;
    }


}