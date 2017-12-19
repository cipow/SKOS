package com.example.cipowela.skos.subactivity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cipowela.skos.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DetailKosActivity extends AppCompatActivity {

    private ImageView cover, foto_owner;
    private TextView nama_owner, alamat, telepon, lain, nama_kos, tipe, harga, sisa, fasilitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        String text = "S KOS";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#000000"))
                , 0, text.length(), 0);
        getSupportActionBar().setTitle(spannableString);

        initObject();
        setData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void initObject() {
        cover = findViewById(R.id.cover_kos);
        foto_owner = findViewById(R.id.gambar_pemilik_kos);

        nama_owner = findViewById(R.id.nama_pemilik_kos);
        alamat = findViewById(R.id.alamat_pemilik_kos);
        telepon = findViewById(R.id.nomor_pemilik_kos);
        lain = findViewById(R.id.lainnya_pemilik_kos);

        nama_kos = findViewById(R.id.nama_kos);
        tipe = findViewById(R.id.tipe_n_jenis);
        harga = findViewById(R.id.harga_kamar);
        sisa = findViewById(R.id.sisa_kamar);
        fasilitas = findViewById(R.id.fasilitas_kamar);
    }

    private void setData() {
        Intent intent = getIntent();
        try {
            JSONObject kamar = new JSONObject(intent.getStringExtra("data"));
            JSONObject owner = kamar.getJSONObject("owner");

            Glide.with(this).load(kamar.getString("cover"))
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(cover);

            Glide.with(this).load(owner.getString("foto"))
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(foto_owner);

            nama_owner.setText(owner.getString("nama"));
            alamat.setText(owner.getString("alamat"));
            telepon.setText(owner.getString("telepon"));
            lain.setText(owner.getString("lain_lain"));

            nama_kos.setText(owner.getString("nama_kos"));
            tipe.setText(kamar.getString("jenis") + " - " + kamar.getString("tipe"));
            harga.setText("Rp " + kamar.getString("harga") + " /bulan");
            sisa.setText(kamar.getString("jumlah") + " kamar tersisa");

            StringBuilder sb = new StringBuilder();
            String[] daftarFasilitas = kamar.getString("fasilitas").split(",");
            for (String s : daftarFasilitas) {
                sb.append(s + "\n");
            }

            fasilitas.setText(sb.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
