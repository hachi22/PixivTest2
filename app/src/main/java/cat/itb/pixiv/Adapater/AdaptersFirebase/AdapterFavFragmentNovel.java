package cat.itb.pixiv.Adapater.AdaptersFirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.itb.pixiv.ClassesModels.IllustrationClass;
import cat.itb.pixiv.ClassesModels.NovelClass;
import cat.itb.pixiv.ClassesModels.User;
import cat.itb.pixiv.FireBase.FireBaseHelper;
import cat.itb.pixiv.R;

public class AdapterFavFragmentNovel extends RecyclerView.Adapter<ViewHolderNovelsRecommended>{
    private List<NovelClass> favlist;
    private Context context;

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public AdapterFavFragmentNovel(List<NovelClass>list){
        this.favlist=list;
    }


    @NonNull
    @Override
    public ViewHolderNovelsRecommended onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderNovelsRecommended(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_novels_recommended,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNovelsRecommended holder, int position) {
        NovelClass model=favlist.get(position);
        User user = FireBaseHelper.getThisUser();
        if (user == null) {
            return;
        }
        if(user.isFaved(model.getKey()))
            holder.bind(model,getContext());
    }

    @Override
    public int getItemCount() {
        return favlist.size();
    }
}
