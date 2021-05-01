package cat.itb.pixiv.Fragments.SubmitWorkFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;

import cat.itb.pixiv.R;

public class BottomSheet extends BottomSheetDialogFragment {

    MaterialButton illustrations;
    MaterialButton manga;
    MaterialButton novels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        illustrations = v.findViewById(R.id.illustrations_button);
        manga = v.findViewById(R.id.manga_button);
        novels = v.findViewById(R.id.novels_button);

        illustrations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentSubmitIllustrations()).commit();
                dismiss();

            }
        });

        manga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentSubmitManga()).commit();
                dismiss();

            }
        });

        novels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentSubmitNovels()).commit();
                dismiss();

            }
        });

        return v;
    }
}
