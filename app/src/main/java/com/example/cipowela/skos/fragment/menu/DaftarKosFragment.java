package com.example.cipowela.skos.fragment.menu;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cipowela.skos.R;
import com.example.cipowela.skos.TestModel;
import com.example.cipowela.skos.adapter.DaftarKosAdapter;
import com.example.cipowela.skos.fragment.menu.listkos.FilterSettings;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaftarKosFragment extends Fragment {
    private RecyclerView view;
    private List<TestModel> testModels = new ArrayList<>();
    private DaftarKosAdapter kosAdapter;

    public DaftarKosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_daftar_kos, container, false);
        view = (RecyclerView) v.findViewById(R.id.rv_daftar_kos);
        kosAdapter = new DaftarKosAdapter(getActivity(), testModels);
        view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        view.setAdapter(kosAdapter);
        isiData();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Log.d("M-harga", sharedPreferences.getString("harga","kosong"));
        Log.d("sisa", String.valueOf(sharedPreferences.getBoolean("sisa",false)));
        Log.d("ordered", sharedPreferences.getString("ordered","kosong"));

        return v;
    }

    private void isiData() {
        TestModel test;

        for (int i = 0; i < 4; i++) {
            test = new TestModel(R.drawable.ruby, "Kos Permata", "putra - normal", "Rp 400.000/bln - sisa: 2 kamar");
            testModels.add(test);

            test = new TestModel(R.drawable.ruby, "Kos Permata", "putra - elite", "Rp 600.000/bln - sisa: 4 kamar");
            testModels.add(test);

            test = new TestModel(R.drawable.ruby, "Kos Perawan", "putri - normal", "Rp 500.000/bln - sisa: 5 kamar");
            testModels.add(test);
        }

        kosAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.filter_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.filter_daftar) {
            startActivity(new Intent(getActivity(), FilterSettings.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
