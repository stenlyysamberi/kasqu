package com.example.kasqu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Spent {

    @SerializedName("jumlah_keluar")
    @Expose
    private String jumlah;

    @SerializedName("gambar")
    @Expose
    private String gambar_petugas;

    public String getGambar_petugas() {
        return gambar_petugas;
    }

    @SerializedName("nama")
    @Expose
    private String nama_petugas;

    @SerializedName("catatan")
    @Expose
    private String catatan;

    @SerializedName("create_at")
    @Expose
    private String tgl_keluar;

    public String getJumlah() {
        return jumlah;
    }

    public String getNama_petugas() {
        return nama_petugas;
    }

    public String getCatatan() {
        return catatan;
    }

    public String getTgl_keluar() {
        return tgl_keluar;
    }
}
