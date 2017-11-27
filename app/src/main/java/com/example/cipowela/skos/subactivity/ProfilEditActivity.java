package com.example.cipowela.skos.subactivity;

import android.graphics.Color;
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
import android.widget.Toast;

import com.example.cipowela.skos.R;

public class ProfilEditActivity extends AppCompatActivity {

    private TextInputLayout namaLayout, teleponLayout, namaKosLayout, alamatLayout, lainLayout;
    private TextInputEditText nama, telepon, namaKos, alamat, lain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_edit);
        settingToolbar();
        initObject();
        setTextChangeListener();
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
            if (checkForm())
                Toast.makeText(this, "save data", Toast.LENGTH_SHORT).show();
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
