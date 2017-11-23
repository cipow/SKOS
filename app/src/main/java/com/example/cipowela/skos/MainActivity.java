package com.example.cipowela.skos;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.AutoScrollHelper;
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

import com.example.cipowela.skos.adapter.TabViewAdapter;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    NavigationView navigation;
    ViewPager pager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setIconActionBar();
        setTitleActionBar();

        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigation = (NavigationView) findViewById(R.id.navigationView);
        pager = (ViewPager) findViewById(R.id.viewPager);

        TabViewAdapter pagerAdapter = new TabViewAdapter(getSupportFragmentManager());
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

                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);

                switch (item.getItemId()) {
                    case R.id.guest_home:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.guest_login:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.guest_register:
                        pager.setCurrentItem(2);
                        break;
                }

                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // set toggle icon navbar
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
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
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF80470A"))
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
