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

    public List<Mitra> getMitra() {
        return mitra;
    }

    public void setMitra(List<Mitra> mitra) {
        this.mitra = mitra;
    }

    public List<Income> getIncome() {
        return income;
    }

    public void setIncome(List<Income> income) {
        this.income = income;
    }

    public List<Spent> getSpent() {
        return spent;
    }

    public void setSpent(List<Spent> spent) {
        this.spent = spent;
    }




}
