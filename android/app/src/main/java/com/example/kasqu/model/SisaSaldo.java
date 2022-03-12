package com.example.kasqu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SisaSaldo {

    @SerializedName("saldo")
    @Expose
    private int sisa_saldo;

    public int getSisa_saldo() {
        return sisa_saldo;
    }
}
