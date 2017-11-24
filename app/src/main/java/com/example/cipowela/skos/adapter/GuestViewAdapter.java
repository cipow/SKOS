package com.example.cipowela.skos.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cipowela.skos.fragment.menu.About;
import com.example.cipowela.skos.fragment.menu.Home;
import com.example.cipowela.skos.fragment.menu.PemilikKosFragment;
import com.example.cipowela.skos.fragment.menu.ownerkos.Login;
import com.example.cipowela.skos.fragment.menu.ownerkos.Register;

/**
 * Created by cipowela on 12/11/17.
 */

public class GuestViewAdapter extends FragmentPagerAdapter{

    private String[] data = {"home", "login", "register", "about"};

    public GuestViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Home();
            case 1:
                return new Login();
            case 2:
                return new Register();
            case 3:
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
