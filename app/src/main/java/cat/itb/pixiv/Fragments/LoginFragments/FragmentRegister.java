package cat.itb.pixiv.Fragments.LoginFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import cat.itb.pixiv.FireBase.FireBaseHelper;
import cat.itb.pixiv.Fragments.HomeFragment;
import cat.itb.pixiv.R;

public class FragmentRegister extends Fragment {
    TextInputLayout username;
    TextInputLayout password;
    TextInputLayout repeatPass;
    TextInputEditText usernameEditText;
    TextInputEditText passwordEditText;
    TextInputEditText repeatPassEditText;
    Button confirm;
    FireBaseHelper fireBaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        username = v.findViewById(R.id.input_layout_username);
        password = v.findViewById(R.id.input_layout_password);
        repeatPass = v.findViewById(R.id.input_layout_password_repeat);
        usernameEditText = v.findViewById(R.id.input_text_username);
        passwordEditText = v.findViewById(R.id.input_text_password);
        repeatPassEditText = v.findViewById(R.id.input_text_password_repeat);
        confirm = v.findViewById(R.id.registerButton);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (registerUser()){
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fragment_container, new HomeFragment()).commit();
                }
            }
        });

        return v;
    }

    public boolean registerUser() {
        if (usernameEditText.getText().toString().isEmpty()) {
            username.setError("Empty username");
            return false;
        } else {
            username.setError("");
            if (passwordEditText.getText().toString().isEmpty()) {
                password.setError("Insert a valid password");
                return false;
            } else if (passwordEditText.getText().toString().length() < 8) {
                password.setError("Insert a valid password");
                return false;
            } else {
                password.setError("");
                if (!passwordEditText.getText().toString().equals(repeatPassEditText.getText().toString())) {
                    repeatPass.setError("the passwords are different");
                    return false;
                } else {
                    repeatPass.setError("");
                    System.out.println(usernameEditText.getText().toString()+"                                    "+ passwordEditText.getText().toString());
                    fireBaseHelper.subirNuevoUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                    return true;
                }
            }
        }
    }
}
