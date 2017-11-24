package com.example.cipowela.skos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cipowela.skos.fragment.menu.ownerkos.Login;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences(Login.OwnnerKosPrefs,0);

                if (preferences.contains("user"))
                    startActivity(new Intent(SplashScreen.this, MainOwnerKosActivity.class));
                else
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));

                finish();
            }
        }, 500);
    }
}
