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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cipowela.skos.ApiSKOS;
import com.example.cipowela.skos.R;
import com.example.cipowela.skos.KamarModel;
import com.example.cipowela.skos.adapter.DaftarKosAdapter;
import com.example.cipowela.skos.fragment.menu.userkos.FilterSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    private RecyclerView view;
    private ProgressBar bar;
    private List<KamarModel> kamarModels = new ArrayList<>();
    private DaftarKosAdapter kosAdapter;
    private KamarModel kamarModel;
    private RequestQueue queue;
    private SharedPreferences sharedPreferences;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        kamarModels.clear();
        view = (RecyclerView) v.findViewById(R.id.rv_daftar_kos);
        view.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        kosAdapter = new DaftarKosAdapter(getActivity(), kamarModels);
        view.setAdapter(kosAdapter);
        bar = v.findViewById(R.id.progress_bar);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        queue = Volley.newRequestQueue(getActivity());
        getData();
        return v;
    }

    private void getData() {
        String harga = sharedPreferences.getString("harga", "kosong");
        int sisa = sharedPreferences.getBoolean("sisa_kamar", false) ? 1 : 0;

        kamarModels.clear();

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, ApiSKOS.getAllKamars(harga, sisa), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Toast.makeText(getActivity(), response.getString("count"), Toast.LENGTH_SHORT).show();
                            JSONArray data = response.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject kamar = data.getJSONObject(i);
                                Log.d("data", kamar.toString());
                                JSONObject owner = kamar.getJSONObject("owner");
                                kamarModel = new KamarModel(
                                        kamar.getString("cover"),
                                        owner.getString("nama_kos"),
                                        kamar.getString("jenis") + " - " + kamar.getString("tipe"),
                                        kamar.getString("harga") + " - sisa "
                                            + kamar.getString("jumlah") + " kamar",
                                        kamar.toString());
                                kamarModels.add(kamarModel);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        bar.setVisibility(View.GONE);
                        kosAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        queue.add(request);
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

        if (item.getItemId() == R.id.filter_refresh) {
            bar.setVisibility(View.VISIBLE);
            getData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
