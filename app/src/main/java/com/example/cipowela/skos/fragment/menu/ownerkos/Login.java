package com.example.cipowela.skos.fragment.menu.ownerkos;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.cipowela.skos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {


    public Login() {
        // Required empty public constructor
    }

    public static String OwnnerKosPrefs = "com.example.cipowela.skos.ownerkos";

    private Button login;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        preferences = getActivity().getSharedPreferences(OwnnerKosPrefs, 0);


        login = (Button) v.findViewById(R.id.ownerkos_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = preferences.edit();
                editor.putString("user", "user");
                editor.apply();
                Toast.makeText(getActivity(), preferences.getString("user", ""), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

}
