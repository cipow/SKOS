package com.example.cipowela.skos.fragment.menu.ownerkos;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cipowela.skos.MainActivity;
import com.example.cipowela.skos.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]{6,32}$";
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z]).{8,32})";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_register, container, false);
        initObject();
        setTextChangeListener();

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.send_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.send) {
            if (checkFormError()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Apakah anda yakin ?");
                builder.setMessage("pastikan data anda sudah sesuai dengan diri anda");
                builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.pager.setCurrentItem(1);
                        Toast.makeText(getActivity(), "kirim data ke server", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

            } else
                Toast.makeText(getActivity(), "Silahkan lengkapi data dengan benar", Toast.LENGTH_LONG).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean checkFormError() {

        if (usernameLayout.getError().toString().isEmpty()
                && passwordLayout.getError().toString().isEmpty()
                && teleponLayout.getError().toString().isEmpty()
                && namaLayout.getError().toString().isEmpty()
                && alamatLayout.getError().toString().isEmpty())
            return true;

        return false;
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
                Pattern pattern = Pattern.compile(USERNAME_PATTERN);
                Matcher matcher = pattern.matcher(s);

                if (s.length() > usernameLayout.getCounterMaxLength())
                    usernameLayout.setError("Max " + usernameLayout.getCounterMaxLength() + " character");
                else if (s.length() < 6)
                    usernameLayout.setError("Min 6 character");
                else if (!matcher.matches())
                    usernameLayout.setError("Hanya boleh huruf kecil, huruf besar, dan garis bawah (_)");
                else
                    usernameLayout.setError("");

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
                Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
                Matcher matcher = pattern.matcher(s);

                if (s.length() > passwordLayout.getCounterMaxLength())
                    passwordLayout.setError("Max " + passwordLayout.getCounterMaxLength() + " character");
                else if (s.length() < 8)
                    passwordLayout.setError("Min 8 character");
                else if (!matcher.matches())
                    passwordLayout.setError("Harus mengandung satu huruf kecil dan angka");
                else
                    passwordLayout.setError("");

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
                    teleponLayout.setError("");
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
                    namaLayout.setError("");
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
                    alamatLayout.setError("");
            }
        });
    }

}
