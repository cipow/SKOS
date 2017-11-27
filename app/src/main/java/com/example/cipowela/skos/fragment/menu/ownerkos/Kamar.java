package com.example.cipowela.skos.fragment.menu.ownerkos;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cipowela.skos.R;
import com.example.cipowela.skos.subactivity.KamarAddActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Kamar extends Fragment {


    public Kamar() {
        // Required empty public constructor
    }

    private View v;
    private RecyclerView rv_kamar;
    private FloatingActionButton add_kamar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_kamar, container, false);
        initObject();
        add_kamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), KamarAddActivity.class));
            }
        });
        return v;
    }

    private void initObject() {
        rv_kamar = (RecyclerView) v.findViewById(R.id.kamar_rv);
        add_kamar = (FloatingActionButton) v.findViewById(R.id.fab_kamar_add);
    }

}
