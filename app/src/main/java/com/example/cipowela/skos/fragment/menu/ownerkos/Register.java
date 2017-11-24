package com.example.cipowela.skos.fragment.menu.ownerkos;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cipowela.skos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {


    public Register() {
        // Required empty public constructor
    }

    private View v;
    private TextInputLayout usernameLayout, passwordLayout, teleponLayout, namaLayout, alamatLayout;
    private TextInputEditText username, password, telepon, nama, alamat;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_register, container, false);
        initObject();
        setTextChangeListener();

        return v;
    }

    private void initObject() {
        usernameLayout = (TextInputLayout) v.findViewById(R.id.IL_username);
        username = (TextInputEditText) v.findViewById(R.id.ET_username);

        passwordLayout = (TextInputLayout) v.findViewById(R.id.IL_password);
        password = (TextInputEditText) v.findViewById(R.id.ET_password);

        teleponLayout = (TextInputLayout) v.findViewById(R.id.IL_telpon);
        telepon = (TextInputEditText) v.findViewById(R.id.ET_telpon);

        namaLayout = (TextInputLayout) v.findViewById(R.id.IL_nama);
        nama = (TextInputEditText) v.findViewById(R.id.ET_nama);

        alamatLayout = (TextInputLayout) v.findViewById(R.id.IL_alamat);
        alamat = (TextInputEditText) v.findViewById(R.id.ET_alamat);
    }

    private void setTextChangeListener() {
        usernameLayout.setError("Min 6 character");
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > usernameLayout.getCounterMaxLength())
                    usernameLayout.setError("Max " + usernameLayout.getCounterMaxLength() + " character");
                else if (s.length() < 6)
                    usernameLayout.setError("Min 6 character");
                else
                    usernameLayout.setError(null);

            }
        });

        passwordLayout.setError("Min 8 character");
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > passwordLayout.getCounterMaxLength())
                    passwordLayout.setError("Max " + passwordLayout.getCounterMaxLength() + " character");
                else if (s.length() < 8)
                    passwordLayout.setError("Min 8 character");
                else
                    passwordLayout.setError(null);

            }
        });

        teleponLayout.setError("Cannot Empty");
        telepon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > teleponLayout.getCounterMaxLength())
                    teleponLayout.setError("Max " + teleponLayout.getCounterMaxLength() + " number");
                else if (s.length() == 0)
                    teleponLayout.setError("Cannot Empty");
                else
                    teleponLayout.setError(null);
            }
        });

        namaLayout.setError("Cannot Empty");
        nama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > namaLayout.getCounterMaxLength())
                    namaLayout.setError("Max " + namaLayout.getCounterMaxLength() + " character");
                else if (s.length() == 0)
                    namaLayout.setError("Cannot Empty");
                else
                    namaLayout.setError(null);
            }
        });

        alamatLayout.setError("Cannot Empty");
        alamat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0)
                    alamatLayout.setError("Cannot Empty");
                else
                    alamatLayout.setError(null);
            }
        });
    }

}
