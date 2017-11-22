package com.example.cipowela.skos.fragment.menu.ownerkos;

import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;

import com.example.cipowela.skos.R;

public class DaftarActivity extends AppCompatActivity {

    TextInputLayout usernameLayout, passwordLayout, teleponLayout, namaLayout, alamatLayout;
    TextInputEditText username, password, telepon, nama, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        changeTitleActionBar();
        initObject();
        setTextChangeListener();
    }

    private void changeTitleActionBar() {
        String text = "Daftar S KOS";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF80470A"))
                , 0, text.length(), 0);
        getSupportActionBar().setTitle(spannableString);
    }

    private void initObject() {
        usernameLayout = (TextInputLayout) findViewById(R.id.IL_username);
        username = (TextInputEditText) findViewById(R.id.ET_username);

        passwordLayout = (TextInputLayout) findViewById(R.id.IL_password);
        password = (TextInputEditText) findViewById(R.id.ET_password);

        teleponLayout = (TextInputLayout) findViewById(R.id.IL_telpon);
        telepon = (TextInputEditText) findViewById(R.id.ET_telpon);

        namaLayout = (TextInputLayout) findViewById(R.id.IL_nama);
        nama = (TextInputEditText) findViewById(R.id.ET_nama);

        alamatLayout = (TextInputLayout) findViewById(R.id.IL_alamat);
        alamat = (TextInputEditText) findViewById(R.id.ET_alamat);
    }

    private void setTextChangeListener() {
        usernameLayout.setError("Min 6 character");
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > usernameLayout.getCounterMaxLength())
                    usernameLayout.setError("Max " + usernameLayout.getCounterMaxLength() + " character");
                else if (s.length() < 6)
                    usernameLayout.setError("Min 6 character");
                else
                    usernameLayout.setError(null);

            }
        });

        passwordLayout.setError("Min 8 character");
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > passwordLayout.getCounterMaxLength())
                    passwordLayout.setError("Max " + passwordLayout.getCounterMaxLength() + " character");
                else if (s.length() < 8)
                    passwordLayout.setError("Min 8 character");
                else
                    passwordLayout.setError(null);

            }
        });

        teleponLayout.setError("Cannot Empty");
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
                    teleponLayout.setError(null);
            }
        });

        namaLayout.setError("Cannot Empty");
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
                    namaLayout.setError(null);
            }
        });

        alamatLayout.setError("Cannot Empty");
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
                    alamatLayout.setError(null);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
