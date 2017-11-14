package com.example.cipowela.skos;

/**
 * Created by cipowela on 14/11/17.
 */

public class TestModel {
    private int gambar;
    private String nama, tipe, harga;

    public TestModel(int gambar, String nama, String tipe, String harga) {
        this.gambar = gambar;
        this.nama = nama;
        this.tipe = tipe;
        this.harga = harga;
    }

    public int getGambar() {
        return gambar;
    }

    public String getNama() {
        return nama;
    }

    public String getTipe() {
        return tipe;
    }

    public String getHarga() {
        return harga;
    }
}
