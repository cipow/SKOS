package com.example.cipowela.skos;

/**
 * Created by cipowela on 14/11/17.
 */

public class KamarModel {
    private String gambar, nama, tipe, harga, object;

    public KamarModel(String gambar, String nama, String tipe, String harga, String object) {
        this.gambar = gambar;
        this.nama = nama;
        this.tipe = tipe;
        this.harga = harga;
        this.object = object;
    }

    public String getGambar() {
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

    public String getObject() {
        return object;
    }
}
