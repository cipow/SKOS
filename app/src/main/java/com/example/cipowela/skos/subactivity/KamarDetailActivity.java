package com.example.cipowela.skos.subactivity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cipowela.skos.R;

public class KamarDetailActivity extends AppCompatActivity {

    ImageView cover, cover_change;
    TextView tipe, jenis, harga, fasilitas, jumlah, penginap, sisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamar_detail);
        settingToolbar();
        initObject();
        cover_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(KamarDetailActivity.this, "ganti cover", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.kamar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_kamar:
                Toast.makeText(this, "hapus kamar", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.edit_kamar:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void settingToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        String text = "Detail Kamar";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#000000"))
                , 0, text.length(), 0);
        getSupportActionBar().setTitle(spannableString);
    }

    private void initObject() {
        cover = (ImageView) findViewById(R.id.cover_image);
        cover_change = (ImageView) findViewById(R.id.cover_change);
        tipe = (TextView) findViewById(R.id.kamar_detail_tipe);
        jenis = (TextView) findViewById(R.id.kamar_detail_jenis);
        harga = (TextView) findViewById(R.id.kamar_detail_harga);
        fasilitas = (TextView) findViewById(R.id.kamar_detail_fasilitas);
        jumlah = (TextView) findViewById(R.id.kamar_detail_jumlah);
        penginap = (TextView) findViewById(R.id.kamar_detail_penginap);
        sisa = (TextView) findViewById(R.id.kamar_detail_sisa);
    }
}
