package cat.itb.pixiv.Adapater.AdaptersFirebase;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import cat.itb.pixiv.ClassesModels.IllustrationClass;
import cat.itb.pixiv.ClassesModels.User;
import cat.itb.pixiv.FireBase.FireBaseHelper;
import cat.itb.pixiv.R;

public class AdapterFavFragmentIlus extends RecyclerView.Adapter<ViewHolderIllustrationsRecommended> {
    private List<IllustrationClass> favlist;
    private Context context;

    public Context getContext() {
        return context;
    }
    public void setContext(Context context) {
        this.context = context;
    }

    public AdapterFavFragmentIlus(List<IllustrationClass>list){
        this.favlist=list;
    }

    @NonNull
    @Override
    public ViewHolderIllustrationsRecommended onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderIllustrationsRecommended(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_illustrations_recommended,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderIllustrationsRecommended holder, int position) {
        IllustrationClass model=favlist.get(position);
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

