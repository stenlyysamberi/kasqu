package com.example.kasqu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mutasi {

    @SerializedName("jumlah_pemasukan")
    @Expose
    private int jumlah;

    @SerializedName("nama_usaha")
    @Expose
    private String nama_usaha;

    public String getNama_usaha() {
        return nama_usaha;
    }

    @SerializedName("nama")
    @Expose
    private String sub_judul;

    @SerializedName("tgl")
    @Expose
    private String tgl_mutasi;

    @SerializedName("status")
    @Expose
    private String status;

    public int getJumlah() {
        return jumlah;
    }

    public String getSub_judul() {
        return sub_judul;
    }

    public String getTgl_mutasi() {
        return tgl_mutasi;
    }

    public String getStatus() {
        return status;
    }
}
