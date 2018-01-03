package com.example.cipowela.skos.subactivity;

import android.content.Intent;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cipowela.skos.R;

import org.json.JSONException;
import org.json.JSONObject;

public class KamarDetailActivity extends AppCompatActivity {

    ImageView cover, cover_change;
    TextView tipe, jenis, harga, fasilitas, jumlah, penginap, sisa;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamar_detail);
        settingToolbar();
        initObject();
        setData();

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
        intent = getIntent();
        cover = (ImageView) findViewById(R.id.cover_image);
        cover_change = (ImageView) findViewById(R.id.cover_change);
        tipe = (TextView) findViewById(R.id.kamar_detail_tipe);
        jenis = (TextView) findViewById(R.id.kamar_detail_jenis);
        harga = (TextView) findViewById(R.id.kamar_detail_harga);
        fasilitas = (TextView) findViewById(R.id.kamar_detail_fasilitas);
        jumlah = (TextView) findViewById(R.id.kamar_detail_jumlah);
    }

    private void setData() {
        try {
            JSONObject object = new JSONObject(intent.getStringExtra("data"));
            Glide.with(this).load(object.getString("cover"))
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(cover);
            tipe.setText(object.getString("tipe"));
            jenis.setText(object.getString("jenis"));
            harga.setText(object.getString("harga"));
            jumlah.setText(object.getString("jumlah"));

            StringBuilder sb = new StringBuilder();
            String[] daftarFasilitas = object.getString("fasilitas").split(",");
            for (String s : daftarFasilitas) {
                sb.append(s + "\n");
            }
            fasilitas.setText(sb.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
