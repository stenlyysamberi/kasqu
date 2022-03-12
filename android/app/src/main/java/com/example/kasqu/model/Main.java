package com.example.kasqu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Main extends Income {
    @SerializedName("mitra")
    @Expose
    private List<Mitra> mitra = null;

    @SerializedName("income")
    @Expose
    private List<Income> income = null;

    @SerializedName("spent")
    @Expose
    private List<Spent> spent = null;

    @SerializedName("mutasi_user")
    @Expose
    private List<Mutasi> mutasi_user = null;

    @SerializedName("sisa_saldo")
    @Expose
    private List<SisaSaldo> sisa_saldo = null;

    public List<SisaSaldo> getSisa_saldo() {
        return sisa_saldo;
    }

    public List<Mitra> getMitra() {
        return mitra;
    }

    public List<Income> getIncome() {
        return income;
    }

    public List<Spent> getSpent() {
        return spent;
    }

    public List<Mutasi> getMutasi_user() {
        return mutasi_user;
    }
}
