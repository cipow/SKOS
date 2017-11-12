package com.example.cipowela.skos.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cipowela.skos.fragment.menu.DaftarKosFragment;
import com.example.cipowela.skos.fragment.menu.PemilikKosFragment;

/**
 * Created by cipowela on 12/11/17.
 */

public class TabViewAdapter extends FragmentPagerAdapter{

    private String[] data = {"Daftar Kos", "Pemilik Kos"};

    public TabViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DaftarKosFragment();
            case 1:
                return new PemilikKosFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data[position];
    }
}
