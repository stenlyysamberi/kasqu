package com.example.kasqu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Income {
    @SerializedName("jumlah_pemasukan")
    @Expose
    private String jumlah;

    @SerializedName("updated_at")
    @Expose
    private String tgl_income;

    public String getTgl_income() {
        return tgl_income;
    }

    @SerializedName("nama_usaha")
    @Expose
    private String nama_store;

    @SerializedName("gambar")
    @Expose
    private String gambar_store;


    public String getJumlah() {
        return jumlah;
    }

    public String getNama_store() {
        return nama_store;
    }

    public String getGambar_store() {
        return gambar_store;
    }
}
