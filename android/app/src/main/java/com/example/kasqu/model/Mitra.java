package com.example.kasqu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mitra {
    @SerializedName("nama_usaha")
    @Expose
    private String nama_usaha;

    @SerializedName("nama")
    @Expose
    private String nama_owner;

    @SerializedName("gambar")
    @Expose
    private String gambar;

    public String getNama_usaha() {
        return nama_usaha;
    }

    public String getNama_owner() {
        return nama_owner;
    }

    public String getGambar() {
        return gambar;
    }
}
