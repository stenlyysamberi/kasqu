package com.example.kasqu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Income {
    @SerializedName("jumlah_pemasukan")
    @Expose
    private int jumlah;



    @SerializedName("updated_at")
    @Expose
    private String tgl_income;

    @SerializedName("nama_usaha")
    @Expose
    private String nama_store;

    @SerializedName("gambar")
    @Expose
    private String gambar_store;


    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getTgl_income() {
        return tgl_income;
    }

    public void setTgl_income(String tgl_income) {
        this.tgl_income = tgl_income;
    }

    public String getNama_store() {
        return nama_store;
    }

    public void setNama_store(String nama_store) {
        this.nama_store = nama_store;
    }

    public String getGambar_store() {
        return gambar_store;
    }

    public void setGambar_store(String gambar_store) {
        this.gambar_store = gambar_store;
    }

//    private Integer subtotal( Integer jumlah){
//        int total = getJumlah();
//        for (int i = 0; i<getJumlah(); i++){
//            int amount = i;
//            total += amount;
//        }
//
//        return total;
//    }
}
