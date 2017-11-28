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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cipowela.skos.R;

public class KamarEditActivity extends AppCompatActivity {

    Spinner tipe;
    TextInputLayout jenisKamarLayout, hargaLayout, jumlahLayout, fasilitasLayout;
    TextInputEditText jenis, harga, jumlah, fasilitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamar_edit);
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
            Toast.makeText(this, "kamar di edit", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void settingToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        String text = "Edit Kamar";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#000000"))
                , 0, text.length(), 0);
        getSupportActionBar().setTitle(spannableString);
    }

    private void initObject() {
        tipe = (Spinner) findViewById(R.id.kamar_add_tipe);

        jenisKamarLayout = (TextInputLayout) findViewById(R.id.IL_jenis_kamar);
        jenis = (TextInputEditText) findViewById(R.id.ET_jenis_kamar);

        hargaLayout = (TextInputLayout) findViewById(R.id.IL_harga_kamar);
        harga = (TextInputEditText) findViewById(R.id.ET_harga_kamar);

        jumlahLayout = (TextInputLayout) findViewById(R.id.IL_jumlah_kamar);
        jumlah = (TextInputEditText) findViewById(R.id.ET_jumlah_kamar);

        fasilitasLayout = (TextInputLayout) findViewById(R.id.IL_fasilitas_kamar);
        fasilitas = (TextInputEditText) findViewById(R.id.ET_fasilitas_kamar);
    }

    private void setTextChangeListener() {
        jenis.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > jenisKamarLayout.getCounterMaxLength())
                    jenisKamarLayout.setError("Max " + jenisKamarLayout.getCounterMaxLength() + " character");
                else if (s.length() == 0)
                    jenisKamarLayout.setError("Cannot Empty");
                else
                    jenisKamarLayout.setError("");
            }
        });

        harga.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > hargaLayout.getCounterMaxLength())
                    hargaLayout.setError("Max " + hargaLayout.getCounterMaxLength() + " number");
                else if (s.length() == 0)
                    hargaLayout.setError("Cannot Empty");
                else
                    hargaLayout.setError("");
            }
        });

        jumlah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > jumlahLayout.getCounterMaxLength())
                    jumlahLayout.setError("Max " + jumlahLayout.getCounterMaxLength() + " number");
                else if (s.length() == 0)
                    jumlahLayout.setError("Cannot Empty");
                else
                    jumlahLayout.setError("");
            }
        });


        fasilitas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0)
                    fasilitasLayout.setError("Cannot Empty");
                else
                    fasilitasLayout.setError("");
            }
        });
    }
}
