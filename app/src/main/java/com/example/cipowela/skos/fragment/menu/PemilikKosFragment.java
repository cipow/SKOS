package com.example.cipowela.skos.fragment.menu;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cipowela.skos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PemilikKosFragment extends Fragment {

    private TextView textView;
    private Button login;

    public PemilikKosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pemilik_kos_login, container, false);
        login = (Button) v.findViewById(R.id.pemilik_login);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
            Toast.makeText(getActivity(), "klik logout", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
