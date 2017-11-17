package com.example.cipowela.skos.fragment.menu.listkos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cipowela.skos.R;

public class DetailKosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
