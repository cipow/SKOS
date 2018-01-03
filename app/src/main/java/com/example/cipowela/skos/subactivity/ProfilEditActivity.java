package com.example.cipowela.skos.subactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
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
import com.example.cipowela.skos.R;
import com.example.cipowela.skos.fragment.menu.ownerkos.Login;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfilEditActivity extends AppCompatActivity {

    private TextInputLayout namaLayout, teleponLayout, namaKosLayout, alamatLayout, lainLayout;
    private TextInputEditText nama, telepon, namaKos, alamat, lain;
    private RequestQueue queue;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_edit);
        settingToolbar();
        initObject();
        setTextChangeListener();

        Intent intent = getIntent();
        nama.setText(intent.getStringExtra("nama"));
        telepon.setText(intent.getStringExtra("telepon"));
        alamat.setText(intent.getStringExtra("alamat"));
        namaKos.setText(intent.getStringExtra("nama_kos"));
        lain.setText(intent.getStringExtra("lain_lain"));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profil_edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.profil_edit_save) {
            if (checkForm()) {
                String text = "Sending";
                SpannableString spannableString = new SpannableString(text);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FFFFFF"))
                        , 0, text.length(), 0);
                final Snackbar snackbar = Snackbar.make(ll, text, BaseTransientBottomBar.LENGTH_INDEFINITE);
                snackbar.show();

                queue = Volley.newRequestQueue(this);
                StringRequest request = new StringRequest(
                        Request.Method.POST, ApiSKOS.editOwner(),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject status = new JSONObject(response);
                                    if (status.getString("status").equals("updated")) {
                                        Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                                        snackbar.dismiss();
                                        finish();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }

                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("nama", nama.getText().toString());
                        params.put("telepon", telepon.getText().toString());
                        params.put("alamat", alamat.getText().toString());
                        params.put("nama_kos", namaKos.getText().toString());
                        params.put("lain_lain", lain.getText().toString());

                        return params;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        SharedPreferences preferences = getSharedPreferences(Login.OwnnerKosPrefs, 0);
                        headers.put("Authorization", preferences.getString("token", ""));

                        return headers;
                    }
                };
                queue.add(request);
            }
            else
                Toast.makeText(this, "silahkan lengkapi dengan benar", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkForm() {
        if (namaLayout.getError().toString().isEmpty()
                && teleponLayout.getError().toString().isEmpty()
                && alamatLayout.getError().toString().isEmpty())
            return true;

        return false;
    }

    private void settingToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        String text = "Edit Info Profil";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#000000"))
                , 0, text.length(), 0);
        getSupportActionBar().setTitle(spannableString);
    }

    private void initObject() {
        namaLayout = (TextInputLayout) findViewById(R.id.IL_nama);
        nama = (TextInputEditText) findViewById(R.id.ET_nama);

        teleponLayout = (TextInputLayout) findViewById(R.id.IL_telpon);
        telepon = (TextInputEditText) findViewById(R.id.ET_telpon);

        namaKosLayout = (TextInputLayout) findViewById(R.id.IL_nama_kos);
        namaKos = (TextInputEditText) findViewById(R.id.ET_nama_kos);

        alamatLayout = (TextInputLayout) findViewById(R.id.IL_alamat);
        alamat = (TextInputEditText) findViewById(R.id.ET_alamat);

        lainLayout = (TextInputLayout) findViewById(R.id.IL_lain);
        lain = (TextInputEditText) findViewById(R.id.ET_lain);

        ll = findViewById(R.id.ll);
    }

    private void setTextChangeListener() {
        nama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > namaLayout.getCounterMaxLength())
                    namaLayout.setError("Max " + namaLayout.getCounterMaxLength() + " character");
                else if (s.length() == 0)
                    namaLayout.setError("Cannot Empty");
                else
                    namaLayout.setError("");
            }
        });

        telepon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > teleponLayout.getCounterMaxLength())
                    teleponLayout.setError("Max " + teleponLayout.getCounterMaxLength() + " number");
                else if (s.length() == 0)
                    teleponLayout.setError("Cannot Empty");
                else
                    teleponLayout.setError("");
            }
        });

        alamat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0)
                    alamatLayout.setError("Cannot Empty");
                else
                    alamatLayout.setError("");
            }
        });
    }
}
