package cat.itb.pixiv.Adapater.AdaptersFirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.itb.pixiv.ClassesModels.MangaClass;
import cat.itb.pixiv.ClassesModels.NovelClass;
import cat.itb.pixiv.ClassesModels.User;
import cat.itb.pixiv.FireBase.FireBaseHelper;
import cat.itb.pixiv.R;

public class AdapterFavManga extends RecyclerView.Adapter<ViewHolderMangaRecommended> {
    private List<MangaClass> favlist;
    private Context context;

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public AdapterFavManga(List<MangaClass>list){
        this.favlist=list;
    }
    @NonNull
    @Override
    public ViewHolderMangaRecommended onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderMangaRecommended(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_illustrations_recommended,parent,false),true);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMangaRecommended holder, int position) {
        MangaClass model=favlist.get(position);
        User user = FireBaseHelper.getThisUser();
        if (user == null) {
            return;
        }
        if(user.isFaved(model.getKey()))
            holder.bind(model,getContext(),true);
    }

    @Override
    public int getItemCount() {
        return favlist.size();
    }
}
