package com.example.cipowela.skos.fragment.menu.ownerkos;


import android.content.Intent;
import android.content.SharedPreferences;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cipowela.skos.ApiSKOS;
import com.example.cipowela.skos.R;
import com.example.cipowela.skos.subactivity.ProfilEditActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profil extends Fragment {


    public Profil() {
        // Required empty public constructor
    }

    ImageView image, image_change;
    TextView nama, telepon, alamat, lain, nama_kos, jmlh_kamar;
    View v;
    RequestQueue queue;
    SharedPreferences preferences;


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
        queue = Volley.newRequestQueue(getActivity());
        preferences = getActivity().getSharedPreferences(Login.OwnnerKosPrefs,0);
        return v;
    }

    private void getData() {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, ApiSKOS.owners(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Glide.with(getActivity()).load(response.getString("foto"))
                                    .thumbnail(0.5f)
                                    .crossFade()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(image);

                            nama.setText(response.getString("nama"));
                            telepon.setText(response.getString("telepon"));
                            alamat.setText(response.getString("alamat"));
                            lain.setText(response.getString("lain_lain"));
                            nama_kos.setText(response.getString("nama_kos"));
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
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", preferences.getString("token", ""));

                return headers;
            }
        };

        queue.add(request);
    }

    private void jumlahKamar() {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, ApiSKOS.kamarOwners(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            jmlh_kamar.setText(response.getString("count"));
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
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", preferences.getString("token", ""));

                return headers;
            }
        };

        queue.add(request);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
        jumlahKamar();
    }

    private void initObject() {
        image = (ImageView) v.findViewById(R.id.profil_image);
        image_change = (ImageView) v.findViewById(R.id.profil_change_image);
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
