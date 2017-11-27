package com.example.cipowela.skos.fragment.menu.ownerkos;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cipowela.skos.R;
import com.example.cipowela.skos.subactivity.ProfilEditActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profil extends Fragment {


    public Profil() {
        // Required empty public constructor
    }

    ImageView image, image_change;
    TextView username, nama, telepon, alamat, lain, nama_kos, jmlh_kamar;
    View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profil, container, false);
        initObject();
        image_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "ganti foto profil", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    private void initObject() {
        image = (ImageView) v.findViewById(R.id.profil_image);
        image_change = (ImageView) v.findViewById(R.id.profil_change_image);
        username = (TextView) v.findViewById(R.id.profil_username);
        nama = (TextView) v.findViewById(R.id.profil_nama);
        telepon = (TextView) v.findViewById(R.id.profil_telepon);
        alamat = (TextView) v.findViewById(R.id.profil_alamat);
        lain = (TextView) v.findViewById(R.id.profil_lain);
        nama_kos = (TextView) v.findViewById(R.id.profil_nama_kos);
        jmlh_kamar = (TextView) v.findViewById(R.id.profil_jumlah_kamar);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profil_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.profil_edit) {
            getActivity().startActivity(new Intent(getActivity(), ProfilEditActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
