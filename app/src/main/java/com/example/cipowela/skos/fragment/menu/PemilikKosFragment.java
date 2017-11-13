package com.example.cipowela.skos.fragment.menu;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cipowela.skos.DaftarActivity;
import com.example.cipowela.skos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PemilikKosFragment extends Fragment {

    public static String PemKosPrefs = "file.pemilik.kos.prefs";

    private TextView textView;
    private Button login, daftar;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public PemilikKosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;
        preferences = getActivity().getSharedPreferences(PemKosPrefs, 0);

        if (preferences.contains("user")) {
            v = inflater.inflate(R.layout.fragment_pemilik_kos, container, false);
        } else {
            v = inflater.inflate(R.layout.fragment_pemilik_kos_login, container, false);
            login = (Button) v.findViewById(R.id.pemilik_login);
            daftar = (Button) v.findViewById(R.id.daftar_pemilik_kos);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor = preferences.edit();
                    editor.putString("user", "user");
                    editor.apply();
                    Toast.makeText(getActivity(), preferences.getString("user",""), Toast.LENGTH_SHORT).show();
                }
            });

            daftar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), DaftarActivity.class));
                }
            });
        }

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getActivity().getSharedPreferences(PemKosPrefs, 0);
        if (preferences.contains("user")) {
            setHasOptionsMenu(true);
        } else {
            setHasOptionsMenu(false);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.pemilik_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.setting_menu) {
            Toast.makeText(getActivity(), "klik setting", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (item.getItemId() == R.id.logout_pemilik) {
            preferences = getActivity().getSharedPreferences(PemKosPrefs, 0);
            editor = preferences.edit();
            editor.remove("user");
            editor.apply();
            Toast.makeText(getActivity(), "klik logout", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
