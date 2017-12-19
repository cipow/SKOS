package com.example.cipowela.skos.fragment.menu.ownerkos;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cipowela.skos.ApiSKOS;
import com.example.cipowela.skos.MainActivity;
import com.example.cipowela.skos.MainOwnerKosActivity;
import com.example.cipowela.skos.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {

    private EditText username, password;


    public Login() {
        // Required empty public constructor
    }

    public static String OwnnerKosPrefs = "com.example.cipowela.skos.ownerkos";

    private Button login;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        preferences = getActivity().getSharedPreferences(OwnnerKosPrefs, 0);

        username = v.findViewById(R.id.username);
        password = v.findViewById(R.id.password);


        login = (Button) v.findViewById(R.id.ownerkos_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Snackbar snackbar = Snackbar.make(v, "Sending...", Snackbar.LENGTH_INDEFINITE);
                snackbar.show();

                RequestQueue queue = Volley.newRequestQueue(getActivity());
                StringRequest request = new StringRequest(
                        Request.Method.POST, ApiSKOS.auth(),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject status = new JSONObject(response);
                                    if (status.getString("status").equals("success")) {
                                        Toast.makeText(getActivity(), "Login", Toast.LENGTH_SHORT).show();
                                        snackbar.dismiss();

                                        editor = preferences.edit();
                                        editor.putString("token", status.getString("api_token"));
                                        editor.apply();

                                        Intent intent = new Intent(getActivity(), MainOwnerKosActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        getActivity().startActivity(intent);
                                    } else if (status.getString("status").equals("no found")) {
                                        snackbar.dismiss();
                                        Toast.makeText(getActivity(), "username not found", Toast.LENGTH_SHORT).show();
                                    } else {
                                        snackbar.dismiss();
                                        Toast.makeText(getActivity(), "wrong password", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    snackbar.dismiss();
                                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                snackbar.dismiss();
                                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }

                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("username", username.getText().toString());
                        params.put("password", password.getText().toString());

                        return params;
                    }
                };
                queue.add(request);
            }
        });

        return v;
    }

}
