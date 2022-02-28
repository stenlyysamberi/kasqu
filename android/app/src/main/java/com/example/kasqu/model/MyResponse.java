package com.example.kasqu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyResponse {
    @SerializedName("result")
    @Expose
    private String result;

    @SerializedName("message")
    @Expose
    private int message;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("nama")
    @Expose
    private String nama;

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getResult() {
        return result;
    }

    public int getMessage() {
        return message;
    }
}
