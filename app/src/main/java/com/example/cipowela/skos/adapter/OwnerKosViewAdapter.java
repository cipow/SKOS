package com.example.cipowela.skos.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cipowela.skos.fragment.menu.About;
import com.example.cipowela.skos.fragment.menu.Home;
import com.example.cipowela.skos.fragment.menu.ownerkos.HowToUse;
import com.example.cipowela.skos.fragment.menu.ownerkos.Kamar;
import com.example.cipowela.skos.fragment.menu.ownerkos.Profil;

/**
 * Created by cipowela on 24/11/17.
 */

public class OwnerKosViewAdapter extends FragmentPagerAdapter {
    private String[] data = {"home", "profil", "kamar", "how", "about"};

    public OwnerKosViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Home();
            case 1:
                return new Profil();
            case 2:
                return new Kamar();
            case 3:
                return new HowToUse();
            case 4:
                return new About();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return data.length;
    }
}
