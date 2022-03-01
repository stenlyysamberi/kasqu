package com.example.kasqu.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("result")
    @Expose
    private String result;

    @SerializedName("message")
    @Expose
    private String message;

    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
