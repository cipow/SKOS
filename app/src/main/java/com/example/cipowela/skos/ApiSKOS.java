package com.example.cipowela.skos;

/**
 * Created by cipowela on 19/12/17.
 */

public class ApiSKOS {
    public static String root_url = "http://cipowela.000webhostapp.com/skos/public";

    public static String getAllKamars(String harga, int sisa) {
        String url = root_url + "/api/kamars" + "?harga=" + harga + "&sisa=" + sisa;
        return url;
    }

    public static String owners() {
        return root_url + "/api/owners";
    }

    public static String auth() {
        return owners() + "/auth";
    }

    public static String kamarOwners() {
        return owners() + "/kamars";
    }
}
