package com.example.cipowela.skos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.cipowela.skos.adapter.GuestViewAdapter;
import com.example.cipowela.skos.adapter.OwnerKosViewAdapter;
import com.example.cipowela.skos.fragment.menu.ownerkos.Login;

public class MainOwnerKosActivity extends AppCompatActivity {

    DrawerLayout drawer;
    NavigationView navigation;
    ViewPager pager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_owner_kos);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setIconActionBar();
        setTitleActionBar();

        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigation = (NavigationView) findViewById(R.id.navigationView);
        pager = (ViewPager) findViewById(R.id.viewPager);

        OwnerKosViewAdapter pagerAdapter = new OwnerKosViewAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.profil:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.kamar:
                        pager.setCurrentItem(2);
                        break;
                    case R.id.logout:
                        SharedPreferences preferences = getSharedPreferences(Login.OwnnerKosPrefs,0);
                        preferences.edit().remove("user").apply();

                        Intent intent = new Intent(MainOwnerKosActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        break;
                    case R.id.menu_how_to_use:
                        pager.setCurrentItem(3);
                        break;
                    case R.id.menu_about:
                        pager.setCurrentItem(4);
                        break;
                }

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // set toggle icon navbar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.ic_drawer);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START))
                    drawer.closeDrawer(GravityCompat.START);
                else
                    drawer.openDrawer(GravityCompat.START);
            }
        });
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setIconActionBar() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.rsz_2skos);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    private void setTitleActionBar() {
        String text = "  S KOS";
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#000000"))
                , 0, text.length(), 0);
        getSupportActionBar().setTitle(spannableString);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}
