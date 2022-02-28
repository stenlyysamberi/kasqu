package com.example.kasqu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Akun {
    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("gambar")
    @Expose
    private String gambar;

    public String getNama() {
        return nama;
    }

    public String getPhone() {
        return phone;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getGambar() {
        return gambar;
    }
}
