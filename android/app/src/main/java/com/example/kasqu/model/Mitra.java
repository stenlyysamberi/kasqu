package com.example.kasqu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mitra {

    private boolean isChecked = false;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @SerializedName("mitra_id")
    @Expose
    private String mitra_id;

    public void setMitra_id(String mitra_id) {
        this.mitra_id = mitra_id;
    }

    public void setNama_usaha(String nama_usaha) {
        this.nama_usaha = nama_usaha;
    }

    public String getMitra_id() {
        return mitra_id;
    }

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
