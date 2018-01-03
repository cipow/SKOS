package com.example.cipowela.skos.fragment.menu.ownerkos;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cipowela.skos.ApiSKOS;
import com.example.cipowela.skos.KamarModel;
import com.example.cipowela.skos.R;
import com.example.cipowela.skos.adapter.KamarAdapter;
import com.example.cipowela.skos.subactivity.KamarAddActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private KamarAdapter adapter;
    private List<KamarModel> kamarModels = new ArrayList<>();
    private KamarModel model;
    private RequestQueue queue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_kamar, container, false);
        initObject();
        rv_kamar.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new KamarAdapter(getActivity(), kamarModels);
        rv_kamar.setAdapter(adapter);
        add_kamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), KamarAddActivity.class));
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void initObject() {
        rv_kamar = (RecyclerView) v.findViewById(R.id.kamar_rv);
        add_kamar = (FloatingActionButton) v.findViewById(R.id.fab_kamar_add);
        queue = Volley.newRequestQueue(getActivity());
    }

    private void getData() {
        kamarModels.clear();
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, ApiSKOS.kamarOwners(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray data = response.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject kamar = data.getJSONObject(i);
                                Log.d("data", kamar.toString());
                                model = new KamarModel(
                                        kamar.getString("cover"),
                                        "NULL",
                                        kamar.getString("jenis") + " - " + kamar.getString("tipe"),
                                        kamar.getString("harga") + " - sisa "
                                                + kamar.getString("jumlah") + " kamar",
                                        kamar.toString());
                                kamarModels.add(model);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                SharedPreferences preferences = getActivity().getSharedPreferences(Login.OwnnerKosPrefs, 0);
                headers.put("Authorization", preferences.getString("token", ""));

                return headers;
            }
        };

        queue.add(request);
    }

}
