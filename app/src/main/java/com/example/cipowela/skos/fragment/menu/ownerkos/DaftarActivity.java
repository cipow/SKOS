package com.example.cipowela.skos.fragment.menu.ownerkos;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import com.example.cipowela.skos.R;

public class DaftarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String text = "Daftar S KOS";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF80470A"))
                , 0, text.length(), 0);
        getSupportActionBar().setTitle(spannableString);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
